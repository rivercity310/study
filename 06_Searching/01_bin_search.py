from bisect import bisect_left, bisect_right


def bin_search(arr, start, end, key):
    pl = start
    pr = end

    while pl < pr:
        pc = (pl + pr) // 2

        if arr[pc] == key:
            return pc
        elif arr[pc] > key:
            pr = pc - 1
        else:
            pl = pc + 1

    return -1


# 이진 탐색 : 재귀적 구현
def bin_search2(arr, start, end, key):
    if start > end:
        return None

    mid = (start + end) // 2

    if arr[mid] == key:
        return mid
    elif arr[mid] > key:
        return bin_search2(arr, start, mid - 1, key)
    else:
        return bin_search2(arr, mid + 1, end, key)


# 또한 python의 bisect 라이브러리를 사용할 수도 있다.
# C++의 upper_bound, lower_bound와 동일

# bisect 라이브러리를 이용해 값이 특정 범위에 속하는 원소의 개수 구하기
def count_by_range(a, left_value, right_value):
    right_index = bisect_right(a, right_value)
    left_index = bisect_left(a, left_value)

    return right_index - left_index


arr = [1, 3, 4, 4, 6, 8, 10, 12, 15]

n = int(input("찾을 값? "))

idx = bin_search2(arr, 0, len(arr) - 1, n)

if idx != None:
    print(f"{arr[idx]}는 {idx + 1}번째에 있습니다.")
else:
    print("존재하지 않습니다.")

count_four = count_by_range(arr, 4, 4)
count_three_to_ten = count_by_range(arr, 3, 10)

print(f"4의 개수: {count_four}")
print(f"3부터 10 사이의 개수 (양 끝 포함): {count_three_to_ten}")
