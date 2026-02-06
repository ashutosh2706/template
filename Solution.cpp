#include <bits/stdc++.h>
#define dbg(x) cerr<<#x<<" = ";_print(x);cerr<<endl;

using namespace std;

void _print(const long long&);
void _print(const int&);
void _print(const char&);
void _print(const long&);
void _print(const double&);
void _print(const string&);
template <class T, class V> void _print(pair<T,V>);
template <class T, class V> void _print(map<T, V>);
template <class T, class V> void _print(unordered_map<T, V>);
template <class T> void _print(vector<T>);
template <class T> void _print(set<T>);
template <class T> void _print(unordered_set<T>);
template <class T> void _print(multiset<T>);
template <class T> void _print(stack<T>);
template <class T> void _print(queue<T>);
template <class T> void _print(priority_queue<T>);
template <class T> void _print(priority_queue<T,vector<T>,greater<T>>);

void _print(const long long& x) { 
    cerr << x; 
}

void _print(const int& x) {
    cerr << x; 
}

void _print(const char& x) { 
    cerr << x; 
}

void _print(const long& x) { 
    cerr << x; 
}

void _print(const double& x) { 
    cerr << x; 
}

void _print(const string& x) { 
    cerr << x; 
}

template <class T, class V> void _print(pair<T, V> p) {
    cerr << "{ ";
    _print(p.first);
    cerr << " : ";
    _print(p.second);
    cerr << " }";
}

template <class T, class V> void _print(map<T, V> v) { 
    cerr << "[ "; 
    for (auto i : v) { 
        _print(i); 
        cerr << " "; 
    } 
    cerr << " ]";
}

template <class T, class V> void _print(unordered_map<T, V> v) {
    cerr << "[ ";
    for (auto i : v) {
        _print(i);
        cerr << " ";
    }
    cerr<<" ]";
}

template <class T> void _print(vector<T> v) { 
    cerr << "[ "; 
    for (T i : v) { 
        _print(i); 
        cerr << " "; 
    } 
    cerr << " ]"; 
}

template <class T> void _print(set<T> v) { 
    cerr << "[ "; 
    for (T i : v) { 
        _print(i); 
        cerr << " "; 
    } 
    cerr << " ]"; 
}

template <class T> void _print(unordered_set<T> v) { 
    cerr << "[ "; 
    for (T i : v) { 
        _print(i); 
        cerr << " "; 
    } 
    cerr << " ]"; 
}

template <class T> void _print(multiset<T> v) { 
    cerr << "[ "; 
    for (T i : v) { 
        _print(i); 
        cerr << " "; 
    } 
    cerr << " ]"; 
}

template <class T> void _print(stack<T> s) { 
    cerr << "\n"; 
    while(!s.empty()) { 
        _print(s.top()); 
        cerr << "\n"; 
        s.pop(); 
    } 
}

template <class T> void _print(queue<T> q) { 
    while(!q.empty()) { 
        _print(q.front()); 
        cerr << " "; 
        q.pop(); 
    } 
}

template <class T> void _print(priority_queue<T> q) { 
    while(!q.empty()) { 
        _print(q.top()); 
        cerr << " "; 
        q.pop(); 
    } 
}

template <class T> void _print(priority_queue<T,vector<T>,greater<T>> q) {
    while(!q.empty()) {
     _print(q.top());
     cerr << " ";
     q.pop();
    }
}



void SieveOfEratosthenes(int n, vector<int>& primes) {
    primes.resize(n+1, 1);
    for (int p = 2; p*p <= n; p++) {
        if(primes[p]) {
            for (int i = p*p; i <= n; i += p)
                primes[i] = 0;
        }
    }
    primes[0] = primes[1] = 0;
}



int nCr(int n, int r) {
    // pascal's triangle implementation
    if(n < r) return 0;
    if((n-r) < r) r = n-r;

    int dp[r+1] = {0};
    dp[0]=1;
    for(int i=1; i<=n; ++i) {
        for(int j=min(r,i); j>0; --j) {
            dp[j] = (dp[j] + dp[j-1])%(int)(1e9+7);
        }
    }

    return dp[r];
}


namespace my
{
    // binary-exponentiation
    int pow(long long a, long long b){
        long long res=1;
        int mod=1e9+7;
        while(b){
            if(b&1)
                res=(res*a)%mod;
            a=(a*a)%mod;
            b>>=1;
        }
        
        return res;
    }
}

/* Disjoint Set Union Data Structure */

class Dsu
{
public:

    vector<int> par, sz;

    Dsu(int n){
        par.resize(n);
        sz.resize(n, 1);
        for(int i=0; i<n; ++i) {
            par[i]=i;
        }
    }

