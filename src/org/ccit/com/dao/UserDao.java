package org.ccit.com.dao;

import org.ccit.com.domain.packaging.Admin;
import org.ccit.com.domain.packaging.Book;
import org.ccit.com.domain.packaging.User;
import org.ccit.com.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 操作数据库中User表的类
 */
public class UserDao {

    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     * @param loginUser 只有用户名和密码
     * @return user包含用户全部数据,没有查询到，返回null
     */
    public User userlogin(User loginUser){
        try {
            //1.编写sql
            String sql = "select * from t_user where User_name = ? and User_pwd = ?";
            //2.调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUser_name(), loginUser.getUser_pwd());

            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//记录日志
            return null;
        }
    }


    public int useradd(User addUser){
        int result = 0;
        try {
            //1.编写sql
            String sql = "INSERT INTO t_user (`User_name`, `User_sex`, `User_age`, `User_tel`, `User_pwd`, `User_pic`, `User_intro`) VALUES" +
                    "(?,?,?,?,?,?,?)";
            //2.调用query方法
             result = template.update(sql, addUser.getUser_name(),addUser.getUser_sex(),addUser.getUser_age(),addUser.getUser_tel(),addUser.getUser_pwd(),addUser.getUser_pic(),addUser.getUser_intro());
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();//记录日志
            return result;
        }
    }

    public int check_Username(String user_name){
        List<User> result=null;
        try {
            //1.编写sql
            String sql = "select * from t_user where User_name =?";
            //2.调用query方法
            result = template.query(sql, new BeanPropertyRowMapper<User>(User.class),user_name);
            System.out.println(result);
            return result.size();
        } catch (DataAccessException e) {
            e.printStackTrace();//记录日志
            return -1;
        }
    }
    public List<User> select_UserList(String username,String usertel){
        List<User> userquery=null;
        String sql = "select * from t_user";
        sql+=" where 1";
        if (!"".equals(username)){
            sql+=" and User_name="+"\'"+username+"\'";
        }
        if(!"".equals(usertel)){
            sql+=" and User_tel="+"\'"+usertel+"\'";
        }
        System.out.println(sql);
        userquery = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        return userquery;
    }

}
