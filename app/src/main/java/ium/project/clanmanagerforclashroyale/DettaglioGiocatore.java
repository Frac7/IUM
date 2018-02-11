package ium.project.clanmanagerforclashroyale;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import ium.project.clanmanagerforclashroyale.data.Clan;
import ium.project.clanmanagerforclashroyale.data.Giocatore;
import ium.project.clanmanagerforclashroyale.data.GiocatoriFactory;

public class DettaglioGiocatore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_giocatore);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int n = getIntent().getExtras().getInt("giocatore");

        Giocatore g = (Giocatore)GiocatoriFactory.getInstance().getAllPlayers().get(n);

        Clan clan = MainActivity.c.getClan();

        String s = g.getNome();

        setTitle("Dettaglio "+s);
        
        TextView nome, ruolo, tag, media_don, media_cor, perc_don, perc_cor;
        nome = (TextView)findViewById(R.id.nome);
        ruolo = (TextView)findViewById(R.id.ruolo);
        tag = (TextView)findViewById(R.id.tag);
        media_don = (TextView)findViewById(R.id.media_don);
        media_cor = (TextView)findViewById(R.id.media_cor);
        perc_don = (TextView)findViewById(R.id.perc_don);
        perc_cor = (TextView)findViewById(R.id.perc_cor);

        nome.setText(g.getNome());
        ruolo.setText(g.getGrado());
        tag.setText(g.getTag());

        int d = 0;
        int c = 0;
        for(int i = 0; i < 10; i++) {
            d += g.getDonazioni()[i];
            c += g.getCoppeBaule()[i];
        }

        media_don.setText(new Integer(d/10).toString());
        media_cor.setText(new Integer(c/10).toString());

        int clan_d = 0;
        int clan_c = 0;

        for(int i = 0; i < 10; i++) {
            clan_d += clan.getDonazioniTotali()[i];
            clan_c += clan.getBauleClan()[i];
        }

        perc_don.setText((100*d/clan_d)+"%");
        perc_cor.setText((100*c/clan_c)+"%");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DettaglioSettimana.n = getIntent().getExtras().getInt("settimana");
    }
}
