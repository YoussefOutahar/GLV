package GUI.JForms.Cars;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.imgscalr.Scalr;

import Models.Car;
import GUI.CurrentSession;
import GUI.Components.Buttons.GLVButton;

public class CarItemPanel extends JPanel{

    public boolean selected = false;
    public Car car;
    public static Color color;
    public Color bgColor = getBackground();

    public CarItemPanel(){
        initComponents();
        productImage.setVisible(false);
        productPrice.setVisible(false);
        viewDetailsButton.setVisible(false);
        modifyButton.setVisible(false);
    }
    
    public CarItemPanel(Car car,Color color) {

        this.car = car;
        
        initComponents();
        
        productName.setText(car.getName());
        productName.setVerticalAlignment(SwingConstants.CENTER);
        changeColors(color);

        setBorder(BorderFactory.createRaisedBevelBorder());

        if(!CurrentSession.checkIfLogged()){
            modifyButton.setVisible(false);
            viewDetailsButton.setVisible(false);
        }
        if (!CurrentSession.checkIfAdmin()) {
            modifyButton.setVisible(false);
            viewDetailsButton.setVisible(true);
        }else {
            modifyButton.setVisible(true);
            viewDetailsButton.setVisible(false);
        }

        addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                selected = !selected;
                if(selected) {
                    Color c = getBackground().darker().darker();
                    setBackground(c);
                    setBorder(BorderFactory.createLoweredBevelBorder());
                } else {
                    setBackground(bgColor);
                    setBorder(BorderFactory.createRaisedBevelBorder());
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }});

        if (car.getImage() == null) {
            productImage.setIcon(new ImageIcon(CarItemPanel.class.getResource("/GUI/resources/black_icons/car.png")));
        }else{
            productImage.setIcon(new ImageIcon(Scalr.resize(car.getImage(), Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, 160, 160)));
        }

        productImage.setVerticalAlignment(SwingConstants.CENTER);
        productImage.setHorizontalAlignment(SwingConstants.CENTER);

        productPrice.setVerticalAlignment(SwingConstants.CENTER);
        productPrice.setHorizontalAlignment(SwingConstants.CENTER);

        modifyButton.addActionListener(e -> {
            ModifyCarFrame.startModifyProductFrame(this.car,color);
        });

        
        productPrice.setText(car.getPrice() + " MAD");
        productPrice.setHorizontalAlignment(SwingConstants.RIGHT);

        viewDetailsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CurrentSession.checkIfLogged()) {
                    RequestCarFrame.startRequestCarFrame(car,color);
                }else{
                    JOptionPane.showMessageDialog(null, "You Are Not Logged in.");
                }
            }});
    }

    public static void initIcons(boolean isDark){
        if(isDark){
            viewDetailsButton.setIcon(new ImageIcon(CarItemPanel.class.getResource("/GUI/resources/black_icons/icons8_info_16px_1.png")));
            modifyButton.setIcon(new ImageIcon(CarItemPanel.class.getResource("/GUI/resources/black_icons/edit.png")));
        }else{
            viewDetailsButton.setIcon(new ImageIcon(CarItemPanel.class.getResource("/GUI/resources/white_icons/icons8_info_16px_1.png")));
            modifyButton.setIcon(new ImageIcon(CarItemPanel.class.getResource("/GUI/resources/white_icons/edit.png")));
        }
    }
    
    public static void changeColors(Color color){
        CarItemPanel.color = color;
        viewDetailsButton.changeButtonColor(color, color);
        modifyButton.changeButtonColor(color, color);
        double luminescence = 0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue();
        if (luminescence < 128) {
            initIcons(false);
        }else{
            initIcons(true);
        }
    }

    private void initComponents() {
        viewDetailsButton = new GLVButton("", 30, 30, Color.RED, Color.gray);
        modifyButton = new GLVButton("", 30, 30, Color.RED, Color.gray);

        productImage = new javax.swing.JLabel();
        productPrice = new javax.swing.JLabel();
        productName = new javax.swing.JLabel();

        modifyButton.setMaximumSize(new java.awt.Dimension(30, 30));
        modifyButton.setMinimumSize(new java.awt.Dimension(30, 30));
        modifyButton.setPreferredSize(new java.awt.Dimension(30, 30));

        viewDetailsButton.setMaximumSize(new java.awt.Dimension(30, 30));
        viewDetailsButton.setMinimumSize(new java.awt.Dimension(30, 30));
        viewDetailsButton.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(viewDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(productImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productImage, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productName)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }                      

    private static javax.swing.JLabel productImage;
    private javax.swing.JLabel productPrice;
    private static GLVButton viewDetailsButton;
    private static GLVButton modifyButton;
    private javax.swing.JLabel productName;
}
