package com.pddstudio.james.http;

import android.os.AsyncTask;

import com.pddstudio.james.core.James;
import com.pddstudio.james.core.abstracts.AbstractService;

/**
 * This Class was created by Patrick J
 * on 19.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class TwilioService extends AbstractService {

    public interface ResponseCallback {
    }

    private final James mJames;
    private String mAccountSid;
    private String mToken;

    public TwilioService(James james) {
        this.mJames = james;
    }

    public TwilioService setCredentials(String accountSid, String token) {
        this.mAccountSid = accountSid;
        this.mToken = token;
        return this;
    }

    @Override
    public String getServiceName() {
        return getClass().getSimpleName();
    }

    @Override
    public Class<?> getServiceClass() {
        return TwilioService.class;
    }

    private class RequestTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

    }

}
