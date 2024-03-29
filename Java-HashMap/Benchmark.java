import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Benchmark {
    static long lookup(HashMap<Integer, Integer> m, Integer[] b, int r) {	
	long sum = 0;

	long t1 = System.nanoTime();
	for (int i = 0; i < r; i ++) {
	    Integer x = m.get(b[i]); 
            if (x != null) 
                sum += x;
	}
	long t2 = System.nanoTime();

	return (t2 - t1) / 1000;
    }

    static void randomize_input(Integer[] a, int n, Integer[] b, int r, float p) {
	Random random = new Random();
	for (int i = 0; i < n; i ++) {
	    a[i] = random.nextInt();
	}
	for (int i = 0; i < r; i ++) {
	    boolean hit = random.nextFloat() <= p;
	    b[i] = hit ? a[random.nextInt(n)] : random.nextInt();
	}
    }
    
    public static void main(String args[]) {
	if (args.length != 4) {
	    System.out.println("Usage: Java Benchmark <size> <requests> <measurements> <hit probability>");
	    System.exit(-1);
	}
	
	int n = Integer.parseInt(args[0]);
	int r = Integer.parseInt(args[1]);
	int k = Integer.parseInt(args[2]);
	float p = Float.parseFloat(args[3]);
	Integer[] a = new Integer[n];
	Integer[] b = new Integer[r];
	long t = 0;

	for (int j = 0; j < k; j ++) {
	    HashMap<Integer, Integer> m = new HashMap<Integer, Integer>(0, 0.5f);
	    randomize_input(a, n, b, r, p);
	    for (int i = 0; i < n; i ++) {
		m.put(a[i], i);
	    }
	    
	    t += lookup(m, b, r);
	    m.clear();
	}

	System.out.printf("%.2f MOPS\n", (double) r * k / (double) t);
    }
}
