package GUI.JForms; 

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.imgscalr.Scalr;

import Models.Car;
import Models.Accounts.Compte;
import Controllers.CarController;
import GUI.CurrentSession;
import GUI.Components.ImageAvatar;
import GUI.Components.GLVTextField;
import GUI.Components.Buttons.GLVButton;
import GUI.Components.Notifications.Notification;
import GUI.Components.SearchBar.EventCallBack;
import GUI.Components.SearchBar.EventTextField;
import GUI.Components.SearchBar.TextFieldAnimation;
import GUI.JForms.Achats.AchatDataPanel;
import GUI.JForms.Cars.CarItemPanel;
import GUI.JForms.Requests.RequestDataPanel;
import GUI.JForms.Users.ModifyUserFrame;
public class MainFrame extends JFrame {
    
    static LinkedList<Car> cars;
    static LinkedList<Car> cartList;
    static LinkedList<CarItemPanel> items;
    static LinkedList<CarItemPanel> cartItems;

    public static Color color = Color.white;

    BufferedImage image = null;
    

    public MainFrame() {
        RequestDataPanel requestsDataPanel = new RequestDataPanel(color);
        AchatDataPanel achatsDataPanel = new AchatDataPanel(color);
        initLayout();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        searchBar.setVisible(false);
        acountMgmtTabs.setSelectedIndex(1);

        productScroll.getVerticalScrollBar().setUnitIncrement(16);
        kartPane.getVerticalScrollBar().setUnitIncrement(16);

        loggedEmail.setVerticalAlignment(SwingConstants.CENTER);
        loggedEmail.setHorizontalAlignment(SwingConstants.CENTER);
        loggedPhoneNumber.setVerticalAlignment(SwingConstants.CENTER);
        loggedPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
        loggedUserName.setVerticalAlignment(SwingConstants.CENTER);
        loggedUserName.setHorizontalAlignment(SwingConstants.CENTER);
        loggedcreationDate.setVerticalAlignment(SwingConstants.CENTER);
        loggedcreationDate.setHorizontalAlignment(SwingConstants.CENTER);

        //signInPuctureHolder.setBorder(new LineBorder(Color.BLACK, 2));

        authLoginTextField.setLabelText("Login");
        emailTextField.setLabelText("Email");
        loginTextField.setLabelText("Login");
        phoneNumberTextField.setLabelText("Phone Number");

        sidePanel.setBackground(color);

        productGrid.setBorder(new EmptyBorder(10, 10, 10, 10));
        kartGrid.setBorder(new EmptyBorder(10, 10, 10, 10));

        initIcons(true);
        initProducts();
        initCart();
        initButtonListeners();

        sidePanel.setBackground(Color.BLACK);

        if(CurrentSession.checkIfLogged()){
            if(!CurrentSession.checkIfAdmin()) {
                connect();
                requestButton.setVisible(true);
                achatsButton.setVisible(true);
            }
        } else {
            requestButton.setVisible(false);
            achatsButton.setVisible(false);
        }

        menuTabs.addTab("tab3", requestsDataPanel);

        menuTabs.addTab("tab4", achatsDataPanel);

        RequestDataPanel.updateRequestTable();
        RequestDataPanel.model.fireTableDataChanged();
        AchatDataPanel.updateAchatTable();
        AchatDataPanel.model.fireTableDataChanged();


    } 
    
