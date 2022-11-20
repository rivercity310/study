#include <iostream>
#include <vector>
#include <iomanip>
#include <random>
using namespace std;

template<typename T>
void prt_v(vector<T> arr) {
	for (auto i : arr) cout << i << " ";
	cout << endl;
}

template <typename T>
auto partition(typename vector<T>::iterator begin, typename vector<T>::iterator end) {
	// 세 개의 반복자 생성
	// 하나는 피벗, 나머지 둘은 피벗을 제외한 벡터의 처음과 끝
	T pivot_val = *begin;
	typename vector<T>::iterator left_iter = begin + 1;
	typename vector<T>::iterator right_iter = end;

	while (true) {
		// 벡터의 첫번째 원소부터 시작하여 피벗보다 큰 원소 찾기
		while (*left_iter <= pivot_val && distance(left_iter, right_iter) > 0)
			left_iter++;

		// 벡터의 마지막 원소부터 역순으로 피벗보다 작은 원소 찾기
		while (*right_iter > pivot_val && distance(left_iter, right_iter) > 0)
			right_iter--;

		if (left_iter == right_iter) break;
		else iter_swap(left_iter, right_iter);
	}

	if (pivot_val > *right_iter) 
		iter_swap(begin, right_iter);
	
	cout << "\n" << endl;
	cout << "Pivot: " << pivot_val << endl;

	cout << "[ 부분 배열 L (pivot보다 작거나 같은) ]" << endl;
	prt_v(vector<T>(begin, right_iter));

	cout << endl;

	cout << "[ 부분 배열 R (pivot보다 큰) ]" << endl;
	prt_v(vector<T>(right_iter, end));

	return right_iter;
}

// 분할 연산 (partition 함수)을 재귀적으로 수행하여 퀵 정렬을 수행
template <typename T>
void quick_sort(typename vector<T>::iterator begin, typename vector<T>::iterator last) {
	if (distance(begin, last) >= 1) {
		// 분할 작업 수행
		typename vector<T>::iterator partition_iter = partition<T>(begin, last);

		// 분할 작업에 의해 생성된 벡터를 재귀적으로 정렬
		quick_sort<T>(begin, partition_iter - 1);
		quick_sort<T>(partition_iter, last);
	}
}

void q_sort_test() {
	random_device rd;
	mt19937 rand(rd());
	uniform_int_distribution<mt19937::result_type> uniform_dist(1, 100);

	vector<int> v1;

	for (int i = 0; i < 10; i++)
		v1.emplace_back(uniform_dist(rand));

	cout << "[ 정렬 전 벡터 ]" << endl;
	prt_v(v1);

	cout << endl;

	// end()는 마지막 원소 다음을 가리키므로 1을 빼준다.
	quick_sort<int>(v1.begin(), v1.end() - 1);
	
	cout << "[ 정렬 후 벡터 ]" << endl;
	prt_v<int>(v1);
}
