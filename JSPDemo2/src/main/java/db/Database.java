package db;

import com.mysql.cj.jdbc.Driver;
import model.Usedata;

import javax.jws.soap.SOAPBinding;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    Connection conn=null;
    public Database() throws ClassNotFoundException, SQLException {

          Class.forName("com.mysql.cj.jdbc.Driver");
          this.conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8","root","123456");
//          System.out.println(conn);
    }
    public void showConnect(){
        if (conn==null){
            System.out.println("数据库连接失败");
        }else {
            System.out.println("数据库连接成功");
        }

    }
    public void close() throws SQLException {
        conn.close();
    }
    //在login表里添加数据
    public void insert(String name,String password) throws SQLException {
        PreparedStatement prep=conn.prepareStatement("insert into test.login values (null,?,?)");
        prep.setString(1,name);
        prep.setString(2,password);
        prep.execute();
    }
    //修改数据
    public void update(String password,int id) throws SQLException {
        PreparedStatement prep=conn.prepareStatement("UPDATE test.login SET password=? where id=?");
        prep.setString(1,password);
        prep.setInt(2,id);
        prep.execute();
    }
    public ArrayList<Usedata>getAlluser() throws SQLException {
       ArrayList<Usedata> uselist=new ArrayList<Usedata>();
       PreparedStatement prep =conn.prepareStatement("select * from test.login");
       prep.execute();
       ResultSet resultSet=prep.executeQuery();
       while (resultSet.next()){
           int mymid=resultSet.getInt("id");
           String myname=resultSet.getNString("name");
           String mypassword=resultSet.getNString("password");
           uselist.add(new Usedata(mymid,myname,mypassword));
       }
       return uselist;
    }
    //在表里查询数据
    public Usedata getUser(int id) throws SQLException {
        PreparedStatement prep=conn.prepareStatement("select * from test.login where id=?");
        prep.setInt(1,id);
        prep.execute();
        ResultSet resultSet=prep.executeQuery();
        if (resultSet.next()){
            int mymid=resultSet.getInt("id");
            String myname=resultSet.getNString("name");
            String mypassword=resultSet.getNString("password");
            return new Usedata(mymid,myname,mypassword);
        }
        return null;
    }
    public Usedata login(int id,String password) throws SQLException {
        PreparedStatement prep=conn.prepareStatement("select test.login.password from test.login where id=?");
        prep.setInt(1,id);
        prep.execute();
        ResultSet resultSet=prep.executeQuery();
        if(resultSet.next()){
            String mypassword=resultSet.getString("password");
            if (password.equals(mypassword)){
                return getUser(id);
            }
            else return null;
        }
        else
            return  null;

    }
}
