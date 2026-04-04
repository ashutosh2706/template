import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.math.*;
import java.util.stream.*;

import static utils.Utility.log;
 
public class Solution {

    static int reverse(int i) {
        var s = new StringBuilder(String.valueOf(i));
        return Integer.parseInt(s.reverse().toString());
    }
    

    static List<String> rotate(String s) {
        List<String> lst = new ArrayList<>();
        String curr = s;
        while(true) {
            StringBuilder nxt = new StringBuilder();
            for(int i=0; i<curr.length(); i++) {
                int ii = (((curr.charAt(i)-'a')+1)%26) + 'a';
                nxt.append((char) ii);
            }
            curr = nxt.toString();
            if (curr.equals(s)) break;
            lst.add(curr);
        }
        return lst;
    }

    static Set<Integer> vis = new HashSet<>();
    static void findDistance(int node, int[] dist, Map<Integer, List<Integer>> graph, int sum) {
        if (vis.contains(node)) return;
        vis.add(node);
        dist[node] = sum;
        for(var adj: graph.get(node)) {
            findDistance(adj, dist, graph, sum+1);
        }
    }

    /*
        Queue:
        available implementations are:
        - ArrayDequeu<>() (fast)
        - LinkedList<>()
        - PriorityQueue<>()

        Methods:
        - offer (add new element at last)
        - poll (remove first element)
        - peek (get the first element without removing)


        Comparator:
        Long.compare((long) a, (long) b) 

        TreeMap<K, V>() 
        - firstEntry()
        - lastEntry()
        - map.compute(key, (k, v) -> {function which returns value for the key})

    */


    static void bfsMark(int x, int y, char[][] board, char[][] board2) {
        if (x >= board.length || y >= board[0].length || x<0 || y<0) return;
        if(board[x][y] == 'O') {
            board2[x][y] = 'O';
            bfsMark(x+1, y, board, board2);
            bfsMark(x-1, y, board, board2);
            bfsMark(x, y+1, board, board2);
            bfsMark(x, y-1, board, board2);
        }
    }

    static List<List<Integer>> perms = new ArrayList<>();
    static void permute(int index, int[] candidates, int target, int sum, List<Integer> current) {
        if(sum >= target) {
            if (sum == target) perms.add(new ArrayList<>(current));
            return;
        }

        for(int i=index; i<candidates.length; i++) {
            current.add(candidates[i]);
            permute(i, candidates, target, sum+candidates[i], current);
            current.remove(current.size()-1);
        }
    }

    // static long[][] dp = new long[13][(int)1e4+1];

    // static long solve(int ind, int[] coins, int amt) {
    //     if (ind >= coins.length || amt < 0) {
    //         return Integer.MAX_VALUE;
    //     }
    //     if (amt==0) return 0;

    //     if(dp[ind][amt] != -1) return dp[ind][amt];

    //     long x=1+solve(ind, coins, amt-coins[ind]);
    //     long y=1+solve(ind+1, coins, amt-coins[ind]);
    //     long z=solve(ind+1, coins, amt);
    //     return dp[ind][amt] = Math.min(x, Math.min(y, z));
    // }


    /*
    [maximum even/odd sum with constraints]
    static int solve(int[] nums, int ind, int parity) {
        if (ind >= nums.length) return 0;
        
        int ans = solve(nums, ind+1, parity);
        int newParity = nums[ind]+parity % 2 == 0 ? 0 : 1;
        if (nums[ind] % 2==0) {
            ans = Math.max(ans, nums[ind] + solve(nums, ind+1, newParity));
        } else {
            ans = Math.max(ans, solve(nums, ind+2, newParity));
        }

        return ans;
    }
    
    */


    // static int[][] dp = new int[2][(int)1e5+1];
    // static int solve(int ind, int[] nums, int parity) {
    //     if(ind >= nums.length) {
    //         return 0;
    //     }

    //     if(dp[parity][ind] != -1) return dp[parity][ind];

    //     int ans=solve(ind+1, nums, parity);
    //     if (parity == 0) {
    //         ans = Math.max(ans, nums[ind]+solve(ind+1, nums, 1));
    //     } else {
    //         ans = Math.max(ans, solve(ind+1, nums, 0)-nums[ind]);
    //     }

    //     return dp[parity][ind] = ans;
    // }


    // static int[] dp = new int[305];

    // static boolean solve(String s, int ind, Set<String> words) {
    //     if (ind >= s.length()) return true;

    //     if(dp[ind] != -1) {
    //         return dp[ind] == 1 ? true : false;
    //     }
    //     StringBuilder tmp = new StringBuilder();
    //     boolean possible=false;
    //     for(int i=ind; i<s.length(); i++) {
    //         tmp.append(s.charAt(i));
    //         if (words.contains(tmp.toString())) {
    //             possible = possible || solve(s, i+1, words);
    //         }
    //     }
    //     dp[ind] = possible ? 1 : 0;
    //     return possible;
    // }

