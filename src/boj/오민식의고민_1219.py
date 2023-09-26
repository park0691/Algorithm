import sys
from collections import deque

input = sys.stdin.readline
INF = sys.maxsize

V, START, DEST, E = map(int, input().split())
edges = []
profits = []
visit = [False for _ in range(V)]
queue = deque([])

for _ in range(E):
    a, b, c = map(int, input().split())
    edges.append([a, b, c])

profits = list(map(int, input().split()))

for i in range(E):
    edges[i][2] = edges[i][2] - profits[edges[i][1]]

# 최단 거리 테이블
distance = [INF] * V

def bellman_ford(start):
    distance[start] = -profits[start]

    # V - 1번 반복
    for i in range(V):
        # 매 반복마다 '모든 간선' 확인
        for j in range(E):
            cur = edges[j][0]
            next_node = edges[j][1]
            cost = edges[j][2]
            # 현재 간선 거쳐 이동 거리가 더 짧으면
            if distance[cur] != INF and distance[cur] + cost < distance[next_node]:
                distance[next_node] = distance[cur] + cost

    # 음수 간선의 사이클 체크 (값 갱신된다면, 음수 사이클 존재)
    for j in range(E):
        cur = edges[j][0]
        next_node = edges[j][1]
        cost = edges[j][2]
        # cycle node 인 경우 담기
        if distance[cur] != INF and distance[cur] + cost < distance[next_node]:
            distance[next_node] = distance[cur] + cost
            queue.append(cur)
            visit[cur] = True


def bfs():
    while True:
        if len(queue) == 0:
            break

        v = queue.pop()
        for e in edges:
            if e[0] != v:
                continue
            adj = e[1]
            cost = e[2]
            if visit[adj]:
                continue
            visit[adj] = True
            queue.append(adj)
    return visit[DEST]


bellman_ford(START)

# 도착 불가인 경우
if distance[DEST] == INF:
    print('gg')
# 돈을 무한히 많이 가지고 있을 수 있다면
elif bfs():
    print('Gee')
else:
    print(-distance[DEST])
