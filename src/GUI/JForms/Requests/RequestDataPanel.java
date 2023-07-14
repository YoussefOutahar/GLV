package GUI.JForms.Requests;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

import Controllers.AchatController;
import Controllers.CarController;
import Controllers.RequestController;
import GUI.CurrentSession;
import GUI.Components.Buttons.GLVButton;
import GUI.JForms.Achats.AchatDataPanel;
import Models.Car;
import Models.Request;

public class RequestDataPanel extends javax.swing.JPanel {

    public static RequestTableModel model;

    public static Color colorr;

    public RequestDataPanel(Color color) {

        initComponents();

        changeColors(color);

        if(CurrentSession.checkIfAdmin()){
            model = new RequestTableModel();
            acceptRequest.setVisible(true);
            modifyRequest.setVisible(false);
            deleteRequest.setVisible(true);
        }else{
            model = new RequestTableModel();
            acceptRequest.setVisible(false);
            modifyRequest.setVisible(true);
            deleteRequest.setVisible(true);
        }

        jTable1.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );

        deleteRequest.addActionListener(e -> {
            int[] rows = jTable1.getSelectedRows();
            if (rows.length == 0) {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une demande à refuser");
                return;
            }
            for(int i=0;i<rows.length;i++){
                try {
                    RequestController.deleteRequest((int)model.getValueAt(rows[i], 0));
                } catch (Exception ec) {}
            }
            updateRequestTable();
        });

        acceptRequest.addActionListener(e -> {
            if(jTable1.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une demande à accepter");
                return;
            }
            Request request = RequestController.getRequest((int)jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            RequestController.modifyRequest(request.getId(), request.getIdUser(), request.getIdCar(), request.getStartingDate(), request.getEndingDate(), true);
            if(request.isAccepted() == false) {
                AchatController.addAchat(request.getIdUser(), request.getIdCar(), request.getStartingDate(), request.getEndingDate());
                RequestController.deleteRequest(request.getId());
            }
            RequestDataPanel.updateRequestTable();
            RequestDataPanel.model.fireTableDataChanged();
            AchatDataPanel.updateAchatTable();
            AchatDataPanel.model.fireTableDataChanged();
            updateRequestTable();
        });

        modifyRequest.addActionListener(e -> {
            if(jTable1.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une demande à modifier");
                return;
            } 
            int requestID = (int)jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            Request request = RequestController.getRequest(requestID);
            Car car = CarController.getCar(request.getIdCar());

            ModifyRequestFrame.startRequestCarFrame(car,requestID, color);
        });
    }

    public static void initIcons(boolean isDark){
        if(isDark){
            acceptRequest.setIcon(new javax.swing.ImageIcon(
                RequestDataPanel.class.getResource("/GUI/resources/black_icons/checkmark.png")));
            deleteRequest.setIcon(new javax.swing.ImageIcon(
                RequestDataPanel.class.getResource("/GUI/resources/black_icons/bin.png")));
            modifyRequest.setIcon(new javax.swing.ImageIcon(
                RequestDataPanel.class.getResource("/GUI/resources/black_icons/edit.png")));
        }else{
            acceptRequest.setIcon(new javax.swing.ImageIcon(
                RequestDataPanel.class.getResource("/GUI/resources/white_icons/checkmark.png")));
            deleteRequest.setIcon(new javax.swing.ImageIcon(
                RequestDataPanel.class.getResource("/GUI/resources/white_icons/bin.png")));
            modifyRequest.setIcon(new javax.swing.ImageIcon(
                RequestDataPanel.class.getResource("/GUI/resources/white_icons/edit.png")));
        }
    }

    public static void changeColors(Color color) {
        colorr = color;
        deleteRequest.changeButtonColor(color, color);
        acceptRequest.changeButtonColor(color, color);
        modifyRequest.changeButtonColor(color, color);
        double luminescence = 0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue();
        if (luminescence < 128) {
            initIcons(false);
        }else{
            initIcons(true);
        }
    }

