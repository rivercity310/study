#include <iostream>
#include <algorithm>
#include <numeric>

using namespace std;

constexpr int SIZE = 5;

void boj_2587() {
	int arr[SIZE];

	for (int i = 0; i < SIZE; i++) {
		cin >> arr[i];
	}

	std::sort(arr, arr + SIZE);

	int avg = std::accumulate(arr, arr + SIZE, 0) / SIZE;

	cout << avg << "\n";
	cout << arr[SIZE / 2];
}