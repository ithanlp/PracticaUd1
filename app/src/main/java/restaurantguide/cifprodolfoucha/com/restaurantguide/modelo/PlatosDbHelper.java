package restaurantguide.cifprodolfoucha.com.restaurantguide.modelo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class PlatosDbHelper extends SQLiteOpenHelper {

    public static final int VERSION_BD = 1;
    public static final String NOMBRE_BD = "PlatosDb.db";
    private static final String SQL_CREAR_TABLAS =
            "CREATE TABLE " + PlatosDbModelo.PlatosDb.NOMBRE_TABLA + " (" +
                    PlatosDbModelo.PlatosDb._ID + " INTEGER PRIMARY KEY," +
                    PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_NOMBRE + " TEXT," +
                    PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_IMAGEN + " TEXT,"+
                    PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_FAVORITO + " INTEGER);";

    private static final String SQL_CREAR_TABLAS2 =
            "CREATE TABLE " + PlatosDbModelo.PlatosDb.NOMBRE_TABLA2 + " (" +
                    PlatosDbModelo.PlatosDb._ID + " INTEGER PRIMARY KEY," +
                    PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_NOMBRE + " TEXT," +
                    PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_LOCALIZACION + " TEXT," +
                    PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_LOCALIZACIONLAT + " REAL," +
                    PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_LOCALIZACIONLON + " REAL);";





    private static final String SQL_BORRAR_TODO =
            "DROP TABLE IF EXISTS " + PlatosDbModelo.PlatosDb.NOMBRE_TABLA+";";

    private static final String SQL_BORRAR_TODO2 =
            "DROP TABLE IF EXISTS " + PlatosDbModelo.PlatosDb.NOMBRE_TABLA2+";";





    public PlatosDbHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAR_TABLAS);
        insertarDatos(db,"prueba","imagenprueba.jpg",1);
        insertarDatos(db,"prueba2","imagenprueba2.jpg",0);
        insertarDatosRestaurantes(db,"prueba3","ferrol",(float)43.222323,(float)-8.123223);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_BORRAR_TODO);
        onCreate(db);
    }

    public long insertarDatos(SQLiteDatabase db,String nombre, String urlImagen,int favorito) {

        // Creamos un array de valores para insertar
        ContentValues values = new ContentValues();
        values.put(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_NOMBRE, nombre);
        values.put(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_IMAGEN, urlImagen);
        values.put(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_FAVORITO, favorito);



        // Insertamos la nueva fila, devolviendo el id de la misma en una variable newRowId
        long newRowId = db.insert(PlatosDbModelo.PlatosDb.NOMBRE_TABLA, null, values);
        return newRowId;
    }

    public long insertarDatosRestaurantes(SQLiteDatabase db,String nombre, String localizacion,float lon,float lat) {

        // Creamos un array de valores para insertar
        ContentValues values = new ContentValues();
        values.put(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_NOMBRE, nombre);
        values.put(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_LOCALIZACION, localizacion);
        values.put(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_LOCALIZACIONLON, lon);
        values.put(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_LOCALIZACIONLAT, lat);


        // Insertamos la nueva fila, devolviendo el id de la misma en una variable newRowId
        long newRowId = db.insert(PlatosDbModelo.PlatosDb.NOMBRE_TABLA2, null, values);
        return newRowId;
    }



    public ArrayList leerDatosPlatos(SQLiteDatabase db) {

// Definimos la proyección con el nombre de las columnas
        String[] projection = {
                BaseColumns._ID,
                PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_NOMBRE,
                PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_IMAGEN,
                PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_FAVORITO
        };

// Filtrar los resultados donde el campo nombre sea igual a prueba
        // String selection = PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_NOMBRE + " = ?";
        //String[] selectionArgs = {"prueba"};

// Ordenamos por uno de los campos, en este caso nombre
        String sortOrder =
                PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_NOMBRE + " DESC";

        Cursor cursor = db.query(
                PlatosDbModelo.PlatosDb.NOMBRE_TABLA,   // La tabla
                projection,             // El array de columnas para consultar, con null salen todas
                null,              // columnas para el where
                null,          // valores para el where
                null,                   // no agrupar
                null,                   // no filtrar (having)
                sortOrder               // Orden
        );

        ArrayList<Plato> platos = new <Plato>ArrayList();
        while(cursor.moveToNext()) {
            long platoId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(PlatosDbModelo.PlatosDb._ID));
            String platoNombre = cursor.getString(
                    cursor.getColumnIndexOrThrow(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_NOMBRE)
            );
            String platoUrlImagen = cursor.getString(
                    cursor.getColumnIndexOrThrow(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_IMAGEN)
            );
            int platoFavorito = cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_FAVORITO)
            );

            Plato p = new Plato(platoId,platoNombre,platoUrlImagen,platoFavorito);
            platos.add(p);

        }
        cursor.close();

        return platos;
    }

    public ArrayList leerDatosRestaurantes(SQLiteDatabase db) {

// Definimos la proyección con el nombre de las columnas
        String[] projection = {
                BaseColumns._ID,
                PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_NOMBRE,
                PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_LOCALIZACION,
                PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_LOCALIZACIONLAT,
                PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_LOCALIZACIONLON
        };

// Filtrar los resultados donde el campo nombre sea igual a prueba
        // String selection = PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA_NOMBRE + " = ?";
        //String[] selectionArgs = {"prueba"};

// Ordenamos por uno de los campos, en este caso nombre
        String sortOrder =
                PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_NOMBRE + " DESC";

        Cursor cursor = db.query(
                PlatosDbModelo.PlatosDb.NOMBRE_TABLA2,   // La tabla
                projection,             // El array de columnas para consultar, con null salen todas
                null,              // columnas para el where
                null,          // valores para el where
                null,                   // no agrupar
                null,                   // no filtrar (having)
                sortOrder               // Orden
        );

        ArrayList<Restaurante> restaurantes = new <Restaurante>ArrayList();
        while(cursor.moveToNext()) {
            long platoId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(PlatosDbModelo.PlatosDb._ID));
            String restauranteNombre = cursor.getString(
                    cursor.getColumnIndexOrThrow(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_NOMBRE)
            );
            String restauranteLocalizacion = cursor.getString(
                    cursor.getColumnIndexOrThrow(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_LOCALIZACION)
            );
            float restauranteLon = cursor.getFloat(
                    cursor.getColumnIndexOrThrow(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_LOCALIZACIONLAT)
            );
            float restauranteLat = cursor.getFloat(
                    cursor.getColumnIndexOrThrow(PlatosDbModelo.PlatosDb.NOMBRE_COLUMNA2_LOCALIZACIONLON)
            );

            Restaurante r = new Restaurante(platoId,restauranteNombre,restauranteLocalizacion,restauranteLon,restauranteLat);
            restaurantes.add(r);

        }
        cursor.close();

        return restaurantes;
    }


}
