import java.util.Arrays;

public class DSU {
    int[] par, sz;
    // Constructor
    public DSU(int n) {
        par = new int[n];
        sz = new int[n];
        Arrays.fill(sz, 1);
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
    }

    // Find with path compression
    public int find(int node) {
        if (par[node] == node) return node;
        return par[node] = find(par[node]);
    }

    // Union by size
    public void union(int nodeA, int nodeB) {
        int u = find(nodeA);
        int v = find(nodeB);
        if (u == v) return;

        if (sz[u] > sz[v]) {
            par[v] = u;
            sz[u] += sz[v];
        } else {
            par[u] = v;
            sz[v] += sz[u];
        }
    }
    // Disconnect (Make node its own parent and reduce size)
    public void disconnect(int n) {
        int parent = find(n);
        sz[parent]--;
        par[n] = n;
    }
}
