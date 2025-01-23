package com.ming.rest.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JsonResult {
    private int code;//请求操作完成之后返回状态码： 成功：200 失败：500 没有登录：403
    private String msg;
    private Object data;

 public JsonResult(int code, String msg, Object data){
     this.code=code;
     this.msg=msg;
     this.data=data;

 }

    public static JsonResult error(String msg){
        return new JsonResult(500,msg,null);
    }
    public static JsonResult error(String msg, Object data){
     return  new JsonResult(500,msg,data);
    }

    public static JsonResult success(){
     return new JsonResult(200,"操作成功",null);
    }

    public static JsonResult success(Object data){
     return new JsonResult(200,"操作成功", data);
    }








}
