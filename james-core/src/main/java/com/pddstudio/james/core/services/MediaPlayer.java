package com.pddstudio.james.core.services;

import com.pddstudio.james.core.James;
import com.pddstudio.james.core.abstracts.AbstractService;

/**
 * This Class was created by Patrick J
 * on 21.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class MediaPlayer extends AbstractService {

    private final James mJames;

    public MediaPlayer(James james) {
        this.mJames = james;
    }

    @Override
    public String getServiceName() {
        return getClass().getSimpleName();
    }

    @Override
    public Class<?> getServiceClass() {
        return MediaPlayer.class;
    }
}
