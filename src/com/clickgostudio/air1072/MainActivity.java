/**
 * Main activity that is launched upon opening the app. Listens to the contact buttons on the home screen, and play + stop buttons
 * @author davidbain
 */

package com.clickgostudio.air1072;

import java.io.InputStream;
import com.clickgostudio.air1072.R;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button stopButton = null;
	private Button playButton = null;
	
	//Settings
	Settings settings = new Settings();
	//private final String ADURL = "http://www.aironair.co.uk/wp-content/uploads/2013/09/App-Banner.png";
	private final String ADURL = settings.getAdBannerURL();
	//private String ADURL = getString(R.string.ad_banner_url);
	//private final String EMAILADD = "studio@aironair.co.uk";
	private final String EMAILADD = settings.getEmailAddress();

 
	/**
	 * Done upon opening the activity
	 */
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

		clickListeners(); //Start click listeners
		adBanner(); //Start ad banner loading and display

	}


	/**
	 * Listens for contact button clicks
	 */
	private void clickListeners(){
		//Email Button
		final View EmailPress = (Button)this.findViewById(R.id.emailBtn);
		EmailPress.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){

				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.setType("message/rfc822");
				emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{EMAILADD});
				//i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
				//i.putExtra(Intent.EXTRA_TEXT   , "body of email");
				try {
					startActivity(Intent.createChooser(emailIntent, "Send email..."));
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
				}

			}
		});

		//Phone Button
		final View PhonePress = (Button)this.findViewById(R.id.phoneBtn);
		PhonePress.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){

				String phoneNum = settings.getPhoneNumber();
				Intent phoneIntent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNum));
				startActivity (phoneIntent);

			}
		});

		//Website Button
		final View WWWPress = (Button)this.findViewById(R.id.websiteBtn);
		WWWPress.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){

				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(settings.getWebsiteURL())); //URL
				startActivity (browserIntent);

			}
		});

		//SMS Button
		final View TxtPress = (Button)this.findViewById(R.id.txtBtn);
		TxtPress.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){

				Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" +settings.getSmsNumber())); smsIntent.putExtra("sms_body", "AIR ");
				startActivity(smsIntent);

			}
		});
	}


	/**
	 * Get and display advertising banner. Also determine screen size and show / hide ad accordingly
	 */
	private void adBanner(){

		//Hide ad banner if screen size too small
		if ((getResources().getConfiguration().screenLayout & 
				Configuration.SCREENLAYOUT_SIZE_MASK) == 
				Configuration.SCREENLAYOUT_SIZE_SMALL) {

			LinearLayout adAreaLl = (LinearLayout)findViewById(R.id.adArea);
			adAreaLl.setVisibility(View.GONE);
		}

		//Show on all other screen sizes
		else{

			new DownloadImageTask((ImageView) findViewById(R.id.display_banner))
			.execute(ADURL);

		}
	}


	/**
	 * Load image from external source Asyncrons
	 */
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView adImage;

		public DownloadImageTask(ImageView bmImage) {
			this.adImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap adBmFa = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				adBmFa = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return adBmFa;
		}

		protected void onPostExecute(Bitmap result) {
			adImage.setImageBitmap(result);
		}
	}


}


