/**
 * Settings for app including URLS and other features. 
 */

package com.clickgostudio.air1072;

public class Settings {
	/********ALL EDITABLE SETTINGS ARE HERE *****/

	//Name of radio station
	private String radioName = "AIR 107.2";

	//URL of the radio stream
	private String radioStreamURL = "http://stream.aironair.co.uk:8002";

	//URL for the advertising / message banner. For no banner leave blank, i.e: ""
	private String adBannerURL = "http://www.aironair.co.uk/wp-content/uploads/2013/09/App-Banner.png";
	
	//Contact button email address
	private String emailAddress = "studio@aironair.co.uk";
	
	//Contact button phone number
	private String phoneNumber = "01305836040";

	//Contact button website URL
	private String websiteURL = "http://aironair.co.uk";
	
	//Contact button SMS number
	private int smsNumber = 66777;
	
	//Message to be shown in notification center whilst playing
	private String mainNotificationMessage = "You're listening to AIR";

	//TOAST notification when play button is pressed
	private String playNotificationMessage = "Starting AIR 107.2";
	
	//Enable console output for streaming info (Buffering etc) 
	private boolean allowConsole = true;
	
	/********END OF EDITABLE SETTINGS**********/
	
	
	
	/********DO NOT EDIT BELOW THIS LINE*******/
	public String getRadioName(){
		return radioName;
	}
	
	public String getRadioStreamURL(){
		return radioStreamURL;
	}

	public String getAdBannerURL(){
		return adBannerURL;
	}

	public String getEmailAddress(){
		return emailAddress;
	}
	
	public String getPhoneNumber(){
		String appendPhoneNumber = "tel:"+phoneNumber;
		return appendPhoneNumber;
	}
	
	public String getWebsiteURL(){
		return websiteURL;
	}
	
	public int getSmsNumber(){
		return smsNumber;
	}
	
	public String getMainNotificationMessage(){
		return mainNotificationMessage;
	}
	
	public String getPlayNotificationMessage(){
		return playNotificationMessage;
	}

	public boolean getAllowConsole(){
		return allowConsole;
	}
}



