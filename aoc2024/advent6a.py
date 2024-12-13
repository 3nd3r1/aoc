def solution(themap: list):
    n = len(themap)
    m = len(themap[0])
    visited = [[False for _ in range(m)] for _ in range(n)]
    start = [0, 0]
    ans = 0

    for i in range(n):
        for j in range(m):
            if themap[i][j] in ("^", ">", "v", "<"):
                start = [i, j]

    while True:
        i, j = start
        if not visited[i][j]:
            ans += 1
        visited[i][j] = True

        if themap[i][j] == "^":
            if i - 1 < 0:
                return ans
            if themap[i - 1][j] == "#":
                themap[i][j] = ">"
            else:
                themap[i][j] = '.'
                themap[i-1][j] = '^'
                start = [i - 1, j]
        elif themap[i][j] == ">":
            if j + 1 >= m:
                return ans
            if themap[i][j + 1] == "#":
                themap[i][j] = "v"
            else:
                themap[i][j] = '.'
                themap[i][j+1] = '>'
                start = [i, j + 1]
        elif themap[i][j] == "v":
            if i + 1 >= n:
                return ans
            if themap[i + 1][j] == "#":
                themap[i][j] = "<"
            else:
                themap[i][j] = '.'
                themap[i+1][j] = 'v'
                start = [i + 1, j]
        elif themap[i][j] == "<":
            if j - 1 < 0:
                return ans
            if themap[i][j - 1] == "#":
                themap[i][j] = "^"
            else:
                themap[i][j] = '.'
                themap[i][j-1] = '<'
                start = [i, j - 1]


with open("input.txt", "r") as fp:
    print(solution([[c for c in line.strip("\n")] for line in fp.readlines()]))
