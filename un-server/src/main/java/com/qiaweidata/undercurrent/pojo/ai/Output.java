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
public class Output {

    private List<String> code;
    private List<CodeDict> codeDict;
    private int completion_token_num;
    private int errcode;
    private int prompt_token_num;
    public void setCode(List<String> code) {
         this.code = code;
     }
     public List<String> getCode() {
         return code;
     }

    public void setCodeDict(List<CodeDict> codeDict) {
         this.codeDict = codeDict;
     }
     public List<CodeDict> getCodeDict() {
         return codeDict;
     }

    public void setCompletion_token_num(int completion_token_num) {
         this.completion_token_num = completion_token_num;
     }
     public int getCompletion_token_num() {
         return completion_token_num;
     }

    public void setErrcode(int errcode) {
         this.errcode = errcode;
     }
     public int getErrcode() {
         return errcode;
     }

    public void setPrompt_token_num(int prompt_token_num) {
         this.prompt_token_num = prompt_token_num;
     }
     public int getPrompt_token_num() {
         return prompt_token_num;
     }

}