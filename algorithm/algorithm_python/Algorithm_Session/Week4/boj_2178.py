import sys
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().split())

arr = []
for _ in range(n):
    tmp = list(map(int, input().rstrip()))
    arr.append(tmp)

rows = [-1, 1, 0, 0]
cols = [0, 0, -1, 1]

def bfs(r, c):
    q = deque()
    q.append((r, c))

    while q:
        curR, curC = q.popleft()
        
        for i in range(4):
            nextR = curR + rows[i]
            nextC = curC + cols[i]

            if nextR < 0 or nextC < 0 or nextR >= n or nextC >= m:
                continue

            if arr[nextR][nextC] == 1:
                arr[nextR][nextC] = arr[curR][curC] + 1
                q.append((nextR, nextC))

bfs(0, 0)
print(arr[n - 1][m - 1])