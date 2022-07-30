from collections import deque

MAX = 9

visited = [False] * MAX

graph = list([] for _ in range(MAX))
graph[0] = []
graph[1] = [2, 3, 8]
graph[2] = [1, 7]
graph[3] = [1, 4, 5]
graph[4] = [3, 5]
graph[5] = [3, 4]
graph[6] = [7]
graph[7] = [2, 6, 8]
graph[8] = [1, 7]


def bfs(start):
    queue = deque([start])
    visited[start] = True

    while queue:
        curr_vertex = queue.popleft()
        visited[curr_vertex] = True

        print(curr_vertex, end=" ")

        for linked_vertex in graph[curr_vertex]:
            if not visited[linked_vertex]:
                queue.append(linked_vertex)
                visited[linked_vertex] = True


def dfs(start):
    visited[start] = True
    print(start, end=" ")

    for linked_vertex in graph[start]:
        if not visited[linked_vertex]:
            dfs(linked_vertex)


dfs(1)
