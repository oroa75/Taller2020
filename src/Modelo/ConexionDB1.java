package Modelo;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionDB1 {

    // Nombre del Driver de JDBC y URL de conexión
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    // PC = localhost puerto de conexión 3306 y nombre de base de datos = inventario
    static final String DATABASE_URL = "jdbc:mariadb://localhost:3306/clase9may";  // [root on Default schema]
    // Declarar la conexión (Connection) y la sentencias (Statement) 
    // para acceder y consultar la base de datos 
    private Connection connection;
    private Statement statement;

    // Constructor que se conecta a la base de datos , consulta, procesa resultados y los muestra 
    public ConexionDB1() {
        try {
            // load database driver class
            Class.forName(JDBC_DRIVER);
            // Se establece la conexión a la base de datos 
            connection = (Connection) DriverManager.getConnection(DATABASE_URL, "root", "root");
            // crear una Sentencia (Statement) de SQL
            statement = (Statement) connection.createStatement();
            // Se consulta la base de datos 
            ResultSet resultSet
                    = statement.executeQuery("select * from usuario");

            // Se procesan los resultados de la consulta de la base de datos 
            StringBuffer results = new StringBuffer();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++) {
                results.append(metaData.getColumnName(i) + "\t");
            }
            results.append("\n");
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    results.append(resultSet.getObject(i) + "\t");
                }
                results.append("\n");
            }
            // Se imprime por consola 
            System.out.println(results.toString());
        } // detecta los problemass interactuando con la base de datos (database)
        catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } // detecta los problemass cargando el Driver de conexión con la base de datos
        catch (ClassNotFoundException classNotFound) {
            JOptionPane.showMessageDialog(null, classNotFound.getMessage(),
                    "Driver Not Found", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } // Asegurar statement y conexión (connection) son cerradas
        finally {
            /*try {
               // statement.close();
               // connection.close();
            } // Manipular exceptions cerrando statement y conexión (connection) 
            catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(null,
                        sqlException.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);

                System.exit(1);
            }*/
        }
    }
 public void listarUsuario() {	
     
 }
 public void consultarUsuarioXCedula() {	
     
 }
    public void adicionarDatos() {	
        String name="camilo Loco";
        String correo="camilo@yahoo.com";
        try {
            // load database driver class
            Class.forName(JDBC_DRIVER);
            // Se establece la conexión a la base de datos 
            connection = (Connection) DriverManager.getConnection(DATABASE_URL, "root", "root");
            // crear una Sentencia (Statement) de SQL
            statement = (Statement) connection.createStatement();
            // Se INSERTA en la base de datos 
            boolean ok = statement.execute("INSERT INTO clase9may.usuario (`IDU`, `tipoID`, `nroCC`, nombre, correo) \n" +
"	VALUES (9, 3, '9999', '"+name+"', '"+correo+"')");
            System.out.println(" estado de insert = "+ ok );
        } catch (SQLException sqlException) {

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionDB1.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            try {
                statement.close();
                connection.close();
            } // Manipular exceptions cerrando statement y conexión (connection) 
            catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(null,
                        sqlException.getMessage(), "Database Error",
                        JOptionPane.ERROR_MESSAGE);

                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {
        ConexionDB1 solucion = new ConexionDB1();
        solucion.adicionarDatos();
        ConexionDB1 solucion2 = new ConexionDB1();
    }

}
