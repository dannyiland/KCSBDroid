package com.clickgostudio.air1072;

import java.io.IOException;

import com.clickgostudio.air1072.R;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class RadioMediaPlayerService extends Service {

	//private MediaPlayer mediaPlayer = null;
	private boolean      isPlaying = false;
	private MediaPlayer radioPlayer;

	private static int classID = 579; // just a number
	
	public static String START_PLAY = "START_PLAY";

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent.getBooleanExtra(START_PLAY, false)) {
			play();	
		}
		return Service.START_STICKY;	
	}

	//Play audio stream
	@SuppressLint("NewApi")
	private void play() {
		if (!isPlaying) {			
			isPlaying = true;
			
			//Return to the current activity
			Intent intent = new Intent(this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
							Intent.FLAG_ACTIVITY_SINGLE_TOP);

			PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
			
			//Build and show notificaion for radio playing
			Notification notification = new Notification.Builder(getApplicationContext())
	         	.setContentTitle("AIR 107.2")
	         	.setContentText("You're listening to AIR")
	         	.setSmallIcon(R.drawable.ic_launcher)
	         	.setContentIntent(pi)
	         	.build();
			
			//Get stream URL
			radioPlayer = new MediaPlayer();
			try {
				radioPlayer.setDataSource("http://stream.aironair.co.uk:8002"); //Place URL here
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			//Buffering Info
			radioPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
				public void onBufferingUpdate(MediaPlayer mp, int percent) {
					Log.i("Buffering", "" + percent);
				}
			});
			
			radioPlayer.prepareAsync();
			radioPlayer.setOnPreparedListener(new OnPreparedListener() {

				public void onPrepared(MediaPlayer mp) {
					radioPlayer.start(); //Start radio stream
				}
			});
			
			
			startForeground(classID, notification);
			
			//Display toast notification
			Toast.makeText(getApplicationContext(), "Starting AIR 107.2",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onDestroy() {
		stop();
	}	
	
	
	//Stop audio 
	private void stop() {
		if (isPlaying) {
			isPlaying = false;
			if (radioPlayer != null) {
				radioPlayer.release();
				radioPlayer = null;
			}
			stopForeground(true);
		}
		
		Toast.makeText(getApplicationContext(), "Stream stopped",
				Toast.LENGTH_LONG).show();
	}
	
}
