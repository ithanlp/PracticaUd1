package restaurantguide.cifprodolfoucha.com.restaurantguide.controlador;

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

public class ConexionBD extends SQLiteOpenHelper{

    private SQLiteDatabase bd;

    public final static String NOME_BD= "platos.db";
    public final static String PATH_BD = "/data/data/restaurantguide.cifprodolfoucha.com.restaurantguide/databases/";
    public final static int VERSION_BD=1;
    private static ConexionBD sInstance;
    private Context cont;

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

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // Si existe, no haemos nada!
        } else {
            // Llamando a este método se crea la base de datos vacía en la ruta
            // por defecto del sistema de nuestra aplicación por lo que
            // podremos sobreescribirla con nuestra base de datos.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copiando database");
            }
        }
    }

    private void copyDataBase() throws IOException {

        OutputStream databaseOutputStream = new FileOutputStream("" + PATH_BD + NOME_BD);
        InputStream databaseInputStream;

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
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

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


    public void abrirBD(){
        if(bd==null || !bd.isOpen()){

            String myPath = PATH_BD + NOME_BD;
            bd = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
        }
    }


    public synchronized void close(){
        if(bd != null){
            bd.close();
        }
        super.close();
    }



    public void mostarDatos(){
        Cursor c = bd.rawQuery("select * from comidas",null);

        if(c.moveToFirst()){
            Toast.makeText(cont, c.getString(1), Toast.LENGTH_SHORT).show();
        }
    }


}
