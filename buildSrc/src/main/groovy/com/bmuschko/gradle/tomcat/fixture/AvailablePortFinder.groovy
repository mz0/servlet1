package com.bmuschko.gradle.tomcat.fixture

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * Finds currently available server ports within a certain port range. Code originally taken from Apache MINA.
 *
 * <em>Note:</em> If possible, it's preferable to let the party creating the server socket select the port (e.g. with <tt>new ServerSocket(0)</tt>) and then query it for the port chosen. With this
 * class, there is always a risk that someone else grabs the port between the time it is returned from <tt>getNextAvailable()</tt> and the time the socket is created.
 * @see <a href="http://www.iana.org/assignments/port-numbers">IANA.org</a>
 */
class AvailablePortFinder {
    private static final int MIN_PRIVATE_PORT = 49152
    private static final int MAX_PRIVATE_PORT = 65535

    private final Lock lock = new ReentrantLock()
    private final int startPort
    private int current
    private static final AvailablePortFinder INSTANCE = new AvailablePortFinder()

    /**
     * Creates a port finder that operates on private ports.
     *
     * @return a port finder that operates on private ports
     */
    static AvailablePortFinder createPrivate() {
        INSTANCE
    }

    private AvailablePortFinder() {
        startPort = new Random().nextInt(MAX_PRIVATE_PORT - MIN_PRIVATE_PORT) + MIN_PRIVATE_PORT
        current = startPort
    }

    /**
     * Gets the next available port.
     *
     * <p>Tries to avoid returning the same port on successive invocations (but it may happen if no other available ports are found).
     *
     * @return the next available port
     * @throws NoSuchElementException if no available port is found
     */
    int getNextAvailable() {
        lock.lock()
        try {
            while (true) {
                if (current >= MAX_PRIVATE_PORT) {
                    current = MIN_PRIVATE_PORT
                } else {
                    current++
                }
                if (current == startPort) {
                    throw new NoSuchElementException("Could not find an available port within port range.");
                }
                int candidate = current
                if (available(candidate)) {
                    return candidate
                }
            }
        } finally {
            lock.unlock()
        }
    }

    /**
     * Checks to see if a specific port is available.
     *
     * @param port the port to check for availability
     * @return <tt>true</tt> if the port is available, <tt>false</tt> otherwise
     */
    private boolean available(int port) {
        try {
            ServerSocket ss = new ServerSocket(port)
            try {
                ss.setReuseAddress(true)
            } finally {
                ss.close()
            }
            DatagramSocket ds = new DatagramSocket(port)
            try {
                ds.setReuseAddress(true)
            } finally {
                ds.close()
            }
            return true
        } catch (IOException e) {
            return false
        }
    }
}
