package com.example.peerssipapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import net.sourceforge.peers.sip.Utils;
import net.sourceforge.peers.sip.core.useragent.UserAgent;
import net.sourceforge.peers.sip.transactionuser.Dialog;
import net.sourceforge.peers.sip.transactionuser.DialogManager;
import net.sourceforge.peers.sip.transport.SipRequest;
import net.sourceforge.peers.sip.transport.SipResponse;

public class IncomingCall extends Activity  implements IncomingCallListner{
	private UserAgent userAgent;
	private SipRequest sipReq;
	private SipResponse sipResp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_incoming_call);
		userAgent = EventManager.getUserAgent();

	}
	
	public void acceptCall(View v){
		new Thread() {
			public void run() {
				try {
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
	
	public void rejectcall(View v){
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				userAgent.terminate(sipReq);
			}
		};
	}

	@Override
	public void hangupClicked(SipRequest sipRequest, SipResponse sipResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pickupClicked(SipRequest sipRequest, SipResponse sipResponse) {
		// TODO Auto-generated method stub
		this.sipReq = sipRequest;
		this.sipResp = sipResponse;
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
		
	}

	@Override
	public void registerSuccssFull() {
		// TODO Auto-generated method stub
		
	}



	
	
}
