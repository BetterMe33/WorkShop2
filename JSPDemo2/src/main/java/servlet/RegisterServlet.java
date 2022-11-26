package servlet;

import db.Database;
import model.Usedata;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RegisterServlet", urlPatterns = "/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;character=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String name=request.getParameter("name");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        PrintWriter out=response.getWriter();
        if (password.equals(password2)){
            try {
                Database database =new Database();
                database.insert(name,password);
                out.println("注册成功");
                ArrayList<Usedata>usedata=database.getAlluser();
        out.println("总表里面共有:"+usedata.size());
        for (Usedata each:usedata){
           out.println(each.getId()+each.getName()+each.getPassword());
        }
        out.println("请返回登录界面"+"<a href=\"http://localhost:8080/JSPDemo2_Web_exploded/\">返回登录");
            } catch (ClassNotFoundException e) {
               e.printStackTrace();
               out.println("注册失败");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                out.println("注册失败2");

            }
        }else {
            out.println("两次输入密码不一致");
            out.println("请返回重新注册"+"<a href=\"http://localhost:8080/JSPDemo2_Web_exploded/Register.jsp\">返回注册");
        }
//        out.println("输入的账号为:"+id+"输入的密码为:"+password);
    }
}
