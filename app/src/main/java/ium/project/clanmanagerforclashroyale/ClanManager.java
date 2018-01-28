package ium.project.clanmanagerforclashroyale;

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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ium.project.clanmanagerforclashroyale.data.Clan;
import ium.project.clanmanagerforclashroyale.data.Filtro;
import ium.project.clanmanagerforclashroyale.data.Giocatore;

public class ClanManager extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //TODO: una volta inserito il filtro funzionante da bottone, modificare la stampa in base alla dimensione della lista. mostrare un messaggio nel caso in cui la lista sia vuota
    //TODO: prendere i numeri del suggeritore e in base a quelli settare lo sfondo dei giocatori nella lista

    private ListView stampa(List<Giocatore> giocatori)
    {
        ListView lista = (ListView)findViewById(R.id.clan_manager_list);

        /* Lista di hash map */
        List<HashMap<String,Object>> listaMappe = new ArrayList<>();

        for(int i = 0; i < 10; i++)
        {
            /* per ogni settimana creo una mappa dove associo una stringa al numero dell'elemento considerato: es donazioni, 50 (settimana 1) */
            HashMap<String,Object> mappa = new HashMap<>();

            mappa.put("nome",giocatori.get(i).getNome());
            mappa.put("grado",giocatori.get(i).getGrado());
            mappa.put("corone",new Integer(giocatori.get(i).getCoppeBaule()[0]));
            mappa.put("donazioni",new Integer(giocatori.get(i).getDonazioni()[0]));
            mappa.put("trofei",new Integer(giocatori.get(i).getCorone()));
            mappa.put("iconaC",new Integer(R.drawable.ic_home_corone_nospace));
            mappa.put("iconaD",new Integer (R.drawable.ic_home_donazioni_nospace));
            mappa.put("iconaT",new Integer(R.drawable.ic_home_trofei_nospace));

            //aggiungo la mappa alla lista precedentemente creata
            listaMappe.add(mappa);
        }

        /* collego le string agli id della vista */
        String [] from = {"nome","grado","corone","donazioni","trofei","iconaC","iconaD","iconaT"}; //chiavi delle mappe di ciascun elemento della lista
        int [] to = {R.id.nome,R.id.grado,R.id.n_corone,R.id.n_donazioni,R.id.n_trofei,R.id.corone,R.id.carte,R.id.coppe}; //id del layout personalizzato

        SimpleAdapter a = new SimpleAdapter(getApplicationContext(),listaMappe,R.layout.layout_list_clan_manager,from,to);
        lista.setAdapter(a);

        return lista;
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

        Clan clan  = new Clan();
        int trofei = clan.getCoppeClan();
        int donazioni = clan.getDonazioniTotali()[0];
        int membri = 10;
        int corone = clan.getBauleClan()[0];
        int baule = Registro.nBaule(corone);

        EditText minCorone, maxCorone;
        EditText minDonazioni, maxDonazioni;
        EditText minTrofei, maxTrofei;

        minCorone = (EditText)findViewById(R.id.min_corone);
        maxCorone = (EditText)findViewById(R.id.max_corone);

        minDonazioni = (EditText)findViewById(R.id.min_donazioni);
        maxDonazioni = (EditText)findViewById(R.id.max_donazioni);

        minTrofei = (EditText)findViewById(R.id.min_trofei);
        maxTrofei = (EditText)findViewById(R.id.max_trofei);

        List<Giocatore> giocatori = clan.getComponenti();
        ium.project.clanmanagerforclashroyale.data.ClanManager c = new ium.project.clanmanagerforclashroyale.data.ClanManager();
        Filtro f = new Filtro();
        c.setnSettimana(0);

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
        //giocatori = c.ApplyFilters();

        /*Button filtro = (Button)findViewById(R.id.filtra);
        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        ListView l = stampa(giocatori);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(ClanManager.this, DettaglioGiocatore.class);
                in.putExtra("giocatore",i); //i è il numero del giocatore
                startActivity(in);
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
