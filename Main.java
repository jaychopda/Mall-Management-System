package JavaIndividual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static Connection con;
    static Statement st;
    public static void main(String args[]) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MallManagementSystemDB","root","");

        if(con!=null){
            System.out.println("Database Connected Successfully");
        }else {
            System.out.println("Database Connection Failed!!");
        }

        st = con.createStatement();


        Scanner sc = new Scanner(System.in);
        MallManagementSystem m = new MallManagementSystem();

        for(int i=0; i<m.arr.length; i++){
            MallManagementSystem.arr[i] = new MallManagementSystem[16];
        }

//        ShopManagementSystem.DefineAllShop();
//        MallManagementSystem.insertIntoNonRentedShop();


        //declare MallManagement array for all shop
        for(int i=0; i<MallManagementSystem.arr.length; i++){
            for(int j=0; j<MallManagementSystem.arr[i].length; j++){
                MallManagementSystem.arr[i][j] = new MallManagementSystem();
            }
        }

        System.out.println("********************************************Welcome To Our Software********************************************");
        System.out.println();



        int choice = 50;
        do {
            System.out.println("************************************************************");
            System.out.println("Press 1 for To Take Shop For Rent");
            System.out.println("Press 2 for View Particular Shop Details");
            System.out.println("Press 3 TO Get List Of Non-Rented Shop Of Particular Floor");
            System.out.println("Press 4 TO Get List Of Rented Shop Of Particular Floor");
            System.out.println("Press 5 For Get Floor Details Order By Rent");
            System.out.println("Press 6 To Withdraw Shop From Rent");
            System.out.println("Press 7 for View All Shop Name");
            System.out.println("Press 0 For Exit");
            System.out.println("************************************************************");
            System.out.println();
            System.out.print("Enter Your Choice : ");
            try {
                choice = sc.nextInt();
            }catch (Exception e){
                System.out.println("Enter Only Character!!!");
            }


            switch (choice) {
                case 1:
                    MallManagementSystem.RentAShop();
                    break;

                case 2:
                    System.out.println("Enter Shop Number To Get Details : ");
                    int SNumber = sc.nextInt();
                    MallManagementSystem.getShopDetailsFromDatabase(SNumber);
                    break;

                case 3:
                    System.out.println("Enter Floor Number : ");
                    int floorNo = sc.nextInt();
                    MallManagementSystem.listOfNonRentedShopOfParticularFloor(floorNo);
                    break;

                case 4:
                    System.out.println("Enter Floor Number : ");
                    int floorNumber = sc.nextInt();
                    MallManagementSystem.listOfRentedShopOfParticularFloor(floorNumber);
                    break;

                case 5:
                    System.out.println("Enter Floor Number : ");
                    int floorNum = sc.nextInt();
                    MallManagementSystem.DisplayFloorDetailsOrderByRent(floorNum);
                    break;

                case 6:
                    int shopNo = 0;
                    boolean flagx = false;
                    while (flagx==false) {
                        try {
                            System.out.print("Enter Shop No To Withdraw From Rent : ");
                            shopNo = sc.nextInt();
                            flagx = true;
                        } catch (Exception e) {
                            System.out.println("Enter Only Digit Value");
                        }
                        sc.nextLine();
                    }

                    MallManagementSystem.withdrawShopFromRent(shopNo);
                    break;

                case 7:
                    m.getRentedShopName();
                    break;

                case 0:
                    System.out.println("Thank You For Visit Our Software");
                    return;

                default:
                    System.out.println("Invalid Input!!!");
            }
        }while(choice!=0);
    }
}