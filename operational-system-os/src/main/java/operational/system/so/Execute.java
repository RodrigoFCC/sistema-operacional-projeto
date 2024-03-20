package operational.system.so;

public class Execute {
    public static void main(String[] args) {
        Process p1 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, null);
        //        p1.setSizeInMemory(3);
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p1);
        System.out.println("P1: " + p1.getSizeInMemory());

        Process p2 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, null);
        //        p2.setSizeInMemory(3);
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p2);
        System.out.println("P2: " + p2.getSizeInMemory());

        Process p3 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, null);
        //        p3.setSizeInMemory(3);
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p3);
        System.out.println("P3: " + p3.getSizeInMemory());

        Process p4 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, null);
        //        p4.setSizeInMemory(3);
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p4);
        System.out.println("P4: " + p4.getSizeInMemory());

        SystemOperation.systemCall(SystemCallTypeEnum.DELETE_PROCESS, p1);
        SystemOperation.systemCall(SystemCallTypeEnum.DELETE_PROCESS, p3);

        Process p5 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, null);
        //        p5.setSizeInMemory(3);
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p5);
        System.out.println("P5: " + p5.getSizeInMemory());

//        Process p6 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, null);
//        //        p6.setSizeInMemory(3);
//        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p6);
//        System.out.println("P6: " + p6.getSizeInMemory());
//
//        Process p7 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, null);
//        //        p7.setSizeInMemory(3);
//        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p7);
//        System.out.println("P7: " + p7.getSizeInMemory());
//
//        Process p8 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, null);
//        //        p8.setSizeInMemory(3);
//        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p8);
//        System.out.println("P8: " + p8.getSizeInMemory());
//
//        Process p9 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, null);
//        //        p9.setSizeInMemory(3);
//        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p9);
//        System.out.println("P9: " + p9.getSizeInMemory());
//
//        Process p10 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, null);
//        //        p10.setSizeInMemory(3);
//        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p10);
//        System.out.println("P10: " + p10.getSizeInMemory());
    }
}
