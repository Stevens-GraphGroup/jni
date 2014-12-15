import java.net.URL;
import java.net.URLClassLoader;
import java.util.StringTokenizer;

public class Sample1
{
    static {
		System.err.println("debugging info: classloader:");
		ClassLoader cl = ClassLoader.getSystemClassLoader();
			URL[] urls = ((URLClassLoader)cl).getURLs();
			for(URL url: urls){
			System.err.println(url.getFile());
			}
		
		System.err.println("\njava.library.path:");
		String property = System.getProperty("java.library.path");
		StringTokenizer parser = new StringTokenizer(property, ";");
		while (parser.hasMoreTokens()) {
			System.err.println(parser.nextToken());
		}
		System.err.println();

		try {
			//System.load("./Sample1.so");
			System.loadLibrary("Sample1");
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
    }


    public native int     intMethod(int n);
    public native boolean booleanMethod(boolean bool);
    public native String  stringMethod(String text);
    public native int     intArrayMethod(int[] intArray);

    public static void main(String[] args)
    {   
        Sample1 sample = new Sample1();
        int     square = sample.intMethod(5);
        boolean bool   = sample.booleanMethod(true);
        String  text   = sample.stringMethod("To be or not to be");

		long d = -System.nanoTime();
        int     sum    = sample.intArrayMethod(new int[] {1,1,2,3,5,8,13} );	
		d += System.nanoTime();
		
        System.out.println("intMehod: " + square);
        System.out.println("booleanMehod: " + bool);
        System.out.println("stringMehod: " + text);
        System.out.println("intArrayMehod: " + sum);
		System.out.println("Time for intArrayMethod in nano seconds: " + d );
    }
}