package operational.system.so.utils;

import operational.system.so.SoProcess;


public class Util {

    public static void paintMemory (String[] memory, SoProcess soProcess, int start, int end) {
        for (int j = start; j < end; j++) {
            memory[j] = soProcess.getId();
        }
    }
    public static int nullValidation (String[] memory, int start, int end) {
        int contNull = 0;
        for (int j = start; j < end; j++) {
            if (memory[j] != null) {
                contNull++;
            }
        }
        return contNull;
    }

    public static void printMemoryStatus (String[] memory) {
        for (int i = 0; i < memory.length; i++) {
            System.out.print(memory[i] + " | ");
        }
        System.out.println("FINISH");
    }
}
