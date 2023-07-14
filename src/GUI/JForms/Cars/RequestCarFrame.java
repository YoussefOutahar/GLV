package GUI.JForms.Cars;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.imgscalr.Scalr;

import Models.Car;
import Controllers.RequestController;
import GUI.CurrentSession;
import GUI.Components.Buttons.GLVButton;
import GUI.Components.Notifications.Notification;
import GUI.JForms.Achats.AchatDataPanel;
import GUI.JForms.Requests.RequestDataPanel;

public class RequestCarFrame extends JFrame{

    public static Color color;
    public static BufferedImage image = null;

    public RequestCarFrame(Car car) {
        initComponents();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        image = car.getImage();

        if (image != null) {
            uploadPictureHolder.setIcon(new ImageIcon(Scalr.resize(image, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, uploadPictureHolder.getWidth(), uploadPictureHolder.getHeight())));
        }else{
            uploadPictureHolder.setIcon(new ImageIcon(ModifyCarFrame.class.getResource("/GUI/resources/black_icons/car.png")));
        }

        exitButton.addActionListener(e -> {
            dispose();
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });

        uploadPictureHolder.setVerticalAlignment(SwingConstants.CENTER);
        uploadPictureHolder.setHorizontalAlignment(SwingConstants.CENTER);

        nameTextField.setText(car.getName());
        colorTextField.setText(car.getColor());
        priceTextField.setText(car.getPrice()+" MAD");

        nameTextField.setEditable(false);
        colorTextField.setEditable(false);
        priceTextField.setEditable(false);

        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int carID = car.getID();
                int userID = CurrentSession.getUser().getId();

                Date startingDate = startingDateChooser1.getDate();
                Date endingDate = endingDateChooser2.getDate();

                if(endingDate == null || startingDate == null){
                    Notification notification = new Notification(RequestCarFrame.this, Notification.Type.WARNING, Notification.Location.BOTTOM_CENTER, "Please Select a Date.");
                    notification.showNotification();
                    return;
                } 

                RequestController.addRequest(userID, carID, startingDate.toString(), endingDate.toString());
                RequestDataPanel.updateRequestTable();
                RequestDataPanel.model.fireTableDataChanged();
                AchatDataPanel.updateAchatTable();
                AchatDataPanel.model.fireTableDataChanged();
                dispose();
            }});
    }

    public static void initIcons(boolean isDark){
        if(isDark){
            exitButton.setIcon(new ImageIcon(ModifyCarFrame.class.getResource("/GUI/resources/black_icons/cross.png")));
        }else{
            exitButton.setIcon(new ImageIcon(ModifyCarFrame.class.getResource("/GUI/resources/white_icons/cross.png")));
        }
    }

    public static void changeColors(Color color){
        
        exitButton.changeButtonColor(color, color);
        cancelButton.changeButtonColor(color, color);
        addButton.changeButtonColor(color, color);
        double luminescence = 0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue();
        if (luminescence < 128) {
            initIcons(false);
        }else{
            initIcons(true);
        }
    }

    private void initComponents() {

        cancelButton = new GLVButton("Cancel",30, 30, Color.white,Color.gray);
        exitButton = new GLVButton("",30, 30, Color.WHITE,Color.gray);
        addButton = new GLVButton("Request",30, 30, Color.white,Color.gray);

        jPanel1 = new javax.swing.JPanel();
        uploadPictureHolder = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        colorTextField = new javax.swing.JTextField();
        nameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        startingDateChooser1 = new com.toedter.calendar.JDateChooser();
        endingDateChooser2 = new com.toedter.calendar.JDateChooser();

        setUndecorated(true);
        getRootPane().setBorder(new LineBorder(Color.BLACK,2,true));
        setIconImage(new ImageIcon(getClass().getResource("/GUI/resources/black_icons/app-logo.png")).getImage());

        exitButton.setPreferredSize(new java.awt.Dimension(30, 30));

        uploadPictureHolder.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setText("Product Name :");

        jLabel5.setText("Color :");

        jLabel3.setText("Price :");

        priceTextField.setPreferredSize(new java.awt.Dimension(112, 22));

        colorTextField.setPreferredSize(new java.awt.Dimension(112, 22));

        jLabel2.setText("Starting Date");

        jLabel4.setText("Ending Date");

        cancelButton.setText("Cancel");
        cancelButton.setPreferredSize(new java.awt.Dimension(69, 23));

        addButton.setPreferredSize(new java.awt.Dimension(69, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uploadPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(colorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(priceTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                    .addComponent(startingDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(endingDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(92, 92, 92))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(colorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startingDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(uploadPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endingDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    public static void startRequestCarFrame(Car car,Color color) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RequestCarFrame(car).setVisible(true);
                changeColors(color);
            }
        });
    }
    
    private static GLVButton addButton;
    private static GLVButton cancelButton;
    private javax.swing.JTextField colorTextField;
    private com.toedter.calendar.JDateChooser endingDateChooser2;
    private static GLVButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField priceTextField;
    private com.toedter.calendar.JDateChooser startingDateChooser1;
    private javax.swing.JLabel uploadPictureHolder;
}
