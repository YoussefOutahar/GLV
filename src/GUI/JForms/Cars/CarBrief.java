package GUI.JForms.Cars;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Models.Car;

public class CarBrief extends JPanel{

    public CarBrief(Car car) {
        initComponents();
        productName.setText(car.getName());
        productName.setVerticalAlignment(SwingConstants.CENTER);
        productName.setHorizontalAlignment(SwingConstants.CENTER);
    }                        
    private void initComponents() {

        productName = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productName, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private javax.swing.JLabel productName;
}
