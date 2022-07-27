# [ 떡볶이 떡 만들기 ]

# 파라메트릭 서치 유형 -> 최적화 문제를 결정 문제(yes or no)로 바꾸어 해결하는 기법
# 원하는 조건을 만족하는 가장 알맞은 값을 찾는 경우 사용됨
# 주로 이진 탐색을 이용하여 해결한다.


# 순차 탐색을 이용한 방법
# N의 범위가 1부터 10억이므로 비효율적이다.
N, M = map(int, input().split())

t_height = list(map(int, input().split()))

cutted = []
result = -1

for h in range(min(t_height), max(t_height)):
    for i in range(N):
        c = t_height[i] - h if t_height[i] - h > 0 else 0
        cutted.append(c)

        print(f"total: {sum(cutted)}")

        if sum(cutted) >= M:
            result = h

    cutted = []

print(result)


# 이진 탐색을 이용한 방법
n2, m2 = map(int, input().split())

arr = list(map(int, input().split()))

start = 0
end = max(arr)

result = 0
while start <= end:
    total = 0
    mid = (start + end) // 2

    for x in arr:
        # 잘랐을 때의 떡의 양 계산
        if x > mid:
            atotal += (x - mid)

    print(f"total: {total}")

    # 떡의 양이 부족한 경우 더 많이 자르기 ( = 왼쪽 부분 탐색)
    if m2 < total:
        start = mid + 1
    else:
        result = mid        # 최대한 덜 잘랐을 때가 정답이므로 여기에서 result 기록
        end = mid - 1

print(result)
