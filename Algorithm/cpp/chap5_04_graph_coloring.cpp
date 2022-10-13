#include <iostream>
#include <vector>
#include <string>
#include <set>
#include <map>
#include <unordered_map>

#include "graph_and_edge.h"
using namespace std;


// 해시 맵을 이용하여 그래프 컬러링에 사용할 색상을 표현
unordered_map<unsigned, string> color_map = {
	{1, "Red"}, {2, "Blue"}, {3, "Green"}, {4, "Yellow"}, {5, "Black"}, {6, "White"}
};

// 그래프 컬러링 알고리즘 구현 함수
template <typename T>
vector<unsigned> greedy_coloring(const Graph<T>& G) {
	unsigned size = G.vertices();
	vector<unsigned> assigned_colors(size);

	// 1번 정점부터 차례대로 검사
	for (unsigned i = 1; i < size; i++) {
		vector<Edge<T>> outgoing_edges = G.edges(i);

		// 1번째 정점과 인접해 있는 정점들의 현재 색상
		set<unsigned> neighbours;

		for (Edge<T> e : outgoing_edges)
			neighbours.insert(assigned_colors[e.dst]);

		// 인접한 정점에 칠해지지 않은 색상 중, 가장 작은 숫자의 색상을 선택
		int smallest = 1;
		for (; smallest <= color_map.size(); smallest++)
			if (neighbours.find(smallest) == neighbours.end())
				break;

		assigned_colors[i] = smallest;
	}

	return assigned_colors;
}

// 그래프 컬러링 결과 출력 함수
template <typename T>
void prt_colors(vector<T>& colors) {
	for (int i = 1; i < colors.size(); i++)
		cout << i << ": " << color_map[colors[i]] << endl;
}

// 테스트 함수
void graph_coloring_test() {
	using T = unsigned;

	// 그래프 객체 생성
	Graph<T> G(9);

	map<unsigned, vector<pair<unsigned, T>>> edge_map;
	edge_map[1] = { {2, 0}, {5, 0} };
	edge_map[2] = { {1, 0}, {5, 0}, {4, 0} };
	edge_map[3] = { {4, 0}, {7, 0} };
	edge_map[4] = { {2, 0}, {3, 0}, {5, 0}, {6, 0}, {8, 0} };
	edge_map[5] = { {1, 0}, {2, 0}, {4, 0}, {8, 0} };
	edge_map[6] = { {4, 0}, {7, 0}, {8, 0} };
	edge_map[7] = { {3, 0}, {6, 0} };
	edge_map[8] = { {4, 0}, {5, 0}, {6, 0} };

	for (auto& i : edge_map)
		for (auto& j : i.second)
			G.add_edge(Edge<T>{i.first, j.first, j.second});

	cout << "[입력 그래프]" << endl;
	cout << G << endl;

	vector<unsigned> colors = greedy_coloring<T>(G);
	cout << "\n\n[그래프 컬러링]" << endl;
	prt_colors(colors);
}