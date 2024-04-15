package operational.system.so.schedule;

import operational.system.so.SoProcess;
import operational.system.so.cpu.CpuManager;

public abstract class Scheduler {
    public CpuManager cm;

    public abstract void execute (SoProcess soProcess);
    public abstract void finish (SoProcess soProcess);

    public Scheduler() {
        cm = new CpuManager();
    }

    public CpuManager getCm() {
        return cm;
    }
}
