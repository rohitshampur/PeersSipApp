package com.example.peerssipapp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sourceforge.peers.media.AbstractSoundManager;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.net.rtp.AudioCodec;
import android.os.Environment;

import com.google.code.microlog4android.Logger;

public class AndroidSoundManager extends AbstractSoundManager {
	public byte[] buffer;
	private int port=50005;
	Logger logger = MainActivity.logger;
	AudioRecord recorder;
	Context context;
	AudioTrack audioTrack;
	AudioManager aManager;
	private int sampleRate = 8000 ; // 44100 for music
	private int channelConfig = AudioFormat.CHANNEL_IN_MONO;    
	private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;       
	int minBufSizeRecord;
	int minBufSizeTrack;
	Object sourceDataLineMutex = new Object();
	File f;
	File f1;
	BufferedOutputStream bos;
	BufferedOutputStream bos1;
	public AndroidSoundManager(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	@Override
	public synchronized byte[] readData() {
		// TODO Auto-generated method stub
		logger.debug("Reading data");
		
		recorder.read(buffer, 0, buffer.length);
		
		/*try {
			
			bos1.write(buffer);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.debug("Io exceptions"+e.getStackTrace());
		}*/
		return buffer;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		logger.debug("Close SoundManager");
		recorder.stop();
		recorder.release();
		audioTrack.stop();
		try {
			bos1.flush();
			bos1.close();
			bos.flush();
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		logger.debug("Sound manager initilization");
		context = MyApp.getContext();
		aManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		minBufSizeRecord = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);
		minBufSizeTrack = AudioTrack.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_OUT_MONO, audioFormat);
		logger.debug(""+minBufSizeRecord);
		buffer = new byte[4096];
		recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,sampleRate,channelConfig,audioFormat,minBufSizeRecord*10);
		audioTrack= new AudioTrack(AudioManager.MODE_IN_COMMUNICATION, 8000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, minBufSizeTrack*10, AudioTrack.MODE_STREAM);
/*		aManager.setMode(AudioManager.MODE_IN_CALL);
		aManager.setSpeakerphoneOn(false);*/
		f1 = new File(Environment.getExternalStorageDirectory()+"/"+"Uttara","call1");
		f = new File(Environment.getExternalStorageDirectory()+"/"+"Uttara","call");
		audioTrack.play();
		synchronized (sourceDataLineMutex) {
          
            recorder.startRecording();
        }
		try {
			bos = new BufferedOutputStream(new FileOutputStream(f1));
			bos1 = new BufferedOutputStream(new FileOutputStream(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public synchronized int writeData(byte[] arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		logger.debug("Sound manager write data");
		logger.debug("Audio session"+audioTrack.getAudioSessionId());
		int len = arg0.length;
		int bytesWritten = 0;
		synchronized (sourceDataLineMutex) {
			bytesWritten += audioTrack.write(arg0, arg1, arg2);	
			//logger.debug("bytes written = "+bytesWritten+"\n length of array = "+len);
		}
		
		
		
		/*try {
			
			bos.write(arg0);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.debug("Io exceptions"+e.getStackTrace());
		}*/
		return bytesWritten;
	}

}
