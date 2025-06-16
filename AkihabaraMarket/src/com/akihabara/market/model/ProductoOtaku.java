
package com.akihabara.market.model;

//Clase que representa un producto otaku
public class ProductoOtaku {

 private int id;             // ID del producto
 private String nombre;      // Nombre del producto
 private String categoria;   // Categoría (figuras, mangas, etc.)
 private double precio;      // Precio del producto
 private int stock;          // Unidades en stock

 // Constructor vacío
 public ProductoOtaku() {
 }

 // Constructor con ID (para productos ya registrados)
 public ProductoOtaku(int id, String nombre, String categoria, double precio, int stock) {
     this.id = id;
     this.nombre = nombre;
     this.categoria = categoria;
     this.precio = precio;
     this.stock = stock;
 }

 // Constructor sin ID (para nuevos productos)
 public ProductoOtaku(String nombre, String categoria, double precio, int stock) {
     this.nombre = nombre;
     this.categoria = categoria;
     this.precio = precio;
     this.stock = stock;
 }

 public int getId() {
     return id;
 }

 public void setId(int id) {
     this.id = id;
 }

 public String getNombre() {
     return nombre;
 }

 public void setNombre(String nombre) {
     this.nombre = nombre;
 }

 public String getCategoria() {
     return categoria;
 }

 public void setCategoria(String categoria) {
     this.categoria = categoria;
 }

 public double getPrecio() {
     return precio;
 }

 public void setPrecio(double precio) {
     this.precio = precio;
 }

 public int getStock() {
     return stock;
 }

 public void setStock(int stock) {
     this.stock = stock;
 }

 // Representación del producto en forma de texto
 @Override
 public String toString() {
     return "id=" + id +
             ", nombre='" + nombre + '\'' +
             ", categoria='" + categoria + '\'' +
             ", precio=" + precio +
             ", stock=" + stock                ;
 }
}
