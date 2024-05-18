package Service;

public class PortConnection {

    private static int portNumber;


    public static void setPortNumber(int portNumber) {
        PortConnection.portNumber = portNumber;

    }
    public static int getPortNumber() {
        return portNumber;
    }
}
