package GUI.JForms.Requests;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import Controllers.RequestController;
import Controllers.CarController;
import Controllers.UsersController;
import GUI.CurrentSession;
import Models.Request;

public class RequestTableModel extends AbstractTableModel {
    
    private final Object[][] data = {};
    
    private final String[] columnNames = { "ID", "User", "Car","StartingDate","EndingDate"};
    
    private final Class[] columnClass = { Integer.class, String.class, String.class, String.class, String.class};

    private Object[][] rowData = new Object[][] {

    };

    public RequestTableModel() {
        LinkedList<Request> requests;
        if(CurrentSession.checkIfLogged()) {
            if (!CurrentSession.checkIfAdmin()) {
                requests = RequestController.getAllRequestsByUser(CurrentSession.getUser().getId());
                System.out.println(requests);
            } else {
                requests = RequestController.getAllRequests();
            }
        } else {
            requests = RequestController.getAllRequests();
        }
        rowData = new Object[requests.size()][6];
        for (int i = 0; i < rowData.length; i++) {
            for (int j = 0; j < 6; j++) {
                    switch (j) {
                        case 0:
                            rowData[i][j] = requests.get(i).getId();
                            break;
                        case 1:
                            rowData[i][j] = UsersController.getUser(requests.get(i).getIdUser()).getUsername();
                            break;
                        case 2:
                            rowData[i][j] = CarController.getCar(requests.get(i).getIdCar()).getName();
                            break;
                        case 3:
                            rowData[i][j] = requests.get(i).getStartingDate();
                            break;
                        case 4:
                            rowData[i][j] = requests.get(i).getEndingDate();
                            break;
                        case 5:
                            rowData[i][j] = requests.get(i).isAccepted();
                            break;
                        default:
                            break;
                    }
                
            }
        }
    }

    public RequestTableModel(String search) {
        LinkedList<Request> requests;
        if(CurrentSession.checkIfLogged()) {
            if (!CurrentSession.checkIfAdmin()) {
                requests = RequestController.getAllRequestsByCarOrUserName(CurrentSession.getUser().getId(),search);
            } else {
                requests = RequestController.getAllRequestsByCarOrUserName(search);
            }
        } else {
            requests = RequestController.getAllRequestsByCarOrUserName(search);
        }
        System.out.println(requests);
        rowData = new Object[requests.size()][6];
        for (int i = 0; i < rowData.length; i++) {
            for (int j = 0; j < 6; j++) {
                    switch (j) {
                        case 0:
                            rowData[i][j] = requests.get(i).getId();
                            break;
                        case 1:
                            rowData[i][j] = UsersController.getUser(requests.get(i).getIdUser()).getUsername();
                            break;
                        case 2:
                            rowData[i][j] = CarController.getCar(requests.get(i).getIdCar()).getName();
                            break;
                        case 3:
                            rowData[i][j] = requests.get(i).getStartingDate();
                            break;
                        case 4:
                            rowData[i][j] = requests.get(i).getEndingDate();
                            break;
                        case 5:
                            rowData[i][j] = requests.get(i).isAccepted();
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
