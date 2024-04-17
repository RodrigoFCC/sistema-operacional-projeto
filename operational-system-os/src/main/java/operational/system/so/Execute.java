package operational.system.so;

public class Execute {
    public static void main(String[] args) {
        Process p1 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, 7);
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p1);
        System.out.println("P1: " + p1.getSizeInMemory() + "***********************************");
//
//        Process p2 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, 5);
//        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p2);
//        System.out.println("P2: " + p2.getSizeInMemory() + "***********************************");
//
//        Process p3 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, 4);
//        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p3);
//        System.out.println("P3: " + p3.getSizeInMemory() + "***********************************");
    }
}
