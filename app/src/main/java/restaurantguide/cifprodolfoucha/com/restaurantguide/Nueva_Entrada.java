package restaurantguide.cifprodolfoucha.com.restaurantguide;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class Nueva_Entrada extends Activity {

    ImageView img;
    Spinner sp;
    static final int REQEST_IMAGE_CAPTURE = 1;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = findViewById(R.id.spinner);
        btn = findViewById(R.id.btnAceptar);
        img = findViewById(R.id.imageView3);
        foto();

        setContentView(R.layout.activity_nueva__entrada);


    }


    public void onClick(View viev){
        if(viev.getId()==btn.getId()){
            finish();
        }
    }


    public void foto(){

        Intent foto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivity(foto);


        Bundle extras = foto.getExtras();
        System.out.println("EXTRASSSSSSSSSSS------ "+foto.getExtras());
        if(foto.hasExtra("data")){
            img.setImageBitmap((Bitmap) foto.getParcelableExtra("data"));
        }else {

            Toast t = Toast.makeText(this, "No funciona", Toast.LENGTH_LONG);
            t.show();
        }

    }







}
