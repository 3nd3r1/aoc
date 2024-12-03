ans = 0
with open("input.txt", "r") as fp:
    for line in fp.readlines():
        ovals = [int(level) for level in line.split(" ")]
        if len(ovals) < 3:
            ans += 1
            continue

        for i in range(0, len(ovals)):
            vals = ovals[0:i] + ovals[i+1:len(ovals)]
            inc = vals[0] < vals[1]
            good = True
            for i in range(1,len(vals)):
                if inc and (vals[i] - vals[i-1] <= 0 or vals[i] - vals[i-1] > 3):
                    good = False
                    break
                if not inc and (vals[i] - vals[i-1] >= 0 or vals[i] - vals[i-1] < -3):
                    good = False
                    break
            if good:
                ans += 1
                break

print(ans)
