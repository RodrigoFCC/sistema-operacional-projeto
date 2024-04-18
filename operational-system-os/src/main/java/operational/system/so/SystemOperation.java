package operational.system.so;

import operational.system.so.memory.MemoryManager;
import operational.system.so.scheduler.*;

import java.util.List;
import java.util.Objects;

public class SystemOperation {
    public static MemoryManager mm;
    public static Scheduler scheduler;

    public static Process systemCall(SystemCallTypeEnum type, int sizeInMemory, int timeToExecute, int priority) {
        if(type.equals(SystemCallTypeEnum.CREATE_PROCESS)) {
            if(Objects.isNull(mm)) {
                mm = new MemoryManager(256, 4);
            }
            if(Objects.isNull(scheduler)) {
                scheduler = new FCFS();
            }
        }
        return new Process(sizeInMemory, timeToExecute, priority);
    }

    public static List<SubProcess> systemCall(SystemCallTypeEnum type, Process process) {
        if(type.equals(SystemCallTypeEnum.WRITE_PROCESS)) {
            boolean checkWriter = mm.checkWrite(process);
            if(checkWriter) {
                mm.write(process);
                scheduler.addSubProcessAndSubProcesses(process);
            }else {
                System.out.println("Page fault");
            }
        } else if(type.equals(SystemCallTypeEnum.READ_PROCESS)) {
            return mm.read(process);
        } else if(type.equals(SystemCallTypeEnum.CLOSE_PROCESS)) {
            System.out.println("To do");
        }
        return null;
    }
}
