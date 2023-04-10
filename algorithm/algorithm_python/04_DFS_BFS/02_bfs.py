from collections import deque


# bfs 메서드 정의
def bfs(graph, start, visited):
    # 큐 구현을 위해 deque 라이브러리 사용
    queue = deque([start])

    # 현재 노드 방문처리
    visited[start] = True

    # 큐가 빌 때까지 반복
    while queue:
        v = queue.popleft()
        print(v, end=" ")

        # 아직 방문하지 않은 인접한 원소들을 큐에 삽입
        for i in graph[v]:
            if not visited[i]:
                queue.append(i)
                visited[i] = True


visited = [False] * 9

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

bfs(graph, 1, visited)
