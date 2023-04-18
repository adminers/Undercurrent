package com.qiaweidata.un.h2;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import java.sql.SQLException;
import java.util.List;

/**
 * h2database
 * SequencePlugin idea
 *
 * @Title: StoreSystemFile
 * @Description: StoreSystemFile
 * @Company: www.wrenchdata.com
 * @author: shenshilong
 * @date: 2023-03-11
 * @version: V1.0
 */
public class StoreSystemFile {

    private Db db;

    public StoreSystemFile() {
        this.db = Db.use();
    }

    public Db getDb() {
        return this.db;
    }

    public static void main(String[] args) throws SQLException {

        //查询
        List<Entity> query = Db.use().query("select * from wd_sys_user where id = ?", "4028818670ecf3390170ecf396220001");
        System.out.println(query);
    }
}
