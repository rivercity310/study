#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

constexpr int N = 4;
constexpr int R = 2;

static int pArr[R];
static bool check[N + 1];


static void permutation(int depth) {
	if (depth == R) {
		for (int i = 0; i < R; i++)
			cout << pArr[i] << " ";
		cout << "\n";
		return;
	}

	for (int i = 1; i <= N; i++) {
		if (!check[i]) {
			check[i] = true;
			pArr[depth] = i;
			permutation(depth + 1);
			check[i] = false;
		}
	}
}

int dpArr[R];

static void duplicatePermutation(int depth) {
	if (depth == R) {
		for (int i = 0; i < R; i++)
			cout << dpArr[i] << " ";
		cout << "\n";
		return;
	}

	for (int i = 1; i <= N; i++) {
		dpArr[depth] = i;
		duplicatePermutation(depth + 1);
	}
}

int cArr[R];

/* 반복문의 시작 값은 (이전에 선택한 값 + 1) */
static void combination(int depth, int next) {
	if (depth == R) {
		for (int i = 0; i < R; i++)
			cout << cArr[i] << " ";
		cout << "\n";
		return;
	}

	for (int i = next; i <= N; i++) {
		cArr[depth] = i;
		combination(depth + 1, i + 1);
	}
}

int dcArr[R] = { 0, };

/* 반복문의 시작 값은 이전에 선택한 값이 된다 */
static void duplicateCombination(int depth, int next)
{
	if (depth == R) {
		for (int i = 0; i < R; i++)
			cout << dcArr[i] << " ";
		cout << "\n";
		return;
	}

	for (int i = next; i <= N; i++) {
		dcArr[depth] = i;
		duplicateCombination(depth + 1, i);
	}

}

static void permutation_with() {
	vector<int> v{ 1, 2, 3 };

	do {
		for (int i = 0; i < v.size(); i++)
			cout << v[i] << " ";
		cout << "\n";

	} while (next_permutation(v.begin(), v.end()));
}

void pc_test() {
	// combination(0, 1);
	permutation_with();
	cout << "\n\n";
	permutation(0);
}