package GUI.JForms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.imgscalr.Scalr;
import java.awt.Image;

import Models.Car;
import Models.Accounts.Admin;
import Models.Accounts.User;
import Controllers.CarController;
import Controllers.UsersController;
import GUI.CurrentSession;
import GUI.Components.ImageAvatar;
import GUI.Components.GLVTextField;
import GUI.Components.Buttons.GLVButton;
import GUI.Components.Notifications.Notification;
import GUI.Components.SearchBar.EventCallBack;
import GUI.Components.SearchBar.EventTextField;
import GUI.Components.SearchBar.TextFieldAnimation;
import GUI.JForms.Achats.AchatDataPanel;
import GUI.JForms.Cars.AddCarFrame;
import GUI.JForms.Cars.CarItemPanel;
import GUI.JForms.Requests.RequestDataPanel;
import GUI.JForms.Users.AddUserFrame;
import GUI.JForms.Users.ModifyUserFrame;
import GUI.JForms.Users.UserPanel;

public class AdminControlFrame extends JFrame {

    public static LinkedList<User> users;
    public static LinkedList<Car> carList;
    public static LinkedList<UserPanel> userPanels;
    public static LinkedList<CarItemPanel> productPanels;

    public static Color color = Color.white;

    public AdminControlFrame() {
        super();
        
         RequestDataPanel requestsDataPanel = new RequestDataPanel(color);
     AchatDataPanel achatsDataPanel = new AchatDataPanel(color);

        initLayout();

        connect();

        

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        searchBar.setVisible(false);

        userScroll.getVerticalScrollBar().setUnitIncrement(16);
        productScroll.getVerticalScrollBar().setUnitIncrement(16);

        loggedEmail.setVerticalAlignment(SwingConstants.CENTER);
        loggedEmail.setHorizontalAlignment(SwingConstants.CENTER);
        loggedPhoneNumber.setVerticalAlignment(SwingConstants.CENTER);
        loggedPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
        loggedUserName.setVerticalAlignment(SwingConstants.CENTER);
        loggedUserName.setHorizontalAlignment(SwingConstants.CENTER);
        loggedcreationDate.setVerticalAlignment(SwingConstants.CENTER);
        loggedcreationDate.setHorizontalAlignment(SwingConstants.CENTER);

        usersGrid.setBorder(new EmptyBorder(10, 10, 10, 10));
        productsGrid.setBorder(new EmptyBorder(10, 10, 10, 10));

        menuTabs.add("", requestsDataPanel);
        menuTabs.add("", achatsDataPanel);

        initIcons(false);
        initUsers();
        initCars();
        initButtonListeners();
        removeUserButton.setVisible(true);
    } 

