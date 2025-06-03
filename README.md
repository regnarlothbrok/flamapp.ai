# Flamapp.ai Assignment

This repository contains solutions to the Flamapp.ai assignment. The assignment includes the following tasks:

1. LRU (Least Recently Used) Cache Implementation in C++
2. Custom HashMap Implementation in C++
3. Book Review Android Application in Java

---

## Question 1: LRU Cache (C++)

**Objective:**  
Implement an efficient LRU Cache that supports `get` and `put` operations in O(1) time.

**Description:**  
The LRU Cache is built using a combination of a hashmap for fast access and a doubly linked list to maintain usage order. When the cache exceeds its capacity, the least recently used item is evicted.

**File Location:**  
`LRUCache/lru_cache.cpp`

**How to Run:**
```bash
g++ LRU.cpp -o lru_cache
./lru_cache
```

---

## Question 2: Custom HashMap (C++)

**Objective:**  
Implement a basic HashMap without using any built-in hash table libraries.

**Description:**  
This implementation provides `put`, `get`, and `remove` operations. Collisions are handled using separate chaining with linked lists. A simple custom hash function is used to map keys to buckets.

**File Location:**  
`CustomHashMap/custom_hashmap.cpp`

**How to Run:**
```bash
g++ HashMap.cpp -o custom_hashmap
./custom_hashmap
```

---

## Question 3: Book Review Android App (Java)

**Objective:**  
Develop an Android application to browse and review books using MVVM or Clean Architecture.

**Features:**
- Browse a list of books fetched using Retrofit from a fake API
- View book details
- Add books to a favorites list
- Store favorites locally using Room for offline access
- Use LiveData or Observables to manage UI updates

**Technologies Used:**
- Java
- MVVM / Clean Architecture
- Retrofit (Networking)
- Room (Local Storage)
- LiveData / Observables
- No external image libraries used

**Folder Location:**  
`BookReviewApp/` (Android Studio project)

**How to Run:**
1. Open the `BookReviewApp` folder in Android Studio
2. Sync Gradle and build the project
3. Run the app on an emulator or connected device

---

## Author

**Pratham**  
