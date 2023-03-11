package com.qiaweidata.pojo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 文件/文件夹信息
 *
 * @Title: FolderInfo
 * @Description: FolderInfo
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2023-02-18
 * @version: V1.0
 */
public class FolderInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = -170898643171530131L;

    private String id;
    private String machineId;
    private String folderName;
    private String parentId;
    private String folderSort;
    private String iconPath;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;

    private long fileSize;

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
     * @return machineId
     */
    public String getMachineId() {
        return this.machineId;
    }

    /**
     * 设置
     *
     * @param machineId
     */
    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    /**
     * 获取
     *
     * @return folderName
     */
    public String getFolderName() {
        return this.folderName;
    }

    /**
     * 设置
     *
     * @param folderName
     */
    public void setFolderName(String folderName) {
        this.folderName = folderName;
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
     * @return folderSort
     */
    public String getFolderSort() {
        return this.folderSort;
    }

    /**
     * 设置
     *
     * @param folderSort
     */
    public void setFolderSort(String folderSort) {
        this.folderSort = folderSort;
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

    /**
     * 获取
     *
     * @return fileSize
     */
    public long getFileSize() {
        return this.fileSize;
    }

    /**
     * 设置
     *
     * @param fileSize
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
