#include <iostream>
#include <vector>
using namespace std;

enum class city : int {
	MOSCOW,
	LONDON,
	SEOUL,
	SEATTLE,
	DUBAI,
	SYDNEY
};

std::ostream& operator<<(ostream& os, const city c) {
	switch (c) {
	case city::LONDON:
		os << "런던";
		return os;
	case city::MOSCOW:
		os << "모스크바";
		return os;
	case city::SEOUL:
		os << "서울";
		return os;
	case city::SEATTLE:
		os << "시에틀";
		return os;
	case city::DUBAI:
		os << "두바이";
		return os;
	case city::SYDNEY:
		os << "시드니";
		return os;
	default:
		return os;
	}
}

// 실제 데이터를 저장할 graph 구조체 정의
struct adj_mat_graph {
	vector<vector<int>> data;

	// 주어진 개수의 노드로 구성된 그래프를 구성하는 생성자 추가
	adj_mat_graph(int n) {
		data.reserve(n);	// n만큼의 용량을 미리 확보해둠 -> vector의 재할당 문제
		vector<int> row(n);
		fill(row.begin(), row.end(), -1);

		for (int i = 0; i < n; i++)
			data.push_back(row);
	}

	// 엣지를 추가하는 함수, 두개의 도시와 가중치를 인자로 받음
	void addEdge(const city c1, const city c2, int dis) {
		cout << "엣지 추가: " << c1 << " - " << c2 << " = " << dis << endl;

		int n1 = static_cast<int>(c1);
		int n2 = static_cast<int>(c2);

		data[n1][n2] = dis;
		data[n2][n1] = dis;
	}

	// 엣지를 제거하는 함수
	void removeEdge(const city c1, const city c2) {
		cout << "엣지 삭제: " << c1 << " - " << c2 << endl;

		int n1 = static_cast<int>(c1);
		int n2 = static_cast<int>(c2);

		data[n1][n2] = -1;
		data[n2][n1] = -1;
	}

	void prt_graph() {
		cout << endl;
		for (auto i : data) {
			for (auto j : i)
				printf("%-10d", j);
			cout << endl;
		}

		cout << endl;
	}
};

void chap2_adj_matrix_graph() {
	adj_mat_graph g(6);

	g.addEdge(city::LONDON, city::MOSCOW, 2500);
	g.addEdge(city::LONDON, city::SEOUL, 9000);
	g.addEdge(city::LONDON, city::DUBAI, 5500);
	g.addEdge(city::SEOUL, city::MOSCOW, 6600);
	g.addEdge(city::SEOUL, city::SEATTLE, 8000);
	g.addEdge(city::SEOUL, city::DUBAI, 7000);
	g.addEdge(city::SEOUL, city::SYDNEY, 8000);
	g.addEdge(city::SEATTLE, city::MOSCOW, 8400);
	g.addEdge(city::SEATTLE, city::SYDNEY, 12000);
	g.addEdge(city::DUBAI, city::SYDNEY, 1200);

	g.prt_graph();

	g.addEdge(city::SEATTLE, city::LONDON, 8000);
	g.removeEdge(city::SEATTLE, city::LONDON);

	g.prt_graph();
}