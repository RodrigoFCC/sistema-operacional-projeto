package operational.system.so;

public class Execute {
    public static void main(String[] args) {
        Process p1 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, 130, 21, 1);
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p1);

        Process p2 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, 90, 10, 3);
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p2);

        Process p3 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, 34, 14, 3);
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p3);

        Process p4 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, 30, 7, 2);
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p4);

//        Process p5 = (Process) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, 9, 11, 1);
//        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p5);
    }
}
