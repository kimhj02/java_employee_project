package employeeTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import employee.*;

public class DeleteGUI extends JFrame 
{
    private JFrame parentFrame;
    private EmployeeSet employeeSet;

    private JTextField idField;
    private JTextField nameField;
    private JTextArea resultArea;

    public DeleteGUI(JFrame parentFrame, EmployeeSet employeeSet) 
    {
        this.parentFrame = parentFrame;
        this.employeeSet = employeeSet;

        setTitle("직원 정보 삭제");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);

        initComponents(); // 컴포넌트 초기화
        addComponentsToFrame(); // 컴포넌트를 프레임에 추가

        setVisible(true);
    }

    private void initComponents() 
    {
        idField = new JTextField();
        nameField = new JTextField();
        resultArea = new JTextArea();
        resultArea.setEditable(false);
    }

    private void addComponentsToFrame() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // 입력 패널
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("직원 ID: "));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("직원 이름: "));
        inputPanel.add(nameField);

        // 삭제 버튼
        JButton deleteButton = new JButton("삭제");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployeeById();
            }
        });

        // 검색 버튼
        JButton searchButton = new JButton("검색");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEmployeeByName();
            }
        });

        inputPanel.add(deleteButton);
        inputPanel.add(searchButton);

        contentPane.add(inputPanel, BorderLayout.NORTH);

        // 결과 영역을 스크롤 팬에 추가
        JScrollPane scrollPane = new JScrollPane(resultArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // 뒤로가기 버튼
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
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

    // ID로 직원 삭제
    private void deleteEmployeeById() {
        String idString = idField.getText();
        if (idString != null && !idString.isEmpty()) {
            try {
                int id = Integer.parseInt(idString);
                Employee employeeToDelete = null;
                for (Employee employee : employeeSet.getEmployees()) {
                    if (employee.getId() == id) {
                        employeeToDelete = employee;
                        break;
                    }
                }
                if (employeeToDelete != null) {
                    int response = JOptionPane.showConfirmDialog(this,
                            "정말로 삭제하시겠습니까?\n\n" + employeeToDelete.toString(),
                            "삭제 확인",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        employeeSet.getEmployees().remove(employeeToDelete);
                        resultArea.setText("직원 정보가 삭제되었습니다.\n" + employeeToDelete.toString());
                    }
                } else {
                    resultArea.setText("해당 ID에 해당하는 직원을 찾을 수 없습니다.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "유효하지 않은 ID입니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "직원 ID를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 이름으로 직원 검색
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
}
