package org.ccit.com.dao;

import org.ccit.com.domain.packaging.*;
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
    public int borrow_book( int book_id, int book_num,int user_id){
        String sqlselect="select * from user_to_book where User_id=? and Book_id=?";
        List<User_to_Book> isexit=null;
        isexit = template.query(sqlselect, new BeanPropertyRowMapper<User_to_Book>(User_to_Book.class),user_id,book_id);
        if (isexit.size()>0){
            return -2;
        }
        String sqlreduce = "update t_book set Book_num =? where Book_id=?";
        String sqladd="insert into user_to_book (User_id, Book_id) values(?,?)";
        int result0 = template.update(sqlreduce,book_num,book_id);
        int  result1 = template.update(sqladd, user_id, book_id);
        return result1;
    }



    public List<Book> select_BookList(String type,String bookname,String bookauthor){
        List<Book> bookquery=null;
        String sql = "select * from t_book";
        if("all".equals(type)){
            sql+=" WHERE 1";
        }
        else {
            sql += " WHERE Book_id IN (SELECT Book_id FROM book_to_booktype WHERE Type_id ="+type+")";
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
            sql += " and BooK_name=? and Book_author=?";
            bookquery = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class),bookname,bookauthor);

        }
        return bookquery;
    }
    public List<Book> select_ISborrowBookList(String bookname,String bookauthor,int userid){
        List<Book> bookquery=null;
        String sql = "select c.Book_id,c.Book_name,c.Book_author from user_to_book a left join t_user b on a.User_id=b.User_id left join t_book c on a.Book_id=c.Book_id";
        sql+=" WHERE b.User_id="+userid;
        if (!"".equals(bookname)){
            sql+=" and c.Book_name="+"\'"+bookname+"\'";
        }
        if (!"".equals(bookauthor)){
            sql+=" and c.Book_author="+"\'"+bookauthor+"\'";
        }
        bookquery = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
        return bookquery;
    }

    public List<Book> select_BookListbyid(int bookid){
        List<Book> bookquery=null;
        String sql = "select * from t_book where Book_id=?";
        bookquery = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class),bookid);
        return bookquery;
    }

    public int updatatelAndnum(int book_id, int book_num, float book_price){
        String sql = "update t_book set Book_num =? , Book_price=? where Book_id=?";
        int result = template.update(sql,book_num,book_price,book_id);
        return result;
    }
    public int addBook(String book_name,String book_author,int book_num,float book_price,int type_id){
        String sqladdbook = "insert into t_book (Book_name, Book_author,Book_num,Book_price) values(?,?,?,?);";
        int result = template.update(sqladdbook,book_name,book_author,book_num,book_price);

        String selectbook="select Book_id from t_book where Book_name=?";
        int book_id = template.queryForObject(selectbook, Integer.class, book_name);
        System.out.println(book_id);
        String sqladdbook_to_booktype ="insert into book_to_booktype (Book_id,Type_id) values(?,?)";
        result = template.update(sqladdbook_to_booktype,book_id,type_id);
        return result;
    }

}
