package employeeTest;

import employee.EmployeeSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployManagementSystemsearchGUI {

    private JFrame parentFrame;

    public EmployManagementSystemsearchGUI(JFrame parentFrame, EmployeeSet employeeSet) {
        this.parentFrame = parentFrame;

        // 새 프레임 생성
        JFrame frame = new JFrame("직원 검색 시스템");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 현재 창만 닫기
        frame.setSize(300, 300);

        // 버튼 생성
        JButton searchAllButton = new JButton("일괄 검색");
        JButton searchFullTimeButton = new JButton("정규직 검색");
        JButton searchPartTimeButton = new JButton("임시직 검색");
        JButton searchByNameButton = new JButton("이름 검색");
        JButton backButton = new JButton("돌아가기");

        // 패널 설정 및 버튼 추가
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.add(searchAllButton);
        panel.add(searchFullTimeButton);
        panel.add(searchPartTimeButton);
        panel.add(searchByNameButton);
        panel.add(backButton);

        // 프레임에 패널 추가
        frame.getContentPane().add(panel);

        // 프레임을 보이게 설정
        frame.setVisible(true);

        // 버튼에 대한 액션 리스너 추가
        searchAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 모든 직원 검색 처리 코드
                new SearchAllGUI(frame, employeeSet);
                frame.setVisible(false);
            }
        });

        searchFullTimeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SearchFullTimeGUI(frame, employeeSet);
                frame.setVisible(false);
            }
        });

        searchPartTimeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SearchPartTimeGUI(frame, employeeSet);
                frame.setVisible(false);
            }
        });

        searchByNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 이름으로 검색하는 코드
                new SearchNameGUI(frame, employeeSet);
                frame.setVisible(false);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 현재 창 닫고 부모 프레임 보이기
                frame.dispose();
                parentFrame.setVisible(true);
            }
        });
    }
}
