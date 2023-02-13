
/*
4.	Online bank management system
        The project would be performing the following operations:
        1.	Creation of the new accounts
        2.	Login, Logout
        3.	Manage Profile and Password
        4.	View the balance of the account
        5.	Deposit the amount
        6.	Withdraw amount
        7.	Transfer the amount
        8.	Close account
*/


import java.util.Scanner;
import utilpack.*;

public class bank_system {
     static utilinter foo = new utilmeth();
     static int chold;

     static {
         try {
             chold = foo.cardnumgen();
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }

     static int num;




    static Scanner scanner = new Scanner(System.in);


    public static void create_user() throws Exception{
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter passoword:");
        String password = scanner.nextLine();
       /* num = cardholder.givenum(username,password);*/
        foo.create(username,password,chold+1);

        num = cardnumHolder.getCardNum(username,password);

    }
    public static void login() throws Exception{
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter passoword:");
        String password = scanner.nextLine();
        foo.signin(username,password);
        num = cardnumHolder.getCardNum(username,password);


    }
    public static void manage() throws Exception{
        System.out.println("1. Username  |  2. Password");
        int m = scanner.nextInt();
        scanner.nextLine();
        if(m==1){
            System.out.println("Enter current username:");
            String cname = scanner.nextLine();
            foo.change_username(num,cname);
        }
        else{
            System.out.println("Enter current password:");
            String cpass = scanner.nextLine();
            foo.change_password(num,cpass);
        }
    }
    public static void my_account()throws Exception{
        foo.myacc(num);
    }
    public static void deposit()throws Exception{
        System.out.println("Enter amount you want to deposit:");
        int sum = scanner.nextInt();
        scanner.nextLine();
        foo.depo(num,sum);
    }
    public static void withdraw()throws Exception{
        System.out.println("Enter amount you want to withdraw:");
        int sum = scanner.nextInt();
        scanner.nextLine();
        foo.withdraw(num,sum);

    }
    public static void transfer() throws Exception{
        System.out.println("Enter requisites:");
        int rec = scanner.nextInt();

        System.out.println("Enter amount of money:");
        int sum = scanner.nextInt();
        scanner.nextLine();
        foo.transfer(num,rec,sum);


    }
    public static void close_account() throws Exception{
        foo.delete(num);
    }
    public static void restore_account() throws Exception{
        foo.restore(num);
    }

}