package ium.project.clanmanagerforclashroyale;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import ium.project.clanmanagerforclashroyale.data.Filtro;
import ium.project.clanmanagerforclashroyale.data.Giocatore;

public class ClanManager extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //TODO: controllare l'effetto del suggeritore sul clan manager
    //TODO: modificare il layout di suggeritore e inserirlo come è stato inserito il filtro in clan manager
    //TODO: modificare il layout di home e inserirlo come tab in clan manager
    //le voci che rimangono nel menu sono: clan manager con tab home e clan manager vero e proprio + filtro + suggeritore
    //la voce esci e la voce informazioni
    //eventualmente inserire una voce per ricordare i filtri applicati e i suggerimenti applicati nel clan manager vero e proprio

    //probabilmente il riferimento a splash screen non serve più... basta riferirsi sempre alla stessa locazione di memoria
    //(vedere cosa viene utilizzato per riferirsi ai giocatori nelle varie schermate)

    private MyAdapter ma = null;

    private Filtro f = new Filtro();

    ium.project.clanmanagerforclashroyale.data.ClanManager c = new ium.project.clanmanagerforclashroyale.data.ClanManager();

    private List<Giocatore> data = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.filtra:
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                GestioneFiltro g = new GestioneFiltro();
                g.setA(ma);
                g.setN(9);
                g.show(fm,"Filtra membri per:");
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clan_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        c.setnSettimana(9);

        //TODO: decidere come gestire la questione capo/cocapo

        ListView l = findViewById(R.id.clan_manager_list);

        data = c.ApplyFilters();

        ma = new MyAdapter(this, data,9,R.layout.layout_list_clan_manager);
        ma.setFm(getSupportFragmentManager());
        l.setAdapter(ma);
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
            i = new Intent(ClanManager.this, Home.class);
        } else if (id == R.id.nav_clanmanager) {
            ;
        } else if (id == R.id.nav_esci) {
            i = new Intent(ClanManager.this, EnterYourTag.class);
        } else if (id == R.id.nav_info) {
            i = new Intent(ClanManager.this, Informazioni.class);
        } else if (id == R.id.nav_registro) {
            i = new Intent(ClanManager.this, Registro.class);
        } else if (id == R.id.nav_regole) {
            i = new Intent(ClanManager.this, Suggeritore.class);
        }


        if(i != null)
            startActivity(i);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
