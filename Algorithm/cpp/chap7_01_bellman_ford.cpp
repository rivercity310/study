#include <iostream>
#include <vector>
#include <limits>

using namespace std;

/*
음수 가중치가 있는 그래프에서 다익스트라 알고리즘은 제대로 동작하지 못할 가능성이 있다.
따라서 벨만-포드 알고리즘을 사용한다.

그래프의 모든 엣지에 대해 다익스트라의 그리디 선택 방법을 (V - 1)번 반복
하여 점진적으로 최단 거리를 찾는다.

결과적으로 다익스트라 알고리즘보다 더 높은 점근적 시간 복잡도를 가지지만
정확한 결과를 제공한다.
*/


// 엣지 구조체
struct Edge {
	int src;
	int dst;
	int weight;
};

// 무한대를 표현하는 상수
const int UNKNOWN = numeric_limits<int>::max();


// 벨만-포드 알고리즘 구현 함수
// edges: 그래프를 포현하는 엣지 리스트, V: 정점 개수, start: 출발 정점 번호
vector<int> BellmanFord(vector<Edge> edges, const int V, int start) {
	vector<int> distance(V, UNKNOWN);
	distance[start] = 0;

	// (V - 1)번 반복
	for (int i = 0; i < V - 1; i++) {

		// 전체 엣지에 대해 반복
		for (Edge& e : edges) {

			// 엣지의 시작 정점의 거리 값이 UNKNOWN이면 스킵
			if (distance[e.src] == UNKNOWN) continue;

			// 인접한 정점의 거리 값이 새로운 경로에 의한 거리 값보다 크면 업데이트
			if (distance[e.dst] > distance[e.src] + e.weight) {
				cout << "(" << e.src << ", " << e.dst << ") ";
				cout << distance[e.dst] << " => " << distance[e.src] + e.weight << endl;
				
				distance[e.dst] = distance[e.src] + e.weight;
			}
		}
	}

	return distance;
}


void BellmanFord_Test() {
	const int V = 5;     // 정점 개수
	vector<Edge> edges;

	vector<vector<int>> edge_map{		// {시작 정점, 목표 정점, 가중치}
		{0, 1, 3},
		{1, 2, 5},
		{1, 3, 10},
		{3, 2, -7},
		{2, 4, 2}
	};

	for (vector<int>& v : edge_map)
		edges.emplace_back(Edge{ v[0], v[1], v[2] });

	for (int i = 0; i < edges.size(); i++) {
		int start = i;
		vector<int> distance = BellmanFord(edges, V, start);

		cout << "[" << start << "번 정점으로부터 최소 거리]\n";

		for (int j = 0; j < distance.size(); j++) {
			if (distance[j] == UNKNOWN)
				cout << j << "번 정점: X" << "\n";
			else
				cout << j << "번 정점: " << distance[j] << "\n";
		}

		cout << endl;
	}
}

vector<int> BF(vector<Edge> edges, int V, int start) {
	vector<int> distance(V, UNKNOWN);
	distance[start] = 0;

	for (int i = 0; i < V - 1; i++) {
		
		cout << "[ " << i + 1 << "번째 반복 ]" << endl;

		for (Edge& e : edges) {
			if (distance[e.src] == UNKNOWN)
				continue;

			if (distance[e.dst] > distance[e.src] + e.weight) {
				cout << "(" << e.src << ", " << e.dst << ") : ";
				cout << distance[e.dst] << " => " << distance[e.src] + e.weight << endl;

				distance[e.dst] = distance[e.src] + e.weight;
			}
		}
	}

	return distance;
}

void bf_test() {
	const int V = 5;

	vector<Edge> edges;

	vector<vector<int>> edge_map{		// {시작 정점, 목표 정점, 가중치}
		{0, 1, 3},
		{1, 2, 5},
		{1, 3, 10},
		{3, 2, -7},
		{2, 4, 2}
	};

	for (vector<int>& v : edge_map)
		edges.emplace_back(Edge{ v[0], v[1], v[2] });

	for (int i = 0; i < edges.size(); i++) {
		int start = i;

		cout << "[[ 시작 정점 : " << start << " ]]" << endl;

		vector<int> distance = BF(edges, V, i);

		for (int j = 0; j < distance.size(); j++) {
			if (distance[j] == UNKNOWN)
				cout << "X ";
			else
				cout << distance[j] << " ";
		}
		cout << "\n" << endl;
	}
}