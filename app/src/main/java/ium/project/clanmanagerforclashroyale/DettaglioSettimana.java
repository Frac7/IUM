package ium.project.clanmanagerforclashroyale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.List;

import ium.project.clanmanagerforclashroyale.data.*;

public class DettaglioSettimana extends AppCompatActivity {

    private Animation anim = null;

    private MyAdapter ma = null;

    private ium.project.clanmanagerforclashroyale.data.ClanManager c = new ium.project.clanmanagerforclashroyale.data.ClanManager();

    private Filtro f = new Filtro();

    public static int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_settimana);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().getExtras() != null)
                n = getIntent().getExtras().getInt("settimana");

        setTitle((n + 1)+" settimane fa");

        c.setnSettimana(n);

        final ListView l = findViewById(R.id.clan_manager_list);

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

        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animazione);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long ll) {
                l.startAnimation(anim);
                Intent ac = new Intent(DettaglioSettimana.this,DettaglioGiocatore.class);
                ac.putExtra("giocatore",i);
                ac.putExtra("settimana",n);
                startActivity(ac);
            }
        });

    }

}
