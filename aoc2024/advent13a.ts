import { readFileSync } from "node:fs";

const INF = 1000000;

const solution = (
    a: [number, number],
    b: [number, number],
    prize: [number, number],
): number => {
    let ans = INF;

    for (let i = 0; i <= 100; i++) {
        for (let j = 0; j <= 100; j++) {
            if (
                a[0] * i + b[0] * j === prize[0] &&
                a[1] * i + b[1] * j === prize[1]
            ) {
                ans = Math.min(ans, i * 3 + j);
            }
        }
    }

    if (ans == INF) {
        return 0;
    }
    return ans;
};

const file = readFileSync("./input.txt", "utf-8");
const lines = file.toString().split("\n");

let ans = 0;

for (let i = 2; i < lines.length; i += 4) {
    const a: [number, number] = [
        Number(lines[i - 2].split(", ")[0].split("+")[1]),
        Number(lines[i - 2].split(", ")[1].split("+")[1]),
    ];
    const b: [number, number] = [
        Number(lines[i - 1].split(", ")[0].split("+")[1]),
        Number(lines[i - 1].split(", ")[1].split("+")[1]),
    ];
    const prize: [number, number] = [
        Number(lines[i].split(", ")[0].split("=")[1]),
        Number(lines[i].split(", ")[1].split("=")[1]),
    ];

    ans += solution(a, b, prize);
}

console.log(ans);
