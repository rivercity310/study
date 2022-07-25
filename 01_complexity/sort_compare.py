from random import randint
import time
import heapq

# 배열에 10,000개의 랜덤 정수 삽입
arr = []
for _ in range(10000):
    arr.append(randint(1, 100))

# 시간 측정
start_time = time.time()

# 선택 정렬: 범위 내 가장 작은 원소를 선택하여 맨 앞과 교환을 반복 O(N^2)
for i in range(len(arr)):
    min_index = i
    for j in range(i + 1, len(arr)):
        if arr[min_index] > arr[j]:
            min_index = j
    arr[i], arr[min_index] = arr[min_index], arr[i]

end_time = time.time()
print("선택 정렬 성능 O(N^2) = ", end_time - start_time)
print(arr[0:10])


# 배열에 10,000개의 랜덤 정수 삽입, 시간 측정
start_time = time.time()

arr = []
for _ in range(10000):
    heapq.heappush(arr, -randint(1, 100))

result = []
for _ in range(len(arr)):
    result.append(-heapq.heappop(arr))

end_time = time.time()
print("힙 정렬 성능 O(NlogN) = ", end_time - start_time)
print(result[0:10])

# 배열을 다시 무작위로 초기화
arr = []
for _ in range(10000):
    arr.append(randint(1, 100))

# 시간 측정 및 기본 정렬 라이브러리 성능 측정
start_time = time.time()

arr.sort()

end_time = time.time()

print("기본 정렬 라이브러리 성능 = ", end_time - start_time)
print(arr[0:10])
