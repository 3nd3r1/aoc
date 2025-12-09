import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class advent9a {
    private static ArrayList<int[]> corners = new ArrayList<>();

    private static long solution() {
        long ans = 0L;
        for (int i = 0; i < corners.size(); i++) {
            for (int j = i + 1; j < corners.size(); j++) {
                long area = (long) (Math.abs(corners.get(i)[0] - corners.get(j)[0]) + 1)
                        * (long) (Math.abs(corners.get(i)[1] - corners.get(j)[1]) + 1);
                ans = Math.max(ans, area);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            while (fpr.hasNextLine()) {
                corners.add(Arrays.stream(fpr.nextLine().split(",")).mapToInt(Integer::parseInt).toArray());
            }

            fpr.close();

            System.out.println(solution());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
