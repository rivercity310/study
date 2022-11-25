import sys

N, M, K = map(int, sys.stdin.readline().rstrip().split())
arr = list(map(int, sys.stdin.readline().rstrip().split()))

# [ 큰수의 법칙 ]
# 주어진 수들을 M번 더하여 가장 큰 수를 만든다
# 단 같은 인덱스에 해당하는 수는 K번 초과하여 더할 수 없다.

arr.sort()
ans = 0

cnt = M

while True:
    for i in range(K):
        if cnt == 0:
            break
        ans += arr[-1]
        cnt -= 1

    if cnt == 0:
        break

    ans += arr[-2]
    cnt -= 1

print(ans)


# 위와 같은 알고리즘은 M의 크기가 커지면 시간 초과 판정을 받을 수 있다.
# 이 문제에서 다음과 같은 규칙을 찾을 수 있다.

# - 가장 큰 수가 K번, 두번째 큰 수가 1번 => (K + 1) 길이의 수열이 반복된다.
# - M / (K + 1)이 수열이 반복되는 횟수가 되고, M을 (K + 1)로 나눈 나머지만큼 가장 큰 수가 추가로 더해진다.


# 가장 큰 수가 더해지는 횟수 (int 대신 // 사용 가능)
count = (int(M / (K + 1)) * K) + (M % (K + 1))
print(f"count = {count}")

# 전체 횟수에서 가장 큰 수가 더해지는 횟수를 빼면 두번째 큰 수가 더해지는 횟수가 된다.
result = count * arr[N - 1]
result += (M - count) * arr[N - 2]
print(result)
