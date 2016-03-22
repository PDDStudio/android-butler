package com.pddstudio.james.http;

import android.os.AsyncTask;

import com.pddstudio.james.core.James;
import com.pddstudio.james.core.abstracts.AbstractService;
import com.pddstudio.james.core.utils.Logger;
import com.pddstudio.james.http.interfaces.RemoteJamesCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private RemoteJamesCallback mRemoteCallback;

    public RemoteJamesService(James james) {
        this.mJames = james;
    }

    public void setConnectionInfo(String remoteAddress, int remotePort) {
        this.mRemoteAddress = remoteAddress;
        this.mRemotePort = remotePort;
    }

    public void setRemoteCallback(RemoteJamesCallback remoteCallback) {
        this.mRemoteCallback = remoteCallback;
    }

    public void connect() {
        new ConnectionService().execute();
    }

    @Override
    public String getServiceName() {
        return null;
    }

    @Override
    public Class<?> getServiceClass() {
        return null;
    }

    private class ConnectionService extends AsyncTask<Void, Object, Void> {

        private Throwable mThrowable;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Socket socket = new Socket(mRemoteAddress, mRemotePort);
                Logger.log(this, "Connection successful: " + socket.isConnected());
                if(mRemoteCallback != null && socket.isConnected()) {
                    publishProgress(true);
                }
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    publishProgress(line);
                }
            } catch (IOException io) {
                mThrowable = io;
                this.cancel(true);
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            if(mRemoteCallback != null) mRemoteCallback.onConnectionFailed(mThrowable);
        }

        @Override
        protected void onProgressUpdate(Object... objects) {
            if(objects[0] instanceof Boolean && mRemoteCallback != null) mRemoteCallback.onConnected();
            if(objects[0] instanceof String && mRemoteCallback != null) mRemoteCallback.onCallbackReceived(objects[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(mRemoteCallback != null) mRemoteCallback.onConnectionClosed();
        }
    }

}
