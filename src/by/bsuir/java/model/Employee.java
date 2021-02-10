package by.bsuir.java.model;

public class Employee {
    private long id;
    private String surname;
    private String name;
    private Position position;
    private double laborInput;

    public Employee() {
    }

    public Employee(long id, String surname, String name, Position position, double laborInput) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.position = position;
        this.laborInput = laborInput;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getLaborInput() {
        return laborInput;
    }

    public void setLaborInput(double laborInput) {
        this.laborInput = laborInput;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return id == employee.getId() &&
                surname.equals(employee.getSurname()) &&
                name.equals(employee.getName()) &&
                position.equals(employee.getPosition()) &&
                laborInput == (employee.getLaborInput());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        temp = Double.doubleToLongBits(laborInput);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return surname + ", " + name + ", " + position + ", " + laborInput;
    }
}
