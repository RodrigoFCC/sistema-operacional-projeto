package operational.system.so;

import operational.system.so.memory.MemoryManager;
import operational.system.so.schedule.FCFS;
import operational.system.so.schedule.Scheduler;

import java.util.List;
import java.util.Objects;

public class SystemOperation {
    public static MemoryManager mm;
    public static Scheduler scheduler;

    public static SoProcess systemCall(SystemCallTypeEnum type, int sizeInMemory) {
        if(type.equals(SystemCallTypeEnum.CREATE_PROCESS)) {
            if (Objects.isNull(mm)) {
                mm = new MemoryManager(256, 4);
            }
            if (Objects.isNull(scheduler)) {
                scheduler = new FCFS();
            }
        }
        return new SoProcess(sizeInMemory);
    }

    public static List<SubProcess> systemCall(SystemCallTypeEnum type, SoProcess soProcess) {
        if(type.equals(SystemCallTypeEnum.WRITE_PROCESS)) {
            mm.write(soProcess);
            scheduler.execute(soProcess);
        } else if(type.equals(SystemCallTypeEnum.READ_PROCESS)) {
            return mm.read(soProcess);
        } else if(type.equals(SystemCallTypeEnum.CLOSE_PROCESS)) {
            mm.closeProcessInMemory(soProcess);
            scheduler.finish(soProcess);
        }
        return null;
    }
}
