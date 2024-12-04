def solution(ss):
    n = len(ss)
    m = len(ss[0])
    ans = 0
    for i in range(n):
        for j in range(m):
            if ss[i][j] != 'A':
                continue
            if i-1 < 0 or i+1 >= n or j-1 < 0 or j+1 >= m:
                continue
            if not ((ss[i-1][j-1] == 'M' and ss[i+1][j+1] == 'S') or (ss[i-1][j-1] == 'S' and ss[i+1][j+1] == 'M')):
                continue
            if not ((ss[i-1][j+1] == 'M' and ss[i+1][j-1] == 'S') or (ss[i-1][j+1] == 'S' and ss[i+1][j-1] == 'M')):
                continue
            ans += 1
    return ans

with open("input.txt", "r") as fp:
    print(solution(list(s.strip("\n") for s in fp.readlines())))
