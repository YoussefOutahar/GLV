package Models;

public class Comment {
    private int id;
    private int idUser;
    private int idCar;
    private String comment;

    public Comment(int id, int idUser, int idCar, String comment) {
        this.id = id;
        this.idUser = idUser;
        this.idCar = idCar;
        this.comment = comment;
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

    public String getComment() {
        return comment;
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

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment [comment=" + comment + ", id=" + id + ", idCar=" + idCar + ", idUser=" + idUser + "]";
    }
}
