package model;

import database.ConfigDB;
import database.UPDATE_OBJECT;
import entity.Coder;
import entity.Contratacion;
import entity.Empresa;
import entity.Vacante;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel  implements UPDATE_OBJECT {

    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;

        try {
            String sql = "INSERT INTO contratacion (vacante_id, coder_id, fecha_aplicacion, estado, salario) VALUES( ?, ?, ?, ?, ?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);


            objPrepare.setInt(1, objContratacion.getVacante_id());
            objPrepare.setInt(2, objContratacion.getCoder_id());
            objPrepare.setString(3, objContratacion.getFechaAplicacionContratacion());
            objPrepare.setString(4, objContratacion.getEstadoContratacion());
            objPrepare.setDouble(5, objContratacion.getSalarioContratacion());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();
            while (objResult.next()){
                objContratacion.setId_contratacion(objResult.getInt(1));
                JOptionPane.showMessageDialog(null, "Contratación creada correctamente");
            }
        }catch (Exception error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return findContratacionById(objContratacion.getId_contratacion());
    }

    @Override
    public List<Object> findAll() {
        List<Object> listContratos = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM contratacion " +
                    "INNER JOIN coder ON coder.id_coder = contratacion.coder_id " +
                    "INNER JOIN vacante ON vacante.id_vacante = contratacion.vacante_id" +
                    "INNER JOIN empresa ON empresa.id_empresa = vacante.empresa_id;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Contratacion objContratacion = new Contratacion();
                Coder objCoder = new Coder();
                Vacante objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId_contratacion(objResult.getInt("contratacion.id_contratacion"));
                objContratacion.setVacante_id(objResult.getInt("contratacion.vacante_id"));
                objContratacion.setCoder_id(objResult.getInt("contratacion.coder_id"));
                objContratacion.setEstadoContratacion(objResult.getString("contratacion.estado"));
                objContratacion.setSalarioContratacion(objResult.getDouble("contratacion.salario"));

                objCoder.setId_coder(objResult.getInt("coder.id_coder"));
                objCoder.setNombreCoder(objResult.getString("coder.nombre"));
                objCoder.setApellidosCoder(objResult.getString("coder.apellidos"));
                objCoder.setDocumentoCoder(objResult.getString("coder.documento"));
                objCoder.setCohorteCoder(objResult.getInt("coder.cohorte"));
                objCoder.setCvCoder(objResult.getString("coder.cv"));
                objCoder.setClanCoder(objResult.getString("coder.clan"));

                objVacante.setId_vacante(objResult.getInt("vacante.id_vacante"));
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

                objContratacion.setObjCoder(objCoder);
                objVacante.setObjEmpresa(objEmpresa);
                objContratacion.setObjVacante(objVacante);

                listContratos.add(objContratacion);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listContratos;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public Object updateObject(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE contratacion SET salario = ? WHERE id_contratacion = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setDouble(1,objContratacion.getSalarioContratacion());
            objPrepare.setInt(2, objContratacion.getId_contratacion());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "La contratacion se actualizo correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return findContratacionById(objContratacion.getId_contratacion());
    }

    @Override
    public boolean delete(Object obj) {
        Contratacion objContratacion = (Contratacion) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM contratacion WHERE id_contratacion = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objContratacion.getId_contratacion());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "La contratacion se elimino correctamente");
            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }

        ConfigDB.closeConnection();
        return isDelete;
    }

    public Contratacion findContratacionById(int contratacion_id){
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = null;

        try {
            String sql = "SELECT * FROM contratacion " +
                         "INNER JOIN vacante ON vacante.id_vacante = contratacion.vacante_id " +
                         "INNER JOIN coder ON coder.id_coder = contratacion.coder_id " +
                         "INNER JOIN empresa ON empresa.id_empresa = vacante.empresa_id" +
                         "WHERE contratacion.id_contratacion = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, contratacion_id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objContratacion = new Contratacion();

                Coder objCoder = new Coder();
                Vacante objVacante = new Vacante();
                Empresa objEmpresa = new Empresa();

                objContratacion.setId_contratacion(objResult.getInt("contratacion.id_contratacion"));
                objContratacion.setVacante_id(objResult.getInt("contratacion.vacante_id"));
                objContratacion.setCoder_id(objResult.getInt("contratacion.coder_id"));
                objContratacion.setEstadoContratacion(objResult.getString("contratacion.estado"));
                objContratacion.setSalarioContratacion(objResult.getDouble("contratacion.salario"));

                objCoder.setId_coder(objResult.getInt("coder.id_coder"));
                objCoder.setNombreCoder(objResult.getString("coder.nombre"));
                objCoder.setApellidosCoder(objResult.getString("coder.apellidos"));
                objCoder.setDocumentoCoder(objResult.getString("coder.documento"));
                objCoder.setCohorteCoder(objResult.getInt("coder.cohorte"));
                objCoder.setCvCoder(objResult.getString("coder.cv"));
                objCoder.setClanCoder(objResult.getString("coder.clan"));

                objVacante.setId_vacante(objResult.getInt("vacante.id_vacante"));
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

                objContratacion.setObjCoder(objCoder);
                objVacante.setObjEmpresa(objEmpresa);
                objContratacion.setObjVacante(objVacante);

            }

        }catch (Exception error){
            System.out.println(error.getMessage());
        }
        ConfigDB.closeConnection();
        return objContratacion;
    }
}
