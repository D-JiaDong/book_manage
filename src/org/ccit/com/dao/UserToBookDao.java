package org.ccit.com.dao;

import org.ccit.com.domain.packaging.User_to_Book;
import org.ccit.com.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @program: JavaWeb-code
 * @description :
 * @author: Jiadong Duan
 * @create: 2020-11-11 14:53
 **/
public class UserToBookDao {
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
    public List<User_to_Book> select_borrowBookList(){
        List<User_to_Book> borrowbookquery=null;
        String sql = "select * from user_to_book";
        borrowbookquery = template.query(sql, new BeanPropertyRowMapper<User_to_Book>(User_to_Book.class));
        return borrowbookquery;
    }
    public int return_book( int book_id, int book_num,int user_id){
        String sqlreduce = "update t_book set Book_num =? where Book_id=?";
        String sqldel="delete from user_to_book where User_id=? and Book_id=?";
        int result0 = template.update(sqlreduce,book_num,book_id);
        int  result1 = template.update(sqldel, user_id, book_id);
        return result1;
    }


    public List<Map<String, Object>> select_borrowBookList_bytype(String type, String bookname, String bookauthor,String bookid,String userid,String username){
        List<Map<String, Object>> borrowbookquery=null;

        String sql = "select a.User_id,a.Book_id,b.User_name,c.Book_name,c.Book_num from user_to_book a left join t_user b on a.User_id=b.User_id left join t_book c on a.Book_id=c.Book_id";
        if("all".equals(type)){
            sql+=" WHERE 1";
        }
        else {
            sql += " WHERE a.Book_id IN (SELECT Book_id FROM book_to_booktype WHERE Type_id ="+type+ ")";
        }
        if (!"".equals(bookid)){
            sql += " and a.Book_id="+bookid;
        }
        if (!"".equals(bookname)){
            sql += " and c.BooK_name="+"\'"+bookname+"\'";
        }
        if (!"".equals(bookauthor)){
            sql = " and c.BooK_author="+"\'"+bookauthor+"\'";
        }
        if (!"".equals(userid)){
            sql += " and a.User_id="+userid;
        }
        if (!"".equals(username)){
            sql += " and b.User_name="+"\'"+username+"\'";
        }
        System.out.println(sql);
        borrowbookquery = template.queryForList(sql);
        return borrowbookquery;
    }
}
