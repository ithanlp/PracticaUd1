package restaurantguide.cifprodolfoucha.com.restaurantguide.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import restaurantguide.cifprodolfoucha.com.restaurantguide.R;
import restaurantguide.cifprodolfoucha.com.restaurantguide.modelo.ImageAdapter;

public class Listado_Restaurantes extends AppCompatActivity {

    private GridView gvPlatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado__restaurantes);

        gvPlatos = findViewById(R.id.gvPlatos);
        gvPlatos.setAdapter(new ImageAdapter(this));
        gvPlatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
