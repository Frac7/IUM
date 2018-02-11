package ium.project.clanmanagerforclashroyale;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        return inflater.inflate(R.layout.fragment_gestione_filtro, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //settare il titolo

        final EditText minCorone, maxCorone;
        final EditText minDonazioni, maxDonazioni;
        final EditText minTrofei, maxTrofei;

        getDialog().setTitle("Filtra membri");

        minCorone = (EditText)view.findViewById(R.id.min_corone);
        maxCorone = (EditText)view.findViewById(R.id.max_corone);

        minDonazioni = (EditText)view.findViewById(R.id.min_donazioni);
        maxDonazioni = (EditText)view.findViewById(R.id.max_donazioni);

        minTrofei = (EditText)view.findViewById(R.id.min_trofei);
        maxTrofei = (EditText)view.findViewById(R.id.max_trofei);

        final TextView t = view.findViewById(R.id.errore);
        t.setVisibility(View.GONE);

        anim = AnimationUtils.loadAnimation(a.getContext(), R.anim.animazione);

        final Button normale = view.findViewById(R.id.pulisci_filtro);
        normale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //normale.startAnimation(anim);
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
            }
        });

        final Button filtra = (Button)view.findViewById(R.id.filtra);
        filtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //filtra.startAnimation(anim);

                int i = 0;

                LinearLayout l = precedente.findViewById(R.id.parametri);
                l.setVisibility(View.VISIBLE);

                String s = "";

                if(!minCorone.getText().toString().equals("")) {
                    f.setMinCoroneBaule(Integer.parseInt(minCorone.getText().toString()));
                    LinearLayout ll = precedente.findViewById(R.id.parametri_corone);
                    ll.setVisibility(View.VISIBLE);
                    s += "min_corone; ";
                }
                else
                {
                    f.setMinCoroneBaule(0);
                    i++;
                }
                if(!maxCorone.getText().toString().equals("")) {
                    LinearLayout ll = precedente.findViewById(R.id.parametri_corone);
                    ll.setVisibility(View.VISIBLE);
                    f.setMaxCoroneBaule(Integer.parseInt(maxCorone.getText().toString()));
                    s += "max_corone; ";
                }
                else
                {
                    f.setMaxCoroneBaule(Integer.MAX_VALUE);
                    i++;
                }
                if(!minDonazioni.getText().toString().equals("")) {
                    LinearLayout ll = precedente.findViewById(R.id.parametri_donazioni);
                    ll.setVisibility(View.VISIBLE);
                    f.setMinDonazioni(Integer.parseInt(minDonazioni.getText().toString()));
                    s += "min_donazioni; ";
                }
                else {
                    i++;
                    f.setMinDonazioni(0);
                }
                if(!maxDonazioni.getText().toString().equals(""))
                {
                    LinearLayout ll = precedente.findViewById(R.id.parametri_donazioni);
                    ll.setVisibility(View.VISIBLE);
                    f.setMaxDonazioni(Integer.parseInt(maxDonazioni.getText().toString()));
                    s += "max_donazioni; ";
                }
                else
                {
                    i++;
                    f.setMaxDonazioni(Integer.MAX_VALUE);
                }
                if(!minTrofei.getText().toString().equals("")) {
                    LinearLayout ll = precedente.findViewById(R.id.parametri_trofei);
                    ll.setVisibility(View.VISIBLE);
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
                    LinearLayout ll = precedente.findViewById(R.id.parametri_trofei);
                    ll.setVisibility(View.VISIBLE);
                    f.setMaxTrofei(Integer.parseInt(maxTrofei.getText().toString()));
                    s += "max_trofei; ";
                }
                else
                {
                    i++;
                    f.setMaxTrofei(Integer.MAX_VALUE);
                }

                if(i == 6)
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

                    String app[] = s.split("; ");
                    for(i = 0; i < app.length; i++)
                    {
                        if(app[i].equals("min_corone"))
                        {
                            if(i + 1 < app.length && app[i + 1].equals("max_corone"))
                            {
                                TextView tv = precedente.findViewById(R.id.corone);
                                tv.setText(f.getMinCoroneBaule()+" - "+f.getMaxCoroneBaule());
                            }
                            else
                            {
                                TextView tv = precedente.findViewById(R.id.corone);
                                tv.setText(f.getMinCoroneBaule()+" - ∞");
                            }
                        }
                        else if(app[i].equals("max_corone"))
                        {
                            TextView tv = precedente.findViewById(R.id.corone);
                            tv.setText("0 - "+f.getMaxCoroneBaule());
                        }

                        if(app[i].equals("min_donazioni"))
                        {
                            if(i + 1 < app.length && app[i + 1].equals("max_donazioni"))
                            {
                                TextView tv = precedente.findViewById(R.id.corone);
                                tv.setText(f.getMinDonazioni()+" - "+f.getMaxDonazioni());
                            }
                            else
                            {
                                TextView tv = precedente.findViewById(R.id.corone);
                                tv.setText(f.getMinDonazioni()+" - ∞");
                            }
                        }
                        else if(app[i].equals("max_corone"))
                        {
                            TextView tv = precedente.findViewById(R.id.corone);
                            tv.setText("0 - "+f.getMaxDonazioni());
                        }
                        if(app[i].equals("min_trofei"))
                        {
                            if(i + 1 < app.length && app[i + 1].equals("max_trofei"))
                            {
                                TextView tv = precedente.findViewById(R.id.corone);
                                tv.setText(f.getMinTrofei()+" - "+f.getMaxTrofei());
                            }
                            else
                            {
                                TextView tv = precedente.findViewById(R.id.corone);
                                tv.setText(f.getMinTrofei()+" - ∞");
                            }
                        }
                        else if(app[i].equals("max_trofei"))
                        {
                            TextView tv = precedente.findViewById(R.id.corone);
                            tv.setText("0 - "+f.getMaxTrofei());
                        }
                    }

                }
            }
        });

        final Button annulla = (Button)view.findViewById(R.id.annulla);
        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //annulla.startAnimation(anim);
                dismiss();
            }
        });
    }
}
