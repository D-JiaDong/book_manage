package org.ccit.com.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.ccit.com.dao.AdminDao;
import org.ccit.com.domain.packaging.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@WebServlet("/Admain_loginServlet")
public class AdmainLoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("utf-8");
       //2.获取请求参数
//        String username = req.getParameter("user_name");
//        String password = req.getParameter("user_pwd");
//        //3.封装user对象
//        User user = new User();
//        user.setUser_name(username);
//        user.setUser_pwd(password);
        Admin admin=new Admin();
        Map<String, String[]> map = req.getParameterMap();
        try {
            BeanUtils.populate(admin,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //2.获取所有请求参数
//        Map<String, String[]> map = req.getParameterMap();
//        //3.创建User对象
//        System.out.println("map:"+ Arrays.toString(map.get("User_name")));
//        System.out.println("map:"+ Arrays.toString(map.get("User_pwd")));
//        User userlogin=new User();
//        //3.2使用BeanUtils封装
//        try {
//            BeanUtils.populate(userlogin,map);
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
        //4.调用UserDao的login方法
        AdminDao dao = new AdminDao();
        Admin admin_test = dao.Adminlogin(admin);

        //5.判断user
        if(admin_test == null){
            //登录失败
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else{
            //登录成功
            //存储数据
            HttpSession session = req.getSession();
            session.setAttribute("admin",admin_test);
            //转发
            req.getRequestDispatcher("/admin/index.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
