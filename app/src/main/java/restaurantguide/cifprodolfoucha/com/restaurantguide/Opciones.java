package restaurantguide.cifprodolfoucha.com.restaurantguide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Opciones extends Activity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        final Switch sw = findViewById(R.id.switch1);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"Tema cambiado Negro",Toast.LENGTH_LONG).show();
                    sw.setText("Negro");
                }else{
                    Toast.makeText(getApplicationContext(),"Tema cambiado Blanco",Toast.LENGTH_LONG).show();
                    sw.setText("Blanco");
                }
            }
        });


        tv = findViewById(R.id.Info);

    }

    public void Onclick(View view) {
        if(view.getId()==R.id.btnInfo){
            tv.setVisibility(View.VISIBLE);
        }

    }
}
