package vn.edu.poly.bookmanager.model;

public class User {

    int imganh;
    String username;
    String passwork;
    String name;
    String phone;

    public User(String username, String passwork, String name, String phone) {
        this.username = username;
        this.passwork = passwork;
        this.name = name;
        this.phone = phone;
    }

    public User() {
        // Empty User Constructor
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImganh() {
        return imganh;
    }

    public void setImganh(int imganh) {
        this.imganh = imganh;
    }
}
