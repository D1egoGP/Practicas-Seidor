

import com.akihabara.market.dao.ProductoDAO;
import com.akihabara.market.model.ProductoOtaku;
import com.akihabara.market.service.LlmService;
import com.akihabara.market.view.InterfazConsola;

import java.util.List;

public class Main {
// Método principal que inicia la aplicación de consola.
    public static void main(String[] args) {
        InterfazConsola vista = new InterfazConsola();   // Vista para interacción por consola
        ProductoDAO dao = new ProductoDAO();			// DAO para operaciones con productos
        LlmService llmService = new LlmService();		// Servicio de IA para sugerencias

        boolean salir = false;

        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();

            try {
                switch (opcion) {
                    case 1 -> {    // Añadir nuevo producto
                        String nombre = vista.leerTexto("Nombre del producto: ");
                        String categoria = vista.leerTexto("Categoría: ");
                        double precio = vista.leerDouble("Precio: ");
                        int stock = vista.leerEntero("Stock: ");

                        ProductoOtaku nuevo = new ProductoOtaku(nombre, categoria, precio, stock);
                        dao.agregarProducto(nuevo);
                        vista.mostrarMensaje("Producto añadido con éxito.");
                    }

                    case 2 -> {    // Consultar producto por ID
                        int id = vista.leerEntero("ID del producto: ");
                        ProductoOtaku prod = dao.obtenerProductoPorId(id);
                        if (prod != null)
                            vista.mostrarProducto(prod.toString());
                        else
                            vista.mostrarError("Producto no encontrado.");
                    }

                    case 3 -> {   // Mostrar todos los productos
                        List<ProductoOtaku> productos = dao.obtenerTodosLosProductos();
                        if (productos.isEmpty())
                            vista.mostrarMensaje("No hay productos registrados.");
                        else
                            productos.forEach(p -> vista.mostrarProducto(p.toString()));
                    }

                    case 4 -> {   // Buscar por nombre
                        String nombre = vista.leerTexto("Nombre a buscar: ");
                        List<ProductoOtaku> porNombre = dao.buscarProductosPorNombre(nombre);
                        if (porNombre.isEmpty())
                            vista.mostrarMensaje("No se encontraron productos con ese nombre.");
                        else
                            porNombre.forEach(p -> vista.mostrarProducto(p.toString()));
                    }

                    case 5 -> {  // Buscar por categoría
                        String categoria = vista.leerTexto("Categoría a buscar: ");
                        List<ProductoOtaku> porCat = dao.buscarProductosPorCategoria(categoria);
                        if (porCat.isEmpty())
                            vista.mostrarMensaje("No se encontraron productos en esa categoría.");
                        else
                            porCat.forEach(p -> vista.mostrarProducto(p.toString()));
                    }

                    case 6 -> {   // Actualizar producto existente
                        int id = vista.leerEntero("ID del producto a actualizar: ");
                        ProductoOtaku existente = dao.obtenerProductoPorId(id);
                        if (existente == null) {
                            vista.mostrarError("No existe producto con ese ID.");
                        } else {
                            String nuevoNombre = vista.leerTexto("Nuevo nombre: ");
                            String nuevaCategoria = vista.leerTexto("Nueva categoría: ");
                            double nuevoPrecio = vista.leerDouble("Nuevo precio: ");
                            int nuevoStock = vista.leerEntero("Nuevo stock: ");

                            ProductoOtaku actualizado = new ProductoOtaku(id, nuevoNombre, nuevaCategoria, nuevoPrecio, nuevoStock);
                            dao.actualizarProducto(actualizado);
                            vista.mostrarMensaje("Producto actualizado correctamente.");
                        }
                    }

                    case 7 -> {  // Eliminar producto por ID
                        int id = vista.leerEntero("ID del producto a eliminar: ");
                        dao.eliminarProducto(id);
                        vista.mostrarMensaje("Producto eliminado.");
                    }

                    case 8 -> {
                        // Opción sugerir nombre con IA
                        String tipo = vista.leerTexto("Introduce el tipo de producto otaku: ");
                        String franquicia = vista.leerTexto("Introduce la franquicia: ");

                        String nombreSugerido = llmService.sugerirNombreProducto(tipo, franquicia);

                        if (nombreSugerido == null || nombreSugerido.isEmpty()) {
                            vista.mostrarMensaje("No se recibió una sugerencia válida de la IA.");
                        } else {
                            vista.mostrarMensaje("Nombre sugerido por IA: " + nombreSugerido);
                        }
                    }

                    case 9 -> { // Salir del programa
                        salir = true;
                        vista.mostrarMensaje("Saliendo del programa. ¡Hasta luego!");
                    }

                    default -> vista.mostrarError("Opción no válida. Intenta de nuevo.");
                }
            } catch (Exception e) {
                vista.mostrarError("Ocurrió un error: " + e.getMessage());
            }
        }
    }
}
