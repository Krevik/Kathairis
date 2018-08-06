package com.Krevik.Sounds;

public class SoundHelper extends SoundEventHandlerMystic {

	public SoundHelper(String mob,String subset) {
		super("mob."+mob+"." + subset);
		register();
	}
	
	public SoundHelper(String Name) {
		super("kether."+Name);
		register();
	}

}