package com.fly.utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fly.core.entity.Column;
import com.fly.core.entity.Table;

/**
 * 
 * Excel 数据库文件解析处理
 *
 * @author 00fly
 * @version [版本号, 2018年3月30日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SQLParseUtil extends SwingWorker<Void, Void>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SQLParseUtil.class);
    
    private LogMonitor logMonitor;
    
    private String path;
    
    public LogMonitor getLogMonitor()
    {
        return logMonitor;
    }
    
    public void setLogMonitor(LogMonitor logMonitor)
    {
        this.logMonitor = logMonitor;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public void setPath(String path)
    {
        this.path = path;
    }
    
    // 创建文件
    @Override
    protected Void doInBackground()
        throws Exception
    {
        exportSQL();
        return null;
    }
    
    /**
     * 导出sql
     * 
     * @param sqlPath
     * @see [类、类#方法、类#成员]
     */
    public void exportSQL()
    {
        logMonitor.setLoggerInfo("开始读取Excel文件...\n");
        try
        {
            File file = new File(path);
            File[] files;
            if (file.isDirectory())
            {
                files = file.listFiles();
            }
            else
            {
                files = new File[] {file};
            }
            int i = 0;
            for (File f1 : files)
            {
                if (f1.isFile() && !f1.isHidden() && f1.getName().endsWith(".xlsx"))
                {
                    i++;
                    logMonitor.setLoggerInfo("----------------------------------------\n");
                    logMonitor.setLoggerInfo("发现第" + i + "个Excel文件...\n");
                    logMonitor.setLoggerInfo("开始处理 《" + f1.getName() + "》\n");
                    List<Table> tables = readFile(f1);
                    if (!tables.isEmpty())
                    {
                        String sql = createTableForMysql(tables).toString();
                        logMonitor.setLoggerInfo(sql);
                        File sqlFile = new File(f1.getAbsolutePath().replace(".xlsx", ".sql"));
                        FileUtils.writeStringToFile(sqlFile, sql, "UTF-8");
                    }
                }
            }
            logMonitor.setLoggerInfo("----------------------------------------\n");
            logMonitor.setLoggerInfo("共" + i + "个Excel文件...\n");
            logMonitor.setLoggerInfo("----------------------------------------\n");
            logMonitor.setLoggerInfo(i > 0 ? "文件处理完成...\n" : "处理完成...\n");
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
            logMonitor.setLoggerInfo(e.getMessage());
        }
    }
    
    /**
     * 读取excel文件并解析
     * 
     * @param file
     * @see [类、类#方法、类#成员]
     */
    public List<Table> readFile(File file)
    {
        try (XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file)))
        {
            List<Table> tables = new ArrayList<>();
            String temp;
            String sqlType;
            String length;
            String tableName = "";
            Table table;
            Column column;
            for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++)
            {
                XSSFSheet st = wb.getSheetAt(sheetIndex);
                table = null;
                // 跳过第一行（rowIndex=0）标题
                for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++)
                {
                    XSSFRow row = st.getRow(rowIndex);
                    if (row == null)
                    {
                        continue;
                    }
                    temp = readCell(row, 0);
                    if (StringUtils.isNotEmpty(temp))
                    {
                        if (table != null)
                        {
                            tables.add(table);
                        }
                        tableName = temp;
                        table = new Table();
                        table.setName(tableName);
                        String comment = StringUtils.trimToEmpty(readCell(row, 1));
                        if (comment.length() > 0)
                        {
                            table.setComment(comment);
                        }
                    }
                    column = new Column();
                    column.setName(readCell(row, 3));
                    column.setComment(readCell(row, 4));
                    column.setPrimaryKey("√".equals(readCell(row, 5)));
                    sqlType = readCell(row, 6);
                    length = readCell(row, 7);
                    if (StringUtils.isBlank(length) || "0".equals(length) || StringUtils.containsAny(sqlType, "int", "datetime", "timestamp", "date", "smalldatetime"))
                    {
                        column.setSqlType(sqlType);
                    }
                    else
                    {
                        column.setSqlType(String.format("%s(%s)", sqlType, length));
                    }
                    if (table != null)
                    {
                        table.addColumn(column);
                    }
                }
                if (table != null)
                {
                    tables.add(table);
                }
            }
            return tables;
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }
    
    /**
     * 读取row行第index列表格文本内容
     * 
     * @param row
     * @param index
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String readCell(XSSFRow row, int index)
    {
        XSSFCell cell = row.getCell(index);
        if (cell != null)
        {
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            return StringUtils.trimToEmpty(cell.getStringCellValue());
        }
        return "";
    }
    
    /**
     * 在Mysql中建表
     * 
     * @param tables
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public static StringBuilder createTableForMysql(List<Table> tables)
    {
        StringBuilder creatTableSql = new StringBuilder();
        for (Table table : tables)
        {
            creatTableSql.append("DROP TABLE IF EXISTS `").append(table.getName()).append("`;\n");
            creatTableSql.append("CREATE TABLE ").append(table.getName()).append(" (");
            int index = 0;
            for (Column column : table.getColumns())
            {
                creatTableSql.append((index++) > 0 ? ",\n" : "\n").append(column.getName()).append("\t").append(column.getSqlType()).append("\t comment '").append(column.getComment()).append("'");
            }
            creatTableSql.append("\n) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 comment='").append(table.getComment()).append("';\n");
            for (Column column : table.getColumns())
            {
                if (column.isPrimaryKey())
                {
                    creatTableSql.append("alter table `").append(table.getName()).append("` add primary key(").append(column.getName()).append(");\n\n");
                }
            }
        }
        return creatTableSql;
    }
}