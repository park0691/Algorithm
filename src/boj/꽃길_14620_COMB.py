import sys
from itertools import combinations

N = int(sys.stdin.readline())
arr = [[*map(int, input().split())] for _ in range(N)]
pos = [(y, x) for y in range(1, N - 1) for x in range(1, N - 1)]
minCost = 9999

# for i in range(1, N - 1):
#     for j in range(1, N - 1):
#         pos.append((i, j))

for p in combinations(pos, 3):
    tempArr = [[False for _ in range(N)] for _ in range(N)]
    tempCost = 0
    for i in range(3):
        y = p[i][0]
        x = p[i][1]

        if tempArr[y][x] or tempArr[y - 1][x] or tempArr[y + 1][x] or tempArr[y][x - 1] or tempArr[y][x + 1]:
            break

        tempArr[y][x] = True
        tempArr[y - 1][x] = True
        tempArr[y + 1][x] = True
        tempArr[y][x - 1] = True
        tempArr[y][x + 1] = True

        tempCost += arr[y][x]
        tempCost += arr[y - 1][x]
        tempCost += arr[y + 1][x]
        tempCost += arr[y][x - 1]
        tempCost += arr[y][x + 1]

        if i == 2:
            minCost = min(minCost, tempCost)

print(minCost)