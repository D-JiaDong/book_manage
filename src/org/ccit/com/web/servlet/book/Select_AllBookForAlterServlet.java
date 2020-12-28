package org.ccit.com.web.servlet.book;



import org.ccit.com.dao.BookDao;
import org.ccit.com.dao.BookTypeDao;
import org.ccit.com.domain.packaging.Book;
import org.ccit.com.domain.packaging.Booktype;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

/****
 * @program: JavaWeb-code
 * @description
 * @author: Jiadong Duan
 * @create: 2020-11-11 11:15
 **/
@WebServlet("/Select_AllBookForAlterServlet")
public  class Select_AllBookForAlterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Description: 从前端传回的数据 返回对应的book列表 和实现分页
         * @param request
         * @param response
         * @return: void
         * @Author: Jiadong Duan
         * @Date: 2020/11/12 18:33
         */
        request.setCharacterEncoding("utf-8");
        InputStreamReader insr = new InputStreamReader(request.getInputStream(),"utf-8");
        String result = "";
        int respInt = insr.read();
        while(respInt!=-1) {
            result +=(char)respInt;
            respInt = insr.read();
        }
        JSONObject object = new JSONObject(result);
        BookDao bookDao = new BookDao();
        int book_id=0;
        int book_price=0;
        int book_num=0;
        String opr="select";
        int pageSize=object.getInt("pageSize");
        try{
            book_id=object.getInt("book_id");
            opr=object.getString("opr");
        }catch (Exception e){
        }

        int Uid=0;
        float Upri=0;
        int Unum=0;
        try{
            Uid=object.getInt("Uid");
            Unum=object.getInt("Unum");
            Upri=object.getFloat("Upri");
        }catch (Exception e){
        }
        if ("submitUpdate".equals(opr)){
            bookDao.updatatelAndnum(Uid,Unum,Upri);
        }
        String Aname="";
        String Aauthor="";
        float Apri=0;
        int Anum=0;
        int Atypeid=0;

        try{
            Aname=object.getString("Aname");
            Aauthor=object.getString("Aauthor");
            Anum=object.getInt("Anum");
            Apri=object.getFloat("Apri");
            Atypeid=object.getInt("Atypeid");
        }catch (Exception e){
        }
        if ("submitAdd".equals(opr)){
            bookDao.addBook(Aname,Aauthor,Anum,Apri,Atypeid);
        }


        String bookauthor=object.getString("bookauthor");
        String bookname=object.getString("bookname");



        List<Book> books = bookDao.select_BookList("all",bookname,bookauthor);


        BookTypeDao bookTypeDao = new BookTypeDao();
        List<Booktype> booktypes= bookTypeDao.BooktypeList();
        JSONArray jsonArray_type=new JSONArray(booktypes);


        int count =books.size();

        int totalPage=count/pageSize;
        if(count%pageSize!=0){
            totalPage++;
        }



        JSONArray jsonArray=new JSONArray(books);
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject= new JSONObject("{\'count\':"+count+", \'totalPage\':"+totalPage+", \'data\':"+jsonArray.toString()+", \'booktypes\':"+jsonArray_type.toString()+"}");
        List<Book> bookbyid=null;
        if ("update".equals(opr)){
            bookbyid= bookDao.select_BookListbyid(book_id);
            JSONArray jsonArray_bookbyid=new JSONArray(bookbyid);
            jsonObject= new JSONObject("{\'bookbyid\':"+jsonArray_bookbyid+",\'count\':"+count+", \'totalPage\':"+totalPage+", \'data\':"+jsonArray.toString()+", \'booktypes\':"+jsonArray_type.toString()+"}");
        }
        out.write(jsonObject.toString());
        System.out.println(jsonObject.toString());
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
