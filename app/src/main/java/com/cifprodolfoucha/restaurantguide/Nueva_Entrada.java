package com.cifprodolfoucha.restaurantguide;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import restaurantguide.cifprodolfoucha.com.restaurantguide.R;
import com.cifprodolfoucha.restaurantguide.almacenamiento.ConexionBD;
import com.cifprodolfoucha.restaurantguide.modelo.ImagenFav;

public class Nueva_Entrada extends Activity implements OnClickListener {

    private ImageView img;
    private Spinner sp;
    private Button btn;
    private final int cons = 1;
    private boolean control = false;
    private EditText etNombrePlato;
    private EditText etNombreRestaurante;
    private String nombrePlato;
    private EditText etDescripcion;
    private EditText etRuta;

    private String descripcion;
    private CheckBox chkFav;
    private RadioButton rbtnCercanos;
    private RadioButton rbtnGuardados;
    private RadioButton rbtnNuevo;

    private Bitmap bit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Acceso concedido", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "DENIED", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, cons);
            Toast.makeText(this, "DENIED 2", Toast.LENGTH_SHORT).show();
        }


        setContentView(R.layout.activity_nueva__entrada);
        sp = findViewById(R.id.spRestDelPlato);
        btn = findViewById(R.id.btnAceptar);
        img = findViewById(R.id.ivPlato);
        chkFav = findViewById(R.id.chkFav);
        etNombrePlato = findViewById(R.id.etNombrePlato);
        etNombreRestaurante = findViewById(R.id.txtNuevoRestaurante);
        rbtnCercanos = findViewById(R.id.rbtnCercanos);
        rbtnGuardados = findViewById(R.id.rbtnGuardados);
        rbtnNuevo = findViewById(R.id.rbtnNuevo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etRuta = findViewById(R.id.etRuta);
        
        img.setOnClickListener(this);
    }


    public void onClick(View viev) {
        if (viev.getId() == btn.getId()) {
            ImagenFav imag = new ImagenFav();
            imag.setNombrePlato(etNombrePlato.getText().toString());
            imag.setDescripcion(etDescripcion.getText().toString());
            imag.setUri(etRuta.getText().toString());
            if(chkFav.isSelected()) {
                imag.setFavorito(1);
            }else{
                imag.setFavorito(0);
            }
            imag.setIdRestaurante(1);


            ConexionBD.InsertarPlato(imag);
            finish();
        }
        if (viev.getId() == img.getId()) {
            foto();
        }



        //Pendiente de que fundione

        /*if (viev.getId() == rbtnNuevo.getId()){
            sp.setVisibility(View.INVISIBLE);
            etNombreRestaurante.setVisibility(View.VISIBLE);
        }
        if(viev.getId() == rbtnCercanos.getId()){
            sp.setVisibility(View.VISIBLE);
            etNombreRestaurante.setVisibility(View.INVISIBLE);
        }
        if(viev.getId() == rbtnGuardados.getId()){
            sp.setVisibility(View.VISIBLE);
            etNombreRestaurante.setVisibility(View.INVISIBLE);
        } */

    }


    public void foto() {
        control = true;
        Intent foto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Time now = new Time();
        //now.setToNow();

        //String arch = Environment.getExternalStorageDirectory() + "/external_sd/Fotos/"+ "ID" +now.format2445().toString() +".jpg";

        //imags.add(arch);

        //File f = new File(arch);

        //Uri uri = Uri.fromFile(f);

        //foto.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(foto, 7777);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 7777 && resultCode == RESULT_OK) {
            OutputStream out;

            bit = (Bitmap) data.getExtras().get("data");
            System.out.println(bit.toString());
            img.setImageBitmap(bit);

            File filepath = Environment.getExternalStorageDirectory();
            File dir = new File(filepath.getAbsolutePath() + "/RestoGuide");
            dir.mkdirs();

            Time now = new Time();
            now.setToNow();


            //UUID.randomUUID genera un código único https://es.wikipedia.org/wiki/Identificador_%C3%BAnico_universal

            File file = new File(dir, UUID.randomUUID()+".png");

            Toast.makeText(this, "Guardada en principio"+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();

            etRuta.setText(file.getAbsolutePath());
            try {
                out = new FileOutputStream(file);
                bit.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                Toast.makeText(this, "Ha petao", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("bitmap", bit);
        outState.putString("plato", etNombrePlato.getText().toString());
        outState.putString("desc", etDescripcion.getText().toString());
        outState.putBoolean("fav", chkFav.isChecked());
        outState.putBoolean("cercano", rbtnCercanos.isChecked());
        outState.putBoolean("guardado", rbtnGuardados.isChecked());
        outState.putBoolean("nuevo", rbtnNuevo.isChecked());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        bit = savedInstanceState.getParcelable("bitmap");
        img.setImageBitmap(bit);
        nombrePlato = savedInstanceState.getString("plato");
        etNombrePlato.setText(nombrePlato);
        descripcion = savedInstanceState.getString("desc");
        etDescripcion.setText(descripcion);
        boolean fav = savedInstanceState.getBoolean("fav");
        if (fav) {
            chkFav.setChecked(true);
        }
        boolean cercano = savedInstanceState.getBoolean("cercano");
        boolean guardado = savedInstanceState.getBoolean("guardado");
        if (cercano) {
            rbtnCercanos.setChecked(true);
        } else if (guardado) {
            rbtnCercanos.setChecked(true);
        } else {
            rbtnNuevo.setChecked(true);
        }


    }


}
