package employeeTest;

import javax.swing.*;
import java.awt.*;
import employee.EmployeeSet;
import employee.PartTimeEmp;
import employee.*;

public class SearchPartTimeGUI extends JFrame {
    private JTextArea resultArea;
    private JFrame parentFrame; // 부모 프레임 추가

    public SearchPartTimeGUI(JFrame parentFrame, EmployeeSet employeeSet) 
    {
        this.parentFrame = parentFrame; // 부모 프레임 설정
        setTitle("임시직 직원 검색");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 현재 창만 닫기
        setSize(400, 300);

        initComponents();
        addComponentsToFrame();

        // Display part-time employees
        displayPartTimeEmployees(employeeSet);

        setVisible(true);
    }

    private void initComponents() {
        resultArea = new JTextArea();
        resultArea.setEditable(false);
    }

    private void addComponentsToFrame() 
    {
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

    // 임시직 직원 정보를 표시하는 메서드
    public void displayPartTimeEmployees(EmployeeSet employeeSet) {
        StringBuilder sb = new StringBuilder();

        // 임시직 직원 정보 추가
        for (PartTimeEmp partTimeEmp : employeeSet.searchPartTime()) 
        {
            sb.append("사번: ").append(partTimeEmp.getId()).append("\n");
            sb.append("이름: ").append(partTimeEmp.getName()).append("\n");
            sb.append("부서: ").append(partTimeEmp.getDepartment()).append("\n");
            sb.append("근무 시간: ").append(partTimeEmp.getHours()).append("\n");
            sb.append("--------------------\n");
        }

        resultArea.setText(sb.toString());
    }
}
