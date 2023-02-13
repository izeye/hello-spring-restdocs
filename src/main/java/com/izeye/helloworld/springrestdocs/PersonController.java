package com.izeye.helloworld.springrestdocs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * {@link RestController} for {@link Person}.
 *
 * @author Johnny Lim
 */
@RestController
@RequestMapping(path = "/persons")
public class PersonController {

    @GetMapping("/search")
    public List<Person> search(@RequestParam String firstName) {
        Person person1 = new Person();
        person1.setFirstName(firstName);
        person1.setLastName("Lim");

        Person person2 = new Person();
        person2.setFirstName(firstName);
        person2.setLastName("Kim");
        return List.of(person1, person2);
    }

}
