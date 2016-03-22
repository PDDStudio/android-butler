package com.pddstudio.james.http.interfaces;

/**
 * This Class was created by Patrick J
 * on 22.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public interface RemoteJamesCallback {
    void onConnected();
    void onConnectionFailed(Throwable throwable);
    void onCallbackReceived(Object o);
    void onConnectionClosed();
}
