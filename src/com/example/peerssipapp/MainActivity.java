package com.example.peerssipapp;

import java.io.File;
import java.io.FileInputStream;
import java.net.SocketException;

import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.google.code.microlog4android.config.PropertyConfigurator;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import net.sourceforge.peers.sip.Utils;
import net.sourceforge.peers.sip.core.useragent.UserAgent;
import net.sourceforge.peers.sip.transactionuser.Dialog;
import net.sourceforge.peers.sip.transactionuser.DialogManager;
import net.sourceforge.peers.sip.transport.SipRequest;
import net.sourceforge.peers.sip.transport.SipResponse;

public class MainActivity extends Activity implements IncomingCallListner {
	public static final Logger logger = LoggerFactory.getLogger();
	EventManager eventManager;
	private SipRequest sipReq;
	private SipResponse sipResp;
	private TextView textvw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PropertyConfigurator.getConfigurator(this).configure();
		textvw = (TextView) findViewById(R.id.textView1);
	}

	public void registerSip(View v) {

		logger.debug("Starting registration");
		try {
			eventManager = new EventManager(this);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void callSip(View v) {
		EventManager.call("sip:rohitshampur@sip2sip.info");
	}

	public void receiveCall(View v) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				textvw.setText("call received");
			}
		});

		new Thread() {
			public void run() {
				try {
					UserAgent userAgent = EventManager.getUserAgent();
					String callId = Utils.getMessageCallId(sipReq);
					DialogManager dialogManager = userAgent.getDialogManager();
					Dialog dialog = dialogManager.getDialog(callId);
					userAgent.acceptCall(sipReq, dialog);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void endCall(View v) {

		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				textvw.setText("call ended");
			}
		});

		
		new Thread() {
			public void run() {
				try {
					UserAgent userAgent = EventManager.getUserAgent();
					userAgent.terminate(sipReq);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	@Override
	public void hangupClicked(SipRequest sipRequest, SipResponse sipResponse) {
		// TODO Auto-generated method stub
		logger.debug("Hangup clicked");
		this.sipReq = sipRequest;
		this.sipResp = sipResponse;
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				textvw.setText("Hangup");
			}
		});
	}

	@Override
	public void pickupClicked(SipRequest sipRequest, SipResponse sipResponse) {
		// TODO Auto-generated method stub
		logger.debug("Incoming call");
		this.sipReq = sipRequest;
		this.sipResp = sipResponse;
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				textvw.setText("Incoming call");
			}
		});

	}
	
	public void playMedia(View v) {
		MediaPlayer mediaPlayer = new MediaPlayer();
		File file = new File(Environment.getExternalStorageDirectory()+"/"+"Uttara","call");
		try{
		FileInputStream fis = new FileInputStream(file);
        mediaPlayer.setDataSource(fis.getFD());
        mediaPlayer.prepare();
        mediaPlayer.start();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void busyHereClicked(SipRequest sipRequest, SipResponse sipResponse) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dtmf(char digit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registering() {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				textvw.setText("Registering");
			}
		});

	}

	@Override
	public void registerSuccssFull() {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				textvw.setText("Registration successfull");
			}
		});

	}

}
