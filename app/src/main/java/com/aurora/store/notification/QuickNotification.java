/*
 * Aurora Store
 * Copyright (C) 2019, Rahul Kumar Patel <whyorean@gmail.com>
 *
 * Aurora Store is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Aurora Store is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Aurora Store.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */

package com.aurora.store.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.aurora.store.R;

public class QuickNotification extends NotificationBase {

    private static final int QUICK_NOTIFICATION_CHANNEL_ID = 69;

    public QuickNotification(Context context) {
        super(context);
    }

    public static QuickNotification show(@NonNull Context context,
                                         @NonNull String contentTitle,
                                         @NonNull String contentText,
                                         @Nullable PendingIntent contentIntent) {
        QuickNotification quickNotification = new QuickNotification(context);
        quickNotification.show(contentTitle, contentText, contentIntent);
        return quickNotification;
    }

    public void show(String contentTitle, String contentText, PendingIntent contentIntent) {
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context, context.getPackageName())
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_PROGRESS)
                .setColorized(true)
                .setColor(context.getResources().getColor(R.color.colorAccent))
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setOnlyAlertOnce(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_notification)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        if (contentIntent != null)
            builder.setContentIntent(contentIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(
                    context.getPackageName(),
                    context.getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Aurora Store Quick Notification Channel");
            manager.createNotificationChannel(channel);
            builder.setChannelId(channel.getId());
        }
        manager.notify(QUICK_NOTIFICATION_CHANNEL_ID, builder.build());
    }
}
