package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    public static Connection objConnection = null;

    public static Connection openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/riwi_java";
            String user = "root";
            String password = "juanjosef12";

            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Me conecte exitosamente!!");


        }catch (ClassNotFoundException error){

            System.out.println("ERROR >> Driver no instalado " + error.getMessage());
        }catch (SQLException error){
            System.out.println("ERROR >> error al conectar con la base de datos " + error.getMessage());
        }

        return objConnection;
    }

    public static void closeConnection(){
        try {

            if (objConnection != null){
                objConnection.close();
                System.out.println("Se finalizo la conexion con exito");
            }

        }catch (SQLException e){

            System.out.println("Error: " + e.getMessage());
        }

    }
}
