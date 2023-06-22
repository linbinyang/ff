package com.web.app.ff;


import com.web.app.ff.model.Event;
import com.web.app.ff.model.Group;
import com.web.app.ff.model.GroupRepository;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {

    @Resource
    private GroupRepository groupRepository;

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Seattle JUG", "Denver JUG",
                "Dublin JUG", "London JUG").forEach(name -> groupRepository.save(new Group(name)));

        Group djug = groupRepository.findByName("Seattle JUG");
        Event e = Event.builder().title("Micro Frontends for Java Developers")
                .description("JHipster now has microfrontend support!")
                .date(Instant.parse("2022-09-13T17:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        groupRepository.save(djug);

        groupRepository.findAll().forEach(System.out::println);
    }
}
