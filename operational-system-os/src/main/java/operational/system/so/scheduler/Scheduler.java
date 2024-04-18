package operational.system.so.scheduler;

import operational.system.so.Process;
import operational.system.so.SubProcess;
import operational.system.so.cpu.CpuManager;

public abstract class Scheduler {
    public CpuManager cpuM;

    public Scheduler() {
        cpuM = new CpuManager(this);
    }

    public  abstract void addSubProcessAndSubProcesses(Process process);

    public abstract SubProcess execute();

    public abstract void delete(Process process);
}
