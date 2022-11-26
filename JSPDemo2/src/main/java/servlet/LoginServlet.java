package servlet;

import db.Database;
import model.Usedata;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;character=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String password = request.getParameter("password");
        PrintWriter out=response.getWriter();
//        out.println("输入的账号为:"+id+"输入的密码为:"+password);
        try {
            Database database=new Database();
            Usedata usedata=database.login(id,password);
            System.out.println(usedata);
            if (usedata==null){
                out.println("该用户不存在或密码错误"+"<a href=\"http://localhost:8080/JSPDemo2_Web_exploded/\">返回登录");
            }
            else{
                out.println("欢迎登录"+usedata.getName());
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            out.println("注册失败");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            out.println("注册失败");
        }
    }
}
