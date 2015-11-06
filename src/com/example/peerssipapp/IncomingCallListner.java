package com.example.peerssipapp;

import net.sourceforge.peers.sip.transport.SipRequest;
import net.sourceforge.peers.sip.transport.SipResponse;

public interface IncomingCallListner {
	
	public void hangupClicked(SipRequest sipRequest,SipResponse sipResponse);
    public void pickupClicked(SipRequest sipRequest,SipResponse sipResponse);
    public void busyHereClicked(SipRequest sipRequest,SipResponse sipResponse);
    public void registering();
    public void registerSuccssFull();
    public void dtmf(char digit);

}
