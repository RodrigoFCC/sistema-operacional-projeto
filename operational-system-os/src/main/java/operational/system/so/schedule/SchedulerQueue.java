package operational.system.so.schedule;

import operational.system.so.SoProcess;
import operational.system.so.SubProcess;
import operational.system.so.SystemCallTypeEnum;
import operational.system.so.SystemOperation;
import operational.system.so.cpu.Core;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;

public class SchedulerQueue extends Scheduler {
    private PriorityQueue<SoProcess> queue;
    private Hashtable<String, List<SubProcess>> subProcesses;

    public SchedulerQueue(Comparator<SoProcess> comparator) {
        this.queue = new PriorityQueue<>(comparator);
    }

    public PriorityQueue<SoProcess> getQueue() {
        return queue;
    }

    @Override
    public void execute(SoProcess soProcess) {
        List<SubProcess> sps = SystemOperation.systemCall(SystemCallTypeEnum.READ_PROCESS, soProcess);
        this.queue.add(soProcess);
        this.subProcesses.put(soProcess.getId(), sps);
        this.registerSubProcesses();
    }

    @Override
    public void finish(SoProcess soProcess) {

    }

    private void registerSubProcesses() {
        //colocar um while aqui
        SoProcess soProcess = this.queue.peek();
        List<SubProcess> sps = this.subProcesses.get(soProcess.getId());
        Core[] cores = this.getCm().getCores();
        for(Core core: cores) {
            if (core.getCurrentSubProcess() != null) {
                SubProcess sp = sps.get(0);
                this.getCm().registerProcess(core.getId(), sp);
            }
        }
    }
}
