import heapq

# 최소 힙 O(NlogN)으로 오름차순 정렬이 완료된다.


# 힙 정렬 (오름차순)
def heapsort_asc(iterable):
    h = []
    result = []

    # 모든 원소를 차례대로 힙에 삽입
    for value in iterable:
        heapq.heappush(h, value)

    # 힙에 삽입된 모든 원소를 차례대로 꺼내어 담는다.
    for _ in range(len(h)):
        result.append(heapq.heappop(h))

    return result


# 힙 정렬 (내림차순) => 파이썬에서는 최대 힙 제공 X (부호를 임시로 변경하는 방식으로 구현 가능)
def heapsort_dsc(iterable):
    h = []
    result = []

    for value in iterable:
        heapq.heappush(h, -value)

    for _ in range(len(h)):
        result.append(-heapq.heappop(h))

    return result


a = [1, 3, 5, 7, 9, 2, 4, 6, 8, 0]

rst1 = heapsort_asc(a)
print(rst1)

rst2 = heapsort_dsc(a)
print(rst2)
