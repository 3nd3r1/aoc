#include <fstream>
#include <iostream>
#include <vector>

using namespace std;

vector<string> garden;
vector<vector<bool>> visited;

pair<int, int> dfs(int i, int j) {
  if (visited[i][j]) {
    return make_pair(0, 0);
  }
  visited[i][j] = true;

  int perimeter = 4;
  int area = 1;

  if ((i - 1 >= 0 && garden[i - 1][j] == garden[i][j]) ||
      ((j - 1 >= 0 && garden[i][j - 1] == garden[i][j]) &&
       (i - 1 < 0 || garden[i - 1][j - 1] != garden[i][j]))) {
    perimeter--;
  }
  if ((i + 1 < garden.size() && garden[i + 1][j] == garden[i][j]) ||
      ((j - 1 >= 0 && garden[i][j - 1] == garden[i][j]) &&
       (i + 1 >= garden.size() || garden[i + 1][j - 1] != garden[i][j]))) {
    perimeter--;
  }
  if ((j - 1 >= 0 && garden[i][j - 1] == garden[i][j]) ||
      ((i - 1 >= 0 && garden[i - 1][j] == garden[i][j]) &&
       (j - 1 < 0 || garden[i - 1][j - 1] != garden[i][j]))) {
    perimeter--;
  }
  if ((j + 1 < garden[0].size() && garden[i][j + 1] == garden[i][j]) ||
      ((i - 1 >= 0 && garden[i - 1][j] == garden[i][j]) &&
       (j + 1 >= garden[0].size() || garden[i - 1][j + 1] != garden[i][j]))) {
    perimeter--;
  }

  if (i - 1 >= 0 && garden[i - 1][j] == garden[i][j]) {
    auto p = dfs(i - 1, j);
    perimeter += p.first;
    area += p.second;
  }
  if (i + 1 < garden.size() && garden[i + 1][j] == garden[i][j]) {
    auto p = dfs(i + 1, j);
    perimeter += p.first;
    area += p.second;
  }
  if (j - 1 >= 0 && garden[i][j - 1] == garden[i][j]) {
    auto p = dfs(i, j - 1);
    perimeter += p.first;
    area += p.second;
  }
  if (j + 1 < garden[0].size() && garden[i][j + 1] == garden[i][j]) {
    auto p = dfs(i, j + 1);
    perimeter += p.first;
    area += p.second;
  }

  return make_pair(perimeter, area);
}

int solution() {
  visited = vector<vector<bool>>(garden.size(),
                                 vector<bool>(garden[0].size(), false));
  int ans = 0;

  for (int i = 0; i < garden.size(); i++) {
    for (int j = 0; j < garden[0].size(); j++) {
      auto p = dfs(i, j);
      ans += p.first * p.second;
    }
  }

  return ans;
}

int main() {
  ifstream fp("input.txt");

  string line;
  while (getline(fp, line)) {
    garden.push_back(line);
  }
  fp.close();

  cout << solution() << endl;
  return 0;
}
