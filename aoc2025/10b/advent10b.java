import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class advent10b {
    record Machine(List<List<Integer>> buttons, int[] joltage) {
    }

    private static List<Machine> machines = new ArrayList<>();
    private static Map<String, Long> mem = new HashMap<>();

    private static void press(int[] state, List<Integer> button, int k) {
        for (int i : button) {
            state[i] += k;
        }
    }

    private static List<List<List<Integer>>> findParitySolutions(List<List<Integer>> buttons, int[] parityTarget) {
        List<List<List<Integer>>> solutions = new ArrayList<>();

        for (int mask = 0; mask < (1 << buttons.size()); mask++) {
            int[] effect = new int[parityTarget.length];
            for (int i = 0; i < buttons.size(); i++) {
                if ((mask & (1 << i)) != 0) {
                    for (int j : buttons.get(i)) {
                        effect[j] ^= 1;
                    }
                }
            }

            boolean match = true;
            for (int j = 0; j < parityTarget.length; j++) {
                if (effect[j] != parityTarget[j]) {
                    match = false;
                    break;
                }
            }

            if (match) {
                List<List<Integer>> combo = new ArrayList<>();
                for (int i = 0; i < buttons.size(); i++) {
                    if ((mask & (1 << i)) != 0) {
                        combo.add(buttons.get(i));
                    }
                }
                solutions.add(combo);
            }
        }

        return solutions;
    }

    private static long dfs(List<List<Integer>> buttons, int[] joltage) {
        boolean good = true;
        for (int j : joltage) {
            if (j != 0) {
                good = false;
                break;
            }
        }
        if (good)
            return 0;

        for (int j : joltage) {
            if (j < 0)
                return Long.MAX_VALUE / 2;
        }

        String key = Arrays.toString(joltage);
        if (mem.containsKey(key))
            return mem.get(key);

        int[] parityTarget = new int[joltage.length];
        for (int i = 0; i < joltage.length; i++) {
            parityTarget[i] = joltage[i] % 2;
        }

        List<List<List<Integer>>> paritySolutions = findParitySolutions(buttons, parityTarget);
        if (paritySolutions.isEmpty()) {
            mem.put(key, Long.MAX_VALUE / 2);
            return Long.MAX_VALUE / 2;
        }

        long minCost = Long.MAX_VALUE / 2;

        for (List<List<Integer>> combo : paritySolutions) {
            int[] effect = new int[joltage.length];
            for (List<Integer> button : combo) {
                press(effect, button, 1);
            }

            int[] newJoltage = new int[joltage.length];
            boolean valid = true;
            for (int i = 0; i < joltage.length; i++) {
                int diff = joltage[i] - effect[i];
                if (diff < 0 || diff % 2 != 0) {
                    valid = false;
                    break;
                }
                newJoltage[i] = diff / 2;
            }

            if (valid) {
                long subCost = dfs(buttons, newJoltage);
                if (subCost < Long.MAX_VALUE / 2) {
                    long cost = combo.size() + 2 * subCost;
                    minCost = Math.min(minCost, cost);
                }
            }
        }

        mem.put(key, minCost);
        return minCost;
    }

    private static long solution() {
        long ans = 0L;

        for (int i = 0; i < machines.size(); i++) {
            mem.clear();
            ans += dfs(machines.get(i).buttons, machines.get(i).joltage);
        }

        return ans;
    }

    private static int[] parseJoltage(String joltageString) {
        String[] joltageSplitString = joltageString.substring(1, joltageString.length() - 1).split(",");
        int[] joltage = new int[joltageSplitString.length];
        for (int i = 0; i < joltageSplitString.length; i++) {
            joltage[i] = Integer.parseInt(joltageSplitString[i]);
        }

        return joltage;
    }

    private static List<Integer> parseButton(String buttonString) {
        String[] buttonSplitString = buttonString.substring(1, buttonString.length() - 1).split(",");
        List<Integer> button = new ArrayList<>();
        for (int i = 0; i < buttonSplitString.length; i++) {
            button.add(Integer.parseInt(buttonSplitString[i]));
        }

        return button;
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            while (fpr.hasNext()) {
                String[] parts = fpr.nextLine().split(" ");

                List<List<Integer>> buttons = new ArrayList<>();

                for (int i = 1; i < parts.length - 1; i++) {
                    buttons.add(parseButton(parts[i]));
                }

                int[] joltage = parseJoltage(parts[parts.length - 1]);

                machines.add(new Machine(buttons, joltage));
            }
            fpr.close();

            System.out.println(solution());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
