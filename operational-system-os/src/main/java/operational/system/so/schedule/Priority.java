package operational.system.so.schedule;

import operational.system.so.SoProcess;

import java.util.Comparator;

public class Priority extends SchedulerQueue{
    public Priority() {
        super(new Comparator<SoProcess>() {
            @Override
            public int compare(SoProcess p1, SoProcess p2) {
                return p1.getPriorityType().getLevel() > p2.getPriorityType().getLevel() ? 1 : -1;
            }
        });
    }
}
