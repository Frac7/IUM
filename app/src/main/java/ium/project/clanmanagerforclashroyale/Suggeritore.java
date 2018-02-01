package ium.project.clanmanagerforclashroyale;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Suggeritore extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggeritore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        SplashScreen.c.setSuggeritore(new ium.project.clanmanagerforclashroyale.data.Suggeritore());
        final Intent clanManager= new Intent(getApplicationContext(),ClanManager.class);
        Button button = (Button) findViewById(R.id.confirm_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view ){
                //Cosa avviene al click di conferma
                Context context=getApplicationContext();
                int duration= Toast.LENGTH_SHORT;
                CharSequence text ="Suggerimenti applicati con succeeso";

                EditText corone =(EditText) findViewById(R.id.corone_MIN_suggeritore);
                if(corone.getText().toString().equals("")){
                    SplashScreen.c.getSuggeritore().setCorone(0);
                }
                else{
                    SplashScreen.c.getSuggeritore().setCorone(Integer.parseInt(corone.getText().toString())); /** Riferimento da cambiare quando spostiamo su splash screen**/
                }

                EditText donazioni =(EditText) findViewById(R.id.donazioni_MIN_suggeritore);
                if(donazioni.getText().toString().equals("")){
                    SplashScreen.c.getSuggeritore().setDonazioni(0);
                }
                else{
                    SplashScreen.c.getSuggeritore().setDonazioni(Integer.parseInt(corone.getText().toString())); /** Riferimento da cambiare quando spostiamo su splash screen**/
                }

                SplashScreen.c.setnSettimana(9);
                SplashScreen.c.applyHint(); //Applicazione suggerimento
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                startActivity(clanManager);
            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //TODO: gestire il back per non tornare alla home una volta effettuato l'accesso se non tramite la voce esci
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent i = null;

        if (id == R.id.nav_home) {
            i = new Intent(Suggeritore.this, Home.class);
        } else if (id == R.id.nav_clanmanager) {
            i = new Intent(Suggeritore.this, ClanManager.class);
        } else if (id == R.id.nav_esci) {
            i = new Intent(Suggeritore.this, EnterYourTag.class);
        } else if (id == R.id.nav_info) {
            i = new Intent(Suggeritore.this, Informazioni.class);
        } else if (id == R.id.nav_registro) {
            i = new Intent(Suggeritore.this, Registro.class);
        } else if (id == R.id.nav_regole) {
            ;
        }
        if(i != null)
            startActivity(i);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
