import controller.CoderController;
import controller.VacanteController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String option = "";
        String option1 = "";
        String option2 = "";
        String option3 = "";

        do {
            option = JOptionPane.showInputDialog("""
                    MENU
                    
                    1. Menu de coders.
                    2. Menu de vacantes.
                    3. Menu de contrataciones.
                    4. Salir.
                    
                    Escoge una opción:
                    """);

            switch (option){
                case "1":
                    do {
                        option1 = JOptionPane.showInputDialog("""
                                MENU DE CODERS
                                
                                1. Añadir coder.
                                2. Listar coders existentes.
                                3. Buscar coders por cohorte.
                                4. Buscar coders por clan.
                                5. Buscar coders por tecnología.
                                6. Actualizar un coder.
                                7. Eliminar coder.
                                8. Salir.
                                
                                Escoge una opción:
                                """);

                        switch (option1){
                            case "1":
                                CoderController.create();
                                break;
                            case "2":
                                CoderController.getAll();
                                break;
                            case "3":
                                CoderController.findCodersByCohorte();
                                break;
                            case "4":
                                CoderController.findCodersByClan();
                                break;
                            case "5":
                                CoderController.findCodersByTecnologia();
                                break;
                            case "6":
                                CoderController.update();
                                break;
                            case "7":
                                CoderController.delete();
                                break;
                        }

                    }while (!option1.equals("8"));
                    break;

                case "2":
                    do {
                        option2 = JOptionPane.showInputDialog("""
                                MENU DE VACANTES
                                
                                1. Añadir vacante.
                                2. Listar vacantes existentes.
                                3. Buscar vacantes por título.
                                4. Buscar vacantes por tecnología.
                                5. Actualizar una vacante.
                                6. Eliminar vacante.
                                7. Salir.
                                
                                Escoge una opción:
                                """);

                        switch (option2){
                            case "1":
                                VacanteController.create();
                                break;
                            case "2":
                                VacanteController.getAll();
                                break;
                            case "3":
                                VacanteController.findVacantesByTitulo();
                                break;
                            case "4":
                                VacanteController.findVacantesByTecnologia();
                                break;
                            case "5":
                                VacanteController.update();
                                break;
                            case "6":
                                VacanteController.delete();
                                break;
                        }

                    }while (!option2.equals("7"));
                    break;

                case "3":
                    do {
                        option3 = JOptionPane.showInputDialog("""
                                MENU DE CONTRATACIONES
                                
                                1. Añadir una contratación.
                                2. Listar contrataciones realizadas existentes.
                                3. Actualizar contrato.
                                4. Eliminar contrato.
                                5. Salir.
                                
                                Escoge una opción:
                                """);

                        switch (option3){
                            case "1":
                                break;
                            case "2":
                                break;
                            case "3":
                                break;
                            case "4":
                                break;
                        }

                    }while (!option3.equals("5"));
                    break;
            }
        }while (!option.equals("4"));
    }
}