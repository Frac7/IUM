package ium.project.clanmanagerforclashroyale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import ium.project.clanmanagerforclashroyale.data.ClanManager;
import ium.project.clanmanagerforclashroyale.data.Giocatore;

import java.util.List;

public class BlankFragment2 extends Fragment {

    private static MyAdapter ma = null;

    public static MyAdapter getAdapter()
    {
        return ma;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.filtra) {
            android.support.v4.app.FragmentManager fm = getFragmentManager();
            GestioneFiltro g = new GestioneFiltro();
            g.setA(BlankFragment2.getAdapter());
            g.setN(9);
            g.show(fm,"Filtra membri per:");
        }

        if (id == R.id.ordina) {
            android.support.v4.app.FragmentManager fm = getFragmentManager();
            GestioneOrdinamento g = new GestioneOrdinamento();
            g.setA(BlankFragment2.getAdapter());
            g.setN(9);
            g.show(fm,"Ordina membri per:");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstance)
    {
        ClanManager c = MainActivity.c;

        c.setnSettimana(9);

        ListView l = v.findViewById(R.id.clan_manager_list);

        List<Giocatore> data = c.ApplyFilters();

        ma = new MyAdapter(getContext(), data,9,R.layout.layout_list_clan_manager);
        ma.setFm(getFragmentManager());
        l.setAdapter(ma);
    }
}
