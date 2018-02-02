package com.neos.trackandroll.service.notifications;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.neos.trackandroll.R;

public class NotificationHandler {

    private static NotificationHandler notificationHandler;
    private static NotificationManager notificationManager;

    /**
     * The constructor of the singleton
     * @param context
     * @return
     */
    public static NotificationHandler getInstance(Context context){
        if(notificationHandler == null){
            notificationHandler = new NotificationHandler();
            notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationHandler;
    }

    /**
     * Method that creates a simple notification
     * @param context : the context
     * @param notificationTitle : the notification title
     * @param notificationText : the notification body
     */
    public void createNotification(Context context, String notificationTitle, String notificationText){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_logo)
                .setContentTitle(notificationTitle)
                .setContentText(notificationText)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        notificationManager.notify(1, mBuilder.build());

    }

}
