package com.casestudy.newsfeed;

import com.casestudy.newsfeed.commands.CommandRegistry;
import com.casestudy.newsfeed.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class NewsFeedApplication implements CommandLineRunner {
    private Scanner scanner;
    private CommandRegistry commandRegistry;

    @Autowired
    public NewsFeedApplication(CommandRegistry commandRegistry) {
        this.scanner = new Scanner(System.in);
        this.commandRegistry = commandRegistry;
    }


    @Override
    public void run(String... args) throws Exception {
        while(true) {
            System.out.println("Tell me what to do: ");
            String input = scanner.nextLine();
            commandRegistry.execute(input);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(NewsFeedApplication.class, args);
    }

}
