package operational.system.so;

public class Execute {
    public static void main(String[] args) {
        SoProcess p1 = (SoProcess) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS,20);
        System.out.println("P1: " + p1.getSizeInMemory());
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p1);

        System.out.println("**********************************************************");

        SoProcess p2 = (SoProcess) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, 38);
        System.out.println("P2: " + p2.getSizeInMemory());
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p2);

        System.out.println("**********************************************************");


        SoProcess p3 = (SoProcess) SystemOperation.systemCall(SystemCallTypeEnum.CREATE_PROCESS, 38);
        System.out.println("P3: " + p3.getSizeInMemory());
        SystemOperation.systemCall(SystemCallTypeEnum.WRITE_PROCESS, p3);
        System.out.println("**********************************************************");

    }
}
