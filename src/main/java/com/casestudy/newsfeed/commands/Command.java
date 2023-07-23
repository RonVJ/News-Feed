package com.casestudy.newsfeed.commands;

import com.casestudy.newsfeed.models.Session;

public interface Command {

    boolean matches(String input);
    void execute(String input);
}
