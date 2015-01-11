import java.net.URL;
import java.net.URLClassLoader;
import java.util.StringTokenizer;

public class Wrap
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
			System.loadLibrary("Wrap");
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
    }
    public native String []    seqpass(String[] n);

    public static void main(String[] args)
    {   
        System.loadLibrary("Wrap");
        Wrap wrap = new Wrap();
        String[]   s = new String[8]; //information stored in 0-3 index the rest are sequences
        s[0] = "User Query1";
		s[1] = "User Query2";
		s[2] = "placeholder1";
		s[3] = "placeholder2";
		s[4] = ">sp|Q6GZX4|001R_FRG3G Putative transcription factor 001R OS=Frog virus 3 (isolate Goorha) GN=FV3-001R PE=4 SV=1"; //these are not actual sequences
		s[5] = "MAFSAEDVLKEYDRRRRMEALLLSLYYPNDRKLLDYKEWSPPRVQVECPKAPVEWNNPPSEKGLIVGHFSGIKYKGEKAQASEVDVNKMCCWVSKFKDAMRRYQGIQTCKIPGKVLSDLDAKIKAYNLTVEGVEGFVRYSRVTKQHVAAFLKELRHSKQYENVNLIHYILTDKRVDIQHLEKDLVKDFKALVESAHRMRQGHMINVKYILYQLLKKHGHGPDGPDILTVKTGSKGVLYDDSFRKIYTDLGWKFTPL";
		s[6] = ">sp|Q6GZX3|002L_FRG3G Uncharacterized protein 002L OS=Frog virus 3 (isolate Goorha) GN=FV3-002L PE=4 SV=1";
		s[7] = "MSIIGATRLQNDKSDTYSAGPCYAGGCSAFTPRGTCGKDWDLGEQTCASGFCTSQPLCARIKKTQVCGLRYSSKGKDPLVSAEWDSRGAPYVRCTYDADLIDTQAQVDQFVSMFGESPSLAERYCMRGVKNTAGELVSRVSSDADPAGGWCRKWYSAHRGPDQDAALGSFCIKNPGAADCKCINRASDPVYQKVKTLHAYPDQCWYVPCAADVGELKMGTQRDTPTNCPTQVCQIVFNMLDDGSVTMDDVKNTINCDFSKYVPPPPPPKPTPPTPPTPPTPPTPPTPPTPPTPRPVHNRKVMFFVAGAVLVAILISTVRW";

        String[] result = wrap.seqpass(s);

        for(int i =0;i < result.length;i++)
		{
			System.out.println(result[i]);
		}
		
    }
}