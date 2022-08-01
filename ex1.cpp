#include <iostream>

using namespace std;

constexpr int INF = 1e9;

/*
[ 방문 판매원 문제 ]
입력받은 그래프에서 1 -> K -> X로 가는 최단 경로 값 구하기
*/

// C++에서 2차원 배열은 배열포인터 형으로 반환해야 한다.
// 배열 포인터와 포인터 배열(ex. int* arr[])의 차이는 구글 검색
typedef int(*arrPointer)[501];

arrPointer init_grp(int N, int M) {
	// 반환 후 지역변수 gp가 사라지는 것을 막기 위해 static 선언
	static int gp[501][501] = { 0, };

	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++) {
			if (i == j) gp[i][j] = 0;
			else gp[i][j] = INF;
		}

	while (M--) {
		int a, b;
		cin >> a >> b;

		gp[a][b] = 1;
		gp[b][a] = 1;
	}

	for (int k = 1; k <= N; k++)
		for (int a = 1; a <= N; a++)
			for (int b = 1; b <= N; b++)
				gp[a][b] = std::min(gp[a][b], gp[a][k] + gp[k][b]);

	return gp;
}

void chap7_ex1() {
	int N, M;
	cin >> N >> M;

	// 배열포인터 형으로 받기
	// arrPointer gp = init_grp(N, M) 또는
	int(*gp)[501] = init_grp(N, M);

	// 그래프 출력
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++)
			printf("%-5d", gp[i][j]);
		putchar('\n');
	}

	int X, K;
	cin >> X >> K;

	int dist = gp[1][K] + gp[K][X];
	int val = dist >= INF ? -1 : dist;

	cout << val;
}