package operational.system.so.memory;

public class FrameMemory implements Cloneable {
    private int frameNumber;
    private int offset;

    public FrameMemory(int frameNumber, int offset) {
        this.frameNumber = frameNumber;
        this.offset = offset;
    }

    @Override
    public FrameMemory clone() {
        try {
            return (FrameMemory) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public FrameMemory(int frameNumber) {
        this(frameNumber, 0);
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
