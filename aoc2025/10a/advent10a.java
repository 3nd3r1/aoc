import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class advent10a {
    record Machine(int target, List<Integer> buttons) {
    }

    private static List<Machine> machines = new ArrayList<>();

    private static long solution() {
        long ans = 0L;

        for (int i = 0; i < machines.size(); i++) {
            Queue<Integer> q = new LinkedList<>();
            Map<Integer, Integer> dist = new HashMap<>();

            dist.put(0, 0);
            q.add(0);

            while (!q.isEmpty()) {
                int state = q.poll();

                if (state == machines.get(i).target)
                    break;

                for (int button : machines.get(i).buttons) {
                    if (!dist.containsKey(state ^ button) || dist.get(state ^ button) > dist.get(state) + 1) {
                        q.add(state ^ button);
                        dist.put(state ^ button, dist.get(state) + 1);
                    }
                }
            }

            ans += dist.get(machines.get(i).target);
        }

        return ans;
    }

    private static int parseTarget(String targetString) {
        int target = 0;
        for (int i = 1; i < targetString.length() - 1; i++) {
            if (targetString.charAt(i) == '#') {
                target |= (1 << i - 1);
            }
        }

        return target;
    }

    private static int parseButton(String buttonString) {
        int button = 0;

        for (int i = 1; i < buttonString.length() - 1; i++) {
            if (buttonString.charAt(i) == ',')
                continue;

            int x = buttonString.charAt(i) - '0';
            button |= (1 << x);
        }

        return button;
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            while (fpr.hasNext()) {
                String[] parts = fpr.nextLine().split(" ");

                int target = parseTarget(parts[0]);
                List<Integer> buttons = new ArrayList<>();

                for (int i = 1; i < parts.length - 1; i++) {
                    buttons.add(parseButton(parts[i]));
                }

                machines.add(new Machine(target, buttons));
            }
            fpr.close();

            System.out.println(solution());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
