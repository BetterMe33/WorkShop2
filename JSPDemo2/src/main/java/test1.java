import db.Database;
import model.Usedata;

import java.sql.SQLException;
import java.util.ArrayList;

public class test1 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Database database=new Database();
        database.showConnect();
//        database.insert("小明","123456");
        database.update("1234",1);
//        database.close();
//        ArrayList<Usedata>usedata=database.getAlluser();
//        System.out.println("总表里面共有:"+usedata.size());
//        for (Usedata each:usedata){
//            System.out.println(each.getId()+each.getName()+each.getPassword());
//        }
        Usedata usedata=database.getUser(1);
        System.out.println(usedata.getId()+usedata.getName());
        usedata = database.login(1, "1234");
            System.out.println(usedata);
            if (usedata==null){
                System.out.println("该用户不存在或密码错误");
            }
            else{
                System.out.println("欢迎登录"+usedata.getName());
            }

    }

}
