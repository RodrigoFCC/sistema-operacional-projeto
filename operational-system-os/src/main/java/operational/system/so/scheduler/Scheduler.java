package operational.system.so.scheduler;

import operational.system.so.Process;
import operational.system.so.cpu.CpuManager;

public abstract class Scheduler {
    public CpuManager cpuM;

    public Scheduler() {
        cpuM = new CpuManager();
    }
    public abstract void execute(Process process);

    public abstract void delete(Process process);

    public CpuManager getCpuM() {
        return cpuM;
    }
}
