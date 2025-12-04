import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class advent4a {
    private static ArrayList<boolean[]> grid = new ArrayList<boolean[]>();

    private static int[][] moves = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
            { 1, 1 } };

    private static long solution() {
        long ans = 0L;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).length; j++) {
                if (!grid.get(i)[j])
                    continue;

                int papers = 0;
                for (int[] move : moves) {
                    int newi = i + move[0];
                    int newj = j + move[1];

                    if (newi < 0 || newi >= grid.size() || newj < 0 || newj >= grid.get(i).length)
                        continue;

                    if (!grid.get(newi)[newj])
                        continue;

                    papers++;
                }
                if (papers < 4) {
                    ans++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            while (fpr.hasNextLine()) {
                String rowString = fpr.nextLine();
                grid.add(new boolean[rowString.length()]);
                for (int i = 0; i < rowString.length(); i++) {
                    if (rowString.charAt(i) == '@') {
                        grid.get(grid.size() - 1)[i] = true;
                    }
                }
            }

            fpr.close();

            System.out.println(solution());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
