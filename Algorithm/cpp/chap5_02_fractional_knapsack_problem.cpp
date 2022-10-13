#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

struct Object {
	int id;
	int weight;
	double value;
	double value_per_unit_weight;

	Object(int id, int weight, double value) {
		this->id = id;
		this->weight = weight;
		this->value = value;
		this->value_per_unit_weight = value / weight;
	}

	// sort()에서 사용
	inline bool operator<(const Object& obj) {
		return this->value_per_unit_weight < obj.value_per_unit_weight;
	}

	friend ostream& operator<<(ostream& os, const Object& obj) {
		os << "[" << obj.id << "] 가격: " << obj.value
			<< " \t무게: " << obj.weight
			<< " kg\t단위 무게 당 가격: " << obj.value_per_unit_weight;

		return os;
	}
};

// 분할 가능 배낭 문제 알고리즘 구현 함수
auto fill_knapsack(vector<Object>& objects, int knapsack_capacity) {
	vector<Object> knapsack_contents;
	knapsack_contents.reserve(objects.size());

	// 물건들을 내림차순으로 정렬
	std::sort(objects.begin(), objects.end());
	std::reverse(objects.begin(), objects.end());

	// '가장 가치 있는' 물건을 먼저 배낭에 추가
	vector<Object>::iterator curr_obj = objects.begin();
	int curr_total_weight = 0;

	while (curr_total_weight < knapsack_capacity && curr_obj != objects.end()) {
		knapsack_contents.push_back(*curr_obj);

		curr_total_weight += curr_obj->weight;
		curr_obj++;
	}

	// 마지막에 추가한 물건에 의해 배낭 최대 허용 무게가 초과된 경우
	int weight_of_last_obj_to_remove = curr_total_weight - knapsack_capacity;
	Object& last_object = knapsack_contents.back();

	if (weight_of_last_obj_to_remove > 0) {
		last_object.weight -= weight_of_last_obj_to_remove;
		last_object.value -= last_object.value_per_unit_weight * weight_of_last_obj_to_remove;
	}

	return knapsack_contents;
}

void fractinal_knapsack_problem_test() {
	vector<Object> obj;
	obj.reserve(7);

	vector<int> weights{ 1, 2, 5, 9, 2, 3, 4 };
	vector<double> values{ 10, 7, 15, 10, 12, 11, 5 };

	for (auto i = 0; i < 7; i++)
		obj.push_back(Object(i + 1, weights[i], values[i]));


	// 사용할 수 있는 물건 정보 출력하기
	cout << "[사용할 수 있는 물건 정보]" << "\n";
	for (auto& o : obj) cout << o << endl;
	cout << endl;


	// 분할 가능 배낭 문제 알고리즘 실행, 배낭의 최대 허용 무게는 7로 지정
	int knapsack_capacity = 7;
	vector<Object> solution = fill_knapsack(obj, knapsack_capacity);

	
	// 배낭에 넣을 물건 정보 출력
	cout << "[배낭에 넣을 물건들 (최대 용량 = " << knapsack_capacity << ")]" << endl;
	for (auto& o : solution) cout << o << endl;
	cout << endl;
}