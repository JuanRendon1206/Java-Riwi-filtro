package entity;

public class Coder {
    private int id_coder;
    private String nombreCoder;
    private String apellidosCoder;
    private String documentoCoder;
    private int cohorteCoder;
    private String cvCoder;
    private String clanCoder;

    public Coder() {
    }

    public Coder(int id_coder, String nombreCoder, String apellidosCoder, String documentoCoder, int cohorteCoder, String cvCoder, String clanCoder) {
        this.id_coder = id_coder;
        this.nombreCoder = nombreCoder;
        this.apellidosCoder = apellidosCoder;
        this.documentoCoder = documentoCoder;
        this.cohorteCoder = cohorteCoder;
        this.cvCoder = cvCoder;
        this.clanCoder = clanCoder;
    }

    public int getId_coder() {
        return id_coder;
    }

    public void setId_coder(int id_coder) {
        this.id_coder = id_coder;
    }

    public String getNombreCoder() {
        return nombreCoder;
    }

    public void setNombreCoder(String nombreCoder) {
        this.nombreCoder = nombreCoder;
    }

    public String getApellidosCoder() {
        return apellidosCoder;
    }

    public void setApellidosCoder(String apellidosCoder) {
        this.apellidosCoder = apellidosCoder;
    }

    public String getDocumentoCoder() {
        return documentoCoder;
    }

    public void setDocumentoCoder(String documentoCoder) {
        this.documentoCoder = documentoCoder;
    }

    public int getCohorteCoder() {
        return cohorteCoder;
    }

    public void setCohorteCoder(int cohorteCoder) {
        this.cohorteCoder = cohorteCoder;
    }

    public String getCvCoder() {
        return cvCoder;
    }

    public void setCvCoder(String cvCoder) {
        this.cvCoder = cvCoder;
    }

    public String getClanCoder() {
        return clanCoder;
    }

    public void setClanCoder(String clanCoder) {
        this.clanCoder = clanCoder;
    }

    @Override
    public String toString() {
        return "Id de el coder: " + id_coder +
                ", Nombre: '" + nombreCoder + '\'' +
                ", Apellidos: '" + apellidosCoder + '\'' +
                ", Documento de identidad: '" + documentoCoder + '\'' +
                ", Cohorte: " + cohorteCoder + "\'" +
                ", Clan: " + clanCoder + "\n" +
                "CV: '" + cvCoder;
    }
}
