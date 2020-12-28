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
@WebServlet("/Select_AlltypeForAlterServlet")
public  class Select_AlltypeForAlterServlet extends HttpServlet {
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
        int booktype_id=0;
        String opr="select";
        int pageSize=object.getInt("pageSize");
        try{
            booktype_id=object.getInt("type_id");
            opr=object.getString("opr");
        }catch (Exception e){
        }


        BookTypeDao bookTypeDao = new BookTypeDao();
        int Uid=0;
        String Uname="";
        try{
            Uid=object.getInt("Uid");
            Uname=object.getString("Uname");
        }catch (Exception e){
        }
        if ("submitUpdate".equals(opr)){
            bookTypeDao.updatename(Uid,Uname);
        }
        String Aname="";

        try{
            Aname=object.getString("Aname");
        }catch (Exception e){
        }
        if ("submitAdd".equals(opr)){
            bookTypeDao.addBooktype(Aname);
        }


        String typename=object.getString("typename");



        List<Booktype> booktypes= bookTypeDao.BooktypeList();
        List<Booktype> selectbooktypes=bookTypeDao.select_BooktypeList(typename);
        JSONArray booktype=new JSONArray(booktypes);
        int count =selectbooktypes.size();

        int totalPage=count/pageSize;
        if(count%pageSize!=0){
            totalPage++;
        }



        JSONArray jsonArray=new JSONArray(selectbooktypes);
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject jsonObject= new JSONObject("{\'count\':"+count+", \'totalPage\':"+totalPage+", \'data\':"+jsonArray.toString()+", \'booktypes\':"+booktype.toString()+"}");
        List<Booktype> booktypebyid=null;
        if ("update".equals(opr)){
            booktypebyid=  bookTypeDao.select_BooktypeListbyid(booktype_id);
            JSONArray jsonArray_booktypebyid=new JSONArray(booktypebyid);
            jsonObject= new JSONObject("{\'booktypebyid\':"+jsonArray_booktypebyid+",\'count\':"+count+", \'totalPage\':"+totalPage+", \'data\':"+jsonArray.toString()+", \'booktypes\':"+booktype.toString()+"}");
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
