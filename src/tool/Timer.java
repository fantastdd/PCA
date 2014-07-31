package tool;

public class Timer {
public static long time;
	public static void start()
{
	time = System.currentTimeMillis();



}
	public static void end(String type)
	{
		System.out.println(type + "  "+ (System.currentTimeMillis() - time));
		
	}
}
