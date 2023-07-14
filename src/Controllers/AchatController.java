package Controllers;

import DataBase.DataBase;
import Models.Achat;
import Models.Car;
import Models.Accounts.User;
import Utils.IdManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class AchatController {
    public static Achat getAchat(int ID) {
        Achat tmp = null;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Achats";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == ID) {
                    tmp = new Achat(resultSet.getInt("ID"), resultSet.getInt("userID"), resultSet.getInt("carID"),
                            resultSet.getString("startingDate"), resultSet.getString("endingDate"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static boolean addAchat(int idUser, int idCar, String startingDate, String endingDate) {
        PreparedStatement preparedStatement = null;
        int ID = IdManager.generateNewID(DataBase.getConnection());
        String query = "INSERT INTO Achats (id, userID, carID, startingDate, endingDate) VALUES (?, ?, ?, ?, ?)";

        try {
            preparedStatement = DataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, ID);
            preparedStatement.setInt(2, idUser);
            preparedStatement.setInt(3, idCar);
            preparedStatement.setString(4, startingDate);
            preparedStatement.setString(5, endingDate);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean modifyAchat(int id,int idUser, int idCar, String startingDate, String endingDate) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Achats SET userID = ?, carID = ?, startingDate = ?, endingDate = ? WHERE id = ?";

        try {
            preparedStatement = DataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idCar);
            preparedStatement.setString(3, startingDate);
            preparedStatement.setString(4, endingDate);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delAchat(int id) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM Achats WHERE id = ?";

        try {
            preparedStatement = DataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User getAchatUser(int ID){
        Achat tmp = getAchat(ID);
        return UsersController.getUser(tmp.getIdUser());
    }

    public static Car getAchatCar(int ID){
        Achat tmp = getAchat(ID);
        return CarController.getCar(tmp.getIdCar());
    }
    
    public static LinkedList<Achat> getAllAchats() {
        LinkedList<Achat> achats = new LinkedList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Achats";
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                achats.add(new Achat(resultSet.getInt("ID"), resultSet.getInt("userID"), resultSet.getInt("carID"),
                        resultSet.getString("startingDate"), resultSet.getString("endingDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return achats;
    }

    public static LinkedList<Achat> getAllAchatsByUser(int idUser) {
        LinkedList<Achat> achats = new LinkedList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM Achats WHERE userID = " + idUser;
        try {
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                achats.add(new Achat(resultSet.getInt("ID"), resultSet.getInt("userID"), resultSet.getInt("carID"),
                        resultSet.getString("startingDate"), resultSet.getString("endingDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return achats;
    }

    public static LinkedList<Achat> getAllAchatsByCarOrUserName(String Name) {
        LinkedList<Achat> tmp = getAllAchats();
        LinkedList<Achat> requests = new LinkedList<Achat>();
        for (Achat achat : tmp) {
            if(CarController.getCar(achat.getIdCar()).getName().toLowerCase().contains(Name.toLowerCase()) 
            || UsersController.getUser(achat.getIdUser()).getUsername().toLowerCase().contains(Name.toLowerCase())){
                requests.add(achat);
            }
        }
        return requests;
    }

    public static LinkedList<Achat> getAllAchatsByCarOrUserName(int id,String Name) {
        LinkedList<Achat> tmp = getAllAchats();
        LinkedList<Achat> achats = new LinkedList<Achat>();
        for (Achat achat : tmp) {
            if(achat.getIdUser() == id){
                if(CarController.getCar(achat.getIdCar()).getName().toLowerCase().contains(Name.toLowerCase()) 
            || UsersController.getUser(achat.getIdUser()).getUsername().toLowerCase().contains(Name.toLowerCase())){
                achats.add(achat);
            }
            }
        }
        return achats;
    }

    public static int getAchatCount(){
        int count = 0;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT COUNT(*) FROM Achats";

        try{
            statement = DataBase.getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            count = resultSet.getInt("COUNT(*)");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    public static boolean deleteAllAchatsByUser(int userID) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM Achats WHERE userID = ?";

        try {
            preparedStatement = DataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteAllAchatsByCar(int carID) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM Achats WHERE carID = ?";

        try {
            preparedStatement = DataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, carID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