    private void initVar(){
        uploadPicButton = new GLVButton("",30, 30, Color.white,Color.gray);
        connectButton = new GLVButton("Sign In",30, 75, Color.white,Color.gray);
        signOutButton = new GLVButton("Sign Out",30, 75, Color.white,Color.gray);
        createNewAcountButton = new GLVButton("New Account",30, 75, Color.white,Color.gray);
        cancelSignUpButton = new GLVButton("Cancel",30, 75, Color.white,Color.gray);
        homeButton = new GLVButton("Home",30, 75, Color.white,Color.gray);
        logOutButton = new GLVButton("",30, 30, Color.white,Color.gray);
        modifyUserButton = new GLVButton("",30, 30,Color.white,Color.gray);
        productsButton = new GLVButton("Cars",30, 75, Color.white,Color.gray);
        requestButton = new GLVButton("Requests",30, 75, Color.white,Color.gray);
        settingsButton = new GLVButton("",30, 75, Color.white,Color.gray);
        exitButton = new GLVButton("",30, 30, Color.white,Color.gray);
        signUpButton = new GLVButton("Sign Up",30, 30, Color.white,Color.gray);
        viewDetailsButton = new GLVButton("",30, 30, Color.white,Color.gray);
        addToKartButton = new GLVButton("",30, 30, Color.white,Color.gray);
        payButton = new GLVButton("",30, 30, Color.white,Color.gray);
        removeFromKartButton = new GLVButton("",30, 30, Color.white,Color.gray);
        changeColorButton = new GLVButton("",30, 30, Color.white,Color.gray);
        viewHistoryButton = new GLVButton("",30, 30, Color.white,Color.gray);
        achatsButton = new GLVButton("Achats",30, 30, Color.white,Color.gray);

        addToKartButton.setVisible(false);
        viewHistoryButton.setVisible(false);
        
        searchBar = new TextFieldAnimation();
        emailTextField = new GLVTextField();
        authPasswordField = new javax.swing.JPasswordField();
        phoneNumberTextField = new GLVTextField();
        loginTextField = new GLVTextField();
        passwordField = new javax.swing.JPasswordField();
        reTypePasswordField = new javax.swing.JPasswordField();
        authLoginTextField = new GLVTextField();

        framePanel = new javax.swing.JPanel();
        sidePanel = new JPanel();
        imageLabel = new JLabel();
        MainPanel = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        titleSeparator = new javax.swing.JSeparator();
        titleLabel = new javax.swing.JLabel();
        seatchPanel = new javax.swing.JPanel();
        searchIcon = new javax.swing.JLabel();
        menuTabs = new javax.swing.JTabbedPane();
        acountMgmtTabs = new javax.swing.JTabbedPane();
        signUpPanel = new javax.swing.JPanel();
        uploadPictureHolder = new ImageAvatar();
        signUpDataPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        retypePasswordLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        
        
        signInPanel = new javax.swing.JPanel();
        signInPuctureHolder = new ImageAvatar();
        loginSignInLabel = new javax.swing.JLabel();
        passwordSignInLabel = new javax.swing.JLabel();
        productsPanel = new javax.swing.JPanel();
        productTablePanel = new javax.swing.JPanel();
        productScroll = new javax.swing.JScrollPane();
        productSpacerPanel = new javax.swing.JPanel();
        productsActionsPanel = new javax.swing.JPanel();
        requestsPanel = new javax.swing.JPanel();
        kartTablePanel = new javax.swing.JPanel();
        kartSpacerPanel = new javax.swing.JPanel();
        kartActionsPanel = new javax.swing.JPanel();
        kartPane = new javax.swing.JScrollPane();
        loggedInPanel = new javax.swing.JPanel();
        loggedInPictureHolder = new ImageAvatar();
        loggedUserName = new javax.swing.JLabel();
        loggedEmail = new javax.swing.JLabel();
        loggedPhoneNumber = new javax.swing.JLabel();
        loggedcreationDate = new javax.swing.JLabel();
        productGrid = new javax.swing.JPanel();
        kartGrid = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
    }
    
