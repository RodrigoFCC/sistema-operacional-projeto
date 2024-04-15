package operational.system.so.scheduler;

import operational.system.so.Process;

import java.util.Comparator;

public class Priority extends SchedulerQueue {
    public Priority() {
        super(new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getPriorityProcessType().getLevel() > p2.getPriorityProcessType().getLevel() ? 1 : -1;
            }
        });
    }
}
