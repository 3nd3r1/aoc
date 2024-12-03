ans = 0
with open("input.txt", "r") as fp:
    for line in fp.readlines():
        levels = [int(level) for level in line.split(" ")]
        if len(levels) < 2:
            ans += 1
            continue

        increasing = levels[0] < levels[1]
        good = True
        for i in range(1,len(levels)):
            if increasing and (levels[i] - levels[i-1] <= 0 or levels[i] - levels[i-1] > 3):
                good = False
                break
            if not increasing and (levels[i] - levels[i-1] >= 0 or levels[i] - levels[i-1] < -3):
                good = False
                break
        if good:
            ans += 1

print(ans)
