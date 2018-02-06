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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ium.project.clanmanagerforclashroyale.data.*;
import ium.project.clanmanagerforclashroyale.data.ClanManager;

public class GestioneFiltro extends DialogFragment {

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
        t.setVisibility(View.INVISIBLE);

        anim = AnimationUtils.loadAnimation(a.getContext(), R.anim.animazione);

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
            }
        });

        final Button filtra = (Button)view.findViewById(R.id.filtra);
        filtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtra.startAnimation(anim);

                int i = 0;

                if(!minCorone.getText().toString().equals(""))
                    f.setMinCoroneBaule(Integer.parseInt(minCorone.getText().toString()));
                else
                {
                    f.setMinCoroneBaule(0);
                    i++;
                }
                if(!maxCorone.getText().toString().equals(""))
                    f.setMaxCoroneBaule(Integer.parseInt(maxCorone.getText().toString()));
                else
                {
                    f.setMaxCoroneBaule(Integer.MAX_VALUE);
                    i++;
                }
                if(!minDonazioni.getText().toString().equals(""))
                    f.setMinDonazioni(Integer.parseInt(minDonazioni.getText().toString()));
                else {
                    i++;
                    f.setMinDonazioni(0);
                }
                if(!maxDonazioni.getText().toString().equals(""))
                    f.setMaxDonazioni(Integer.parseInt(maxDonazioni.getText().toString()));
                else
                {
                    i++;
                    f.setMaxDonazioni(Integer.MAX_VALUE);
                }
                if(!minTrofei.getText().toString().equals(""))
                    f.setMinTrofei(Integer.parseInt(minTrofei.getText().toString()));
                else
                {
                    i++;
                    f.setMinTrofei(0);
                }
                if(!maxTrofei.getText().toString().equals(""))
                    f.setMaxTrofei(Integer.parseInt(maxTrofei.getText().toString()));
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
                }
            }
        });

        final Button annulla = (Button)view.findViewById(R.id.annulla);
        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                annulla.startAnimation(anim);

                f.setMinCoroneBaule(0);
                f.setMaxCoroneBaule(Integer.MAX_VALUE);
                f.setMinDonazioni(0);
                f.setMaxDonazioni(Integer.MAX_VALUE);
                f.setMinTrofei(0);
                f.setMaxTrofei(Integer.MAX_VALUE);
                dismiss();
            }
        });

        final Button pulisci = (Button)view.findViewById(R.id.pulisci);
        pulisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pulisci.startAnimation(anim);

                minCorone.setText("");
                minDonazioni.setText("");
                minTrofei.setText("");
                maxCorone.setText("");
                maxTrofei.setText("");
                maxDonazioni.setText("");
            }
        });
    }
}
