package com.example.peerssipapp;

import net.sourceforge.peers.Logger;
import net.sourceforge.peers.sip.AbstractState;
import net.sourceforge.peers.sip.transport.SipResponse;

public class CallState extends AbstractState {
	
	public CallState(String id, Logger logger) {
		super(id, logger);
		// TODO Auto-generated constructor stub
	}
	
	public void callClicked() {}
    public void incomingCall() {}
    public void calleePickup() {}
    public void error(SipResponse sipResponse) {}
    public void pickupClicked() {}
    public void busyHereClicked() {}
    public void hangupClicked() {}
    public void remoteHangup() {}
    public void closeClicked() {}
    public void ringing() {}

}
