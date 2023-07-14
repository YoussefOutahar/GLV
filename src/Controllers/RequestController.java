package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import DataBase.DataBase;
import Models.Car;
import Models.Request;
import Models.Accounts.User;

public class RequestController {
    public static Request getRequest(int id) {
        Request tmp = null;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Requests";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                if (resultSet.getInt("id") == id) {
                    tmp = new Request(resultSet.getInt("id"), resultSet.getInt("userID"), resultSet.getInt("carID"),
                            resultSet.getString("startingDate"), resultSet.getString("endingDate"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static boolean addRequest(int idUser, int idCar, String startingDate, String endingDate) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO Requests (ID,userID, carID, startingDate, endingDate,isAccepted) VALUES (?, ?, ?, ?, ?,false)";

        int id = Utils.IdManager.generateNewID(DataBase.getConnection());

        try {
            preparedStatement = DataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
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

    public static boolean modifyRequest(int id,int idUser, int idCar, String startingDate, String endingDate,boolean isAccepted) {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Requests SET userID = ?, carID = ?, startingDate = ?, endingDate = ?,isAccepted = ? WHERE id = ?";

        try {
            preparedStatement = DataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idCar);
            preparedStatement.setString(3, startingDate);
            preparedStatement.setString(4, endingDate);
            preparedStatement.setBoolean(5, isAccepted);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteRequest(int id) {
        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM Requests WHERE id = ?";

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

    public static User getRequestUser(int ID) {
        Request tmp = getRequest(ID);
        return UsersController.getUser(tmp.getIdUser());
    }

    public static Car getRequestCar(int ID) {
        Request tmp = getRequest(ID);
        return CarController.getCar(tmp.getIdCar());
    }

    public static LinkedList<Request> getAllRequests() {
        LinkedList<Request> tmp = new LinkedList<Request>();
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Requests";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                tmp.add(new Request(resultSet.getInt("id"), resultSet.getInt("userID"), resultSet.getInt("carID"),
                        resultSet.getString("startingDate"), resultSet.getString("endingDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static LinkedList<Request> getAllRequestsByUser(int idUser) {
        LinkedList<Request> tmp = new LinkedList<Request>();
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Requests WHERE userID = " + idUser;
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (resultSet.next()) {
                tmp.add(new Request(resultSet.getInt("id"), resultSet.getInt("userID"), resultSet.getInt("carID"),
                        resultSet.getString("startingDate"), resultSet.getString("endingDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static LinkedList<Request> getAllRequestsByCarOrUserName(String Name) {
        LinkedList<Request> tmp = getAllRequests();
        LinkedList<Request> requests = new LinkedList<Request>();
        for (Request request : tmp) {
            if(CarController.getCar(request.getIdCar()).getName().toLowerCase().contains(Name.toLowerCase()) 
            || UsersController.getUser(request.getIdUser()).getUsername().toLowerCase().contains(Name.toLowerCase())){
                requests.add(request);
            }
        }
        return requests;
    }

    public static LinkedList<Request> getAllRequestsByCarOrUserName(int id,String Name) {
        LinkedList<Request> tmp = getAllRequests();
        LinkedList<Request> requests = new LinkedList<Request>();
        for (Request request : tmp) {
            if(request.getIdUser() == id){
                String carName = CarController.getCar(request.getIdCar()).getName();
                String userName = UsersController.getUser(request.getIdUser()).getUsername();
                if(carName.toLowerCase().contains(Name.toLowerCase()) 
                    || userName.toLowerCase().contains(Name.toLowerCase())){
                    requests.add(request);
                }
            }
        }
        return requests;
    }
    
    public static int getRequestCount(){
        int count = 0;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT COUNT(*) FROM Requests";

        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
            count = resultSet.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static boolean deleteAllRequestsByUser(int userID) {
        LinkedList<Request> tmp = getAllRequestsByUser(userID);
        for (Request request : tmp) {
            deleteRequest(request.getId());
        }
        return true;
    }

    public static boolean deleteAllRequestsByCar(int carID) {
        LinkedList<Request> tmp = getAllRequests();
        for (Request request : tmp) {
            if(request.getIdCar() == carID){
                deleteRequest(request.getId());
            }
        }
        return true;
    }
}
