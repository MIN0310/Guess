package com.min.guess;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String TAG = MainActivity.class.getSimpleName();
    int secret = new Random().nextInt(10)+1;
    int counter;
    private TextView number;
    private ImageView result;
    private TextView edCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "secret: " + secret);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        number = findViewById(R.id.guess_number);
        result = findViewById(R.id.smile_image);
        edCounter = findViewById(R.id.counter);
        edCounter.setText(counter + "");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                result.setVisibility(View.GONE);
            }
        });
    }

    private void reset() {
        secret = new Random().nextInt(10)+1;
        Log.d(TAG, "secret: " + secret);
        counter = 0;
        edCounter.setText(counter + "");
    }

    public void guess(View view) {
        int num = Integer.parseInt(number.getText().toString());
        result.setVisibility(View.VISIBLE);
        result.setAlpha(1.0f);
        counter++;
        edCounter.setText(counter + "");

        if (num == secret){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("你的數字")
                    .setMessage("答對了!!你猜了" + counter +"次")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            reset();
                        }
                    })
                    .show();
            result.setImageResource(R.drawable.bomb);
        } else if (num > secret) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("你的數字")
                    .setMessage("再小")
                    .setPositiveButton("OK", null)
                    .show();
            result.setImageResource(R.drawable.smile);
            result.animate().alpha(0.0f).setDuration(1200);
        } else if (num < secret) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("你的數字")
                    .setMessage("再大")
                    .setPositiveButton("OK", null)
                    .show();
            result.setImageResource(R.drawable.smile);
            result.animate().alpha(0.0f).setDuration(1200);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
