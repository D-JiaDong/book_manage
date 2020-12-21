package org.ccit.com.dao;

import org.ccit.com.domain.packaging.Book;
import org.ccit.com.domain.packaging.User;
import org.ccit.com.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @program: JavaWeb-code
 * @description :
 * @author: Jiadong Duan
 * @create: 2020-11-11 14:53
 **/
public class BookDao {
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
    public List<Book> PopularBookList(){
        List<Book> bookquery=null;
        String sql = "select * from t_book";
        bookquery = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
        return bookquery;
    }



    public List<Book> select_BookList(String type,String bookname,String bookauthor){
        List<Book> bookquery=null;
        String sql = "select * from t_book";
        if("all".equals(type)){
            sql+=" WHERE 1";
        }
        if("xiaoshuo".equals(type)){
            sql+=" WHERE Book_id IN (SELECT Book_id FROM book_to_booktype WHERE Type_id =(SELECT Type_id FROM t_booktype WHERE Type_name=\"小说\"))";
        }
        if("suibi".equals(type)){
            sql+=" WHERE Book_id IN (SELECT Book_id FROM book_to_booktype WHERE Type_id =(SELECT Type_id FROM t_booktype WHERE Type_name=\"随笔\"))";
        }
        if("sanwen".equals(type)){
            sql+=" WHERE Book_id IN (SELECT Book_id FROM book_to_booktype WHERE Type_id =(SELECT Type_id FROM t_booktype WHERE Type_name=\"散文\"))";
        }
        if("biancheng".equals(type)){
            sql+=" WHERE Book_id IN (SELECT Book_id FROM book_to_booktype WHERE Type_id =(SELECT Type_id FROM t_booktype WHERE Type_name=\"编程\"))";
        }
        if("kepu".equals(type)){
            sql+=" WHERE Book_id IN (SELECT Book_id FROM book_to_booktype WHERE Type_id =(SELECT Type_id FROM t_booktype WHERE Type_name=\"科普\"))";
        }

        if ("".equals(bookname)&&"".equals(bookauthor)){
            bookquery = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
            }
        if ("".equals(bookname)&&!"".equals(bookauthor)){
            sql += " and  Book_author=?";
            bookquery = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class),bookauthor);
        }
        if (!"".equals(bookname)&&"".equals(bookauthor)){
            sql += " and BooK_name=?";
            bookquery = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class),bookname);
        }
        if (!"".equals(bookname)&&!"".equals(bookauthor)){
            sql = " and BooK_name=? and Book_author=?";
            bookquery = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class),bookname,bookauthor);

        }
        return bookquery;
    }


}
