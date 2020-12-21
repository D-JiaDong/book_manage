package org.ccit.com.dao;

import org.ccit.com.domain.packaging.Admin;
import org.ccit.com.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;

/**
 * 操作数据库中Admin表的类
 */
public class AdminDao {

    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     * @param loginAdmin 只有用户名和密码
     * @return Admin包含用户全部数据,没有查询到，返回null
     */
    public Admin Adminlogin(Admin loginAdmin){
        try {
            //1.编写sql
            System.out.println(loginAdmin.getAdm_name());
            System.out.println(loginAdmin.getAdm_pwd());
            String sql = "select * from t_admin where Adm_name = ? and Adm_pwd = ?";
            //2.调用query方法
            Admin Admin = template.queryForObject(sql,
                    new BeanPropertyRowMapper<Admin>(Admin.class),
                    loginAdmin.getAdm_name(), loginAdmin.getAdm_pwd());

            return Admin;
        } catch (DataAccessException e) {
            e.printStackTrace();//记录日志
            return null;
        }
    }
}
