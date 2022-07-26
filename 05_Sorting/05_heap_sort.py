import heapq


# 파이썬에는 PriorityQueue 라이브러리도 제공하고 있지만, 코테 환경에서는 heapq가 더 빠르다.
# 파이썬의 힙은 최소 힙으로 구성되어 있고, 단순히 원소를 힙에 넣었다가 뺴는 것만으로도
# O(NlogN)에 오름차순 정렬이 완료된다.
#
# 최대 힙을 따로 제공하지 않으므로 원소의 부호를 변경하는 방식을 이용하여 구현해야 한다.


def heapsort(iterable):
    h = []
    result = []

    # 모든 원소를 차례대로 힙에 삽입
    for value in iterable:
        heapq.heappush(h, value)

    # 힙에 삽입된 모든 원소를 꺼내 담기
    for _ in range(len(h)):
        result.append(heapq.heappop(h))

    return result


result = heapsort([1, 3, 5, 7, 9, 2, 4, 6, 8, 10])
print(f"result = {result}")
