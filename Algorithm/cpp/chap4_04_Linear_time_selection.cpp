#include <iostream>
#include <vector>
#include <algorithm>
#include <random>
using namespace std;

// 두 반복자 사이의 중간 값을 찾는 함수
template <typename T>
typename vector<T>::iterator find_median(
	typename vector<T>::iterator begin, 
	typename vector<T>::iterator last
) 
{
	// 정렬
	sort(begin, last);
	
	// 가운데 원소 반복자 반환
	return begin + (distance(begin, last) / 2);
}

// 피벗 위치 반복자를 인자로 받는 형태의 분할 함수
template <typename T>
auto partition_using_given_pivot(
	typename vector<T>::iterator begin, 
	typename vector<T>::iterator end, 
	typename vector<T>::iterator pivot
) 
{
	typename vector<T>::iterator left_iter = begin;
	typename vector<T>::iterator right_iter = end;

	while (true) {
		while (*left_iter < *pivot && left_iter != right_iter) left_iter++;
		while (*right_iter >= *pivot && left_iter != right_iter) right_iter--;

		if (left_iter == right_iter) break;
		else
			iter_swap(left_iter, right_iter);
	}

	if (*pivot > *right_iter) iter_swap(pivot, right_iter);
	
	return right_iter;
}

// 선형 시간 검색 알고리즘
template <typename T>
typename vector<T>::iterator linear_time_select(
	typename vector<T>::iterator begin,
	typename vector<T>::iterator last,
	size_t i
)
{
	auto size = std::distance(begin, last);

	if (size > 0 && i < size) {
		// 다섯개의 원소로 구분하여 만들 부분 벡터 Vi의 개수 계산
		int num_Vi = (size + 4) / 5;
		size_t j = 0;

		// 각각의 Vi에서 중앙값을 찾아 벡터 M에 저장
		vector<T> M;
		for (; j < size / 5; j++) {
			auto b = begin + (j * 5);
			auto l = begin + (j * 5) + 5;

			M.push_back(*find_median<T>(b, l));
		}

		if (j * 5 < size) {
			auto b = begin + (j * 5);
			auto l = begin + (j * 5) + (size % 5);

			M.push_back(*find_median<T>(b, l));
		}

		// Vi의 중앙값으로 구성된 벡터 M에서 다시 중앙값 q를 찾음
		auto med = (M.size() == 1) ? M.begin() : linear_time_select<T>(M.begin(), M.end() - 1, M.size() / 2);

		// 분할 연산을 적용하고 피벗 q의 위치 k를 찾음
		auto partition_iter = partition_using_given_pivot<T>(begin, last, med);
		auto k = std::distance(begin, partition_iter) + 1;

		if (i == k) return partition_iter;
		else if (i < k) return linear_time_select<T>(begin, partition_iter - 1, i);
		else if (i > k) return linear_time_select<T>(partition_iter + 1, last, i - k);
	}
	else
		return begin;
}

// 테스트용 병합 정렬 함수
template <typename T>
vector<T> mg(vector<T>& arr1, vector<T>& arr2) {
	vector<T> merged;

	auto iter1 = arr1.begin();
	auto iter2 = arr2.begin();

	while (iter1 != arr1.end() && iter2 != arr2.end()) {
		if (*iter1 < *iter2) {
			merged.emplace_back(*iter1);
			iter1++;
		}
		else {
			merged.emplace_back(*iter2);
			iter2++;
		}
	}

	if (iter1 != arr1.end()) {
		for (; iter1 != arr1.end(); iter1++)
			merged.emplace_back(*iter1);
	}
	else {
		for (; iter2 != arr2.end(); iter2++)
			merged.emplace_back(*iter2);
	}

	return merged;
}

template <typename T>
vector<T> mg_sort(vector<T> arr) {
	if (arr.size() > 1) {
		auto mid = size_t(arr.size() / 2);
		auto left_half = mg_sort(vector<T>(arr.begin(), arr.begin() + mid));
		auto right_half = mg_sort(vector<T>(arr.begin() + mid, arr.end()));

		return mg<T>(left_half, right_half);
	}

	return arr;
}

void pv(vector<int> v) {
	for (auto i : v) cout << i << " ";
	cout << endl;
}

void run_linear_select_test() {
	vector<int> s1;

	random_device rd;
	mt19937 rand(rd());
	uniform_int_distribution<mt19937::result_type> uniform_dist(1, 1000);

	for (int i = 0; i < 10; i++) s1.push_back(uniform_dist(rand));
	
	cout << "입력 벡터" << endl;
	pv(s1);
	cout << endl;

	cout << "정렬된 벡터" << endl;
	pv(mg_sort(s1));
	cout << endl;

	while (true) {
		size_t s;
		cout << "몇번째? ";
		cin >> s;

		if (s <= 0) break;
		cout << *linear_time_select<int>(s1.begin(), s1.end() - 1, s) << endl;
	}
}