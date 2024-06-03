package com.ispc.lemone;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.ispc.lemone.clases.CategoriaProducto;
import com.ispc.lemone.clases.Orden;
import com.ispc.lemone.clases.Persona;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.clases.ProductoDestacado;
import com.ispc.lemone.clases.TipoUsuario;
import com.ispc.lemone.clases.Usuario;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lemonemobile_3.db";
    private static final int DATABASE_VERSION = 4; // Incrementa la versión de la base de datos

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tablaTiposUsuarios = "CREATE TABLE IF NOT EXISTS TiposDeUsuarios (" +
                "Id INT NOT NULL, " +
                "Nombre VARCHAR(50) NOT NULL, " +
                "PRIMARY KEY(Id))";
        db.execSQL(tablaTiposUsuarios);

        String tablaPersonas = "CREATE TABLE IF NOT EXISTS Personas (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nombre VARCHAR(50) NOT NULL)";
        db.execSQL(tablaPersonas);

        String tablaTiposDePersonas = "CREATE TABLE IF NOT EXISTS TiposDePersonas (" +
                "Id INT NOT NULL, " +
                "Nombre VARCHAR(50) NOT NULL, " +
                "PRIMARY KEY(Id))";
        db.execSQL(tablaTiposDePersonas);

        String tablaUsuarios = "CREATE TABLE IF NOT EXISTS Usuarios (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "IdTipoDeUsuario INT NOT NULL, " +
                "IdPersona INT, " +
                "Email VARCHAR(50), " +
                "Password VARCHAR(255) NOT NULL, " +
                "ActivoActualmente BIT NOT NULL DEFAULT 1, " +
                "Estado VARCHAR(5) DEFAULT 'A', " +
                "DatosPersonales VARCHAR(50) DEFAULT 'Completar nombre y apellido', " +
                "Telefono VARCHAR(50) DEFAULT 'Completar Teléfono', " +
                "FOREIGN KEY(IdPersona) REFERENCES Personas(Id), " +
                "FOREIGN KEY(IdTipoDeUsuario) REFERENCES TiposDeUsuarios(Id))";
        db.execSQL(tablaUsuarios);



        String tablaTiposOperacion = "CREATE TABLE IF NOT EXISTS TiposDeOperacion (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nombre VARCHAR(50))";
        db.execSQL(tablaTiposOperacion);

        String tablaOrdenes = "CREATE TABLE IF NOT EXISTS Ordenes (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Fecha DATE NOT NULL, " +
                "IdProducto INT NOT NULL, " +
                "IdPersona INT, " +
                "Cantidad INT NOT NULL, " +
                "IdTipoDeOperacion INT NOT NULL, " +
                "FOREIGN KEY(IdPersona) REFERENCES Personas(Id), " +
                "FOREIGN KEY(IdProducto) REFERENCES Productos(Id))";
        db.execSQL(tablaOrdenes);

        String tablaCategorias = "CREATE TABLE IF NOT EXISTS Categorias (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nombre VARCHAR(50) NOT NULL)";
        db.execSQL(tablaCategorias);

        String tablaProductos = "CREATE TABLE IF NOT EXISTS Productos (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Codigo VARCHAR(20) NOT NULL, " +
                "Nombre VARCHAR(50) NOT NULL, " +
                "Descripcion VARCHAR(250) NOT NULL, " +
                "InventarioMinimo INT, " +
                "PrecioDeCosto DECIMAL(17, 2), " +
                "PrecioDeVenta DECIMAL(17, 2), " +
                "IdCategoria INT, " +
                "ActivoActualmente BIT, " +
                "FOREIGN KEY(IdCategoria) REFERENCES Categorias(Id))";
        db.execSQL(tablaProductos);

        String tablaProductosDestacados = "CREATE TABLE IF NOT EXISTS ProductosDestacados (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FechaDesde DATETIME NOT NULL, " +
                "FechaHasta DATETIME NOT NULL, " +
                "IdProducto INT NOT NULL, " +
                "FOREIGN KEY(IdProducto) REFERENCES Productos(Id))";
        db.execSQL(tablaProductosDestacados);

        db.execSQL("INSERT INTO TiposDeUsuarios VALUES (1,'Administrador')");
        db.execSQL("INSERT INTO TiposDeUsuarios VALUES (2,'Usuario común')");

        db.execSQL("INSERT INTO Personas (Id, Nombre) VALUES (1, 'Admin')");
        db.execSQL("INSERT INTO Personas (Id, Nombre) VALUES (2, 'Melisa')");
        db.execSQL("INSERT INTO Personas (Id, Nombre) VALUES (3, 'Juan')");
        db.execSQL("INSERT INTO Personas (Id, Nombre) VALUES (4, 'Marta')");

        db.execSQL("INSERT INTO Usuarios (Id, IdTipoDeUsuario, IdPersona, Email, Password, ActivoActualmente) VALUES (1, 1, 1, 'admin@admin.com', '12345678', 1)");
        db.execSQL("INSERT INTO Usuarios (Id, IdTipoDeUsuario, IdPersona, Email, Password, ActivoActualmente) VALUES (2, 2, 2, 'melisaapaz@gmail.com', '12345678', 1)");
        db.execSQL("INSERT INTO Usuarios (Id, IdTipoDeUsuario, IdPersona, Email, Password, ActivoActualmente) VALUES (3, 2, 3, 'juanperez@gmail.com', '12345678', 1)");
        db.execSQL("INSERT INTO Usuarios (Id, IdTipoDeUsuario, IdPersona, Email, Password, ActivoActualmente) VALUES (4, 2, 4, 'martasanchez@gmail.com', '12345678', 1)");

        db.execSQL("INSERT INTO TiposDeOperacion VALUES (1,'Ingreso de stock')");
        db.execSQL("INSERT INTO TiposDeOperacion VALUES (2,'Egreso de stock')");

        db.execSQL("INSERT INTO Categorias VALUES (1,'Categoría 1')");
        db.execSQL("INSERT INTO Categorias VALUES (2,'Categoría 2')");
        db.execSQL("INSERT INTO Categorias VALUES (3,'Categoría 3')");

        db.execSQL("INSERT INTO Productos VALUES (1,'420101','Producto 1','Descripción producto 1',10,120,220,1,1)");

        db.execSQL("INSERT INTO Ordenes (Fecha, IdProducto, IdPersona, IdTipoDeOperacion, Cantidad) VALUES ('23/10/2023', 1, 1, 1, 10)");
        db.execSQL("INSERT INTO Ordenes (Fecha, IdProducto, IdPersona, IdTipoDeOperacion, Cantidad) VALUES ('24/10/2023', 1, 1, 2, 2)");
        db.execSQL("INSERT INTO Ordenes (Fecha, IdProducto, IdPersona, IdTipoDeOperacion, Cantidad) VALUES ('25/10/2023', 1, 2, 1, 10)");
        db.execSQL("INSERT INTO Ordenes (Fecha, IdProducto, IdPersona, IdTipoDeOperacion, Cantidad) VALUES ('26/10/2023', 1, 2, 2, 3)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            String tablaProductosDestacados = "CREATE TABLE IF NOT EXISTS ProductosDestacados (" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "FechaDesde DATETIME NOT NULL, " +
                    "FechaHasta DATETIME NOT NULL, " +
                    "IdProducto INT NOT NULL, " +
                    "FOREIGN KEY(IdProducto) REFERENCES Productos(Id))";
            db.execSQL(tablaProductosDestacados);
        }
        if (oldVersion < 3) {
            // Agregar la columna Estado en la versión 3
            db.execSQL("ALTER TABLE Usuarios ADD COLUMN Estado VARCHAR(5) DEFAULT 'A'");
        }
        if (oldVersion < 4) {
            // Agregar la columna Estado en la versión 3
            db.execSQL("ALTER TABLE Usuarios ADD COLUMN DatosPersonales VARCHAR(50) DEFAULT 'Completar nombre y apellido'");
            db.execSQL("ALTER TABLE Usuarios ADD COLUMN Telefono VARCHAR(50) DEFAULT 'Completar teléfono'");
        }
        // Maneja otras actualizaciones de esquema aquí
    }
    public boolean existeProductoDestacadoEnFechas(int idProducto, Date fechaDesde, Date fechaHasta) {
        SQLiteDatabase db = this.getReadableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        String query = "SELECT COUNT(*) FROM productos_destacados WHERE idProducto = ? AND ((fechaDesde BETWEEN ? AND ?) OR (fechaHasta BETWEEN ? AND ?))";
        Cursor cursor = db.rawQuery(query, new String[]{
                String.valueOf(idProducto),
                sdf.format(fechaDesde),
                sdf.format(fechaHasta),
                sdf.format(fechaDesde),
                sdf.format(fechaHasta)
        });

        boolean existe = false;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                existe = cursor.getInt(0) > 0;
            }
            cursor.close();
        }
        db.close();
        return existe;
    }

    public List<Producto> obtenerTodosLosProductos2() {
        List<Producto> productos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Productos", null);

        if (cursor.moveToFirst()) {
            do {
                int id =  cursor.getInt(cursor.getColumnIndexOrThrow("Id"));

                String codigo = cursor.getString(cursor.getColumnIndexOrThrow("Codigo"));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("Nombre"));
                String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("Descripcion"));
                int inventarioMinimo = cursor.getInt(cursor.getColumnIndexOrThrow("InventarioMinimo"));
                double precioDeCosto = cursor.getDouble(cursor.getColumnIndexOrThrow("PrecioDeCosto"));
                double precioDeVenta = cursor.getDouble(cursor.getColumnIndexOrThrow("PrecioDeVenta"));
                int idCategoria = cursor.getInt(cursor.getColumnIndexOrThrow("IdCategoria"));
                boolean activoActualmente = cursor.getInt(cursor.getColumnIndexOrThrow("ActivoActualmente"))  == 1;;

                Producto producto = new Producto(id, codigo, nombre, descripcion, inventarioMinimo, precioDeCosto, precioDeVenta, null, activoActualmente);
                productos.add(producto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productos;
    }

    public int obtenerIdProductoPorNombre(String nombre) {
        int idProducto = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Id FROM Productos WHERE Nombre = ?", new String[]{nombre});

        if (cursor.moveToFirst()) {
            idProducto = cursor.getInt(cursor.getColumnIndexOrThrow("Id"));
        }

        cursor.close();
        db.close();

        return idProducto;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        String query = "SELECT * FROM Usuarios WHERE Estado = 'A'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int idTipoUsuario = cursor.getInt(1);
                int idPersona = cursor.getInt(2);
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("Email"));
                String password = cursor.getString(4);
                String telefono = cursor.getString(6);
                String datosPersonales = cursor.getString(7);
                boolean activoActualmente = cursor.getInt(5) == 1;

                Usuario usuario = new Usuario();
                usuario.setId(id);
                usuario.setTipoUsuario(buscarTipoUsuarioPorId(idTipoUsuario));
                usuario.setPersona(buscarPersonaPorId(idPersona));
                usuario.setEmail(email);
                usuario.setPassword(password);
                usuario.setActivoActualmente(activoActualmente);
                usuario.setTelefono(telefono);
                usuario.setDatosPersonales(datosPersonales);

                usuarios.add(usuario);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return usuarios;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        Usuario usuario = new Usuario();
        String query = "SELECT * FROM Usuarios WHERE email = '" + email + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            int idTipoUsuario = cursor.getInt(1);
            int idPersona = cursor.getInt(2);
            String password = cursor.getString(4);
            boolean activoActualmente = cursor.getInt(5) == 1;
            usuario.setId(id);
            usuario.setTipoUsuario(buscarTipoUsuarioPorId(idTipoUsuario));
            usuario.setPersona(buscarPersonaPorId(idPersona));
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setActivoActualmente(activoActualmente);
        }
        cursor.close();
        db.close();
        return usuario;
    }

    public Persona buscarPersonaPorId(int id) {
        String query = "SELECT * FROM Personas WHERE id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Persona persona = new Persona();
        if (cursor.moveToFirst()) {
            String apellido = cursor.getString(1);
            String nombre = cursor.getString(2);
            double telefono = cursor.getDouble(3);
            int idTipoPersona = cursor.getInt(4);
            String domicilio = cursor.getString(5);
            persona.setId(id);
            persona.setApellido(apellido);
            persona.setNombre(nombre);
            persona.setTelefono(telefono);
            persona.setDomicilio(domicilio);
        }
        cursor.close();
        db.close();
        return persona;
    }

    public TipoUsuario buscarTipoUsuarioPorId(int id) {
        String query = "SELECT * FROM TiposDeUsuarios WHERE id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        TipoUsuario tipoUsuario = null;
        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(1);
            tipoUsuario = new TipoUsuario(id, nombre);
        }
        cursor.close();
        db.close();
        return tipoUsuario;
    }

    public boolean borrarUsuario(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Estado", "B");

        // Actualiza la tabla y obtiene el número de filas afectadas
        int rowsAffected = db.update("Usuarios", values, "Id = ?", new String[]{String.valueOf(id)});

        // Cierra la base de datos después de usarla
        db.close();

        // Devuelve verdadero si al menos una fila fue afectada
        return rowsAffected > 0;
    }




    public boolean editarUsuario(Usuario usuario, String email, String datosPersonales, String telefono, String nuevaPass, boolean isActivoActualmente) {
        SQLiteDatabase db = this.getWritableDatabase();

        int idUsuario = usuario.getId();

        db.beginTransaction();
        try {
            ContentValues valuesUsuario = new ContentValues();
            if (nuevaPass != null && !nuevaPass.isEmpty()) {
                valuesUsuario.put("Password", nuevaPass);
            }
            if (email != null && !email.isEmpty()) {
                valuesUsuario.put("Email", email);
            }
            if (datosPersonales != null && !datosPersonales.isEmpty()) {
                valuesUsuario.put("DatosPersonales", datosPersonales);
            }
            if (telefono != null && !telefono.isEmpty()) {
                valuesUsuario.put("Telefono", telefono);
            }
            valuesUsuario.put("ActivoActualmente", isActivoActualmente ? 1 : 0);

            int filasActualizadas = db.update("Usuarios", valuesUsuario, "Id = ?", new String[]{String.valueOf(idUsuario)});

            if (filasActualizadas > 0) {
                db.setTransactionSuccessful();
                return true;
            } else {
                return false;
            }
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public Usuario obtenerUsuarioPorId(int idUsuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        Usuario usuario = null;
        Cursor cursor = db.query("Usuarios", null, "Id = ?", new String[]{String.valueOf(idUsuario)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow("Id")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("Email")));
            usuario.setDatosPersonales(cursor.getString(cursor.getColumnIndexOrThrow("DatosPersonales")));
            usuario.setTelefono(cursor.getString(cursor.getColumnIndexOrThrow("Telefono")));
            usuario.setActivoActualmente(cursor.getInt(cursor.getColumnIndexOrThrow("ActivoActualmente")) == 1);
            // Asigna otros campos según sea necesario
            cursor.close();
        }
        db.close();
        return usuario;
    }


    public boolean guardarUsuario(Usuario usuario) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("IdTipoDeUsuario", 2);
            //values.put("IdPersona", 1);
            values.put("Email", usuario.getEmail());
            values.put("Password", usuario.getPassword());
            values.put("ActivoActualmente", 1);

            result = db.insert("Usuarios", null, values);
            db.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return result != -1;
    }
    public boolean agregarProducto(Producto producto) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Codigo", producto.getCodigo());
            values.put("Nombre", producto.getNombre());
            values.put("Descripcion", producto.getDescripcion());
            values.put("InventarioMinimo", producto.getInventarioMinimo());
            values.put("PrecioDeCosto", producto.getPrecioDeCosto());
            values.put("PrecioDeVenta", producto.getPrecioDeVenta());
            values.put("IdCategoria", producto.getCategoriaProducto().getId());
            values.put("ActivoActualmente", producto.isActivoActualmente() ? 1 : 0);

            result = db.insert("Productos", null, values);
            db.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return result != -1;
    }

    public CategoriaProducto buscarCategoriaPorId(int id) {
        String query = "SELECT * FROM Categorias WHERE id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        CategoriaProducto categoriaProducto = null;
        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(1);
            categoriaProducto = new CategoriaProducto(id, nombre);
        }
        cursor.close();
        db.close();
        return categoriaProducto;
    }

    public List<Producto> listaDeProductos() {
        List<Producto> productos = new ArrayList<>();

        String query = "SELECT * FROM Productos";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String codigo = cursor.getString(1);
                String nombre = cursor.getString(2);
                String descripcion = cursor.getString(3);
                int inventariominimo = cursor.getInt(4);
                Double preciodecosto = cursor.getDouble(5);
                Double preciodeventa = cursor.getDouble(6);
                int idcategoria = 1;//cursor.getInt(7);
                int activoActualmenteInt = cursor.getInt(8);
                boolean activoActualmente = (activoActualmenteInt == 1);

                Producto producto = new Producto();
                producto.setId(id);
                producto.setCodigo(codigo);
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setInventarioMinimo(inventariominimo);
                producto.setPrecioDeCosto(preciodecosto);
                producto.setPrecioDeVenta(preciodeventa);
                //producto.setCategoriaProducto(buscarCategoriaPorId(idcategoria));
                producto.setActivoActualmente(activoActualmente);

                productos.add(producto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return productos;
    }

    public List<Producto> buscarProductosPorCodigo(String codigo) {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Productos WHERE Codigo = ?";
        String[] selectionArgs = {codigo};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String codigoProducto = cursor.getString(1);
                String nombre = cursor.getString(2);
                String descripcion = cursor.getString(3);

                Producto producto = new Producto();
                producto.setId(id);
                producto.setCodigo(codigoProducto);
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);

                productos.add(producto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return productos;
    }

    public List<Usuario> buscarUsuariosPorNombre(String nombre) {
        List<Usuario> usuarios = new ArrayList<>();
        String query;
        String[] selectionArgs;

        if (!nombre.isEmpty()) {
            query = "SELECT * FROM Usuarios WHERE Email LIKE ?";
            selectionArgs = new String[]{"%" + nombre + "%"};
        } else {
            query = "SELECT * FROM Usuarios";
            selectionArgs = null;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int idTipoUsuario = cursor.getInt(1);
                int idPersona = cursor.getInt(2);
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("Email"));
                String password = cursor.getString(4);
                boolean activoActualmente = cursor.getInt(5) == 1;

                Usuario usuario = new Usuario();
                usuario.setId(id);
                usuario.setTipoUsuario(buscarTipoUsuarioPorId(idTipoUsuario));
                usuario.setPersona(buscarPersonaPorId(idPersona));
                usuario.setEmail(email);
                usuario.setPassword(password);
                usuario.setActivoActualmente(activoActualmente);

                usuarios.add(usuario);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();

            return usuarios;
        }
        return usuarios;
    }

    public boolean eliminarProductoPorId(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM Productos WHERE Id = " + id;

        try {
            db.execSQL(query);
            db.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            db.close();
            return false;
        }
    }

    public boolean actualizarProducto(Producto productoSeleccionado) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Codigo", productoSeleccionado.getCodigo());
            values.put("Nombre", productoSeleccionado.getNombre());
            values.put("Descripcion", productoSeleccionado.getDescripcion());
            values.put("InventarioMinimo", productoSeleccionado.getInventarioMinimo());
            values.put("PrecioDeCosto", productoSeleccionado.getPrecioDeCosto());
            values.put("PrecioDeVenta", productoSeleccionado.getPrecioDeVenta());
            values.put("IdCategoria", productoSeleccionado.getCategoriaProducto().getId());
            values.put("ActivoActualmente", productoSeleccionado.isActivoActualmente() ? 1 : 0);

            result = db.update("Productos", values, "ID=?", new String[]{String.valueOf(productoSeleccionado.getId())});
            db.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return result != 0;
    }

    public List<Orden> getOrdenesConDetalles() {
        List<Orden> ordenes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT o.Id, strftime('%d/%m/%Y', DATE(SUBSTR(o.Fecha, 7, 4) || '-' || SUBSTR(o.Fecha, 4, 2) || '-' || SUBSTR(o.Fecha, 1, 2))) as Fecha, pro.Codigo, pro.Nombre as Producto, o.Cantidad, t.Nombre as TipoDeOperacion, (p.Apellido || ', ' || p.Nombre) as Persona FROM Ordenes o inner join Personas p on p.Id = o.IdPersona inner join Productos pro on pro.Id = o.IdProducto inner join TiposDeOperacion t on t.Id = o.IdTipoDeOperacion";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String fecha = cursor.getString(cursor.getColumnIndex("Fecha"));
                @SuppressLint("Range") String codigoProducto = cursor.getString(cursor.getColumnIndex("Codigo"));
                @SuppressLint("Range") String nombreProducto = cursor.getString(cursor.getColumnIndex("Producto"));
                int cantidad = cursor.getInt(cursor.getInt(3));
                @SuppressLint("Range") String tipoOperacion = cursor.getString(cursor.getColumnIndex("TipoDeOperacion"));
                @SuppressLint("Range") String nombrePersona = cursor.getString(cursor.getColumnIndex("Persona"));

                Orden orden = new Orden();
                orden.setFecha(fecha);
                orden.setCodigoProducto(codigoProducto);
                orden.setNombreProducto(nombreProducto);
                orden.setCantidad(cantidad);
                orden.setTipoOperacion(tipoOperacion);
                orden.setNombrePersona(nombrePersona);

                ordenes.add(orden);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return ordenes;
    }

    public List<Producto> getInventario() {
        List<Producto> inventarios = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT p.Id, P.Codigo, P.Nombre As Producto, C.Nombre as Categoria, P.InventarioMinimo FROM Productos P INNER JOIN Categorias C ON C.Id = P.IdCategoria WHERE P.ActivoActualmente = 1 AND P.InventarioMinimo < 100";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(cursor.getInt(0));
                //@SuppressLint("Range") String codigoProducto = cursor.getString(cursor.getColumnIndex("Codigo"));
                //@SuppressLint("Range") String nombreProducto = cursor.getString(cursor.getColumnIndex("Producto"));
                //@SuppressLint("Range")  String categoria = cursor.getString(cursor.getColumnIndex("Categoria"));
                //int inventarioInt = cursor.getInt(cursor.getInt(4));

                Producto inventario = new Producto();

                inventario.setId(id);
                //inventario.setCodigo(codigoProducto);
                //inventario.setNombre(nombreProducto);
                //inventario.setCategoriaDeProducto(categoria);
                //inventario.setInventarioMinimo(inventarioInt);

                inventarios.add(inventario);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return inventarios;
    }

    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Productos";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String codigoProducto = cursor.getString(1);
                String nombre = cursor.getString(2);
                String descripcion = cursor.getString(3);

                Producto producto = new Producto();
                producto.setId(id);
                producto.setCodigo(codigoProducto);
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);

                productos.add(producto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return productos;
    }

    public boolean insertarProductoDestacado(ProductoDestacado productoDestacado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FechaDesde", productoDestacado.getFechaDesde());
        contentValues.put("FechaHasta", productoDestacado.getFechaHasta());
        contentValues.put("IdProducto", productoDestacado.getIdProducto());

        long result = db.insert("ProductosDestacados", null, contentValues);
        db.close();

        // Log insert operation result
        Log.d("ProductoDestacado", "Insert result: " + result);

        return result != -1;
    }

    public List<ProductoDestacado> getProductosDestacados() {
        List<ProductoDestacado> productosDestacados = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT pd.IdProducto, p.Nombre, pd.FechaDesde, pd.FechaHasta FROM ProductosDestacados pd INNER JOIN Productos p ON pd.IdProducto = p.Id";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int idProducto = cursor.getInt(0);
                String nombreProducto = cursor.getString(1);
                long fechaDesde = cursor.getLong(2);
                long fechaHasta = cursor.getLong(3);

                ProductoDestacado productoDestacado = new ProductoDestacado(fechaDesde, fechaHasta, idProducto, nombreProducto);
                productosDestacados.add(productoDestacado);
            }
            cursor.close();
        }
        db.close();
        return productosDestacados;
    }

    public boolean productoYaDestacado(long idProducto, long fechaDesde, long fechaHasta) {
        List<ProductoDestacado> productosExistente = obtenerProductosPorIdYFechas(idProducto, fechaDesde, fechaHasta);
        boolean existe = !productosExistente.isEmpty();

        // Agregar registros de log para depurar
        Log.d("ProductoDestacado", "ID Producto: " + idProducto);
        Log.d("ProductoDestacado", "Fecha Desde: " + fechaDesde);
        Log.d("ProductoDestacado", "Fecha Hasta: " + fechaHasta);
        Log.d("ProductoDestacado", "Productos Existentes: " + productosExistente.size());
        Log.d("ProductoDestacado", "Producto ya destacado: " + existe);

        return existe;
    }

    public List<ProductoDestacado> obtenerProductosPorIdYFechas(long idProducto, long fechaDesde, long fechaHasta) {
        List<ProductoDestacado> productosDestacados = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM ProductosDestacados WHERE IdProducto = " + idProducto + " AND (FechaDesde <= " + fechaDesde + " AND FechaHasta >= " + fechaHasta + ")";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int idProducto1 = cursor.getInt(0);
                String nombreProducto1 = cursor.getString(1);
                long fechaDesde1 = cursor.getLong(2);
                long fechaHasta1 = cursor.getLong(3);

                ProductoDestacado productoDestacado = new ProductoDestacado(fechaDesde1, fechaHasta1, idProducto1, nombreProducto1);
                productosDestacados.add(productoDestacado);
            }
            cursor.close();
        }
        db.close();
        return productosDestacados;
    }
}
