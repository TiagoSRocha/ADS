public class Lock extends Device{
    protected boolean isLocked; //nao preciso desta merda acho pq tenho isON no Device.
    
    public Lock(boolean isLocked){
        super("LOCK");
        this.isLocked = isLocked;
    }
}
