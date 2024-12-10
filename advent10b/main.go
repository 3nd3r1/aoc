package main

import (
	"fmt"
	"os"
	"strings"
)

func dfs(themap []string, i, j int) int {
    if themap[i][j] == '9' {
        return 1
    }

    ret := 0 

    if i-1 >= 0 && themap[i-1][j] == themap[i][j]+1 {
        ret += dfs(themap, i-1, j)
    }
    if i+1 < len(themap) && themap[i+1][j] == themap[i][j]+1 {
        ret += dfs(themap, i+1, j)
    }
    if j-1 >= 0 && themap[i][j-1] == themap[i][j]+1 {
        ret += dfs(themap, i, j-1)
    }
    if j+1 < len(themap[0]) && themap[i][j+1] == themap[i][j]+1 {
        ret += dfs(themap, i, j+1)
    }

    return ret
}

func solution(themap []string) int {
    ans := 0

    for i:=0; i<len(themap); i++ {
        for j:=0; j<len(themap[0]); j++ {
            if themap[i][j] == '0' {
                ans += dfs(themap, i, j)
            }
        }
    }

    return ans
}

func check(err error) {
	if err != nil {
		panic(err)
	}
}

func main() {
	data, err := os.ReadFile("../input.txt")
	check(err)

    themap := strings.Split(string(data), "\n")
    themap = themap[:len(themap)-1]

	fmt.Println(solution(themap))
}
