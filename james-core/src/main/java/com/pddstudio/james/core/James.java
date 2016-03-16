package com.pddstudio.james.core;

import android.content.Context;

/*
 * This Class was created by Patrick J
 * on 16.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public final class James<T> {

    private final Context mContext;
    private T serviceType;

    private James(Context context) {
        this.mContext = context;
    }

    public static James with(Context context) {
        return new James(context);
    }

    public T serve(Object o) {
        return serviceType;
    }

}
