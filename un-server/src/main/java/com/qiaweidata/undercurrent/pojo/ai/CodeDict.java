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
public class CodeDict {

    private String context;
    private double cum_log_probs;
    private String generated;
    public void setContext(String context) {
         this.context = context;
     }
     public String getContext() {
         return context;
     }

    public void setCum_log_probs(double cum_log_probs) {
         this.cum_log_probs = cum_log_probs;
     }
     public double getCum_log_probs() {
         return cum_log_probs;
     }

    public void setGenerated(String generated) {
         this.generated = generated;
     }
     public String getGenerated() {
         return generated;
     }

}