package com.keta.generate.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import com.keta.generate.MyDataSource;
import com.keta.generate.util.ConvertHandler;

/**
 * SqlServer Metadata读取 2018-11-14
 */
public class SqlServer extends MyDataSource
{
    @Override
    public Table getTable(String tableName)
        throws SQLException
    {
        ResultSet rs = null;
        try (Connection connection = getConnection())
        {
            Table t = new Table(tableName);
            t.setColumns(new ArrayList<Column>());
            DatabaseMetaData dmd = connection.getMetaData();// 获取数据库的MataData信息
            rs = dmd.getColumns(null, null, tableName, null);
            getColumns(rs, t);
            rs = dmd.getPrimaryKeys(null, null, tableName);
            t.setPk(getPk(rs));
            ConvertHandler.tableHandle(t);
            return t;
        }
        finally
        {
            close(rs);
        }
    }
    
    @Override
    public Set<String> getAllTableName()
        throws SQLException
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try (Connection connection = getConnection())
        {
            Set<String> tableNameSet = new TreeSet<>();
            ps = connection.prepareStatement("select name from sysobjects where xtype='u'");
            rs = ps.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next())
            {
                for (int i = 1; i <= columnCount; i++)
                {
                    Object obj = rs.getObject(i);
                    tableNameSet.add(obj.toString());
                }
            }
            return tableNameSet;
        }
        finally
        {
            close(rs);
            close(ps);
        }
    }
    
    /**
     * 获取所有列
     * 
     * @param rs
     * @param t
     * @throws SQLException
     */
    private void getColumns(ResultSet rs, Table t)
        throws SQLException
    {
        while (rs.next())
        {
            // 这里没有提供获取当前列是否主键/外键的信息提示
            Column col = new Column();
            col.setName(rs.getString("COLUMN_NAME"));
            col.setType(rs.getString("TYPE_NAME"));
            col.setSize(rs.getInt("COLUMN_SIZE"));
            col.setNullable(rs.getBoolean("NULLABLE"));
            col.setDigits(rs.getInt("DECIMAL_DIGITS"));
            col.setDefaultValue(rs.getString("COLUMN_DEF"));
            col.setComment(rs.getString("REMARKS"));
            t.getColumns().add(col);
        }
    }
    
    /**
     * 获取主键
     * 
     * @param rs
     * @return
     * @throws SQLException
     */
    private Column getPk(ResultSet rs)
        throws SQLException
    {
        Column pk = new Column();
        while (rs.next())
        {
            pk.setName(rs.getString("COLUMN_NAME"));
        }
        return pk;
    }
}
