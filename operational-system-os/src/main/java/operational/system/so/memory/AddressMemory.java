package operational.system.so.memory;

public class AddressMemory {
    private int start;
    private int end;

    public AddressMemory(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int addressMemorySize(AddressMemory addressMemory) {
        return (addressMemory.getEnd() - addressMemory.getStart()) + 1;
    }

    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
}
