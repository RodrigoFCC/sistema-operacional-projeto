package operational.system.so.utils;

import operational.system.so.SubProcess;


public class Util {

    public static void printMemoryStatus (SubProcess[][] physicalMemory, int pageSize) {
        for (int i = 0; i < physicalMemory.length; i++) {
            for(int j = 0; j < pageSize; j++) {
                if (physicalMemory[i][j] != null) {
                    System.out.print(physicalMemory[i][j].getId() + " | ");
                } else {
                    System.out.print(physicalMemory[i][j] + " | ");
                }
            }
            System.out.println("");
        }
    }
}
