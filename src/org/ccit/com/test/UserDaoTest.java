package org.ccit.com.test;

import org.ccit.com.dao.UserDao;
import org.ccit.com.domain.packaging.User;
import org.junit.Test;

public class UserDaoTest {



    @Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setUser_name("小红");
        loginuser.setUser_pwd("1234");


        UserDao dao = new UserDao();
        User user = dao.userlogin(loginuser);

        System.out.println(user);
    }
}
