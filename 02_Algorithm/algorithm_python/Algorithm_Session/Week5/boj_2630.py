import sys

# 색종이 만들기(미해결) - 분할정복

input = sys.stdin.readline 

n = int(input())

m = []

for _ in range(n):
    m.append(list(map(int, input().split())))
    
# 모두 같으면 true
def check_area(arr, n):
    chk = arr[0][0]

    for i in range(1, n):
        for j in range(n):
            if arr[i][j] != chk:
                return False
    
    return True
    

cnt = [0, 0]

def cutting(arr, n):
    if n == 1:
        cnt[arr[0][0]] += 1
        return

    tmp = n // 2

    arrs = [[], [], [], []]
    for i in range(tmp):
        arrs[0].append(arr[i][0:tmp])
    
    for i in range(tmp):
        arrs[1].append(arr[i][tmp:n])
    
    for i in range(tmp):
        arrs[2].append(arr[0:tmp][i])

    for i in range(tmp):
        arrs[3].append(arr[tmp:n][i])

    for arr in arrs:
        chk = check_area(arr, tmp)

        if chk:
            cnt[arr[0][0]] += 1
        else:
            cutting(arr, tmp)



cutting(m, n)
print(cnt[0])
print(cnt[1])