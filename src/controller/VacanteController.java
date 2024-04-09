package controller;

import entity.Empresa;
import entity.Vacante;
import model.EmpresaModel;
import model.VacanteModel;

import javax.swing.*;
import java.util.List;

public class VacanteController {

    public static void getAll(){
        VacanteModel objVacanteModel = new VacanteModel();
        List<Object> listVacantes = objVacanteModel.findAll();

        if (listVacantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vacantes para listar", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String listVacantesText = "Lista de vacantes \n";
            for (Object iterador : listVacantes) {
                Vacante objVacante = (Vacante) iterador;
                listVacantesText += objVacante.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, listVacantesText);
        }
    }

    public static String getAllString(){
        VacanteModel objVacanteModel = new VacanteModel();
        String listVacantes = "Lista de vacantes \n";

        for (Object iterador : objVacanteModel.findAll()){
            Vacante objVacante = (Vacante) iterador;
            listVacantes += objVacante.toString() + "\n";
        }
        return listVacantes;
    }

    public static void create(){

        try {
            VacanteModel objVacanteModel = new VacanteModel();
            int empresa_id = Integer.parseInt(JOptionPane.showInputDialog(EmpresaController.getAllString() + "\n Ingrese el id de la empresa dueña de esta vacante: "));
            String titulo = JOptionPane.showInputDialog("Inserte el titulo de la vacante: ");
            String descripcion = JOptionPane.showInputDialog("Inserte la descripción de la vacante: ");
            String duracion = JOptionPane.showInputDialog("Inserte la duración de el contrato de esta vacante: ");
            String estado = "ACTIVO";
            String tecnologia = JOptionPane.showInputDialog("Inserte la tecnologia de la vacante: ");

            EmpresaModel objEmpresaModel = new EmpresaModel();
            Empresa objEmpresa = objEmpresaModel.findEmpresaById(empresa_id);

            if (objEmpresa == null){
                JOptionPane.showMessageDialog(null, "Empresa no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
            }else{

                Vacante objVacante = new Vacante();
                objVacante.setId_vacante(empresa_id);
                objVacante.setTituloVacante(titulo);
                objVacante.setDescripcionVacante(descripcion);
                objVacante.setDuracionVacante(duracion);
                objVacante.setEstadoVacante(estado);
                objVacante.setTecnologiaVacante(tecnologia);

                objVacante = (Vacante) objVacanteModel.insert(objVacante);

                JOptionPane.showMessageDialog(null, objVacante.toString());
            }
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id de la empresa debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void delete(){
        VacanteModel objVacanteModel = new VacanteModel();
        try {
            String listVacantes = getAllString();

            int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listVacantes + "\n Ingresa el id de la vacante a eliminar"));
            Vacante objVacante = objVacanteModel.findVacanteById(idDelete);
            int confirm = 1;

            if (objVacante == null){
                JOptionPane.showMessageDialog(null, "Vacante no encontrada", "Error", JOptionPane.ERROR_MESSAGE);

            }else{
                confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de borrar esta vacante? \n" +
                        "Ten en cuenta que con ella se borraran todos sus contratos. \n" + objVacante.toString());

                if (confirm == 0){
                    objVacanteModel.delete(objVacante);
                }
            }
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id de la vacante debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void update(){
        try {
            VacanteModel objVacanteModel = new VacanteModel();
            String listVacantes = getAllString();
            int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listVacantes + "\n Ingresa el id de la vacante a actualizar"));

            Vacante objVacante = objVacanteModel.findVacanteById(idUpdate);

            if (objVacante == null){
                JOptionPane.showMessageDialog(null, "Vacante no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
            }else {
                int empresa_id = Integer.parseInt(JOptionPane.showInputDialog(EmpresaController.getAllString() + "\n Ingrese el id de la nueva empresa de la vacante: ",
                        objVacante.getEmpresa_id()));
                String titulo = JOptionPane.showInputDialog("Inserte el nuevo titulo de la vacante: ",
                        objVacante.getTituloVacante());
                String descripcion = JOptionPane.showInputDialog("Inserte la nueva descripción de la vacante: ",
                        objVacante.getDescripcionVacante());
                String duracion = JOptionPane.showInputDialog("Inserte la nueva duración de el contrato de esta vacante: ",
                        objVacante.getDuracionVacante());

                String estado = "ACTIVO";

                String tecnologia = JOptionPane.showInputDialog("Inserte la nueva tecnologia de la vacante: ",
                        objVacante.getTecnologiaVacante());

                EmpresaModel objEmpresaModel = new EmpresaModel();
                Empresa objEmpresa = objEmpresaModel.findEmpresaById(empresa_id);

                if (objEmpresa == null) {
                    JOptionPane.showMessageDialog(null, "Empresa no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                objVacante.setEmpresa_id(empresa_id);
                objVacante.setTituloVacante(titulo);
                objVacante.setDescripcionVacante(descripcion);
                objVacante.setDuracionVacante(duracion);
                objVacante.setEstadoVacante(estado);
                objVacante.setTecnologiaVacante(tecnologia);

                objVacanteModel.updateObject(objVacante);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id de la vacante y de la empresa debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void findVacantesByTitulo() {
        VacanteModel objVacanteModel = new VacanteModel();

        try {
            String titulo = JOptionPane.showInputDialog("Ingrese el titulo de la vacante por el que quiere buscar vacantes: ");

            List<Object> vacantes = objVacanteModel.findVacantesByTitulo(titulo);

            if (vacantes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay vacantes con este titulo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            StringBuilder listVacantes = new StringBuilder("Lista de vacantes con " + titulo + " de titulo:\n");
            for (Object iterador : vacantes) {
                Vacante objVacante = (Vacante) iterador;
                listVacantes.append(objVacante.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, listVacantes.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR>> : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void findVacantesByTecnologia() {
        VacanteModel objVacanteModel = new VacanteModel();

        try {
            String tecnologia = JOptionPane.showInputDialog("Ingrese la tecnología de la vacante por el que quiere buscar vacantes: ");

            List<Object> vacantes = objVacanteModel.findVacantesByTecnologia(tecnologia);

            if (vacantes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay vacantes con esta tecnologia.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            StringBuilder listVacantes = new StringBuilder("Lista de vacantes con " + tecnologia + " de tecnología:\n");
            for (Object iterador : vacantes) {
                Vacante objVacante = (Vacante) iterador;
                listVacantes.append(objVacante.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, listVacantes.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR>> : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
