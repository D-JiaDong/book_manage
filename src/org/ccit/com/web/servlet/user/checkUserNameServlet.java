package org.ccit.com.web.servlet.user;
import org.ccit.com.dao.UserDao;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @program: book_manage
 * @description
 * @author: Jiadong Duan
 * @create: 2020-12-19 12:17
 **/
@WebServlet("/checkUserNameServlet")
public class checkUserNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        InputStreamReader insr = new InputStreamReader(request.getInputStream(),"utf-8");
        String result = "";
        int respInt = insr.read();
        while(respInt!=-1) {
            result +=(char)respInt;
            respInt = insr.read();
        }
//        System.out.println(result);
        JSONObject object = new JSONObject(result);
        String user_name=object.getString("user_name");

        UserDao userDao = new UserDao();
        int isexist=userDao.check_Username(user_name);


        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject= new JSONObject("{\'isexist\':"+isexist+"}");
        out.write(jsonObject.toString());
        out.flush();
        out.close();



        //JSONObject jsonRet = JSONObject.

        //4.调用UserDao的login方法

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