    private void initVar(){
        connectButton = new GLVButton("Sign In",30, 75, Color.white,Color.gray);
        signOutButton = new GLVButton("Sign Out",30, 75, Color.white,Color.gray);
        createNewAcountButton = new GLVButton("Create New Account",30, 75, Color.white,Color.gray);
        cancelSignUpButton = new GLVButton("Cancel",30, 75, Color.white,Color.gray);
        homeButton = new GLVButton("Home",30, 75, Color.white,Color.gray);
        logOutButton = new GLVButton("",30, 30, Color.white,Color.gray);
        requestsButton = new GLVButton("Requests",30, 75, Color.white,Color.gray);
        viewHistoryButton = new GLVButton("",30, 75, Color.white,Color.gray);
        downloadRepportButton = new GLVButton("",30, 30, Color.white,Color.gray);
        achatsButton = new GLVButton("Achats",30, 30, Color.white,Color.gray);

        downloadRepportButton.setVisible(false);
        viewHistoryButton.setVisible(false);

        framePanel = new javax.swing.JPanel();
        SidePanel = new JPanel();
        usersButton = new GLVButton("Users",30, 75, Color.white,Color.gray);
        productsButton = new GLVButton("Products",30, 75, Color.white,Color.gray);
        settingsButton = new GLVButton("",30, 75, Color.white,Color.gray);
        imageLabel = new javax.swing.JLabel();
        MainPanel = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        exitButton = new GLVButton("",30, 30, Color.white,Color.gray);
        titleSeparator = new javax.swing.JSeparator();
        titleLabel = new javax.swing.JLabel();
        seatchPanel = new javax.swing.JPanel();
        searchIcon = new javax.swing.JLabel();
        searchBar = new TextFieldAnimation();
        menuTabs = new javax.swing.JTabbedPane();
        acountMgmtTabs = new javax.swing.JTabbedPane();
        signUpPanel = new javax.swing.JPanel();
        signUpButton = new GLVButton("Sign Up",30, 30, Color.white,Color.gray);
        uploadPictureHolder = new ImageAvatar();
        signUpDataPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        retypePasswordLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new GLVTextField();
        phoneNumberTextField = new GLVTextField();
        loginTextField = new GLVTextField();
        uploadPicButton = new GLVButton("",30, 30, Color.white,Color.gray);
        passwordField = new javax.swing.JPasswordField();
        reTypePasswordField = new javax.swing.JPasswordField();
        signInPanel = new javax.swing.JPanel();
        signInPuctureHolder = new ImageAvatar();
        loginSignInLabel = new javax.swing.JLabel();
        passwordSignInLabel = new javax.swing.JLabel();
        authPasswordField = new javax.swing.JPasswordField();
        authLoginTextField = new GLVTextField();
        usersPanel = new javax.swing.JPanel();
        usersTablePanel = new javax.swing.JPanel();
        userScroll = new javax.swing.JScrollPane();
        userSpacerPanel = new javax.swing.JPanel();
        usersActionsPanel = new javax.swing.JPanel();
        removeUserButton = new GLVButton("",30, 30, Color.white,Color.gray);
        addUserButton = new GLVButton("",30, 30, Color.white,Color.gray);
        productPanel = new javax.swing.JPanel();
        productTablePanel = new javax.swing.JPanel();
        productSpacerPanel = new javax.swing.JPanel();
        productActionsPanel = new javax.swing.JPanel();
        addProductButton = new GLVButton("",30, 30, Color.white,Color.gray);
        removeProductButton = new GLVButton("",30, 60, Color.white,Color.gray);
        productScroll = new javax.swing.JScrollPane();
        //changeColorButton = new MoujaButton("",30, 30, Color.white,Color.gray);
        loggedInPanel = new javax.swing.JPanel();
        loggedInPictureHolder = new ImageAvatar();
        modifyUserButton = new GLVButton("",30, 30,Color.white,Color.gray);
        loggedUserName = new javax.swing.JLabel();
        loggedEmail = new javax.swing.JLabel();
        loggedPhoneNumber = new javax.swing.JLabel();
        loggedcreationDate = new javax.swing.JLabel();
        usersGrid = new javax.swing.JPanel();
        productsGrid = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
    }
    
