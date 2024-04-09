package entity;

public class Contratacion {
    private int id_contratacion;
    private int vacante_id;
    private int coder_id;
    private String fechaAplicacionContratacion;
    private String estadoContratacion;
    private double salarioContratacion;
    private Vacante objVacante;
    private Coder objCoder;

    public Contratacion() {
    }

    public Contratacion(int id_contratacion, int vacante_id, int coder_id, String fechaAplicacionContratacion, String estadoContratacion, double salarioContratacion) {
        this.id_contratacion = id_contratacion;
        this.vacante_id = vacante_id;
        this.coder_id = coder_id;
        this.fechaAplicacionContratacion = fechaAplicacionContratacion;
        this.estadoContratacion = estadoContratacion;
        this.salarioContratacion = salarioContratacion;
    }

    public int getId_contratacion() {
        return id_contratacion;
    }

    public void setId_contratacion(int id_contratacion) {
        this.id_contratacion = id_contratacion;
    }

    public int getVacante_id() {
        return vacante_id;
    }

    public void setVacante_id(int vacante_id) {
        this.vacante_id = vacante_id;
    }

    public int getCoder_id() {
        return coder_id;
    }

    public void setCoder_id(int coder_id) {
        this.coder_id = coder_id;
    }

    public String getFechaAplicacionContratacion() {
        return fechaAplicacionContratacion;
    }

    public void setFechaAplicacionContratacion(String fechaAplicacionContratacion) {
        this.fechaAplicacionContratacion = fechaAplicacionContratacion;
    }

    public String getEstadoContratacion() {
        return estadoContratacion;
    }

    public void setEstadoContratacion(String estadoContratacion) {
        this.estadoContratacion = estadoContratacion;
    }

    public double getSalarioContratacion() {
        return salarioContratacion;
    }

    public void setSalarioContratacion(double salarioContratacion) {
        this.salarioContratacion = salarioContratacion;
    }

    public Vacante getObjVacante() {
        return objVacante;
    }

    public void setObjVacante(Vacante objVacante) {
        this.objVacante = objVacante;
    }

    public Coder getObjCoder() {
        return objCoder;
    }

    public void setObjCoder(Coder objCoder) {
        this.objCoder = objCoder;
    }

    @Override
    public String toString() {
        return "Id de contratación: " + id_contratacion +
                ", Fecha de aplicación: '" + fechaAplicacionContratacion + '\'' +
                ", Estado de la contratación: '" + estadoContratacion + '\'' +
                ", Salario de el coder: " + salarioContratacion + "\n" +
                "   - Información de la vacante: " + objVacante.getTituloVacante() + objVacante.getDescripcionVacante() + objVacante.getEmpresa_id() + "\n" +
                "   - Información de el coder: " + objCoder;
    }
}
