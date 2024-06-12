package employeeTest;

import javax.swing.*;

import employee.EmployeeSet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployManagementSystemDeleteGUI 
{
    
    private JFrame parentFrame;

    public EmployManagementSystemDeleteGUI(JFrame parentFrame, EmployeeSet employeeSet) 
    {
        this.parentFrame = parentFrame;

        // 새 프레임 생성
        JFrame frame = new JFrame("직원 정보 삭제 시스템");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 현재 창만 닫기
        frame.setSize(300, 200);
        
        // 버튼 생성
        JButton deleteInfoButton = new JButton("직원 정보 삭제 (by id)");
        JButton backButton = new JButton("돌아가기");

        // 패널 설정 및 버튼 추가
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1,10, 10 ));
        panel.add(deleteInfoButton);
        panel.add(backButton);

        // 프레임에 패널 추가
        frame.getContentPane().add(panel);

        // 프레임을 보이게 설정
        frame.setVisible(true);

        // 버튼에 대한 액션 리스너 추가
        deleteInfoButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                // ID에 따른 정보 삭제 작업 수행
                frame.setVisible(false);
                new DeleteGUI(frame, employeeSet); // EmployeeSet 인스턴스 전달
            }
        });

        backButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                // 현재 창 닫고 부모 프레임 보이기
                frame.dispose();
                parentFrame.setVisible(true);
            }
        });
    }
}
