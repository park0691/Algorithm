import sys

N = int(sys.stdin.readline())
arr = [[*map(int, input().split())] for _ in range(N)]
minCost = 9999
visit = [[False for _ in range(N)] for _ in range(N)]

dy = [0, 0, -1, 0, 1]
dx = [0, -1, 0, 1, 0]

def acceptable(y, x):
    for i in range(5):
        ny, nx = y + dy[i], x + dx[i]
        if visit[ny][nx]:
            return False
    return True

def setVisit(y, x, val):
    for i in range(5):
        ny, nx = y + dy[i], x + dx[i]
        visit[ny][nx] = val

def getCost(y, x):
    cost = 0
    for i in range(5):
        ny, nx = y + dy[i], x + dx[i]
        cost += arr[ny][nx]
    return cost

def dfs(level, cost):
    global minCost

    if minCost <= cost:
        return

    if level == 3:
        minCost = min(minCost, cost)
        return

    for ny in range(1, N - 1):
        for nx in range(1, N - 1):
            if acceptable(ny, nx):
                setVisit(ny, nx, True)
                dfs(level + 1, cost + getCost(ny, nx))
                setVisit(ny, nx, False)

dfs(0, 0)

print(minCost)
