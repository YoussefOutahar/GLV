package GUI.JForms.Cars;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controllers.CarController;
import GUI.Components.GLVTextField;
import GUI.Components.Buttons.GLVButton;
import GUI.JForms.AdminControlFrame;

public class AddCarFrame extends JFrame {

    BufferedImage image = null;

    public AddCarFrame() {
        initComponents();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        nameTextField.setLabelText("Product Name");
        priceTextField.setLabelText("Price");
        colorTextField.setLabelText("Color");

        uploadPictureHolder.setVerticalAlignment(SwingConstants.CENTER);
        uploadPictureHolder.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });

        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
        uploadPicButton.addActionListener(new ActionListener(){
            public ImageIcon resize(String imgPath) {
                ImageIcon path = new ImageIcon(imgPath);
                Image img = path.getImage();
                Image newImg = img.getScaledInstance(uploadPictureHolder.getWidth() * 2, uploadPictureHolder.getHeight() * 2,
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
                    uploadPictureHolder.setIcon(resize(path));
                    try {
                        image = ImageIO.read(selFile);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    pack();
                }
            }});

            addButton.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String name = nameTextField.getText();
                    String price = priceTextField.getText();
                    //String description = descriptionTextArea.getText();
                    //String Fournisseur = fournisseurComboBox.getSelectedItem().toString();
                    String color = colorTextField.getText();
                    
                    try {
                        CarController.addCar(name, color, Integer.parseInt(price), image);
                    } catch (Exception e) {
                        showMessageDialog(null, "Please Provide Valid Data");
                    }
                    dispose();
                    AdminControlFrame.initCars();
                }
            });
    }

    private static void changeColors(Color color){
        nameTextField.setLineColor(color);
        priceTextField.setLineColor(color);
        colorTextField.setLineColor(color);
        fournisseurComboBox.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}
            
            @Override
            public void mousePressed(MouseEvent e) {}
            
            @Override
            public void mouseReleased(MouseEvent e) {}
            
            @Override
            public void mouseEntered(MouseEvent e) {
                fournisseurComboBox.setBorder(new LineBorder(color,1));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                fournisseurComboBox.setBorder(new LineBorder(Color.GRAY,1));
            }});
        descriptionTextArea.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}
            
            @Override
            public void mousePressed(MouseEvent e) {}
            
            @Override
            public void mouseReleased(MouseEvent e) {}
            
            @Override
            public void mouseEntered(MouseEvent e) {
                descriptionTextArea.setBorder(new LineBorder(color,1));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                descriptionTextArea.setBorder(new LineBorder(Color.GRAY,1));
            }});
        addButton.changeButtonColor(color, color);
        exitButton.changeButtonColor(color, color);
        uploadPicButton.changeButtonColor(color, color);
        cancelButton.changeButtonColor(color, color);
        double luminescence = 0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue();
        if (luminescence < 128) {
            initIcons(false);
        }else{
            initIcons(true);
        }
    }

    public static void initIcons(boolean isDark){
        if (isDark) {
            uploadPicButton.setIcon(new javax.swing.ImageIcon(AddCarFrame.class.getResource("/GUI/resources/black_icons/upload.png")));
            uploadPictureHolder.setIcon(new ImageIcon(AddCarFrame.class.getResource("/GUI/resources/black_icons/car.png")));
            exitButton.setIcon(new javax.swing.ImageIcon(AddCarFrame.class.getResource("/GUI/resources/black_icons/cross.png")));
        } else {
            uploadPicButton.setIcon(new javax.swing.ImageIcon(AddCarFrame.class.getResource("/GUI/resources/white_icons/upload.png")));
            uploadPictureHolder.setIcon(new ImageIcon(AddCarFrame.class.getResource("/GUI/resources/white_icons/car.png")));
            exitButton.setIcon(new javax.swing.ImageIcon(AddCarFrame.class.getResource("/GUI/resources/white_icons/cross.png")));
        }
    }
    
    private void initComponents() {

        signUpPanel = new javax.swing.JPanel();
        addButton = new GLVButton("Add",30, 30, Color.white,Color.gray);
        uploadPictureHolder = new javax.swing.JLabel();
        dataPanel = new javax.swing.JPanel();
        nameTextField = new GLVTextField();
        uploadPicButton = new GLVButton("",30, 30, Color.white,Color.gray);
        fournisseurComboBox = new JComboBox<String>();
        priceTextField = new GLVTextField();
        descriptionTextArea = new javax.swing.JTextArea();
        cancelButton = new GLVButton("Cancel",30, 30, Color.white,Color.gray);
        exitButton = new GLVButton("",30, 30, Color.white,Color.gray);
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        colorTextField = new GLVTextField();

        setUndecorated(true);
        getRootPane().setBorder(new LineBorder(Color.BLACK,2,true));
        setIconImage(new ImageIcon(getClass().getResource("/GUI/resources/black_icons/app-logo.png")).getImage());

        addButton.setPreferredSize(new java.awt.Dimension(69, 30));

        uploadPictureHolder.setBackground(new java.awt.Color(0, 0, 0));

        uploadPicButton.setPreferredSize(new java.awt.Dimension(30, 30));

        priceTextField.setPreferredSize(new java.awt.Dimension(112, 22));

        jLabel1.setText("Product Name :");

        jLabel3.setText("Price :");

        colorTextField.setPreferredSize(new java.awt.Dimension(112, 22));

        jLabel5.setText("Color :");

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataPanelLayout.createSequentialGroup()
                        .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(dataPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(colorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dataPanelLayout.createSequentialGroup()
                        .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(dataPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dataPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(251, Short.MAX_VALUE))))
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uploadPicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(colorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dataPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dataPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        cancelButton.setText("Cancel");
        cancelButton.setPreferredSize(new java.awt.Dimension(69, 23));

        exitButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout signUpPanelLayout = new javax.swing.GroupLayout(signUpPanel);
        signUpPanel.setLayout(signUpPanelLayout);
        signUpPanelLayout.setHorizontalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpPanelLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(uploadPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        signUpPanelLayout.setVerticalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uploadPictureHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 890, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(signUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(signUpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }

    public static void startAddProductFrame(Color color) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCarFrame().setVisible(true);
                changeColors(color);
            }
        });
    }

    private static GLVButton addButton;
    private static GLVButton cancelButton;
    private javax.swing.JPanel dataPanel;
    private static javax.swing.JTextArea descriptionTextArea;
    private static GLVButton exitButton;
    private static JComboBox<String> fournisseurComboBox;
    private static GLVTextField nameTextField;
    private static GLVTextField colorTextField;
    private static GLVTextField priceTextField;
    private javax.swing.JPanel signUpPanel;
    private static GLVButton uploadPicButton;
    private static javax.swing.JLabel uploadPictureHolder; 
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel5;
}

