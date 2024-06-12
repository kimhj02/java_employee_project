package employeeTest;

import javax.swing.*;
import employee.EmployeeSet; // EmployeeSet �겢�옒�뒪 媛��졇�삤湲�
import employee.FullTimeEmp;
import employee.DepartmentEnum; // DepartmentEnum �겢�옒�뒪 媛��졇�삤湲�
import employee.PositionEnum; // PositionEnum �겢�옒�뒪 媛��졇�삤湲�

public class RegisterFullTimeGUI extends JFrame 
{
    private JLabel nameLabel, departmentLabel, positionLabel, gradeLabel; // �씪踰� �꽑�뼵
    private JTextField nameField, gradeField; // �뀓�뒪�듃 �븘�뱶 �꽑�뼵
    private JComboBox<String> departmentComboBox, positionComboBox; // 肄ㅻ낫 諛뺤뒪 �꽑�뼵
    private JButton registerButton, backButton; // 踰꾪듉 �꽑�뼵

    private String[] departments = {"珥앸Т", "�씤�궗", "湲고쉷", "�깮�궛", "�쁺�뾽"}; // 遺��꽌 諛곗뿴
    private String[] positions = {"�궗�썝", "���由�", "遺��옣", "怨쇱옣", "�씠�궗"}; // 吏곸쐞 諛곗뿴
    private JFrame parentFrame; // 遺�紐� �봽�젅�엫 李몄“
    private EmployeeSet employeeSet; // EmployeeSet �씤�뒪�꽩�뒪 蹂��닔 異붽��

    public RegisterFullTimeGUI(JFrame parentFrame, EmployeeSet employeeSet) 
    {
        this.parentFrame = parentFrame;
        this.employeeSet = employeeSet;

        setTitle("�젙洹쒖쭅 吏곸썝 �벑濡�"); // �봽�젅�엫 �젣紐� �꽕�젙
        setSize(300, 250); // �봽�젅�엫 �겕湲� �꽕�젙
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 李� �떕湲� �꽕�젙
        setLayout(null); // �젅�씠�븘�썐 �꽕�젙

        nameLabel = new JLabel("�씠由�:"); // �씠由� �씪踰� �깮�꽦
        nameLabel.setBounds(20, 20, 80, 25); // �씪踰� �쐞移� 諛� �겕湲� �꽕�젙
        add(nameLabel); // �씪踰� 異붽��

        nameField = new JTextField(); // �씠由� �뀓�뒪�듃 �븘�뱶 �깮�꽦
        nameField.setBounds(100, 20, 160, 25); // �뀓�뒪�듃 �븘�뱶 �쐞移� 諛� �겕湲� �꽕�젙
        add(nameField); // �뀓�뒪�듃 �븘�뱶 異붽��

        departmentLabel = new JLabel("遺��꽌:"); // 遺��꽌 �씪踰� �깮�꽦
        departmentLabel.setBounds(20, 50, 80, 25); // �씪踰� �쐞移� 諛� �겕湲� �꽕�젙
        add(departmentLabel); // �씪踰� 異붽��

        departmentComboBox = new JComboBox<>(departments); // 遺��꽌 肄ㅻ낫 諛뺤뒪 �깮�꽦
        departmentComboBox.setBounds(100, 50, 160, 25); // 肄ㅻ낫 諛뺤뒪 �쐞移� 諛� �겕湲� �꽕�젙
        add(departmentComboBox); // 肄ㅻ낫 諛뺤뒪 異붽��

        positionLabel = new JLabel("吏곸쐞:"); // 吏곸쐞 �씪踰� �깮�꽦
        positionLabel.setBounds(20, 80, 80, 25); // �씪踰� �쐞移� 諛� �겕湲� �꽕�젙
        add(positionLabel); // �씪踰� 異붽��

        positionComboBox = new JComboBox<>(positions); // 吏곸쐞 肄ㅻ낫 諛뺤뒪 �깮�꽦
        positionComboBox.setBounds(100, 80, 160, 25); // 肄ㅻ낫 諛뺤뒪 �쐞移� 諛� �겕湲� �꽕�젙
        add(positionComboBox); // 肄ㅻ낫 諛뺤뒪 異붽��

        gradeLabel = new JLabel("�샇遊�:"); // �샇遊� �씪踰� �깮�꽦
        gradeLabel.setBounds(20, 110, 80, 25); // �씪踰� �쐞移� 諛� �겕湲� �꽕�젙
        add(gradeLabel); // �씪踰� 異붽��

        gradeField = new JTextField(); // �샇遊� �뀓�뒪�듃 �븘�뱶 �깮�꽦
        gradeField.setBounds(100, 110, 160, 25); // �뀓�뒪�듃 �븘�뱶 �쐞移� 諛� �겕湲� �꽕�젙
        add(gradeField); // �뀓�뒪�듃 �븘�뱶 異붽��

        registerButton = new JButton("�벑濡�"); // �벑濡� 踰꾪듉 �깮�꽦
        registerButton.setBounds(20, 150, 100, 25); // 踰꾪듉 �쐞移� 諛� �겕湲� �꽕�젙
        registerButton.addActionListener(e -> confirmEmployee()); // 踰꾪듉�뿉 �븸�뀡 由ъ뒪�꼫 異붽��
        add(registerButton); // 踰꾪듉 異붽��

        backButton = new JButton("�룎�븘媛�湲�"); // �룎�븘媛�湲� 踰꾪듉 �깮�꽦
        backButton.setBounds(170, 150, 100, 25); // 踰꾪듉 �쐞移� 諛� �겕湲� �꽕�젙
        backButton.addActionListener(e -> goBack()); // 踰꾪듉�뿉 �븸�뀡 由ъ뒪�꼫 異붽��
        add(backButton); // 踰꾪듉 異붽��

        setVisible(true); // �봽�젅�엫�쓣 蹂댁씠寃� �꽕�젙
    }

