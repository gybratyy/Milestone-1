package utilpack;
import java.sql.*;
import java.util.Scanner;

import dbpack.*;

public class utilmeth implements utilinter {

    dbinter db = new dbmeth();
    Scanner scanner = new Scanner(System.in);
    boolean signedin = false;
    
/*

    int chold = 400043000;
*/


    @Override
    public void create(String name, String passw, int card) throws Exception {
        db.connect();
        String query = "insert into users(username,password,card_number,balance) values('" + name + "','" + passw + "','"+card+"',0);";
        db.execute(query);
        signedin = true;
        db.disconnect();

    }
    @Override
    public void myacc(int num) throws Exception{
        db.connect();
        String query = "select * from users where card_number='"+num+"'";
        ResultSet r = db.result(query);

        if(r.next()) {

            System.out.println("ID: " + r.getInt("id"));
            System.out.println("Username: " + r.getString("username"));
            System.out.println("Card Number: " + r.getInt("card_number"));
            System.out.println("Balance: " + r.getInt("balance"));

        }
        db.disconnect();
    }
    @Override
    public void signin(String name, String passw) throws SQLException {
        db.connect();
        String query = "select * from users where username='"+name+"'and password='"+passw+"';";
        ResultSet r = db.result(query);
        if(r.next()){
            System.out.println("Welcome, "+name);
            signedin = true;
        }
        else{
            System.out.println("Wrong username or password");
        }
        db.disconnect();
    }

    @Override
    public void depo(int num, int sum) throws Exception {
        db.connect();
        int current=0;
        String bal = "select balance from users where card_number='"+num+"';";
        ResultSet r = db.result(bal);
        if(r.next()){
        current = r.getInt("balance");
        }
        int fin = current+sum;
        String balup = "update users set balance='"+fin+"' where card_number='"+num+"';";
        db.execute(balup);
        db.disconnect();
    }
    @Override
    public void withdraw(int num,int sum) throws SQLException {
        db.connect();
        int current=0;
        String bal = "select balance from users where card_number='"+num+"';";
        ResultSet r = db.result(bal);
        if(r.next()){
            current = r.getInt("balance");
        }
        if(current==0 || current<sum){
            System.out.println("ERROR: Low balance");
        }
        else{
            int fin = current-sum;
            String baldown = "update users set balance='"+fin+"' where card_number='"+num+"';";
            db.execute(baldown);
        }
        db.disconnect();
    }
    @Override
    public void transfer(int num,int num1,int sum)throws Exception{
        //num1 = dest acc
        //num = current acc
        db.connect();
        String query = "select * from users where card_number='"+num1+"';";
        ResultSet r = db.result(query);
        if (r.next()){
            withdraw(num,sum);
            depo(num1,sum);
        }
        else{
            System.out.println("ERROR: User does not exist");
        }
        db.disconnect();
    }

    @Override
    public void delete(int num) throws Exception {
        db.connect();
        /*String paste = "insert into freeze select * from users where card_number='"+num+"';";

        db.execute(paste);
        db.execute(cut);
        db.disconnect();*/
        String getfromusers = "select * from users where card_number='"+num+"';";
        String addtofreeze = "";
        ResultSet r = db.result(getfromusers);
        if(r.next()){
            addtofreeze = "insert into freeze(id,username,password,card_number,balance) values('"+r.getInt("id")+"','"+r.getString("username")+"','"+r.getString("password")+"','"+r.getInt("card_number")+"','"+r.getInt("balance")+"');";
        }
        db.execute(addtofreeze);
        String cut = "delete from users where card_number='"+num+"';";
        db.execute(cut);
        db.disconnect();


    }
    @Override
    public void restore(int num) throws Exception{
        db.connect();

        db.disconnect();
    }

    @Override
    public void change_password(int num, String password) throws Exception {
        db.connect();
        String changep = "select password from users where card_number='"+num+"';";
        ResultSet r = db.result(changep);
        if(r.next()) {
            if (password.equals(r.getString("password"))) {
                System.out.println("Enter new password:");
                String newpassword = scanner.nextLine();
                String q = "update users set password='" + newpassword + "' where card_number='"+num+"';";
                db.execute(q);
            } else {
                System.out.println("ERROR: Wrong password");
            }
        }
        db.disconnect();

    }

    @Override
    public void change_username(int num, String name) throws Exception {
        db.connect();
        String changeu = "select username from users where card_number='"+num+"';";
        ResultSet r = db.result(changeu);
        if(r.next()){
            if(name.equals(r.getString("username"))){
                System.out.println("Enter new username:");
                String newusername = scanner.nextLine();
                String q = "update users set username='"+newusername+"' where card_number='"+num+"';";
                db.execute(q);
            }
        else{
            System.out.println("ERROR: Wrong username");
        }
        }
        db.disconnect();
    }

    @Override
    public int cardnumgen() throws Exception {
        db.connect();
        String query = "select card_number from users order by id desc limit 1;";
        ResultSet r =db.result(query);
        int c=0;
        if(r.next()){
            c=r.getInt("card_number");
        }
        return c;
    }
}
