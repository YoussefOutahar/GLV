package Controllers;

import DataBase.DataBase;
import Utils.ImageProcessing;
import Utils.IdManager;

import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Models.Accounts.Admin;
import Models.Accounts.User;

public class UsersController {
    public static User getUser(int ID) {
        User tmp = null;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Users";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == ID && resultSet.getBoolean("isAdmin") == false) {
                    BufferedImage image = ImageProcessing.convertToBufferedImage(resultSet.getBytes("image"));
                    tmp = new User(resultSet.getInt("ID"), resultSet.getString("username"),
                            resultSet.getString("password"), resultSet.getString("date"), image,
                            resultSet.getString("email"), resultSet.getString("phoneNumber"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static User getUser(String name){
        User tmp = null;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Users";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(name) && resultSet.getBoolean("isAdmin") == false) {
                    BufferedImage image = ImageProcessing.convertToBufferedImage(resultSet.getBytes("image"));
                    tmp = new User(resultSet.getInt("ID"), resultSet.getString("username"),
                            resultSet.getString("password"), resultSet.getString("date"), image,
                            resultSet.getString("email"), resultSet.getString("phoneNumber"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }
    
    public static Admin getAdmin(int ID) {
        Admin tmp = null;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Users";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == ID && resultSet.getInt("isAdmin") == 1) {
                    BufferedImage image = ImageProcessing.convertToBufferedImage(resultSet.getBytes("image"));
                    tmp = new Admin(resultSet.getInt("ID"), resultSet.getString("username"),
                            resultSet.getString("password"), resultSet.getString("date"), image,
                            resultSet.getString("email"), resultSet.getString("phoneNumber"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static boolean addUser(String name, String password, boolean isAdmin, BufferedImage image,String email,String phoneNumber) {
        PreparedStatement statement = null;
        byte[] imgData = ImageProcessing.convertImgtoBytes(image);
        int ID = IdManager.generateNewID(DataBase.getConnection());
        String sqlAddProductQuery = "INSERT INTO Users VALUES(?,?,?,?,?,datetime('now'),?,?);";
        
        try {
            statement = DataBase.getConnection().prepareStatement(sqlAddProductQuery);
            statement.setInt(1, ID);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setBoolean(4, isAdmin);
            statement.setBytes(5, imgData);
            statement.setString(6, email);
            statement.setString(7, phoneNumber);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delUser(int ID) {
        AchatController.deleteAllAchatsByUser(ID);
        RequestController.deleteAllRequestsByUser(ID);

        Statement statement = null;
        String sqlDelUserQuery = "DELETE from USERS where ID=" + ID + ";";

        try {
            statement = DataBase.getConnection().createStatement();
            statement.executeUpdate(sqlDelUserQuery);
            statement.close();
            return true;
        } catch (SQLException e) {
            System.err.println("Statement Creation Failed");
            e.printStackTrace();
        }
        return false;
    }

    public static boolean modifyUser(int ID,String username, String password, BufferedImage image, String email,
    String phoneNumber){
        PreparedStatement statement = null;
        String sqlQuery = "UPDATE Users SET username = ?,password = ?,isAdmin = ?, image = ?,date = datetime('now'), email = ?, phoneNumber = ? WHERE ID = ?;";

        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setBoolean(3, false);
            statement.setBytes(4, ImageProcessing.convertImgtoBytes(image));
            statement.setString(5, email);
            statement.setString(6, phoneNumber);
            statement.setInt(7, ID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean modifyAdmin(int ID,String username, String password, BufferedImage image, String email,
    String phoneNumber){
        PreparedStatement statement = null;
        String sqlQuery = "UPDATE Users SET username = ?,password = ?,isAdmin = ?, image = ?,date = datetime('now'), email = ?, phoneNumber = ? WHERE ID = ?;";

        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setBoolean(3, true);
            statement.setBytes(4, ImageProcessing.convertImgtoBytes(image));
            statement.setString(5, email);
            statement.setString(6, phoneNumber);
            statement.setInt(7, ID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static LinkedList<User> getAllUsers() {
        LinkedList<User> users = new LinkedList<User>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT ID,isAdmin FROM Users;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            while (rs.next()) {
                if (rs.getInt("isAdmin") == 0) {
                    users.add(getUser(rs.getInt("ID")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static LinkedList<Admin> getAllAdmins() {
        LinkedList<Admin> users = new LinkedList<Admin>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT ID,isAdmin FROM Users;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            while (rs.next()) {
                if (rs.getInt("isAdmin") == 1) {
                    users.add(getAdmin(rs.getInt("ID")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static int getUserCount() {
        int nmbr = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT COUNT(*) FROM Users WHERE isAdmin = 0;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            nmbr = rs.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nmbr;
    }

    public static int getAdminCount() {
        int nmbr = 0;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sqlQuery = "SELECT COUNT(*) FROM Users WHERE isAdmin = 1;";
        try {
            statement = DataBase.getConnection().prepareStatement(sqlQuery);
            rs = statement.executeQuery();
            nmbr = rs.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nmbr;
    }
}
