#include <map>

using namespace std;

// 엣지와 그래프 정의
template <typename T>
struct Edge {
	unsigned src;
	unsigned dst;
	T weight;

	// Edge 객체 비교는 가중치를 이용한다.
	inline bool operator < (const Edge<T>& e) const {
		return this->weight < e.weight;
	}

	inline bool operator > (const Edge<T>& e) const {
		return this->weight > e.weight;
	}
};

template <typename T>
class Graph {
private:
	unsigned V;     // 정점의 개수
	vector<Edge<T>> edge_list;

public:
	// N개의 정점으로 구성된 그래프
	Graph(unsigned N) : V(N) {}

	// 그래프의 정점 개수 반환
	unsigned vertices() const {
		return V;
	}

	// 전체 엣지 리스트 반환
	const vector<Edge<T>>& edges() const {
		return edge_list;
	}

	// 정점 v에서 나가는 모든 엣지를 반환
	vector<Edge<T>> edges(unsigned v) const {
		vector<Edge<T>> edges_from_v;

		for (Edge<T> e : edge_list)
			if (e.src == v)
				edges_from_v.emplace_back(e);

		return edges_from_v;
	}

	void add_edge(Edge<T>&& e) {
		// 엣지 양 끝 정점 ID가 유효한지 검사
		if (e.src >= 1 && e.src <= V && e.dst >= 1 && e.dst <= V)
			edge_list.emplace_back(e);
		else
			cerr << "에러: 유효 범위를 벗어난 정점" << endl;
	}

	// 표준 출력 스트림 지원
	template <typename U>
	friend ostream& operator<<(ostream& os, const Graph<U>& G) {
		for (unsigned i = 1; i < G.vertices(); i++) {
			os << i << ":\t";
			vector<Edge<T>> edges = G.edges(i);

			for (Edge<T>& e : edges)
				os << "{" << e.dst << ": " << e.weight << "}, ";

			os << endl;
		}

		return os;
	}
};

// 그래프를 생성하여 반환하는 함수
template <typename T>
Graph<T> create_reference_graph() {
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

	return G;
}

// 그래프를 생성하여 반환하는 함수
template <typename T>
Graph<T> create_reference_graph2() {
	Graph<T> G(9);

	map<unsigned, vector<pair<unsigned, T>>> edge_map;
	edge_map[1] = { {2, 2}, {5, 3} };
	edge_map[2] = { {1, 2}, {5, 5}, {4, 1} };
	edge_map[3] = { {4, 2}, {7, 3} };
	edge_map[4] = { {2, 1}, {3, 2}, {5, 2}, {6, 4}, {8, 5} };
	edge_map[5] = { {1, 3}, {2, 5}, {4, 2}, {8, 3} };
	edge_map[6] = { {4, 4}, {7, 4}, {8, 1} };
	edge_map[7] = { {3, 3}, {6, 4} };
	edge_map[8] = { {4, 5}, {5, 3}, {6, 1} };

	for (auto& i : edge_map)
		for (auto& j : i.second)
			G.add_edge(Edge<T>{i.first, j.first, j.second});

	return G;
}
