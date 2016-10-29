package ml.puredark.hviewer.ruletester.http;

public class Logger {

    public static void d(String tag, String message){
    	System.out.println(tag + " " +  message);
    }

    public static void e(String tag, String message, Throwable e){
        System.out.println(tag + " " +  message + " " + e.getMessage());
    }
}
