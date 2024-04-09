package entity;

public class Empresa {
    private int id_empresa;
    private String nombreEmpresa;
    private String sectorEmpresa;
    private String ubicacionEmpresa;
    private String contactoEmpresa;

    public Empresa() {
    }

    public Empresa(int id_empresa, String nombreEmpresa, String sectorEmpresa, String ubicacionEmpresa, String contactoEmpresa) {
        this.id_empresa = id_empresa;
        this.nombreEmpresa = nombreEmpresa;
        this.sectorEmpresa = sectorEmpresa;
        this.ubicacionEmpresa = ubicacionEmpresa;
        this.contactoEmpresa = contactoEmpresa;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getSectorEmpresa() {
        return sectorEmpresa;
    }

    public void setSectorEmpresa(String sectorEmpresa) {
        this.sectorEmpresa = sectorEmpresa;
    }

    public String getUbicacionEmpresa() {
        return ubicacionEmpresa;
    }

    public void setUbicacionEmpresa(String ubicacionEmpresa) {
        this.ubicacionEmpresa = ubicacionEmpresa;
    }

    public String getContactoEmpresa() {
        return contactoEmpresa;
    }

    public void setContactoEmpresa(String contactoEmpresa) {
        this.contactoEmpresa = contactoEmpresa;
    }

    @Override
    public String toString() {
        return "Id de la empresa: " + id_empresa +
                ", Nombre: '" + nombreEmpresa + '\'' +
                ", Sector: '" + sectorEmpresa + '\'' +
                ", Ubicación: '" + ubicacionEmpresa + '\'' +
                ", Contacto: '" + contactoEmpresa;
    }
}
