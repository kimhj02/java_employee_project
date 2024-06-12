package employee;

public class FullTimeEmp extends Employee {
    private PositionEnum position;
    private int grade;

    public FullTimeEmp(int id, String name, DepartmentEnum department, PositionEnum position, int grade) {
        super(id, name, department);
        this.position = position;
        this.grade = grade;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public void setPosition(PositionEnum position) {
        this.position = position;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public void print() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Position: " + position);
        System.out.println("Grade: " + grade);
    }

    @Override
    public String toString() {
        return super.toString() + "," + position + "," + grade;
    }

    public static FullTimeEmp fromString(String str) {
        String[] parts = str.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        DepartmentEnum department = DepartmentEnum.valueOf(parts[2]);
        PositionEnum position = PositionEnum.valueOf(parts[3]);
        int grade = Integer.parseInt(parts[4]);
        return new FullTimeEmp(id, name, department, position, grade);
    }
}
