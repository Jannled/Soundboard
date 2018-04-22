package com.github.jannled.soundboard.sound;

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
	
	private Mixer getSelectedAudioDevice()
	{
		final String audioManagerName = (String) Main.window.getAudioDevices().getSelectedItem();
		
		for(int i=0; i<audioMixers.length; i++)
		{
			if(audioMixers[i].getName().equals(audioManagerName))
			{
				return AudioSystem.getMixer(audioMixers[i]);
			}
		}
		return null;
	}
	
	public void play(Sound audio)
	{
		getSelectedAudioDevice();
	}
}
