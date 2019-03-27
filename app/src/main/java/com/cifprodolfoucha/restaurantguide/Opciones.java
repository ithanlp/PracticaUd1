package com.cifprodolfoucha.restaurantguide;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import restaurantguide.cifprodolfoucha.com.restaurantguide.R;

public class Opciones extends Activity {

    TextView tv;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        final Switch sw = findViewById(R.id.swTema);

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

        sp = findViewById(R.id.spRestDelPlato);

        tv = findViewById(R.id.Info);

    }


    public void Onclick(View view) {
        if(view.getId()==R.id.btnInfo){
            tv.setVisibility(View.VISIBLE);
        }

    }
}
