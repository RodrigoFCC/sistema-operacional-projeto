package operational.system.so.scheduler;

import operational.system.so.Process;

import java.util.Comparator;

public class SJF extends SchedulerQueue {
    public SJF() {
        super(new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                return p1.getTimeToExecute() < p2.getTimeToExecute() ? 1 : -1;
            }
        });
    }
}
