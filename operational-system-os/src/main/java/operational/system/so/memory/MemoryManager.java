package operational.system.so.memory;

import operational.system.so.SoProcess;
import operational.system.so.utils.Util;
import operational.system.so.SubProcess;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class MemoryManager {
    private int sizeMemory;
    private int pageSize;
    private SubProcess[][] physicalMemory;
   private Hashtable<String, FrameMemory> logicalMemory;
   private static int NUM_OF_INSTRUCTIONS_PER_SUB_PROCESS = 7;


    public MemoryManager(int sizeMemory, int pageSize) {
        this.pageSize = pageSize;
        this.sizeMemory = sizeMemory;
        int pages = (int) Math.ceil(this.sizeMemory/ this.pageSize);
        this.physicalMemory = new SubProcess[pages][pageSize];
        this.logicalMemory = new Hashtable<>();

    }

    public void write (SoProcess soProcess) {
        this.writeUsingPaging(soProcess);
    }

    private void writeUsingPaging(SoProcess soProcess) {
        List<FrameMemory> frames = this.getFrames(soProcess);
        if (frames != null){
            for (int i = 0; i < frames.size(); i++) {
                for (int offset = 0; offset < this.pageSize; offset++) {
                    FrameMemory frame = frames.get(i);
                    //Atualiza a memoria física
                    SubProcess sp = new SubProcess(soProcess.getId(), NUM_OF_INSTRUCTIONS_PER_SUB_PROCESS);
                    this.physicalMemory[frame.getFrameNumber()][offset] = sp;
                    //Atualiza a memoria lógica
                    frame.setOffset(offset);
                    this.logicalMemory.put(sp.getId(), frame);
                }
            }
        } else {
            // Aqui a troca de pagina acontece
        }
        this.printMemoryStatus();
        SubProcess.processNumber = 0;
    }

    private List<FrameMemory> getFrames(SoProcess soProcess) {
        List<FrameMemory> frames = new LinkedList<>();
        int numOfPages = (int) Math.ceil(soProcess.getSizeInMemory()/this.pageSize);
        for (int frame = 0; frame < this.physicalMemory.length; frame++) {
            if (this.physicalMemory[frame][0] == null){
                frames.add(new FrameMemory(frame));
                if (frames.size() == numOfPages) {
                    return frames;
                }
            }
        }
        return null;
    }

    //algoritimo de substituição de paginas( pagepull)


    private void printMemoryStatus(){
        for (int i = 0; i < this.physicalMemory.length; i++) {
            for (int j = 0; j < this.physicalMemory[i].length; j++) {
                SubProcess sp = this.physicalMemory[i][j];
                String spId = null;
                if (sp != null){
                    spId = sp.getId();
                }
                if (j == this.physicalMemory[i].length - 1) {
                    System.out.println(spId);
                } else {
                    System.out.print(spId + " | ");
                }
            }
        }
    }
    public void deleteProcessInMemory(SoProcess soProcess) {
//        for(int i = 0; i < physicalMemory.length; i++) {
//            if(physicalMemory[i] == soProcess.getId()) {
//                physicalMemory[i] = null;
//            }
//        }
//        Util.printMemoryStatus(physicalMemory);
    }
}
