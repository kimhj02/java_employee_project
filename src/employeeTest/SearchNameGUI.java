package employeeTest;

import employee.Employee;
import employee.EmployeeSet;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import employee.*;


public class SearchNameGUI extends JFrame {

    private JTextField nameField;
    private JTextArea resultArea;
    private JFrame parentFrame;
    private EmployeeSet employeeSet;

    public SearchNameGUI(JFrame parentFrame, EmployeeSet employeeSet) {
        this.parentFrame = parentFrame;
        this.employeeSet = employeeSet;

        setTitle("이름으로 직원 검색");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        initComponents();
        addComponentsToFrame();

        setVisible(true);
    }

    private void initComponents() {
        nameField = new JTextField(20);
        resultArea = new JTextArea();
        resultArea.setEditable(false);
    }

    private void addComponentsToFrame() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("이름: "));
        inputPanel.add(nameField);
        JButton searchButton = new JButton("검색");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchEmployeeByName();
            }
        });
        inputPanel.add(searchButton);

        contentPane.add(inputPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("돌아가기");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
        buttonPanel.add(backButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void searchEmployeeByName() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "이름을 입력하세요.");
            return;
        }

        ArrayList<Employee> employees = employeeSet.getEmployees();
        StringBuilder sb = new StringBuilder();
        boolean found = false;

        for (Employee emp : employees) {
            if (emp.getName().equalsIgnoreCase(name)) {
                found = true;
                sb.append("사번: ").append(emp.getId()).append("\n");
                sb.append("이름: ").append(emp.getName()).append("\n");
                sb.append("부서: ").append(emp.getDepartment()).append("\n");
                if (emp instanceof FullTimeEmp) {
                    FullTimeEmp fullTimeEmp = (FullTimeEmp) emp;
                    sb.append("직위: ").append(fullTimeEmp.getPosition()).append("\n");
                    sb.append("연차: ").append(fullTimeEmp.getGrade()).append("\n");
                } else if (emp instanceof PartTimeEmp) {
                    PartTimeEmp partTimeEmp = (PartTimeEmp) emp;
                    sb.append("근무 시간: ").append(partTimeEmp.getHours()).append("\n");
                }
                sb.append("--------------------\n");
            }
        }
        
        if (!found) {
            sb.append("!!! 해당 이름의 직원이 없습니다.");
        }

        resultArea.setText(sb.toString());
    }

    private void goBack() {
        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
        dispose();
    }
}
