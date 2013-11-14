package hippocraticapps.glucopro;

import java.util.Date;

public class SugarRecord extends Record{
	// static storage vars
	public static int shiftID;
	public static boolean pre;
	public static float level;
	public static long  time;
	
	public String parseTimeUnix(){ //will return a string of the date represented by the timestamp.
		// We may want to change this later to a custom format. This will do for now.
		// Saves you a ton of time on the front end Jesse.
		Date time = new Date(this.time);
		return time.toString();
	}
	
	public SugarRecord( int shiftID, boolean pre, float level, long time ){
		// set values
		this.shiftID = shiftID;
		this.pre = pre;
		this.level = level;
		this.time = time;
	}
}
