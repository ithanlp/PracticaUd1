package restaurantguide.cifprodolfoucha.com.restaurantguide.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import restaurantguide.cifprodolfoucha.com.restaurantguide.modelo.ImagenFav;
import restaurantguide.cifprodolfoucha.com.restaurantguide.modelo.Plato;

public class ConexionBD extends SQLiteOpenHelper{

    private static SQLiteDatabase bd;

    public final static String NOME_BD= "platos.db";
    public static String PATH_BD = null;
    public final static int VERSION_BD=1;
    private static ConexionBD sInstance;
    private static Context cont;

    public ConexionBD(Context context){
        super(context, NOME_BD,null,VERSION_BD);
        cont = context;
    }

    public ConexionBD (Context context, String nombre, SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context,nombre,factory,version);
        this.cont=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    public void createDataBase() throws IOException {
        PATH_BD = "/data/data/"+cont.getPackageName()+"/databases/";
        boolean dbExist = checkDataBase();

        if (dbExist) {
            // Si existe, no haemos nada!
            this.getWritableDatabase();
        } else {
            // Llamando a este método se crea la base de datos vacía en la ruta
            // por defecto del sistema de nuestra aplicación por lo que
            // podremos sobreescribirla con nuestra base de datos.



            try {
                this.getReadableDatabase();
                copyDataBase();

            } catch (IOException e) {

                e.printStackTrace();
                //throw new Error("Error copiando database");
            }
        }
    }

    private void copyDataBase() throws IOException {

        OutputStream databaseOutputStream = new FileOutputStream("" + PATH_BD + NOME_BD);
        InputStream databaseInputStream;
        System.out.println("aqui");
        byte[] buffer = new byte[1024];
        int length;

        databaseInputStream = cont.getAssets().open("platos.db");
        while ((length = databaseInputStream.read(buffer)) > 0) {
            databaseOutputStream.write(buffer);
        }

        databaseInputStream.close();
        databaseOutputStream.flush();
        databaseOutputStream.close();
    }


    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = PATH_BD + NOME_BD;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

        } catch (SQLiteException e) {
            // Base de datos no creada todavia
        }

        if (checkDB != null) {

            checkDB.close();
        }

        return checkDB != null ? true : false;

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public static synchronized ConexionBD getInstance(Context context){

        if(sInstance==null){
            sInstance = new ConexionBD(context.getApplicationContext());
        }

        return sInstance;

    }


    public static void abrirBD(){
        if(bd==null || !bd.isOpen()){
            String myPath = PATH_BD + NOME_BD;
            bd = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
        }
    }


    public synchronized void close(){
        if(bd != null){
            bd.close();
        }
        super.close();
    }



    public static void mostarDatos(){
        abrirBD();
        Cursor c = bd.rawQuery("select * from comidas",null);

        if(c.moveToFirst()){
            Toast.makeText(cont, c.getString(1), Toast.LENGTH_SHORT).show();
        }

        c.close();
        bd.close();
    }

    public ArrayList<ImagenFav> imagenesFavs(){
        abrirBD();
        ArrayList<ImagenFav> favs = new ArrayList<>();
        ImagenFav fav = new ImagenFav();
        Cursor c = bd.rawQuery("select * from comidas where favorito=0",null);

        if (c.moveToFirst()){
            do{
                fav.setNombrePlato(c.getString(1));
                fav.setUri(c.getString(2));
                fav.setDescripcion(c.getString(3));
                fav.setFavorito(c.getInt(4));
                fav.setIdRestaurante(c.getInt(5));

                favs.add(fav);
            }while(c.moveToNext());

        }

        c.close();
        bd.close();
        return favs;
    }

    public static void InsertarPlato(ImagenFav imagen){

        abrirBD();

        ContentValues valores = new ContentValues();
        valores.put("nombre",imagen.getNombrePlato());
        valores.put("urlImg",imagen.getUri());
        valores.put("descripcion",imagen.getDescripcion());
        valores.put("favorito",imagen.getFavorito());
        valores.put("restaurante",imagen.getIdRestaurante());

        bd.insert("comidas",null,valores);


        bd.close();

    }



}
