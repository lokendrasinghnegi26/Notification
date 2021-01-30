package com.lokendrasingh.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final String CHANNEL_ID= "Personal Information";
    private final int NOTIFICATION_ID= 001;

    Button btnnotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnnotification= (Button)findViewById(R.id.Notification);
       btnnotification.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               createNotificationChannel();
               // after tap notification which UI will be shown
               Intent intent = new Intent(MainActivity.this, MainActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

               NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                       .setSmallIcon(R.drawable.noti)
                       .setContentTitle("Notification")
                       .setContentText("You have a message")
                       .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                       .setContentIntent(pendingIntent)
                       .setAutoCancel(true);        //automatically removes the notification when the user taps it.


               NotificationManager notificationManager= (NotificationManager)
                       getSystemService(NOTIFICATION_SERVICE);
               notificationManager.notify(0,builder.build());
           }
       });
    }
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "personal information";
            String description = "Include all personal notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}