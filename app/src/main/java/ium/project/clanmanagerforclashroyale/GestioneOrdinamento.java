package ium.project.clanmanagerforclashroyale;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ium.project.clanmanagerforclashroyale.data.ClanManager;
import ium.project.clanmanagerforclashroyale.data.Filtro;
import ium.project.clanmanagerforclashroyale.data.Giocatore;

public class GestioneOrdinamento extends DialogFragment {

    private int n;

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
        return inflater.inflate(R.layout.fragment_gestione_ordinamento, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //settare il titolo

        final RadioButton corone, donazioni, trofei;

        getDialog().setTitle("Ordina membri");

        corone = view.findViewById(R.id.corone);
        donazioni = view.findViewById(R.id.donazioni);
        trofei = view.findViewById(R.id.trofei);

        final TextView t = view.findViewById(R.id.errore);
        t.setVisibility(View.INVISIBLE);

        donazioni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                corone.setChecked(false);
                trofei.setChecked(false);
            }
        });

        corone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donazioni.setChecked(false);
                trofei.setChecked(false);
            }
        });

        trofei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donazioni.setChecked(false);
                corone.setChecked(false);
            }
        });

        Button crescente = (Button)view.findViewById(R.id.crescente);
        crescente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ClanManager c = new ClanManager();
                c.setnSettimana(n);
                List<Giocatore> data = new ArrayList<>();
                for(Giocatore g : a.getL())
                    data.add(g);

                String par = "";

                if(corone.isChecked())
                    par = "corone";
                if(donazioni.isChecked())
                    par = "donazioni";
                if(trofei.isChecked())
                    par = "trofei";

                if(!par.equals("")) {
                    if (data.size() != 0) {
                        Collections.sort(data, new Ordina(par,"crescente"));
                        a.getL().removeAll(a.getL());
                        a.getL().addAll(data);

                        a.notifyDataSetChanged();
                        dismiss();

                        Toast t = Toast.makeText(a.getContext(), "Lista ordinata", Toast.LENGTH_SHORT);
                        t.show();
                    } else {
                        Toast t = Toast.makeText(a.getContext(), "Nessun risultato", Toast.LENGTH_LONG);
                        t.show();
                    }
                }else
                {
                    t.setVisibility(View.VISIBLE);
                    corone.setError("");
                    donazioni.setError("");
                    trofei.setError("");
                }
            }
        });

        Button decrescente = view.findViewById(R.id.decrescente);
        decrescente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ClanManager c = new ClanManager();
                c.setnSettimana(n);
                List<Giocatore> data = new ArrayList<>();
                for(Giocatore g : a.getL())
                    data.add(g);

                String par = "";

                if(corone.isChecked())
                    par = "corone";
                if(donazioni.isChecked())
                    par = "donazioni";
                if(trofei.isChecked())
                    par = "trofei";

                if(!par.equals("")) {
                    if (data.size() != 0) {
                        Collections.sort(data, new Ordina(par,"decrescente"));
                        a.getL().removeAll(a.getL());
                        a.getL().addAll(data);

                        a.notifyDataSetChanged();
                        dismiss();

                        Toast t = Toast.makeText(a.getContext(), "Lista ordinata", Toast.LENGTH_SHORT);
                        t.show();
                    } else {
                        Toast t = Toast.makeText(a.getContext(), "Nessun risultato", Toast.LENGTH_LONG);
                        t.show();
                    }
                }else
                {
                    t.setVisibility(View.VISIBLE);
                    corone.setError("");
                    donazioni.setError("");
                    trofei.setError("");
                }
            }
        });

        Button annulla = (Button)view.findViewById(R.id.annulla);
        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Button pulisci = (Button)view.findViewById(R.id.pulisci);
        pulisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                corone.setChecked(false);
                donazioni.setChecked(false);
                trofei.setChecked(false);
            }
        });
    }

    class Ordina implements java.util.Comparator<Giocatore>
    {
        private String p = "";
        private String c = "";

        public Ordina(String par, String crit)
        {
            this.p = par;
            this.c = crit;
        }
        public int compare(Giocatore g, Giocatore g1)
        {
            int r = 0;

            if(p.equals("corone") && c.equals("crescente"))
                r = g.getCoppeBaule()[n] - g1.getCoppeBaule()[n];
            if(p.equals("corone") && c.equals("decrescente"))
                r = - g.getCoppeBaule()[n] + g1.getCoppeBaule()[n];

            if(p.equals("donazioni") && c.equals("crescente"))
                r = g.getDonazioni()[n] - g1.getDonazioni()[n];
            if(p.equals("donazioni") && c.equals("decrescente"))
                r = - g.getDonazioni()[n] + g1.getDonazioni()[n];

            if(p.equals("trofei") && c.equals("crescente"))
                r = g.getCorone() - g1.getCorone();
            if(p.equals("trofei") && c.equals("decrescente"))
                r = - g.getCorone() + g1.getCorone();

            return r;
        }
    }
}
