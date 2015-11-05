package com.example.peerssipapp;

import net.sourceforge.peers.sip.transport.SipRequest;

public interface IncomingCallListner {
	
	public void hangupClicked(SipRequest sipRequest);
    public void pickupClicked(SipRequest sipRequest);
    public void busyHereClicked(SipRequest sipRequest);
    public void dtmf(char digit);

}
