import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class advent2a {
    record Range(long left, long right) {
    }

    static private ArrayList<Range> ranges = new ArrayList<>();

    public static long solution() {
        long ans = 0L;
        for (Range range : ranges) {
            System.out.println(range);

            String leftString = String.valueOf(range.left);
            String rightString = String.valueOf(range.right);

            for (int size = leftString.length(); size <= rightString.length(); size++) {
                if (size % 2 != 0) continue;

                long repeatable = (long)Math.pow(10, (size/2)-1);
                while (repeatable < Math.pow(10, size/2)) {
                    long id = repeatable + repeatable * (long)Math.pow(10, size/2);

                    repeatable++;
                    if (id < range.left) continue;
                    if (id > range.right) break;

                    System.out.println(id);
                    ans += id;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            for (String rangeString : fpr.nextLine().split(",")) {
                long left = Long.parseLong(rangeString.split("-")[0]);
                long right = Long.parseLong(rangeString.split("-")[1]);
                ranges.add(new Range(left, right));
            }

            fpr.close();

            System.out.println(solution());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
