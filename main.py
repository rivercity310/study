N = int(input())


arr = []
for _ in range(N):
    arr.append(int(input()))

st = [0] * (max(arr) + 1)

for i in arr:
    st[i] += 1

for i in range(len(st)):
    for j in range(st[i]):
        print(i)
