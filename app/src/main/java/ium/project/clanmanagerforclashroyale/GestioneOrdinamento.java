package ium.project.clanmanagerforclashroyale;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ium.project.clanmanagerforclashroyale.data.ClanManager;
import ium.project.clanmanagerforclashroyale.data.Giocatore;

public class GestioneOrdinamento extends DialogFragment {

    private View precedente = null;

    private Animation anim = null;

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

    public void setP(View p)
    {
        this.precedente = p;
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
        t.setVisibility(View.GONE);

        anim = AnimationUtils.loadAnimation(a.getContext(), R.anim.animazione);

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

        final Button crescente = (Button)view.findViewById(R.id.crescente);
        crescente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //crescente.startAnimation(anim);

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

                    LinearLayout l = precedente.findViewById(R.id.parametri);
                    l.setVisibility(View.VISIBLE);

                    LinearLayout ll = precedente.findViewById(R.id.parametri_ordinamento);
                    ll.setVisibility(View.VISIBLE);

                    TextView tv = precedente.findViewById(R.id.ordinamento);
                    ImageView iv = precedente.findViewById(R.id.ordinamento_img);

                    tv.setText("Crescente");

                    if(corone.isChecked())
                        iv.setImageResource(R.drawable.ic_home_corone_nospace);
                    if(donazioni.isChecked())
                        iv.setImageResource(R.drawable.ic_home_donazioni_nospace);
                    if(trofei.isChecked())
                        iv.setImageResource(R.drawable.ic_home_trofei_nospace);


                }else
                {
                    t.setVisibility(View.VISIBLE);
                    corone.setError("");
                    donazioni.setError("");
                    trofei.setError("");
                }
            }
        });

        final Button decrescente = view.findViewById(R.id.decrescente);
        decrescente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //decrescente.startAnimation(anim);

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

                    LinearLayout l = precedente.findViewById(R.id.parametri);
                    l.setVisibility(View.VISIBLE);

                    LinearLayout ll = precedente.findViewById(R.id.parametri_ordinamento);
                    ll.setVisibility(View.VISIBLE);

                    TextView tv = precedente.findViewById(R.id.ordinamento);
                    ImageView iv = precedente.findViewById(R.id.ordinamento_img);

                    tv.setText("Decrescente");

                    if(corone.isChecked())
                        iv.setImageResource(R.drawable.ic_home_corone_nospace);
                    if(donazioni.isChecked())
                        iv.setImageResource(R.drawable.ic_home_donazioni_nospace);
                    if(trofei.isChecked())
                        iv.setImageResource(R.drawable.ic_home_trofei_nospace);
                }else
                {
                    t.setVisibility(View.VISIBLE);
                    corone.setError("");
                    donazioni.setError("");
                    trofei.setError("");
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
