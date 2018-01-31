package ium.project.clanmanagerforclashroyale;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ium.project.clanmanagerforclashroyale.data.Giocatore;

public class ConfermaAzione extends DialogFragment {

    private String utente = "";
    private String grado = "";
    private Giocatore g = null;

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
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Conferma azione");
        builder.setMessage("Vuoi davvero "+grado+" "+utente+"?");

        builder.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(grado.equals("promuovere"))
                    g.Promozione(true);
                else
                    g.Retrocessione(true);
                dismiss();
            }
        });



        builder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }


}
