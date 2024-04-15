package operational.system.so.memory;

import operational.system.so.Process;
import operational.system.so.SubProcess;
import operational.system.so.utils.Util;

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
        this.writeUsingPaging(process);
    }

    private void writeUsingPaging(Process process) {
        List<FrameMemory> frames = getFrames(process);
        if(frames != null) {
            int subProcessSize = 0;
            for(int i = 0; i < frames.size(); i++) {
                for(int offset = 0; offset < this.pageSize; offset++) {
                    FrameMemory frame = frames.get(i);
                    //MEMÓRIA FÍSICA
                    SubProcess sp = new SubProcess(process.getId(), NUMBER_OF_INSTRUCTIONS_PER_SUBPROCESS);
                    this.physicalMemory[frame.getFrameNumber()][offset] = sp;
                    //MEMÓRIA LÓGICA
                    frame.setOffset(offset);
                    this.logicalMemory.put(sp.getId(), frame);
                    subProcessSize++;
                    if((subProcessSize) == process.getSizeInMemory()) {
                        subProcessSize = 0;
                        frames.clear();
                        break;
                    }
                }
            }
            Util.printMemoryStatus(this.physicalMemory, this.pageSize);
        } else {
            //TODO - TROCA DE PÁGINA
        }
    }

    private List<FrameMemory> getFrames(Process process) {
        List<FrameMemory> frames = new LinkedList<>();
        int numberOfFramerPerProcess = (int) (Math.ceil(process.getSizeInMemory()/this.pageSize)+ 1);
        for(int frame = 0; frame < this.physicalMemory.length; frame++) {
            if(this.physicalMemory[frame][0] == null) {
                frames.add(new FrameMemory(frame));
                if(frames.size() == numberOfFramerPerProcess) {
                    return frames;
                }
            }
        }
        return null;
    }

    public List<SubProcess> read(Process process) {
        List<String> ids = process.getProcesses();
        List<SubProcess> subProcesses = new LinkedList<>();
        for(String id : ids) {
            FrameMemory frame = this.logicalMemory.get(id);
            subProcesses.add(this.physicalMemory[frame.getFrameNumber()][frame.getOffset()]);
        }
        return subProcesses;
    }

    public void delete(Process process) {

    }
}
