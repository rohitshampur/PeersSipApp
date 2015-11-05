package com.example.peerssipapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import net.sourceforge.peers.sip.Utils;
import net.sourceforge.peers.sip.core.useragent.SipListener;
import net.sourceforge.peers.sip.core.useragent.UserAgent;
import net.sourceforge.peers.sip.syntaxencoding.SipUriSyntaxException;
import net.sourceforge.peers.sip.transactionuser.Dialog;
import net.sourceforge.peers.sip.transactionuser.DialogManager;
import net.sourceforge.peers.sip.transport.SipRequest;
import net.sourceforge.peers.sip.transport.SipResponse;

public class IncomingCall extends Activity  implements SipListener{
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
	public void calleePickup(SipResponse arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void error(SipResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void incomingCall(SipRequest arg0, SipResponse arg1) {
		// TODO Auto-generated method stub
		this.sipReq = arg0;
		this.sipResp = arg1;
		
	}

	@Override
	public void registerFailed(SipResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerSuccessful(SipResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registering(SipRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remoteHangup(SipRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ringing(SipResponse arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
