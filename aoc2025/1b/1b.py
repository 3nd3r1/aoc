with open("input.txt") as fp:
    lines = fp.readlines()
    dial = 50
    ans = 0
    for line in lines:
        times = int(line[1:]) % 100
        ans += (int(line[1:])-times)//100
        if line[0] == "L":
            if times > dial and dial != 0:
                ans += 1
            times *= -1
        else:
            if times + dial > 100:
                ans += 1
        dial += times
        dial = (dial + 100) % 100
        if dial == 0:
            ans += 1
        print(dial, ans)
    print(ans)
