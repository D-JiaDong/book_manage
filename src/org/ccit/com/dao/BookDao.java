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
    public List<Book> PopularBookList(int currenttPage,int pageSize,String opr){
        if("PopularSearch".equals(opr)){
            String sql = "select * from t_book limit ?,?";
            System.out.println((currenttPage-1)*pageSize);
            System.out.println(pageSize);

            List<Book> bookquery = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class),(currenttPage-1)*pageSize,pageSize);
            System.out.println(bookquery);
            return bookquery;
        }
      return null;
    }

    /**
     * @Description: 返回book表数据总数
     * @param
     * @return: java.lang.Long
     * @Author: Jiadong Duan
     * @Date: 2020/11/12 19:11
     */
    public Long getCount(){
        String sql = "select count(*) from t_book";
        Long count = template.queryForObject(sql, Long.class);
        return count;
    }
}
