# [ 부품 찾기 ]


# 방법 1. 이진 탐색을 이용한 방법
def bin_search(arr, start, end, key):
    if start > end:
        return None

    mid = (start + end) // 2

    if arr[mid] == key:
        return mid
    elif arr[mid] > key:
        return bin_search(arr, start, mid - 1, key)
    else:
        return bin_search(arr, mid + 1, end, key)


N = int(input())
serial_number = list(map(int, input().split()))

M = int(input())
find_serial_number = list(map(int, input().split()))

for i in range(M):
    idx = bin_search(serial_number, 0, len(
        serial_number) - 1, find_serial_number[i])

    if idx != None:
        print("yes", end=" ")
    else:
        print("no", end=" ")


print()


# 방법 2. 계수 정렬의 개념을 이용한 방법
arr = [0] * 1000001

for i in serial_number:
    arr[i] = 1

for i in find_serial_number:
    if arr[i] == 1:
        print("yes", end=" ")
    else:
        print("no", end=" ")


print()


# 방법 3. 집합 자료형을 이용한 방법
arr = set(serial_number)

for i in find_serial_number:
    if i in arr:
        print("yes", end=" ")
    else:
        print("no", end=" ")
