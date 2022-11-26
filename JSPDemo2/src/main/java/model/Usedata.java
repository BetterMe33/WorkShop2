package model;

public class Usedata {
    private Integer id;
    private String Name;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public Usedata(Integer id,String name,String password) {
        this.id=id;
        Name = name;
        this.password=password;
    }

    public Usedata(String name, String password) {
        Name = name;
        this.password=password;
    }

    public String getName() {
        return Name;
    }

    public Integer getId() {
        return id;
    }
}
