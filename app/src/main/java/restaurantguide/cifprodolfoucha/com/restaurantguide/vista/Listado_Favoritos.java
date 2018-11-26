package restaurantguide.cifprodolfoucha.com.restaurantguide.vista;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

import restaurantguide.cifprodolfoucha.com.restaurantguide.R;
import restaurantguide.cifprodolfoucha.com.restaurantguide.modelo.ImageAdapter;

public class Listado_Favoritos extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado__favoritos);

        GridView gv = findViewById(R.id.gvFavs);
        gv.setAdapter(new ImageAdapter(this));

        String ruta = "";


        final String finalRuta = ruta;

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + finalRuta), "image/*");
                startActivity(intent);
            }
        });


    }
}
