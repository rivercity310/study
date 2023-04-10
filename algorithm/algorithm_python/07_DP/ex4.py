N, M = map(int, input().split())

coins = []
for i in range(N):
    coins.append(int(input()))

dp = [10001] * (M + 1)
dp[0] = 0

for coin in coins:
    for i in range(coin, len(dp)):
        if dp[i - coin] != 10001:
            dp[i] = min(dp[i], dp[i - coin] + 1)

print(dp)
result = dp[M] if dp[M] != 10001 else -1
print(result)
