import java.util.ArrayList;

class Brain implements Runnable{


    private static Brain obj;
    protected TempSensor tempsensor;
    protected LockSensor locksensor;
    protected AcmeSwitch acmeswitch;
    protected DoeSwitch doeswitch;
    protected String protocols = "ENL, HEAT, LOCK, DEFAULT, DOE";
    ArrayList<LockSensor> lockSensors = new ArrayList<LockSensor>();
    protected boolean atHome;
     private Brain() {}


     public static synchronized Brain getBrain()
     {
         if (obj==null) {
             obj = new Brain();
            new Thread(new Brain()).start();
         }
         return obj;
     }
     public void run() {
     }
     public boolean getAtHome() { return this.atHome;}

     public void setAtHome(boolean value){this.atHome = value;}

    public void attach(LockSensor locksensor, TempSensor tempsensor, DoeSwitch doeSwitch, AcmeSwitch acmeswitch) throws IncompatibleProtocolException {
        if(!protocols.contains(locksensor.getProtocol())) {
            throw new IncompatibleProtocolException();
        }else
            this.locksensor = locksensor;
        if(!protocols.contains(tempsensor.getProtocol())) {
            throw new IncompatibleProtocolException();
        }else
            this.tempsensor = tempsensor;
        if(!protocols.contains(doeSwitch.getProtocol())) {
            throw new IncompatibleProtocolException();
        }else
            this.doeswitch = doeSwitch;
        if(!protocols.contains(acmeswitch.getProtocol())) {
            throw new IncompatibleProtocolException();
        }else
            this.acmeswitch = acmeswitch;
    }
 }
