#include <iostream>
#include <vector>
using namespace std;

/*
#include <iostream>
#include <vector>
#include <iomanip>

using namespace std;

enum class city : int {
	LONDON, PARIS, SEOUL, CALIFORNIA, NEWYORK
};

ostream& operator<<(ostream& os, const city c) {
	switch (c) {
	case city::LONDON:
		os << "London";
		return os;
	case city::PARIS:
		os << "Paris";
		return os;
	case city::SEOUL:
		os << "Seoul";
		return os;
	case city::CALIFORNIA:
		os << "California";
		return os;
	case city::NEWYORK:
		os << "Newyork";
		return os;
	default:
		return os;
	}
}

class adj_mat_grp {
	vector<vector<int>> data;

	void setEdge(const city& c1, const city& c2, int weight) {
		int n1 = static_cast<int>(c1);
		int n2 = static_cast<int>(c2);

		data[n1][n2] = weight;
		data[n2][n1] = weight;
	}

public:
	adj_mat_grp(int n) {
		data.reserve(n);

		vector<int> v(n);
		fill(v.begin(), v.end(), -1);

		for (int i = 0; i < n; i++) data.push_back(v);
	}

	void addEdge(const city& c1, const city& c2, int weight) {
		cout << c1 << " <--> " << c2 << "의 엣지를 추가합니다: " << weight << endl;
		setEdge(c1, c2, weight);
	}

	void removeEdge(const city& c1, const city& c2) {
		cout << c1 << " <-X-> " << c2 << "의 엣지를 삭제합니다." << endl;
		setEdge(c1, c2, -1);
	}

	void prt_data() {
		for (vector<int> i : data) {
			for (int j : i)
				cout << setw(5) << left << j;
			cout << endl;
		}
	}
};

int main() {
	auto g = new adj_mat_grp(6);

	g->addEdge(city::CALIFORNIA, city::SEOUL, 8000);
	g->addEdge(city::CALIFORNIA, city::LONDON, 4000);
	g->addEdge(city::CALIFORNIA, city::PARIS, 5500);
	g->addEdge(city::LONDON, city::SEOUL, 4500);
	g->addEdge(city::LONDON, city::PARIS, 1000);
	g->addEdge(city::NEWYORK, city::CALIFORNIA, 2500);

	g->prt_data();

	g->removeEdge(city::CALIFORNIA, city::LONDON);

	g->prt_data();

}
*/


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