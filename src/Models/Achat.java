package Models;

public class Achat {
    private int id;
    private int idUser;
    private int idCar;
    private String startingDate;
    private String endingDate;

    public Achat(int id, int idUser, int idCar, String startingDate, String endingDate) {
        this.id = id;
        this.idUser = idUser;
        this.idCar = idCar;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
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

    @Override
    public String toString() {
        return "Achat [endingDate=" + endingDate + ", id=" + id + ", idCar=" + idCar + ", idUser=" + idUser
                + ", startingDate=" + startingDate + "]";
    }
}
