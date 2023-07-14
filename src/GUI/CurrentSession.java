package GUI;

import Models.Accounts.Admin;
import Models.Accounts.User;
import Models.Accounts.Compte;
import Controllers.UsersController;

public class CurrentSession {
    private CurrentSession() {}
    private static CurrentSession instance;
    public static CurrentSession getInstance() {
        if (instance == null) {
            instance = new CurrentSession();
        }
        return instance;
    }
    private static User user = null;
    private static Admin admin = null;

    public static boolean setSession(String login, String password){
        int ID = Compte.searchForUsers(login, password);
        if (ID == -1)
            return false;
        if (ID == -2)
            return false;
        Compte.checkIfAdmin(ID);
        if (Compte.checkIfAdmin(ID)) {
            setAdmin(UsersController.getAdmin(ID));
            return true;
        } else {
            setUser(UsersController.getUser(ID));
            return true;
        }
    }

    public static void logOut(){
        user = null;
        admin = null;
    }
    
    public static boolean checkIfLogged(){
        if (user == null && admin == null)
            return false;
        return true;
    }

    public static boolean checkIfAdmin() {
        if (admin == null) {
            return false;
        } else {
            return true;
        }
    }

    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        CurrentSession.user = user;
    }
    public static Admin getAdmin() {
        return admin;
    }
    public static void setAdmin(Admin admin) {
        CurrentSession.admin = admin;
    }
}
