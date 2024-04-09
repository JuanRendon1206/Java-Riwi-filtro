package model;

import database.ConfigDB;
import entity.Empresa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel{

    public List<Object> findAll() {
        List<Object> listEmpresas = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM empresa;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Empresa objEmpresa = new Empresa();

                objEmpresa.setId_empresa(objResult.getInt("id_empresa"));
                objEmpresa.setNombreEmpresa(objResult.getString("nombre"));
                objEmpresa.setSectorEmpresa(objResult.getString("sector"));
                objEmpresa.setUbicacionEmpresa(objResult.getString("ubicacion"));
                objEmpresa.setContactoEmpresa(objResult.getString("contacto"));

                listEmpresas.add(objEmpresa);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return listEmpresas;
    }

    public Empresa findEmpresaById(int empresa_id){
        Connection objConnection = ConfigDB.openConnection();
        Empresa objEmpresa = null;

        try {
            String sql = "SELECT * FROM empresa WHERE id_empresa = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, empresa_id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objEmpresa = new Empresa();
                objEmpresa.setId_empresa(objResult.getInt("id_empresa"));
                objEmpresa.setNombreEmpresa(objResult.getString("nombre"));
                objEmpresa.setSectorEmpresa(objResult.getString("sector"));
                objEmpresa.setUbicacionEmpresa(objResult.getString("ubicacion"));
                objEmpresa.setContactoEmpresa(objResult.getString("contacto"));
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return objEmpresa;
    }
}
