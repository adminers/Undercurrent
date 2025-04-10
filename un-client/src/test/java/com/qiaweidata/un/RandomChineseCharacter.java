package com.qiaweidata.un;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomChineseCharacter {
    public static void main(String[] args) {

        StringBuilder sql = new StringBuilder("INSERT INTO 人类 (id, name) ");
        int i = 0;
        List<String> datas = new ArrayList<>();
        for (; i < 80000; i++) {
            char randomChar = charRandom();
            sql.append("VALUES('").append(i).append("', '").append(randomChar).append("人").append("'),\n");
            datas.add(randomChar + "人");
        }
        sql.append("VALUES('").append(i++).append("', '").append("中国人").append("'),\n");
        sql.append("VALUES('").append(i++).append("', '").append("德国人").append("'),\n");
        sql.append("VALUES('").append(i++).append("', '").append("西班牙人").append("'),\n");
        sql.append("VALUES('").append(i++).append("', '").append("俄罗斯人").append("'),\n");
        datas.add("中国人");
        datas.add("德国人");
        datas.add("西班牙人");
        datas.add("俄罗斯人");
        System.out.println(sql);
        insertData(datas);
    }

     public static void insertData(List<String> datas) {

        // 数据库连接信息
        String url = "jdbc:mysql://192.168.1.6:3306/test"; // 数据库 URL
        String user = "root"; // 用户名
        String password = "admin"; // 密码

        // SQL 插入语句
        String sql = "INSERT INTO 人类 (id, name) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // 关闭自动提交，手动管理事务
            connection.setAutoCommit(false);
            int size = datas.size();
            for (int i = 0; i < size; i++) {

                // 设置参数
                preparedStatement.setString(1, String.valueOf(i));
                preparedStatement.setString(2, datas.get(i));

                // 将当前插入操作添加到批处理
                preparedStatement.addBatch();
                // 每 1000 条执行一次批处理
                if (i % 1000 == 0) {
                    preparedStatement.executeBatch(); // 执行批处理
                }
            }

            // 执行剩余的批处理
            preparedStatement.executeBatch();

            // 提交事务
            connection.commit();
            System.out.println("成功插入 " + size + " 条数据。");
        } catch (SQLException e) {
            e.printStackTrace();
            // 如果发生异常，回滚事务
        }
    }

    private static char charRandom() {

        Random random = new Random();

        // 汉字的 Unicode 范围是 0x4E00 到 0x9FA5
        int randomCodePoint = 0x4E00 + random.nextInt(0x9FA5 - 0x4E00 + 1);
        char randomChar = (char) randomCodePoint;
        System.out.println("随机生成的汉字是: " + randomChar);
        return randomChar;
    }
}