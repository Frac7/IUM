package ium.project.clanmanagerforclashroyale;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BlankFragment3 extends Fragment {

    private MainActivity obj;

    public void setParent(MainActivity o)
    {
        this.obj = o;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment3, container, false);
    }

    @Override
    public void onViewCreated (View v, Bundle savedInstance)
    {
        final EditText corone =(EditText) v.findViewById(R.id.corone_MIN_suggeritore);
        final EditText donazioni =(EditText) v.findViewById(R.id.donazioni_MIN_suggeritore);

        if(MainActivity.c.getSuggeritore().getCorone()!=0) {
            corone.setText(new Integer(MainActivity.c.getSuggeritore().getCorone()).toString());
        }

        if(MainActivity.c.getSuggeritore().getDonazioni()!=0) {
            donazioni.setText(new Integer(MainActivity.c.getSuggeritore().getDonazioni()).toString());
        }

        //final Intent clanManager= new Intent(getApplicationContext(),ClanManager.class);
        Button button = (Button) v.findViewById(R.id.confirm_button);
        final Context context = v.getContext();
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view ){
                //Cosa avviene al click di conferma
                //Context context=getApplicationContext();
                int duration= Toast.LENGTH_SHORT;
                CharSequence text ="Suggerimenti applicati con succeeso";


                if(corone.getText().toString().equals("")){
                    MainActivity.c.getSuggeritore().setCorone(0);
                }
                else{
                    MainActivity.c.getSuggeritore().setCorone(Integer.parseInt(corone.getText().toString())); /** Riferimento da cambiare quando spostiamo su splash screen**/
                }


                if(donazioni.getText().toString().equals("")){
                    MainActivity.c.getSuggeritore().setDonazioni(0);
                }
                else{
                    MainActivity.c.getSuggeritore().setDonazioni(Integer.parseInt(donazioni.getText().toString())); /** Riferimento da cambiare quando spostiamo su splash screen**/
                }

                MainActivity.c.setnSettimana(9);
                MainActivity.c.applyHint(); //Applicazione suggerimento
                if(MainActivity.c.getSuggeritore().getCorone() == 0 && MainActivity.c.getSuggeritore().getDonazioni() == 0)
                    text = "Nessun suggerimento applicato";
                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
                obj.changeTab();
                //startActivity(clanManager);

            }
        });
    }
}
