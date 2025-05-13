package net.openwebinars.java.mysql.crud;

import net.openwebinars.java.mysql.crud.dao.ProductoDao;
import net.openwebinars.java.mysql.crud.dao.ProductoDaoImpl;
import net.openwebinars.java.mysql.crud.model.Producto;
import net.openwebinars.java.mysql.crud.pool.MyDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.init();
    }

    public static void testDao() {

        ProductoDao dao = ProductoDaoImpl.getInstance();

        Producto prod = new Producto("Teclado Mecánico", new BigDecimal("79.95"), 15, 1);

        try {
            int n = dao.add(prod);
            System.out.println("El número de registros insertados es: " + n);

            List<Producto> productos = dao.getAll();

            if (productos.isEmpty())
                System.out.println("No hay productos registrados");
            else
                productos.forEach(System.out::println);

            Producto prod1 = dao.getById(1);

            System.out.println("\n" + prod1 + "\n");

            prod1.setStock(20);
            n = dao.update(prod1);

            prod1 = dao.getById(1);

            System.out.println("\n" + prod1 + "\n");

            dao.delete(1);

            productos = dao.getAll();
            if (productos.isEmpty())
                System.out.println("No hay productos registrados");
            else
                productos.forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void testPool() {
        try (Connection conn = MyDataSource.getConnection()) {

            DatabaseMetaData metaData = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet tables = metaData.getTables(null, null, "%", types);

            while (tables.next()) {
                System.out.println(tables.getString("TABLE_NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
