package employeeTest;

import javax.swing.*;
import employee.EmployeeSet; // EmployeeSet 클래스 가져오기
import employee.PartTimeEmp;
import employee.DepartmentEnum; // DepartmentEnum 클래스 가져오기

public class RegisterPartTimeGUI extends JFrame 
{
    private JLabel nameLabel, departmentLabel, hoursLabel; // 라벨 선언
    private JTextField nameField, hoursField; // 텍스트 필드 선언
    private JComboBox<String> departmentComboBox; // 콤보 박스 선언
    private JButton registerButton, backButton; // 버튼 선언

    private String[] departments = {"총무", "인사", "기획", "생산", "영업"}; // 부서 배열
    private JFrame parentFrame; // 부모 프레임 참조
    private EmployeeSet employeeSet; // EmployeeSet 인스턴스 변수 추가

    public RegisterPartTimeGUI(JFrame parentFrame, EmployeeSet employeeSet) 
    {
        this.parentFrame = parentFrame;
        this.employeeSet = employeeSet;

        setTitle("임시직 직원 등록"); // 프레임 제목 설정
        setSize(300, 250); // 프레임 크기 설정
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창 닫기 설정
        setLayout(null); // 레이아웃 설정

        nameLabel = new JLabel("이름:"); // 이름 라벨 생성
        nameLabel.setBounds(20, 20, 80, 25); // 라벨 위치 및 크기 설정
        add(nameLabel); // 라벨 추가

        nameField = new JTextField(); // 이름 텍스트 필드 생성
        nameField.setBounds(100, 20, 160, 25); // 텍스트 필드 위치 및 크기 설정
        add(nameField); // 텍스트 필드 추가

        departmentLabel = new JLabel("부서:"); // 부서 라벨 생성
        departmentLabel.setBounds(20, 50, 80, 25); // 라벨 위치 및 크기 설정
        add(departmentLabel); // 라벨 추가

        departmentComboBox = new JComboBox<>(departments); // 부서 콤보 박스 생성
        departmentComboBox.setBounds(100, 50, 160, 25); // 콤보 박스 위치 및 크기 설정
        add(departmentComboBox); // 콤보 박스 추가

        hoursLabel = new JLabel("근무시간:"); // 근무시간 라벨 생성
        hoursLabel.setBounds(20, 80, 80, 25); // 라벨 위치 및 크기 설정
        add(hoursLabel); // 라벨 추가

        hoursField = new JTextField(); // 근무시간 텍스트 필드 생성
        hoursField.setBounds(100, 80, 160, 25); // 텍스트 필드 위치 및 크기 설정
        add(hoursField); // 텍스트 필드 추가

        registerButton = new JButton("등록"); // 등록 버튼 생성
        registerButton.setBounds(20, 150, 100, 25); // 버튼 위치 및 크기 설정
        registerButton.addActionListener(e -> confirmEmployee()); // 버튼에 액션 리스너 추가
        add(registerButton); // 버튼 추가

        backButton = new JButton("돌아가기"); // 돌아가기 버튼 생성
        backButton.setBounds(170, 150, 100, 25); // 버튼 위치 및 크기 설정
        backButton.addActionListener(e -> goBack()); // 버튼에 액션 리스너 추가
        add(backButton); // 버튼 추가

        setVisible(true); // 프레임을 보이게 설정
    }

    private void confirmEmployee() 
    {
        String name = nameField.getText(); // 이름 필드 값 가져오기
        String department = (String) departmentComboBox.getSelectedItem(); // 선택한 부서 값 가져오기
        String hoursStr = hoursField.getText(); // 근무시간 필드 값 가져오기
        int hours;

        try 
        {
            hours = Integer.parseInt(hoursStr); // 근무시간 필드 값 변환
        } catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this, "유효한 근무시간 값을 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE); // 에러 메시지 표시
            return;
        }

        String confirmMessage = String.format("이름: %s\n부서: %s\n근무시간: %d\n\n위의 정보로 직원을 등록하시겠습니까?", name, department, hours);
        int response = JOptionPane.showConfirmDialog(this, confirmMessage, "직원 정보 확인", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) 
        {
            registerEmployee(name, department, hours);
        }
    }

    private void registerEmployee(String name, String department, int hours) 
    {
        // Enum 값 변환을 위해 맵핑
        DepartmentEnum departmentEnum;
        switch (department) 
        {
            case "총무":
                departmentEnum = DepartmentEnum.GENERAL_AFFAIR;
                break;
            case "인사":
                departmentEnum = DepartmentEnum.PERSONNEL;
                break;
            case "기획":
                departmentEnum = DepartmentEnum.PLANNING;
                break;
            case "생산":
                departmentEnum = DepartmentEnum.PRODUCTION;
                break;
            case "영업":
                departmentEnum = DepartmentEnum.SALES;
                break;
            default:
                JOptionPane.showMessageDialog(this, "유효한 부서 값을 선택하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                return;
        }

        PartTimeEmp newEmployee = new PartTimeEmp(employeeSet.part_id, name, departmentEnum, hours); // 새로운 임시직 직원 생성
        employeeSet.getEmployees().add(newEmployee); // 직원 목록에 추가
        employeeSet.part_id++; // 임시직 직원 ID 증가

        JOptionPane.showMessageDialog(this, "직원 등록이 완료되었습니다.", "등록 성공", JOptionPane.INFORMATION_MESSAGE); // 성공 메시지 표시

        // 입력 필드 초기화
        nameField.setText("");
        departmentComboBox.setSelectedIndex(0);
        hoursField.setText("");
    }

    private void goBack() 
    {
        dispose(); // 현재 창 닫기
        parentFrame.setVisible(true); // 부모 프레임 보이기
    }
}
