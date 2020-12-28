package org.ccit.com.web.servlet.admin;

import org.apache.commons.beanutils.BeanUtils;
import org.ccit.com.dao.AdminDao;
import org.ccit.com.dao.UserDao;
import org.ccit.com.domain.packaging.Admin;
import org.ccit.com.domain.packaging.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@WebServlet("/admin_loginServlet")
public class AdminLoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("utf-8");
        Admin admin = new Admin();
        Map<String, String[]> map = req.getParameterMap();
        try {
            BeanUtils.populate(admin,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.调用UserDao的login方法
        AdminDao dao=new AdminDao();
        Admin admin_test = dao.Adminlogin(admin);

        //5.判断user
        if(admin_test == null){
            //登录失败
            req.getRequestDispatcher("/admin/login.html").forward(req,resp);
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
