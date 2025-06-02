package com.akihabara.market.model;


public class ProductoOtaku {
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    // Este es el constructor vacio
    public ProductoOtaku() {
    }
    // Este es el contructor con todos loa atributos
    public ProductoOtaku(String nombre, String categoria, double precio, int stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
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
    // Extrae el productor por nombre 
    @Override
    public String toString() {
        return "ProductoOtaku {" +
                "Nombre='" + nombre + '\'' +
                ", Categoria='" + categoria + '\'' +
                ", Precio=" + precio +
                ", Stock=" + stock +
                '}';
    }
}
