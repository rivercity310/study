import sys
from collections import deque

input = sys.stdin.readline

n, m, v = map(int, input().split())

graph = [[] for _ in range(n + 1)]
visited = [False] * (n + 1)

for _ in range(m):
    a, b = map(int, input().split())

    graph[a].append(b)
    graph[b].append(a)

def dfs(start):
    stk = deque([start])
    visited[start] = True
    
    while stk:
        now = stk.pop()
        print(now, end=" ")

        for next in graph[now]:
            if not visited[next]:
                visited[next] = True
                stk.append(next)
                
def dfs2(start):
    visited[start] = True
    print(start, end=" ")

    for next in graph[start]:
        if not visited[next]:
            dfs2(next)

def bfs(start):
    q = deque([start])
    visited[start] = True

    while q:
        now = q.popleft()
        print(now, end=" ")

        for next in graph[now]:
            if not visited[next]:
                visited[next] = True
                q.append(next)

bfs(v)
    
    
