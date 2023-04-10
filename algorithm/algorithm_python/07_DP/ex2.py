# 개미 전사

n = int(input())
arr = list(map(int, input().split()))

dp = [arr[0], max(arr[0], arr[1])]

for i in range(2, n):
    dp.append(max(dp[i - 1], dp[i - 2] + arr[i]))

print(dp[n - 1])
print(dp)
