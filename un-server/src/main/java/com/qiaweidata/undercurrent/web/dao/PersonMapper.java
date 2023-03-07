package com.qiaweidata.undercurrent.web.dao;

import com.qiaweidata.undercurrent.web.entity.Machine;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PersonMapper {

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param Machine
     */
    @Insert("INSERT INTO t_sys_machine (id,system_name, parent_id, icon_path, create_time, "
        + "create_user, update_time,update_user) VALUES(#{id}, #{systemName}, #{parentId}, #{iconPath}, "
        + "#{createTime}, #{createUser}, #{updateTime}, #{updateUser})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(Machine machine);

    /**
     * 更新操作
     *
     * @param Machine
     * @return 受影响的行数
     */
    @Update("update person set name=#{name},age=#{age} where id=#{id}")
    Long update(Machine machine);

    /**
     * 删除操作
     *
     * @param id
     * @return 受影响的行数
     */
    @Delete("delete from person where id=#{id}")
    Long delete(@Param("id") Long id);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select id,name,age from person")
    List<Machine> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param id
     * @return
     */
    @Select("select id,name,age from person where id=#{id}")
    Machine selectById(@Param("id") Long id);
}
