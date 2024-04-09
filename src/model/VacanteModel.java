package model;
import database.ConfigDB;
import database.UPDATE_OBJECT;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements UPDATE_OBJECT {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;

        try {
            String sql = "INSERT INTO vacante (empresa_id, titulo, descripcion, duracion, estado, tecnologia) VALUES( ?, ?, ?, ?, ?, ?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objVacante.getEmpresa_id());
            objPrepare.setString(2, objVacante.getTituloVacante());
            objPrepare.setString(3, objVacante.getDescripcionVacante());
            objPrepare.setString(4, objVacante.getDuracionVacante());
            objPrepare.setString(5, objVacante.getEstadoVacante());
            objPrepare.setString(6, objVacante.getTecnologiaVacante());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objVacante.setId_vacante(objResult.getInt(1));
                JOptionPane.showMessageDialog(null, "Vacante insertada correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return findVacanteById(objVacante.getId_vacante());
    }

    @Override
    public List<Object> findAll() {

        List<Object> listVacantes = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM vacante INNER JOIN empresa ON empresa.id_empresa = vacante.empresa_id ORDER BY vacante.id_vacante ASC;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vacante objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();

                objVacante.setId_vacante(objResult.getInt("vacante.id_vacante"));
                objVacante.setEmpresa_id(objResult.getInt("vacante.empresa_id"));
                objVacante.setTituloVacante(objResult.getString("vacante.titulo"));
                objVacante.setDescripcionVacante(objResult.getString("vacante.descripcion"));
                objVacante.setDuracionVacante(objResult.getString("vacante.duracion"));
                objVacante.setEstadoVacante(objResult.getString("vacante.estado"));
                objVacante.setTecnologiaVacante(objResult.getString("vacante.tecnologia"));

                objEmpresa.setId_empresa(objResult.getInt("empresa.id_empresa"));
                objEmpresa.setNombreEmpresa(objResult.getString("empresa.nombre"));
                objEmpresa.setSectorEmpresa(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacionEmpresa(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContactoEmpresa(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacantes.add(objVacante);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listVacantes;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public Object updateObject(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE vacante SET empresa_id = ?, titulo = ?, descripcion = ?, duracion = ?, estado = ?, tecnologia = ? WHERE id_vacante = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objVacante.getEmpresa_id());
            objPrepare.setString(2, objVacante.getTituloVacante());
            objPrepare.setString(3, objVacante.getDescripcionVacante());
            objPrepare.setString(4, objVacante.getDuracionVacante());
            objPrepare.setString(5, objVacante.getEstadoVacante());
            objPrepare.setString(6, objVacante.getTecnologiaVacante());
            objPrepare.setInt(7, objVacante.getId_vacante());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "La vacante se actualizo correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return findVacanteById(objVacante.getId_vacante());
    }

    @Override
    public boolean delete(Object obj) {

        Vacante objVacante = (Vacante) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM vacante WHERE id_vacante = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objVacante.getId_vacante());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "La vacante se elimino correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return isDelete;
    }



    public Vacante findVacanteById(int vacante_id){
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = null;

        try {
            String sql = "SELECT * FROM vacante INNER JOIN empresa ON empresa.id_empresa = vacante.empresa_id WHERE vacante.id_vacante = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, vacante_id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();

                objVacante.setId_vacante(objResult.getInt("vacante.id_vacante"));
                objVacante.setEmpresa_id(objResult.getInt("vacante.empresa_id"));
                objVacante.setTituloVacante(objResult.getString("vacante.titulo"));
                objVacante.setDescripcionVacante(objResult.getString("vacante.descripcion"));
                objVacante.setDuracionVacante(objResult.getString("vacante.duracion"));
                objVacante.setEstadoVacante(objResult.getString("vacante.estado"));
                objVacante.setTecnologiaVacante(objResult.getString("vacante.tecnologia"));

                objEmpresa.setId_empresa(objResult.getInt("empresa.id_empresa"));
                objEmpresa.setNombreEmpresa(objResult.getString("empresa.nombre"));
                objEmpresa.setSectorEmpresa(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacionEmpresa(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContactoEmpresa(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return objVacante;
    }

    public List<Object> findVacantesByTitulo(String titulo_vacante) {

        List<Object> listVacantes = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT vacante.*, empresa.* FROM vacante " +
                    "INNER JOIN empresa ON empresa.id_empresa = vacante.empresa_id " +
                    "WHERE vacante.titulo = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, titulo_vacante);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vacante objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();

                objVacante.setId_vacante(objResult.getInt("vacante.id_vacante"));
                objVacante.setEmpresa_id(objResult.getInt("vacante.empresa_id"));
                objVacante.setTituloVacante(objResult.getString("vacante.titulo"));
                objVacante.setDescripcionVacante(objResult.getString("vacante.descripcion"));
                objVacante.setDuracionVacante(objResult.getString("vacante.duracion"));
                objVacante.setEstadoVacante(objResult.getString("vacante.estado"));
                objVacante.setTecnologiaVacante(objResult.getString("vacante.tecnologia"));

                objEmpresa.setId_empresa(objResult.getInt("empresa.id_empresa"));
                objEmpresa.setNombreEmpresa(objResult.getString("empresa.nombre"));
                objEmpresa.setSectorEmpresa(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacionEmpresa(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContactoEmpresa(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacantes.add(objVacante);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listVacantes;
    }

    public List<Object> findVacantesByTecnologia(String tecnologia_vacante) {

        List<Object> listVacantes = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT vacante.*, empresa.* FROM vacante " +
                    "INNER JOIN empresa ON empresa.id_empresa = vacante.empresa_id " +
                    "WHERE vacante.tecnologia = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, tecnologia_vacante);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vacante objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();

                objVacante.setId_vacante(objResult.getInt("vacante.id_vacante"));
                objVacante.setEmpresa_id(objResult.getInt("vacante.empresa_id"));
                objVacante.setTituloVacante(objResult.getString("vacante.titulo"));
                objVacante.setDescripcionVacante(objResult.getString("vacante.descripcion"));
                objVacante.setDuracionVacante(objResult.getString("vacante.duracion"));
                objVacante.setEstadoVacante(objResult.getString("vacante.estado"));
                objVacante.setTecnologiaVacante(objResult.getString("vacante.tecnologia"));

                objEmpresa.setId_empresa(objResult.getInt("empresa.id_empresa"));
                objEmpresa.setNombreEmpresa(objResult.getString("empresa.nombre"));
                objEmpresa.setSectorEmpresa(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacionEmpresa(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContactoEmpresa(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacantes.add(objVacante);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listVacantes;
    }

    public List<Object> findVacantesActivas() {

        List<Object> listVacantes = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT vacante.*, empresa.* FROM vacante " +
                    "INNER JOIN empresa ON empresa.id_empresa = vacante.empresa_id " +
                    "WHERE vacante.estado = 'ACTIVO';";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Vacante objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();

                objVacante.setId_vacante(objResult.getInt("vacante.id_vacante"));
                objVacante.setEmpresa_id(objResult.getInt("vacante.empresa_id"));
                objVacante.setTituloVacante(objResult.getString("vacante.titulo"));
                objVacante.setDescripcionVacante(objResult.getString("vacante.descripcion"));
                objVacante.setDuracionVacante(objResult.getString("vacante.duracion"));
                objVacante.setEstadoVacante(objResult.getString("vacante.estado"));
                objVacante.setTecnologiaVacante(objResult.getString("vacante.tecnologia"));

                objEmpresa.setId_empresa(objResult.getInt("empresa.id_empresa"));
                objEmpresa.setNombreEmpresa(objResult.getString("empresa.nombre"));
                objEmpresa.setSectorEmpresa(objResult.getString("empresa.sector"));
                objEmpresa.setUbicacionEmpresa(objResult.getString("empresa.ubicacion"));
                objEmpresa.setContactoEmpresa(objResult.getString("empresa.contacto"));

                objVacante.setObjEmpresa(objEmpresa);

                listVacantes.add(objVacante);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listVacantes;
    }

    public Vacante changeEstadoToInactivo(int vacante_id){
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "UPDATE vacante SET estado = 'INACTIVO' WHERE id_vacante = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, vacante_id);

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                JOptionPane.showMessageDialog(null, "La vacante paso a ser INACTIVA");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return findVacanteById(vacante_id);
    }

    public Vacante changeEstadoToActivo(int vacante_id){
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "UPDATE vacante SET estado = 'ACTIVO' WHERE id_vacante = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, vacante_id);

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                JOptionPane.showMessageDialog(null, "La vacante paso a ser ACTIVA");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return findVacanteById(vacante_id);
    }

}
