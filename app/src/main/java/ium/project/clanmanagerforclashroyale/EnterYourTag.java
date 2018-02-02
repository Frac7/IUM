package ium.project.clanmanagerforclashroyale;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;


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

                //SET CONTROL OF SIGN-UP
                //if(   (tag.getText().toString().equals("JV8H923K") && ID.getText().toString().equals("1-1938466"))
                //        ||(tag.getText().toString().equals("KV86923L") && ID.getText().toString().equals("2-7373748"))
                //        ||(tag.getText().toString().equals("SV87453L") && ID.getText().toString().equals("2-8274739"))
                //        ||(tag.getText().toString().equals("BG2k405L") && ID.getText().toString().equals("1-7485920"))
                //        ||(tag.getText().toString().equals("HV97B54F") && ID.getText().toString().equals("2-9545928"))
                //        ){

                //CALL CONSTRUCT AND SEND INTENT
                Intent sign = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(sign);
                //}else{
                //}
            }
        });
    }
}