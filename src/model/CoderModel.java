package model;
import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD{

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoderModel = (Coder) obj;

        try {
            String sql = "INSERT INTO coder (nombre, apellidos, documento, cohorte, cv, clan) VALUES( ?, ?, ?, ?, ?, ?);";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objCoderModel.getNombreCoder());
            objPrepare.setString(2, objCoderModel.getApellidosCoder());
            objPrepare.setString(3, objCoderModel.getDocumentoCoder());
            objPrepare.setInt(4, objCoderModel.getCohorteCoder());
            objPrepare.setString(5, objCoderModel.getCvCoder());
            objPrepare.setString(6, objCoderModel.getClanCoder());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objCoderModel.setId_coder(objResult.getInt(1));
                JOptionPane.showMessageDialog(null, "Coder agregado correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return objCoderModel;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listCoders = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM coder;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Coder objCoder = new Coder();

                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombreCoder(objResult.getString("nombre"));
                objCoder.setApellidosCoder(objResult.getString("apellidos"));
                objCoder.setDocumentoCoder(objResult.getString("documento"));
                objCoder.setCohorteCoder(objResult.getInt("cohorte"));
                objCoder.setCvCoder(objResult.getString("cv"));
                objCoder.setClanCoder(objResult.getString("clan"));

                listCoders.add(objCoder);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listCoders;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE coder SET nombre = ?, apellidos = ?, documento = ?, cohorte = ?, cv = ?, clan = ? WHERE id_coder = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objCoder.getNombreCoder());
            objPrepare.setString(2, objCoder.getApellidosCoder());
            objPrepare.setString(3, objCoder.getDocumentoCoder());
            objPrepare.setInt(4, objCoder.getCohorteCoder());
            objPrepare.setString(5, objCoder.getCvCoder());
            objPrepare.setString(6, objCoder.getClanCoder());
            objPrepare.setInt(7, objCoder.getId_coder());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0){
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "El coder se actualizo correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return isUpdate;
    }
    @Override
    public boolean delete(Object obj) {

        Coder objCoder = (Coder) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDelete = false;

        try {
            String sql = "DELETE FROM coder WHERE id_coder = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objCoder.getId_coder());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null, "El coder se elimino correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());

        }

        ConfigDB.closeConnection();
        return isDelete;
    }

    public Coder findCoderById(int coder_id){
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = null;

        try {
            String sql = "SELECT * FROM coder WHERE id_coder = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, coder_id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objCoder = new Coder();
                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombreCoder(objResult.getString("nombre"));
                objCoder.setApellidosCoder(objResult.getString("apellidos"));
                objCoder.setDocumentoCoder(objResult.getString("documento"));
                objCoder.setCohorteCoder(objResult.getInt("cohorte"));
                objCoder.setCvCoder(objResult.getString("cv"));
                objCoder.setClanCoder(objResult.getString("clan"));
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return objCoder;
    }

    public List<Object> findCodersByCohorte(int coders_cohorte) {

        List<Object> listCoders = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM coder WHERE cohorte = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, coders_cohorte);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                Coder objCoder = new Coder();

                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombreCoder(objResult.getString("nombre"));
                objCoder.setApellidosCoder(objResult.getString("apellidos"));
                objCoder.setDocumentoCoder(objResult.getString("documento"));
                objCoder.setCohorteCoder(objResult.getInt("cohorte"));
                objCoder.setCvCoder(objResult.getString("cv"));
                objCoder.setClanCoder(objResult.getString("clan"));

                listCoders.add(objCoder);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listCoders;
    }


    public List<Object> findCodersByClan(String coders_clan) {

        List<Object> listCoders = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM coder WHERE clan = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, coders_clan);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){

                Coder objCoder = new Coder();

                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombreCoder(objResult.getString("nombre"));
                objCoder.setApellidosCoder(objResult.getString("apellidos"));
                objCoder.setDocumentoCoder(objResult.getString("documento"));
                objCoder.setCohorteCoder(objResult.getInt("cohorte"));
                objCoder.setCvCoder(objResult.getString("cv"));
                objCoder.setClanCoder(objResult.getString("clan"));

                listCoders.add(objCoder);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listCoders;
    }

    public  List<Coder> findCodersByTecnologia(String coders_tecnologia){

        List<Coder> listCoders = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM coder WHERE cv LIKE ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+coders_tecnologia+"%");
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){

                Coder objCoder = new Coder();

                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombreCoder(objResult.getString("nombre"));
                objCoder.setApellidosCoder(objResult.getString("apellidos"));
                objCoder.setDocumentoCoder(objResult.getString("documento"));
                objCoder.setCohorteCoder(objResult.getInt("cohorte"));
                objCoder.setCvCoder(objResult.getString("cv"));
                objCoder.setClanCoder(objResult.getString("clan"));

                listCoders.add(objCoder);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoders;
    }

    public Coder findCoderByDocument(int document_coder){
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = null;

        try {
            String sql = "SELECT * FROM coder WHERE documento = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, document_coder);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objCoder = new Coder();

                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombreCoder(objResult.getString("nombre"));
                objCoder.setApellidosCoder(objResult.getString("apellidos"));
                objCoder.setDocumentoCoder(objResult.getString("documento"));
                objCoder.setCohorteCoder(objResult.getInt("cohorte"));
                objCoder.setCvCoder(objResult.getString("cv"));
                objCoder.setClanCoder(objResult.getString("clan"));
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return objCoder;
    }
}
