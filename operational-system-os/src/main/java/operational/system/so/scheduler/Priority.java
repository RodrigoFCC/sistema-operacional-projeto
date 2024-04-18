package operational.system.so.scheduler;


import operational.system.so.Process;
import operational.system.so.SubProcess;
import operational.system.so.SystemCallTypeEnum;
import operational.system.so.SystemOperation;

import java.util.Comparator;
import java.util.List;

public class Priority extends SchedulerQueue {

    public Priority() {
        super(new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p2.getPriorityProcessType() - p1.getPriorityProcessType();
            }
        });
    }

    @Override
    public void addSubProcessAndSubProcesses(Process process) {
        if (this.queue != null) {
            this.queue.add(process);
            if (this.subProcesses != null) {
                List<SubProcess> subProcesses = SystemOperation.systemCall(SystemCallTypeEnum.READ_PROCESS, process);
                this.subProcesses.addAll(subProcesses);
            }
        }
    }

    @Override
    public SubProcess execute() {

        if (this.queue != null) {
            this.subProcesses.sort((sp1, sp2) -> sp2.getProcess().getPriorityProcessType() - sp1.getProcess().getPriorityProcessType());
            this.queue.poll();
            return this.subProcesses.poll();
        }
        return null;
    }

    @Override
    public void delete(Process process) {

    }
}