    // static int[] dp = new int[2505];
    // static int solve(int ind, int[] nums) {
    //     int ans=1;
    //     if (dp[ind] != -1) return dp[ind];
    //     for(int i=ind+1; i<nums.length; i++) {
    //         if (nums[i] > nums[ind]) {
    //             ans = Math.max(ans, 1+solve(i, nums));
    //         }
    //     }
    //     return dp[ind] = ans;
    // }

    // static int[][] dp = new int[205][205];
    // static int solve(int i, int j, List<List<Integer>> triangle) {

    //     if (i==triangle.size()) return 0;
    //     if(dp[i][j] != -1) return dp[i][j];
        
    //     return dp[i][j] = triangle.get(i).get(j) + Math.min(solve(i+1, j, triangle), solve(i+1, j+1, triangle));
    // }

    // static int solve(int[][] grid, int x, int y, int[][] dp) {
    //     if(x == grid.length || y == grid[0].length || grid[x][y] == 1) 
    //         return 0;
    //     if (x==grid.length-1 && y==grid[0].length-1) 
    //         return 1;
    //     if (dp[x][y] != -1) return dp[x][y];
    //     return dp[x][y] = solve(grid, x+1, y, dp) + solve(grid, x, y+1, dp);
    // }
    
    // static boolean check(String s1, String s2, String s3, int x, int y, int[][] dp) {
        
    //     if (x == s1.length() && y == s2.length()) 
    //         return x+y == s3.length();
        
    //     if (dp[x][y] != -1) return dp[x][y] == 1;

    //     boolean ans = false;
    //     if (x < s1.length() && s1.charAt(x) == s3.charAt(x+y)) 
    //         ans |= check(s1, s2, s3, x+1, y, dp);
    //     if (y < s2.length() && s2.charAt(y) == s3.charAt(x+y))
    //         ans |= check(s1, s2, s3, x, y+1, dp);

    //     dp[x][y] = ans ? 1 : 0;
    //     return ans;
    // }


    // static int solve(String word1, String word2, int x, int y, int[][] dp) {
        
    //     if (x == word1.length()) 
    //         return y==word2.length() ? 0 : word2.length()-y;

    //     if (y == word2.length()) 
    //         return 1+ solve(word1, word2, x+1, y, dp);

    //     if (dp[x][y] != -1) return dp[x][y];

    //     int ans=0;
    //     if(word1.charAt(x) == word2.charAt(y)) {
    //         ans = solve(word1, word2, x+1, y+1, dp);
    //     } else {
    //         // insert
    //         ans = 1+ solve(word1, word2, x, y+1, dp);
    //         // replace
    //         ans = Math.min(ans, 1+solve(word1, word2, x+1, y+1, dp));
    //         // rem
    //         ans = Math.min(ans, 1+solve(word1, word2, x+1, y, dp));
    //     }

    //     return dp[x][y] = ans;
    // }

    // static int findMin(List<int[]> cnt) {
    //     return Math.min(cnt.get(0)[0] + cnt.get(1)[1], 
    //     cnt.get(0)[1] + cnt.get(1)[0]);
    // }


    // static int[][] dp = new int[301][301];
    // static int find(char[][] mat, int i, int j, int R, int C) {
    //     if(i >= R || j >= C) return 0;
        
    //     if (dp[i][j] != -1) return dp[i][j];

    //     int mini = Math.min(find(mat, i+1, j, R, C), find(mat, i, j+1, R, C));
    //     mini = Math.min(find(mat, i+1, j+1, R, C), mini);

    //     if (mat[i][j] == '0') return dp[i][j] = 0;
    //     else return dp[i][j] = 1+mini;
    // }

    // static int[][][] dp = new int[101][2][1001];

    // static int solve(int ind, int[] prices, int trans, int buy) {
    //     if (ind == prices.length) return 0;

    //     if (dp[trans][buy][ind] != -1) return dp[trans][buy][ind];
    //     int ans=solve(ind+1, prices, trans, buy);

    //     if (buy == 1) {
    //         if (trans > 0) {
    //             ans = Math.max(ans, solve(ind+1, prices, trans, 0) - prices[ind]);
    //         }
    //     } else {
    //         ans = Math.max(ans, prices[ind] + solve(ind+1, prices, trans-1, 1));
    //     }
        
    //     return dp[trans][buy][ind] = ans;
    // }

    // static int get(int num) {
    //     int cnt=0;
    //     while(num%5==0) {
    //         num/=5;
    //         cnt++;
    //     }
    //     return cnt;
    // }

    

