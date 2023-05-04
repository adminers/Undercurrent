/**
  * Copyright 2023 bejson.com 
  */
package com.qiaweidata.undercurrent.pojo.ai;
import java.util.List;

/**
 * Auto-generated: 2023-05-04 15:28:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Input {

    private int n;
    private List<String> stop;
    private String text;
    public void setN(int n) {
         this.n = n;
     }
     public int getN() {
         return n;
     }

    public void setStop(List<String> stop) {
         this.stop = stop;
     }
     public List<String> getStop() {
         return stop;
     }

    public void setText(String text) {
         this.text = text;
     }
     public String getText() {
         return text;
     }

}