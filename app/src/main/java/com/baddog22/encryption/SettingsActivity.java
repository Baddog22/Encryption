package com.baddog22.encryption;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
//import android.widget.Toast;


public class SettingsActivity extends Activity {


    public int offset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        offset= (int)this.getResources().getInteger(R.integer.offset);
        NumberPicker np = (NumberPicker)findViewById(R.id.numberPicker);
        np.setMaxValue(5);
       np.setMinValue(0);
        np.setValue(offset);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
           @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String value = String.valueOf(newVal);

               // Toast.makeText(getApplicationContext(),value, Toast.LENGTH_SHORT).show();
            }
        });





        }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }


    public void backButtonClick(View v)
    {

        this.finish();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
