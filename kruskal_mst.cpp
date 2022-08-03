#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

constexpr int MAX = 7;

struct edg {
	int src;
	int dst;
	int weight;
};

int pa[MAX];
vector<edg> edges;

void prt_edges() {
	for (edg& e : edges)
		cout << e.src << " " << e.dst << " " << e.weight << endl;
}

int find_par(int x) {
	if (pa[x] != x)
		pa[x] = find_par(pa[x]);
	return pa[x];
}

void union_par(int a, int b) {
	int root_a = find_par(a);
	int root_b = find_par(b);

	cout << "root_a : " << root_a << "\troot_b : " << root_b << "\n";

	if (root_a < root_b)
		pa[root_b] = root_a;
	else
		pa[root_a] = root_b;
}

vector<edg> kr(int& result) {
	result = 0;

	vector<edg> mst;

	for (edg& e : edges) {
		if (find_par(e.src) != find_par(e.dst)) {
			union_par(e.src, e.dst);
			result += e.weight;
			mst.push_back(edg{ e.src, e.dst, e.weight });
		}
		else
			cout << e.src << " <---> " << e.dst << " 사이클 발생\n";

	}

	return mst;
}

void kruskal_mst() {
	int N;
	cin >> N;

	for (int i = 1; i < MAX; i++)
		pa[i] = i;

	for (int i = 0; i < N; i++) {
		int a, b, cost;
		cin >> a >> b >> cost;

		edges.push_back(edg{ a, b, cost });
	}

	cout << "[입력 완료]" << "\n";
	prt_edges();

	std::sort(edges.begin(), edges.end(), [](const edg& a, const edg& b) {
		return a.weight < b.weight;
		});

	cout << "[가중치 기준으로 정렬 완료]" << "\n";
	prt_edges();

	cout << "[kruskal]" << "\n";
	int result;
	vector<edg> mst = kr(result);

	cout << "MST의 가중치 합 : " << result << "\n";
	for (edg& e : mst)
		cout << "(" << e.src << ", " << e.dst << ", " << e.weight << ")\t";
	cout << endl;
}