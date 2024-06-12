package employeeTest;

import javax.swing.*;
import java.awt.*;
import employee.EmployeeSet;
import employee.FullTimeEmp;

public class SearchFullTimeGUI extends JFrame {
    private JTextArea resultArea;
    private JFrame parentFrame; // 부모 프레임 추가

    public SearchFullTimeGUI(JFrame parentFrame, EmployeeSet employeeSet) {
        this.parentFrame = parentFrame; // 부모 프레임 설정
        setTitle("정규직 직원 검색");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 현재 창만 닫기
        setSize(400, 300);

        initComponents();
        addComponentsToFrame();

        // Display full-time employees
        displayFullTimeEmployees(employeeSet);

        setVisible(true);
    }

    private void initComponents() {
        resultArea = new JTextArea();
        resultArea.setEditable(false);
    }

    private void addComponentsToFrame() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("돌아가기");
        backButton.addActionListener(e -> goBack());
        buttonPanel.add(backButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    // 돌아가기 버튼을 눌렀을 때의 동작
    private void goBack() {
        if (parentFrame != null) { // 부모 프레임이 존재하면
            parentFrame.setVisible(true); // 부모 프레임을 보이게 설정
        }
        dispose(); // 현재 창 닫기
    }

    // 정규직 직원 정보를 표시하는 메서드
    public void displayFullTimeEmployees(EmployeeSet employeeSet) {
        StringBuilder sb = new StringBuilder();

        // 정규직 직원 정보 추가
        for (FullTimeEmp fullTimeEmp : employeeSet.searchFullTime()) {
            sb.append("사번: ").append(fullTimeEmp.getId()).append("\n");
            sb.append("이름: ").append(fullTimeEmp.getName()).append("\n");
            sb.append("부서: ").append(fullTimeEmp.getDepartment()).append("\n");
            sb.append("직급: ").append(fullTimeEmp.getPosition()).append("\n");
            sb.append("호봉: ").append(fullTimeEmp.getGrade()).append("\n");
            sb.append("--------------------\n");
        }

        resultArea.setText(sb.toString());
    }
}
