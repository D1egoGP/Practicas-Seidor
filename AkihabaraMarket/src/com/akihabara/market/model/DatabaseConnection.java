package com.akihabara.market.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Conexion
public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/akihabara_db";  
    private static final String USER = "userAkihabara";  
    private static final String PASSWORD = "curso";  
    private Connection conexion;

    // Establece la conexión a la base de datos.
    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Se ha cargado en memoria el driver de MySQL.");

            // Establecer la conexión con la base de datos
            this.conexion = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Se ha establecido con éxito la conexión.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver de MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos: " + e.getMessage());
        }
    }

    //Método que devuelve la conexión actual.
    public Connection getConexion() {
        return conexion;
    }

    //Método que cierra la conexión con la base de datos.
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Se ha cerrado la conexión con la base de datos.");
            } else {
                System.out.println("La conexión ya está cerrada o no existe.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
        }
    }
}
