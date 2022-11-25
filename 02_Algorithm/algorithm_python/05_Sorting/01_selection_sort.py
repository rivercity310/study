
def selection_sort(arr):
    for i in range(len(arr)):
        min_index = i
        for j in range(i + 1, len(arr)):
            if arr[min_index] > arr[j]:
                min_index = j

        arr[min_index], arr[i] = arr[i], arr[min_index]


arr = [7, 5, 3, 2, 6, 8, 9, 1]

print(arr)
selection_sort(arr)
print(arr)


# 코딩 테스트에서는 특정 리스트에서 가장 작은 데이터를 찾는 일이 잦으므로
# 선택 정렬 소스코드 형태에 익숙해질 필요가 있다.
