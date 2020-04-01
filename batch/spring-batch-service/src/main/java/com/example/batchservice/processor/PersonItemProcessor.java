package com.example.batchservice.processor;

import com.example.batchservice.model.Person;
import org.springframework.batch.item.ItemProcessor;

/**
 * 将对象A，转化为对象B
 */
public class PersonItemProcessor  implements ItemProcessor<Person, Person> {
    @Override
    public Person process(final Person person) throws Exception {
        final int id = person.getId();
        final String firstName = person.getFirstName();
        final String lastName = person.getLastName();

        final Person transformedPerson = new Person(id, firstName, lastName);
        return transformedPerson;
    }
}
