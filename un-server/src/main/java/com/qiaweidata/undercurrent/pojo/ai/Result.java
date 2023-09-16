/**
  * Copyright 2023 bejson.com 
  */
package com.qiaweidata.undercurrent.pojo.ai;
import java.util.Date;

/**
 * Auto-generated: 2023-05-04 15:28:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Result {

    private String app;
    private String created_at;
    private Input input;
    private Output output;
    private double process_time;
    private String task_id;
    private String updated_at;

    /**
     * 获取
     *
     * @return app
     */
    public String getApp() {
        return this.app;
    }

    /**
     * 设置
     *
     * @param app
     */
    public void setApp(String app) {
        this.app = app;
    }

    /**
     * 获取
     *
     * @return created_at
     */
    public String getCreated_at() {
        return this.created_at;
    }

    /**
     * 设置
     *
     * @param created_at
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     * 获取
     *
     * @return input
     */
    public Input getInput() {
        return this.input;
    }

    /**
     * 设置
     *
     * @param input
     */
    public void setInput(Input input) {
        this.input = input;
    }

    /**
     * 获取
     *
     * @return output
     */
    public Output getOutput() {
        return this.output;
    }

    /**
     * 设置
     *
     * @param output
     */
    public void setOutput(Output output) {
        this.output = output;
    }

    /**
     * 获取
     *
     * @return process_time
     */
    public double getProcess_time() {
        return this.process_time;
    }

    /**
     * 设置
     *
     * @param process_time
     */
    public void setProcess_time(double process_time) {
        this.process_time = process_time;
    }

    /**
     * 获取
     *
     * @return task_id
     */
    public String getTask_id() {
        return this.task_id;
    }

    /**
     * 设置
     *
     * @param task_id
     */
    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    /**
     * 获取
     *
     * @return updated_at
     */
    public String getUpdated_at() {
        return this.updated_at;
    }

    /**
     * 设置
     *
     * @param updated_at
     */
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}