left = []
right = []

with open("input.txt", "r") as fp:
    for line in fp.readlines():
        left.append(int(line.split("  ")[0]))
        right.append(int(line.split("  ")[1]))

ans = 0
left = sorted(left)
right = sorted(right)

for l, r in zip(left,right):
    ans += abs(l-r)

print(ans)
