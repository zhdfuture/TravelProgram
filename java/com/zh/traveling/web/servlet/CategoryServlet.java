package com.zh.traveling.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.traveling.entity.Categroy;
import com.zh.traveling.service.CategroyService;
import com.zh.traveling.service.CategroyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategroyService service = new CategroyServiceImpl();

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询所有
        List<Categroy> cs = service.findAll();
        //处理服务器的响应格式：json格式
        //2.将list集合写回到前台（序列化json返回）
        writeValue(cs,response);

    }

}
