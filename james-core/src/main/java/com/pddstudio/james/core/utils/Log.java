package com.pddstudio.james.core.utils;

/**
 * This Class was created by Patrick J
 * on 22.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public enum Log {
    DEBUG("D"),
    INFO("I"),
    WARNING("W"),
    ERROR("E");

    final String log;

    Log(String log) {
        this.log = log;
    }

    public String getPrefix() {
        return log;
    }

}
