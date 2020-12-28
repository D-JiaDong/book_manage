package org.ccit.com.web.servlet.admin;



import org.ccit.com.dao.BookDao;
import org.ccit.com.dao.BookTypeDao;
import org.ccit.com.dao.UserToBookDao;
import org.ccit.com.domain.packaging.Book;
import org.ccit.com.domain.packaging.Booktype;
import org.ccit.com.domain.packaging.User;
import org.ccit.com.domain.packaging.User_to_Book;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/****
 * @program: JavaWeb-code
 * @description
 * @author: Jiadong Duan
 * @create: 2020-11-11 11:15
 **/
@WebServlet("/Select_AllUser_BookServlet")
public  class Select_AllUser_BookServlet extends HttpServlet {
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
//        System.out.println(result);
        JSONObject object = new JSONObject(result);
        int pageSize=object.getInt("pageSize");
        String type=object.getString("type");
        int book_id=0;
        String opr="select";
        int book_num = 0;
        int user_id=0;
        try{
            book_id=object.getInt("book_id");
            opr=object.getString("opr");
            book_num=object.getInt("book_num");
            user_id=object.getInt("user_id");
        }catch (Exception e){
        }

        String bookid=object.getString("bookid");
        String userid=object.getString("userid");
        String usernmae=object.getString("username");
        String bookauthor=object.getString("bookauthor");
        String bookname=object.getString("bookname");


        //还书
        UserToBookDao userToBookDao = new UserToBookDao();
        int return_result=0;
        if("return".equals(opr)){
            return_result=userToBookDao.return_book(book_id,book_num+1,user_id);
        }

        List<Map<String, Object>> usersbooks = userToBookDao.select_borrowBookList_bytype(type,bookname,bookauthor,bookid,userid,usernmae);

        BookTypeDao bookTypeDao = new BookTypeDao();
        List<Booktype> booktypes= bookTypeDao.BooktypeList();
        JSONArray jsonArray_type=new JSONArray(booktypes);


        int count =usersbooks.size();

        int totalPage=count/pageSize;
        if(count%pageSize!=0){
            totalPage++;
        }



        JSONArray jsonArray=new JSONArray(usersbooks);
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject= new JSONObject("{\'return_result\':"+return_result+",\'count\':"+count+", \'totalPage\':"+totalPage+", \'data\':"+jsonArray.toString()+", \'booktypes\':"+jsonArray_type.toString()+"}");
        out.write(jsonObject.toString());
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
