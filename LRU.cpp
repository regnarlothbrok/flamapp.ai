#include<bits/stdc++.h>
#define fl(i,n) for(int i = 0; i<n; i++)
using namespace std;
#define ll long long
void swap(int *a, int *b)  {
    int temp = *a;
    *a = *b;
    *b = temp;
}



class LRUCache {
private:
    // Doubly Linked List Node
    struct Node {
        int key, value;
        Node* prev;
        Node* next;
        Node(int k, int v) : key(k), value(v), prev(nullptr), next(nullptr) {}
    };

    int capacity;
    unordered_map<int, Node*> cache;
    Node* head;
    Node* tail;

    void remove(Node* node) {
        node->prev->next = node->next;
        node->next->prev = node->prev;
    }

    void insert(Node* node) {
        node->next = head->next;
        node->prev = head;
        head->next->prev = node;
        head->next = node;
    }

public:
    LRUCache(int capacity) : capacity(capacity) {
        head = new Node(0, 0); // dummy head
        tail = new Node(0, 0); // dummy tail
        head->next = tail;
        tail->prev = head;
    }

    int get(int key) {
        if (cache.find(key) != cache.end()) {
            Node* node = cache[key];
            remove(node);
            insert(node);
            return node->value;
        }
        return -1;
    }

    void put(int key, int value) {
        if (cache.find(key) != cache.end()) {
            Node* node = cache[key];
            node->value = value;
            remove(node);
            insert(node);
        } else {
            if (cache.size() == capacity) {
                Node* lru = tail->prev;
                remove(lru);
                cache.erase(lru->key);
                delete lru;
            }
            Node* node = new Node(key, value);
            cache[key] = node;
            insert(node);
        }
    }

    ~LRUCache() {
        Node* current = head;
        while (current) {
            Node* next = current->next;
            delete current;
            current = next;
        }
    }
};

void solve(){
    LRUCache lru(2);
    lru.put(1, 1);
    lru.put(2, 2);
    cout << lru.get(1) << endl; // returns 1
    lru.put(3, 3);              // evicts key 2
    cout << lru.get(2) << endl; // returns -1
    lru.put(4, 4);              // evicts key 1
    cout << lru.get(1) << endl; // returns -1
    cout << lru.get(3) << endl; // returns 3
    cout << lru.get(4) << endl; // returns 4


}
int main(){
 ios::sync_with_stdio(0);
 cin.tie(0);
     int t=1;
    //  cin>>t;
     while(t--)solve();
    return 0;
}




