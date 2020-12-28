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

/**
 * @program: JavaWeb-code
 * @description
 * @author: Jiadong Duan
 * @create: 2020-11-11 11:15
 **/
@WebServlet("/booklistServlet")
public class BookListServlet extends HttpServlet {
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
        String opr=object.getString("opr");

        int pageSize=object.getInt("pageSize");
        //热门查询



        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.PopularBookList();
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
        out.write(jsonObject.toString());
        out.flush();
        out.close();



        //JSONObject jsonRet = JSONObject.

        //4.调用UserDao的login方法


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
