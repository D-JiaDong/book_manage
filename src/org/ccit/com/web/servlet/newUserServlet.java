package org.ccit.com.web.servlet;

import org.ccit.com.dao.UserDao;
import org.ccit.com.domain.packaging.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

/**
 * @program: book_manage
 * @description
 * @author: Jiadong Duan
 * @create: 2020-12-18 15:05
 **/
@WebServlet("/newUserServlet")
public class newUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name=new String(request.getParameter("user_name").getBytes("ISO-8859-1"),"UTF-8");
        String user_age=new String(request.getParameter("user_age").getBytes("ISO-8859-1"),"UTF-8");
        String user_sex=new String(request.getParameter("user_sex").getBytes("ISO-8859-1"),"UTF-8");
        String user_pwd=new String(request.getParameter("user_pwd").getBytes("ISO-8859-1"),"UTF-8");
        String user_tel=new String(request.getParameter("user_tel").getBytes("ISO-8859-1"),"UTF-8");
        String user_intro=new String(request.getParameter("user_intro").getBytes("ISO-8859-1"),"UTF-8");

        User adduser = new User();
        adduser.setUser_name(user_name);
        adduser.setUser_sex(user_sex);
        adduser.setUser_age(Integer.parseInt(user_age));
        adduser.setUser_tel(user_tel);
        adduser.setUser_pwd(user_pwd);
        adduser.setUser_intro(user_intro);



        String picsrc=new String(request.getParameter("picsrc").getBytes("ISO-8859-1"),"UTF-8");
        String picname=new String(request.getParameter("picname").getBytes("ISO-8859-1"),"UTF-8");
        picsrc = picsrc.replace("data:image/png;base64,", "");
        picsrc = picsrc.replace("data:image/jpeg;base64,", "");
        UUID uuid = UUID.randomUUID();
        String user_pic="/static/upload/"+uuid+picname;
        adduser.setUser_pic(user_pic);

        UserDao userDao = new UserDao();
        int result=userDao.useradd(adduser);
        //添加成功 导入图片
        if(result>0){
            File file = null;
            String filePath="D:\\JetBrains_2020\\IdeaProjects\\JavaWeb-code\\book_manage\\web\\static\\upload";
            File  dir=new File(filePath);
            if (!dir.exists() && !dir.isDirectory()) {
                dir.mkdirs();
            }
            BufferedOutputStream bos = null;
            java.io.FileOutputStream fos = null;
            try {
                byte[] bytes = Base64.getDecoder().decode(picsrc);
                file=new File(filePath+"\\"+uuid+picname);
                fos = new java.io.FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                bos.write(bytes);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        request.getRequestDispatcher("/user/login.html").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
