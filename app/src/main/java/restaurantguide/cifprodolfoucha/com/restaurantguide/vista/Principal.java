package restaurantguide.cifprodolfoucha.com.restaurantguide.vista;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import restaurantguide.cifprodolfoucha.com.restaurantguide.R;
import restaurantguide.cifprodolfoucha.com.restaurantguide.controlador.ConexionBD;
import restaurantguide.cifprodolfoucha.com.restaurantguide.modelo.ImagenFav;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ClipData clip;
    private ClipboardManager clipboard;
    private ConexionBD bd;
    public InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ConexionBD.getInstance(this.getApplicationContext()).abrirBD();
        ConexionBD.getInstance(this.getApplicationContext()).mostarDatos();
        ConexionBD.getInstance(this.getApplicationContext()).cerrarBD();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnAñadir);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, Nueva_Entrada.class);
                startActivity(intent);

            }
        });


        //crear on start



        String bdDestino = "/data/data/restaurantguide.cifprodolfoucha.com.restaurantguide/databases/"+ConexionBD.NOME_BD;

        File filebdDestino = new File(bdDestino);
        if(filebdDestino.exists())return;

        String pathbd = "/data/data/restaurantguide.cifprodolfoucha.com.restaurantguide/databases/";

        File filepathdb = new File(pathbd);
        filepathdb.mkdirs();



        try{
            inputStream = getAssets().open(ConexionBD.NOME_BD);
            OutputStream ops = new FileOutputStream(bdDestino);

            int puntero;
            byte[] buffer = new byte[2048];


            while((puntero = inputStream.read(buffer))>0){
                ops.write(buffer,0,puntero);
            }

            inputStream.close();
            ops.flush();
            ops.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navPrincipal);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actionOpciones) {
            Intent intent = new Intent(Principal.this, Opciones.class);
            startActivity(intent);
        } else if(id == R.id.actionSalir){
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navNuevoPlato) {
            Intent intent = new Intent(Principal.this, Nueva_Entrada.class);
            startActivity(intent);
        } else if (id == R.id.navListaRestaurantes) {

            Intent rest = new Intent (Principal.this, Listado_Restaurantes.class);
            startActivity(rest);

        } else if (id == R.id.navListaFavs) {

            Intent rest = new Intent (Principal.this, Listado_Favoritos.class);
            startActivity(rest);
        } else if (id == R.id.navOpciones) {
            Intent intent = new Intent(Principal.this, Opciones.class);
            startActivity(intent);
        } else if (id == R.id.navCorreo) {

            Intent send = new Intent (Intent.ACTION_SEND);
            send.setType("text/plain");


            ClipboardManager clipB = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(null,"restaurantguide@rest.com");
            clipB.setPrimaryClip(clip);

            Toast toast = Toast.makeText(this, "restaurantguide@rest.com", Toast.LENGTH_LONG);
            toast.show();

            send.putExtra(Intent.EXTRA_EMAIL,"restaurantguide@rest.com");


            if(send.resolveActivity(getPackageManager())!=null){
                startActivity(send);
            }

        } else if(id == R.id.navSalir){
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
