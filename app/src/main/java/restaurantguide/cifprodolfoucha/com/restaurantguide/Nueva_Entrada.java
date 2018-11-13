package restaurantguide.cifprodolfoucha.com.restaurantguide;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Spinner;

public class Nueva_Entrada extends Activity {

    ImageView img;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = findViewById(R.id.spinner);

        img = findViewById(R.id.imageView3);
        img.setImageBitmap(foto());

        setContentView(R.layout.activity_nueva__entrada);


    }

    public Bitmap foto(){

        Intent foto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Bundle extras = foto.getExtras();

        Bitmap imagen = (Bitmap) extras.get("data");

        return imagen;

    }

}
