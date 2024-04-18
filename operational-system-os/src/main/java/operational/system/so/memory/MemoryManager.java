package operational.system.so.memory;

import operational.system.so.Process;
import operational.system.so.SubProcess;
import operational.system.so.utils.Util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class MemoryManager {
    private int pageSize;
    private int sizeMemory;
    private SubProcess[][] physicalMemory;
    private Hashtable <String, FrameMemory> logicalMemory;

    public static int NUMBER_OF_INSTRUCTIONS_PER_SUBPROCESS = 7;

    public MemoryManager(int sizeMemory, int pageSize) {
        this.pageSize = pageSize;
        this.sizeMemory = sizeMemory;
        int frames = (int) Math.ceil(sizeMemory/pageSize);
        this.physicalMemory = new SubProcess[frames][pageSize];
        logicalMemory = new Hashtable<>();
    }

    public void write (Process process) {
        this.allocateProcessWithPaging(process);
    }
    private List<Integer> findEmptyPages() {
        List<Integer> framesIndex = new ArrayList<>();
        for (int frame = 0; frame < this.physicalMemory.length; frame++) {
            SubProcess[] element = this.physicalMemory[frame];
            if (element[0] == null) {
                framesIndex.add(frame);
            }
        }
        return framesIndex;
    }

    private void allocateProcessWithPaging(Process process) {
        List<Integer> emptyFrames = findEmptyPages();
        int countSize = 0;
        for (Integer indexFrame : emptyFrames) {
            SubProcess[] page = this.physicalMemory[indexFrame];
            int indexPage = 0;
            while (indexPage < page.length && countSize < process.getSizeInMemory()) {
                String subProcessId = process.getProcesses().get(countSize);
                this.physicalMemory[indexFrame][indexPage] = new SubProcess(subProcessId, process);
                this.logicalMemory.put(subProcessId, new FrameMemory(indexFrame, indexPage));
                countSize++;
                indexPage++;
            }
        }
        Util.printMemoryStatus(this.physicalMemory, this.pageSize);
    }

    public List<SubProcess> read(Process process) {
        List<String> ids = process.getProcesses();
        List<SubProcess> subProcesses = new LinkedList<>();
        for (String id : ids) {
            FrameMemory frame = this.logicalMemory.get(id);
            if (frame != null) {
                subProcesses.add(this.physicalMemory[frame.getFrameNumber()][frame.getOffset()]);
            }
        }
        return subProcesses;
    }

    public boolean checkWrite(Process process) {
        List<Integer> emptyframes = findEmptyPages();
        return emptyframes.size() >= (double) process.getSizeInMemory() / this.pageSize;
    }
}
