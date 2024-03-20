package operational.system.so;

import operational.system.so.cpu.CpuManager;
import operational.system.so.memory.MemoryManager;
import operational.system.so.memory.StrategyTypeEnum;
import operational.system.so.schedule.Schedule;

import java.util.Objects;

public class SystemOperation {
    public static MemoryManager mm;
    public static CpuManager cm;
    public static Schedule schedule;

    public static Object systemCall(SystemCallTypeEnum type, Process process) {
        if(type.equals(SystemCallTypeEnum.CREATE_PROCESS)) {
            if(Objects.isNull(mm)) {
                mm = new MemoryManager(StrategyTypeEnum.FIRST_FIT);
            }
            if(Objects.isNull(cm)) {
                cm = new CpuManager();
            }
            return new Process();
        } else if(type.equals(SystemCallTypeEnum.WRITE_PROCESS)) {
            mm.write(process);
        } else if(type.equals(SystemCallTypeEnum.READ_PROCESS)) {
            return new Process();
        } else if(type.equals(SystemCallTypeEnum.DELETE_PROCESS)) {
            mm.deleteProcessInMemory(process);
        }
        return null;
    }
}
