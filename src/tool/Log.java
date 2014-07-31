package tool;


public class Log {

public static void sysecho(String message){System.out.println("SYS LOG: " + message);}
public static void sysecho(String message, String cls, String method)
{
	System.out.println("SYS LOG: " + message + " " + cls + "::" + method);
}
public static void echo(String message){ System.out.println("LOG: " + message);}
public static void echo(Object message){ System.out.println("LOG: " + message.toString());}
public static void echo(String message, String sig){ System.out.println("LOG: " + message + " " + sig);}

public static void echo(String message, String cls, String method)
{
	
	System.out.println("LOG: " + message + " " + cls + "::" + method);
}
public static String printRGB(int[] rgb)
{
	return String.format("r:%d g:%d b:%d", rgb[0], rgb[1], rgb[2]);
}



}
