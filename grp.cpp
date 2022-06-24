#include <iostream>
#include <vector>
#include <map>
#include <iomanip>
using namespace std;

// 인접 행렬을 이용한 그래프 구현
class adj_mat_ex {
private:
	vector<vector<int>> mat;

	void setEdge(const int n1, const int n2, const int weight) {
		mat[n1 - 1][n2 - 1] = weight;
		mat[n2 - 1][n1 - 1] = weight;
	}
	
public:
	adj_mat_ex(int n) {
		mat.reserve(n);

		vector<int> v(n);
		fill(v.begin(), v.end(), -1);

		for (int i = 0; i < n; i++)
			mat.push_back(v);
	}

	void addEdge(const int n1, const int n2, const int weight) {
		cout << n1 << " < ----- > " << n2 << " 엣지를 추가합니다." << endl;
		setEdge(n1, n2, weight);
	}

	void removeEdge(const int n1, const int n2) {
		cout << n1 << " < ----- > " << n2 << " 엣지를 삭제합니다." << endl;
		setEdge(n1, n2, -1);
	}

	friend ostream& operator<<(ostream& os, adj_mat_ex am) {
		for (auto i : am.mat) {
			for (auto j : i)
				os << setw(5) << right << j;
			os << "\n\n";
		}

		return os;
	}
};

class adj_list_ex {
private:
	vector<vector<pair<int, int>>> data;

	void setEdge(const int n1, const int n2, const int weight) {
		data[n1 - 1].push_back(make_pair(n2, weight));
		data[n2 - 1].push_back(make_pair(n1, weight));
	}
public:
	adj_list_ex(int n) {
		data = vector<vector<pair<int, int>>>(n, vector<pair<int, int>>());
	}

	void addEdge(const int n1, const int n2, const int weight) {
		cout << n1 << " < ----- > " << n2 << " 엣지를 추가합니다." << endl;
		setEdge(n1, n2, weight);
	}

	void removeEdge(const int n1, const int n2) {
		cout << n1 << " < ----- > " << n2 << " 엣지를 삭제합니다." << endl;
		setEdge(n1, n2, -1);
	}

	friend ostream& operator<<(ostream& os, adj_list_ex ad) {
		for (auto i : ad.data) {
			for (auto j : i) {
				os << "(" << j.first << ", " << j.second << ")   =>   ";
			}
			os << endl;
		}

		return os;
	}
};

void grp_ex() {
	adj_mat_ex grp1(8);
	grp1.addEdge(1, 2, 10);
	grp1.addEdge(1, 3, 20);
	grp1.addEdge(2, 3, 30);
	grp1.addEdge(2, 5, 40);
	grp1.addEdge(3, 4, 50);
	grp1.addEdge(3, 6, 60);
	grp1.addEdge(4, 5, 70);
	grp1.addEdge(5, 6, 80);

	cout << "[엣지를 삭제하기 전의 인접 행렬 그래프]" << endl;
	cout << grp1 << endl;

	grp1.removeEdge(3, 4);
	cout << "[엣지를 삭제한 후 인접 행렬 그래프]" << endl;
	cout << grp1 << endl;


	adj_list_ex grp2(8);
	grp2.addEdge(1, 2, 10);
	grp2.addEdge(1, 3, 20);
	grp2.addEdge(2, 3, 30);
	grp2.addEdge(2, 5, 40);
	grp2.addEdge(3, 4, 50);
	grp2.addEdge(3, 6, 60);
	grp2.addEdge(4, 5, 70);
	grp2.addEdge(5, 6, 80);

	cout << "[엣지를 삭제하기 전의 인접 리스트 그래프]" << endl;
	cout << grp2 << endl;

	grp2.removeEdge(3, 4);
	cout << "[엣지를 삭제한 후 인접 리스트 그래프]" << endl;
	cout << grp2 << endl;


}