#include <iostream>e
#include <vector>
#include <algorithm>
#include <numeric>
using namespace std;

template <typename T>
auto compute_waiting_times(vector<T>& service_times) {
	vector<T> W(service_times.size());
	W[0] = 0;

	for (auto i = 1; i < service_times.size(); i++)
		W[i] = W[i - 1] + service_times[i - 1];

	return W;
}

template <typename T>
void pt_vt(vector<T>& v) {
	for (auto i : v) {
		cout.width(3);
		cout << i << " ";
	}
	cout << endl;
}

template <typename T>
void compute_and_print_waiting_times(vector<T>& service_times) {
	vector<T> waiting_times = compute_waiting_times<int>(service_times);

	cout << "- 처리 시간: ";
	pt_vt(service_times);

	cout << "- 대기 시간: ";
	pt_vt(waiting_times);

	double ave_waiting_times = std::accumulate(waiting_times.begin(), waiting_times.end(), 0.0) / waiting_times.size();
	cout << "- 평균 대기 시간: " << ave_waiting_times << endl;
}

void shortest_job_first_scheduling_test() {
	vector<int> service_times{ 8, 1, 2, 4, 9, 2, 3, 5 };

	cout << "[ 처음 일 처리 시간 & 대기 시간 ]" << endl;
	compute_and_print_waiting_times(service_times);

	// 일 처리 시간을 오름차순으로 정렬
	std::sort(service_times.begin(), service_times.end());

	cout << "\n[ 정렬 후 일 처리 시간 & 대기 시간 ]" << endl;
	compute_and_print_waiting_times(service_times);
}