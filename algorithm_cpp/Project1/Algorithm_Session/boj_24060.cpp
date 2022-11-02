#include <iostream>
#include <vector>
using namespace std;

int cnt;

void merging(int* arr, int left, int mid, int right) {
	int i = left;
	int j = mid + 1;
	int idx = 0;

	int* tmp = new int[right - left + 1];
	while (i <= mid && j <= right) {
		if (arr[i] <= arr[j])
			tmp[idx++] = arr[i++];
		else
			tmp[idx++] = arr[j++];
	}

	while (i <= mid)
		tmp[idx++] = arr[i++];

	while (j <= right)
		tmp[idx++] = arr[j++];

	i = left;
	idx = 0;

	while (i <= right)
		arr[i++] = tmp[idx++];
}

void merge_sort(int* arr, int left, int right) {
	if (left < right) {
		int mid = (left + right) / 2;

		merge_sort(arr, left, mid);
		merge_sort(arr, mid + 1, right);
		merging(arr, left, mid, right);
	}
}

void boj_24060() {
	int n, k;
	cin >> n >> k;

	int* arr = new int[n];
	for (int i = 0; i < n; i++)
		cin >> arr[i];

	merge_sort(arr, 0, n - 1);

	

	delete[] arr;
}
