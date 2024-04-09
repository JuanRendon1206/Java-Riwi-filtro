package controller;

import entity.Empresa;
import model.EmpresaModel;

public class EmpresaController {
    public static String getAllString(){
        EmpresaModel objEmpresaModel = new EmpresaModel();
        String listEmpresas = "Lista de empresas \n";

        for (Object iterador : objEmpresaModel.findAll()){
            Empresa objEmpresa = (Empresa) iterador;
            listEmpresas += objEmpresa.toString() + "\n";
        }
        return listEmpresas;
    }
}
