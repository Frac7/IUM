package ium.project.clanmanagerforclashroyale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.List;

import ium.project.clanmanagerforclashroyale.data.Giocatore;

/**
 * Created by Asus on 29/01/2018.
 */

public class MyAdapter extends ArrayAdapter {

    private Context context;
    private List<Giocatore> l;
    private int n;
    private int layout;

    public MyAdapter(Context context, List<Giocatore> l, int settimana, int layout)
    {
        super(context,layout,l);
        this.context = context;
        this.l = l;
        this.n = settimana;
        this.layout = layout;

    }

    public View getView(int position, View v, ViewGroup vg)
    {
        LayoutInflater i = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = i.inflate(layout,null);
        Giocatore g = (Giocatore)getItem(position);
        TextView t_nome = (TextView)v.findViewById(R.id.nome);
        t_nome.setText(g.getNome());
        TextView t_grado = (TextView)v.findViewById(R.id.grado);
        t_grado.setText(g.getGrado());
        TextView t_corone = (TextView)v.findViewById(R.id.n_corone);
        t_corone.setText(new Integer(g.getCoppeBaule()[n]).toString());
        TextView t_donazioni = (TextView)v.findViewById(R.id.n_donazioni);
        t_donazioni.setText(new Integer(g.getDonazioni()[n]).toString());
        TextView t_trofei = (TextView)v.findViewById(R.id.n_trofei);
        t_trofei.setText(new Integer(g.getCorone()).toString());
        return v;
    }
}
