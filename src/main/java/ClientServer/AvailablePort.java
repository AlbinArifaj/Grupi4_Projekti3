package ClientServer;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class AvailablePort {
    /**
     * Checks to see if a specific port is available.
     *
     * @param port the port to check for availability
     */

    public static boolean available(int port) {
        if (port < 1 || port > 65535) {
            return false;
        }

        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                }
            }
        }

        return false;
    }
}
