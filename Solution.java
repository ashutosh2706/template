import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.math.*;
import java.util.stream.*;

import static utils.Utility.log;
 
public class Solution {

    static int raverse(int i) {
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


    public static void main(String... args) {
        /// for IPair use int[]{a, b}

        int[][] points = {{10,16},{2,8},{1,6},{7,12}};

        Arrays.sort(points, (int[] a, int[] b) -> a[0]-b[0]);

        int ans=0;
        for(int i=0; i<points.length;) {
            int start = points[i][0], end=points[i][1];
            int j=i+1;
            
        }


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