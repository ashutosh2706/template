package utils;
import java.util.*;
import java.math.*;
import java.util.stream.*;

public class Utility {

    private static final boolean ENABLED = true;

    public static void log(String format, Object... args) {
        if (!ENABLED) return;

        StringBuilder sb = new StringBuilder();
        int argIndex = 0;
        for (int i = 0; i < format.length(); i++) {
            if (i + 1 < format.length()
                    && format.charAt(i) == '{'
                    && format.charAt(i + 1) == '}'
                    && argIndex < args.length) {
                sb.append(stringify(args[argIndex++]));
                i++;
            } else {
                sb.append(format.charAt(i));
            }
        }

        while (argIndex < args.length) {
            sb.append(" ").append(stringify(args[argIndex++]));
        }

        System.err.println(sb.toString());
    }

    static final int MOD = (int) 1e9 + 7;
    public static void sieveOfEratosthenes(int n, List<Integer> primes) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        isPrime[0] = isPrime[1] = false;
        // Storing prime numbers in the list
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes.add(i);
        }
    }

    /*
    first element >= target
    */

    public static int lowerBound(int[] nums, int target) {
        int low=0, hi=nums.length-1, mid;
        while(low <= hi) {
            mid = low+(hi-low)/2;
            if (nums[mid] < target) 
                low=mid+1;
            else hi=mid-1;
        }

        return low;
    }
    
    // first element > target
    public static int upperBound(int[] nums, int target) {
        int lo=0, hi=nums.length-1, mid;
        while (lo <= hi) {
            mid=lo+(hi-lo)/2;
            if (nums[mid]<=target) 
                lo=mid+1;
            else hi=mid-1;
        }
        return lo;
    }

    public static int upperBound(List<Integer> nums, int target) {
        int lo=0, hi=nums.size()-1, mid;
        while (lo <= hi) {
            mid=lo+(hi-lo)/2;
            if (nums.get(mid)<=target) 
                lo=mid+1;
            else hi=mid-1;
        }
        return lo;
    }

    public static int nCr(int n, int r) {
        // pascal's triangle
        if (n < r) return 0;
        if ((n - r) < r) r = n - r;

        int[] dp = new int[r + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(r, i); j > 0; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }

        return dp[r];
    }

    public static int pow(long a, long b) {
        // binary exponentiation
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res * a) % MOD;
            }
            a = (a * a) % MOD;
            b >>= 1;
        }
        return (int) res;
    }

    public static void log(Object o) {
        // System.out.println("-----------------------");
        if(o instanceof int[]) {
            Arrays.stream((int[]) o).forEach(i->System.out.print(i+" "));
            System.out.print('\n');
        } else if (o instanceof long[]) {
            Arrays.stream((long[]) o).forEach(i->System.out.print(i+" "));
            System.out.print('\n');
        } 
        else if (o instanceof String[]) {
            String[] strings = (String[]) o;
            System.out.print("[");
            for(int i=0; i<strings.length; ++i) {
                System.out.print(" "+strings[i]+" ");
            }
            System.out.print("]\n");
        } else if (o instanceof int[][]) {
            System.out.print("{");
            int L=((int[][]) o).length;
            for(int i=0; i<L; ++i) {
                int[] r=((int[][])o)[i];
                System.out.print(" { ");
                Arrays.stream(r).forEach(c -> System.out.print(c+" "));
                System.out.print("}");
            }
            System.out.println(" }");
        }
        else if (o instanceof long[][]) {
            System.out.print('{');
            int L=((long[][]) o).length;
            for(int i=0; i<L; ++i) {
                long[] r=((long[][])o)[i];
                System.out.print('{');
                Arrays.stream(r).forEach(c -> System.out.print(c+' '));
                System.out.println('}');
            }
            System.out.println('}');
        }
        else
            System.out.println(o);
        // System.out.println("-----------------------");
    }

    private static String stringify(Object obj) {
        if (obj == null) return "null";

        if (obj.getClass().isArray()) {
            if (obj instanceof int[]) return Arrays.toString((int[]) obj);
            if (obj instanceof long[]) return Arrays.toString((long[]) obj);
            if (obj instanceof double[]) return Arrays.toString((double[]) obj);
            if (obj instanceof char[]) return Arrays.toString((char[]) obj);
            if (obj instanceof boolean[]) return Arrays.toString((boolean[]) obj);
            if (obj instanceof int[][]) {
                int[][] arr = (int[][]) obj;
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<arr.length; i++) {
                    sb.append('{');
                    sb.append(Arrays.toString((int[]) arr[i]));
                    sb.append('}');
                }
                return sb.toString();
            }
            return Arrays.deepToString((Object[]) obj);
        }

        return obj.toString();
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}