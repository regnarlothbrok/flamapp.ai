#include<bits/stdc++.h>
#define fl(i,n) for(int i = 0; i<n; i++)
using namespace std;
#define ll long long
void swap(int *a, int *b)  {
    int temp = *a;
    *a = *b;
    *b = temp;
}

class MyHashMap {
private:
    static const int SIZE = 10007; 
    vector<pair<int, int>> table[SIZE];

    int hash(int key) {
        return key % SIZE;
    }

public:
    MyHashMap() {}

    void put(int key, int value) {
        int h = hash(key);
        for (auto &p : table[h]) {
            if (p.first == key) {
                p.second = value;
                return;
            }
        }
        table[h].emplace_back(key, value);
    }

    int get(int key) {
        int h = hash(key);
        for (auto &p : table[h]) {
            if (p.first == key) return p.second;
        }
        return -1;
    }

    void remove(int key) {
        int h = hash(key);
        for (auto it = table[h].begin(); it != table[h].end(); ++it) {
            if (it->first == key) {
                table[h].erase(it);
                return;
            }
        }
    }
};
void solve(){
    MyHashMap obj;
    obj.put(1, 10);
    obj.put(2, 20);
    cout << obj.get(1) << endl;  
    cout << obj.get(3) << endl;  
    obj.put(2, 30);
    cout << obj.get(2) << endl;  
    obj.remove(2);
    cout << obj.get(2) << endl;  
}
int main(){
 ios::sync_with_stdio(0);
 cin.tie(0);
     int t=1;
    //  cin>>t;
     while(t--)solve();
    return 0;
}