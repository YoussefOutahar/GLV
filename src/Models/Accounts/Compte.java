package Models.Accounts;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Controllers.UsersController;
import DataBase.DataBase;

public class Compte {

    private int id;
    private String username;
    private String password;
    private String date;
    private BufferedImage image;
    private String email;
    private String phoneNumber;
    
    public Compte(int id, String username, String password, String date, BufferedImage image, String email,
            String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.date = date;
        this.image = image;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "Compte [date=" + date + ", email=" + email + ", id=" + id + ", password=" + password + ", phoneNumber="
                + phoneNumber + ", username=" + username + "]";
    }
    
    public static int searchForUsers(String login, String password) {
        int ID = -1;
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT ID,username,password FROM Users";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (login.equals(resultSet.getString("username"))) {
                    if (password.equals(resultSet.getString("password"))) {
                        ID = resultSet.getInt("ID");
                    } else {
                        ID = -2;
                        System.out.println("Incorrect password");
                    }
                    break;
                }
            }
            if (ID == -1) {
                System.out.println("User not Found");
            } else {
                System.out.println("User Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ID;
    }

    public static boolean checkIfAdmin(int ID) {
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT ID,isAdmin FROM Users";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getInt("isAdmin") == 1 && resultSet.getInt("ID") == ID) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean signUp(String login, String password, BufferedImage image, String email, String phoneNumber) {
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT username FROM Users";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(login)) {
                    System.out.println(resultSet.getString("username"));
                    System.out.println("User Already Exists");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        UsersController.addUser(login, password, false, image, email, phoneNumber);
        System.out.println("User created");
        return true;
    }
}