    public static void main(String... args) {

        String encoded = " b  ac";
        int rows=2;

        int len=encoded.length();
        int col=len/rows;
        StringBuilder ans=new StringBuilder();
        for(int i=0; i<col; ++i) {
            int j=i;
            while(j<len) {
                ans.append(encoded.charAt(j));
                j+=(col+1);
            }
        }
        
        StringBuilder decoded = new StringBuilder();
        for(int i=ans.length()-1; i>=0; --i) {
            if (ans.charAt(i) == ' ') continue;
            else {
                while(i >= 0) {
                    decoded.append(ans.charAt(i--));
                }
                break;
            }   
        }
        
        log(decoded.reverse().toString().length());
        

        // int[] nums = {3,2,3,1,2,4,5,5,6};
        // int k=4;

        // Queue<Integer> pq = new PriorityQueue<>();
        // for(int i: nums) {
        //     pq.offer(i);
        //     if (pq.size()>k) {
        //         pq.poll();
        //     }
        // }

        // int ans=pq.poll();

        // int left = 2147483646, right = 2147483647;
        // int ans=0;
        // for(long i=30; i>=0; --i) {
        //     if (((1L << i) & left) > 0) {
        //         // how many skips required to unset current bit ??
        //         long p = (long) Math.pow(2, i);
        //         long rem=(long) left % p;
        //         if ((long) left + (p-rem) > (long) right) 
        //             ans |= (1 << i);
        //     }
        // }

        // create a executor service using fixed thread pool
        // ExecutorService executor = Executors.newFixedThreadPool(3);
        // submit task to run via executor
        // for(int i=0; i<10; i++) {
        //     final int x=i;
        //     executor.submit(() -> {
        //         System.out.println("Task "+x+ " executed by thread: "+Thread.currentThread().getName());
        //     });
        // }

        // // finally
        // executor.shutdown();

        // CompletableFuture.supplyAsync(() -> {
        //     return "HI";
        // })
        // .whenComplete((res, ex) -> {
        //     if (ex != null) 
        //         System.out.println("Exception occurred: "+ ex.getMessage());
        // })
        // .thenAccept((s) -> System.out.println(s));

        // executor.shutdown();

        // future.thenAccept((st) -> {
        //     System.out.println(st);
        // });

        // future.whenComplete((res, ex) -> {
        //     if (ex != null) {
        //         System.out.println("success");
        //     }
        // });

        // future.join();


        // int n = 10000;

        // int ans=0;
        // for(int i=n; i>=5; --i) {
        //     ans += get(i);
        // }
        
        
        // int[] prices = {3,2,6,5,0,3};
        // int k=2;

        // for(int i=0; i<101; i++) 
        //     for(int j=0; j<2; j++) Arrays.fill(dp[i][j], -1);

        // int i=solve(0, prices, k, 1);
        // log(i);

        // char[][] matrix = {
        //     {'1','0','1','0','0'},
        //     {'1','0','1','1','1'},
        //     {'1','1','1','1','1'},
        //     {'1','0','0','1','0'}
        // };

        // for(int i=0; i<301; i++) Arrays.fill(dp[i], -1);
        // int R=matrix.length, C=matrix[0].length;

        // find(matrix, 0, 0, R, C);
        
        // int ans=-1;
        // for(int i=0; i<301; i++) {
        //     for(int j=0; j<301; j++) {
        //         ans = Math.max(ans, dp[i][j]);
        //     }
        // }

        // brute -> better/optimal

        // String s = "10001100101000000";

        // int n=s.length();
        // StringBuilder s1 = new StringBuilder();
        // StringBuilder s2 = new StringBuilder();

        // for(int i=0; i<2*n; ++i) {
        //     if (i%2 == 0) {
        //         s1.append('0');
        //         s2.append('1');
        //     } else {
        //         s1.append('1');
        //         s2.append('0');
        //     }
        // }

        // log(s1);
        // log(s2);
        // int ans=0, a1=0, a2=0;
        // for(int i=0; i<n; ++i) {
        //     if (s.charAt(i) != s1.charAt(i)) a1++;
        //     if (s.charAt(i) != s2.charAt(i)) a2++;
        // }

        // ans = Math.min(a1, a2);
        // s+=s;
        // for(int l=0, r=n; r<2*n; ++r) {
        //     if (s.charAt(r) != s1.charAt(r)) a1++;
        //     if (s.charAt(r) != s2.charAt(r)) a2++;

        //     if (s.charAt(l) != s1.charAt(l)) --a1;
        //     if (s.charAt(l) != s2.charAt(l)) --a2;
        //     ++l;

        //     ans=Math.min(ans, Math.min(a1, a2));
        // }

        
        // String word1 = "", word2 = "a";

        // int[][] dp = new int[501][501];
        // for(int i=0; i<501; i++)
        //     Arrays.fill(dp[i], -1);

        // int x = solve(word1, word2, 0, 0, dp);
        // log(x);        
        // String s = "babad";

        // int ans=0;
        // StringBuilder palin = new StringBuilder();
        // for(int i=0; i<s.length(); i++) {
        //     int l=i, r=i;
        //     while(l >=0 && r < s.length() && s.charAt(l)==s.charAt(r)) {
        //         l--;
        //         r++;
        //     }

        //     if (r-l-1 > ans) {
        //         ans = r-l-1;
        //         palin = new StringBuilder();
        //         for (int x=l+1; x<r; x++) palin.append(s.charAt(x));
        //     }
        //     l=i; 
        //     r=i+1;
        //     while(l>=0 && r<s.length() && s.charAt(l) == s.charAt(r)) {
        //         l--;
        //         r++;
        //     }
        //     if (r-l-1 > ans) {
        //         ans = r-l-1;
        //         palin = new StringBuilder();
        //         for (int x=l+1; x<r; x++) palin.append(s.charAt(x));
        //     }

        // }

        // int n=4, k=11;
        // StringBuilder sb = new StringBuilder("0");
        // n-=1;
        // while (n-- > 0) {
        //     int len=sb.length();
        //     sb.append('1');
        //     for(int i=len-1; i>=0; --i) {
        //         if (sb.charAt(i) == '0') 
        //             sb.append('1');
        //         else 
        //             sb.append('0');
        //     }
        // }

        // log(sb.toString());
        // log(sb.charAt(k-1));
        // int[][] obs = {{0,1},{0,0}};
        // int[][] dp = new int[205][205];
        // for(int i=0; i<205; i++)
        //     Arrays.fill(dp[i], -1);
        
        // int x=solve(obs, 0, 0, dp);
        // log(x);

        // int minValue = Integer.MIN_VALUE;
        // for(int[] row: grid) {
        //     int t=row[0];
        //     for(int i: row) t=Math.min(t, i);
        //     minValue = Math.max(minValue, t);
        // }

        // log(minValue);
        // List<Integer> valuesFromRow = new ArrayList<>();

        // for(int[] row: grid) {
        //     int mismatch = 18, val=Integer.MAX_VALUE;
        //     for(int num: row) {
        //         int curr_mis = 0;
        //         for(int i=0; i<18; i++) {
        //             if (((1 << i) & minValue) == 0 && ((1 << i) & num) > 0) {
        //                 curr_mis++;
        //             } 
        //         }

        //         if (curr_mis < mismatch) {
        //             mismatch = curr_mis;
        //             val = num;
        //         }
        //     }

        //     valuesFromRow.add(val);
        // }
        
        // log(valuesFromRow);
        // int ans=valuesFromRow.get(0);
        // for(int i: valuesFromRow) ans |= i;
        // log(ans);
        // List<List<Integer>> triangle = Arrays.asList(
        //     Arrays.asList(2),
        //     Arrays.asList(3,4),
        //     Arrays.asList(6,5,7),
        //     Arrays.asList(4,1,8,3)
        // );


        // int[][] dp = new int[205][205];
        // Arrays.fill(dp[triangle.size()], 0);
        // for(int i=triangle.size()-1; i>=0; i--) {
        //     for(int j=triangle.get(i).size()-1; j>=0; j--) {
        //         dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i+1][j], dp[i+1][j+1]);
        //     }
        // }

        // String s = "abcd";
        // List<String> wordDict = Arrays.asList("a","abc","b","cd");
        
        // boolean[] dp = new boolean[305];
        // Arrays.fill(dp, false);
        // Set<String> words = new HashSet<>(wordDict);
        
        // dp[s.length()] = dp[s.length()+1] = true;
        // for(int i=s.length()-1; i>=0; i--) {
        //     StringBuilder tmp =new StringBuilder();
        //     for(int ind=i; ind < s.length(); ind++) {
        //         tmp.append(s.charAt(ind));
        //         if (words.contains(tmp.toString())) {
        //             dp[i] = dp[i] || dp[ind+1];
        //         }
        //     }
        // }

        // boolean[] dp = new boolean[305];
        // dp[s.length()+1] = dp[s.length()] = true;
        
        // for(int i=s.length()-1; i>=0; i--) {
            
            
        // }

        



        // int[] dp = new int[(int)1e5+2];
        // Arrays.fill(dp, -1);

        // dp[nums.length] = dp[nums.length+1] = 0;
        // for(int i=nums.length-1; i>=0; i--) {
        //     dp[i] = dp[i+1];
        //     dp[i] = Math.max(dp[i], nums[i] + dp[i+2]);
        // }

        // int[] cnt = new int[]{0, 0};
        // for(int i=0; i<t.length(); i++) {
        //     if(t.charAt(i) == '0') cnt[0]++;
        //     else cnt[1]++;
        // }

        // StringBuilder ans = new StringBuilder();
        // for(int i=0; i<s.length(); i++) {
            
        //     if(s.charAt(i) == '0' && cnt[1] > 0) {
        //         ans.append('1');
        //         cnt[1]--;
        //     } else if (s.charAt(i)=='1' && cnt[0] > 0) {
        //         ans.append('0');
        //         cnt[0]--;
        //     } else {
        //         if (cnt[0] > 0) {
        //             ans.append('0');
        //             cnt[0]--;
        //         } else {
        //             ans.append('1');
        //             cnt[1]--;
        //         }
        //     }
        // }

        // StringBuilder xor = new StringBuilder();
        // for(int i=0; i<ans.length(); i++) {
        //     if (ans.charAt(i) == s.charAt(i)) xor.append('0');
        //     else xor.append('1');
        // }

        // int[] nums = {10,1,3,9};
        // int[] colors = {1,1,1,2};

        // long[] dp = new long[(int)1e5+2];
        // Queue<Integer> q = new ArrayDeque<>();
        // PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Long.compare((long) a, (long) b));


        // dp[nums.length] = dp[nums.length+1] = 0;
        // for(int i=nums.length-1; i>=0; i--) {
        //     dp[i] = dp[i+1];
        //     if(i < nums.length-1 && colors[i]==colors[i+1]) 
        //         dp[i] = Math.max(dp[i], nums[i] + dp[i+2]);
        //     else 
        //         dp[i] = Math.max(dp[i], nums[i] + dp[i+1]);
        // }

        /// for IPair use int[]{a, b}

        // int[] nums = {97972481,930598563,314313375,175573412,162190171,219104574,422802583,607950364,592864533,976554265,812497864,680816749,759971788,125263581,87657934,716673807,303336691,71618316,697802577,474649598,373446233,84929297,966011901,774697275,401252862,349443940,541515978,722312906,479444254,439036905,586856629,456283761,486432108,366271311,607068433,567537885,558649446};
        // long k=32999014233L;

        // TreeMap<Integer, Integer> mp = new TreeMap<>();
        
        // long ans=0;
        // for(int l=0, r=0; r<nums.length; r++) {
        //     mp.compute(nums[r], (key, v) -> v==null ? 1 : v+1);
        //     int min=mp.firstKey(), max=mp.lastKey();
        //     while(l<=r && (long)(max-min)*(long)(r-l+1) > k) {
        //         if(mp.get(nums[l]) == 1) mp.remove(nums[l]);
        //         else mp.put(nums[l], mp.get(nums[l])-1);
        //         l++;
        //         max=mp.lastKey();
        //         min=mp.firstKey();
        //     }
        //     ans += (r-l+1);
        // }

        // log(ans);

        // int[] nums = {6,2,1,2,4,5};
        // int x=5;

        // for(int i=0; i<2; i++) Arrays.fill(dp[i], -1);
        // long ans=solve(0, nums, 0);
        
        // dp[0][nums.length] = 0;
        // dp[1][nums.length] = 0;

        // for(int ind=nums.length-1; ind>=0; ind--) {
        //     for(int parity=0; parity<2; parity++) {
        //         // skipping
        //         dp[parity][ind] = dp[parity][ind+1];
        //         if (parity == 0) {
        //             dp[parity][ind] = Math.max(dp[parity][ind], nums[ind] + dp[1][ind+1]);
        //         } else {
        //             dp[parity][ind] = Math.max(dp[parity][ind], dp[0][ind+1] - nums[ind]);
        //         }
        //     }
        // }

        // long ans = dp[0][0];
        // log(ans);
        
        // int[] nums = {1,3,2,5,6,2,9,12,14,11,2,2,3,4};
        // int eans = solve(nums, 0, 0);
        // int oans = solve(nums, 0, 1);

        // int[] coins = {357,239,73,52};
        // int amt=9832;
        // for(int i=0; i<13; i++) Arrays.fill(dp[i], -1);
        // long xx = solve(0, coins, amt);
        // log(xx);

        // int[] candidates = {2,3,6,7};
        // int target = 7;

        // permute(0, candidates, target, 0, new ArrayList<>());
        // log(perms);

        // int[][] preq = {{1,0}};
        // int numCourses=2;

        // int cnt=0;
        // int[] indeg = new int[numCourses];
        // Arrays.fill(indeg, 0);
        // ArrayList<Integer>[] gr = new ArrayList[numCourses];
        // for(int i=0; i<numCourses; i++) {
        //     gr[i] = new ArrayList<>();
        // }
        // for(int[] i: preq) {
        //     indeg[i[1]]++;
        //     gr[i[0]].add(i[1]);
        // }

        // // kahn's algo for topological sorting
        // Queue<Integer> q = new ArrayDeque<>();
        // for(int i=0; i<numCourses; i++) {
        //     if (indeg[i] == 0) q.offer(i);
        // }
        
        // while(!q.isEmpty()) {
        //     int u=q.poll();
        //     cnt++;
        //     for(int adj: gr[u]) {
        //         if (--indeg[adj] == 0) q.offer(adj);
        //     }
        // }

        // log(cnt);

        // char[][] board = {
        //     {'X','X','X','X'},
        //     {'X','O','O','X'},
        //     {'X','X','O','X'},
        //     {'X','O','X','X'}
        // };


        // char[][] board2 = board;
        // for(int i=0; i<board.length; i++) {
        //     for(int j=0; j<board[0].length; j++) {
        //         board2[i][j] = 'X';
        //     }
        // }
        
        // log(board[3][1]);

        // for(int i=0; i<board[0].length; i++) {
        //     // first row
        //     if(board[0][i] == 'O') 
        //         bfsMark(0, i, board, board2);
        //     // last row
        //     if(board[board.length-1][i] == 'O') {
        //         bfsMark(board.length-1, i, board, board2);
        //         log("last");
        //     }
        // }

        // for(int i=0; i<board.length; i++) {
        //     // first col
        //     if (board[i][0] == 'O') bfsMark(i, 0, board, board2);
        //     // last col
        //     if (board[i][board[0].length-1] == 'O') 
        //         bfsMark(i, board[0].length-1, board, board2);
        // }
        
        // int islands=0;

        // int R=grid.length, C = grid[0].length;
        // for(int i=0; i<R; i++) {
        //     for(int j=0; j<C; j++) {
        //         if (grid[i][j] == '1') {
        //             ++islands;
        //             bfsMark(i, j, grid);
        //         }
        //     }
        // }

        // log(islands);

        // String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};

        // Stack<Integer> st = new Stack<>();
        // for(int i=0; i<tokens.length; i++) {
        //     char ch = tokens[i].charAt(0);
        //     if (Character.isDigit(ch) || tokens[i].length() > 1) {
        //         StringBuilder t = new StringBuilder();
        //         for(int j=0; j<tokens[i].length(); j++) t.append(tokens[i].charAt(j));
        //         st.push(Integer.parseInt(t.toString()));
        //     }
        //     else {
        //         int a = st.pop();
        //         int b = st.pop();
        //         if (ch == '+') st.push(a+b);
        //         else if(ch == '-') st.push(b-a);
        //         else if(ch == '*') st.push(a*b);
        //         else st.push(b/a);
        //     }

        //     log(st);
        // }

        // int ans=st.pop();
        // log(ans);
        
        // int[][] intervals = {};
        // int[] nw = {6, 8};
    
        // int[][] nIntervals = new int[intervals.length+1][2];
        // for(int i=0; i<intervals.length; i++) {
        //     nIntervals[i] = intervals[i];
        // }
        // nIntervals[intervals.length] = nw;
        // Arrays.sort(nIntervals, (int[] a, int[] b) -> a[0]-b[0]);
        // List<List<Integer>> t = new ArrayList<>();
        // for(int i=0; i<nIntervals.length;) {
        //     int start = nIntervals[i][0], end=nIntervals[i][1];
        //     int j=i+1;
        //     while(j < nIntervals.length && nIntervals[j][0] <= end) {
        //         end = Math.max(end, nIntervals[j][1]);
        //         j++;
        //     }
        //     i=j;
        //     t.add(Arrays.asList(start, end));
        // }
        
        // log(t);
        // int[][] ans = new int[t.size()][];
        // for(int i=0; i<t.size(); i++) {
        //     ans[i] = t.get(i).stream().mapToInt(Integer::intValue).toArray();
        // }
        
        // String s="abcabcbb";
        
        // int l=0, r=0, ans=0;
        // Map<Character, Integer> mp = new HashMap<>();
        // Character extra = null;
        // for(; r<s.length(); r++) {
        //     mp.put(s.charAt(r), mp.getOrDefault(s.charAt(r), 0)+1);
        //     if (mp.get(s.charAt(r)) > 1) extra=s.charAt(r);
        //     while(extra != null) {
        //         if (Character.valueOf(s.charAt(l)) == extra) extra=null;
        //         mp.put(s.charAt(l), Math.max(0, mp.get(s.charAt(l))-1));
        //         l++;
        //     }
        //     ans = Math.max(ans, r-l+1);
        // }

        
        // Queue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        // int l=0, r=heights.length-1;
        // int ans=0;
        // while(l < r) {
        //     ans = Math.max(ans, (r-l)*Math.min(heights[l], heights[r]));
        //     if (heights[l] > heights[r]) r--;
        //     else if(heights[l] < heights[r]) l++;
        //     else {
        //         l++;
        //         r--;
        //     }
        // }

        // Set<Integer> riders =new HashSet<>();
        // TreeMap<Integer, Integer> driverMp = new TreeMap<>();
        // TreeMap<Integer, Integer> riderMp = new TreeMap<>();

        // int timer=1;

        // riders.add(1);
        // riders.contains(riderMp)

        // int[] nums = {3,7};
        // TreeMap<Integer, Integer> mp = new TreeMap<>();
        // for(int i=0; i<nums.length; i++) {
        //     mp.compute(nums[i], (k,v) -> v==null ? 1 : v+1);
        // }

        // boolean alice=true;
        // int last = -1;
        // while(!mp.isEmpty()) {
        //     // alice will maximize, bob will minimize
        //     if (alice) {
        //         var e = mp.firstEntry();
        //         last = e.getKey();
        //         if (e.getValue() == 1) mp.remove(last);
        //         else mp.put(last, e.getValue()-1);
        //     } else {
        //         var e=mp.lastEntry();
        //         last = e.getKey();
        //         if (e.getValue() == 1) mp.remove(last);
        //         else mp.put(last, e.getValue()-1);
        //     }
        //     alice = !alice;
        // }

        /// Dijkstra's Algorithm
        // int[][] edges = {{2,3,25}, {2,1,18}, {3,1,2}};
        // int n=4;

        // Map<Integer, List<int[]>> graph = new HashMap<>();
        // for(int[] e: edges) {
        //     graph.compute(e[0], (k, v) -> {
        //         if (v == null) v = new ArrayList<>();
        //         v.add(new int[] {e[1], e[2]});
        //         return v;
        //     });

        //     graph.compute(e[1], (k, v) -> {
        //         if (v == null) v = new ArrayList<>();
        //         v.add(new int[] {e[0], 2*e[2]});
        //         return v;
        //     });
        // }

        // {dist, node}
        // Queue<int[]> pq = new PriorityQueue<>((p1, p2) -> p1[0] - p2[0]);
        // int[] dist = new int[n];
        // Arrays.fill(dist, Integer.MAX_VALUE);
        // dist[0] = 0;
        // pq.offer(new int[] {0,0});
        // while(!pq.isEmpty()) {
        //     var p = pq.poll();
        //     int node=p[1], curr_dist = p[0];
        //     if(!graph.containsKey(node)) continue;
        //     for(var adj: graph.get(node)) {
        //         if (curr_dist + adj[1] < dist[adj[0]]) {
        //             dist[adj[0]] = curr_dist+adj[1];
        //             pq.offer(new int[] {dist[adj[0]], adj[0]});
        //         }
        //     }
        // }

        // log(dist);

        // List<IPair> ls = new ArrayList<>();
        // IPair ip = new IPair(1, 2);
        // ls.add(new IPair(3, 4));
        // log(ls);
        // log(ip.first);

        // String s = "011001";
        // List<Integer> cnt = new ArrayList<>();
        // for(int i=0; i<s.length(); ) {
        //     int z=0, o =0 ;
        //     if (s.charAt(i) == '0') {
        //         while(i < s.length() && s.charAt(i) == '0') {
        //             z++;
        //             i++;
        //         }
        //         cnt.add(z);
        //     } else {
        //         while(i < s.length() && s.charAt(i) == '1') {
        //             o++;
        //             i++;
        //         }
        //         cnt.add(o);
        //     }
        // }

        // log("cnt: {}", cnt);
        // int ans=0;
        // for(int i=0; i<cnt.size()-1; i++) {
        //     ans += Math.min(cnt.get(i), cnt.get(i+1));
        // }

        // log(ans);

        // for(int i=0; i<)

        // String s = "ABCDE";
        // int rows = 4;

        // List<String> ans = new ArrayList<>();
        // StringBuilder sb = new StringBuilder();
        // for(int i=0; i<s.length(); i++) {
        //     if(sb.length() < rows) {
        //         sb.append(s.charAt(i));
        //     } else {
        //         ans.add(sb.toString());
        //         sb = new StringBuilder();
        //         int j=i;
        //         sb.append('0');
        //         for(; j<i+(rows-2); j++) {
        //             if (j < s.length())
        //                 sb.append(s.charAt(j));
        //             else sb.append('0');
        //         }
        //         sb.append('0');
        //         ans.add(sb.reverse().toString());
        //         sb = new StringBuilder();
        //         i=j-1;
        //     }
        // }

        // if (sb.length() > 0) {
        //     while(sb.length() < rows) {
        //         sb.append('0');
        //     }
        //     ans.add(sb.toString());
        // }
        
        // log(ans);
        // StringBuilder res = new StringBuilder();
        // for(int i=0; i<rows; i++) {
        //     for(String word: ans) {
        //         if (word.length() > i && word.charAt(i) != '0') 
        //             res.append(word.charAt(i)); 
        //     }
        // }
        

        // log(res.toString());

        // int[] nums = {5,4,-9,6};
        // int k=2;

        // List<Integer> lst = new ArrayList<>();
        // Map<Integer, Integer> mp = new HashMap<>();
        // for(int i=0; i<nums.length; i++) {
        //     if(nums[i] >= 0) lst.add(nums[i]);
        //     else mp.put(i, nums[i]);
        // }
        
        // int N=lst.size();
        // int[] rotated = new int[N];
        // for(int i=0; i<lst.size(); i++) {
        //     if (k > N) k %= N;
        //     rotated[((i-k)+N)%N] = lst.get(i);
        // }

        // int[] ans=new int[nums.length];
        // for(int i=0, j=0; i<nums.length; i++) {
        //     if(mp.containsKey(i)) ans[i] = mp.get(i);
        //     else ans[i] = rotated[j++];
        // }

        // log(ans);

        // int n=4;
        // int[][] edges={{0,1},{0,2},{0,3}};
        // int x=1, y=2, z=3;

        // Map<Integer, List<Integer>> graph = new HashMap<>();
        // for(int[] e:edges) {
        //     graph.compute(e[0], (k, v) -> {
        //         if (v == null) v = new ArrayList<>();
        //         v.add(e[1]);
        //         return v;
        //     });

        //     graph.compute(e[1], (k, v) -> {
        //         if (v==null) v=new ArrayList<>();
        //         v.add(e[0]);
        //         return v;
        //     });
        // }
        // int[] dx=new int[n];
        // int[] dy=new int[n];
        // int[] dz=new int[n];

        // Arrays.fill(dx, -1);
        // Arrays.fill(dy, -1);
        // Arrays.fill(dz, -1);

        // vis.clear();
        // findDistance(x, dx, graph, 0);
        // vis.clear();
        // findDistance(y, dy, graph, 0);
        // vis.clear();
        // findDistance(z, dz, graph, 0);

        // int ans=0;

        // for(int i=0; i<n; i++) {
        //     int[] dist = new int[3];
        //     dist[0] = dx[i];
        //     dist[1] = dy[i];
        //     dist[2] = dz[i];
        //     Arrays.sort(dist);
        //     if ((dist[2]*dist[2]) == Math.pow(dist[0], 2) + Math.pow(dist[1], 2)) ans++;
        // }

        // int[] nums = {1,2,3};
        // int[] target = {2,1,3};
        
        // Set<Integer> st = new HashSet<>();
        // for(int i=0; i<nums.length; i++) {
        //     if(nums[i] != target[i]) st.add(nums[i]);
        // }
        
        // int N=bl.length;
        // long ans = 0;
        // for(int i=0; i<N; i++) {
        //     for(int j=i+1; j<N; j++) {
        //         if ((i != j) && (bl[j][0] < tr[i][0] && tr[j][0] > bl[i][0]) && (bl[j][1] < tr[i][1] && tr[j][1] > bl[i][1])) {
                    
        //             log("i: {}, j: {}", i, j);

        //             long x1 = Math.max(bl[i][0], bl[j][0]);
        //             long y1 = Math.max(bl[i][1], bl[j][1]);
        //             long x2 = Math.min(tr[i][0], tr[j][0]);
        //             long y2 = Math.min(tr[i][1], tr[j][1]);
        //             long len = Math.min(y2-y1, x2-x1);
        //             ans = Math.max(ans, len*len);
        //         }
        //     } 
        // }

        // log("ans: {}", ans);

        // int[][] squares = {{0,0,2}, {1,1,1}};

        // double y = 0, total_area=0;
        // double l = Double.MAX_VALUE, h=Double.MIN_VALUE;
        // for(int[] sq: squares) {
        //     l = Math.min(l, sq[0]);
        //     h = Math.max(h, sq[1]+sq[2]);
        //     total_area += sq[2] * sq[2];
        // }

        // double rem=total_area/2;
        // while(l < h) {
        //     if (h-l < Math.pow(10, -5)) break;
        //     double curr=0, mid=l+(h-l)/2;
        //     for(int[] sq: squares) {
        //         if (sq[1] < mid) {
        //             double y2=sq[1]+sq[2];
        //             if(y2 <= mid) {
        //                 curr += sq[2]*sq[2];
        //             } else {
        //                 curr += sq[2] * (mid - sq[1]);
        //             }
        //         }
        //     }
        //     log("curr: {} y: {}", curr, y);
            
        //     if (curr < rem) l=mid;
        //     else h=mid;
        // }

        // log(l);

        // String[] words = {"ab","aa","za","aa"};
        // Map<String, Long> mp = new HashMap<>();
        // for(var w:words) {
        //     int diff = w.charAt(0) - 'a';
        //     StringBuilder sb = new StringBuilder(w.charAt(0));
        //     for(int i=1; i<w.length(); i++) {
        //         int ii = (((w.charAt(i) - 'a') + 26 - diff) % 26) + 'a';
        //         sb.append((char) ii);
        //     }
        //     mp.put(sb.toString(), mp.getOrDefault(sb.toString(), 0L)+1);
        // }

        // long pairs=0;
        // for(var e: mp.entrySet()) {
        //     var n = e.getValue();
        //     pairs += (n*(n-1))/2;
        // }

        // Util.print(pairs);
        // int pairs = 0;
        // for(var w:words) {
        //     if (mp.get(w) == 1) mp.remove(w);
        //     else mp.put(w, mp.get(w)-1);
        //     pairs += mp.getOrDefault(w, 0);
        //     for(var r: rotate(w)) {
        //         pairs += mp.getOrDefault(r, 0);
        //     }
        // }

        // Util.print(pairs);

        // int n =8;
        // int[] diff = {3,5,2,4,2,3,1};
        // int[][] res = {{3,2}};

        // long[] a = new long[n];
        // Arrays.fill(a, Long.MAX_VALUE);
        // a[0]=0;
        // for(int[] r: res) a[r[0]] = r[1];

        // // a[i+1] <= a[i] + diff[i]
        // // a[i] <= a[i+1] + diff[i] 
        // for(int i=0; i<n-1; i++) {
        //     a[i+1] = Math.min(a[i+1], a[i]+diff[i]); 
        // }

        // for(int i=n-2; i>=0; i--) {
        //     a[i] = Math.min(a[i], a[i+1]+diff[i]);
        // }
        
        // long max_value = Arrays.stream(a).max().orElse(-1);
        // Util.print(max_value);


        // int[] cost = {10,5,8};
        // String s = "aaa";
        
        // Map<Character, Long> mp = new HashMap<>();
        // for(int i=0; i<s.length(); i++) {
        //     mp.put(s.charAt(i), mp.getOrDefault(s.charAt(i), 0L)+cost[i]);
        // }

        // long totalCost = mp.entrySet().stream().map(Map.Entry::getValue).reduce(0L, (sum, ele) -> sum+ele);
        // long minCost = Long.MAX_VALUE;
        // for(var entry: mp.entrySet()) {
        //     minCost = Math.min(minCost, totalCost-entry.getValue());
        // }

        // Util.print(minCost);
        
    }
}