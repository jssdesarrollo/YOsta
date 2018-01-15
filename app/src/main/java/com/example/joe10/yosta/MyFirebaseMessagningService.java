package com.example.joe10.yosta;

/**
 * Created by joe10 on 28/06/2016.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by joe10 on 20/06/2016.
 */

public class MyFirebaseMessagningService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);
        //Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        //v.vibrate(500);
        Log.e("FIREBASE", remoteMessage.getNotification().getBody());
        String ab = remoteMessage.getData().toString();
        Log.e("datos", ab);
        Log.e("TAG", "Notification Click Action: " + remoteMessage.getNotification().getClickAction());
        //showNotification(remoteMessage.getData().get("click_action"));

    }
    /*private void showNotification(String sms){
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("FCM test")
                .setContentText(sms)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());

    }*/
}

