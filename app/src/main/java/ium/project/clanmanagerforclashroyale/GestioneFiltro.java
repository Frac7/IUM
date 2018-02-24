package ium.project.clanmanagerforclashroyale;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ium.project.clanmanagerforclashroyale.data.*;
import ium.project.clanmanagerforclashroyale.data.ClanManager;

public class GestioneFiltro extends DialogFragment {

    public void setP(View p)
    {
        this.precedente = p;
    }

    private View precedente = null;

    private Animation anim = null;

    private int n;

    private Filtro f = new Filtro ();

    private MyAdapter a = null;

    public void setN(int n)
    {
        this.n = n;
    }

    public void setA(MyAdapter a)
    {
        this.a = a;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_gestione_filtro, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        final EditText minCorone, maxCorone;
        final EditText minDonazioni, maxDonazioni;
        final EditText minTrofei, maxTrofei;

        getDialog().setTitle("Filtra membri");

        int titleDividerId = getResources().getIdentifier("titleDivider", "id", "android");
        View titleDivider = getDialog().findViewById(titleDividerId);
        if (titleDivider != null)
            titleDivider.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        minCorone = (EditText)view.findViewById(R.id.min_corone);
        maxCorone = (EditText)view.findViewById(R.id.max_corone);

        minDonazioni = (EditText)view.findViewById(R.id.min_donazioni);
        maxDonazioni = (EditText)view.findViewById(R.id.max_donazioni);

        minTrofei = (EditText)view.findViewById(R.id.min_trofei);
        maxTrofei = (EditText)view.findViewById(R.id.max_trofei);

        final TextView t = view.findViewById(R.id.errore);
        t.setVisibility(View.GONE);

        anim = AnimationUtils.loadAnimation(a.getContext(), R.anim.animazione);

        final LinearLayout parametri = precedente.findViewById(R.id.parametri);

        final LinearLayout corone = precedente.findViewById(R.id.parametri_corone);
        corone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f.setMinCoroneBaule(0);
                f.setMaxCoroneBaule(Integer.MAX_VALUE);

                corone.startAnimation(anim);

                ClanManager c = new ClanManager();
                c.setnSettimana(n);
                c.setFiltro(f);
                List<Giocatore> data = c.ApplyFilters();
                a.getL().removeAll(a.getL());
                a.getL().addAll(data);

                a.notifyDataSetChanged();

                Toast t = Toast.makeText(a.getContext(), "Filtro corone rimosso",Toast.LENGTH_SHORT);
                t.show();

                corone.setVisibility(View.GONE);
            }
        });

        final LinearLayout donazioni = precedente.findViewById(R.id.parametri_donazioni);
        donazioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f.setMinDonazioni(0);
                f.setMaxDonazioni(Integer.MAX_VALUE);

                donazioni.startAnimation(anim);

                ClanManager c = new ClanManager();
                c.setnSettimana(n);
                c.setFiltro(f);
                List<Giocatore> data = c.ApplyFilters();
                a.getL().removeAll(a.getL());
                a.getL().addAll(data);

                a.notifyDataSetChanged();

                Toast t = Toast.makeText(a.getContext(), "Filtro donazioni rimosso",Toast.LENGTH_SHORT);
                t.show();

                donazioni.setVisibility(View.GONE);
            }
        });

        final LinearLayout trofei = precedente.findViewById(R.id.parametri_trofei);
        trofei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f.setMinTrofei(0);
                f.setMaxTrofei(Integer.MAX_VALUE);

                trofei.startAnimation(anim);

                ClanManager c = new ClanManager();
                c.setnSettimana(n);
                c.setFiltro(f);
                List<Giocatore> data = c.ApplyFilters();
                a.getL().removeAll(a.getL());
                a.getL().addAll(data);

                a.notifyDataSetChanged();

                Toast t = Toast.makeText(a.getContext(), "Filtro trofei rimosso",Toast.LENGTH_SHORT);
                t.show();

                trofei.setVisibility(View.GONE);
            }
        });

        final Button normale = view.findViewById(R.id.pulisci_filtro);
        normale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                normale.startAnimation(anim);

                ClanManager c = new ClanManager();
                c.setnSettimana(n);
                c.setFiltro(new Filtro());
                List<Giocatore> data = c.ApplyFilters();
                a.getL().removeAll(a.getL());
                a.getL().addAll(data);

                a.notifyDataSetChanged();
                dismiss();
                Toast t = Toast.makeText(a.getContext(), "Lista ripristinata",Toast.LENGTH_SHORT);
                t.show();

                parametri.setVisibility(View.GONE);

                donazioni.setVisibility(View.GONE);
                trofei.setVisibility(View.GONE);
                corone.setVisibility(View.GONE);


            }
        });

        final Button filtra = (Button)view.findViewById(R.id.filtra);
        filtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtra.startAnimation(anim);

                int i = 0;

                parametri.setVisibility(View.VISIBLE);

                String s = "";

                if(!minCorone.getText().toString().equals("")) {
                    f.setMinCoroneBaule(Integer.parseInt(minCorone.getText().toString()));
                    s += "min_corone; ";
                }
                else
                {
                    f.setMinCoroneBaule(0);
                    i++;
                }
                if(!maxCorone.getText().toString().equals(""))
                {
                    f.setMaxCoroneBaule(Integer.parseInt(maxCorone.getText().toString()));
                    s += "max_corone; ";
                }
                else
                {
                    f.setMaxCoroneBaule(Integer.MAX_VALUE);
                    i++;
                }
                if(!minDonazioni.getText().toString().equals("")) {
                    f.setMinDonazioni(Integer.parseInt(minDonazioni.getText().toString()));
                    s += "min_donazioni; ";
                }
                else
                {
                    i++;
                    f.setMinDonazioni(0);
                }
                if(!maxDonazioni.getText().toString().equals(""))
                {
                    f.setMaxDonazioni(Integer.parseInt(maxDonazioni.getText().toString()));
                    s += "max_donazioni; ";
                }
                else
                {
                    i++;
                    f.setMaxDonazioni(Integer.MAX_VALUE);
                }
                if(!minTrofei.getText().toString().equals(""))
                {
                    f.setMinTrofei(Integer.parseInt(minTrofei.getText().toString()));
                    s += "min_trofei; ";
                }
                else
                {
                    i++;
                    f.setMinTrofei(0);
                }
                if(!maxTrofei.getText().toString().equals(""))
                {
                    f.setMaxTrofei(Integer.parseInt(maxTrofei.getText().toString()));
                    s += "max_trofei; ";
                }
                else
                {
                    i++;
                    f.setMaxTrofei(Integer.MAX_VALUE);
                }

                int j = 0;

                if(f.getMaxCoroneBaule() < f.getMinCoroneBaule()) {
                    j++;
                    maxCorone.setError("");
                }
                if(f.getMaxDonazioni() < f.getMinDonazioni()) {
                    j++;
                    maxDonazioni.setError("");
                }
                if(f.getMaxTrofei() < f.getMinTrofei()) {
                    j++;
                    maxTrofei.setError("");
                }

                if(j != 0)
                {
                    t.setVisibility(View.VISIBLE);
                    t.setText("MAX minore di MIN");
                }
                else if(i == 6)
                {
                    t.setVisibility(View.VISIBLE);
                    minCorone.setError("");
                    maxCorone.setError("");
                    minDonazioni.setError("");
                    maxDonazioni.setError("");
                    minTrofei.setError("");
                    maxTrofei.setError("");
                }
                else
                {
                    ClanManager c = new ClanManager();
                    c.setnSettimana(n);
                    c.setFiltro(f);
                    List<Giocatore> data = c.ApplyFilters();
                    a.getL().removeAll(a.getL());
                    a.getL().addAll(data);

                    a.notifyDataSetChanged();
                    dismiss();
                    if(data.size() != 0) {
                        Toast t = Toast.makeText(a.getContext(), "Lista filtrata",Toast.LENGTH_SHORT);
                        t.show();
                    }
                    else
                    {
                        Toast t = Toast.makeText(a.getContext(), "Nessun risultato",Toast.LENGTH_LONG);
                        t.show();
                    }

                    ArrayList<String> app = new ArrayList<>();
                    String[] arrayString = s.split("; ");
                    for(i = 0; i < arrayString.length; i++)
                    {
                        app.add(arrayString[i]);
                    }

                        if(app.contains("min_corone") || app.contains("max_corone"))
                        {
                            corone.setVisibility(View.VISIBLE);
                            TextView tv = precedente.findViewById(R.id.corone);
                            if(f.getMaxCoroneBaule() == Integer.MAX_VALUE)
                                tv.setText(f.getMinCoroneBaule()+" - ∞");
                            else
                                tv.setText(f.getMinCoroneBaule()+" - "+f.getMaxCoroneBaule());
                        }
                        else
                        {
                            corone.setVisibility(View.GONE);
                        }

                        if(app.contains("min_donazioni") || app.contains("max_donazioni"))
                        {
                            donazioni.setVisibility(View.VISIBLE);
                            TextView tv = precedente.findViewById(R.id.donazioni);
                            if(f.getMaxDonazioni() == Integer.MAX_VALUE)
                                tv.setText(f.getMinDonazioni()+" - ∞");
                            else
                                tv.setText(f.getMinDonazioni()+" - "+f.getMaxDonazioni());
                        }
                        else
                        {
                            donazioni.setVisibility(View.GONE);
                        }

                        if(app.contains("min_trofei") || app.contains("max_trofei"))
                        {
                            trofei.setVisibility(View.VISIBLE);
                            TextView tv = precedente.findViewById(R.id.trofei);
                            if(f.getMaxTrofei() == Integer.MAX_VALUE)
                                tv.setText(f.getMinTrofei()+" - ∞");
                            else
                                tv.setText(f.getMinTrofei()+" - "+f.getMaxTrofei());
                        }
                        else
                        {
                            trofei.setVisibility(View.GONE);
                        }
                }
            }
        });

        final Button annulla = (Button)view.findViewById(R.id.annulla);
        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                annulla.startAnimation(anim);
                dismiss();
            }
        });
    }
}
