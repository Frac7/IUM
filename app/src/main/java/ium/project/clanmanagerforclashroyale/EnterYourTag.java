package ium.project.clanmanagerforclashroyale;

import android.content.Intent;
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
                    if ((tag.getText().toString().equals("JV8H923K") && ID.getText().toString().equals("1-1938466")))
                    {
                        //CALL CONSTRUCT AND SEND INTENT
                        Intent sign = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(sign);
                    }
                }
            }
        });
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
}
//TODO: Gestire eventualmente la memorizzazione dei dati