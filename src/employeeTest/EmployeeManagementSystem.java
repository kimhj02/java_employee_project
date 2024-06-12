/*package employeeTest;

import java.util.Scanner;
import menus.MainMenuEnum;
import menus.Menu;

public class EmployeeManagementSystem 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();

        while (true) 
        {
            MainMenuEnum selectedMainMenu = menu.mainMenu(scanner);

            switch (selectedMainMenu) 
            {
                case REGISTER:
                    menu.registerMenu(scanner);
                    break;
                case SEARCH:
                    menu.searchMenu(scanner);
                    break;
                case UPDATE:
                    menu.updateMenu(scanner);
                    break;
                case DELETE:
                    menu.deleteMenu(scanner);
                    break;
                case EXIT:
                    System.out.println("good bye~");
                    scanner.close();
                    return; 
            }
        }
    }
}
*/