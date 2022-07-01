#include <iostream>
#include <vector>
#include <iomanip>
#include <random>
using namespace std;

template <typename T>
auto partition(typename vector<T>::iterator begin, typename vector<T>::iterator end) {
	// 세 개의 반복자 생성
	// 하나는 피벗, 나머지 둘은 벡터의 처음과 끝
	T pivot_val = *begin;
	auto left_iter = begin + 1;
	auto right_iter = end;

	while (true) {
		// 벡터의 첫번째 원소부터 시작하여 피벗보다 큰 원소 찾기
		while (*left_iter <= pivot_val && distance(left_iter, right_iter) > 0)
			left_iter++;

		// 벡터의 마지막 원소부터 역순으로 피벗보다 작은 원소 찾기
		while (*right_iter > pivot_val && distance(left_iter, right_iter) > 0)
			right_iter--;

		cout << "[ iter_swap 이전 ]" << "\n";
		cout << "left_iterator_value: " << *left_iter << "\n";
		cout << "right_iterator_value: " << *right_iter << endl;

		if (left_iter == right_iter) break;
		else iter_swap(left_iter, right_iter);
	
		cout << "[ iter_swap 이후 ]" << "\n";
		cout << "left_iterator_value: " << *left_iter << "\n";
		cout << "right_iterator_value: " << *right_iter << "\n";
		cout << "\n\n\n" << endl;
	}

	if (pivot_val > *right_iter) {
		cout << "[ pivot과 right_iter를 Swap ]" << "\n";
		cout << "변경 전 >> pivot: " << *begin << ", right_iter: " << *right_iter << "\n";
		iter_swap(begin, right_iter);
		cout << "변경 후 >> pivot: " << *begin << ", right_iter: " << *right_iter << endl;
	}

	return right_iter;
}

// 분할 연산 (partition 함수)을 재귀적으로 수행하여 퀵 정렬을 수행
template <typename T>
void quick_sort(typename vector<T>::iterator begin, typename vector<T>::iterator last) {
	if (distance(begin, last) >= 1) {
		// 분할 작업 수행
		auto partition_iter = partition<T>(begin, last);

		// 분할 작업에 의해 생성된 벡터를 재귀적으로 정렬
		quick_sort<T>(begin, partition_iter - 1);
		quick_sort<T>(partition_iter, last);
	}
}

template <typename T>
void prt_vt3(vector<T> arr) {
	for (auto i : arr)
		cout << setw(10) << right << i;
	cout << endl;
}

void q_sort_test() {
	random_device rd;
	mt19937 rand(rd());

	uniform_int_distribution<mt19937::result_type> uniform_dist(1, 1000);
	
	vector<int> v1;
	vector<double> v2;
	for (int i = 0; i < 3; i++) {
		v1.push_back(uniform_dist(rand));
		v2.push_back(uniform_dist(rand) / (double)(i+ 1));
	}

	cout << "[ 정렬 전 벡터 ]" << endl;
	prt_vt3(v1);
	prt_vt3(v2);

	// end()는 마지막 원소 다음을 가리키므로 1을 빼준다.
	quick_sort<int>(v1.begin(), v1.end() - 1);
	quick_sort<double>(v2.begin(), v2.end() - 1);
	
	cout << "[ 정렬 후 벡터 ]" << endl;
	prt_vt3<int>(v1);
	prt_vt3<double>(v2);


}
