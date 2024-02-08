package morning.everyone;

import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public class Demo {

    private static EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("default");

    @SneakyThrows
    public static void main(String[] args) {

        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        var person = doInSessionReturning(entityManager -> entityManager.find(Person.class, 1L));
        var theSamePerson = doInSessionReturning(entityManager -> entityManager.find(Person.class, 1L));
        System.out.println(person == theSamePerson);*/

       /*doInSession(entityManager -> {
            var person1 = entityManager.find(Person.class, 3L);
            System.out.println(person1);
            person1.setLastName("atata");
            entityManager.persist(person1);
            entityManager.remove(person1);

            var theSamePerson1 = entityManager.createQuery(
                    "select p from Person p where p.firstName = :firstName",
                            Person.class
                    )
                    .setParameter("firstName", "Chuck")
                    .getSingleResult();
            System.out.println(theSamePerson1);
            System.out.println(person1 == theSamePerson1);
        });*/

        Note note = new Note();
        note.setBody("Morning everyone");

        Person person = new Person();
        person.setFirstName("Andriy");
        person.setLastName("Paliychuk");
        person.setEmail("paliychuk@mail.com");
        person.addNote(note);

        doInSession(entityManager -> entityManager.persist(person));

    }

    // 1:16    27
    // todo: 1. persist(newPerson) – persist new person with new note so the note is inserted due to cascade (SELECT – 0, INSERT – 2)
    public static void todo1() {
        doInSession(entityManager -> {
            Person person = new Person();
            person.setFirstName("Raz");
            person.setLastName("Dva");
            person.setEmail("razdva@mail.com");

            Note note = new Note();
            note.setBody("todo1");
            person.addNote(note);

            entityManager.persist(person);
        });
    }

    // todo: 2. persist(newNote) – persist new note linked to existing person (SELECT – 1, INSERT – 1)
    public static void todo2() {
        doInSession(entityManager -> {
            Person person1 = entityManager.find(Person.class, 55L);

            Note note = new Note();
            note.setBody("todo2");
            note.setPerson(person1);
            entityManager.persist(note);
        });
    }

    // todo: 3. no persist – create new note and add it to the existing person (SELECT – 1, INSERT – 1)
    public static void todo3() {
        doInSession(entityManager -> {
            Person person = entityManager.find(Person.class, 84L);
            person.addNote(new Note("It's working"));
        });
    }

    // todo: 4. * persist new note by person id without loading person to the session (SELECT – 0, INSERT – 1)
   public static void todo4() {
        doInSession(entityManager -> {
            Person person = entityManager.getReference(Person.class, 99L);
            Note note = new Note("yo");
            note.setPerson(person);
            entityManager.persist(note);
        });
    }

    public static void doInSession(Consumer<EntityManager> entityManagerConsumer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManagerConsumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
