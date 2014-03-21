/**
 * Main activity that is launched upon opening the app. Listens to the contact buttons on the home screen, and play + stop buttons
 * @author davidbain
 */

package com.clickgostudio.air1072;

import com.clickgostudio.air1072.R;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends Activity {

	private Button stopButton = null;
	private Button playButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		playButton = (Button)findViewById(R.id.PlayButton);
		playButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), 
						RadioMediaPlayerService.class);
				intent.putExtra(RadioMediaPlayerService.START_PLAY, true);
				startService(intent);

			}
		});

		stopButton = (Button)findViewById(R.id.StopButton);
		stopButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//Get new MediaPlayerService activity
				Intent intent = new Intent(getApplicationContext(),
						RadioMediaPlayerService.class);
				stopService(intent);
			}
		});

		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		clickListeners();

	}


	/**
	 * Listens for contact button clicks
	 */
	private void clickListeners(){
		//Email Button
		final View EmailPress = (Button)this.findViewById(R.id.emailBtn);
		EmailPress.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){
				if (view == findViewById(R.id.emailBtn)){
					Intent emailIntent = new Intent(Intent.ACTION_SEND);
					//startActivity (emailIntent); TODO Fix
				}
			}
		});

		//Phone Button
		final View PhonePress = (Button)this.findViewById(R.id.phoneBtn);
		PhonePress.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){
				if (view == findViewById(R.id.phoneBtn)){
					String phoneNum = "tel:01305836040";
					Intent phoneIntent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNum));
					startActivity (phoneIntent);
				}
			}
		});

		//Website Button
		final View WWWPress = (Button)this.findViewById(R.id.websiteBtn);
		WWWPress.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){
				if (view == findViewById(R.id.websiteBtn)){
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://aironair.co.uk")); //URL
					startActivity (browserIntent);
				}
			}
		});

		//SMS Button
		final View TxtPress = (Button)this.findViewById(R.id.txtBtn);
		TxtPress.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){
				if (view == findViewById(R.id.txtBtn)){
					Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" +66777)); smsIntent.putExtra("sms_body", "AIR ");
					startActivity(smsIntent);
					//TODO Fix crash
				}
			}
		});
	}

}
