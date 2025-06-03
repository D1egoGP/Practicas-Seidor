import com.akihabara.market.model.InterfazConsola;
import com.akihabara.market.model.ProductoOtaku;
import com.akihabara.market.model.ProductoDAO;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        InterfazConsola vista = new InterfazConsola();
        ProductoDAO dao = new ProductoDAO();

        boolean salir = false;

        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.leerOpcion();

            switch (opcion) {
                case 1 -> {
                    String nombre = vista.leerTexto("Nombre del producto: ");
                    String categoria = vista.leerTexto("Categoría: ");
                    double precio = vista.leerDouble("Precio: ");
                    int stock = vista.leerEntero("Stock: ");

                    ProductoOtaku nuevo = new ProductoOtaku(nombre, categoria, precio, stock);
                    dao.agregarProducto(nuevo);
                    vista.mostrarMensaje("✅ Producto añadido con éxito.");
                }

                case 2 -> {
                    int id = vista.leerEntero("ID del producto: ");
                    ProductoOtaku prod = dao.obtenerProductoPorId(id);
                    if (prod != null)
                        vista.mostrarProducto(prod.toString());
                    else
                        vista.mostrarError("Producto no encontrado.");
                }

                case 3 -> {
                    List<ProductoOtaku> productos = dao.obtenerTodosLosProductos();
                    if (productos.isEmpty())
                        vista.mostrarMensaje("No hay productos registrados.");
                    else
                        productos.forEach(p -> vista.mostrarProducto(p.toString()));
                }

                case 4 -> {
                    String nombre = vista.leerTexto("Nombre a buscar: ");
                    List<ProductoOtaku> porNombre = dao.buscarProductosPorNombre(nombre);
                    if (porNombre.isEmpty())
                        vista.mostrarMensaje("No se encontraron productos con ese nombre.");
                    else
                        porNombre.forEach(p -> vista.mostrarProducto(p.toString()));
                }

                case 5 -> {
                    String categoria = vista.leerTexto("Categoría a buscar: ");
                    List<ProductoOtaku> porCat = dao.buscarProductosPorCategoria(categoria);
                    if (porCat.isEmpty())
                        vista.mostrarMensaje("No se encontraron productos en esa categoría.");
                    else
                        porCat.forEach(p -> vista.mostrarProducto(p.toString()));
                }

                case 6 -> {
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
                        vista.mostrarMensaje("✅ Producto actualizado correctamente.");
                    }
                }

                case 7 -> {
                    int id = vista.leerEntero("ID del producto a eliminar: ");
                    dao.eliminarProducto(id);
                    vista.mostrarMensaje("🗑️ Producto eliminado (si existía).");
                }

                case 8 -> {
                    vista.mostrarMensaje("👋 Saliendo del programa...");
                    salir = true;
                }

                default -> vista.mostrarError("Opción no válida. Intenta de nuevo.");
            }
        }
    }
}
