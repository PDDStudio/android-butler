package com.pddstudio.james.core;

import android.content.Context;
import android.util.Log;

import com.pddstudio.james.core.abstracts.AbstractService;

import java.lang.reflect.Constructor;

/*
 * This Class was created by Patrick J
 * on 16.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public final class James {

    private static final String LOG_TAG = "James";

    private final Context mContext;

    private James(Context context) {
        this.mContext = context;
    }

    public static James with(Context context) {
        return new James(context);
    }

    public <T extends AbstractService> T serve(Class<T> serviceName) {
        try {
            Constructor<?> mConstructor = serviceName.getConstructor(James.class);
            return (T) mConstructor.newInstance(this);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Unable to find, instantiate or cast the class you're looking for, Sir! Did you do something wrong?", e);
            throw new RuntimeException(e);
        }
    }

    public Context getContext() {
        return mContext;
    }

}
