package com.pddstudio.james.http;

import com.pddstudio.james.core.James;
import com.pddstudio.james.core.abstracts.AbstractService;

/**
 * This Class was created by Patrick J
 * on 19.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class TwilioService extends AbstractService {

    private final James mJames;

    public TwilioService(James james) {
        this.mJames = james;
    }

    @Override
    public String getServiceName() {
        return getClass().getSimpleName();
    }

    @Override
    public Class<?> getServiceClass() {
        return TwilioService.class;
    }
}