    int Parent(int node){
        if(par[node] == node) return node;
        return par[node] = Parent(par[node]);
    }

    void Union(int nodeA, int nodeB){
        int u=Parent(nodeA);
        int v=Parent(nodeB);
        if(u==v) return;

        if(sz[u] > sz[v]){
            par[v] = u;
            sz[u]+=sz[v];
        } else {
            par[u] = v;
            sz[v]+=sz[u];
        }
    }

    void Disconnect(int n){
        int parent = Parent(n);
        sz[parent]--;
        par[n] = n;
    }


};


vector<string> ans;
int N;
void solve(string s) {
    
    if(s.length() == N) {
        ans.push_back(s);
        return;
    }

    if(s.length() == 0) {
        s.push_back('1');
        solve(s);
        s.pop_back();
        s.push_back('0');
        solve(s);
        s.pop_back();
    } else {
        if(s.back() == '0') {
            s.push_back('1');
            solve(s);
            s.pop_back();
        } else {
            s.push_back('0');
            solve(s);
            s.pop_back();
            s.push_back('1');
            solve(s);
            s.pop_back();
        }
    }
    
}

unordered_map<int, vector<int>> graph;
vector<int> colors;


bool dfs(int node, int col) {

    if(colors[node] == -1) {
        colors[node] = col;
    } else {
        if(colors[node] != col) return 0;
    }

    bool fl = 1;
    for(auto &adj: graph[node]) {
        fl = fl && dfs(adj, !col);
    }

    return fl;
}


void fill(vector<vector<int>> &count, char ch, vector<vector<char>> &grid) {

    int r = grid.size(), c = grid[0].size();

    for(int i=0; i<r; ++i) {
        for(int j=0; j<c; ++j) {
            count[i][j] = (grid[i][j] == ch);
            if(i==0 && j==0) continue;
            if(i > 0 && j > 0) {
                count[i][j] += (count[i-1][j] + count[i][j-1] - count[i-1][j-1]);
            } else if(i == 0) {
                count[i][j] += count[i][j-1];
            } else 
                count[i][j] += count[i-1][j];
        }
    }
} 


void toh(int disc, char source, char dest, char aux) {
    if(disc == 0) return;

    toh(disc-1, source, aux, dest);
    cout << "Move disc " << disc << " from " <<  source << " to " << dest << endl;
    toh(disc-1, aux, dest, source); 
}

bool palin(int s, int e, string &str) {
    while(s <= e) {
        if(str[s] == str[e]) {
            ++s;
            --e;
        } else return 0;
    }

    return 1;
}

int dp[2001];

int solve(int ind, string &s) {
    if(ind == s.length()) return 0;

    if(dp[ind] != -1) return dp[ind];
    int ans = 1e5;
    for(int i=ind; i<s.length(); ++i){
        if(palin(ind, i, s)) {
            ans = min(ans, 1 + solve(i+1, s));
        }
    }

    return dp[ind] = ans;
}


struct node {
    int data;
    struct node *next;
};

typedef struct node node;

class Node {
public:
    int data;
    Node* next;

    Node(int data) {
        this->data = data;
        next = nullptr;
    }
};

// unordered_map<int, vector<int>> graph;
int dij(int N) {

    vector<int> dist(N+1, 1e9);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int,int>>> pq;
    pq.push({0, 0});    // {distance, source}
    dist[0] = 0;
    while(!pq.empty()) {
        int node = pq.top().second;
        int distance = pq.top().first;
        pq.pop();
        for(auto &adj: graph[node]) {
            if(distance + 1 < dist[adj]) {
                dist[adj] = 1 + distance;
                pq.push({dist[adj], adj});
            }
        }
    }

    return dist[N];
}

long long maxEnergyBoost(vector<int>& drinkA, vector<int>& drinkB, int ind, int A) {

    if(ind == (int)drinkA.size()) return 0;

    long long ans = A ? drinkA[ind] : drinkB[ind];
    ans += maxEnergyBoost(drinkA, drinkB, ind+1, A);
    ans = max(ans, maxEnergyBoost(drinkA, drinkB, ind+1, !A));
    return ans;
}

void exec() {
    
    vector<int> drinkA {4,1,1};
    vector<int> drinkB {1,1,3};

    cout << max(drinkA[0] + maxEnergyBoost(drinkA, drinkB, 1, 1),  drinkB[0] + maxEnergyBoost(drinkA, drinkB, 1, 0));

    


}


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);
    #ifndef ONLINE_JUDGE
    freopen("err.txt","w",stderr);
    //freopen("in.txt", "r", stdin);
    #endif
    int tc=1;
    //cin >> tc;
    while(tc--) exec();
    return 0;
}
