# 2차원 리스트(행렬)을 90도 회전하는 메서드
def rotate_a_matrix_by_90_degree(a):
    row_length = len(a)
    col_length = len(a[0])

    res = [[0] * row_length for _ in range(col_length)]
    for row in range(row_length):
        for col in range(col_length):
            res[col][row_length - 1 - row] = a[row][col]

    return res


a = [
    [1, 2, 3, 4],
    [5, 6, 7, 8],
    [9, 10, 11, 12]
]

# 180도 회전
for _ in range(2):
    a = rotate_a_matrix_by_90_degree(a)

print(a)
