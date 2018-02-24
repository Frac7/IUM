package ium.project.clanmanagerforclashroyale;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ium.project.clanmanagerforclashroyale.data.*;

public class ConfermaAzione extends DialogFragment {

    private String utente = "";
    private String grado = "";
    private Giocatore g = null;
    private MyAdapter a = null;
    private Animation anim = null;

    public void setA(MyAdapter a)
    {
        this.a = a;
    }

    public void setUtente(String s)
    {
        this.utente = s;
    }

    public void setGrado(String s)
    {
        this.grado = s;
    }

    public void setG(Giocatore g)
    {
        this.g = g;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Remove title bar
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_conferma_azione_, container, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        getDialog().setTitle("Conferma azione");

        int titleDividerId = getResources().getIdentifier("titleDivider", "id", "android");
        View titleDivider = getDialog().findViewById(titleDividerId);
        if (titleDivider != null)
            titleDivider.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        anim = AnimationUtils.loadAnimation(a.getContext(), R.anim.animazione);

        TextView t = (TextView)view.findViewById(R.id.msg);
        t.setText("Vuoi davvero "+grado+" "+utente+"?");

        final Button annulla = (Button)view.findViewById(R.id.negativo);
        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                annulla.startAnimation(anim);
                dismiss();
            }
        });

        final Button conferma = (Button)view.findViewById(R.id.positivo);
        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conferma.startAnimation(anim);
                if(grado.equals("promuovere"))
                {
                    g.Promozione(true);
                    g.setPromozione(false);
                }
                else if(grado.equals("retrocedere"))
                {
                    g.Retrocessione(true);
                    g.setRetrocessione(false);
                }
                else if(grado.equals("espellere")) {
                    a.getL().remove(g);
                    GiocatoriFactory.getInstance().RemovePlayer(g);
                }

                a.notifyDataSetChanged();
                dismiss();
                Toast toast = Toast.makeText(a.getContext(),"Modifica effettuata",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}
