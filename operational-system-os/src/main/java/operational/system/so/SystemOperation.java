package operational.system.so;

import operational.system.so.cpu.CpuManager;
import operational.system.so.memory.MemoryManager;
import operational.system.so.schedule.Schedule;

import java.util.Objects;

public class SystemOperation {
    public static MemoryManager mm;
    public static CpuManager cm;
    public static Schedule schedule;

    public static SoProcess systemCall(SystemCallTypeEnum type, int sizeInMemory) {
        if(type.equals(SystemCallTypeEnum.CREATE_PROCESS)) {
            if (Objects.isNull(mm)) {
                mm = new MemoryManager(256, 4);
            }
            if (Objects.isNull(cm)) {
                cm = new CpuManager(4);
            }
        }
        return new SoProcess(sizeInMemory);
    }

    public static void systemCall(SystemCallTypeEnum type, SoProcess soProcess) {
        if(type.equals(SystemCallTypeEnum.WRITE_PROCESS)) {
            mm.write(soProcess);
        } else if(type.equals(SystemCallTypeEnum.READ_PROCESS)) {
//            new SoProcess();
        } else if(type.equals(SystemCallTypeEnum.DELETE_PROCESS)) {
            mm.deleteProcessInMemory(soProcess);
        }
    }
}
