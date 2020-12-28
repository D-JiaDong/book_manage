package org.ccit.com.dao;

import org.ccit.com.domain.packaging.Book;
import org.ccit.com.domain.packaging.Booktype;
import org.ccit.com.domain.packaging.User_to_Book;
import org.ccit.com.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @program: book_manage
 * @description
 * @author: Jiadong Duan
 * @create: 2020-12-25 13:58
 **/
public class BookTypeDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     * @Description: 返回热门书籍数据
     * @param :currenttPage
     * @param :pageSize
     * @param :opr
     * @return: java.util.List<org.ccit.com.domain.packaging.Book>
     * @Author: Jiadong Duan
     * @Date: 2020/11/12 18:43
     */
    public List<Booktype> BooktypeList(){
        List<Booktype> booktypequery=null;
        String sql = "select * from t_booktype";
        booktypequery = template.query(sql, new BeanPropertyRowMapper<Booktype>(Booktype.class));
        return booktypequery;
    }

    public List<Booktype> select_BooktypeList(String typename){
        List<Booktype> bookquery=null;
        String sql = "select * from t_booktype";
        sql+=" WHERE 1";
        if (!"".equals(typename)){
            sql+=" and Type_name="+"\'"+typename+"\'";
        }
        bookquery = template.query(sql, new BeanPropertyRowMapper<Booktype>(Booktype.class));
        return bookquery;
    }
    public List<Booktype> select_BooktypeListbyid(int typeid){
        List<Booktype> booktypequery=null;
        String sql = "select * from t_booktype where Type_id=?";
        booktypequery = template.query(sql, new BeanPropertyRowMapper<Booktype>(Booktype.class),typeid);
        return booktypequery;
    }

    public int updatename(int type_id, String type_name){
        String sql = "update t_booktype set Type_name =? where Type_id=?";
        int result = template.update(sql,type_name,type_id);
        return result;
    }
    public int addBooktype(String type_name){
        String sqladdbooktype ="insert into t_booktype (Type_name) values(?)";
        int result = template.update(sqladdbooktype,type_name);
        return result;
    }
}
