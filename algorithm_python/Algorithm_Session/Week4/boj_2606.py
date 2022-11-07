import sys
from collections import deque

input = sys.stdin.readline

n = int(input())
m = int(input())

graph = [[] for _ in range(n + 1)]
visited = [False] * (n + 1)

for _ in range(m):
    a, b = map(int, input().split())

    graph[a].append(b)
    graph[b].append(a)

cnt = -1
q = deque([1])
visited[1] = True

while q:
    now = q.popleft()
    cnt += 1

    for next in graph[now]:
        if not visited[next]:
            visited[next] = True
            q.append(next)

print(cnt)
