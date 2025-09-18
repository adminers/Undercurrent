package com.qiaweidata.mulit;

import java.sql.*;
import java.util.concurrent.*;
import java.util.Random;

public class MultiThreadUpdateTest {
    // 数据库连接参数 - 请根据你的环境修改
    private static final String DB_URL = "jdbc:mysql://192.168.1.6:3306/snowy_shen?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true&useInformationSchema=true&rewriteBatchedStatements=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";
    
    // 线程池大小
    private static final int THREAD_POOL_SIZE = 10;
    
    // 测试数据量
    private static final int TEST_DATA_SIZE = 100;
    
    public static void main(String[] args) {
        // 初始化数据库连接池
        ConnectionPool connectionPool = new ConnectionPool(THREAD_POOL_SIZE);
        
        try {
            // 准备测试数据
            prepareTestData(connectionPool);
            
            // 创建线程池
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            CountDownLatch latch = new CountDownLatch(THREAD_POOL_SIZE);
            
            System.out.println("开始多线程更新测试...");
            long startTime = System.currentTimeMillis();
            
            // 提交任务到线程池
            for (int i = 0; i < THREAD_POOL_SIZE; i++) {
                executor.submit(new UpdateTask(i, connectionPool, latch));
            }
            
            // 等待所有线程完成
            latch.await();
            long endTime = System.currentTimeMillis();
            
            System.out.println("所有线程完成，总耗时: " + (endTime - startTime) + "ms");
            
            // 验证结果
            verifyResults(connectionPool);
            
            // 关闭线程池和连接池
            executor.shutdown();
            connectionPool.closeAllConnections();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // 准备测试数据
    private static void prepareTestData(ConnectionPool pool) throws SQLException {
        try (Connection conn = pool.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // 清空表
            stmt.executeUpdate("TRUNCATE TABLE wd_item_lc");
            
            // 插入测试数据
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO wd_item_lc (name, quantity, price) VALUES (?, ?, ?)");
            
            for (int i = 1; i <= TEST_DATA_SIZE; i++) {
                pstmt.setString(1, "Item_" + i);
                pstmt.setInt(2, 100);
                pstmt.setDouble(3, i * 10.0);
                pstmt.addBatch();
                
                if (i % 50 == 0) {
                    pstmt.executeBatch();
                }
            }
            pstmt.executeBatch();
            conn.commit();
            
            System.out.println("已插入 " + TEST_DATA_SIZE + " 条测试数据");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    // 验证结果
    private static void verifyResults(ConnectionPool pool) throws SQLException, InterruptedException {
        try (Connection conn = pool.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total, SUM(quantity) as sum_qty FROM wd_item_lc")) {
            
            if (rs.next()) {
                int total = rs.getInt("total");
                int sumQty = rs.getInt("sum_qty");
                
                System.out.println("验证结果 - 总记录数: " + total + ", 数量总和: " + sumQty);
                
                // 每个线程更新了10条记录，每条记录数量减少1，所以总和应该减少10*THREAD_POOL_SIZE
                int expectedSum = TEST_DATA_SIZE * 100 - 10 * THREAD_POOL_SIZE;
                
                if (sumQty == expectedSum) {
                    System.out.println("✓ 测试通过: 数量总和符合预期");
                } else {
                    System.out.println("✗ 测试失败: 预期数量总和 " + expectedSum + ", 实际 " + sumQty);
                }
            }
        }
    }
    
    // 更新任务
    static class UpdateTask implements Runnable {
        private final int threadId;
        private final ConnectionPool pool;
        private final CountDownLatch latch;
        private final Random random = new Random();
        
        public UpdateTask(int threadId, ConnectionPool pool, CountDownLatch latch) {
            this.threadId = threadId;
            this.pool = pool;
            this.latch = latch;
        }
        
        @Override
        public void run() {
            try (Connection conn = pool.getConnection()) {
                // 每个线程更新10条不同的记录
                for (int i = 1; i <= 10; i++) {
                    int itemId = threadId * 10 + i; // 确保每个线程更新不同的ID
                    
                    // 执行更新操作
                    try (PreparedStatement pstmt = conn.prepareStatement(
                         "UPDATE wd_item_lc SET quantity = quantity - 1 WHERE id = ?")) {
                        pstmt.setInt(1, itemId);
                        int rowsAffected = pstmt.executeUpdate();
                        
                        if (rowsAffected == 0) {
                            System.out.println("线程 " + threadId + ": 更新ID " + itemId + " 失败，记录不存在");
                        } else {
                            System.out.println("线程 " + threadId + ": 成功更新ID " + itemId);
                        }
                    }
                    
                    // 随机延迟，模拟真实场景
//                    Thread.sleep(random.nextInt(50));
                }
                
                // 提交事务
                conn.commit();
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }
    
    // 简单的数据库连接池
    static class ConnectionPool {
        private final BlockingQueue<Connection> pool;
        
        public ConnectionPool(int size) {
            size *= 10;
            this.pool = new LinkedBlockingQueue<>(size);
            try {
                for (int i = 0; i < size; i++) {
                    Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                    conn.setAutoCommit(false); // 关闭自动提交，便于控制事务
                    pool.offer(conn);
                }
            } catch (SQLException e) {
                throw new RuntimeException("初始化数据库连接池失败", e);
            }
        }
        
        public Connection getConnection() throws InterruptedException {
            return pool.take();
        }
        
        public void releaseConnection(Connection conn) {
            try {
                pool.put(conn);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        public void closeAllConnections() {
            for (Connection conn : pool) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // 静态块加载JDBC驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("找不到MySQL JDBC驱动", e);
        }
    }
}