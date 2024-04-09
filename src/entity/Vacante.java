package entity;

public class Vacante {
    private int id_vacante;
    private int empresa_id;
    private String tituloVacante;
    private String descripcionVacante;
    private String duracionVacante;
    private String estadoVacante;
    private String tecnologiaVacante;
    private Empresa objEmpresa;

    public Vacante() {
    }

    public Vacante(int id_vacante, int empresa_id, String tituloVacante, String descripcionVacante, String duracionVacante, String estadoVacante, String tecnologiaVacante) {
        this.id_vacante = id_vacante;
        this.empresa_id = empresa_id;
        this.tituloVacante = tituloVacante;
        this.descripcionVacante = descripcionVacante;
        this.duracionVacante = duracionVacante;
        this.estadoVacante = estadoVacante;
        this.tecnologiaVacante = tecnologiaVacante;
    }

    public int getId_vacante() {
        return id_vacante;
    }

    public void setId_vacante(int id_vacante) {
        this.id_vacante = id_vacante;
    }

    public int getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(int empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public String getDescripcionVacante() {
        return descripcionVacante;
    }

    public void setDescripcionVacante(String descripcionVacante) {
        this.descripcionVacante = descripcionVacante;
    }

    public String getDuracionVacante() {
        return duracionVacante;
    }

    public void setDuracionVacante(String duracionVacante) {
        this.duracionVacante = duracionVacante;
    }

    public String getEstadoVacante() {
        return estadoVacante;
    }

    public void setEstadoVacante(String estadoVacante) {
        this.estadoVacante = estadoVacante;
    }

    public String getTecnologiaVacante() {
        return tecnologiaVacante;
    }

    public void setTecnologiaVacante(String tecnologiaVacante) {
        this.tecnologiaVacante = tecnologiaVacante;
    }

    public Empresa getObjEmpresa() {
        return objEmpresa;
    }

    public void setObjEmpresa(Empresa objEmpresa) {
        this.objEmpresa = objEmpresa;
    }

    @Override
    public String toString() {
        return "Id de la vacante: " + id_vacante +
                ", Titulo: '" + tituloVacante + '\'' +
                ", Descripción: '" + descripcionVacante + '\'' +
                ", Duración: '" + duracionVacante + '\'' +
                ", Estado: '" + estadoVacante + '\'' +
                ", Tecnología: " + tecnologiaVacante + "\n" +
                "   - Información de la empresa" + objEmpresa;
    }
}
