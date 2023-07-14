package Models.Accounts;

import java.awt.image.BufferedImage;

import Controllers.UsersController;

public class Admin extends Compte{
    public Admin(int id, String username, String password, String date, BufferedImage image,String email,
    String phoneNumber){
        super(id,username,password,date,image,email,phoneNumber);
    }

    @Override
    public String toString() {
        return "Admin = true :: "+super.toString();
    }

    public static void removeUserByID(int Id) {
        UsersController.delUser(Id);
    }

    public static void addProduct(String FournisseurName, String Productname, String description, BufferedImage image, Double price,int quantity){
    }

    public static void removeProductByID(int Id) {
    }
}
