package org.runnerup.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import org.runnerup.R;
import org.runnerup.common.util.Constants;
import org.runnerup.view.MainLayout;


public class GpsBoundState implements NotificationState {
    private final Notification notification;

    public GpsBoundState(Context context) {

        String chanId = NotificationStateManager.getChannelId(context);
        Intent i = new Intent(context, MainLayout.class)
                .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                .putExtra(Constants.Intents.FROM_NOTIFICATION, true);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, chanId)
                .setContentIntent(pi)
                .setContentTitle(context.getString(R.string.Activity_ready))
                .setContentText(context.getString(R.string.Ready_to_start_running))
                .setSmallIcon(R.drawable.ic_stat_notify)
                .setOnlyAlertOnce(true)
                .setLocalOnly(true);
        if (Build.VERSION.SDK_INT >= 21) {
            builder.setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setCategory(Notification.CATEGORY_SERVICE);
        }

        notification = builder.build();
    }

    @Override
    public Notification createNotification() {
        return notification;
    }
}