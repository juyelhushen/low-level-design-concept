package LibraryManagementSystem.entity;

public final class Librarian extends Person {
    private final String employeeId;

    public Librarian(String personId, String name, String phone, String employeeId) {
        super(personId, name, phone);
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }
}
