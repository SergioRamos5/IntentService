package com.trycatch.intentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button boton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar2);
        boton = findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, IntenteService.class);
                intent.putExtra("iteraciones", 10);
                MainActivity.this.startService(intent);
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(IntenteService.ACTION_PROGRESO);
        filter.addAction(IntenteService.ACTION_FIN);
        ProgressReceiver rcv = new ProgressReceiver();
        registerReceiver(rcv, filter);

    }

    public class ProgressReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(IntenteService.ACTION_PROGRESO))
            {
                int prog = intent.getIntExtra("progreso", 0);
                progressBar.setProgress(prog);
            }
            else if(intent.getAction().equals(IntenteService.ACTION_FIN))
            {
                Toast.makeText(MainActivity.this, "Task Finish", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
