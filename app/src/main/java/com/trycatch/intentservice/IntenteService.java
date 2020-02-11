package com.trycatch.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class IntenteService extends IntentService {

    public static final String ACTION_PROGRESO = "com.trycatch.intentservice.PROGRESO";
    public static final String ACTION_FIN = "com.trycatch.intentservice.FIN";

    public IntenteService(String name) {
        super(name);
    }
    public IntenteService(){super("");}

    @Override
    protected void onHandleIntent(Intent intent) {
        int progreso = intent.getIntExtra("iteraciones", 0);
        for (int i = 1; i <= progreso; i++)
        {
            tareaLarga();

            Intent bcIntent = new Intent();
            bcIntent.setAction(ACTION_PROGRESO);
            bcIntent.putExtra("progreso", i*10);
            sendBroadcast(bcIntent);
        }

        Intent bcIntent = new Intent();
        bcIntent.setAction(ACTION_FIN);
        sendBroadcast(bcIntent);
    }

    private void tareaLarga()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
