package GUI.JForms.Achats;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import Controllers.AchatController;
import Controllers.CarController;
import Controllers.UsersController;
import GUI.CurrentSession;
import Models.Achat;

public class AchatTableModel extends AbstractTableModel {
    
    private final Object[][] data = {};
    
    private final String[] columnNames = { "ID", "User", "Car","StartingDate","EndingDate"};
    
    private final Class[] columnClass = { Integer.class, String.class, String.class, String.class, String.class};

    private Object[][] rowData = new Object[][] {

    };

    public AchatTableModel() {
        LinkedList<Achat> achats;
        if(CurrentSession.checkIfLogged()) {
            if (!CurrentSession.checkIfAdmin()) {
                achats = AchatController.getAllAchatsByUser(CurrentSession.getUser().getId());
            } else {
                achats = AchatController.getAllAchats();
            }
        } else {
            achats = AchatController.getAllAchats();
        }
        rowData = new Object[achats.size()][6];
        for (int i = 0; i < rowData.length; i++) {
            for (int j = 0; j < 6; j++) {
                    switch (j) {
                        case 0:
                            rowData[i][j] = achats.get(i).getId();
                            break;
                        case 1:
                            rowData[i][j] = UsersController.getUser(achats.get(i).getIdUser()).getUsername();
                            break;
                        case 2:
                            rowData[i][j] = CarController.getCar(achats.get(i).getIdCar()).getName();
                            break;
                        case 3:
                            rowData[i][j] = achats.get(i).getStartingDate();
                            break;
                        case 4:
                            rowData[i][j] = achats.get(i).getEndingDate();
                            break;
                        default:
                            break;
                    }
                
            }
        }
    }

    public AchatTableModel(String search) {
        LinkedList<Achat> achats;
        if(CurrentSession.checkIfLogged()) {
            if (!CurrentSession.checkIfAdmin()) {
                achats = AchatController.getAllAchatsByCarOrUserName(CurrentSession.getUser().getId(),search);
            } else {
                achats = AchatController.getAllAchatsByCarOrUserName(search);
            }
        } else {
            achats = AchatController.getAllAchatsByCarOrUserName(search);
        }
        rowData = new Object[achats.size()][6];
        for (int i = 0; i < rowData.length; i++) {
            for (int j = 0; j < 6; j++) {
                    switch (j) {
                        case 0:
                            rowData[i][j] = achats.get(i).getId();
                            break;
                        case 1:
                            rowData[i][j] = UsersController.getUser(achats.get(i).getIdUser()).getUsername();
                            break;
                        case 2:
                            rowData[i][j] = CarController.getCar(achats.get(i).getIdCar()).getName();
                            break;
                        case 3:
                            rowData[i][j] = achats.get(i).getStartingDate();
                            break;
                        case 4:
                            rowData[i][j] = achats.get(i).getEndingDate();
                            break;
                        default:
                            break;
                    }
                
            }
        }
    }

    @Override
    public int getRowCount() {
        return rowData.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowData[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        rowData[rowIndex][columnIndex] = aValue;
    }
}
