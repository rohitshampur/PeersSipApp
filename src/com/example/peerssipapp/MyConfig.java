package com.example.peerssipapp;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.example.peerssipapp.utils.Utils;

import net.sourceforge.peers.Config;
import net.sourceforge.peers.media.MediaMode;
import net.sourceforge.peers.sip.syntaxencoding.SipURI;
import net.sourceforge.peers.sip.syntaxencoding.SipUriSyntaxException;

public class MyConfig implements Config {

	@Override
	public InetAddress getLocalInetAddress() {
	InetAddress inetAddress;
	try {
	// if you have only one active network interface, getLocalHost()
	// should be enough
	//inetAddress = InetAddress.getLocalHost();
	// if you have several network interfaces like I do,
	// select the right one after running ipconfig or ifconfig
	inetAddress = InetAddress.getByName(Utils.getIPAddress(true));
	} catch (UnknownHostException e) {
	e.printStackTrace();
	return null;
	}
	return inetAddress;
	}
	@Override public String getUserPart() { return "uttarainfo"; }
	@Override public String getDomain() { return "sip2sip.info"; }
	@Override public String getPassword() { return "qwertyuiop99"; }
	@Override // use microphone and speakers to capture and playback sound
	public MediaMode getMediaMode() { return MediaMode.captureAndPlayback; }
	
	@Override public void setLocalInetAddress(InetAddress inetAddress) { }
	@Override public void setUserPart(String userPart) { }
	@Override public void setDomain(String domain) { }
	@Override public void setPassword(String password) { }
	@Override public void setOutboundProxy(SipURI outboundProxy) { }
	@Override public void setSipPort(int sipPort) { }
	@Override public void setMediaMode(MediaMode mediaMode) { }
	@Override public void setMediaDebug(boolean mediaDebug) { }
	@Override public void setMediaFile(String mediaFile) { }
	@Override public void setRtpPort(int rtpPort) { }
	@Override public void save() { }
	@Override
	public String getMediaFile() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SipURI getOutboundProxy() {
		// TODO Auto-generated method stub
		SipURI uri = null;
		try {
			uri = new SipURI("sip:proxy.sipthor.net");
		} catch (SipUriSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uri;
	}
	@Override
	public InetAddress getPublicInetAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getRtpPort() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getSipPort() {
		// TODO Auto-generated method stub
		return 5060;
	}
	@Override
	public boolean isMediaDebug() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setPublicInetAddress(InetAddress arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
