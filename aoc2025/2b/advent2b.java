import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class advent2b {
    record Range(long left, long right) {
    }

    static private ArrayList<Range> ranges = new ArrayList<>();

    public static long solution() {
        long ans = 0L;
        for (Range range : ranges) {
            System.out.println(range);

            String leftString = String.valueOf(range.left);
            String rightString = String.valueOf(range.right);
            
            Set<Long> used = new HashSet<>();

            for (int size = leftString.length(); size <= rightString.length(); size++) {
                for (int split = 2; split <= size; split++) {
                    if (size % split != 0) continue;

                    long repeatable = (long)Math.pow(10, (size/split)-1);
                    while (repeatable < Math.pow(10, size/split)) {
                        long id = Long.parseLong(String.valueOf(repeatable).repeat(split));

                        repeatable++;
                        if (id < range.left) continue;
                        if (id > range.right) break;
                        if (used.contains(id)) continue;
                        used.add(id);

                        System.out.println(id);
                        ans += id;
                    }
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
