#define MAIN
#define DAY1
#define BS
#define DAY2

#ifdef MAIN
#include <iostream>
using namespace std;
#endif

#ifdef DAY1
#endif

#ifdef BS
extern void boj_1620();
#endif

#ifdef DAY2
extern void boj_9012();
extern void boj_1541();
#endif

#define MAX 101
#define LEN 10

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);


	bool arr[MAX][MAX] = {false,};
	
	int n;
	cin >> n;

	for (int i = 0; i < n; i++) {
		int row, col;
		cin >> row >> col;

		for (int r = row; r < row + LEN; r++) 
			for (int c = col; c < col + LEN; c++) 
				if (!arr[r][c])
					arr[r][c] = true;
	}

	int cnt = 0;
	for (int i = 0; i < MAX; i++)
		for (int j = 0; j < MAX; j++)
			if (arr[i][j])
				cnt++;

	cout << cnt;
}