//package conexiondb1;
import java.sql.*;
import javax.swing.JOptionPane;
public class ConexionDB1 {
    // Nombre del Driver de JDBC y URL de conexión
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    // PC = localhost puerto de conexión 3306 y nombre de base de datos = inventario
    static final String DATABASE_URL = "jdbc:mariadb://localhost:3306/inventario";  // [root on Default schema]
    // Declarar la conexión (Connection) y la sentencias (Statement) 
    // para acceder y consultar la base de datos 
    private Connection connection;
    private Statement statement;
    private String salida;
    // Constructor que se conecta a la base de datos , consulta, procesa resultados y los muestra 
    public ConexionDB1() {
        try {
            // load database driver class
            Class.forName(JDBC_DRIVER);
            // Se establece la conexión a la base de datos 
            connection = (Connection) DriverManager.getConnection(DATABASE_URL, "root", "olroa123");
            // crear una Sentencia (Statement) de SQL
            statement = (Statement) connection.createStatement();
            // Se consulta la base de datos 
            ResultSet resultSet
                    = statement.executeQuery("select * from empleado");

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
                    "Error de base de datos ", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } // detecta los problemass cargando el Driver de conexión con la base de datos
        catch (ClassNotFoundException classNotFound) {
            JOptionPane.showMessageDialog(null, classNotFound.getMessage(),
                    "No encuentra el Driver ", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } // Asegurar statement y conexión (connection) son cerradas
        finally {
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
    }

}