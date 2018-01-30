package ium.project.clanmanagerforclashroyale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

import ium.project.clanmanagerforclashroyale.data.*;

public class DettaglioSettimana extends AppCompatActivity {

    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_settimana);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Clan clan  = Home.c.getClan();

        n = getIntent().getExtras().getInt("settimana");
        int trofei = clan.getCoppeClan();
        int donazioni = clan.getDonazioniTotali()[n];
        int membri = 10;
        int corone = clan.getBauleClan()[n];
        int baule = clan.NBauleClan(corone);

        setTitle("Dettaglio settimana "+n);

        TextView nTrofei = (TextView) findViewById(R.id.trofei_home_number);
        nTrofei.setText(new Integer(trofei).toString());

        TextView nDonazioni = (TextView) findViewById(R.id.donazioni_home_number);
        nDonazioni.setText(new Integer(donazioni).toString());

        TextView nMembri = (TextView) findViewById(R.id.membri_home_number);
        nMembri.setText(new Integer(membri).toString());

        TextView nBaule = (TextView) findViewById(R.id.baule_home_number);
        nBaule.setText(new Integer(baule).toString());
        int a = Integer.parseInt(nBaule.getText().toString());
        ImageView i = (ImageView)findViewById(R.id.baule);
        switch(a)
        {
            case 0:
                i.setImageResource(R.drawable.ic_home_baule);
                break;
            case 1:
                i.setImageResource(R.drawable.ic_home_baule_1);
                break;
            case 2:
                i.setImageResource(R.drawable.ic_home_baule_2);
                break;
            case 3:
                i.setImageResource(R.drawable.ic_home_baule_3);
                break;
            case 4:
                i.setImageResource(R.drawable.ic_home_baule_4);
                break;
            case 5:
                i.setImageResource(R.drawable.ic_home_baule_5);
                break;
            case 6:
                i.setImageResource(R.drawable.ic_home_baule_6);
                break;
            case 7:
                i.setImageResource(R.drawable.ic_home_baule_7);
                break;
            case 8:
                i.setImageResource(R.drawable.ic_home_baule_8);
                break;
            case 9:
                i.setImageResource(R.drawable.ic_home_baule_9);
                break;
            case 10:
                i.setImageResource(R.drawable.ic_home_baule_10);
                break;
        }

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
        final ium.project.clanmanagerforclashroyale.data.ClanManager c = new ium.project.clanmanagerforclashroyale.data.ClanManager();
        final Filtro f = new Filtro();
        c.setnSettimana(n);

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

        ListView l = findViewById(R.id.clan_manager_list);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(DettaglioSettimana.this, DettaglioGiocatore.class);
                in.putExtra("giocatore",i); //i è il numero del giocatore
                startActivity(in);
            }
        });

        final MyAdapter ma = new MyAdapter(this, GiocatoriFactory.getInstance().getAllPlayers(),n,R.layout.layout_list_clan_manager_dettaglio);
        l.setAdapter(ma);

        Button filtro = (Button)findViewById(R.id.filtra);
        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.setFiltro(f);
                ma.clear();
                ma.addAll(c.ApplyFilters());
                ma.notifyDataSetChanged();
            }
        });
    }

}
