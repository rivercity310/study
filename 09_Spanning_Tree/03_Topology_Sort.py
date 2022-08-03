# [ 위상 정렬 ] : O(V + E)
# 사이클이 없는 방향 그래프(DAG, 유향 비순환 그래프)의 모든 노드를
# 방향성에 거스르지 않도록 순서대로 나열하는 것

# 진입차수(In-degree): 특정한 노드로 들어오는 간선의 개수
# 진출차수(Out-degree): 특정한 노드로 나가는 간선의 개수

# 위상 정렬은 DFS를 이용하는 방법과 큐를 이용하는 방법이 있다.
# 큐를 이용하는 위상 정렬 알고리즘의 동작 과정은 다음과 같다.
'''
1. 진입차수가 0인 모든 노드를 큐에 넣는다.
2. 큐가 빌 때까지 다음의 과정을 반복한다.
    2 - 1. 큐에서 원소를 꺼내 해당 노드에서 나가는 간선을 그래프에서 제거
    2 - 2. 새롭게 진입차수가 0이 된 노드를 큐에 넣는다.
3. 각 노드가 큐에 들어온 순서가 위상 정렬을 수행한 결과와 같다.
'''

# [위상 정렬의 특징]
# 1. 위상 정렬에서는 여러 가지 답이 존재할 수 있다.
#   => 한 단계에서 큐에 새롭게 들어가는 원소가 2개 이상인 경우
# 2. 모든 원소를 방문하기 전에 큐가 빈다면 사이클이 존재한다고 판단할 수 있다.
#   => 사이클에 포함된 원소 중에서 어떠한 원소도 큐에 들어가지 못하기 때문
# 3. 스택을 활용한 DFS를 이용해 위상 정렬을 수행하는 방법도 있다.


from collections import deque

v, e = map(int, input().split())
indegree = [0] * (v + 1)
graph = [[] for _ in range(v + 1)]


for _ in range(e):
    a, b = map(int, input().split())
    graph[a].append(b)
    indegree[b] += 1


def topology_sort():
    result = []      # 알고리즘 수행 결과를 담을 리스트
    q = deque()

    for i in range(1, v + 1):
        if indegree[i] == 0:
            q.append(i)

    while q:
        now = q.popleft()
        result.append(now)

        # 해당 원소와 연결된 노드들의 진입차수에서 1을 빼준다
        for i in graph[now]:
            indegree[i] -= 1

            # 새롭게 진입차수가 0이 되는 노드를 큐에 삽입해준다.
            if indegree[i] == 0:
                q.append(i)

    # 결과 출력
    for i in result:
        print(i, end=" ")


topology_sort()
