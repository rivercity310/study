import sys

# [1이 될 때까지]
'''
어떠한 수 N이 1이 될 때까지 다음 두 과정 중 하나를 반복적으로 선택하여 수행하려고 한다.
단, 두번째 연산은 N이 K로 나누어떨어질 때만 선택할 수 있다.

1. N에서 1을 뺀다.
2. N을 K로 나눈다.

N과 K가 주어질 때, N이 1이 될 때까지 1번 혹은 2번 과정을 수행해야 하는 최소 횟수를 구하시오.
'''

# Idea
# 주어진 N에 대하여 최대한 많이 나누기 => 최소 횟수
N, K = map(int, sys.stdin.readline().rstrip().split())

cnt = 0
while (N != 1):
    if N % K == 0:
        N //= K
    else:
        N -= 1

    print("N =", N)

    cnt += 1

print(cnt)
