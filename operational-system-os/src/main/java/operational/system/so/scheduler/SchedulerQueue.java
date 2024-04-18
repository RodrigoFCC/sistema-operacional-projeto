package operational.system.so.scheduler;

import operational.system.so.Process;
import operational.system.so.SubProcess;

import java.util.*;

public abstract class SchedulerQueue extends Scheduler{
    protected PriorityQueue<Process> queue;
    protected LinkedList<SubProcess> subProcesses;

    public SchedulerQueue(Comparator<Process> comparator) {
        super();
        this.queue = new PriorityQueue<>(comparator);
        this.subProcesses = new LinkedList<>();
    }
}