    private static void initIcons(boolean isDark){
        if(isDark){
            logOutButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/black_icons/sign-out.png")));
            uploadPictureHolder.setImage(new javax.swing.ImageIcon(
                    AdminControlFrame.class.getResource("/GUI/resources/black_icons/user.png")));
            signInPuctureHolder.setImage(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/black_icons/user.png")));
            exitButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/black_icons/cross.png")));
            settingsButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/black_icons/settings-sliders.png")));
            uploadPicButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/black_icons/upload.png")));
            modifyUserButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/black_icons/edit.png")));
            removeProductButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/black_icons/bin.png")));
            addUserButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/black_icons/add-user.png")));
            removeUserButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/black_icons/bin.png")));
            addProductButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/black_icons/plus.png")));
            viewHistoryButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/black_icons/history.png")));
            downloadRepportButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/black_icons/rapport.png")));
            ImageIcon logo = new ImageIcon(MainFrame.class.getResource("/GUI/resources/black_icons/app-logo.png"));
            Image logoImg = logo.getImage();
            Image scaledLogoImage = logoImg.getScaledInstance(117, 117, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledLogoImage));
            searchBar.setForeground(Color.white);
        }else{
            logOutButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/sign-out.png")));
            uploadPictureHolder.setImage(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/user.png")));
            signInPuctureHolder.setImage(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/user.png")));
            exitButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/cross.png")));
            settingsButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/settings-sliders.png")));
            uploadPicButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/upload.png")));
            modifyUserButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/edit.png")));
            removeProductButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/bin.png")));
            addUserButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/add-user.png")));
            removeUserButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/bin.png")));
            addProductButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/plus.png")));
            viewHistoryButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/history.png")));
            downloadRepportButton.setIcon(new javax.swing.ImageIcon(
                AdminControlFrame.class.getResource("/GUI/resources/white_icons/rapport.png")));
            ImageIcon logo = new ImageIcon(MainFrame.class.getResource("/GUI/resources/white_icons/app-logo.png"));
            Image logoImg = logo.getImage();
            Image scaledLogoImage = logoImg.getScaledInstance(117, 117, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledLogoImage));
            searchBar.setForeground(Color.BLACK);
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
        usersButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menuTabs.setSelectedIndex(1);
                ActionListener[] actions = searchBar.getActionListeners();
                for (ActionListener listener : actions) {
                    searchBar.removeActionListener(listener);
                }
                searchBar.addEvent(new EventTextField() {
                    @Override
                    public void onPressed(EventCallBack call) {
                        String search = searchBar.getText();
                        if(search.equals("")){
                            usersGrid.removeAll();
                            for (int i = 0; i < users.size(); i++) {
                                userPanels.add(new UserPanel(users.get(i),AdminControlFrame.color));
                                usersGrid.add(userPanels.get(i));
                            }
                            for (int i = 0; i < 3; i++) {
                                usersGrid.add(new UserPanel());
                            }
                            usersGrid.setLayout(new GridLayout(0,3,20,20));
                        }else{
                            usersGrid.removeAll();
                            for (int i = 0; i < users.size(); i++) {
                                if(users.get(i).getUsername().toLowerCase().contains(search.toLowerCase())){
                                    userPanels.add(new UserPanel(users.get(i),AdminControlFrame.color));
                                    usersGrid.add(userPanels.get(i));
                                }
                            }
                            for (int i = 0; i < 3; i++) {
                                usersGrid.add(new UserPanel());
                            }
                            usersGrid.setLayout(new GridLayout(0,3,20,20));
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {}
                        call.done();
                        try {
                            SwingUtilities.updateComponentTreeUI(AdminControlFrame.this);
                        } catch (Exception e) {}
                    }
                    @Override
                    public void onCancel() {
                        initUsers();
                    }
                });
                searchBar.setVisible(true);
            }});
        productsButton.addActionListener(new ActionListener(){
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
                        if(search.equals("")){
                            productsGrid.removeAll();
                            for (int i = 0; i < carList.size(); i++) {
                                productPanels.add(new CarItemPanel(carList.get(i),AdminControlFrame.color));
                                productsGrid.add(productPanels.get(i));
                            }
                            for (int i = 0; i < 3; i++) {
                                productsGrid.add(new CarItemPanel());
                            }
                            productsGrid.setLayout(new GridLayout(0,3,20,20));
                        }else{
                            productsGrid.removeAll();
                            for (int i = 0; i < carList.size(); i++) {
                                if(carList.get(i).getName().toLowerCase().contains(search.toLowerCase())){
                                    productPanels.add(new CarItemPanel(carList.get(i),AdminControlFrame.color));
                                    productsGrid.add(productPanels.get(i));
                                }
                            }
                            for (int i = 0; i < 3; i++) {
                                productsGrid.add(new CarItemPanel());
                            }
                            productsGrid.setLayout(new GridLayout(0,3,20,20));
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {}
                        call.done();
                        try {
                            SwingUtilities.updateComponentTreeUI(AdminControlFrame.this);
                        } catch (Exception e) {}
                    }
                    @Override
                    public void onCancel() {
                        initCars();
                    }
                });
                searchBar.setVisible(true);
            }});
        requestsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBar.setVisible(true);
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
                            SwingUtilities.updateComponentTreeUI(AdminControlFrame.this);
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
                menuTabs.setSelectedIndex(3);
            }});
        achatsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBar.setVisible(true);
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
                            SwingUtilities.updateComponentTreeUI(AdminControlFrame.this);
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
                menuTabs.setSelectedIndex(4);
            }});
        settingsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsFrame.createSettingsPage(AdminControlFrame.color);
            }});
        connectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(authLoginTextField.getText().equals("") || String.valueOf(authPasswordField.getPassword()).equals("")){
                        authLoginTextField.setBorder(new LineBorder(Color.RED, 2));
                        authPasswordField.setBorder(new LineBorder(Color.RED, 2));
                        return;
                    }
                    boolean logged = CurrentSession.setSession(authLoginTextField.getText(),String.valueOf(authPasswordField.getPassword()));
                    if (logged){
                        if(CurrentSession.checkIfLogged()){
                            if(CurrentSession.checkIfAdmin()){
                                System.out.println("Admin Logged");
                            }else{
                                loggedEmail.setText(CurrentSession.getUser().getEmail());
                                loggedPhoneNumber.setText(CurrentSession.getUser().getPhoneNumber());
                                loggedUserName.setText(CurrentSession.getUser().getUsername());
                                loggedcreationDate.setText(CurrentSession.getUser().getDate());
                                loggedInPictureHolder.setImage(new javax.swing.ImageIcon(CurrentSession.getUser().getImage()));
                                acountMgmtTabs.setSelectedIndex(2);
                            }
                        }
                    }else{
                        authLoginTextField.setBorder(new LineBorder(Color.RED, 2));
                        authPasswordField.setBorder(new LineBorder(Color.RED, 2));
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
                        acountMgmtTabs.setSelectedIndex(1);
                    }});
        logOutButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!CurrentSession.checkIfLogged()){
                            JOptionPane.showMessageDialog(null, "You are not logged in", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        CurrentSession.logOut();
                        loggedEmail.setText("");
                        loggedPhoneNumber.setText("");
                        loggedUserName.setText("");
                        loggedcreationDate.setText("");
                        loggedInPictureHolder.setImage(new javax.swing.ImageIcon(getClass().getResource("/GUI/resources/black_icons/user-big.png")));
                        acountMgmtTabs.setSelectedIndex(1);
                        RequestDataPanel.updateRequestTable();
                        RequestDataPanel.model.fireTableDataChanged();
                        AchatDataPanel.updateAchatTable();
                        AchatDataPanel.model.fireTableDataChanged();
                        MainFrame.startMainFrame(AdminControlFrame.color);
                        dispose();
                    }});

        removeUserButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            LinkedList<UserPanel> toRemove = new LinkedList<>();
                            for (UserPanel userPanel : userPanels) {
                                if (userPanel.selected) {
                                    Admin.removeUserByID(userPanel.user.getId());
                                    toRemove.add(userPanel);
                                    SwingUtilities.updateComponentTreeUI(AdminControlFrame.this);
                                }
                            }
                            userPanels.removeAll(toRemove);
                            initUsers();
                        }});

        removeProductButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            LinkedList<CarItemPanel> toRemove = new LinkedList<>();
                            for (CarItemPanel productPanel : productPanels) {
                                if (productPanel.selected) {
                                    CarController.deleteCar(productPanel.car.getID());
                                    toRemove.add(productPanel);
                                }
                            }
                            productPanels.removeAll(toRemove);
                            SwingUtilities.updateComponentTreeUI(AdminControlFrame.this);
                            initCars();
                        }});

        addUserButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            AddUserFrame.startAddUserFrame(AdminControlFrame.color);
                        }});

        addProductButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            AddCarFrame.startAddProductFrame(AdminControlFrame.color);
                        }});
    
        modifyUserButton.addActionListener(e -> {
            ModifyUserFrame.startModifyUserFrame(CurrentSession.getAdmin(), AdminControlFrame.color);
        });
    
        viewHistoryButton.addActionListener(e -> {
            //CommandesHistoryFrame.startCommandesHistoryFrame(AdminControlFrame.color);
        });
    
        downloadRepportButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(); 
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Select Folder");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                //GeneratePDF.generatePDF(chooser.getSelectedFile().getAbsolutePath());
                Notification notification = new Notification(AdminControlFrame.this, Notification.Type.SUCCESS, Notification.Location.BOTTOM_CENTER, "report downloaded successfully");
                notification.showNotification();
            }else{
                Notification notification = new Notification(AdminControlFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "report download aborted");
                notification.showNotification();
            }
        });
    }                    
    
    public static void initUsers(){
        userScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        usersGrid.removeAll();
        users = UsersController.getAllUsers();
        userPanels = new LinkedList<>();
        for (int i = 0; i < users.size(); i++) {
            userPanels.add(new UserPanel(users.get(i),AdminControlFrame.color));
            usersGrid.add(userPanels.get(i));
        }

        for (int i = 0; i < 3; i++) {
            usersGrid.add(new UserPanel());
        }
        usersGrid.setLayout(new GridLayout(0,3,20,20));
    }

    public static void initCars(){
        productScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        productsGrid.removeAll();
        carList = CarController.getAllCars();
        productPanels = new LinkedList<CarItemPanel>();
        for (int i = 0; i < carList.size(); i++) {
            productPanels.add(new CarItemPanel(carList.get(i),AdminControlFrame.color));
            productsGrid.add(productPanels.get(i));
        }
        for (int i = 0; i < 3; i++) {
            productsGrid.add(new CarItemPanel());
        }
        productsGrid.setLayout(new GridLayout(0,3,20,20));
    }

    public static void connect(){
        RequestDataPanel.updateRequestTable();
        RequestDataPanel.model.fireTableDataChanged();
        AchatDataPanel.updateAchatTable();
        AchatDataPanel.model.fireTableDataChanged();
        loggedEmail.setText(CurrentSession.getAdmin().getEmail());
        loggedPhoneNumber.setText(CurrentSession.getAdmin().getPhoneNumber());
        loggedUserName.setText(CurrentSession.getAdmin().getUsername());
        loggedcreationDate.setText(CurrentSession.getAdmin().getDate());
        if (CurrentSession.getAdmin().getImage() != null) {
            loggedInPictureHolder.setImage(new javax.swing.ImageIcon(Scalr.resize(CurrentSession.getAdmin().getImage(), Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, loggedInPictureHolder.getWidth(), loggedInPictureHolder.getHeight())));
        }else{
            loggedInPictureHolder.setImage(new ImageIcon(AdminControlFrame.class.getResource("/GUI/resources/black_icons/user-big.png")));
        }
        acountMgmtTabs.setSelectedIndex(2);
    }

    public static void changeColors(Color color){
        AdminControlFrame.color = color;
        if (color != null) {
            RequestDataPanel.changeColors(color);
            AchatDataPanel.changeColors(color);
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
            SidePanel.setBackground(color);
            addUserButton.changeButtonColor(color, color);
            //changeColorButton.changeButtonColor(color.brighter(), color.darker());
            usersButton.changeButtonColor(color, color);
            modifyUserButton.changeButtonColor(color, color);
            connectButton.changeButtonColor(color, color);
            createNewAcountButton.changeButtonColor(color, color);
            settingsButton.changeButtonColor(color, color);
            removeProductButton.changeButtonColor(color, color);
            addProductButton.changeButtonColor(color, color);
            exitButton.changeButtonColor(color, color);
            homeButton.changeButtonColor(color, color);
            productsButton.changeButtonColor(color, color);
            logOutButton.changeButtonColor(color, color);
            signOutButton.changeButtonColor(color, color);
            signUpButton.changeButtonColor(color, color);
            uploadPicButton.changeButtonColor(color, color);
            removeUserButton.changeButtonColor(color, color);
            cancelSignUpButton.changeButtonColor(color, color);
            uploadPicButton.changeButtonColor(color, color);
            requestsButton.changeButtonColor(color, color);
            viewHistoryButton.changeButtonColor(color, color);
            downloadRepportButton.changeButtonColor(color, color);
            achatsButton.changeButtonColor(color, color);
            double luminescence = 0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue();
            if (luminescence < 128) {
                initIcons(false);
            }else{
                initIcons(true);
            }
            initUsers();
            initCars();
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

        usersButton.setPreferredSize(new java.awt.Dimension(73, 30));

        productsButton.setPreferredSize(new java.awt.Dimension(73, 30));

        imageLabel.setPreferredSize(new java.awt.Dimension(60, 60));

        logOutButton.setPreferredSize(new java.awt.Dimension(30, 30));

        requestsButton.setPreferredSize(new java.awt.Dimension(73, 30));

        settingsButton.setPreferredSize(new java.awt.Dimension(30, 30));

        achatsButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(SidePanel);
        SidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usersButton, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(productsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(requestsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(achatsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(productsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(requestsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(achatsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        framePanel.add(SidePanel, java.awt.BorderLayout.LINE_START);

        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton.setPreferredSize(new java.awt.Dimension(30, 30));

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titleLabel.setText("Gestion Location Voitures");

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

        uploadPictureHolder.setBackground(new java.awt.Color(0, 0, 0));

        signUpDataPanel.setBackground(new java.awt.Color(255, 255, 255));

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
                .addContainerGap()
                .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel)
                    .addComponent(retypePasswordLabel)
                    .addComponent(phoneLabel)
                    .addComponent(emailLabel)
                    .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(emailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addComponent(phoneNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reTypePasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(223, Short.MAX_VALUE))
        );
        signUpDataPanelLayout.setVerticalGroup(
            signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpDataPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signUpDataPanelLayout.createSequentialGroup()
                        .addComponent(retypePasswordLabel)
                        .addGap(18, 18, 18)
                        .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailLabel)
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(signUpDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneLabel)
                            .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(reTypePasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
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

        loginSignInLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        loginSignInLabel.setText("Login :");
        loginSignInLabel.setPreferredSize(new java.awt.Dimension(56, 30));
        loginSignInLabel.setRequestFocusEnabled(false);

        passwordSignInLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordSignInLabel.setText("Password :");
        passwordSignInLabel.setPreferredSize(new java.awt.Dimension(56, 30));
        passwordSignInLabel.setRequestFocusEnabled(false);

        authPasswordField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        authLoginTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

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
        downloadRepportButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout loggedInPanelLayout = new javax.swing.GroupLayout(loggedInPanel);
        loggedInPanel.setLayout(loggedInPanelLayout);
        loggedInPanelLayout.setHorizontalGroup(
            loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(downloadRepportButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modifyUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(loggedInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(viewHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(downloadRepportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        acountMgmtTabs.addTab("tab3", loggedInPanel);

        menuTabs.addTab("tab5", acountMgmtTabs);

        usersTablePanel.setLayout(new java.awt.BorderLayout());
        userScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout productGridLayout = new javax.swing.GroupLayout(usersGrid);
        usersGrid.setLayout(productGridLayout);
        productGridLayout.setHorizontalGroup(
            productGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
        );
        productGridLayout.setVerticalGroup(
            productGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );

        userScroll.setViewportView(usersGrid);

        usersTablePanel.add(userScroll, java.awt.BorderLayout.CENTER);

        userSpacerPanel.setPreferredSize(new java.awt.Dimension(865, 25));

        javax.swing.GroupLayout productSpacerPanelLayout = new javax.swing.GroupLayout(userSpacerPanel);
        userSpacerPanel.setLayout(productSpacerPanelLayout);
        productSpacerPanelLayout.setHorizontalGroup(
            productSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        productSpacerPanelLayout.setVerticalGroup(
            productSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        usersTablePanel.add(userSpacerPanel, java.awt.BorderLayout.PAGE_START);

        usersActionsPanel.setPreferredSize(new java.awt.Dimension(865, 50));

        removeUserButton.setPreferredSize(new java.awt.Dimension(30, 30));

        addUserButton.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Grp 19.");

        javax.swing.GroupLayout productsActionsPanelLayout = new javax.swing.GroupLayout(usersActionsPanel);
        usersActionsPanel.setLayout(productsActionsPanelLayout);
        productsActionsPanelLayout.setHorizontalGroup(
            productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productsActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 458, Short.MAX_VALUE)
                .addComponent(addUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        productsActionsPanelLayout.setVerticalGroup(
            productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productsActionsPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(productsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeUserButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addUserButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        //-------------------------------------------------

        usersTablePanel.add(usersActionsPanel, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout usersPanelLayout = new javax.swing.GroupLayout(usersPanel);
        usersPanel.setLayout(usersPanelLayout);
        usersPanelLayout.setHorizontalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(usersTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        usersPanelLayout.setVerticalGroup(
            usersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usersPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(usersTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuTabs.addTab("tab1", usersPanel);

        productTablePanel.setLayout(new java.awt.BorderLayout());

        productSpacerPanel.setPreferredSize(new java.awt.Dimension(895, 25));

        javax.swing.GroupLayout kartSpacerPanelLayout = new javax.swing.GroupLayout(productSpacerPanel);
        productSpacerPanel.setLayout(kartSpacerPanelLayout);
        kartSpacerPanelLayout.setHorizontalGroup(
            kartSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        kartSpacerPanelLayout.setVerticalGroup(
            kartSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        productTablePanel.add(productSpacerPanel, java.awt.BorderLayout.PAGE_START);

        productActionsPanel.setPreferredSize(new java.awt.Dimension(895, 50));

        addProductButton.setPreferredSize(new java.awt.Dimension(30, 30));

        removeProductButton.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Grp 19.");

        javax.swing.GroupLayout kartActionsPanelLayout = new javax.swing.GroupLayout(productActionsPanel);
        productActionsPanel.setLayout(kartActionsPanelLayout);
        kartActionsPanelLayout.setHorizontalGroup(
            kartActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kartActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 458, Short.MAX_VALUE)
                .addComponent(addProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kartActionsPanelLayout.setVerticalGroup(
            kartActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kartActionsPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(kartActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeProductButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addProductButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        productTablePanel.add(productActionsPanel, java.awt.BorderLayout.PAGE_END);

        productScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout kartGridLayout = new javax.swing.GroupLayout(productsGrid);
        productsGrid.setLayout(kartGridLayout);
        kartGridLayout.setHorizontalGroup(
            kartGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
        );
        kartGridLayout.setVerticalGroup(
            kartGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        productScroll.setViewportView(productsGrid);

        productTablePanel.add(productScroll, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout kartPanelLayout = new javax.swing.GroupLayout(productPanel);
        productPanel.setLayout(kartPanelLayout);
        kartPanelLayout.setHorizontalGroup(
            kartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productTablePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
        );
        kartPanelLayout.setVerticalGroup(
            kartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kartPanelLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(productTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuTabs.addTab("tab3", productPanel);

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
    
    public static void startAdminControlFrame(Color color) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminControlFrame().setVisible(true);
                changeColors(color);
            }
        });
    }

    private static GLVButton addUserButton;
    private static GLVButton usersButton;
    private static GLVButton modifyUserButton;
    private static GLVButton connectButton;
    private static GLVButton createNewAcountButton;
    private static GLVButton settingsButton;
    private static GLVButton removeProductButton;
    private static GLVButton addProductButton;
    private static GLVButton exitButton;
    private static GLVButton homeButton;
    private static GLVButton productsButton;
    private static GLVButton logOutButton;
    private static GLVButton signOutButton;
    private static GLVButton signUpButton;
    private static GLVButton uploadPicButton;
    private static GLVButton removeUserButton;
    private static GLVButton cancelSignUpButton;
    private static GLVButton requestsButton;
    private static GLVButton viewHistoryButton;
    private static GLVButton downloadRepportButton;
    private static GLVButton achatsButton;

    private JPanel Header;
    private static JPanel MainPanel;
    private JPanel framePanel;
    private static JPanel SidePanel;
    private JPanel productPanel;
    private JPanel productSpacerPanel;
    private JPanel productTablePanel;
    private JPanel userSpacerPanel;
    private JPanel usersTablePanel;
    private JPanel usersActionsPanel;
    private JPanel usersPanel;
    private JPanel seatchPanel;
    private JPanel signInPanel;
    private JPanel signUpDataPanel;
    private JPanel signUpPanel;
    private static JPanel productsGrid;
    private static JPanel usersGrid;
    private JPanel productActionsPanel;
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
    private static JScrollPane productScroll;
    
    private static GLVTextField loginTextField;
    private JTabbedPane menuTabs;
    private static JPasswordField passwordField;
    private static GLVTextField phoneNumberTextField;
    private static JScrollPane userScroll;
    private static JPasswordField reTypePasswordField;
    private static TextFieldAnimation searchBar;
    private JSeparator titleSeparator;
}
