import sys, copy

N, M = map(int, sys.stdin.readline().split())
arr = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
cctvs = []
walls = []
dy = [0, -1, 0, 1]
dx = [-1, 0, 1, 0]
dir = {
    'UP' : 1, 'DOWN' : 3, 'LEFT' : 0, 'RIGHT' : 2
}
minCnt = 64

for i in range(N):
    for j in range(M):
        if 1 <= arr[i][j] <= 5:
            cctvs.append([i, j])
        if arr[i][j] == 6:
            walls.append([i, j])

def solve(i, map, watchCnt):
    global minCnt
    cnt = (N * M) - len(cctvs) - len(walls) - watchCnt
    if i == len(cctvs):
        if cnt < minCnt:
            minCnt = cnt
        return
    # shallow copy
    # tempMap = map.copy() #1
    # tempMap = map[:]

    cy = cctvs[i][0]
    cx = cctvs[i][1]

    if map[cy][cx] == 1:
        curMap = copy.deepcopy(map)
        curWatchCnt = watch('LEFT', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)

        curMap = copy.deepcopy(map)
        curWatchCnt = watch('RIGHT', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)

        curMap = copy.deepcopy(map)
        curWatchCnt = watch('UP', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)

        curMap = copy.deepcopy(map)
        curWatchCnt = watch('DOWN', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)
    elif map[cy][cx] == 2:
        curMap = copy.deepcopy(map)
        curWatchCnt = watch('LEFT', cy, cx, curMap)
        curWatchCnt += watch('RIGHT', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)

        curMap = copy.deepcopy(map)
        curWatchCnt = watch('UP', cy, cx, curMap)
        curWatchCnt += watch('DOWN', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)
    elif map[cy][cx] == 3:
        curMap = copy.deepcopy(map)
        curWatchCnt = watch('LEFT', cy, cx, curMap)
        curWatchCnt += watch('UP', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)

        curMap = copy.deepcopy(map)
        curWatchCnt = watch('UP', cy, cx, curMap)
        curWatchCnt += watch('RIGHT', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)

        curMap = copy.deepcopy(map)
        curWatchCnt = watch('RIGHT', cy, cx, curMap)
        curWatchCnt += watch('DOWN', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)

        curMap = copy.deepcopy(map)
        curWatchCnt = watch('DOWN', cy, cx, curMap)
        curWatchCnt += watch('LEFT', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)
    elif map[cy][cx] == 4:
        curMap = copy.deepcopy(map)
        curWatchCnt = watch('LEFT', cy, cx, curMap)
        curWatchCnt += watch('UP', cy, cx, curMap)
        curWatchCnt += watch('RIGHT', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)

        curMap = copy.deepcopy(map)
        curWatchCnt = watch('UP', cy, cx, curMap)
        curWatchCnt += watch('RIGHT', cy, cx, curMap)
        curWatchCnt += watch('DOWN', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)

        curMap = copy.deepcopy(map)
        curWatchCnt = watch('RIGHT', cy, cx, curMap)
        curWatchCnt += watch('DOWN', cy, cx, curMap)
        curWatchCnt += watch('LEFT', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)

        curMap = copy.deepcopy(map)
        curWatchCnt = watch('DOWN', cy, cx, curMap)
        curWatchCnt += watch('LEFT', cy, cx, curMap)
        curWatchCnt += watch('UP', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)
    elif map[cy][cx] == 5:
        curMap = copy.deepcopy(map)
        curWatchCnt = watch('LEFT', cy, cx, curMap)
        curWatchCnt += watch('UP', cy, cx, curMap)
        curWatchCnt += watch('RIGHT', cy, cx, curMap)
        curWatchCnt += watch('DOWN', cy, cx, curMap)
        solve(i + 1, curMap, curWatchCnt + watchCnt)

def watch(dir, y, x, map):
    cnt = 0
    if dir == 'UP':
        for cy in range(y - 1, -1, -1):
            if map[cy][x] == 0:
                cnt += 1
                map[cy][x] = '#'
            elif map[cy][x] == 6:
                break
    elif dir == 'DOWN':
        for cy in range(y + 1, N):
            if map[cy][x] == 0:
                cnt += 1
                map[cy][x] = '#'
            elif map[cy][x] == 6:
                break
    elif dir == 'LEFT':
        for cx in range(x - 1, -1, -1):
            if map[y][cx] == 0:
                cnt += 1
                map[y][cx] = '#'
            elif map[y][cx] == 6:
                break
    elif dir == 'RIGHT':
        for cx in range(x + 1, M, 1):
            if map[y][cx] == 0:
                cnt += 1
                map[y][cx] = '#'
            elif map[y][cx] == 6:
                break
    return cnt

solve(0, arr, 0)
print(minCnt)