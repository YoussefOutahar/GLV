package GUI.JForms.Users;


import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import Models.Accounts.User;
import GUI.Components.ImageAvatar;
import GUI.Components.PanelRound;
import GUI.Components.Buttons.GLVButton;

import org.imgscalr.Scalr;

public class UserPanel extends PanelRound {

    public Boolean selected = false;
    public User user;
    public Color color;

    public UserPanel(){
        initComponents();
        setRoundBottomLeft(100);
        setRoundBottomRight(100);
        setRoundTopLeft(100);
        setRoundTopRight(100);
        userImage.setVisible(false);
        userName.setVisible(false);
        userEmail.setVisible(false);
        userPhoneNumber.setVisible(false);
        userPassword.setVisible(false);
        modifyUserButton.setVisible(false);
        viewDetailsButton.setVisible(false);
    }

    public UserPanel(User user,Color color) {
        initComponents();

        changeColors(color);

        setRoundBottomLeft(100);
        setRoundBottomRight(100);
        setRoundTopLeft(100);
        setRoundTopRight(100);

        this.user = user;

        this.userName.setText(user.getUsername());
        this.userPassword.setText(user.getPassword());
        this.userEmail.setText(user.getEmail());
        this.userPhoneNumber.setText(user.getPhoneNumber());

        userImage.setGradientColor1(color);
        userImage.setGradientColor2(color);
        
        if (user.getImage() == null) {
            this.userImage.setImage(new ImageIcon(getClass().getResource("/GUI/resources/black_icons/user-big.png")));
            //this.userImage.setIcon();
        }else{
            this.userImage.setImage(new ImageIcon(Scalr.resize(user.getImage(), Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 160, 160)));
        }

        userName.setVerticalAlignment(SwingConstants.CENTER);
        userName.setHorizontalAlignment(SwingConstants.CENTER);
        userPassword.setVerticalAlignment(SwingConstants.CENTER);
        userPassword.setHorizontalAlignment(SwingConstants.CENTER);
        
        userEmail.setVerticalAlignment(SwingConstants.CENTER);
        userEmail.setHorizontalAlignment(SwingConstants.CENTER);

        userPhoneNumber.setVerticalAlignment(SwingConstants.CENTER);
        userPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);

        setMaximumSize(getPreferredSize());

        addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                selected = !selected;
                if(selected) {
                    setBorder(BorderFactory.createLoweredBevelBorder());
                } else {
                    setBorder(BorderFactory.createRaisedBevelBorder());
                }
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
            }});

        setBorder(BorderFactory.createRaisedBevelBorder());

        // userImage.setVerticalAlignment(SwingConstants.CENTER);
        // userImage.setHorizontalAlignment(SwingConstants.CENTER);

        modifyUserButton.addActionListener(e -> {
            ModifyUserFrame.startModifyUserFrame(user, color);
        });

        viewDetailsButton.setVisible(false);
    }

    public static void initIcons(boolean isDark){
        if(isDark){
            modifyUserButton.setIcon(new ImageIcon(UserPanel.class.getResource("/GUI/resources/black_icons/edit.png")));
            viewDetailsButton.setIcon(new ImageIcon(UserPanel.class.getResource("/GUI/resources/black_icons/icons8_info_16px_1.png")));
        }else{
            modifyUserButton.setIcon(new ImageIcon(UserPanel.class.getResource("/GUI/resources/white_icons/edit.png")));
            viewDetailsButton.setIcon(new ImageIcon(UserPanel.class.getResource("/GUI/resources/white_icons/icons8_info_16px_1.png")));
        }
    }

    public void changeColors(Color color){
        this.color = color;
        modifyUserButton.changeButtonColor(color, color);
        viewDetailsButton.changeButtonColor(color, color);
        userImage.setGradientColor1(color);
        userImage.setGradientColor2(color);
        double luminescence = 0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue();
        if (luminescence < 128) {
            initIcons(false);
        }else{
            initIcons(true);
        }
    }
    
    private void initComponents() {

        userImage = new ImageAvatar();
        userName = new javax.swing.JLabel();
        userPassword = new javax.swing.JLabel();
        userEmail = new javax.swing.JLabel();
        userPhoneNumber = new javax.swing.JLabel();
        modifyUserButton = new GLVButton("", 30, 30, Color.RED, Color.GRAY);
        viewDetailsButton = new GLVButton("", 30, 30, Color.RED, Color.GRAY);

        modifyUserButton.setMaximumSize(new java.awt.Dimension(30, 30));
        modifyUserButton.setMinimumSize(new java.awt.Dimension(30, 30));
        modifyUserButton.setPreferredSize(new java.awt.Dimension(30, 30));

        viewDetailsButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(userName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modifyUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(userImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modifyUserButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewDetailsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }       
    
    private javax.swing.JLabel userEmail;
    private ImageAvatar userImage;
    private javax.swing.JLabel userName;
    private javax.swing.JLabel userPassword;
    private javax.swing.JLabel userPhoneNumber;
    private static GLVButton modifyUserButton;
    private static GLVButton viewDetailsButton;
}
