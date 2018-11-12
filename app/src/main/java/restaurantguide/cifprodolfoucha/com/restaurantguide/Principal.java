package restaurantguide.cifprodolfoucha.com.restaurantguide;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ClipData clip;
    ClipboardManager clipboard;
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_Principal);
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
        if (id == R.id.action_settings_principal) {
            Intent intent = new Intent(Principal.this, Opciones.class);
            startActivity(intent);
        } else if(id == R.id.action_exit_principal){
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera_Principal) {
            Intent intent = new Intent(Principal.this, Nueva_Entrada.class);
            startActivity(intent);
        } else if (id == R.id.nav_restaurantes_principal) {

        } else if (id == R.id.nav_fabs_Principal) {

        } else if (id == R.id.nav_opciones_Principal) {
            Intent intent = new Intent(Principal.this, Opciones.class);
            startActivity(intent);
        } else if (id == R.id.nav_correo_principal) {

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

        } else if(id == R.id.nav_salir_principal){
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
