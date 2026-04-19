import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.math.*;
import java.util.stream.*;

import static utils.Utility.log;
import static utils.Utility.lowerBound;
import static utils.Utility.sieveOfEratosthenes;
import static utils.Utility.upperBound;
 
public class Solution {

    public static void main(String... args) {
        
        // Arrays.stream(dp).forEach(r -> Arrays.fill(r, -1));

        int n=3, m=3;
        int[][] sources = {
            {0,0,1},
            {2,2,2}
        };

        int[][] grid = new int[n][m];
        int[][] time = new int[n][m];

        Queue<int[]> que = new ArrayDeque<>();
        for(int[] src: sources) {
            grid[src[0]][src[1]]=src[2];
            time[src[0]][src[1]]=0;
            que.offer(new int[]{src[0], src[1]});
        }

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        log(grid);
        int curr=1;
        while(!que.isEmpty()) {
            int sz=que.size();
            while(sz-->0) {
                int[] d=que.poll();
                int x=d[0], y=d[1];

                for(int[] dir: dirs) {
                    int xi=x+dir[0], yi=y+dir[1];
                    if (xi >= 0 && xi < n && yi >= 0 && yi < m) {
                        if(grid[xi][yi] == 0) {
                            grid[xi][yi]=grid[x][y];
                            time[xi][yi]=curr;
                            que.offer(new int[]{xi, yi});
                        } else {
                            if (time[xi][yi] == curr) {
                                grid[xi][yi] = Math.max(grid[xi][yi], grid[x][y]);
                            }
                        }
                    }  
                }
            }
            ++curr;
        }

        log(grid);
        log(curr);

        
    }
}