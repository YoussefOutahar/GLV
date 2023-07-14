package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Models.Car;
import Models.Comment;
import Models.Accounts.User;

public class CommentsController {
    public static Comment getComments(int id){
        Comment tmp = null;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Comments";
        try {
            commandeStatement = DataBase.DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == id) {
                    tmp = new Comment(resultSet.getInt("ID"),resultSet.getInt("userID"),resultSet.getInt("carID"),resultSet.getString("comment"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static boolean addComment(int userID,int carID,String comment) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO Comments (ID,userID, carID, comment) VALUES (?, ?, ?, ?)";

        int id = Utils.IdManager.generateNewID(DataBase.DataBase.getConnection());
        try {
            preparedStatement = DataBase.DataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, userID);
            preparedStatement.setInt(3, carID);
            preparedStatement.setString(4, comment);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean modifyComment(int ID,int userID,int carID,String comment) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Comments SET userID = ?, carID = ?, comment = ? WHERE ID = ?";

        try {
            preparedStatement = DataBase.DataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, carID);
            preparedStatement.setString(3, comment);
            preparedStatement.setInt(4, ID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteComment(int ID) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM Comments WHERE ID = ?";

        try {
            preparedStatement = DataBase.DataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User getCommentUser(int ID){
        Comment tmp = getComments(ID);
        return UsersController.getUser(tmp.getIdUser());
    }

    public static Car getCommentCar(int ID){
        Comment tmp = getComments(ID);
        return CarController.getCar(tmp.getIdCar());
    }
    
    public static LinkedList<Comment> getAllComments(){
        LinkedList<Comment> tmp = new LinkedList<Comment>();
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT ID FROM Comments";
        try {
            commandeStatement = DataBase.DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
            while (resultSet.next()) {
                tmp.add(getComments(resultSet.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static int getCommentsCount(){
        int count = 0;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT Count(*) FROM Comments";

        try{
            commandeStatement = DataBase.DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
            count = resultSet.getInt("Count(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
