package menus;

import java.util.Scanner;
import utils.Utils;
import employee.*;

public class Menu //메인 메뉴 main에서 해당 class를 호출하여 사용함
{
    EmployeeSet set = new EmployeeSet(); // EmployeeSet 객체 생성

    public MainMenuEnum mainMenu(Scanner s)
    {
        System.out.println("");
        System.out.println("======== 메인 메뉴 ===");


        //메인 메뉴 포시
        for (MainMenuEnum m : MainMenuEnum.values()) 
        {
            System.out.println("  " + m.getCode() + ". " + m.getMessage());
        }

        int n;
        MainMenuEnum select = null;

        while (true) 
        {
            n = Utils.checkInputInteger(s, "선택>>> ");

            // 유효한 값 입력 확인
            boolean valid = false;
            // 입력이 올바른지 확인하고 해당하는 메뉴를 선택
            for (MainMenuEnum m : MainMenuEnum.values()) 
            {
                if (n == m.getCode()) {
                    valid = true;
                    select = m;
                    break;
                }
            }
            if (valid) 
            {
                break;
            }
            System.out.println("Invalid Input. Try again");
        }

        return select;
    }

    public void registerMenu(Scanner s) //직원 등록 메뉴 
    {
        RegisterMenuEnum registerMenuEnum = null;
        do {
            System.out.println("");
            System.out.println("======== 등록 메뉴 ===");

            //등록 메뉴 표시
            for (RegisterMenuEnum m : RegisterMenuEnum.values()) 
            {
                System.out.println("  " + m.getCode() + ". " + m.getMessage());
            }

            int n;
            while (true) 
            {
                n = Utils.checkInputInteger(s, "선택>>> ");

                boolean valid = false;
                for (RegisterMenuEnum m : RegisterMenuEnum.values())
                {
                    if (n == m.getCode()) {
                        valid = true;
                        registerMenuEnum = m;
                        break;
                    }
                }

                if (valid) 
                {
                    break;
                }
                System.out.println("Invalid Input. Try again");
            }

            // 선택한 등록 메뉴 처리
            switch (registerMenuEnum) 
            {
                case REGISTER_FULL_TIME:
                    set.RegisterFullTimeEmp(s);
                    break;
                case REGISTER_PART_TIME:
                    set.RegisterPartTimeEmp(s);
                    break;
                case REGISTER_CANCEL:
                    break;
            }
        } while (registerMenuEnum != RegisterMenuEnum.REGISTER_CANCEL);
    }

    public void searchMenu(Scanner s) //직원 검색 메뉴
    {
        SearchMenuEnum searchMenuEnum = null;
        do {
            System.out.println("");
            System.out.println("======== 검색 메뉴 ===");

            for (SearchMenuEnum m : SearchMenuEnum.values()) {
                System.out.println("  " + m.getCode() + ". " + m.getMessage());
            }

            int n;
            while (true) {
                n = Utils.checkInputInteger(s, "선택>>> ");

                boolean valid = false;
                for (SearchMenuEnum m : SearchMenuEnum.values()) {
                    if (n == m.getCode()) {
                        valid = true;
                        searchMenuEnum = m;
                        break;
                    }
                }
                if (valid) 
                {
                    break;
                }
                System.out.println("Invalid Input. Try again");
            }

            // 선택한 검색 메뉴 처리
            switch (searchMenuEnum) 
            {
                case SEARCH_ALL:
                    System.out.println(" >>> 일괄 검색 <<<");
                    set.searchAll();
                    break;
                case SEARCH_FULLTIME:
                    System.out.println(" >>> 정규직 일괄 검색 <<<");
                    set.searchFullTime();
                    break;
                case SEARCH_PARTIME:
                    System.out.println(" >>> 임시직 일괄 검색 <<<");
                    set.searchPartTime();
                    break;
                case SEARCH_NAME:
                    System.out.println(" >>> 이름 검색 <<<");
                    set.searchByName(s);
                    break;
                case SEARCH_CANCEL:
                    break;
            }
        } while (searchMenuEnum != SearchMenuEnum.SEARCH_CANCEL);
    }

    public void updateMenu(Scanner s) //직원 갱신 메뉴
    {
        UpdateMenuEnum updateMenuEnum = null;
        do {
            System.out.println("");
            System.out.println("======== 정보 갱신 메뉴 ===");

            for (UpdateMenuEnum m : UpdateMenuEnum.values()) { //추력
                System.out.println("  " + m.getCode() + ". " + m.getMessage());
            }

            int n;
            while (true) //선택
            {
                n = Utils.checkInputInteger(s, "선택>>> ");

                boolean valid = false;
                for (UpdateMenuEnum m : UpdateMenuEnum.values()) {
                    if (n == m.getCode()) {
                        valid = true;
                        updateMenuEnum = m;
                        break;
                    }
                }
                if (valid) {
                    break;
                }
                System.out.println("Invalid Input. Try again");
            }
            
            switch (updateMenuEnum) //선택에 따른 기능 수행
            {
                case IMPORT_UPDATE:
                    System.out.println(" >>> 정보 갱신 <<<");
                    set.updateEmployee(s);
                    break;
                case UPDATE_CANCEL:
                    break;
            }
        } while (updateMenuEnum != UpdateMenuEnum.UPDATE_CANCEL);
    }

    public void deleteMenu(Scanner s) //직원 삭제 메뉴
    {
        DeleteMenuEnum deleteMenuEnum = null;
        do {
            System.out.println("");
            System.out.println("======== 삭제 메뉴 ===");

            //삭제 메뉴 표시
            for (DeleteMenuEnum m : DeleteMenuEnum.values()) 
            {
                System.out.println("  " + m.getCode() + ". " + m.getMessage());
            }

            int n;
            while (true) 
            {
                n = Utils.checkInputInteger(s, "선택>>> ");

                boolean valid = false;
                for (DeleteMenuEnum m : DeleteMenuEnum.values()) 
                {
                    if (n == m.getCode()) 
                    {
                        valid = true;
                        deleteMenuEnum = m;
                        break;
                    }
                }
                if (valid) 
                {
                    break;
                }
                System.out.println("Invalid Input. Try again");
            }

            // 선택한 삭제 메뉴 처리
            switch (deleteMenuEnum) 
            {
                case DELETE_FUN:
                    System.out.println(" >>> 직원 정보 삭제 <<<");
                    set.deleteEmployeeById(s);
                    break;
                case DELETE_CANCEL:
                    break;
            }
        } while (deleteMenuEnum != DeleteMenuEnum.DELETE_CANCEL);
    }

}