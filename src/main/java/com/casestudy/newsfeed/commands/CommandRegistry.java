package com.casestudy.newsfeed.commands;

import com.casestudy.newsfeed.models.Session;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class CommandRegistry {
    private List<Command> commands;
    public static Session currentSession;

    @Autowired
    public CommandRegistry(FollowCommand followCommand
    , PostCommand postCommand
    , CommentCommand commentCommand
    , SignUpCommand signUpCommand
    , LoginCommand loginCommand
    , UpVoteCommand upVoteCommand
    , DownVoteCommand downVoteCommand
    , ShowNewsFeedCommand showNewsFeedCommand) {
        currentSession = new Session();
        commands = new ArrayList<>();
        commands.add(followCommand);
        commands.add(postCommand);
        commands.add(commentCommand);
        commands.add(signUpCommand);
        commands.add(loginCommand);
        commands.add(upVoteCommand);
        commands.add(downVoteCommand);
        commands.add(showNewsFeedCommand);
    }

    public void execute(String input) {
        for(Command command : commands) {
            if(command.matches(input)) {
                command.execute(input);
                break;
            }
        }
    }

    public static boolean checkIfValidSession() {
        if(currentSession.getCreatedBy() == null) {
            return false;
        }
        return true;
    }
}
