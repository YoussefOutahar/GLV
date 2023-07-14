package Controllers;

import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import DataBase.DataBase;
import Models.Car;

public class CarController {
    public static Car getCar(int id) {
        Car tmp = null;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Cars";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == id) {
                    BufferedImage image = Utils.ImageProcessing.convertToBufferedImage(resultSet.getBytes("image"));
                    tmp = new Car(resultSet.getInt("ID"), resultSet.getString("name"), resultSet.getString("color"),
                            resultSet.getInt("price"), image);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static Car getCar(String name) {
        Car tmp = null;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT * FROM Cars";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (resultSet.next()) {
                if (resultSet.getString("name").equals(name)) {
                    BufferedImage image = Utils.ImageProcessing.convertToBufferedImage(resultSet.getBytes("image"));
                    tmp = new Car(resultSet.getInt("ID"), resultSet.getString("name"), resultSet.getString("color"),
                            resultSet.getInt("price"), image);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }
    
    public static boolean addCar(String name, String color, int price, BufferedImage image) {
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO Cars (ID,name, color, price, image) VALUES (?, ?, ?, ?, ?)";

        int id = Utils.IdManager.generateNewID(DataBase.getConnection());
        try {
            preparedStatement = DataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, color);
            preparedStatement.setInt(4, price);
            preparedStatement.setBytes(5, Utils.ImageProcessing.convertImgtoBytes(image));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean modifyCar(int ID,String name, String color, int price, BufferedImage image){
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Cars SET name = ?, color = ?, price = ?, image = ? WHERE ID = ?";

        try {
            preparedStatement = DataBase.getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, color);
            preparedStatement.setInt(3, price);
            preparedStatement.setBytes(4, Utils.ImageProcessing.convertImgtoBytes(image));
            preparedStatement.setInt(5, ID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteCar(int id){
        AchatController.deleteAllAchatsByCar(id);
        RequestController.deleteAllRequestsByCar(id);

        PreparedStatement preparedStatement = null;
        String query = "DELETE FROM Cars WHERE ID = ?";

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

    public static LinkedList<Car> getAllCars(){
        LinkedList<Car> tmp = new LinkedList<Car>();
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT ID FROM Cars";
        try {
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
            while (resultSet.next()) {
                tmp.add(getCar(resultSet.getInt("ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    public static int getCarCount(){
        int count=0;
        Statement commandeStatement = null;
        ResultSet resultSet = null;
        String commandeQuery = "SELECT COUNT(*) FROM Cars";

        try{
            commandeStatement = DataBase.getConnection().createStatement();
            resultSet = commandeStatement.executeQuery(commandeQuery);
            count = resultSet.getInt("COUNT(*)");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }
}