    public static void updateRequestTable(){
        model = new RequestTableModel();
        jTable1.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        jTable1.repaint();
    }

    public static void searchFunction(String search){
        if(search.equals("")){
            updateRequestTable();
            return;
        }
        if(CurrentSession.checkIfAdmin()){
            model = new RequestTableModel(search);
        }else{
            model = new RequestTableModel(search);
        }
        jTable1.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
    }

    private void initComponents() {

        fournisseurPanel = new javax.swing.JPanel();
        fournisseurTablePanel = new javax.swing.JPanel();
        fournisseurScroll = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        FournisseurSpacerPanel = new javax.swing.JPanel();
        fournisseurActionsPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        acceptRequest = new GLVButton("",30,30,Color.RED,Color.WHITE);
        deleteRequest = new GLVButton("",30,30,Color.RED,Color.WHITE);
        modifyRequest = new GLVButton("", 30, 30, Color.RED, Color.WHITE);

        fournisseurTablePanel.setLayout(new java.awt.BorderLayout());

        fournisseurScroll.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        fournisseurScroll.setViewportView(jTable1);

        fournisseurTablePanel.add(fournisseurScroll, java.awt.BorderLayout.CENTER);

        FournisseurSpacerPanel.setPreferredSize(new java.awt.Dimension(865, 25));

        javax.swing.GroupLayout FournisseurSpacerPanelLayout = new javax.swing.GroupLayout(FournisseurSpacerPanel);
        FournisseurSpacerPanel.setLayout(FournisseurSpacerPanelLayout);
        FournisseurSpacerPanelLayout.setHorizontalGroup(
            FournisseurSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
        );
        FournisseurSpacerPanelLayout.setVerticalGroup(
            FournisseurSpacerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        fournisseurTablePanel.add(FournisseurSpacerPanel, java.awt.BorderLayout.PAGE_START);

        fournisseurActionsPanel.setPreferredSize(new java.awt.Dimension(865, 50));

        deleteRequest.setPreferredSize(new java.awt.Dimension(30, 30));

        acceptRequest.setPreferredSize(new java.awt.Dimension(30, 30));

        modifyRequest.setPreferredSize(new java.awt.Dimension(30, 30));

        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Copyright GLV. All rights reserved.");

        javax.swing.GroupLayout fournisseurActionsPanelLayout = new javax.swing.GroupLayout(fournisseurActionsPanel);
        fournisseurActionsPanel.setLayout(fournisseurActionsPanelLayout);
        fournisseurActionsPanelLayout.setHorizontalGroup(
            fournisseurActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fournisseurActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 452, Short.MAX_VALUE)
                .addComponent(acceptRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        fournisseurActionsPanelLayout.setVerticalGroup(
            fournisseurActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fournisseurActionsPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(fournisseurActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteRequest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceptRequest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyRequest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        fournisseurTablePanel.add(fournisseurActionsPanel, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout fournisseurPanelLayout = new javax.swing.GroupLayout(fournisseurPanel);
        fournisseurPanel.setLayout(fournisseurPanelLayout);
        fournisseurPanelLayout.setHorizontalGroup(
            fournisseurPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fournisseurTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        fournisseurPanelLayout.setVerticalGroup(
            fournisseurPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fournisseurPanelLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(fournisseurTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(fournisseurPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(fournisseurPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JPanel FournisseurSpacerPanel;
    private static GLVButton acceptRequest;
    private static GLVButton deleteRequest;
    private javax.swing.JPanel fournisseurActionsPanel;
    private static javax.swing.JPanel fournisseurPanel;
    private javax.swing.JScrollPane fournisseurScroll;
    private javax.swing.JPanel fournisseurTablePanel;
    private static javax.swing.JTable jTable1; 
    private static GLVButton modifyRequest;  
    private static javax.swing.JLabel jLabel8;
}