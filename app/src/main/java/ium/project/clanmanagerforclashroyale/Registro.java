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

    public static int nBaule (int corone)
    {
        if(corone < 70)
            return 0;
        else if (corone < 160)
            return 1;
        else if(corone < 270)
            return 2;
        else if (corone < 400)
            return 3;
        else if(corone < 550)
            return 4;
        else if(corone < 720)
            return 5;
        else if(corone < 910)
            return 6;
        else if(corone <1120)
            return 7;
        else if(corone < 1350)
            return 8;
        else if(corone < 1600)
            return 9;
        else
            return 10;
    }

    //TODO: spostare altrove questa porzione di codice e inserire le immagini per ogni numero di baule

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
        Clan clan = new Clan();
        /* array di corone e donazioni totali per ogni settimana */
        int [] baule = clan.getBauleClan();
        int [] donazioni = clan.getDonazioniTotali();

        /* Lista di hash map */
        List< HashMap<String,Integer> > listaMappe = new ArrayList<>();

        for(int i = 0; i < 10; i++)
        {
            /* per ogni settimana creo una mappa dove associo una stringa al numero dell'elemento considerato: es donazioni, 50 (settimana 1) */
            HashMap<String,Integer> mappa = new HashMap<>();

            mappa.put("donazioni",donazioni[i]);
            mappa.put("baule",Registro.nBaule(baule[i]));
            mappa.put("corone",baule[i]);
            mappa.put("settimana",i);
            mappa.put("iconaD",R.drawable.ic_home_donazioni);
            mappa.put("iconaB",R.drawable.ic_home_baule);
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
            i = new Intent(Registro.this, Home.class);
        } else if (id == R.id.nav_clanmanager) {
            i = new Intent(Registro.this, ClanManager.class);
        } else if (id == R.id.nav_esci) {
            i = new Intent(Registro.this, EnterYourTag.class);
        } else if (id == R.id.nav_info) {
            i = new Intent(Registro.this, Informazioni.class);
        } else if (id == R.id.nav_registro) {
            ;
        } else if (id == R.id.nav_regole) {
            i = new Intent(Registro.this, Suggeritore.class);
        }

        if(i != null)
            startActivity(i);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
