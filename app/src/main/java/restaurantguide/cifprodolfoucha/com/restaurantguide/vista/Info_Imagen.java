package restaurantguide.cifprodolfoucha.com.restaurantguide.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import restaurantguide.cifprodolfoucha.com.restaurantguide.R;

public class Info_Imagen extends Activity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__imagen);
        btn = findViewById(R.id.btnEditar);

    }

    public void onClick(View view) {

        if(view.getId()==btn.getId()){
            Intent intent = new Intent(getApplicationContext(),Editar_Imagen.class);
        }

    }
}
