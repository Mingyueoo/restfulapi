package com.ming.rest.controller;

import com.ming.rest.domian.Employee;
import com.ming.rest.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class EmployeeController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "ok";
    }
/**
 * 获取所有员工
 * 1. 请求路径--确认资源--员工--/employees
 * 2. 请求方法--查询--GET
 * 3. 请求参数--无
 * 4. 请求响应--多个员工--List<Employee> --json
 * url: http://localhost:80/employees
 * */
//@RequestMapping(value = "/employees",method = RequestMethod.GET)
//@ResponseBody
//public List<Employee> list(){
//    //查询MySql数据库，得到员工列表
//    //假装查询数据库得到List集合
//    List<Employee> list = Arrays.asList(new Employee( 1L, "Ming1", 20), new Employee( 2L, "Ming2", 21));
//    return list;
//}
@RequestMapping(value = "/employees", method = RequestMethod.GET)
@ResponseBody
public JsonResult getList(){//工作中用这个，统一格式
    List<Employee> list = Arrays.asList(new Employee( 1L, "Ming1", 20), new Employee( 2L, "Ming2", 21));
    return JsonResult.success(list);

}
    /**
     * 新增员工
     * 1. 请求路径--确认资源--员工--/employees
     * 2. 请求方法--添加--POST
     * 3. 请求参数--员工相关属性-- 参数：name-age
     * 4. 请求响应--新增员工对象--Employee--json
     * url: http://localhost:80/employees
     * 注意事项：浏览器发出的请求都是GET Request，所以这边要借助接口测试工具postman
     * */
@RequestMapping(value = "/employees",method =RequestMethod.POST)
@ResponseBody
public Employee add(Employee e){
    //假装添加数据成功，返回自动增长id值
    //http://localhost:80/employees?name=Ming3&age=22
    e.setId(1L);
    return e;
}

/**
 * 更新员工
 * 1. 请求路径--确认资源--员工--/employees
 * 2. 请求方法--添加--PUT
 * 3. 请求参数--员工相关属性-- 参数：id-name-age
 * 4. 请求响应--Employee--json
 * url: http://localhost:80/employees
 * 使用postman进行接口测试
 * */
@RequestMapping(value = "/employees",method = RequestMethod.PUT)
@ResponseBody
public Employee update(Employee e){
    e.setName(e.getName()+"_update");//http://localhost:80/employees?name=Ming5
    return e;
}

/**
 * 删除员工
 * 1. 请求路径--确认资源--员工--/employees
 * 2. 请求方法--删除--DELETE
 * 3. 请求参数--员工相关属性--参数：id
 * 4. 请求响应--删除之后状态--JsonResult--json
 * JsonResult--统一的响应返回值
 * {
 * code: 200
 * msg: "操作成功"
 * data: null
 * }
 * url: http://localhost:80/employees
 * 使用postman进行接口测试
 * */
@RequestMapping(value = "/employees",method = RequestMethod.DELETE)
@ResponseBody
public JsonResult delete(Long id){
    return JsonResult.success();
}

/**
 * 查询指定id的员工信息
 * 1. 请求路径--确认资源--员工--/employees
 * 2. 请求方法--添加--GET
 * 3. 请求参数--员工相关属性-- 参数：id
 * 4. 请求响应--Employee--json
 * url: http://localhost:80/employees
 * 使用postman进行接口测试
 * 项目启动时，直接报错，说mapping映射重复，与getList方法是相同的映射路径"/employees"与请求方法GET
 *
 * 查询所有员工接口：/employees   GET
 * 查询单个员工接口：/employees   GET
 * 上面两个接口映射路径与请求方法完全相同，springmvc认为是同一个接口，不能同时存在，解决方案
 * 方案1：使用多级路径方式 比如：/employees/detail
 * 方案2：使用参数路径方式 比如：/employees/{id}，将请求参数作为路径一部分，进行url区分
 * 参数路径：比如：/employees/{id}，其中{id} 参数占位符
 * 客户端访问：http://localhost:80/1 ->1就是id参数
 * 注意点：接口要获取参数路径中参数必须使用注释@PathVariable，目的是让springmvc参数解析器从路径中解析出参数并进行赋值。
 * 如果参数路径中的占位符名称与请求映射方法形式参数名称不一致时，必须明确的指定映射
 * /employees/{eid}-->@PathVariable("eid") Long id
 *
 * URL组成部分：
 * http://ip:port/path?parameter1=value1&parameter2=value2
 * http://localhost:80/employees?id=1&name=mingyue ->"/employees"
 * http://localhost:80/employees/1?name=mingyue ->"/employees/{id}"
 * http://localhost:80/employees/1/mingyue ->"/employees/{id}/{name}"
 *
 * 参数路径优缺点：
 * 优点：可以在一定程度上隐藏参数
 * 缺点：如果参数较多，URL路径就很长，不优雅
 *
 *
 *
 * */
//@RequestMapping(value = "/employees",method = RequestMethod.GET)
//@ResponseBody
//public Employee detail(Long id){
//    return  new Employee(id,"Mingyue",18);
//}
//@RequestMapping(value = "/employees/detail",method = RequestMethod.GET)
//@ResponseBody
//public Employee detail(Long id){
//    return  new Employee(id,"Mingyue",18);
//}

@RequestMapping(value = "/employees/{id}",method = RequestMethod.GET)
@ResponseBody
public Employee detail(@PathVariable Long id){
    return  new Employee(id,"Mingyue",18);
}

@RequestMapping(value = "employees/{id}/{name}/{age}",method = RequestMethod.GET)
@ResponseBody
//public Employee info(@PathVariable Long id, @PathVariable String name, @PathVariable int age){
//    return new Employee(id,name,age);
//}
public Employee info(Employee e){//也可以将id/name/age封装成对象
return e;
}





}
