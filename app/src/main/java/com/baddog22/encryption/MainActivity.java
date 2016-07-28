package com.baddog22.encryption;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    TextView output_textView;
    TextView input_textView;
    RadioButton encrypt;
    RadioButton decrypt;
    TextView password_textView;
    int offset=0;
View.OnLongClickListener longpress = new View.OnLongClickListener()
    {



    @Override
    public boolean onLongClick(View v) {
        Button heldButton = (Button)v;
        String heldButtonText = "";

        switch (((Button) v).getText().toString()){
            case "Compute" :  heldButtonText = "Press to encrypt the input with the password";
                              break;
            case "Clear"   :  heldButtonText = "Clear all fields";
                              break;
            case "Switch Input/Output" : heldButtonText= "Swap your input text with your output text";
                                           break;

        }
        Toast.makeText(getApplicationContext(), heldButtonText, Toast.LENGTH_LONG).show();
        return true;
    }
};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseForm();
    }

    public void initialiseForm()
    {
        Button buttonCompute = (Button)findViewById(R.id.btn_compute);
        Button buttonClear = (Button)findViewById(R.id.btn_clear);
        Button buttonSwitch = (Button)findViewById(R.id.btn_switch);
        buttonClear.setOnLongClickListener(longpress);
        buttonCompute.setOnLongClickListener(longpress);
        buttonSwitch.setOnLongClickListener(longpress);
        output_textView = (TextView)findViewById(R.id.Output);
        input_textView = (TextView)findViewById(R.id.Input);
        encrypt = (RadioButton)findViewById(R.id.rdo_encrypt);
        decrypt = (RadioButton)findViewById(R.id.rdo_decrypt);
        password_textView= (TextView)findViewById(R.id.Password);
        offset= (int)this.getResources().getInteger(R.integer.offset);
    }
    public void onButtonPress(View view)
    {
        int pos;
        if(encrypt.isChecked()!= false || decrypt.isChecked()!= false  )
        {
            if (encrypt.isChecked()==true)
            {
                output_textView.setText(encryptme(input_textView.getText().toString()));
            }
            if (decrypt.isChecked()==true)
            {
                output_textView.setText(decryptme(input_textView.getText().toString()));
            }

        }
        else
        {
            new AlertDialog.Builder(this)
                    .setTitle("Error!")
                    .setMessage("Please check one radio button")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    //function that returns a string - encrypting the input
    private String encryptme(String inp)
    {
        int pos=0;

        String password = password_textView.getText().toString();
        char[] chararray = password.toCharArray();
        String  result = "";
        for(char c : inp.toCharArray())
        {

            if(pos>=password.length())
            {
                pos=0;
                char keychar = chararray[pos];
                result += (char)(c + keychar + offset);
                pos+=1;
            }
            else
            {

                char keychar = chararray[pos];
                result += (char)(c + keychar + offset);
                pos+=1;

            }
        }

        return result;
    }

    //function that returns a string - decrypting the input
    private String decryptme(String inp)
    {
        int pos=0;
        String password = password_textView.getText().toString();
        char[] chararray = password.toCharArray();
        String  result = "";
        for(char c : inp.toCharArray())
        {
            if(pos>=password.length())
            {
                pos=0;
                char keychar = chararray[pos];
                result += (char)(c - keychar );
                pos+=1;
            }
            else {

                char keychar = chararray[pos];
                result += (char) (c - keychar);
                pos += 1;

            }
        }
        return result;
    }

    public void onButtonClearClick(View v){
        output_textView.setText(null);
        input_textView.setText(null);
        password_textView.setText(null);
        encrypt.setChecked(false);
        decrypt.setChecked(false);
        output_textView.clearFocus();
        password_textView.clearFocus();
        input_textView.requestFocus();
    }

    //event listener for when the switch button is pressed - swap input and output text
    public void switchinp(View v)
    {
        output_textView = (TextView)findViewById(R.id.Output);
        input_textView = (TextView)findViewById(R.id.Input);
        input_textView.setText(output_textView.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.menu_main, menu);
        return true;
    }


    //Just a mess around using toasts : displays "name" when options menu item pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings){
            Intent Settings = new Intent(this, SettingsActivity.class);
            this.startActivity(Settings);
        }
        return super.onOptionsItemSelected(item);
    }



}









