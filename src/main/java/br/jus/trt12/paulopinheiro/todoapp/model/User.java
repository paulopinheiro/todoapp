package br.jus.trt12.paulopinheiro.todoapp.model;

public class User {
    private Integer userid;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String location;
    private String gender;

    public User() {}

    public User(Integer userid, String firstName, String lastName, String userName, String password, String location, String gender) {
        this.userid = userid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.location = location;
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public String getGender() {
        return gender;
    }

    public Integer getUserid() {
        return userid;
    }
}
