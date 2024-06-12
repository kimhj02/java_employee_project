package employeeTest;

import employee.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import employee.EmployeeSet;

public class EmployeeManagementSystemGUI extends JFrame
{

    private EmployeeSet employeeSet = new EmployeeSet(); // EmployeeSet 객체 생성
    private static final String FILE_NAME = "employees.txt";

    public EmployeeManagementSystemGUI() {
        setTitle("직원 등록 프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 전체 프레임에 BorderLayout 설정
        setLayout(new BorderLayout());

        // 메인 패널 생성 및 레이아웃 설정
        JPanel mainPanel = new JPanel(new GridLayout(7, 1, 0, 10)); // 7개의 행, 간격은 10 픽셀

        // 직원 등록 버튼
        JButton registerBtn = new JButton("직원 등록");
        registerBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // 가운데 정렬
        registerBtn.setPreferredSize(new Dimension(200, 50)); // 크기 설정
        registerBtn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                setVisible(false);
                new EmployManagementSystemresistergui(EmployeeManagementSystemGUI.this, employeeSet);
            }
        });
        mainPanel.add(registerBtn);

        // 직원 탐색 버튼
        JButton searchBtn = new JButton("직원 탐색");
        searchBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // 가운데 정렬
        searchBtn.setPreferredSize(new Dimension(200, 50)); // 크기 설정
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new EmployManagementSystemsearchGUI(EmployeeManagementSystemGUI.this, employeeSet);
            }
        });
        mainPanel.add(searchBtn);

        // 직원 업데이트 버튼
        JButton updateBtn = new JButton("직원 업데이트");
        updateBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // 가운데 정렬
        updateBtn.setPreferredSize(new Dimension(200, 50)); // 크기 설정
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new EmployManagementSystemUpdateGUI(EmployeeManagementSystemGUI.this, employeeSet);
            }
        });
        mainPanel.add(updateBtn);

        // 직원 삭제 버튼
        JButton deleteBtn = new JButton("직원 삭제");
        deleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // 가운데 정렬
        deleteBtn.setPreferredSize(new Dimension(200, 50)); // 크기 설정
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new EmployManagementSystemDeleteGUI(EmployeeManagementSystemGUI.this, employeeSet);
            }
        });
        mainPanel.add(deleteBtn);

        // 직원 저장 버튼
        JButton saveBtn = new JButton("직원 정보 저장");
        saveBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // 가운데 정렬
        saveBtn.setPreferredSize(new Dimension(200, 50)); // 크기 설정
        saveBtn.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                try {
                    employeeSet.saveToFile(FILE_NAME);
                    JOptionPane.showMessageDialog(null, "직원 정보가 저장되었습니다.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "저장 중 오류가 발생했습니다: " + ex.getMessage());
                }
            }
        });
        mainPanel.add(saveBtn);

        // 직원 불러오기 버튼
        JButton loadBtn = new JButton("직원 정보 불러오기");
        loadBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // 가운데 정렬
        loadBtn.setPreferredSize(new Dimension(200, 50)); // 크기 설정
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    employeeSet.loadFromFile(FILE_NAME);
                    JOptionPane.showMessageDialog(null, "직원 정보가 불러와졌습니다.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "불러오기 중 오류가 발생했습니다: " + ex.getMessage());
                }
            }
        });
        mainPanel.add(loadBtn);

        // 프로그램 종료 버튼
        JButton exitBtn = new JButton("프로그램 종료");
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT); // 가운데 정렬
        exitBtn.setPreferredSize(new Dimension(200, 50)); // 크기 설정
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPanel.add(exitBtn);

        // 메인 패널을 프레임의 중앙에 추가
        add(mainPanel, BorderLayout.CENTER);

        // 프레임 크기 설정 및 보이기
        setSize(400, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeManagementSystemGUI();
            }
        });
    }
}
