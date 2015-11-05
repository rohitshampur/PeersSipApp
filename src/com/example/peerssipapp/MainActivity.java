package com.example.peerssipapp;

import java.net.SocketException;

import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.google.code.microlog4android.config.PropertyConfigurator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	public static final Logger logger = LoggerFactory.getLogger();
	EventManager eventManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PropertyConfigurator.getConfigurator(this).configure();
		
	}
	
	public void registerSip(View v){
		
		logger.debug("Starting registration");
		try {
			eventManager = new EventManager(this);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void callSip(View v){
		EventManager.call("sip:uttarainfo@sip2sip.info");
	}
	
	public void receiveCall(View v) {
		eventManager.calleePickup(EventManager.getSipResponse());
	}
}
