package com.example.peerssipapp;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import net.sourceforge.peers.media.AbstractSoundManager;

public class AndroidSoundManager extends AbstractSoundManager {
	public byte[] buffer;
	private int port=50005;

	AudioRecord recorder;
	Context context;
	AudioTrack audioTrack;
	AudioManager aManager;
	private int sampleRate = 16000 ; // 44100 for music
	private int channelConfig = AudioFormat.CHANNEL_IN_MONO;    
	private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;       
	int minBufSize;
	public AndroidSoundManager(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	@Override
	public byte[] readData() {
		// TODO Auto-generated method stub
		byte[] buffer = new byte[minBufSize];
		recorder.startRecording();
		recorder.read(buffer, 0, buffer.length);

		return buffer;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		recorder.stop();
		audioTrack.stop();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		context = MyApp.getContext();
		aManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		minBufSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);
		recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,sampleRate,channelConfig,audioFormat,minBufSize*10);
		audioTrack= new AudioTrack(AudioManager.STREAM_MUSIC, 11025, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, minBufSize, AudioTrack.MODE_STREAM);
		aManager.setMode(AudioManager.MODE_IN_CALL);
		aManager.setSpeakerphoneOn(false);
		
	}

	@Override
	public int writeData(byte[] arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		int bytesWritten = audioTrack.write(arg0, arg1, arg2);
		return bytesWritten;
	}

}
