package employeeTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import employee.*;

public class UpdatebyidGUI extends JFrame 
{
    private JFrame parentFrame;
    private EmployeeSet employeeSet;

    private JTextField nameField;
    private JTextArea resultArea;

    public UpdatebyidGUI(JFrame parentFrame, EmployeeSet employeeSet) 
    {
        this.parentFrame = parentFrame;
        this.employeeSet = employeeSet;

        setTitle("직원 정보 갱신");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        initComponents();
        addComponentsToFrame();

        setVisible(true);
    }

    private void initComponents() 
    {
        nameField = new JTextField();
        resultArea = new JTextArea();
        resultArea.setEditable(false);
    }

    private void addComponentsToFrame() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("직원 이름: "), BorderLayout.WEST);
        inputPanel.add(nameField, BorderLayout.CENTER);

        JButton searchButton = new JButton("검색");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEmployeeByName();
            }
        });

        inputPanel.add(searchButton, BorderLayout.EAST);

        contentPane.add(inputPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton updateButton = new JButton("업데이트");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 업데이트 다이얼로그 띄우기
                showUpdateDialog();
            }
        });
        buttonPanel.add(updateButton);

        JButton backButton = new JButton("돌아가기");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                parentFrame.setVisible(true);
            }
        });
        buttonPanel.add(backButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void searchEmployeeByName() {
        String name = nameField.getText();
        StringBuilder sb = new StringBuilder();

        for (Employee employee : employeeSet.getEmployees()) {
            if (employee.getName().equals(name)) {
                sb.append("사번: ").append(employee.getId()).append("\n");
                sb.append("이름: ").append(employee.getName()).append("\n");
                sb.append("부서: ").append(employee.getDepartment()).append("\n");
                if (employee instanceof PartTimeEmp) {
                    sb.append("근무 시간: ").append(((PartTimeEmp) employee).getHours()).append("\n");
                } else if (employee instanceof FullTimeEmp) {
                    sb.append("직위: ").append(((FullTimeEmp) employee).getPosition()).append("\n");
                    sb.append("연차: ").append(((FullTimeEmp) employee).getGrade()).append("\n");
                }
                sb.append("--------------------\n");
            }
        }

        if (sb.length() == 0) {
            sb.append("해당 이름을 가진 직원이 없습니다.");
        }

        resultArea.setText(sb.toString());
    }

    private void showUpdateDialog() {
        String idString = JOptionPane.showInputDialog(this, "업데이트할 직원의 ID를 입력하세요:", "직원 ID 입력", JOptionPane.PLAIN_MESSAGE);
        if (idString != null && !idString.isEmpty()) {
            try {
                int id = Integer.parseInt(idString);
                updateEmployee(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "유효하지 않은 ID입니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void updateEmployee(int id) {
        Employee employeeToUpdate = null;
        // 주어진 ID에 해당하는 직원 찾기
        for (Employee employee : employeeSet.getEmployees()) {
            if (employee.getId() == id) {
                employeeToUpdate = employee;
                break;
            }
        }
        // 직원을 찾았을 경우 업데이트 다이얼로그 띄우기
        if (employeeToUpdate != null) {
            // 업데이트할 정보 입력 받기
            String name = JOptionPane.showInputDialog(this, "직원 이름을 입력하세요:", "직원 정보 업데이트", JOptionPane.PLAIN_MESSAGE);
            if (name == null || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "직원 이름을 입력해야 합니다.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DepartmentEnum department = selectDepartment(); // 부서 선택
            PositionEnum position = null;
            int grade = 0;
            int hours = 0;
            if (employeeToUpdate instanceof FullTimeEmp) 
            {
                position = selectPosition(); // 직위 선택
                String gradeStr = JOptionPane.showInputDialog(this, "연차를 입력하세요:", "직원 정보 업데이트", JOptionPane.PLAIN_MESSAGE);
                grade = Integer.parseInt(gradeStr);
            } else if (employeeToUpdate instanceof PartTimeEmp) {
                String hoursStr = JOptionPane.showInputDialog(this, "근무 시간을 입력하세요:", "직원 정보 업데이트", JOptionPane.PLAIN_MESSAGE);
                hours = Integer.parseInt(hoursStr);
            }
            // 업데이트된 정보로 직원 객체 업데이트
            employeeToUpdate.setName(name);
            employeeToUpdate.setDepartment(department);
            if (employeeToUpdate instanceof FullTimeEmp) 
            {
                ((FullTimeEmp) employeeToUpdate).setPosition(position);
                ((FullTimeEmp) employeeToUpdate).setGrade(grade);
            } else if (employeeToUpdate instanceof PartTimeEmp) 
            {
                ((PartTimeEmp) employeeToUpdate).setHours(hours);
            }
            // 결과 표시
            resultArea.setText("직원 정보가 업데이트되었습니다.\n" + employeeToUpdate.toString());
        } else {
            JOptionPane.showMessageDialog(this, "해당 ID에 해당하는 직원을 찾을 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private DepartmentEnum selectDepartment() {
        String[] departmentOptions = { "총무", "인사", "기획", "생산", "영업" };
        DepartmentEnum department = null;
        while (department == null) {
            String departmentStr = (String) JOptionPane.showInputDialog(this, "부서를 선택하세요:", "부서 선택", JOptionPane.PLAIN_MESSAGE, null, departmentOptions, departmentOptions[0]);
            if (departmentStr == null) {
                // 사용자가 취소를 누르면 메서드를 빠져나감
                break;
            }
            // 사용자 입력 문자열과 일치하는 DepartmentEnum 값을 찾음
            for (DepartmentEnum dept : DepartmentEnum.values()) {
                if (dept.getMessage().equalsIgnoreCase(departmentStr)) {
                    department = dept;
                    break;
                }
            }
            if (department == null) {
                JOptionPane.showMessageDialog(this, "유효하지 않은 부서입니다. 다시 선택해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        }
        return department;
    }
    
    private PositionEnum selectPosition() {
        String[] positionOptions = { "이사", "부장", "과장", "대리", "사원" };
        PositionEnum position = null;
        while (position == null) {
            String positionStr = (String) JOptionPane.showInputDialog(this, "직위를 선택하세요:", "직위 선택", JOptionPane.PLAIN_MESSAGE, null, positionOptions, positionOptions[0]);
            if (positionStr == null) 
            {
                // 사용자가 취소를 누르면 메서드를 빠져나감
                break;
            }
            // 사용자 입력 문자열과 일치하는 PositionEnum 값을 찾음
            for (PositionEnum pos : PositionEnum.values()) 
            {
                if (pos.getMessage().equalsIgnoreCase(positionStr)) {
                    position = pos;
                    break;
                }
            }
            if (position == null) 
            {
                JOptionPane.showMessageDialog(this, "유효하지 않은 직위입니다. 다시 선택해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        }
        return position;
    }
    
}
