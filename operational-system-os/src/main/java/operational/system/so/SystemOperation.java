package operational.system.so;

import operational.system.so.cpu.CpuManager;
import operational.system.so.memory.MemoryManager;
import operational.system.so.scheduler.FCFS;
import operational.system.so.scheduler.Scheduler;

import java.util.List;
import java.util.Objects;

public class SystemOperation {
    public static MemoryManager mm;
    public static Scheduler scheduler;

    public static Process systemCall(SystemCallTypeEnum type, int sizeInMemory) {
        if(type.equals(SystemCallTypeEnum.CREATE_PROCESS)) {
            if(Objects.isNull(mm)) {
                mm = new MemoryManager(256, 4);
            }
            if(Objects.isNull(scheduler)) {
                scheduler = new FCFS();
            }
        }
        return new Process(sizeInMemory);
    }

    public static List<SubProcess> systemCall(SystemCallTypeEnum type, Process process) {
        if(type.equals(SystemCallTypeEnum.WRITE_PROCESS)) {
            mm.write(process);
//            scheduler.execute(process);
        } else if(type.equals(SystemCallTypeEnum.READ_PROCESS)) {
//            return mm.read(process);
        } else if(type.equals(SystemCallTypeEnum.CLOSE_PROCESS)) {
             mm.delete(process);
//            scheduler.delete(process);
        }
        return null;
    }
}
