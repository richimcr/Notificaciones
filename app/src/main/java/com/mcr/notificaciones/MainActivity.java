package com.mcr.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btNotificacion;
    private NotificationCompat.Builder notification;
    private static int codigo = 12345;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notification = new NotificationCompat.Builder(this,"10");
        notification.setAutoCancel(true);


        btNotificacion=(Button) findViewById(R.id.btNotificacion);
        btNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               createNotificationChannel();

               notification = new NotificationCompat.Builder(getApplicationContext(), "1")
                       .setSmallIcon(R.mipmap.ic_launcher_round)
                       .setContentTitle("Soy una notificacion")
                       .setContentText("Soy la primera notificacion")
                       .setAutoCancel(true)
                       .setStyle(new NotificationCompat.BigTextStyle().bigText("Mucho mas texto agregado a la notificacion"))
                       .setPriority(NotificationCompat.PRIORITY_HIGH);



                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent);
                NotificationManager  notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(codigo,notification.build());
                Toast.makeText(getApplicationContext(),"Hola",Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "MiCanal",NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Canal de prueba");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }



}
