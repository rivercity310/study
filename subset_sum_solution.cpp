#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/*
[ 부분 집합의 합 문제 ]

Dynamic Programming에 대해 알아보면서 전수 조사(Brute-Force), 백트래킹, 메모이제이션, 타뷸레이션이라는
네 가지 접근 방법에 대해 모두 고려해본다.

앞에 세가지 접근 방식은 입력 크기 N이 증가함에 따라 그 한계가 명확하다.
그럼에도 이들 방법을 차례대로 구현해봄으로서 각 접근 방식의 차이를 알아가는 것이 목표이다.
*/

#define DEBUG 0
#if DEBUG
#define PRINT(x) cerr << x
#else
#define PRINT(x)
#endif

/* 집합 set의 모든 부분집합을 구하는 함수 */
void GetAllSubsets(vector<int> set, vector<int> subset, int index, vector<vector<vector<int>>>& allSubsets) {
	
	// 집합 set의 끝에 다다른 경우
	if (index == set.size()) {

		// 부분집합 크기를 인덱스로 사용하여 부분집합을 allSubsets에 추가
		allSubsets[subset.size()].push_back(subset);
		return;
	}

	// 원소를 추가하지 않고 재귀 호출
	GetAllSubsets(set, subset, index + 1, allSubsets);

	// 원소를 부분집합에 추가한 후 재귀 호출
	subset.push_back(set[index]);
	GetAllSubsets(set, subset, index + 1, allSubsets);
}

bool SubsetSum_BruteForce(vector<int> set, int target) {
	vector<vector<vector<int>>> allSubsets(set.size() + 1);
	GetAllSubsets(set, {}, 0, allSubsets);

	/* 각 부분집합의 원소 합을 target과 비교하고 일치하면 true 반환 */
	for (int i = 0; i < set.size(); i++) {
		PRINT("부분집합의 원소 개수: " << i << endl);

		for (auto subset : allSubsets[i]) {
			PRINT("\t{ ");
			
			int sum = 0;
			for (auto number : subset) {
				sum += number;
				PRINT(number << " ");
			}

			PRINT("} = " << sum << endl);

			if (sum == target)
				return true;
		}
	}

	return false;
}

void subset_test() {
	vector<int> set = { 13, 79, 45, 29 };
	int target = 58;

	bool found = SubsetSum_BruteForce(set, target);

	if (found) cout << "원소 합이 " << target << "인 부분집합 존재!" << endl;
	else cout << "원소 합이 " << target << "인 부분집합 존재하지 않음!" << endl;
}