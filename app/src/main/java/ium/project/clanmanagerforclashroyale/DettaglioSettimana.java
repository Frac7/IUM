package ium.project.clanmanagerforclashroyale;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ium.project.clanmanagerforclashroyale.data.Clan;

public class DettaglioSettimana extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_settimana);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Clan clan  = new Clan();
        int n = getIntent().getExtras().getInt("settimana");
        int trofei = clan.getCoppeClan();
        int donazioni = clan.getDonazioniTotali()[n];
        int membri = 10;
        int corone = clan.getBauleClan()[n];
        int baule = Registro.nBaule(corone);

        TextView nSettimana = (TextView) findViewById(R.id.n);
        nSettimana.setText(new Integer(n).toString());

        TextView nTrofei = (TextView) findViewById(R.id.trofei_home_number);
        nTrofei.setText(new Integer(trofei).toString());

        TextView nDonazioni = (TextView) findViewById(R.id.donazioni_home_number);
        nDonazioni.setText(new Integer(donazioni).toString());

        TextView nMembri = (TextView) findViewById(R.id.membri_home_number);
        nMembri.setText(new Integer(membri).toString());

        TextView nBaule = (TextView) findViewById(R.id.baule_home_number);
        nBaule.setText(new Integer(baule).toString());

        EditText minCorone, maxCorone;
        EditText minDonazioni, maxDonazioni;
        EditText minTrofei, maxTrofei;

        //recuperare le view e impostare i filtri per la lista. se le view sono vuote mostrare la lista di default. tenere conto della settimana. copia e incolla per clan manager



    }

}
