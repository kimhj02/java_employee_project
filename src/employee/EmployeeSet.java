package employee;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import utils.Utils;

public class EmployeeSet 
{
    private ArrayList<Employee> employees; // 모든 직원 컬렉션으로 받음
    public int full_id = 10101; // 정규직 사번
    public int part_id = 50101; // 임시직 사번

    public EmployeeSet() 
    {
        employees = new ArrayList<>(); // ArrayList 초기화
    }

    // 정규직 직원 등록 메소드
    public void RegisterFullTimeEmp(Scanner s) 
    {
        System.out.println("");
        System.out.println(">>> 정규직 등록 <<<");

        String name = Utils.getName(s);
        DepartmentEnum department = Utils.getDepartment(s);
        PositionEnum position = Utils.getPosition(s);
        int grade = Utils.getGrade(s);

        employees.add(new FullTimeEmp(full_id, name, department, position, grade)); // add로 추가
        full_id++;
    }

    // 임시직 직원 등록 메소드
    public void RegisterPartTimeEmp(Scanner s) {
        System.out.println("");
        System.out.println(">>> 임시직 등록 <<<");

        String name = Utils.getName(s);
        DepartmentEnum department = Utils.getDepartment(s);
        int hours = Utils.getHours(s);

        employees.add(new PartTimeEmp(part_id, name, department, hours)); // add로 추가
        part_id++;
    }

    public void searchAll() {
        for (Employee emp : employees) { // 모든 직원의 정보 출력
            System.out.println("\n================");
            emp.print();
        }
    }

