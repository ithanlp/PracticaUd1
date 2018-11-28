package restaurantguide.cifprodolfoucha.com.restaurantguide.controlador;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ConexionBD extends SQLiteOpenHelper{

    private SQLiteDatabase bd;

    public final static String NOME_BD= "platos.db";
    public final static int VERSION_BD=1;
    private static ConexionBD sInstance;

    public ConexionBD(Context context){
        super(context, NOME_BD,null,VERSION_BD);

    }




    @Override
    public void onCreate(SQLiteDatabase db) {

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
            bd = sInstance.getWritableDatabase();
        }
    }

    public void cerrarBD(){
        if(bd==null || bd.isOpen()){
            bd.close();
        }
    }

    public void mostarDatos(){
        Cursor c = bd.rawQuery("select * from comidas",null);

        if(c.moveToFirst()){
            Toast.makeText(null, c.getString(1), Toast.LENGTH_SHORT).show();
        }
    }


}
