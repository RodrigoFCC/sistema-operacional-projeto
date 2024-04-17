package operational.system.so.scheduler;

import operational.system.so.Process;
import operational.system.so.SubProcess;
import operational.system.so.SystemCallTypeEnum;
import operational.system.so.SystemOperation;
import operational.system.so.cpu.Core;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;

public class SchedulerQueue extends Scheduler{
    private PriorityQueue<Process> queue;
    private Hashtable<String, List<SubProcess>> subProcesses = new Hashtable<>();

    public SchedulerQueue(Comparator<Process> comparator) {
        this.queue = new PriorityQueue<>(comparator);
    }

    public SchedulerQueue() {
        this.subProcesses = new Hashtable<>();
    }

    public PriorityQueue<Process> getQueue() {
        return queue;
    }

    @Override
    public void execute(Process process) {
        List<SubProcess> subPProcesses = SystemOperation.systemCall(SystemCallTypeEnum.READ_PROCESS, process);

        this.queue.add(process);
        this.subProcesses.put(process.getId(), subPProcesses);
        this.registerSubProcesses();
    }

    private void registerSubProcesses() {
        int cc = 0;
        while (cc < 7) {
            Process process = this.queue.peek();
            List<SubProcess> sps = this.subProcesses.get(process.getId());
            Core[] cores = this.getCpuM().getCores();
            for(Core core : cores) {
                if(core.getCurrentSubProcess() == null) {
                    SubProcess sp = sps.get(cc);
                    this.getCpuM().registerProcess(core.getId(), sp);
                    System.out.println("Processo " + sp.getId() + " entrou no core " + core.getId());
                    cc++;
                } else {
                    System.out.println("O core " + core.getId() + " está ocupado!");
                    cc++;
                }
            }
        }
    }

    @Override
    public void delete(Process process) {

    }
}
