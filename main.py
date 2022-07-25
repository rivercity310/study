from collections import deque

visited = [False] * 9


def bfs(graph, start, visited):
    queue = deque([start])
    visited[start] = True

    while queue:
        v = queue.popleft()
        print(v, end=" ")

        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i] = True


visited2 = [False] * 9


def dfs(graph, start, visited2):
    visited2[start] = True
    print(start, end=" ")

    for i in graph[start]:
        if not visited2[i]:
            dfs(graph, i, visited2)


graph = [[] for _ in range(9)]
graph[0] = []
graph[1] = [2, 3, 8]
graph[2] = [1, 7]
graph[3] = [1, 4, 5]
graph[4] = [3, 5]
graph[5] = [3, 4]
graph[6] = [7]
graph[7] = [2, 6, 8]
graph[8] = [1, 7]

bfs(graph, 1, visited)
print()
dfs(graph, 1, visited2)
