/**
  * Copyright 2023 bejson.com 
  */
package com.qiaweidata.undercurrent.pojo.ai;

/**
 * Auto-generated: 2023-05-04 15:28:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private String message;
    private Result result;
    private int status;
    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setResult(Result result) {
         this.result = result;
     }
     public Result getResult() {
         return result;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

}