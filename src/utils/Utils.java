package utils;
// Utils class
import java.util.InputMismatchException;
import java.util.Scanner;

import employee.DepartmentEnum;
import employee.PositionEnum;

public class Utils 
{
    static public int checkInputInteger(Scanner s, String msg) 
	{
        int n=0;
        boolean valid;
        do {
            valid = true;
            System.out.print(msg);

            try {
                n = s.nextInt();
            } catch(InputMismatchException e) 
			{
                System.out.println("정수 입력하세요.");
                s.next();
                valid = false;
            }
        } while(!valid);
        
        return n;
    }
    public static int getID(Scanner s) 
    {
        return checkInputInteger(s, ">> id: ");
    }

    public static String getName(Scanner s) 
    {
        System.out.print(">> name: ");
        return s.next();
    }

    public static DepartmentEnum getDepartment(Scanner s) { //부서 입력
        int dept;
        DepartmentEnum department = null;
        while (true) 
        {
            System.out.print("== 부서 목록: ");

            for (DepartmentEnum d : DepartmentEnum.values()) 
            {
                System.out.print(d.getMessage() + "(" + d.getCode() + ") ");
            }
            
            System.out.println("");
            dept = checkInputInteger(s, ">> department: ");

            boolean valid = false;
            for (DepartmentEnum d : DepartmentEnum.values()) 
            {
                if (dept == d.getCode()) {
                    valid = true;
                    department = d;
                    break;
                }
            }

            if (valid) 
            {
                break;
            }
        }
        return department;
    }

    public static PositionEnum getPosition(Scanner s) { //직위 입력
        int pos;
        PositionEnum position = null;
        while (true) {
            System.out.print("== 직위 목록: ");
            for (PositionEnum p : PositionEnum.values()) {
                System.out.print(p.name() + "(" + p.getCode() + ") ");
            }
            System.out.println("");
            pos = checkInputInteger(s, ">> position: ");

            boolean valid = false;
            for (PositionEnum p : PositionEnum.values()) {
                if (pos == p.getCode()) {
                    valid = true;
                    position = p;
                    break;
                }
            }

            if (valid) {
                break;
            }
        }
        return position;
    }

    public static int getGrade(Scanner s) 
    {
        return checkInputInteger(s, ">> grade: ");
    }
    public static int getHours(Scanner s) 
    {
        return checkInputInteger(s, ">> hours: ");
    }
}