    private static void initIcons(boolean isdark){
        if (isdark) {
            logOutButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/sign-out.png")));
            uploadPictureHolder.setImage(new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/user-big.png")));
            signInPuctureHolder.setImage(new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/user-big.png")));
            exitButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/cross.png")));
            settingsButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/settings-sliders.png")));
            uploadPicButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/upload.png")));
            modifyUserButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/edit.png")));
            addToKartButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/shopping-cart.png")));
            payButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/dollar.png")));
            removeFromKartButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/bin.png")));
            viewHistoryButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/history.png")));
            ImageIcon logo = new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/app-logo.png"));
            Image logoImg = logo.getImage();
            Image scaledLogoImage = logoImg.getScaledInstance(117, 117, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledLogoImage));
        }else{
            logOutButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/sign-out.png")));
            uploadPictureHolder.setImage(new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/user-big.png")));
            signInPuctureHolder.setImage(new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/user-big.png")));
            exitButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/cross.png")));
            settingsButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/settings-sliders.png")));
            uploadPicButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/upload.png")));
            modifyUserButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/edit.png")));
            addToKartButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/shopping-cart.png")));
            payButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/dollar.png")));
            removeFromKartButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/bin.png")));
            viewHistoryButton.setIcon(new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/history.png")));
            ImageIcon logo = new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/app-logo.png"));
            Image logoImg = logo.getImage();
            Image scaledLogoImage = logoImg.getScaledInstance(117, 117, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledLogoImage));
        }
    }  
    
    private void initButtonListeners(){
        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        homeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menuTabs.setSelectedIndex(0);
                searchBar.setVisible(false);
            }});
        productsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menuTabs.setSelectedIndex(1);
                ActionListener[] actions = searchBar.getActionListeners();
                for (ActionListener listener : actions) {
                    searchBar.removeActionListener(listener);
                }
                //searchBar.addActionListener(pActionListener);
                searchBar.addEvent(new EventTextField() {
                    @Override
                    public void onPressed(EventCallBack call) {
                        String search = searchBar.getText();
                        
                        if(search.equals("")){
                            productGrid.removeAll();
                            for (int i = 0; i < cars.size(); i++) {
                                items.add(new CarItemPanel(cars.get(i),color));
                                productGrid.add(items.get(i));
                            }
                            for (int i = 0; i < 4; i++) {
                                productGrid.add(new CarItemPanel());
                            }
                            productGrid.setLayout(new GridLayout(0,3,20,20));
                        }else{
                            productGrid.removeAll();
                            for (int i = 0; i < cars.size(); i++) {
                                if(cars.get(i).getName().toLowerCase().contains(search.toLowerCase())){
                                    items.add(new CarItemPanel(cars.get(i),color));
                                    productGrid.add(items.get(i));
                                }
                            }
                            for (int i = 0; i < 4; i++) {
                                productGrid.add(new CarItemPanel());
                            }
                            productGrid.setLayout(new GridLayout(0,3,20,20));
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {}
                        call.done();
                        try {
                            SwingUtilities.updateComponentTreeUI(MainFrame.this);
                        } catch (Exception e) {}
                    }
                    @Override
                    public void onCancel() {
                        initProducts();
                    }
                });
                searchBar.setVisible(true);
            }});
        requestButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menuTabs.setSelectedIndex(2);
                ActionListener[] actions = searchBar.getActionListeners();
                for (ActionListener listener : actions) {
                    searchBar.removeActionListener(listener);
                }
                searchBar.addEvent(new EventTextField() {
                    @Override
                    public void onPressed(EventCallBack call) {
                        String search = searchBar.getText();
                        RequestDataPanel.searchFunction(search);
                        try {
                            SwingUtilities.updateComponentTreeUI(MainFrame.this);
                        } catch (Exception e) {}
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}
                        call.done();
                    }
                    @Override
                    public void onCancel() {
                        RequestDataPanel.searchFunction("");
                    }
                });
                searchBar.setVisible(true);
            }});
        achatsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menuTabs.setSelectedIndex(3);
                ActionListener[] actions = searchBar.getActionListeners();
                for (ActionListener listener : actions) {
                    searchBar.removeActionListener(listener);
                }
                searchBar.addEvent(new EventTextField() {
                    @Override
                    public void onPressed(EventCallBack call) {
                        String search = searchBar.getText();
                        AchatDataPanel.searchFunction(search);
                        try {
                            SwingUtilities.updateComponentTreeUI(MainFrame.this);
                        } catch (Exception e) {}
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}
                        call.done();
                    }
                    @Override
                    public void onCancel() {
                        AchatDataPanel.searchFunction("");
                    }
                });
                searchBar.setVisible(true);
            }});
        settingsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsFrame.createSettingsPage(MainFrame.color);
            }});
        signUpButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginTextField.getText().equals("") || String.valueOf(passwordField.getPassword()).equals("")) {
                    Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "Account cannot be created");
                    notification.showNotification();
                    return;
                }
                if (!String.valueOf(passwordField.getPassword()).equals(String.valueOf(reTypePasswordField.getPassword()))) {
                    Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "Password and retypr password are not the same");
                    notification.showNotification();
                    return;
                }
                boolean accountCreated = Compte.signUp(loginTextField.getText(), String.valueOf(passwordField.getPassword()), image, emailTextField.getText(), phoneNumberTextField.getText());
                if (accountCreated) {
                    Notification notification = new Notification(MainFrame.this, Notification.Type.SUCCESS, Notification.Location.BOTTOM_CENTER, "User Created succesfully");
                    notification.showNotification();
                    acountMgmtTabs.setSelectedIndex(1);
                } else {
                    showMessageDialog(null, "Account can not be created");
                }
            }});
        uploadPicButton.addActionListener(new ActionListener(){
                public ImageIcon resize(String imgPath) {
                    ImageIcon path = new ImageIcon(imgPath);
                    Image img = path.getImage();
                    Image newImg = img.getScaledInstance(imageLabel.getWidth() * 2, imageLabel.getHeight() * 2,
                            Image.SCALE_SMOOTH);
                    ImageIcon image = new ImageIcon(newImg);
                    return image;
                }
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser file = new JFileChooser();
                    file.setCurrentDirectory(new File(System.getProperty("user.home")));
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
                    file.addChoosableFileFilter(filter);
                    int res = file.showSaveDialog(null);
                    if (res == JFileChooser.APPROVE_OPTION) {
                        File selFile = file.getSelectedFile();
                        String path = selFile.getAbsolutePath();
                        try {
                            image = ImageIO.read(selFile);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        uploadPictureHolder.setImage(resize(path));
                        pack();
                    }
                }});
        connectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(authLoginTextField.getText().equals("") || String.valueOf(authPasswordField.getPassword()).equals("")){
                        Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "Please Enter Your Information");
                        notification.showNotification();
                        return;
                    }
                    boolean logged = CurrentSession.setSession(authLoginTextField.getText(),String.valueOf(authPasswordField.getPassword()));
                    if (logged){  
                        if(CurrentSession.checkIfLogged()){
                            if(CurrentSession.checkIfAdmin()){
                                MainFrame.this.dispose();
                                if (CurrentSession.getAdmin() != null) {
                                    AdminControlFrame.startAdminControlFrame(MainFrame.color);
                                    System.out.println("An Admin is now Logged");
                                }
                            }else{
                                RequestDataPanel.updateRequestTable();
                                RequestDataPanel.model.fireTableDataChanged();
                                AchatDataPanel.updateAchatTable();
                                AchatDataPanel.model.fireTableDataChanged();
                                connect();
                                Notification notification = new Notification(MainFrame.this, Notification.Type.SUCCESS, Notification.Location.BOTTOM_CENTER, "Logged In Successfully, Welcome " + CurrentSession.getUser().getUsername());
                                notification.showNotification();
                            }
                        }
                    }else{
                        Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "Couldn't Log You In");
                        notification.showNotification();
                    }
                }});
        createNewAcountButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        acountMgmtTabs.setSelectedIndex(0);
                    }});
        cancelSignUpButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        image = null;
                        uploadPictureHolder.setImage(new ImageIcon(getClass().getResource("/GUI/resources/black_icons/user-big.png")));
                        acountMgmtTabs.setSelectedIndex(1);
                    }});
        logOutButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!CurrentSession.checkIfLogged()){
                            Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "You are not logged in");
                            notification.showNotification();
                            return;
                        }
                        CurrentSession.logOut();
                        searchBar.setVisible(false);
                        requestButton.setVisible(false);
                        achatsButton.setVisible(false);
                        loggedEmail.setText("");
                        loggedPhoneNumber.setText("");
                        loggedUserName.setText("");
                        loggedcreationDate.setText("");
                        loggedInPictureHolder.setImage(new javax.swing.ImageIcon(getClass().getResource("/GUI/resources/black_icons/user-big.png")));
                        acountMgmtTabs.setSelectedIndex(1);
                        authLoginTextField.setText("");
                        authPasswordField.setText("");
                        menuTabs.setSelectedIndex(0);
                        Notification notification = new Notification(MainFrame.this, Notification.Type.SUCCESS, Notification.Location.BOTTOM_CENTER, "Logged Out Successfully");
                        notification.showNotification();
                    }});
        modifyUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CurrentSession.checkIfLogged()){
                    ModifyUserFrame.startModifyUserFrame(CurrentSession.getUser(), color);
                }else{
                    Notification notification = new Notification(MainFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "You must be logged in to modify users");
                    notification.showNotification();
                }
                SwingUtilities.updateComponentTreeUI(MainFrame.this);
            }});
        }  
    
    public static void connect(){
        // RequestDataPanel.updateRequestTable();
        // RequestDataPanel.model.fireTableDataChanged();
        // AchatDataPanel.updateAchatTable();
        // AchatDataPanel.model.fireTableDataChanged();
        loggedEmail.setText(CurrentSession.getUser().getEmail());
        loggedPhoneNumber.setText(CurrentSession.getUser().getPhoneNumber());
        loggedUserName.setText(CurrentSession.getUser().getUsername());
        loggedcreationDate.setText(CurrentSession.getUser().getDate());
        requestButton.setVisible(true);
        achatsButton.setVisible(true);
        if (CurrentSession.getUser().getImage() != null) {
            loggedInPictureHolder.setImage(new javax.swing.ImageIcon(Scalr.resize(CurrentSession.getUser().getImage(), Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, loggedInPictureHolder.getWidth(), loggedInPictureHolder.getHeight())));
        }else{
            loggedInPictureHolder.setImage(new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/user-big.png")));
        }
        acountMgmtTabs.setSelectedIndex(2);
    }

    private static void initProducts(){
        productScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        productGrid.removeAll();
        cars = CarController.getAllCars();
        items = new LinkedList<>();
        for (Car car : cars) {
            CarItemPanel item = new CarItemPanel(car, color);
            items.add(item);
            productGrid.add(item);
        }
        for (int i = 0; i < 4; i++) {
            productGrid.add(new CarItemPanel());
        }
        productGrid.setLayout(new GridLayout(0,3,20,20));
    }

    private static void initCart(){
        kartPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cartItems = new LinkedList<>();
        cartList = new LinkedList<>();
        kartGrid.setLayout(new GridLayout(0,3,20,20));
    }

    public static void changeColors(Color color){
        MainFrame.color = color;
        RequestDataPanel.changeColors(color);
        AchatDataPanel.changeColors(color);
        if (color != null) {
            loggedInPictureHolder.setGradientColor1(color);
            loggedInPictureHolder.setGradientColor2(color);
            signInPuctureHolder.setGradientColor1(color);
            signInPuctureHolder.setGradientColor2(color);
            uploadPictureHolder.setGradientColor1(color);
            uploadPictureHolder.setGradientColor2(color);
            searchBar.setAnimationColor(color);
            searchBar.setCaretColor(color);
            authLoginTextField.setLineColor(color);
            authPasswordField.addFocusListener(new FocusListener(){
                @Override
                public void focusGained(FocusEvent e) {
                    authPasswordField.setBorder(BorderFactory.createLineBorder(color));
                }
                @Override
                public void focusLost(FocusEvent e) {
                    authPasswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
                }});
            passwordField.addFocusListener(new FocusListener(){
                @Override
                public void focusGained(FocusEvent e) {
                    passwordField.setBorder(BorderFactory.createLineBorder(color));
                }
                @Override
                public void focusLost(FocusEvent e) {
                    passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
                }});
            reTypePasswordField.addFocusListener(new FocusListener(){
                @Override
                public void focusGained(FocusEvent e) {
                    reTypePasswordField.setBorder(BorderFactory.createLineBorder(color));
                }
                @Override
                public void focusLost(FocusEvent e) {
                    reTypePasswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
                }});
            emailTextField.setLineColor(color);
            loginTextField.setLineColor(color);
            phoneNumberTextField.setLineColor(color);
            sidePanel.setBackground(color);
            addToKartButton.changeButtonColor(color, color);
            changeColorButton.changeButtonColor(color, color);
            productsButton.changeButtonColor(color, color);
            modifyUserButton.changeButtonColor(color, color);
            connectButton.changeButtonColor(color, color);
            createNewAcountButton.changeButtonColor(color, color);
            settingsButton.changeButtonColor(color, color);
            removeFromKartButton.changeButtonColor(color, color);
            payButton.changeButtonColor(color, color);
            exitButton.changeButtonColor(color, color);
            homeButton.changeButtonColor(color, color);
            requestButton.changeButtonColor(color, color);
            logOutButton.changeButtonColor(color, color);
            signOutButton.changeButtonColor(color, color);
            signUpButton.changeButtonColor(color, color);
            uploadPicButton.changeButtonColor(color, color);
            viewDetailsButton.changeButtonColor(color, color);
            cancelSignUpButton.changeButtonColor(color, color);
            uploadPicButton.changeButtonColor(color, color);
            viewHistoryButton.changeButtonColor(color, color);
            achatsButton.changeButtonColor(color, color);
            initProducts();
            initCart();
            double luminescence = 0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue();
            if (luminescence < 128) {
                initIcons(false);
            }else{
                initIcons(true);
            }
        }
    }

    public static void updateFrame(){
        SwingUtilities.updateComponentTreeUI(SwingUtilities.getRootPane(MainPanel));
    }

    private void initLayout() {     

        initVar();

        setUndecorated(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getRootPane().setBorder(new LineBorder(Color.BLACK,2,true));
        // setIconImage(new ImageIcon(getClass().getResource("/com/example/GUI/resources/black_icons/app-logo.png")).getImage());

        framePanel.setLayout(new java.awt.BorderLayout());

        homeButton.setPreferredSize(new java.awt.Dimension(73, 30));

        productsButton.setPreferredSize(new java.awt.Dimension(73, 30));

        requestButton.setPreferredSize(new java.awt.Dimension(73, 30));

        imageLabel.setPreferredSize(new java.awt.Dimension(60, 60));

        logOutButton.setPreferredSize(new java.awt.Dimension(30, 30));

        achatsButton.setPreferredSize(new java.awt.Dimension(30, 30));
        settingsButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(requestButton, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(productsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(achatsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(productsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(requestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(achatsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );


        framePanel.add(sidePanel, java.awt.BorderLayout.LINE_START);

        //MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        //Header.setBackground(new java.awt.Color(255, 255, 255));

        exitButton.setPreferredSize(new java.awt.Dimension(30, 30));

        //titleSeparator.setBackground(new java.awt.Color(0, 0, 0));

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titleLabel.setText("Gestion Location Voitures");

        //seatchPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout seatchPanelLayout = new javax.swing.GroupLayout(seatchPanel);
        seatchPanel.setLayout(seatchPanelLayout);
        seatchPanelLayout.setHorizontalGroup(
            seatchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seatchPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        seatchPanelLayout.setVerticalGroup(
            seatchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, seatchPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(seatchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchBar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(titleSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                        .addComponent(seatchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(titleLabel))
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleSeparator)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(seatchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        MainPanel.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 160));

        signUpButton.setText("Sign Up");
        signUpButton.setPreferredSize(new java.awt.Dimension(69, 30));

        loginLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loginLabel.setText("Login :");

        retypePasswordLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        retypePasswordLabel.setText("Re-Type Password :");

        phoneLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        phoneLabel.setText("Phone Number :");

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordLabel.setText("Password :");

        emailLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        emailLabel.setText("Email :");

        uploadPicButton.setPreferredSize(new java.awt.Dimension(30, 30));

        passwordField.setPreferredSize(new java.awt.Dimension(112, 22));

        reTypePasswordField.setPreferredSize(new java.awt.Dimension(112, 22));

        javax.swing.GroupLayout signUpDataPanelLayout = new javax.swing.GroupLayout(signUpDataPanel);
        signUpDataPanel.setLayout(signUpDataPanelLayout);
        signUpDataPanelLayout.setHorizontalGroup(
            signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpDataPanelLayout.createSequentialGroup()
                .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpDataPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(signUpDataPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(loginTextField)
                                .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reTypePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(310, Short.MAX_VALUE))
        );
        signUpDataPanelLayout.setVerticalGroup(
            signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpDataPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reTypePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cancelSignUpButton.setText("Cancel");
        cancelSignUpButton.setPreferredSize(new java.awt.Dimension(69, 23));

        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Grp 19.");

        javax.swing.GroupLayout signUpPanelLayout = new javax.swing.GroupLayout(signUpPanel);
        signUpPanel.setLayout(signUpPanelLayout);
        signUpPanelLayout.setHorizontalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpPanelLayout.createSequentialGroup()
                        .addComponent(uploadPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(signUpDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(signUpPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelSignUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(signUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        signUpPanelLayout.setVerticalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(signUpDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(signUpPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(uploadPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(signUpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelSignUpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        acountMgmtTabs.addTab("tab2", signUpPanel);

        //signInPanel.setBackground(new java.awt.Color(255, 255, 255));

        loginSignInLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loginSignInLabel.setText("Login :");
        loginSignInLabel.setPreferredSize(new java.awt.Dimension(56, 30));
        loginSignInLabel.setRequestFocusEnabled(false);

        passwordSignInLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordSignInLabel.setText("Password :");
        passwordSignInLabel.setPreferredSize(new java.awt.Dimension(56, 30));
        passwordSignInLabel.setRequestFocusEnabled(false);

        authPasswordField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        authLoginTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        connectButton.setText("Sign In");
        connectButton.setPreferredSize(new java.awt.Dimension(30, 30));

        createNewAcountButton.setText("New Account");
        createNewAcountButton.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("Grp 19.");

        javax.swing.GroupLayout signInPanelLayout = new javax.swing.GroupLayout(signInPanel);
        signInPanel.setLayout(signInPanelLayout);
        signInPanelLayout.setHorizontalGroup(
            signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createNewAcountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(authLoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(authPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(signInPanelLayout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(signInPuctureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(262, Short.MAX_VALUE))
        );
        signInPanelLayout.setVerticalGroup(
            signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signInPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(signInPuctureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(authLoginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(authPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createNewAcountButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        acountMgmtTabs.addTab("tab2", signInPanel);

        modifyUserButton.setPreferredSize(new java.awt.Dimension(30, 30));

        loggedUserName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loggedEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loggedPhoneNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        loggedcreationDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setText("Email :");

        jLabel2.setText("Name :");

        jLabel3.setText("Phone Number :");

        jLabel4.setText("Creation Date :");

        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Grp 19.");

        viewHistoryButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout loggedInPanelLayout = new javax.swing.GroupLayout(loggedInPanel);
        loggedInPanel.setLayout(loggedInPanelLayout);
        loggedInPanelLayout.setHorizontalGroup(
            loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loggedInPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loggedInPanelLayout.createSequentialGroup()
                        .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(104, 104, 104))
                    .addGroup(loggedInPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(loggedUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loggedEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loggedPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loggedcreationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(456, 456, 456))
            .addGroup(loggedInPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(loggedInPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loggedInPanelLayout.setVerticalGroup(
            loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInPanelLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(loggedInPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loggedUserName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loggedEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loggedPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loggedcreationDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modifyUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(viewHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        acountMgmtTabs.addTab("tab3", loggedInPanel);

        menuTabs.addTab("tab5", acountMgmtTabs);

        //productsPanel.setBackground(new java.awt.Color(255, 255, 255));

        //productTablePanel.setBackground(new java.awt.Color(255, 255, 255));
        productTablePanel.setLayout(new java.awt.BorderLayout());

        //productScroll.setBackground(new java.awt.Color(255, 255, 255));
        productScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout productGridLayout = new javax.swing.GroupLayout(productGrid);
        productGrid.setLayout(productGridLayout);
        productGridLayout.setHorizontalGroup(
            productGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
        );
        productGridLayout.setVerticalGroup(
            productGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );

        productScroll.setViewportView(productGrid);

        productTablePanel.add(productScroll, java.awt.BorderLayout.CENTER);

        //productSpacerPanel.setBackground(new java.awt.Color(255, 255, 255));
        productSpacerPanel.setPreferredSize(new java.awt.Dimension(865, 25));

        javax.swing.GroupLayout productSpacerPanelLayout = new javax.swing.GroupLayout(productSpacerPanel);
        productSpacerPanel.setLayout(productSpacerPanelLayout);
        productSpacerPanelLayout.setHorizontalGroup(
            productSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        productSpacerPanelLayout.setVerticalGroup(
            productSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        productTablePanel.add(productSpacerPanel, java.awt.BorderLayout.PAGE_START);

        //productsActionsPanel.setBackground(new java.awt.Color(255, 255, 255));
        productsActionsPanel.setPreferredSize(new java.awt.Dimension(865, 50));

        addToKartButton.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Grp 19.");

        javax.swing.GroupLayout productsActionsPanelLayout = new javax.swing.GroupLayout(productsActionsPanel);
        productsActionsPanel.setLayout(productsActionsPanelLayout);
        productsActionsPanelLayout.setHorizontalGroup(
            productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productsActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 528, Short.MAX_VALUE)
                .addComponent(addToKartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        productsActionsPanelLayout.setVerticalGroup(
            productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsActionsPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addToKartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        productTablePanel.add(productsActionsPanel, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout productsPanelLayout = new javax.swing.GroupLayout(productsPanel);
        productsPanel.setLayout(productsPanelLayout);
        productsPanelLayout.setHorizontalGroup(
            productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        productsPanelLayout.setVerticalGroup(
            productsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productsPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(productTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuTabs.addTab("tab1", productsPanel);

        //kartPanel.setBackground(new java.awt.Color(255, 255, 255));

        //kartTablePanel.setBackground(new java.awt.Color(255, 255, 255));
        kartTablePanel.setLayout(new java.awt.BorderLayout());

        //kartSpacerPanel.setBackground(new java.awt.Color(255, 255, 255));
        kartSpacerPanel.setPreferredSize(new java.awt.Dimension(895, 25));

        javax.swing.GroupLayout kartSpacerPanelLayout = new javax.swing.GroupLayout(kartSpacerPanel);
        kartSpacerPanel.setLayout(kartSpacerPanelLayout);
        kartSpacerPanelLayout.setHorizontalGroup(
            kartSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        kartSpacerPanelLayout.setVerticalGroup(
            kartSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        kartTablePanel.add(kartSpacerPanel, java.awt.BorderLayout.PAGE_START);

        //kartActionsPanel.setBackground(new java.awt.Color(255, 255, 255));
        kartActionsPanel.setPreferredSize(new java.awt.Dimension(895, 50));

        payButton.setPreferredSize(new java.awt.Dimension(30, 30));

        removeFromKartButton.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Grp 19.");

        javax.swing.GroupLayout kartActionsPanelLayout = new javax.swing.GroupLayout(kartActionsPanel);
        kartActionsPanel.setLayout(kartActionsPanelLayout);
        kartActionsPanelLayout.setHorizontalGroup(
            kartActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kartActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 458, Short.MAX_VALUE)
                .addComponent(payButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeFromKartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kartActionsPanelLayout.setVerticalGroup(
            kartActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kartActionsPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(kartActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeFromKartButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(payButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        kartTablePanel.add(kartActionsPanel, java.awt.BorderLayout.PAGE_END);

        //kartPane.setBackground(new java.awt.Color(255, 255, 255));
        kartPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout kartGridLayout = new javax.swing.GroupLayout(kartGrid);
        kartGrid.setLayout(kartGridLayout);
        kartGridLayout.setHorizontalGroup(
            kartGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
        );
        kartGridLayout.setVerticalGroup(
            kartGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        kartPane.setViewportView(kartGrid);

        kartTablePanel.add(kartPane, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout kartPanelLayout = new javax.swing.GroupLayout(requestsPanel);
        requestsPanel.setLayout(kartPanelLayout);
        kartPanelLayout.setHorizontalGroup(
            kartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kartTablePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
        );
        kartPanelLayout.setVerticalGroup(
            kartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kartPanelLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(kartTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        MainPanel.add(menuTabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 870, 520));

        framePanel.add(MainPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(framePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(framePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    
    public static void startMainFrame(Color color) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
                changeColors(color);
            }
        });
    }
    
    private static GLVButton addToKartButton;
    private static GLVButton changeColorButton;
    private static GLVButton productsButton;
    private static GLVButton modifyUserButton;
    private static GLVButton connectButton;
    private static GLVButton createNewAcountButton;
    private static GLVButton settingsButton;
    private static GLVButton removeFromKartButton;
    private static GLVButton payButton;
    private static GLVButton exitButton;
    private static GLVButton homeButton;
    private static GLVButton requestButton;
    private static GLVButton logOutButton;
    private static GLVButton signOutButton;
    private static GLVButton signUpButton;
    private static GLVButton uploadPicButton;
    private static GLVButton viewDetailsButton;
    private static GLVButton cancelSignUpButton;
    private static GLVButton viewHistoryButton;
    private static GLVButton achatsButton;

    private JPanel Header;
    private static JPanel MainPanel;
    private JPanel framePanel;
    private static JPanel sidePanel;
    private JPanel requestsPanel;
    private JPanel kartSpacerPanel;
    private JPanel kartTablePanel;
    private JPanel productSpacerPanel;
    private JPanel productTablePanel;
    private JPanel productsActionsPanel;
    private JPanel productsPanel;
    private JPanel seatchPanel;
    private JPanel signInPanel;
    private JPanel signUpDataPanel;
    private JPanel signUpPanel;
    private static JPanel kartGrid;
    private static JPanel productGrid;
    private JPanel kartActionsPanel;
    private JPanel loggedInPanel;

    private JLabel emailLabel;
    private static JLabel imageLabel;
    private static JLabel loggedEmail;
    private static ImageAvatar loggedInPictureHolder;
    private static JLabel loggedPhoneNumber;
    private static JLabel loggedUserName;
    private static JLabel loggedcreationDate;
    private JLabel loginLabel;
    private JLabel loginSignInLabel;
    private JLabel passwordLabel;
    private JLabel passwordSignInLabel;
    private JLabel phoneLabel;
    private JLabel retypePasswordLabel;
    private JLabel searchIcon;
    private static ImageAvatar signInPuctureHolder;
    private JLabel titleLabel;
    private static ImageAvatar uploadPictureHolder;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;

    private static JTabbedPane acountMgmtTabs;
    private static GLVTextField authLoginTextField;
    private static JPasswordField authPasswordField;
    private static GLVTextField emailTextField;
    private static JScrollPane kartPane;
    
    private static GLVTextField loginTextField;
    private static JTabbedPane menuTabs;
    private static JPasswordField passwordField;
    private static GLVTextField phoneNumberTextField;
    private static JScrollPane productScroll;
    private static JPasswordField reTypePasswordField;
    private static TextFieldAnimation searchBar;
    private static JSeparator titleSeparator;
}