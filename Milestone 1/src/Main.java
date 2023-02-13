// last best ver: 10:20
import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import utilpack.*;
import dbpack.*;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {


        int c;
        do{
            System.out.println("1. Sign in | 2. Register | 3. Close");
            c = scanner.nextInt();
            switch (c){
                case 1:
                    bank_system.login();
                    menu();
                    break;
                case 2:
                    bank_system.create_user();
                    menu();
                    break;
            }
            scanner.nextLine();
        }while (c!=3);

    }
    public static void menu() throws Exception {

        int ch;

        do{
            System.out.println("1. My account");
            System.out.println("2. Manage Profile and Password");
            System.out.println("3. Deposit the amount");
            System.out.println("4. Withdraw amount");
            System.out.println("5. Transfer the amount");
            System.out.println("6. Close account");
            System.out.println("7. Log out");
            System.out.println("8. Exit");


            ch = scanner.nextInt();

            switch (ch){
                case 1: bank_system.my_account();break;
                case 2: bank_system.manage();break;
                case 3: bank_system.deposit();break;
                case 4: bank_system.withdraw();break;
                case 5: bank_system.transfer();break;
                case 6: bank_system.close_account();break;
                case 7: break;

            }
            scanner.nextLine();
        }while(ch!=8);
    }


}