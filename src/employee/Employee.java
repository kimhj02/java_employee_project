package employee;

public abstract class Employee {
    protected int id;
    protected String name;
    protected DepartmentEnum department;

    public Employee(int id, String name, DepartmentEnum department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DepartmentEnum getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(DepartmentEnum department) {
        this.department = department;
    }

    public abstract void print();

    @Override
    public String toString() {
        return id + "," + name + "," + department;
    }

    public static Employee fromString(String str) {
        String[] parts = str.split(",");
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        DepartmentEnum department = DepartmentEnum.valueOf(parts[2]);
        // 하위 클래스에 따라 적절히 캐스팅 필요
        // 예시로 FullTimeEmp로 가정
        return new FullTimeEmp(id, name, department, PositionEnum.MANAGER, 1);
    }
}
