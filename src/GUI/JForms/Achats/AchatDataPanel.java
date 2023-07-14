package GUI.JForms.Achats;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

import Controllers.RequestController;
import GUI.CurrentSession;
import GUI.Components.Buttons.GLVButton;
import Models.Request;

public class AchatDataPanel extends javax.swing.JPanel {

    public static AchatTableModel model;

    public static Color colorr;

    public AchatDataPanel(Color color) {

        initComponents();

        changeColors(color);

        if(CurrentSession.checkIfAdmin()){
            acceptAchat.setVisible(false);
            declineAchat.setVisible(false);
            deleteAchat.setVisible(true);
            model = new AchatTableModel();
        }else{
            acceptAchat.setVisible(false);
            declineAchat.setVisible(false);
            deleteAchat.setVisible(false);
            model = new AchatTableModel();
        }

        jTable1.setModel(model);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable1.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
        jTable1.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );


        deleteAchat.addActionListener(e -> {
            int[] rows = jTable1.getSelectedRows();
            if (rows.length == 0) {
                return;
            }
            for(int i=0;i<rows.length;i++){
                try {
                    RequestController.deleteRequest((int)model.getValueAt(rows[i], 0));
                } catch (Exception ec) {}
            }
            updateAchatTable();
        });

        acceptAchat.addActionListener(e -> {
            if(jTable1.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une demande à accepter");
                return;
            }
            Request request = RequestController.getRequest((int)jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            RequestController.modifyRequest(request.getId(), request.getIdUser(), request.getIdCar(), request.getStartingDate(), request.getEndingDate(), true);
            updateAchatTable();
        });

        declineAchat.addActionListener(e -> {
            if(jTable1.getSelectedRow() == -1){
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un fournisseur à rejeter");
                return;
            }
            RequestController.deleteRequest((int)jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            updateAchatTable();
        });
    }

    public static void initIcons(boolean isDark){
        if(isDark){
            acceptAchat.setIcon(new javax.swing.ImageIcon(
                AchatDataPanel.class.getResource("/GUI/resources/black_icons/plus.png")));
            deleteAchat.setIcon(new javax.swing.ImageIcon(
                AchatDataPanel.class.getResource("/GUI/resources/black_icons/bin.png")));
            declineAchat.setIcon(new javax.swing.ImageIcon(
                AchatDataPanel.class.getResource("/GUI/resources/black_icons/edit.png")));
        }else{
            acceptAchat.setIcon(new javax.swing.ImageIcon(
                AchatDataPanel.class.getResource("/GUI/resources/white_icons/plus.png")));
            deleteAchat.setIcon(new javax.swing.ImageIcon(
                AchatDataPanel.class.getResource("/GUI/resources/white_icons/bin.png")));
            declineAchat.setIcon(new javax.swing.ImageIcon(
                AchatDataPanel.class.getResource("/GUI/resources/white_icons/edit.png")));
        }
    }

    public static void changeColors(Color color) {
        colorr = color;
        deleteAchat.changeButtonColor(color, color);
        acceptAchat.changeButtonColor(color, color);
        declineAchat.changeButtonColor(color, color);
        double luminescence = 0.2126*color.getRed() + 0.7152*color.getGreen() + 0.0722*color.getBlue();
        if (luminescence < 128) {
            initIcons(false);
        }else{
            initIcons(true);
        }
    }

    public static void updateAchatTable(){
        model = new AchatTableModel();
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
            updateAchatTable();
            return;
        }
        if(CurrentSession.checkIfAdmin()){
            model = new AchatTableModel(search);
        }else{
            model = new AchatTableModel(search);
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
        acceptAchat = new GLVButton("",30,30,Color.RED,Color.WHITE);
        deleteAchat = new GLVButton("",30,30,Color.RED,Color.WHITE);
        declineAchat = new GLVButton("", 30, 30, Color.RED, Color.WHITE);

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

        deleteAchat.setPreferredSize(new java.awt.Dimension(30, 30));

        acceptAchat.setPreferredSize(new java.awt.Dimension(30, 30));

        declineAchat.setPreferredSize(new java.awt.Dimension(30, 30));

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
                .addComponent(acceptAchat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(declineAchat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteAchat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        fournisseurActionsPanelLayout.setVerticalGroup(
            fournisseurActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fournisseurActionsPanelLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(fournisseurActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteAchat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceptAchat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(declineAchat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private static GLVButton acceptAchat;
    private static GLVButton deleteAchat;
    private javax.swing.JPanel fournisseurActionsPanel;
    private static javax.swing.JPanel fournisseurPanel;
    private javax.swing.JScrollPane fournisseurScroll;
    private javax.swing.JPanel fournisseurTablePanel;
    private static javax.swing.JTable jTable1; 
    private static GLVButton declineAchat;  
    private static javax.swing.JLabel jLabel8;
}