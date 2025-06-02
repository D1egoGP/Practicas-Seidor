import com.akihabara.market.model.DatabaseConnection;

public class Main {

    public static void main(String[] args) {

        // Crear una instancia de DatabaseConnection para establecer la conexión
        DatabaseConnection dbConnection = new DatabaseConnection();

        // Verificar si la conexión se ha establecido correctamente
        if (dbConnection.getConexion() != null) {
            System.out.println("Conexión establecida correctamente.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }

        // Cerrar la conexión al finalizar
        dbConnection.cerrarConexion();
    }
}
