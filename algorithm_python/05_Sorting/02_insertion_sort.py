def insertion_sort(arr):
    for i in range(1, len(arr)):
        for j in range(i, 0, -1):
            if arr[j] < arr[j - 1]:
                arr[j], arr[j - 1] = arr[j - 1], arr[j]
            else:
                break


arr = [7, 5, 9, 0, 1, 3, 6, 2, 4]

print(arr)
insertion_sort(arr)
print(arr)


# 삽입 정렬의 시간 복잡도는 O(N^2)이지만 데이터가 거의 정렬되어 있는 상태라면 매우 빠르게 동작한다.
# (최선의 경우 O(N)의 시간 복잡도를 가지고, 퀵 정렬보다 강력하다)
