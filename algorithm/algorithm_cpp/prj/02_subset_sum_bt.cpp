#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>
#include <time.h>

using namespace std;

/*
유효하지 않은 모든 경우를 제거하는 백트래킹 기법 

다음 기저 조건을 추가하여 불필요한 과정을 없앤다.

- 1. 만약 현재 부분집합의 합이 target과 같다면 TRUE
- 2. 그렇지 않다면:
	- 만약 현재 부분집합의 합이 target보다 크다면 FALSE
	- 만약 집합의 끝에 도달한 경우 FALSE
*/

bool SubsetSum_Backtracking(vector<int>& set, int sum, int i) {
	// 기저 조건 처리 코드
	/* 만약 현재 부분집합의 합이 target과 같다면 */
	if (sum == 0) return true;

	/* 만약 집합의 끝에 도달했거나, 부분집합의 합이 target보다 커지면 */
	if (i == set.size() || set[i] > sum) return false;

	
	/* 
	=> Case 1: sum에서 set[i]를 빼서 재귀 호출 (i번째 원소를 부분집합에 추가)
	=> Case 2: sum을 그대로 전달하여 재귀 호출 (i번쨰 원소를 부분집합에 추가하지 않음)
	*/
	return SubsetSum_Backtracking(set, sum - set[i], i + 1) || SubsetSum_Backtracking(set, sum, i + 1);
}

void GetTime(clock_t& timer, string type) {
	timer = clock() - timer;

	cout << type << " 방법 경과 시간 : ";
	cout << fixed << setprecision(5) << (float)timer / CLOCKS_PER_SEC << endl;

	timer = clock();
}

void subset_bt_test() {
	vector<int> set = { 16, 1058, 22, 13, 46, 55, 3, 92, 47, 7, 98, 367, 807, 106, 333, 85, 577, 9, 3059 };
	int target = 6076;

	sort(set.begin(), set.end());

	clock_t timer = clock();
	bool found = SubsetSum_Backtracking(set, target, 0);

	if (found) cout << "원소 합이 " << target << "인 부분집합 존재!" << endl;
	else cout << "원소 합이 " << target << "인 부분집합 존재하지 않음!" << endl;

	GetTime(timer, "Back Tracking");
	cout << endl;
}