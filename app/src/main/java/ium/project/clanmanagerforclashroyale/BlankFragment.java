package ium.project.clanmanagerforclashroyale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ium.project.clanmanagerforclashroyale.data.Clan;


public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated (View v, Bundle savedInstance)
    {
        Clan clan = MainActivity.c.getClan();

        Integer bauleClan= clan.NBauleClan(clan.getBauleClan()[9]);

        TextView nomeClan =(TextView) v.findViewById(R.id.nome_clan);
        nomeClan.setText(clan.getNome());

        TextView tagClan =(TextView) v.findViewById(R.id.tag_clan);
        tagClan.setText(clan.getTag());

        ImageView logo = (ImageView) v.findViewById(R.id.badge_clan);
        logo.setImageResource(clan.getLogo());

        TextView  trofeiClan = (TextView) v.findViewById(R.id.trofei_home_number);
        trofeiClan.setText(new Integer(clan.getCoppeClan()).toString());

        TextView donazioniClan = (TextView) v.findViewById(R.id.donazioni_home_number);
        donazioniClan.setText(new Integer(clan.getDonazioniTotali()[9]).toString());

        TextView membriClan = (TextView) v.findViewById(R.id.membri_home_number);
        membriClan.setText(new Integer(clan.getnMembri()).toString() );

        ImageView bauli= (ImageView) v.findViewById(R.id.baule);

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
        TextView baule_Clan= (TextView)v.findViewById(R.id.baule_home_number);
        baule_Clan.setText(bauleClan.toString());

        TextView descrizioneClan = (TextView) v.findViewById(R.id.descrizione_home_text);
        descrizioneClan.setText(clan.getDescrizione());

        TextView tipoClan=(TextView)v.findViewById(R.id.tipo_text);
        tipoClan.setText(clan.getTipo());

        TextView posizioneClan=(TextView)v.findViewById(R.id.posizione_text);
        posizioneClan.setText(clan.getPosizione());

        TextView trofeiRichiestiClan=(TextView)v.findViewById(R.id.content_home_clan_richiesti_number);
        trofeiRichiestiClan.setText(new Integer(clan.getTrofeiRichiesti()).toString());
    }
}
