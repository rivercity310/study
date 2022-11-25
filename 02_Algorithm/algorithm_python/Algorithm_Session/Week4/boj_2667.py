import sys
from collections import deque

input = sys.stdin.readline

n = int(input())

arr = []
rows = [-1, 1, 0, 0]
cols = [0, 0, -1, 1]
size = len(arr)
ans = []

for _ in range(n):
    arr.append(list(map(int, input().rstrip())))

def bfs(i, j):
    q = deque()
    q.append((i, j))
    arr[i][j] = 0
    cnt = 0

    while q:
        curR, curC = q.popleft()
        cnt += 1

        print(f"curR: {curR}, curC: {curC}")

        for i in range(4):
            nextR = curR + rows[i]
            nextC = curC + cols[i]

            if not(nextR < 0 or nextC < 0 or nextR >= size or nextC >= size or arr[nextR][nextC] == 0):
                q.append((nextR, nextC))
                arr[nextR][nextC] = 0
    
    ans.append(cnt)

for i in range(n):
    for j in range(n):
        if arr[i][j] == 1:
            bfs(i, j)

ans.sort()

print(len(ans))
for val in ans:
    print(val)