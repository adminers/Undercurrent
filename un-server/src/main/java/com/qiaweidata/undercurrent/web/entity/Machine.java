package com.qiaweidata.undercurrent.web.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Title: Machine
 * @Description: Machine
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2023-02-17
 * @version: V1.0
 */
public class Machine implements Serializable {

    @Serial
    private static final long serialVersionUID = -3231415588312540635L;

    private String id;
    private String systemName;
    private String parentId;
    private String iconPath;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;

    /**
     * 获取
     *
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     *
     * @return systemName
     */
    public String getSystemName() {
        return this.systemName;
    }

    /**
     * 设置
     *
     * @param systemName
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     * 获取
     *
     * @return parentId
     */
    public String getParentId() {
        return this.parentId;
    }

    /**
     * 设置
     *
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取
     *
     * @return iconPath
     */
    public String getIconPath() {
        return this.iconPath;
    }

    /**
     * 设置
     *
     * @param iconPath
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    /**
     * 获取
     *
     * @return createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取
     *
     * @return createUser
     */
    public String getCreateUser() {
        return this.createUser;
    }

    /**
     * 设置
     *
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取
     *
     * @return updateTime
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置
     *
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取
     *
     * @return updateUser
     */
    public String getUpdateUser() {
        return this.updateUser;
    }

    /**
     * 设置
     *
     * @param updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
