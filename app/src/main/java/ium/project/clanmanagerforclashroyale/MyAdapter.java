package ium.project.clanmanagerforclashroyale;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private FragmentManager fm;

    public List<Giocatore> getL()
    {
        return this.l;
    }

    public MyAdapter(Context context, List<Giocatore> l, int settimana, int layout)
    {
        super(context,layout,l);
        this.context = context;
        this.l = l;
        this.n = settimana;
        this.layout = layout;
    }

    public void setFm(FragmentManager fm)
    {
        this.fm = fm;
    }

    public void setL(List<Giocatore> l)
    {
        this.l = l;
    }

    public View getView(int position, View v, ViewGroup vg)
    {
        LayoutInflater i = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = i.inflate(layout,null);
        final Giocatore g = (Giocatore)getItem(position);
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
        TextView t_numero = (TextView)v.findViewById(R.id.numero);
        t_numero.setText((position + 1)+" ");

        if(this.layout == R.layout.layout_list_clan_manager)
        {
            LinearLayout r = (LinearLayout)v.findViewById(R.id.main);
            if(g.isEspulsione() || g.isRetrocessione())
                r.setBackgroundResource(R.drawable.box_trasparente_lista_rosso);
            else if(g.isPromozione() && !g.isEspulsione() && !g.isRetrocessione())
                r.setBackgroundResource(R.drawable.box_trasparente_lista_verde);
            else if(!g.isPromozione() && !g.isEspulsione() && !g.isRetrocessione())
                ;

            this.notifyDataSetChanged();

            Button positivo = (Button)v.findViewById(R.id.positivo);
            Button negativo = (Button)v.findViewById(R.id.negativo);

            if(g.getGrado().equals("Capo")) {
                positivo.setVisibility(View.INVISIBLE);
                negativo.setVisibility(View.INVISIBLE);
            }
            else if(g.getGrado().equals("Anziano") || g.getGrado().equals("Co-capo"))
            {
                if(g.getGrado().equals("Co-capo"))
                    positivo.setVisibility(View.INVISIBLE);
                negativo.setText("Retrocedi");
            }

            positivo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConfermaAzione c = new ConfermaAzione();
                    c.setUtente(g.getNome());
                    c.setGrado("promuovere");
                    c.setG(g);
                    c.setA(MyAdapter.this);
                    c.show(fm,"Conferma azione");
                }
            });

            negativo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ConfermaAzione c = new ConfermaAzione();
                    c.setUtente(g.getNome());
                    if(g.getGrado().equals("Anziano") || g.getGrado().equals("Co-capo"))
                        c.setGrado("retrocedere");
                    else
                        c.setGrado("espellere");
                    c.setG(g);
                    c.setA(MyAdapter.this);
                    c.show(fm,"Conferma azione");
                }
            });

        }

        return v;
    }
}
