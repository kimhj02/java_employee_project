package employeeTest;

import javax.swing.*;

import employee.EmployeeSet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployManagementSystemUpdateGUI 
{

    private JFrame parentFrame;

    public EmployManagementSystemUpdateGUI(JFrame parentFrame, EmployeeSet employeeSet) 
    {
        this.parentFrame = parentFrame;

        // 새 프레임 생성
        JFrame frame = new JFrame("정보 갱신 시스템");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 현재 창만 닫기
        frame.setSize(300, 200);

        // 버튼 생성
        JButton updateInfoButton = new JButton("정보 갱신 (by id)");
        JButton backButton = new JButton("돌아가기");

        // 패널 설정 및 버튼 추가
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1,10, 10));
        panel.add(updateInfoButton);
        panel.add(backButton);

        // 프레임에 패널 추가
        frame.getContentPane().add(panel);

        // 프레임을 보이게 설정
        frame.setVisible(true);

        // 버튼에 대한 액션 리스너 추가
        updateInfoButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                new UpdatebyidGUI(frame, employeeSet);
                frame.setVisible(false);
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                // 현재 창 닫고 부모 프레임 보이기
                frame.dispose();
                parentFrame.setVisible(true);
            }
        });
    }
}