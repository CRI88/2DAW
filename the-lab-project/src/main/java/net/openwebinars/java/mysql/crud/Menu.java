package net.openwebinars.java.mysql.crud;

import net.openwebinars.java.mysql.crud.dao.ProductoDao;
import net.openwebinars.java.mysql.crud.dao.ProductoDaoImpl;
import net.openwebinars.java.mysql.crud.model.Producto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Menu {

    private KeyboardReader reader;
    private ProductoDao dao;

    public Menu() {
        reader = new KeyboardReader();
        dao = ProductoDaoImpl.getInstance();
    }

    public void init() {

        int opcion;

        do {
            menu();
            opcion = reader.nextInt();

            switch (opcion) {
                case 1 -> listAll();
                case 2 -> listById();
                case 3 -> insert();
                case 4 -> update();
                case 5 -> delete();
                case 0 -> System.out.println("\nSaliendo del programa...\n");
                default -> System.err.println("\nEl número introducido no se corresponde con una operación válida\n\n");
            }

        } while (opcion != 0);

    }

    public void menu() {

        System.out.println("SISTEMA DE GESTIÓN DE PRODUCTOS");
        System.out.println("===============================\n");
        System.out.println("-> Introduzca una opción de entre las siguientes: \n");
        System.out.println("0: Salir");
        System.out.println("1: Listar todos los productos");
        System.out.println("2: Listar un producto por su ID");
        System.out.println("3: Insertar un nuevo producto");
        System.out.println("4: Actualizar un producto");
        System.out.println("5: Eliminar un producto");
        System.out.print("\nOpción: ");

    }

    public void insert() {

        System.out.println("\nINSERCIÓN DE UN NUEVO PRODUCTO");
        System.out.println("--------------------------------\n");

        System.out.print("Nombre del producto: ");
        String nombre = reader.nextLine();

        System.out.print("Precio del producto: ");
        BigDecimal precio = reader.nextBigDecimal();

        System.out.print("Stock disponible: ");
        int stock = reader.nextInt();

        System.out.print("ID de la categoría: ");
        int categoriaId = reader.nextInt();

        try {
            dao.add(new Producto(nombre, precio, stock, categoriaId));
            System.out.println("Producto registrado con éxito");
        } catch (SQLException e) {
            System.err.println("Error insertando el producto. Vuelva a intentarlo.");
        }

        System.out.println("");

    }

    public void listAll() {
        System.out.println("\nLISTADO DE TODOS LOS PRODUCTOS");
        System.out.println("--------------------------------\n");

        try {
            List<Producto> result = dao.getAll();

            if (result.isEmpty())
                System.out.println("No hay productos registrados.");
            else {
                printCabeceraTablaProducto();
                result.forEach(this::printProducto);
            }

        } catch (SQLException e) {
            System.err.println("Error consultando en la base de datos.");
        }

        System.out.println("\n");
    }

    public void listById() {

        System.out.println("\nBÚSQUEDA DE PRODUCTO POR ID");
        System.out.println("------------------------------\n");

        try {
            System.out.print("Introduzca el ID del producto a buscar: ");
            int id = reader.nextInt();

            Producto producto = dao.getById(id);

            if (producto == null)
                System.out.println("No hay productos con ese ID.");
            else {
                printCabeceraTablaProducto();
                printProducto(producto);
            }

            System.out.println("\n");

        } catch (SQLException ex) {
            System.err.println("Error consultando en la base de datos.");
        }

    }

    public void update() {

        System.out.println("\nACTUALIZACIÓN DE UN PRODUCTO");
        System.out.println("------------------------------\n");

        try {
            System.out.print("Introduzca el ID del producto a modificar: ");
            int id = reader.nextInt();

            Producto producto = dao.getById(id);

            if (producto == null)
                System.out.println("No existe un producto con ese ID.");
            else {
                printCabeceraTablaProducto();
                printProducto(producto);
                System.out.println();

                System.out.printf("Nuevo nombre (%s): ", producto.getNombre());
                String nombre = reader.nextLine();
                if (!nombre.isBlank()) producto.setNombre(nombre);

                System.out.printf("Nuevo precio (%s): ", producto.getPrecio());
                String precioStr = reader.nextLine();
                if (!precioStr.isBlank()) producto.setPrecio(new BigDecimal(precioStr));

                System.out.printf("Nuevo stock (%s): ", producto.getStock());
                String stockStr = reader.nextLine();
                if (!stockStr.isBlank()) producto.setStock(Integer.parseInt(stockStr));

                System.out.printf("Nueva categoría (%s): ", producto.getCategoriaId());
                String categoriaStr = reader.nextLine();
                if (!categoriaStr.isBlank()) producto.setCategoriaId(Integer.parseInt(categoriaStr));

                dao.update(producto);

                System.out.printf("Producto con ID %s actualizado.\n", producto.getId());

            }

        } catch (SQLException ex) {
            System.err.println("Error consultando en la base de datos.");
        }

        System.out.println("");

    }

    public void delete() {

        System.out.println("\nBORRADO DE UN PRODUCTO");
        System.out.println("------------------------\n");

        try {
            System.out.print("Introduzca el ID del producto a borrar: ");
            int id = reader.nextInt();

            System.out.printf("¿Está seguro de borrar el producto con ID=%s? (s/n): ", id);
            String confirm = reader.nextLine();

            if (confirm.equalsIgnoreCase("s")) {
                dao.delete(id);
                System.out.printf("Producto con ID %s borrado\n", id);
            }

        } catch (SQLException ex) {
            System.err.println("Error borrando de la base de datos.");
        }

        System.out.println("");

    }

    private void printCabeceraTablaProducto() {
        System.out.printf("%5s %-30s %-10s %-10s %-10s\n", "ID", "NOMBRE", "PRECIO", "STOCK", "CATEGORÍA");
        IntStream.range(1, 70).forEach(x -> System.out.print("-"));
        System.out.println();
    }

    private void printProducto(Producto p) {
        System.out.printf("%5d %-30s %-10.2f %-10d %-10d\n",
                p.getId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getCategoriaId());
    }

    static class KeyboardReader {

        BufferedReader br;
        StringTokenizer st;

        public KeyboardReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException ex) {
                    System.err.println("Error leyendo del teclado");
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        BigDecimal nextBigDecimal() {
            return new BigDecimal(next());
        }

        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException ex) {
                System.err.println("Error leyendo del teclado");
                return "";
            }
        }
    }

}
