# dfs 메서드 정의
def dfs(graph, v, visited):
    # 현재 노드 방문처리
    visited[v] = True
    print(v, end=" ")

    # 현재 노드와 연결된 다른 노드를 재귀적으로 방문
    for i in graph[v]:
        if not visited[i]:
            dfs(graph, i, visited)


def dfs2(start):
    visited[start] = True
    print(start, end=" ")

    for i in graph[start]:
        if not visited[i]:
            dfs2(i)


'''
graph = [
    [],
    [2, 3, 8],
    [1, 7],
    [1, 4, 5],
    [3, 5],
    [3, 4],
    [7],
    [2, 6, 8],
    [1, 7]
]
'''

graph = list([] for _ in range(9))
graph[0] = []
graph[1] = [2, 3, 8]
graph[2] = [1, 7]
graph[3] = [1, 4, 5]
graph[4] = [3, 5]
graph[5] = [3, 4]
graph[6] = [7]
graph[7] = [2, 6, 8]
graph[8] = [1, 7]

visited = [False] * 9

dfs2(1)
