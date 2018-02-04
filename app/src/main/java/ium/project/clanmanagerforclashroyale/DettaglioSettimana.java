package ium.project.clanmanagerforclashroyale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;

import ium.project.clanmanagerforclashroyale.data.*;

public class DettaglioSettimana extends AppCompatActivity {

    private MyAdapter ma = null;

    private ium.project.clanmanagerforclashroyale.data.ClanManager c = new ium.project.clanmanagerforclashroyale.data.ClanManager();

    private Filtro f = new Filtro();

    private int n;

    //TODO: la settimana ripristinata è sempre la settimana 0... ???

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
            case R.id.filtra: {
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                GestioneFiltro g = new GestioneFiltro();
                g.setA(ma);
                g.setN(n);
                g.show(fm, "Filtra membri per:");
            }
                break;
            case R.id.ordina: {
                android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                GestioneOrdinamento g = new GestioneOrdinamento();
                g.setA(BlankFragment2.getAdapter());
                g.setN(n);
                g.show(fm, "Ordina membri per:");
            }
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_settimana);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().getExtras() != null)
                n = getIntent().getExtras().getInt("settimana");

        setTitle((9 - n)+" settimane fa");

        c.setnSettimana(n);

        ListView l = findViewById(R.id.clan_manager_list);

        f.setMinCoroneBaule(0);
        f.setMaxCoroneBaule(Integer.MAX_VALUE);
        f.setMinDonazioni(0);
        f.setMaxDonazioni(Integer.MAX_VALUE);
        f.setMinTrofei(0);
        f.setMaxTrofei(Integer.MAX_VALUE);

        c.setFiltro(f);

        List<Giocatore> data = c.ApplyFilters();

        ma = new MyAdapter(this, data,n,R.layout.layout_list_clan_manager_dettaglio);
        l.setAdapter(ma);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ac = new Intent(DettaglioSettimana.this,DettaglioGiocatore.class);
                ac.putExtra("giocatore",i);
                startActivity(ac);
            }
        });
    }

}
