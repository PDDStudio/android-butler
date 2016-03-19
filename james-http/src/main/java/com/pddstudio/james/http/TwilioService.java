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
    private String mTargetAddress;
    private ResponseCallback mResponseCallback;

    public TwilioService(James james) {
        this.mJames = james;
    }

    public TwilioService setCredentials(String accountSid, String token) {
        this.mAccountSid = accountSid;
        this.mToken = token;
        return this;
    }

    public TwilioService setResponseCallback(ResponseCallback responseCallback) {
        this.mResponseCallback = responseCallback;
        return this;
    }

    private void validateService() {
        if(mAccountSid == null || mAccountSid.isEmpty()) throw new RuntimeException("TwilioService AccountSID can't be null or empty.");
        if(mToken == null || mToken.isEmpty()) throw new RuntimeException("TwilioService Token can't be null or empty");
        if(mResponseCallback == null) throw new RuntimeException("TwilioService can't be executed without a callback.");
    }

    public void execute(String phoneNumber) {
        //assign the target address
        this.mTargetAddress = phoneNumber;
        //validate that everything is correct
        if(mTargetAddress == null || mTargetAddress.isEmpty()) throw new RuntimeException("Can't execute a request on a phone number which is null or empty.");
        validateService();
        //execute the task
        //TODO
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
