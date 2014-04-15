/**
 * Settings for app including URLS and other features. 
 */

package com.clickgostudio.air1072;

public class Settings {
	/********ALL EDITABLE SETTINGS ARE HERE *****/

	//Name of radio station
	private String radioName = "KCSB 91.9";

	//URL of the radio stream
	private String radioStreamURL = "http://live.kcsb.org:80/KCSB_128";
	
	//URL for the advertising / message banner. For no banner leave blank, i.e: ""
	private String adBannerURL = "";
	//Contact button email address
	private String emailAddress = "info@kcsb.org";
	
	//Contact button phone number
	private String phoneNumber = "8058938000";

	//Contact button website URL
	private String websiteURL = "http://kcsb.org";
	
	//Contact button SMS number
	private String smsNumber = "8054098272";
	
	//Message to be shown in notification center whilst playing
	private String mainNotificationMessage = "You're listening to KCSB";

	//TOAST notification when play button is pressed
	private String playNotificationMessage = "Starting KCSB 91.9";
	
	//Enable console output for streaming info (Buffering etc) - Disable = false
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
	
	public String getSmsNumber(){
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



