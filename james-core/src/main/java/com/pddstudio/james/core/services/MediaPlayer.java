package com.pddstudio.james.core.services;

import android.support.annotation.RawRes;

import com.pddstudio.james.core.James;
import com.pddstudio.james.core.abstracts.AbstractService;

/**
 * This Class was created by Patrick J
 * on 21.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class MediaPlayer extends AbstractService {

    private final James mJames;
    private android.media.MediaPlayer mMediaPlayer;

    public MediaPlayer(James james) {
        this.mJames = james;
    }

    public void play(@RawRes int rawId) {
        this.mMediaPlayer = android.media.MediaPlayer.create(mJames.getContext(), rawId);
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
