package ium.project.clanmanagerforclashroyale;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ium.project.clanmanagerforclashroyale.data.Clan;
import ium.project.clanmanagerforclashroyale.data.Filtro;
import ium.project.clanmanagerforclashroyale.data.Giocatore;
import ium.project.clanmanagerforclashroyale.data.GiocatoriFactory;

public class ClanManager extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        Clan clan  = SplashScreen.c.getClan();
        int trofei = clan.getCoppeClan();
        int donazioni = clan.getDonazioniTotali()[9];
        int membri = 10;
        int corone = clan.getBauleClan()[9];
        int baule = clan.NBauleClan(clan.getBauleIsettimana(0));

        final EditText minCorone, maxCorone;
        final EditText minDonazioni, maxDonazioni;
        final EditText minTrofei, maxTrofei;

        minCorone = (EditText)findViewById(R.id.min_corone);
        maxCorone = (EditText)findViewById(R.id.max_corone);

        minDonazioni = (EditText)findViewById(R.id.min_donazioni);
        maxDonazioni = (EditText)findViewById(R.id.max_donazioni);

        minTrofei = (EditText)findViewById(R.id.min_trofei);
        maxTrofei = (EditText)findViewById(R.id.max_trofei);

        List<Giocatore> giocatori = clan.getComponenti();
        final ium.project.clanmanagerforclashroyale.data.ClanManager c = new ium.project.clanmanagerforclashroyale.data.ClanManager();
        final Filtro f = new Filtro();
        c.setnSettimana(9);

        ListView l = findViewById(R.id.clan_manager_list);

        f.setMinCoroneBaule(0);
        f.setMaxCoroneBaule(Integer.MAX_VALUE);
        f.setMinDonazioni(0);
        f.setMaxDonazioni(Integer.MAX_VALUE);
        f.setMinTrofei(0);
        f.setMaxTrofei(Integer.MAX_VALUE);

        c.setFiltro(f);

        final List<Giocatore> data = c.ApplyFilters();

        final MyAdapter ma = new MyAdapter(this, data,9,R.layout.layout_list_clan_manager);
        ma.setFm(getSupportFragmentManager());
        l.setAdapter(ma);

        Button pulisci = (Button)findViewById(R.id.pulisci);
        pulisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maxCorone.setText("");
                minCorone.setText("");
                maxTrofei.setText("");
                minTrofei.setText("");
                maxDonazioni.setText("");
                minDonazioni.setText("");
            }
        });

        Button filtro = (Button)findViewById(R.id.filtra);
        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!minCorone.getText().toString().equals(""))
                    f.setMinCoroneBaule(Integer.parseInt(minCorone.getText().toString()));
                else
                    f.setMinCoroneBaule(0);
                if(!maxCorone.getText().toString().equals(""))
                    f.setMaxCoroneBaule(Integer.parseInt(maxCorone.getText().toString()));
                else
                    f.setMaxCoroneBaule(Integer.MAX_VALUE);
                if(!minDonazioni.getText().toString().equals(""))
                    f.setMinDonazioni(Integer.parseInt(minDonazioni.getText().toString()));
                else
                    f.setMinDonazioni(0);
                if(!maxDonazioni.getText().toString().equals(""))
                    f.setMaxDonazioni(Integer.parseInt(maxDonazioni.getText().toString()));
                else
                    f.setMaxDonazioni(Integer.MAX_VALUE);
                if(!minTrofei.getText().toString().equals(""))
                    f.setMinTrofei(Integer.parseInt(minTrofei.getText().toString()));
                else
                    f.setMinTrofei(0);
                if(!maxTrofei.getText().toString().equals(""))
                    f.setMaxTrofei(Integer.parseInt(maxTrofei.getText().toString()));
                else
                    f.setMaxTrofei(Integer.MAX_VALUE);

                c.setFiltro(f);

                data.removeAll(data);
                data.addAll(c.ApplyFilters());
                ma.notifyDataSetChanged();
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
