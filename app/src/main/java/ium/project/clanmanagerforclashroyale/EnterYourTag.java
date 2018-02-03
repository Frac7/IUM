package ium.project.clanmanagerforclashroyale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class EnterYourTag extends AppCompatActivity {

    //DECLARATION
    EditText tag, ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!isConnected(EnterYourTag.this)) buildDialog(EnterYourTag.this).show();
        else {
            //SET VIEW
            setContentView(R.layout.activity_enter_your_tag);

            //SET ID REFERENCES
            tag = findViewById(R.id.tag);
            ID = findViewById(R.id.ID);

            Button sign_in = this.findViewById(R.id.email_sign_in_button);

            sign_in.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkInput()) {
                        //SET CONTROL OF SIGN-UP
                        if ((tag.getText().toString().equals("JV8H923K") && ID.getText().toString().equals("1-1938466"))) {
                            //CALL CONSTRUCT AND SEND INTENT
                            Intent sign = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(sign);
                        }
                    }
                }
            });
        }
    }

    private boolean checkInput() {
        int errors = 0;
        if (tag.getText() == null || tag.getText().length() == 0) {
            tag.setError("Tag mancante");
            errors++;
        } else {
            tag.setError(null);
        }
        if (ID.getText() == null || ID.getText().length() == 0) {
            ID.setError("ID mancante");
            errors++;
        } else {
            ID.setError(null);
        }
        return errors == 0;
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Nessuna connessione a internet");
        builder.setMessage("Per continuare è necessaria una connesione a internet. Premere OK per uscire.");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }
}
//TODO: Gestire eventualmente la memorizzazione dei dati