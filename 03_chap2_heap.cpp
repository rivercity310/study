#include <iostream>
#include <queue>
#include <vector>
using namespace std;

/*
힙을 이용하여 데이터를 받을 때마다 지금까지 받은 데이터의 중앙값(median)을 계산

[중앙값]
데이터 개수가 짝수: 가운데 위치한 두 데이터의 산술 평균 (a + b) / 2
데이터 개수가 홀수: 정렬하여 가운데 위치한 값
*/

/*
새로 들어오는 데이터가 기존 데이터의 중앙값보다 작으면 최대 힙에 저장하고 크면 최소 힙에 저장
=> 두 힙의 최상단 원소를 이용하여 중앙값을 계산할 수 있다.
*/
struct median {
	priority_queue<int> maxHeap;
	priority_queue<int, vector<int>, greater<int>> minHeap;

	// 새로 들어오는 데이터를 저장
	void insert(int data) {
		if (maxHeap.size() == 0) {
			maxHeap.push(data);
			return;
		}

		if (maxHeap.size() == minHeap.size()) {
			if (data <= get()) maxHeap.push(data);
			else minHeap.push(data);
			return;
		}

		if (maxHeap.size() < minHeap.size()) {
			if (data > get()) {
				maxHeap.push(minHeap.top());
				minHeap.pop();
				minHeap.push(data);
			}
			else
				maxHeap.push(data);

			return;
		}

		if (data < get()) {
			minHeap.push(maxHeap.top());
			maxHeap.pop();
			maxHeap.push(data);
		}
		else
			minHeap.push(data);
	}

	// 저장된 원소로부터 중앙값을 구하여 반환
	double get() {
		if (maxHeap.size() == minHeap.size())
			return (maxHeap.top() + minHeap.top()) / 2.0;

		if (maxHeap.size() < minHeap.size())
			return minHeap.top();

		return maxHeap.top();
	}
};

void chap2_heap() {
	median med;

	med.insert(1);
	cout << "1 삽입 후 중앙값: " << med.get() << endl;

	med.insert(5);
	cout << "5 삽입 후 중앙값: " << med.get() << endl;

	med.insert(2);
	cout << "2 삽입 후 중앙값: " << med.get() << endl;

	med.insert(10);
	cout << "10 삽입 후 중앙값: " << med.get() << endl;

	med.insert(40);
	cout << "40 삽입 후 중앙값: " << med.get() << endl;
}
