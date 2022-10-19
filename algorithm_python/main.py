import sys

input = sys.stdin.readline

n = int(input())

arr = []
cnt = [0] * (8001)           # cnt -4000 ~ 4000  cnt[4000] == 0
for _ in range(n):
    x = int(input())
    arr.append(x)
    cnt[4000 + x] += 1

arr.sort()

avg = sum(arr) / n
med = arr[(int)(n / 2)]
fre = -1
ran = arr[n - 1] - arr[0]

if cnt.count(max(cnt)) == 1:
    fre = cnt.index(max(cnt))
else:
    arr2 = []
    f = max(cnt)
    for i in range(len(cnt)):
        if cnt[i] == f:
            arr2.append(i)

    fre = arr2[1]


print("%d" % (avg + 0.5))
print(med)
print(fre)
print(ran)
