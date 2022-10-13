//#include <iostream>
//#include <vector>
//#include <string>
//#include <unordered_map>
//#include <set>
//#include <map>
//#include <algorithm>
//#include <utility>
//
//using namespace std;
//
//extern unordered_map<unsigned, string> color_map;
//
//namespace welsh_powell {
//
//	template <typename T>
//	struct Edge {
//		unsigned src;
//		unsigned dst;
//		T weight;
//
//		inline bool operator < (const Edge<T>& e) const {
//			return this->weight < e.weight;
//		}
//	};
//
//	template <typename T>
//	class Graph {
//	private:
//		unsigned V;
//		vector<Edge<T>> edge_list;
//
//	public:
//		Graph(unsigned N) : V(N) {}
//
//		unsigned vertices() const {
//			return V;
//		}
//
//		vector<Edge<T>>& edges() const {
//			return edge_list;
//		}
//
//		vector<Edge<T>> edges(unsigned v) const {
//			vector<Edge<T>> edges_from_v;
//
//			for (auto& e : edge_list)
//				if (e.src == v)
//					edges_from_v.push_back(e);
//
//			return edges_from_v;
//		}
//
//		// 우측값 참조법 &&
//		void add_edge(Edge<T>&& e) {
//			if (e.src >= 1 && e.src <= V && e.dst >= 1 && e.dst <= V)
//				edge_list.emplace_back(e);
//			else
//				cerr << "Error! " << endl;
//		}
//
//		// 표준 출력 스트림 지원
//		template <typename U>
//		friend ostream& operator<< (ostream& os, const Graph<U>& G) {
//			for (unsigned i = 1; i < G.vertices(); i++) {
//				os << i << ":\t";
//				vector<Edge<T>> edges = G.edges(i);
//				for (auto& e : edges)
//					os << "{" << e.dst << ": " << e.weight << "}, ";
//
//				os << endl;
//			}
//
//			return os;
//		}
//	};
//
//}
//
//template <typename T>
//auto welsh_powell_coloring(const welsh_powell::Graph<T>& G) {
//	unsigned size = G.vertices();
//	vector<pair<unsigned, size_t>> degrees;  // 차수
//
//	// 각 정점의 차수를 <정점 ID, 차수>의 쌍으로 취함
//	for (unsigned i = 1; i < size; i++)
//		degrees.push_back(make_pair(i, G.edges[i].size()));
//
//	// 정점의 차수를 기준으로 내림차순 정렬
//	std::sort(degrees.begin(), degrees.end(), [](const auto& a, const auto& b) {
//		return a.second > b.second;
//		});
//
//	cout << "[색상 지정 순서 (괄호는 차수)]" << endl;
//	for (auto const i : degrees)
//		cout << i.first << " (" << i.second << ")" << endl;
//
//	vector<unsigned> assigned_colors(size);
//	int color_to_be_assigned = 1;
//
//	while (true) {
//		for (auto const i : degrees) {
//			// 이미 색칠되있으면 다음 정점 검사
//			if (assigned_colors[i.first] != 0) continue;
//
//			auto outgoing_edges = G.edges(i.first);
//
//			// i번째 정점과 인접해있는 정점들의 현재 색상
//			set<unsigned> neighbours;
//
//			for (auto& e : outgoing_edges)
//				neighbours.insert(assigned_colors[e.dst]);
//
//
//			// i번째 정점과 인접한 정점이 color_to_be_assigned 색상을 가지고 있지 않다면
//			// i번째 정점에 color_to_be_assigned 색상을 지정한다.
//			if (neighbours.find(color_to_be_assigned) == neighbours.end())
//				assigned_colors[i.first] = color_to_be_assigned;
//		}
//
//		color_to_be_assigned++;
//
//		// 모든 정점에 칠해져있으면 종료
//		if (std::find(assigned_colors.begin() + 1, assigned_colors.end(), 0) == assigned_colors.end())
//			break;
//	}
//
//	return assigned_colors;
//}
//
//template <typename T>
//void print_colors(vector<T>& colors) {
//	for (auto i = 1; i < colors.size(); i++)
//		cout << i << ": " << color_map[colors[i]] << endl;
//}
//
//void welsh_powell_test() {
//	using T = unsigned;
//
//	// 그래프 객체 생성
//	welsh_powell::Graph<T> G(9);
//
//	map<unsigned, vector<pair<unsigned, T>>> edge_map;
//	edge_map[1] = { {2, 0}, {5, 0} };
//	edge_map[2] = { {1, 0}, {5, 0}, {4, 0} };
//	edge_map[3] = { {4, 0}, {7, 0} };
//	edge_map[4] = { {2, 0}, {3, 0}, {5, 0}, {6, 0}, {8, 0} };
//	edge_map[5] = { {1, 0}, {2, 0}, {4, 0}, {8, 0} };
//	edge_map[6] = { {4, 0}, {7, 0}, {8, 0} };
//	edge_map[7] = { {3, 0}, {6, 0} };
//	edge_map[8] = { {4, 0}, {5, 0}, {6, 0} };
//
//	for (auto& i : edge_map)
//		for (auto& j : i.second)
//			G.add_edge(welsh_powell::Edge<T>{i.first, j.first, j.second});
//
//	cout << "[입력 그래프]" << endl;
//	cout << G << endl;
//	
//	vector<unsigned> colors = welsh_powell_coloring<T>(G);
//	cout << "[그래프 컬러링]" << endl;
//	print_colors(colors);
//}