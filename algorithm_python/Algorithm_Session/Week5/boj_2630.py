import sys

# 색종이 만들기(미해결) - 분할정복

input = sys.stdin.readline 

n = int(input())

m = []

for _ in range(n):
    m.append(list(map(int, input().split())))

# 모두 같으면 true
def check_area(arr):
    if max(arr) == min(arr):
        if max(arr) == 1:
            return 1
        else:
            return 0
    return -1
    

cnt = [0, 0]

def cutting(n):
    if n == 1:
        return

    tmp = int(n/2)

    lt = m[0:tmp][0:tmp]
    lb = m[0:tmp][tmp:n]
    rt = m[tmp:n][0:tmp]
    rb = m[tmp:n][tmp:n]

    arrs = [lt, lb, rt, rb]

    for arr in arrs:
        print(f"arr : {arr}")

    for arr in arrs:
        if check_area(arr) == 1:
            cnt[1] += 1
        elif check_area(arr) == 0:
            cnt[0] += 1
        else:
            cutting(tmp)



cutting(n)
print(cnt[0])
print(cnt[1])