package com.zh.traveling.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//不需要被访问到，不需要注解
public class BaseServlet extends HttpServlet {
    //重写HttpServlet中的service方法（servlet的入口）
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成方法分发
        //1.获取访问子类的Servlet的uri路径
        String uri = req.getRequestURI();
        System.out.println("请求uri:"+uri);
        //2.获取方法名
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);   //从后向前截取，含头不含尾
        System.out.println("方法名："+methodName);
          //3.获取方法对象Method
            //（1）.获取子类对象
            Class clazz=this.getClass();
            System.out.println(clazz);
            //（2）.调用子类中的方法
            //暴力反射（获取子类私有的/受保护的方法）
        try {
            //忽略访问权限修饰符，获取方法，使用getDeclaredMethod
         // Method method=  clazz.getDeclaredMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
      //暴力法不好，将方法改成public的，直接调用
       Method method=clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
          //调用子类中的方法
            //强制解决反射的验证（因为当前调用的子类的方法是protected修饰的）
           // method.setAccessible(true);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    //直接将传入的对象(obj)序列化为json，并且写回客户端(流的方式，还可以用字符串的方式）

    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }

     // 将传入的对象序列化为json，返回给调用者
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}


