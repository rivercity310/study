import sys

# recursion depth 완화
sys.setrecursionlimit(3000)


# top-down(하향식) 방식 : 메모이제이션 기법
memo = [0] * 1000


def fib(x):
    if x == 1 or x == 2:
        return 1

    if memo[x] != 0:
        return memo[x]

    memo[x] = fib(x - 1) + fib(x - 2)
    return memo[x]


print(fib(200))


# bottom-up(상향식) 방식 : dp 테이블

dp = [0] * 1000

dp[1] = 1
dp[2] = 2

for i in range(3, len(dp)):
    dp[i] = dp[i - 1] + dp[i - 2]

print(dp[300])
