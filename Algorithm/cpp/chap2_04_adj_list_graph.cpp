#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/*
#include <iostream>
#include <vector>
#include <iomanip>
using namespace std;

enum class city : int {
	MOSCOW,
	LONDON,
	SEOUL,
	SEATTLE,
	DUBAI,
	SYDNEY
};

extern std::ostream& operator<<(ostream& os, const city c);

class adj_list_grp {
	vector<vector<pair<int, int>>> data;

	void setEdge(const city& c1, const city& c2, int weight) {
		int n1 = static_cast<int>(c1);
		int n2 = static_cast<int>(c2);

		data[n1].push_back(make_pair(n2, weight));
		data[n2].push_back({ n1, weight });
	}

public:
	adj_list_grp(int n) {
		data = vector<vector<pair<int, int>>>(n, vector<pair<int, int>>());
	}

	void addEdge(const city& c1, const city& c2, int weight) {
		setEdge(c1, c2, weight);
	}

	void removeEdge(const city& c1, const city& c2) {
		setEdge(c1, c2, -1);
	}

	void prt_data() {
		cout << endl;
		for (vector<pair<int, int>> i : data) {
			for (pair<int, int> j : i) {
				cout << "(" << static_cast<city>(j.first) << ", " << j.second << ") => ";
			}
			cout << endl;
		}

		cout << endl;
	}
};


int main() {
	auto g = new adj_list_grp(6);

	g->addEdge(city::DUBAI, city::LONDON, 3000);
	g->addEdge(city::DUBAI, city::SEATTLE, 4000);
	g->addEdge(city::SEATTLE, city::MOSCOW, 5000);
	g->addEdge(city::SEOUL, city::LONDON, 6000);
	g->addEdge(city::SYDNEY, city::SEATTLE, 7000);
	g->addEdge(city::MOSCOW, city::SEOUL, 8000);

	g->prt_data();
}
*/

// extern enum class city : int;  => enum 형은 컴파일 타임 상수이므로 extern이 안된다..
enum class city : int {
	MOSCOW,
	LONDON,
	SEOUL,
	SEATTLE,
	DUBAI,
	SYDNEY
};
extern std::ostream& operator<<(ostream& os, const city c);

struct adj_list_graph {
	vector<vector<pair<int, int>>> data;

	adj_list_graph(int n) {
		data = vector<vector<pair<int, int>>>(n, vector<pair<int, int>>());
	}

	void addEdge(const city c1, const city c2, int dis) {
		cout << "엣지 추가: " << c1 << " - " << c2 << " = " << dis << endl;
		
		int n1 = static_cast<int>(c1);
		int n2 = static_cast<int>(c2);

		data[n1].push_back(make_pair(n2, dis));
		data[n2].push_back({ n1, dis });
	}

	void removeEdge(const city c1, const city c2) {
		cout << "엣지 삭제: " << c1 << " - " << c2 << endl;

		int n1 = static_cast<int>(c1);
		int n2 = static_cast<int>(c2);

		auto it = remove_if(data[n1].begin(), data[n1].end(), [n2](const auto& pair) {
			return pair.first == n2;
			});
		data[n1].erase(it, data[n1].end());

		it = remove_if(data[n2].begin(), data[n2].end(), [n1](const auto& pair) {
			return pair.first == n1;
			});
		data[n2].erase(it, data[n2].end());
	}

	void prt_graph() {
		cout << endl;
		for (auto i : data) {
			for (auto j : i)
				printf("(%d, %d)\t", j.first, j.second);
			putchar('\n');
		}
		cout << endl;
	}
};

void chap2_adj_list_graph() {
	adj_list_graph g(6);

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

