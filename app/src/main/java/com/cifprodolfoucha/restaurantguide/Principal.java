package com.cifprodolfoucha.restaurantguide;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import restaurantguide.cifprodolfoucha.com.restaurantguide.R;
import com.cifprodolfoucha.restaurantguide.almacenamiento.ConexionBD;
import com.cifprodolfoucha.restaurantguide.modelo.ImagenFav;

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



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnAñadir);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Principal.this, Nueva_Entrada.class);
                startActivity(intent);

            }
        });


        //Crear BD:

        ConexionBD bd = new ConexionBD(this, "platos.bd",null,1);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        try {
            bd.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }


        NavigationView navigationView = findViewById(R.id.navPrincipal);
        navigationView.setNavigationItemSelectedListener(this);

        //PruebaMetodo:
        ArrayList<ImagenFav> favs = new ArrayList<>();
        //ConexionBD.getInstance(this.getApplicationContext()).abrirBD();
        favs = ConexionBD.getInstance(this.getApplicationContext()).imagenesFavs();
        //ConexionBD.getInstance(this.getApplicationContext()).mostarDatos();
        //ConexionBD.getInstance(this.getApplicationContext()).close();
        for (ImagenFav fav : favs) {
            Toast.makeText(this, fav.toString(), Toast.LENGTH_SHORT).show();
        }
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
