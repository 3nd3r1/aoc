import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class advent6b {
    private static final String OPS = "*+";
    private static List<List<Long>> groups = new ArrayList<>();
    private static List<Integer> operators = new ArrayList<>();

    private static void createGroupsAndOperators(List<String> lines) {
        List<List<Integer>> groupIndexes = new ArrayList<>();

        String operatorLine = lines.get(lines.size() - 1);
        for (int i = 0; i < operatorLine.length(); i++) {
            operators.add(OPS.indexOf(operatorLine.charAt(i)));

            int j = i+1;
            while (j < operatorLine.length() && operatorLine.charAt(j) == ' ') {
                j++;
            }

            if (j < operatorLine.length())
                j--;

            groupIndexes.add(Arrays.asList(i, j));

            i = j;
        }

        int groupCount = groupIndexes.size();

        for (int i = 0; i < groupCount; i++) {
            groups.add(new ArrayList<>());
            int groupIndexMin = groupIndexes.get(i).get(0);
            int groupIndexMax = groupIndexes.get(i).get(1);

            for (int j = groupIndexMax - 1; j >= groupIndexMin; j--) {
                long num = 0;
                int exp = 0;
                for (int k = lines.size() - 2; k >= 0; k--) {
                    char digitChar = lines.get(k).charAt(j);
                    if (digitChar == ' ')
                        continue;
                    num += (digitChar - '0') * (long) Math.pow(10, exp);
                    exp++;
                }
                groups.get(i).add(num);
            }
        }
    }

    private static long solution() {
        long ans = 0L;

        for (int i = 0; i < groups.size(); i++) {
            long val = 0;
            if (operators.get(i) == 0)
                val = 1;

            for (long x : groups.get(i)) {
                if (OPS.charAt(operators.get(i)) == '*') {
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

            List<String> lines = new ArrayList<>();
            while (fpr.hasNext()) {
                lines.add(fpr.nextLine());
            }
            fpr.close();

            createGroupsAndOperators(lines);

            System.out.println(solution());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
