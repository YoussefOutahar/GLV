package Models;

public class Request {
    private int id;
    private int idUser;
    private int idCar;
    private String startingDate;
    private String endingDate;
    private boolean isAccepted;

    public Request(int id, int idUser, int idCar, String startingDate, String endingDate) {
        this.id = id;
        this.idUser = idUser;
        this.idCar = idCar;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.isAccepted = false;
    }

    public Request(int id, int idUser, int idCar, String startingDate, String endingDate, boolean isAccepted) {
        this.id = id;
        this.idUser = idUser;
        this.idCar = idCar;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.isAccepted = isAccepted;
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdCar() {
        return idCar;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public void setAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    @Override
    public String toString() {
        return "Request [endingDate=" + endingDate + ", id=" + id + ", idCar=" + idCar + ", idUser=" + idUser
                + ", isAccepted=" + isAccepted + ", startingDate=" + startingDate + "]";
    }
}
