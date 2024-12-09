def solution(themap: list):
    n = len(themap)
    m = len(themap[0])
    start = [0, 0]
    ans = 0

    for i in range(n):
        for j in range(m):
            if themap[i][j] in ("^", ">", "v", "<"):
                start = [i, j]

    for si in range(n):
        for sj in range(m):
            if themap[si][sj] != ".":
                continue
            oldstart = start
            oldmap = [row[:] for row in themap]
            themap[si][sj] = "#"
            cc = 0
            while cc < 2 * n * m:
                cc += 1
                i, j = start
                if themap[i][j] == "^":
                    if i - 1 < 0:
                        break
                    if themap[i - 1][j] == "#":
                        themap[i][j] = ">"
                    else:
                        themap[i][j] = "."
                        themap[i - 1][j] = "^"
                        start = [i - 1, j]
                elif themap[i][j] == ">":
                    if j + 1 >= m:
                        break
                    if themap[i][j + 1] == "#":
                        themap[i][j] = "v"
                    else:
                        themap[i][j] = "."
                        themap[i][j + 1] = ">"
                        start = [i, j + 1]
                elif themap[i][j] == "v":
                    if i + 1 >= n:
                        break
                    if themap[i + 1][j] == "#":
                        themap[i][j] = "<"
                    else:
                        themap[i][j] = "."
                        themap[i + 1][j] = "v"
                        start = [i + 1, j]
                elif themap[i][j] == "<":
                    if j - 1 < 0:
                        break
                    if themap[i][j - 1] == "#":
                        themap[i][j] = "^"
                    else:
                        themap[i][j] = "."
                        themap[i][j - 1] = "<"
                        start = [i, j - 1]
            if cc == 2 * n * m:
                ans += 1
            start = oldstart
            themap = [row[:] for row in oldmap]
    return ans


with open("input.txt", "r") as fp:
    print(solution([[c for c in line.strip("\n")] for line in fp.readlines()]))
