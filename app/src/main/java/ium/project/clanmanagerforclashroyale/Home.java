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
import android.widget.TextView;
import android.widget.ImageView;

import ium.project.clanmanagerforclashroyale.data.Clan;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static ium.project.clanmanagerforclashroyale.data.ClanManager c = new ium.project.clanmanagerforclashroyale.data.ClanManager();
    //TODO: spostrare "Clan clan = new Clan (); in splash screen e cambiare tutti i riferimenti nelle altre schermate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Variabile di tipo clan

        Clan clan = c.getClan();

        Integer bauleClan= clan.NBauleClan(clan.getBauleClan()[9]);

        TextView nomeClan =(TextView) findViewById(R.id.nome_clan);
        nomeClan.setText(clan.getNome());

        TextView tagClan =(TextView) findViewById(R.id.tag_clan);
        tagClan.setText(clan.getTag());

        ImageView logo= (ImageView) findViewById(R.id.badge_clan);
        logo.setImageResource(clan.getLogo());

        TextView  trofeiClan = (TextView) findViewById(R.id.trofei_home_number);
        trofeiClan.setText(new Integer(clan.getCoppeClan()).toString());

        TextView donazioniClan = (TextView) findViewById(R.id.donazioni_home_number);
        donazioniClan.setText(new Integer(clan.getDonazioniTotali()[9]).toString());

        TextView membriClan = (TextView) findViewById(R.id.membri_home_number);
        membriClan.setText(new Integer(clan.getnMembri()).toString() );

        ImageView bauli= (ImageView) findViewById(R.id.baule);

        switch (bauleClan){
            case 0: bauli.setImageResource(R.drawable.ic_home_baule);
                break;
            case 1: bauli.setImageResource(R.drawable.ic_home_baule_1);
                break;
            case 2: bauli.setImageResource(R.drawable.ic_home_baule_2);
                break;
            case 3: bauli.setImageResource(R.drawable.ic_home_baule_3);
                break;
            case 4: bauli.setImageResource(R.drawable.ic_home_baule_4);
                break;
            case 5: bauli.setImageResource(R.drawable.ic_home_baule_5);
                break;
            case 6: bauli.setImageResource(R.drawable.ic_home_baule_6);
                break;
            case 7: bauli.setImageResource(R.drawable.ic_home_baule_7);
                break;
            case 8: bauli.setImageResource(R.drawable.ic_home_baule_8);
                break;
            case 9: bauli.setImageResource(R.drawable.ic_home_baule_9);
                break;
            case 10: bauli.setImageResource(R.drawable.ic_home_baule_10);
                break;

        }
        TextView baule_Clan= (TextView)findViewById(R.id.baule_home_number);
        baule_Clan.setText(bauleClan.toString());

        TextView descrizioneClan = (TextView) findViewById(R.id.descrizione_home_text);
        descrizioneClan.setText(clan.getDescrizione());

        TextView tipoClan=(TextView)findViewById(R.id.tipo_text);
        tipoClan.setText(clan.getTipo());

        TextView posizioneClan=(TextView)findViewById(R.id.posizione_text);
        posizioneClan.setText(clan.getPosizione());

        TextView trofeiRichiestiClan=(TextView)findViewById(R.id.content_home_clan_richiesti_number);
        trofeiRichiestiClan.setText(new Integer(clan.getTrofeiRichiesti()).toString());
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
            ;
        } else if (id == R.id.nav_clanmanager) {
            i = new Intent(Home.this, ClanManager.class);
        } else if (id == R.id.nav_esci) {
            i = new Intent(Home.this, EnterYourTag.class);
        } else if (id == R.id.nav_info) {
            i = new Intent(Home.this, Informazioni.class);
        } else if (id == R.id.nav_registro) {
            i = new Intent(Home.this, Registro.class);
        } else if (id == R.id.nav_regole) {
            i = new Intent(Home.this, Suggeritore.class);
        }

        if(i != null)
            startActivity(i);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
