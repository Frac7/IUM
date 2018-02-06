package ium.project.clanmanagerforclashroyale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


public class EnterYourTag extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener{

    //DECLARATION
    EditText tag, ID;
    private CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private Animation anim = null;

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
            rem_userpass = findViewById(R.id.checkBoxRem);
            sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animazione);

            final Button sign_in = this.findViewById(R.id.email_sign_in_button);

            View.OnClickListener click = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    sign_in.startAnimation(anim);
                    if (checkInput()) {
                        //SET CONTROL OF SIGN-UP
                        if ((tag.getText().toString().equals("JV8H923K") && ID.getText().toString().equals("1-1938466"))) {
                            //CALL CONSTRUCT AND SEND INTENT
                            Intent sign = new Intent(getApplicationContext(), MainActivity.class);
                            finish();
                            startActivity(sign);
                        }else{
                            if(tag.getText().toString().length() != 8){
                                tag.setError("Lunghezza tag errata");
                            }else{
                                tag.setError("Il tag inserito non appartiene ad un amministratore clan");
                            }
                            if(ID.getText().toString().length() != 9){
                                ID.setError("Lunghezza ID errata");
                            }else{
                                ID.setError("L'ID non è associato al tag inserito");
                            }

                        }
                    }
                }
            };

            Bundle extras = getIntent().getExtras();

            if (extras == null) {
                //SE SEI QUI DENTRO VUOL DIRE CHE NON HAI PREMUTO ESCI NEL MENU

                if(sharedPreferences.getBoolean(KEY_REMEMBER, false)){
                    rem_userpass.setChecked(true);
                }else{
                    rem_userpass.setChecked(false);
                }

                tag.setText(sharedPreferences.getString(KEY_USERNAME, ""));
                ID.setText(sharedPreferences.getString(KEY_PASS, ""));

                tag.addTextChangedListener(this);
                ID.addTextChangedListener(this);
                rem_userpass.setOnCheckedChangeListener(this);

                if ((tag.getText().toString().equals("JV8H923K") && ID.getText().toString().equals("1-1938466"))) {
                    //CALL CONSTRUCT AND SEND INTENT
                    Intent sign = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(sign);
                }

                sign_in.setOnClickListener(click);

            }
            else{
                //SE SEI QUI DENTRO VUOL DIRE CHE HAI PREMUTO ESCI NEL MENU LATERALE
                rem_userpass.setChecked(false);
                managePrefs();
                sign_in.setOnClickListener(click);
            }
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

    private void managePrefs(){
        if(rem_userpass.isChecked()){
            editor.putString(KEY_USERNAME, tag.getText().toString().trim());
            editor.putString(KEY_PASS, ID.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);
            editor.remove(KEY_USERNAME);
            editor.apply();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        managePrefs();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        managePrefs();
    }
}