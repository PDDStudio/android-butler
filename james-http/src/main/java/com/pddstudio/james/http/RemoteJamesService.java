package com.pddstudio.james.http;

import com.pddstudio.james.core.James;
import com.pddstudio.james.core.abstracts.AbstractService;
import com.pddstudio.james.core.utils.Logger;

import java.io.IOException;
import java.net.Socket;

/**
 * This Class was created by Patrick J
 * on 22.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class RemoteJamesService extends AbstractService {

    private final James mJames;

    private String mRemoteAddress = "localhost";
    private int mRemotePort = 1337; //lol, geeky stuff

    public RemoteJamesService(James james) {
        this.mJames = james;
    }

    public void setConnectionInfo(String remoteAddress, int remotePort) {
        this.mRemoteAddress = remoteAddress;
        this.mRemotePort = remotePort;
    }

    public void connect() throws IOException {
        Socket socket = new Socket(mRemoteAddress, mRemotePort);
        Logger.log(this, "Connection successful: " + socket.isConnected());
    }

    @Override
    public String getServiceName() {
        return null;
    }

    @Override
    public Class<?> getServiceClass() {
        return null;
    }
}
