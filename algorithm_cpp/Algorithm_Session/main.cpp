#define MAIN

// #define WEEK1
// #define WEEK2
// #define WEEK3
#define WEEK4

#ifdef MAIN
#include <iostream>
#include <vector>
#include <algorithm>
#define FAST_IO ios::sync_with_stdio(0); cin.tie(0); cout.tie(0)
using namespace std;
#endif

#ifdef WEEK1
extern void boj_1620();
extern void boj_2504();
extern void boj_9012();
extern void boj_1541();
#endif

#ifdef WEEK2
extern void boj_1874();
extern void boj_11866();
extern void boj_11866_2();
extern void boj_4949();
extern void boj_10816();
extern void boj_2587();
extern void boj_4195();
extern void boj_24060();
extern void boj_15828();
extern void boj_1966();
extern void boj_1004();
#endif

#ifdef WEEK3
extern void boj_11279();
extern void boj_11000();
#endif

#ifdef WEEK4
extern void boj_1012();
extern void boj_1920();
extern void boj_11051();
extern void boj_1010();
extern void boj_9375();
extern void boj_2667();
extern void boj_2178();
extern void pc_test();
#endif

int main() {
	boj_2178();
}