package ium.project.clanmanagerforclashroyale;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ium.project.clanmanagerforclashroyale.data.Clan;

public class Registro extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        /* la vista della lista viene recuperata tramite tag dall'activity*/
        ListView lista = (ListView) findViewById(R.id.registro_list);
        /*nuova istanza di clan con parametri settati di default*/
        Clan clan = MainActivity.c.getClan();
        //da vedere
        /* array di corone e donazioni totali per ogni settimana */
        int [] baule = clan.getBauleClan();
        int [] donazioni = clan.getDonazioniTotali();

        /* Lista di hash map */
        List< HashMap<String,Integer> > listaMappe = new ArrayList<>();

        for(int i = 0; i < 9; i++)
        {
            /* per ogni settimana creo una mappa dove associo una stringa al numero dell'elemento considerato: es donazioni, 50 (settimana 1) */
            HashMap<String,Integer> mappa = new HashMap<>();

            mappa.put("donazioni",donazioni[i]);
            int a = clan.NBauleClan(baule[i]);
            mappa.put("baule",a);
            mappa.put("corone",baule[i]);
            mappa.put("settimana",(9 - i));
            mappa.put("iconaD",R.drawable.ic_home_donazioni);
            switch(a)
            {
                case 0:
                    mappa.put("iconaB",R.drawable.ic_home_baule);
                    break;
                case 1:
                    mappa.put("iconaB",R.drawable.ic_home_baule_1);
                    break;
                case 2:
                    mappa.put("iconaB",R.drawable.ic_home_baule_2);
                    break;
                case 3:
                    mappa.put("iconaB",R.drawable.ic_home_baule_3);
                    break;
                case 4:
                    mappa.put("iconaB",R.drawable.ic_home_baule_4);
                    break;
                case 5:
                    mappa.put("iconaB",R.drawable.ic_home_baule_5);
                    break;
                case 6:
                    mappa.put("iconaB",R.drawable.ic_home_baule_6);
                    break;
                case 7:
                    mappa.put("iconaB",R.drawable.ic_home_baule_7);
                    break;
                case 8:
                    mappa.put("iconaB",R.drawable.ic_home_baule_8);
                    break;
                case 9:
                    mappa.put("iconaB",R.drawable.ic_home_baule_9);
                    break;
                case 10:
                    mappa.put("iconaB",R.drawable.ic_home_baule_10);
                    break;
            }
            mappa.put("iconaC",R.drawable.ic_home_corone_nospace);
            mappa.put("iconaV",R.drawable.ic_view);

            //aggiungo la mappa alla lista precedentemente creata
            listaMappe.add(mappa);
        }

        /* collego le string agli id della vista */
        String [] from = {"donazioni","baule","corone","settimana","iconaD","iconaB","iconaC","iconaV"}; //chiavi delle mappe di ciascun elemento della lista
        int [] to = {R.id.text_donazioni_1, R.id.text_baule_1,R.id.text_corone_1, R.id.settimana, R.id.img_donazioni,R.id.img_baule,R.id.img_corone,R.id.img_view}; //id del layout personalizzato

        SimpleAdapter a = new SimpleAdapter(getApplicationContext(),listaMappe,R.layout.layout_list_clan_registro,from,to);
        lista.setAdapter(a);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent n = new Intent(Registro.this, DettaglioSettimana.class);
                n.putExtra("settimana",i); //i è il numero della settimana
                startActivity(n);
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
            i = new Intent(Registro.this, MainActivity.class);
        } else if (id == R.id.nav_esci) {
            i = new Intent(Registro.this, EnterYourTag.class);
        } else if (id == R.id.nav_info) {
            i = new Intent(Registro.this, Informazioni.class);
        } else if (id == R.id.nav_registro) {
            ;
        }

        if(i != null)
            startActivity(i);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
