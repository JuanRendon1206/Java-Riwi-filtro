package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;
import java.util.List;

public class CoderController{

    public static void getAll(){
        CoderModel objCoderModel = new CoderModel();
        List<Object> listCoders = objCoderModel.findAll();

        if (listCoders.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay coders para listar", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String listCodersText = "Lista de coders \n";
            for (Object iterador : listCoders) {
                Coder objCoder = (Coder) iterador;
                listCodersText += objCoder.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, listCodersText);
        }
    }

    public static String getAllString() {
        CoderModel objCoderModel = new CoderModel();
        String listCoders = "Lista de coders \n";

        for (Object iterador : objCoderModel.findAll()) {
            Coder objCoder = (Coder) iterador;
            listCoders += objCoder.toString() + "\n";
        }
        return listCoders;
    }

    public static void create(){
        CoderModel objCoderModel = new CoderModel();

        String nombre = JOptionPane.showInputDialog("Inserte el nombre de el coder: ");
        String apellido = JOptionPane.showInputDialog("Ingrese los apellidos de el coder: ");
        String documento = JOptionPane.showInputDialog("Ingrese el documento de identidad de el coder.\nEste debe tener exactamente 10 dígitos: ");
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cohorte de el coder: "));
        String cv = JOptionPane.showInputDialog("Ingrese el CV de el coder: ");
        String clan = JOptionPane.showInputDialog("Ingrese el clan de el coder: ");

        if (documento.length() != 10) {
            JOptionPane.showMessageDialog(null, "El documento de identidad debe tener exactamente 10 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Coder coderExistente = objCoderModel.findCoderByDocument(Integer.parseInt(documento));
            if (coderExistente != null) {
                JOptionPane.showMessageDialog(null, "El documento ya existe en un coder.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Coder objCoder = new Coder();
            objCoder.setNombreCoder(nombre);
            objCoder.setApellidosCoder(apellido);
            objCoder.setDocumentoCoder(documento);
            objCoder.setCohorteCoder(cohorte);
            objCoder.setCvCoder(cv);
            objCoder.setClanCoder(clan);

            objCoderModel.insert(objCoder);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El documento de identidad debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void delete(){
        CoderModel objCoderModel = new CoderModel();
        String listCoders = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCoders + "\n Ingresa el id de el coder a eliminar"));
        Coder objCoder = objCoderModel.findCoderById(idDelete);
        int confirm = 1;

        if (objCoder == null){
            JOptionPane.showMessageDialog(null, "Coder no encontrado", "Error", JOptionPane.ERROR_MESSAGE );
        }else{
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro de borrar este coder?. Con el se borraran todos sus contratos. \n"
                    + objCoder.toString());

            if (confirm == 0){
                objCoderModel.delete(objCoder);
            }
        }
    }

    public static void update(){

        CoderModel objCoderModel = new CoderModel();

        try {
            String listCoders = getAllString();

            int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCoders + "\nIngresa el id de el coder a actualizar"));
            Coder objCoder = objCoderModel.findCoderById(idUpdate);

            if (objCoder == null){
                JOptionPane.showMessageDialog(null, "Coder no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String nombre = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre de el coder :",
                        objCoder.getNombreCoder());
                String apellido = JOptionPane.showInputDialog(null, "Ingrese los nuevos apellidos de el coder: ",
                        objCoder.getApellidosCoder());
                String documento = JOptionPane.showInputDialog(null, "Ingrese el nuevo documento de identidad de el coder\nEste debe tener exactamente 10 dígitos: ",
                        objCoder.getDocumentoCoder());
                int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cohorte de el coder: ",
                        objCoder.getCohorteCoder()));
                String cv = JOptionPane.showInputDialog("Ingrese el nuevo CV de el coder: ",
                        objCoder.getCvCoder());
                String clan = JOptionPane.showInputDialog("Ingrese el nuevo clan de el coder: ",
                        objCoder.getClanCoder());

                if (documento.length() != 10) {
                    JOptionPane.showMessageDialog(null, "El documento de identidad debe tener exactamente 10 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Coder coderExistente = objCoderModel.findCoderByDocument(Integer.parseInt(documento));
                    if (coderExistente != null && coderExistente.getId_coder() != objCoder.getId_coder()) {
                        JOptionPane.showMessageDialog(null, "El documento ya existe en otro coder.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    objCoder.setNombreCoder(nombre);
                    objCoder.setApellidosCoder(apellido);
                    objCoder.setDocumentoCoder(documento);
                    objCoder.setCohorteCoder(cohorte);
                    objCoder.setCvCoder(cv);
                    objCoder.setClanCoder(clan);

                    objCoderModel.update(objCoder);

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El documento de identidad debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El id de el coder debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void findCodersByCohorte() {

        CoderModel objCoderModel = new CoderModel();

        try {
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cohorte por el que quiere buscar coders: "));

            List<Object> coders = objCoderModel.findCodersByCohorte(cohorte);

            if (coders.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay coders con esta cohorte.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            StringBuilder listCoders = new StringBuilder("Lista de coders con la cohorte: " + cohorte + ":\n");
            for (Object iterador : coders) {
                Coder objCoder = (Coder) iterador;
                listCoders.append(objCoder.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, listCoders.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR>> : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void findCodersByClan() {

        CoderModel objCoderModel = new CoderModel();

        try {
            String clan = JOptionPane.showInputDialog("Ingrese el nombre de el clan por el que quiere buscar coders: ");

            List<Object> coders = objCoderModel.findCodersByClan(clan);

            if (coders.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay coders de este clan.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            StringBuilder listCoders = new StringBuilder("Lista de coders con el clan: " + clan + ":\n");
            for (Object iterador : coders) {
                Coder objCoder = (Coder) iterador;
                listCoders.append(objCoder.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, listCoders.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR>> : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void findCodersByTecnologia() {

        CoderModel objCoderModel = new CoderModel();

        try {
            String tecnologia = JOptionPane.showInputDialog("Ingrese la tecnología por el que quiere buscar coders: ");

            List<Coder> coders = objCoderModel.findCodersByTecnologia(tecnologia);

            if (coders.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay coders para esta tecnología.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            StringBuilder listCoders = new StringBuilder("Lista de coders con esta tecnología: " + tecnologia + ":\n");
            for (Object iterador : coders) {
                Coder objCoder = (Coder) iterador;
                listCoders.append(objCoder.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, listCoders.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR>> : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
