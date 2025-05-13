package net.openwebinars.java.mysql.crud.dao;

import net.openwebinars.java.mysql.crud.model.Producto;
import net.openwebinars.java.mysql.crud.pool.MyDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDaoImpl implements ProductoDao {

    private static ProductoDaoImpl instance;

    static {
        instance = new ProductoDaoImpl();
    }

    private ProductoDaoImpl() {}

    public static ProductoDaoImpl getInstance() {
        return instance;
    }

    @Override
    public int add(Producto producto) throws SQLException {
        String sql = """
                INSERT INTO producto (nombre, precio, stock, categoria_id)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, producto.getNombre());
            pstm.setBigDecimal(2, producto.getPrecio());
            pstm.setInt(3, producto.getStock());
            pstm.setInt(4, producto.getCategoriaId());

            return pstm.executeUpdate();
        }
    }

    @Override
    public Producto getById(int id) throws SQLException {
        String sql = "SELECT * FROM producto WHERE id = ?";
        Producto producto = null;

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecio(rs.getBigDecimal("precio"));
                    producto.setStock(rs.getInt("stock"));
                    producto.setCategoriaId(rs.getInt("categoria_id"));
                }
            }
        }

        return producto;
    }

    @Override
    public List<Producto> getAll() throws SQLException {
        String sql = "SELECT * FROM producto";
        List<Producto> productos = new ArrayList<>();

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getBigDecimal("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setCategoriaId(rs.getInt("categoria_id"));

                productos.add(producto);
            }
        }

        return productos;
    }

    @Override
    public int update(Producto producto) throws SQLException {
        String sql = """
                UPDATE producto SET nombre = ?, precio = ?, stock = ?, categoria_id = ?
                WHERE id = ?
                """;

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, producto.getNombre());
            pstm.setBigDecimal(2, producto.getPrecio());
            pstm.setInt(3, producto.getStock());
            pstm.setInt(4, producto.getCategoriaId());
            pstm.setInt(5, producto.getId());

            return pstm.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM producto WHERE id = ?";

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();
        }
    }
}
