#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct {
  int i;
  int j;
} coord;

typedef struct {
  coord c;
  int dist;
  int cheat;
} item;

int n;
int m;
int cheatMin = 100;
coord start = {0, 0};
coord end = {0, 0};
bool wall[10000][10000];
coord moves[] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
bool cheatVisited[1000][1000];

int distStart[1000][1000];
int distEnd[1000][1000];

void buildDistStart() {
  coord q[10000];
  int front = 0;
  int rear = 0;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      distStart[i][j] = 1000000;
      distStart[i][j] = 1000000;
      distStart[i][j] = 1000000;
    }
  }

  q[rear] = start;
  distStart[start.i][start.j] = 0;
  rear++;

  while (front != rear) {
    coord f = q[front];
    front++;

    for (int i = 0; i < 4; i++) {
      int ni = f.i + moves[i].i;
      int nj = f.j + moves[i].j;

      if (ni >= 0 && ni < n && nj >= 0 && nj < m && !wall[ni][nj] &&
          distStart[ni][nj] > distStart[f.i][f.j] + 1) {
        distStart[ni][nj] = distStart[f.i][f.j] + 1;
        q[rear] = (coord){ni, nj};
        rear++;
      }
    }
  }
}

void buildDistEnd() {
  coord q[10000];
  int front = 0;
  int rear = 0;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      distEnd[i][j] = 1000000;
      distEnd[i][j] = 1000000;
      distEnd[i][j] = 1000000;
    }
  }

  q[rear] = end;
  distEnd[end.i][end.j] = 0;
  rear++;

  while (front != rear) {
    coord f = q[front];
    front++;

    for (int i = 0; i < 4; i++) {
      int ni = f.i + moves[i].i;
      int nj = f.j + moves[i].j;

      if (ni >= 0 && ni < n && nj >= 0 && nj < m && !wall[ni][nj] &&
          distEnd[ni][nj] > distEnd[f.i][f.j] + 1) {
        distEnd[ni][nj] = distEnd[f.i][f.j] + 1;
        q[rear] = (coord){ni, nj};
        rear++;
      }
    }
  }
}

int dfs(coord c, int dist, bool cheat) {
  if (c.i < 0 || c.j < 0 || c.i >= n || c.j >= m) {
    return 0;
  }

  if (distStart[c.i][c.j] < dist) {
    return 0;
  }

  if (wall[c.i][c.j] && !cheat) {
    return 0;
  }

  if (distEnd[c.i][c.j] + dist + cheatMin <= distEnd[start.i][start.j]) {
    return 1;
  }

  if (cheat == 0) {
    return 0;
  }

  int ret = 0;
  
  for (int i = 0; i < 4; i++) {
    int ni = c.i + moves[i].i;
    int nj = c.j + moves[i].j;

    bool ncheat = cheat;
    if (wall[c.i][c.j]) {
      ncheat = false;
    }

    ret += dfs((coord){ni,nj}, dist+1, ncheat);
  }

  return ret;
}

int solution() {
  buildDistStart();
  buildDistEnd();
  return dfs(start, 0, true);
}

int main() {
  FILE *fp;
  char *line = NULL;
  size_t len = 0;
  ssize_t read;

  fp = fopen("input.txt", "r");

  if (fp == NULL) {
    exit(0);
  }

  int i = 0;
  while ((read = getline(&line, &len, fp)) != -1) {
    m = read-1;
    for (int j = 0; j < read; j++) {
      if (line[j] == '#') {
        wall[i][j] = true;
      } else if (line[j] == 'S') {
        start.i = i;
        start.j = j;
      } else if (line[j] == 'E') {
        end.i = i;
        end.j = j;
      }
    }
    i++;
  }
  n = i;

  fclose(fp);
  if (line)
    free(line);

  printf("%d\n", solution());

  return 0;
}
