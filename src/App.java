import javax.swing.UIManager;

import DataBase.DataBase;
import GUI.Components.SplashScreen.SplashScreen;
import GUI.JForms.MainFrame;
import com.formdev.flatlaf.FlatDarkLaf;

public class App {
    public static void main(String[] args) {
        DataBase.initDB("./src/DataBase/data.db");
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        new SplashScreen(null, true).setVisible(true);
        MainFrame.startMainFrame(MainFrame.color);
    }
}