    private void confirmEmployee() 
    {
        String name = nameField.getText(); // �씠由� �븘�뱶 媛� 媛��졇�삤湲�
        String department = (String) departmentComboBox.getSelectedItem(); // �꽑�깮�븳 遺��꽌 媛� 媛��졇�삤湲�
        String position = (String) positionComboBox.getSelectedItem(); // �꽑�깮�븳 吏곸쐞 媛� 媛��졇�삤湲�
        String gradeStr = gradeField.getText(); // �샇遊� �븘�뱶 媛� 媛��졇�삤湲�
        int grade;

        try 
        {
            grade = Integer.parseInt(gradeStr); // �샇遊� �븘�뱶 媛� 蹂��솚
        } catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this, "�쑀�슚�븳 �샇遊� 媛믪쓣 �엯�젰�븯�꽭�슂.", "�엯�젰 �삤瑜�", JOptionPane.ERROR_MESSAGE); // �뿉�윭 硫붿떆吏� �몴�떆
            return;
        }

        String confirmMessage = String.format("�씠由�: %s\n遺��꽌: %s\n吏곸쐞: %s\n�샇遊�: %d\n\n�쐞�쓽 �젙蹂대줈 吏곸썝�쓣 �벑濡앺븯�떆寃좎뒿�땲源�?", name, department, position, grade);
        int response = JOptionPane.showConfirmDialog(this, confirmMessage, "吏곸썝 �젙蹂� �솗�씤", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) 
        {
            registerEmployee(name, department, position, grade);
        }
    }

    private void registerEmployee(String name, String department, String position, int grade) 
    {
        // Enum 媛� 蹂��솚�쓣 �쐞�빐 留듯븨
        DepartmentEnum departmentEnum;
        switch (department) 
        {
            case "珥앸Т":
                departmentEnum = DepartmentEnum.GENERAL_AFFAIR;
                break;
            case "�씤�궗":
                departmentEnum = DepartmentEnum.PERSONNEL;
                break;
            case "湲고쉷":
                departmentEnum = DepartmentEnum.PLANNING;
                break;
            case "�깮�궛":
                departmentEnum = DepartmentEnum.PRODUCTION;
                break;
            case "�쁺�뾽":
                departmentEnum = DepartmentEnum.SALES;
                break;
            default:
                JOptionPane.showMessageDialog(this, "�쑀�슚�븳 遺��꽌 媛믪쓣 �꽑�깮�븯�꽭�슂.", "�엯�젰 �삤瑜�", JOptionPane.ERROR_MESSAGE);
                return;
        }

        PositionEnum positionEnum;
        switch (position) 
        {
            case "�궗�썝":
                positionEnum = PositionEnum.STAFF;
                break;
            case "���由�":
                positionEnum = PositionEnum.ASSISTANT_MANAGER;
                break;
            case "遺��옣":
                positionEnum = PositionEnum.GENERAL_MANAGER;
                break;
            case "怨쇱옣":
                positionEnum = PositionEnum.MANAGER;
                break;
            case "�씠�궗":
                positionEnum = PositionEnum.DIRECTOR;
                break;
            default:
                JOptionPane.showMessageDialog(this, "�쑀�슚�븳 吏곸쐞 媛믪쓣 �꽑�깮�븯�꽭�슂.", "�엯�젰 �삤瑜�", JOptionPane.ERROR_MESSAGE);
                return;
        }
        FullTimeEmp newEmployee = new FullTimeEmp(employeeSet.full_id, name, departmentEnum, positionEnum, grade); // �깉濡쒖슫 �젙洹쒖쭅 吏곸썝 �깮�꽦
        employeeSet.getEmployees().add(newEmployee); // 吏곸썝 紐⑸줉�뿉 異붽��
        employeeSet.full_id++; // �젙洹쒖쭅 吏곸썝 ID 利앷��

        JOptionPane.showMessageDialog(this, "吏곸썝 �벑濡앹씠 �셿猷뚮릺�뿀�뒿�땲�떎.", "�벑濡� �꽦怨�", JOptionPane.INFORMATION_MESSAGE); // �꽦怨� 硫붿떆吏� �몴�떆

        // �엯�젰 �븘�뱶 珥덇린�솕
        nameField.setText("");
        departmentComboBox.setSelectedIndex(0);
        positionComboBox.setSelectedIndex(0);
        gradeField.setText("");
    }

    private void goBack() 
    {
        dispose(); // �쁽�옱 李� �떕湲�
        parentFrame.setVisible(true); // 遺�紐� �봽�젅�엫 蹂댁씠湲�
    }
}
