package com.pddstudio.james.http;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.pddstudio.james.core.James;
import com.pddstudio.james.core.abstracts.AbstractService;
import com.pddstudio.james.http.model.TwilioResponse;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This Class was created by Patrick J
 * on 19.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class TwilioService extends AbstractService {

    public interface ResponseCallback {
        void onRequestStarted();
        void onRequestFailed();
        void onRequestFinished(@Nullable TwilioResponse twilioResponse);
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

    public void lookup(String phoneNumber) {
        //assign the target address
        this.mTargetAddress = phoneNumber;
        //validate that everything is correct
        if(mTargetAddress == null || mTargetAddress.isEmpty()) throw new RuntimeException("Can't execute a request on a phone number which is null or empty.");
        validateService();
        //execute the task
        new RequestTask().execute();
    }

    @Override
    public String getServiceName() {
        return getClass().getSimpleName();
    }

    @Override
    public Class<?> getServiceClass() {
        return TwilioService.class;
    }

    private class RequestTask extends AsyncTask<Void, Void, TwilioResponse> {

        private static final String TWILIO_URL_PRE = "https://lookups.twilio.com/v1/PhoneNumbers/";
        private static final String TWILIO_URL_POST = "?Type=carrier";

        private RequestTask() {}

        @Override
        public void onPreExecute() {
            mResponseCallback.onRequestStarted();
        }

        @Override
        protected TwilioResponse doInBackground(Void... params) {

            OkHttpClient okHttpClient = new OkHttpClient();
            Gson gson = new Gson();

            try {

                Request request = new Request.Builder()
                        .url(TWILIO_URL_PRE + mTargetAddress + TWILIO_URL_POST)
                        .addHeader(mAccountSid, mToken)
                        .build();

                Response response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()) return gson.fromJson(response.body().charStream(), TwilioResponse.class);
                else throw new IOException("Request wasn't successful");
            } catch (IOException io) {
                io.printStackTrace();
                this.cancel(true);
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            mResponseCallback.onRequestFailed();
        }

        @Override
        public void onPostExecute(TwilioResponse twilioResponse) {
            mResponseCallback.onRequestFinished(twilioResponse);
        }

    }

}
