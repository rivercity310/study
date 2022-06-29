#include <iostream>
#include <vector>
#include <chrono>
#include <algorithm>
#include <random>
using namespace std;


bool lin_sch(vector<int>& v, int n) {
	for (auto i : v)
		if (i == n) return true;

	return false;
}

bool bin_sch(vector<int>& v, int n) {
	auto first = v.begin();
	auto end = v.end();

	while (true) {
		
	}
}
