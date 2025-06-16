package com.akihabara.market.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.akihabara.market.model.ProductoOtaku;

public class ProductoDAO {

    private DatabaseConnection db;
//Realizar operaciones CRUD
    public ProductoDAO() {
        db = new DatabaseConnection();
    }
//Constructor que inicializa la conexión con la base de datos.
    
    //Inserta un nuevo producto en la base de datos.
    public void agregarProducto(ProductoOtaku producto) {
        String sql = "INSERT INTO productos (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";

        try (Connection conn = db.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCategoria());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());

            stmt.executeUpdate();
            System.out.println("El producto fue agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }
//Obtiene un producto por su ID.
    public ProductoOtaku obtenerProductoPorId(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        ProductoOtaku producto = null;

        try (Connection conn = db.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = new ProductoOtaku(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("categoria"),
                            rs.getDouble("precio"),
                            rs.getInt("stock")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el producto por ID: " + e.getMessage());
        }

        return producto;
    }
//Devuelve una lista con todos los productos de la base de datos.
    public List<ProductoOtaku> obtenerTodosLosProductos() {
        String sql = "SELECT * FROM productos";
        List<ProductoOtaku> lista = new ArrayList<>();

        try (Connection conn = db.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProductoOtaku producto = new ProductoOtaku(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                );
                lista.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener todos los productos: " + e.getMessage());
        }

        return lista;
    }
//Actualiza los datos de un producto existente basado en su nombre.
    public boolean actualizarProducto(ProductoOtaku producto) {
        String sql = "UPDATE productos SET categoria = ?, precio = ?, stock = ? WHERE nombre = ?";
        boolean actualizado = false;

        try (Connection conn = db.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getCategoria());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getStock());
            stmt.setString(4, producto.getNombre());

            int filas = stmt.executeUpdate();
            actualizado = filas > 0;
            System.out.println(actualizado ? "El producto fue actualizado correctamente." : "El producto no ha sido encontrado.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }

        return actualizado;
    }
//Elimina un producto de la base de datos según su ID.
    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        boolean eliminado = false;

        try (Connection conn = db.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int filas = stmt.executeUpdate();
            eliminado = filas > 0;
            System.out.println(eliminado ? "El producto fue eliminado correctamente." : "El producto no ha sido encontrado.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }

        return eliminado;
    }
//Busca productos cuyo nombre contenga una determinada cadena.
    public List<ProductoOtaku> buscarProductosPorNombre(String nombre) {
        String sql = "SELECT * FROM productos WHERE nombre LIKE ?";
        List<ProductoOtaku> lista = new ArrayList<>();

        try (Connection conn = db.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nombre + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ProductoOtaku producto = new ProductoOtaku(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("categoria"),
                            rs.getDouble("precio"),
                            rs.getInt("stock")
                    );
                    lista.add(producto);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar los productos por nombre: " + e.getMessage());
        }

        return lista;
    }
//Busca productos según una categoría dada.
    public List<ProductoOtaku> buscarProductosPorCategoria(String categoria) {
        String sql = "SELECT * FROM productos WHERE categoria LIKE ?";
        List<ProductoOtaku> lista = new ArrayList<>();

        try (Connection conn = db.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + categoria + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ProductoOtaku producto = new ProductoOtaku(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("categoria"),
                            rs.getDouble("precio"),
                            rs.getInt("stock")
                    );
                    lista.add(producto);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar productos por categoría: " + e.getMessage());
        }

        return lista;
    }
}
