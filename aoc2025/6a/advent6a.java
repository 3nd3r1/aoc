import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class advent6a {
    private static List<List<Integer>> groups = new ArrayList<>();
    private static List<Integer> operators = new ArrayList<>();

    private static long solution() {
        long ans = 0L;

        for (int i = 0; i < groups.size(); i++) {
            long val = 0;
            if (operators.get(i) == 0)
                val = 1;

            for (int x : groups.get(i)) {
                if (operators.get(i) == 0) {
                    val *= x;
                } else {
                    val += x;
                }
            }
            ans += val;
        }

        return ans;
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            while (fpr.hasNext()) {
                String line = fpr.nextLine();
                if (!fpr.hasNext()) {
                    for (String operator : line.split(" ")) {
                        if (operator.isEmpty())
                            continue;
                        operators.add("*+".indexOf(operator));
                    }
                    break;
                }

                List<Integer> nums = Arrays.stream(line.split(" ")).filter(x -> !x.isEmpty()).map(Integer::parseInt)
                        .toList();

                if (groups.size() == 0) {
                    for (int i = 0; i < nums.size(); i++) {
                        groups.add(new ArrayList<>());
                    }
                }

                for (int i = 0; i < nums.size(); i++) {
                    groups.get(i).add(nums.get(i));
                }
            }

            fpr.close();

            System.out.println(solution());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