    public ArrayList<FullTimeEmp> searchFullTime() 
    {
        ArrayList<FullTimeEmp> fullTimeEmployees = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp instanceof FullTimeEmp) {
                fullTimeEmployees.add((FullTimeEmp) emp);
            }
        }
        return fullTimeEmployees;
    }

    public ArrayList<PartTimeEmp> searchPartTime() 
    {
        ArrayList<PartTimeEmp> partTimeEmployees = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp instanceof PartTimeEmp) {
                partTimeEmployees.add((PartTimeEmp) emp);
            }
        }
        return partTimeEmployees;
    }

    public void searchByName(Scanner s) {
        System.out.println("검색할 이름을 입력하세요");
        String searchName = Utils.getName(s); // 이름 받기
        boolean found = false;

        for (Employee emp : employees) { // 직원 목록을 돌면서 탐색
            if (emp.getName().equals(searchName)) { // 이름이 일치하면 출력
                System.out.println();
                System.out.println("================ ");
                emp.print();
                found = true;
            }
        }
        if (!found) { // 없다면
            System.out.println("!!! 해당 이름의 직원이 없습니다.");
        }
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void updateEmployee(Scanner s) {
        System.out.println("");
        System.out.println(">>> 직원 정보 갱신 <<<");

        boolean foundName = false; // 이름이 존재하는지 확인하는 boolean형 변수
        ArrayList<Integer> matchingIndexes = new ArrayList<>(); // 동일한 이름의 직원들의 인덱스를 저장하는 ArrayList

        // 이름으로 직원 검색
        System.out.println("갱신할 직원의 이름을 입력하세요:");

        String searchName = Utils.getName(s); // 이름 검색

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equals(searchName)) { // 이름이 일치하는 직원의 인덱스를 저장 get사용
                foundName = true;
                matchingIndexes.add(i);
            }
        }

        if (!foundName) { // 이름이 존재하지 않는 경우
            System.out.println("!!! 해당 이름의 직원이 없습니다.");
            return;
        }

        // 같은 이름을 가진 직원들의 정보 출력
        for (int index : matchingIndexes) {
            System.out.println();
            System.out.println("================ ");
            employees.get(index).print();
        }

        // 직원의 ID 입력 받기
        System.out.println("\n갱신할 직원의 ID를 입력하세요:");
        int searchId = Utils.getID(s); // 사용자로부터 ID 입력 받기

        // 선택한 직원의 인덱스 찾기
        int updateIndex = -1;
        for (int index : matchingIndexes) {
            if (employees.get(index).getId() == searchId) { // 선택한 직원의 ID와 일치하는 직원의 인덱스 저장
                updateIndex = index;
                break;
            }
        }

        if (updateIndex == -1) { // id가 일치하는 사람이 없다면
            System.out.println("!!! 해당 ID의 직원이 없습니다.");
            return;
        }

        if (employees.get(updateIndex) instanceof FullTimeEmp) { // 정규직인 경우
            FullTimeEmp fullTimeEmp = (FullTimeEmp) employees.get(updateIndex); //get사용
            System.out.println();
            System.out.println("================ ");
            fullTimeEmp.setName(Utils.getName(s)); // 이름
            fullTimeEmp.setDepartment(Utils.getDepartment(s)); // 부서
            fullTimeEmp.setPosition(Utils.getPosition(s)); // 직위
            fullTimeEmp.setGrade(Utils.getGrade(s)); // 연차
        } else if (employees.get(updateIndex) instanceof PartTimeEmp) { // 임시직인 경우
            PartTimeEmp partTimeEmp = (PartTimeEmp) employees.get(updateIndex); //get사용
            System.out.println();
            partTimeEmp.setName(Utils.getName(s)); // 이름
            partTimeEmp.setDepartment(Utils.getDepartment(s)); // 부서
            partTimeEmp.setHours(Utils.getHours(s)); // 시간
        }

        // 갱신된 직원 정보 출력
        System.out.println("\n=== 갱신 결과 ===");
        employees.get(updateIndex).print();
    }

    public void deleteEmployeeById(Scanner s) //직원 정보 삭제 기능
    {
        boolean found = false;

        System.out.println("삭제할 직원의 id 확인을 위해 직원 이름을 검색합니다.");
        System.out.println("\n검색할 이름을 입력하세요");

        String deleteName = Utils.getName(s); // 삭제할 직원의 이름 입력 받기

        while (!found) { //이름을 찾았고 id를 찾으면 탈출
            // 검색한 이름이 존재하는지 확인
            boolean nameFound = false;

            for (Employee emp : employees) {
                if (emp.getName().equals(deleteName)) //해당 이름을 가진 직원의 정보를 출력
                {
                    nameFound = true;
                    System.out.println("\n================");
                    emp.print();
                }
            }

            if (!nameFound) // 이름이 존재하지 않을 때
            {
                System.out.println("!!! 해당 이름의 직원이 없습니다.\n");
                System.out.println("이름을 입력하세요:");
                deleteName = Utils.getName(s); // 다시 이름 입력 받기

            } else //이름이 존재 한다면
            {
                // 정보를 삭제할 직원의 ID를 입력 받음
                System.out.println("정보를 삭제할 직원의 ID를 입력하세요:");
                int deleteId = Utils.getID(s); // 삭제할 직원의 ID 입력 받기
                boolean idMatched = false;

                for (Employee emp : employees) {
                    if (emp.getId() == deleteId && emp.getName().equals(deleteName)) {
                        idMatched = true;
                        System.out.println(deleteName + " 삭제하겠습니까? (1:예, 2:아니오)");
                        int choice = Utils.checkInputInteger(s, "선택>>> ");

                        if (choice == 1) {
                            System.out.println("해당 직원을 삭제하였습니다.");
                            employees.remove(emp); // 직원 삭제
                        } else {
                            System.out.println("삭제가 취소되었습니다.");
                        }
                        found = true;
                        break;
                    }
                }

                if (!idMatched) {
                    System.out.println("해당 ID의 직원이 아닙니다.");
                }
            }
        }
    }

    // 직원 정보를 파일에 저장하는 메소드
    public void saveToFile(String filename) throws IOException 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) 
        {
            for (Employee employee : employees) 
            {
                writer.write(employee.toString());
                writer.newLine();
            }
        }
    }

    // 파일에서 직원 정보를 읽어오는 메소드
    public void loadFromFile(String filename) throws IOException 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
        {
            String line;
            employees.clear(); //기존에 있는 값을 리셋하고
            while ((line = reader.readLine()) != null) 
            {
                Employee employee = Employee.fromString(line);
                employees.add(employee);
            }
        }
    }
}
