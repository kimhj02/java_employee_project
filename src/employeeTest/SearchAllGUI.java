package employeeTest;

import javax.swing.*;
import employee.Employee;
import employee.EmployeeSet;
import employee.FullTimeEmp;
import employee.PartTimeEmp;
import java.awt.*;

public class SearchAllGUI extends JFrame {
    private JFrame parentFrame;
    private JTextArea textArea;
    
    public SearchAllGUI(JFrame parentFrame, EmployeeSet employeeSet) {
        this.parentFrame = parentFrame;

        setTitle("전체 직원 검색");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 현재 창만 닫기
        setSize(800, 600);

        initComponents();
        addComponentsToFrame();

        // Display all employees in the textarea
        displayAllEmployees(employeeSet);

        setVisible(true);
        setSize(400, 300);
    }

    private void initComponents() {
        // 텍스트 에어리어 생성
        textArea = new JTextArea();
        textArea.setEditable(false);

        // 스크롤 가능하도록 JScrollPane에 텍스트 에어리어 추가
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);

        // 레이아웃 매니저 설정
        setLayout(new BorderLayout());

        // 돌아가기 버튼 생성
        JButton backButton = new JButton("돌아가기");
        backButton.addActionListener(e -> goBack());

        // 버튼 패널 생성하여 돌아가기 버튼 추가
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);

        add(textAreaScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void goBack() {
        dispose(); // 현재 창 닫기
        parentFrame.setVisible(true); // 부모 프레임 보이기
    }

    private void addComponentsToFrame() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // 텍스트 에어리어를 메인 콘텐츠에 추가
        contentPane.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // 버튼 패널 생성하여 돌아가기 버튼 추가
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("돌아가기");
        backButton.addActionListener(e -> goBack());
        buttonPanel.add(backButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    // 전체 직원 정보를 표시하는 메서드
    public void displayAllEmployees(EmployeeSet employeeSet) {
        // 텍스트 에어리어 초기화
        textArea.setText("");

        // 직원 정보 추가
        for (Employee emp : employeeSet.getEmployees()) {
            if (emp instanceof FullTimeEmp) {
                FullTimeEmp fullTimeEmp = (FullTimeEmp) emp;
                textArea.append("사번: " + fullTimeEmp.getId() + "\n");
                textArea.append("이름: " + fullTimeEmp.getName() + "\n");
                textArea.append("부서: " + fullTimeEmp.getDepartment() + "\n");
                textArea.append("직급: " + fullTimeEmp.getPosition() + "\n");
                textArea.append("호봉: " + fullTimeEmp.getGrade() + "\n");
            } else if (emp instanceof PartTimeEmp) {
                PartTimeEmp partTimeEmp = (PartTimeEmp) emp;
                textArea.append("사번: " + partTimeEmp.getId() + "\n");
                textArea.append("이름: " + partTimeEmp.getName() + "\n");
                textArea.append("부서: " + partTimeEmp.getDepartment() + "\n");
                textArea.append("근무시간: " + partTimeEmp.getHours() + "\n");
            }
            textArea.append("--------------------\n");
        }
    }
}
