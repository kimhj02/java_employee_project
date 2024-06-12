package employee;

public class PartTimeEmp extends Employee {
    private int hours;

    public PartTimeEmp(int id, String name, DepartmentEnum department, int hours) {
        super(id, name, department);
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public void print() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Hours: " + hours);
    }

    @Override
    public String toString() {
        return super.toString() + "," + hours;
    }

    public static PartTimeEmp fromString(String str) {
        String[] parts = str.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        DepartmentEnum department = DepartmentEnum.valueOf(parts[2]);
        int hours = Integer.parseInt(parts[3]);
        return new PartTimeEmp(id, name, department, hours);
    }
}
