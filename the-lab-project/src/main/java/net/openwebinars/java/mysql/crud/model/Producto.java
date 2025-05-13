package net.openwebinars.java.mysql.crud.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Producto {

    private int id;
    private String nombre;
    private BigDecimal precio;
    private int stock;
    private int categoriaId;

    public Producto() {}

    public Producto(String nombre, BigDecimal precio, int stock, int categoriaId) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoriaId = categoriaId;
    }

    public Producto(int id, String nombre, BigDecimal precio, int stock, int categoriaId) {
        this(nombre, precio, stock, categoriaId);
        this.id = id;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return id == producto.id &&
                stock == producto.stock &&
                categoriaId == producto.categoriaId &&
                Objects.equals(nombre, producto.nombre) &&
                Objects.equals(precio, producto.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, precio, stock, categoriaId);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", categoriaId=" + categoriaId +
                '}';
    }
}
