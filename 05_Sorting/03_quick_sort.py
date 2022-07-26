# [ 퀵 정렬 ] : 평균 시간 복잡도 = O(NlogN)
# 1. 피벗은 리스트의 첫번쨰 원소이다.
# 2. 리스트의 왼쪽부터 피벗보다 큰 원소를, 오른쪽부터 피벗보다 작은 원소를 찾는다.
# 3. 두 값을 교환한다. 단, 엇갈린 경우 작은 데이터(오른쪽)와 피벗의 위치를 변경한다.   => 분할(파티션)
# 4. 위 과정을 분할된 리스트에 대해 각각 재귀적으로 적용한다. (종료 조건: 리스트의 데이터 개수가 1개인 경우)

def quick_sort(arr, start, end):
    if start >= end:
        return

    pivot = start
    left = start + 1
    right = end

    # 엇갈릴 때까지 반복 => pivot과 right를 교환하는 순간 분할이 완성된다.
    while left <= right:
        while left <= end and arr[left] <= arr[pivot]:
            left += 1

        while right > start and arr[right] >= arr[pivot]:
            right -= 1

        if left > right:   # 엇갈렸다면 작은 값과 피벗을 스왑한다.
            arr[right], arr[pivot] = arr[pivot], arr[right]
        else:
            arr[left], arr[right] = arr[right], arr[left]

    # 분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬을 수행한다.
    quick_sort(arr, start, right - 1)
    quick_sort(arr, right + 1, end)


# 파이썬의 장점을 살린 퀵 정렬 소스코드 (리스트 컴프리헨션, 슬라이싱 활용)
def quick_sort2(arr):
    if len(arr) <= 1:
        return arr

    pivot = arr[0]
    tail = arr[1:]

    left_side = [x for x in tail if x <= pivot]
    right_side = [x for x in tail if x > pivot]

    return quick_sort2(left_side) + [pivot] + quick_sort2(right_side)


arr = [7, 4, 5, 6, 3, 2, 1, 9, 8]

print(arr)
quick_sort(arr, 0, len(arr) - 1)
print(arr)

arr = [7, 4, 5, 6, 3, 2, 1, 9, 8]
print(arr)
print(quick_sort2(arr))
