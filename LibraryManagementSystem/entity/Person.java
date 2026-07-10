package LibraryManagementSystem.entity;

public sealed abstract class Person permits Member, Librarian {
    private final String personId;
    private final String  name;
    private final String phone;

    public Person(String personId, String name, String phone) {
        this.personId = personId;
        this.name = name;
        this.phone = phone;
    }

    public String getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
