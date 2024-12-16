<?php
$map = [];
$dist = [];
$visited = [];
$pq = new Pq();
$INF = 100000000000;

class Pq extends SplPriorityQueue {
    public function compare($a, $b): int {
        if ($a === $b) return 0;
        return $a > $b ? -1 : 1;
    }
}

function doMoves(int $i, int $j, array $prices): void {
    global $map, $dist, $pq;

    if ($i+1<count($map) && $map[$i+1][$j] != "#" && $dist[$i+1][$j] > $dist[$i][$j] + $prices[0]) {
        $dist[$i+1][$j] = $dist[$i][$j] + $prices[0];
        $pq->insert([$i+1, $j, 0], $dist[$i+1][$j]);
    }
    if ($j+1<count($map[0]) && $map[$i][$j+1] != "#" && $dist[$i][$j+1] > $dist[$i][$j] + $prices[1]) {
        $dist[$i][$j+1] = $dist[$i][$j] + $prices[1];
        $pq->insert([$i, $j+1, 1], $dist[$i][$j+1]);
    }
    if ($i-1 >= 0 && $map[$i-1][$j] != "#" && $dist[$i-1][$j] > $dist[$i][$j] + $prices[2]) {
        $dist[$i-1][$j] = $dist[$i][$j] + $prices[2];
        $pq->insert([$i-1, $j, 2], $dist[$i-1][$j]);
    }
    if ($j-1 >= 0 && $map[$i][$j-1] != "#" && $dist[$i][$j-1] > $dist[$i][$j] + $prices[3]) {
        $dist[$i][$j-1] = $dist[$i][$j] + $prices[3];
        $pq->insert([$i, $j-1, 3], $dist[$i][$j-1]);
    }
}

function solution(): int {
    global $map, $visited, $dist, $pq, $INF;

    $visited = array_fill(0, count($map), []);
    $dist = array_fill(0, count($map), []);
    for ($i = 0; $i < count($map); $i++) {
        $visited[$i] = array_fill(0, count($map[$i]), false);
        $dist[$i] = array_fill(0, count($map[$i]), $INF);
    }

    for ($i = 0; $i < count($map); $i++) {
        for ($j = 0; $j < count($map[$i]); $j++) {
            if ($map[$i][$j] == "S") {
                $dist[$i][$j] = 0;
                $pq->insert([$i, $j, 1], 0);
            }
        }
    }

    while (!$pq->isEmpty()) {
        $top = $pq->extract();
        $i = $top[0];
        $j = $top[1];
        $side = $top[2];
        
        if ($map[$i][$j] == "E") {
            return $dist[$i][$j];
        }

        if ($side == 0) {
            doMoves($i, $j, [1, 1001, 2001, 1001]);
        } else if ($side == 1) {
            doMoves($i, $j, [1001, 1, 1001, 2001]);
        } else if ($side == 2) {
            doMoves($i, $j, [2001, 1001, 1, 1001]);
        } else if ($side == 3) {
            doMoves($i, $j, [1001, 2001, 1001, 1]);
        }
    }

    return -1;
}

$fp = fopen("input.txt", "r") or die();
$map = explode("\n",fread($fp, filesize("input.txt")));
fclose($fp);

for ($i = 0; $i < count($map); $i++) {
    $map[$i] = str_split($map[$i]);
}

echo solution()."\n";
?>
