package com.akihabara.market.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
// Gestiona la conexión con la base de datos MySQL.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/akihabara_db?useSSL=false&serverTimezone=UTC";  // URL de conexión a la base de datos
    private static final String USER = "userAkihabara";
    private static final String PASSWORD = "curso";
//Constructor que carga el driver JDBC de MySQL.
    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL cargado correctamente.");
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo cargar el driver MySQL: " + e.getMessage());
        }
    }
//Establece y devuelve una conexión con la base de datos.
    public Connection getConexion() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al obtener conexión: " + e.getMessage());
            return null;
        }
    }
}
