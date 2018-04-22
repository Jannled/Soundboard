package com.github.jannled.soundboard.sound;

import java.io.InputStream;

public class Sound
{
	private String name;
	private InputStream istream;
	private int length;
	
	public Sound(String name, InputStream s, int length)
	{
		this.name = name;
		this.istream = s;
		this.length = length;
	}

	public String getName()
	{
		return name;
	}

	public InputStream getInputstream()
	{
		return istream;
	}

	public int getLength()
	{
		return length;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return name + " " + length;
	}
}
