package com.github.jannled.soundboard.sound;

import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

import com.github.jannled.soundboard.Main;

public class Audioplayer
{
	private Mixer.Info[] audioMixers;
	
	public Audioplayer()
	{
		audioMixers = AudioSystem.getMixerInfo();
		String[] mixerNames = new String[audioMixers.length];
		
		for(int i=0; i<audioMixers.length; i++)
		{
			mixerNames[i] = audioMixers[i].getName();
			Main.window.getAudioDevices().addItem(mixerNames[i]);
		}
	}
	
	public void play(InputStream audio)
	{
		
	}
}
