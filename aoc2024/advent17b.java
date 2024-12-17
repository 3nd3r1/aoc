import java.io.File;
import java.util.Scanner;

public class advent17b {
    private static String programString;
    private static int[][] programs;
    private static long[] registers = {0, 0, 0};

    private static long combo(int operand) {
        switch (operand) {
            case 0:
                return operand;
            case 1:
                return operand;
            case 2:
                return operand;
            case 3:
                return operand;
            case 4:
                return registers[0];
            case 5:
                return registers[1];
            case 6:
                return registers[2];
        }
        return -1;
    }

    public static long solution() {
        long regA = 0;
        int targetlen = 1;
        while (targetlen <= programString.length()) {
            registers[0] = regA;
            registers[1] = 0;
            registers[2] = 0;

            String out = "";

            int ip = 0;
            while (ip < programs.length) {
                int[] program = programs[ip];
                switch(program[0]) {
                    case 0:
                        registers[0] = registers[0] / (long)Math.pow(2, combo(program[1]));
                        break;
                    case 1:
                        registers[1] = registers[1] ^ program[1];
                        break;
                    case 2:
                        registers[1] = combo(program[1])%8;
                        break;
                    case 3:
                        if (registers[0] != 0) {
                            ip = (program[1]/2)-1;
                        }
                        break;
                    case 4:
                        registers[1] = registers[1] ^ registers[2];
                        break;
                    case 5:
                        if (out.length() > 0) {
                            out += ",";
                        }
                        out += Long.toString(combo(program[1])%8);
                        break;
                    case 6:
                        registers[1] = registers[0] / (long)Math.pow(2, combo(program[1]));
                        break;
                    case 7:
                        registers[2] = registers[0] / (long)Math.pow(2, combo(program[1]));
                        break;
                }
                ip++;
            }

            if (out.equals(programString)) {
                return regA;
            }

            if (out.equals(programString.substring(programString.length()-targetlen))) {
                regA *= 8; 
                targetlen+=2;
            } else {
                regA++;
            }
        }
        return regA;
    }

    public static void main(String[] args) {
        try {
            File fp = new File("input.txt");
            Scanner fpr = new Scanner(fp);

            registers[0] = Long.parseLong(fpr.nextLine().split(": ")[1]);
            registers[1] = Long.parseLong(fpr.nextLine().split(": ")[1]);
            registers[2] = Long.parseLong(fpr.nextLine().split(": ")[1]);
            fpr.nextLine();

            String[] programStrings = fpr.nextLine().split(": ")[1].split(",");
            fpr.close();
            programString = String.join(",",programStrings);
            programs = new int[programStrings.length/2][2];

            for (int i = 1; i < programStrings.length; i++) {
                programs[i/2][0] = Integer.parseInt(programStrings[i-1]);
                programs[i/2][1] = Integer.parseInt(programStrings[i]);
            }

            System.out.println(solution());
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
