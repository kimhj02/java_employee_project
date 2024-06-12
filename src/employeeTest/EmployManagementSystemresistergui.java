package employeeTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import employee.EmployeeSet;

public class EmployManagementSystemresistergui 
{
    private EmployeeSet employeeSet; // EmployeeSet 인스턴스 변수 추가

    public EmployManagementSystemresistergui(JFrame parentFrame, EmployeeSet employeeSet) 
    {
        this.employeeSet = employeeSet;

        // 새 프레임 생성
        JFrame frame = new JFrame("직원 등록 시스템");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 현재 창만 닫기
        frame.setSize(300, 200);

        // 버튼 생성
        JButton fullTimeButton = new JButton("정규직 등록");
        JButton partTimeButton = new JButton("임시직 등록");
        JButton backButton = new JButton("돌아가기");

        // 패널 설정 및 버튼 추가
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.add(fullTimeButton);
        panel.add(partTimeButton);
        panel.add(backButton);

        // 프레임에 패널 추가
        frame.getContentPane().add(panel);

        // 프레임을 보이게 설정
        frame.setVisible(true);

        // 버튼에 대한 액션 리스너 추가
        fullTimeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 정규직 등록 GUI 열기
                frame.setVisible(false);
                new RegisterFullTimeGUI(frame, employeeSet); // EmployeeSet 인스턴스 전달
            }
        });

        partTimeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 임시직 등록 GUI 열기
                frame.setVisible(false);
                new RegisterPartTimeGUI(frame, employeeSet); // EmployeeSet 인스턴스 전달
            }
        });

        backButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) {
                // 현재 창 닫고 부모 프레임 보이기
                frame.dispose();
                parentFrame.setVisible(true);
            }
        });
    }
}
