package JavaIndividual;

import com.mysql.cj.util.StringInspector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

class MallManagementSystem{
    class Node{
        String name;
        Node next;
        Node(String name){
            this.name = name;
            this.next = null;
        }
    }
    Node Head;
    void getRentedShopName() throws SQLException {
        String sql = "select shopName from rentedShop";
        ResultSet rs = Main.st.executeQuery(sql);
        while (rs.next()){
            add(rs.getString(1));
        }
        displayRentedShop(Head);
    }
    void add(String name){
        Node newNode = new Node(name);
        if(Head==null){
            Head = newNode;
        }else {
            newNode.next = Head;
            Head = newNode;
        }
    }
    void displayRentedShop(Node Head){
        Node temp = Head;
        while (temp!=null){
            System.out.println("Shop Name : "+temp.name);
            temp = temp.next;
        }
    }

    static Scanner sc = new Scanner(System.in);

    String ShopRenterName;
    long ShopRenterMobileNo;
    String ShopRenterGovernmentIdentityNo;
    String ShopName;
    String ShopType;
    String ShopLocationType;
    String ShopSize;
    int ShopNumber;
    double ShopRent;
    double ShopDeposit;

    public static MallManagementSystem arr[][] = new MallManagementSystem[5][];

    public static void RentAShop() throws SQLException {


//        sc.nextLine();
        System.out.print("Enter Your Name : ");
        String name = sc.nextLine();

        //Mobile Number Verification
        long mobileNumber = 0;
        while (true)
        try {
            if(mobileNumber / 1000000000 < 6){
                System.out.print("Enter Your Mobile Number : ");
                mobileNumber = sc.nextLong();
                break;
            }else {
                System.out.println("Enter Only 10 Digit");
            }
        }catch (Exception e){
            System.out.println("Enter Valid Digit");
        }


        //PAN Card Verification

        String GovernmentIdentityNo = "";
        boolean flag = false;
        while(flag==false) {
            boolean flag1 = false;
            System.out.print("Enter Your Pan Number : ");
            GovernmentIdentityNo = sc.next();
            if(GovernmentIdentityNo.length()==10) {
                for (int i = 0; i < 5; i++) {
                    if (GovernmentIdentityNo.charAt(i) >= 'A' && GovernmentIdentityNo.charAt(i) <= 'Z') {
                        flag1 = true;
                    }
                    else{
                        flag1 = false;
                    }
                }
                if (flag1 == false) {
                    System.out.println("PAN number first  Five Character Are Contain Capital Later");

                }

                boolean flag2 = false;
                for (int i = 5; i < 9; i++) {
                    if (GovernmentIdentityNo.charAt(i) - 48 >= 0 && GovernmentIdentityNo.charAt(i) - 48 <= 9) {
                        flag2 = true;
                    }
                    else{
                        flag2 = false;
                    }
                }
                if (flag2 == false) {
                    System.out.println("PAN Numbers 5 To 9 Character must Between 0 To 9");
                }
                boolean flag3 = false;
                if (GovernmentIdentityNo.charAt(9) >= 'A' && GovernmentIdentityNo.charAt(9) <= 'Z') {
                    flag3 = true;
                }
                else{
                    flag3 = false;
                }

                if (flag1 == flag2 == flag3 == true) {
                    flag = true;
                } else {
                    System.out.println("Enter Pan Number Again!!!");
                }
            }
            else{
                System.out.println("PAN Number Must BE Equal To 10 Character!!!");
            }
        }

        //Shop Name Verification
//        sc.nextLine();
        String SName = sc.nextLine();
        boolean flag0 = false;
        while (flag0==false){
            System.out.print("Enter Your Shop Name : ");
            try {
                SName=sc.nextLine();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            char c[] = new char[SName.length()];
            for(int j=0; j<c.length; j++){
                c[j] = SName.charAt(j);
            }
            for(int i=0; i<c.length; i++){
                if((c[i]>='a'&&c[i]<='z')  || (c[i]>='A'&&c[i]<='Z')){
                    flag0 = true;
                }else {
                    flag0 = false;
                    break;
                }
            }
            if(flag0==false){
                System.out.println("Please Enter Only Character");
            }
        }

        //Shop Type
        boolean flag1 = false;
        int type = 5;
        while (flag1==false) {
            System.out.println("Enter Shop Type");
            System.out.println("Press 1 For Novelty Shop");
            System.out.println("Press 2 For Women's Wear Shop");
            System.out.println("Press 3 For Men's Wear Shop");
            System.out.println("Press 4 For Food Shop");
            System.out.println("Press 5 For Other");
            try {
                type = sc.nextInt();
            }catch (Exception e){
                System.out.println("Enter Only Digit!!!");
            }
            if(type>=1 && type<=5){
                flag1 = true;
            }else {
                System.out.println("Please Choose Valid Number Between 1 to 5");
            }
        }

        //Shop Location Type
        boolean flag2 = false;
        int L = 2;
        while (flag2==false) {
            System.out.println("Enter Shop Location Type Details");
            System.out.println("Press 1 For Prime Location");
            System.out.println("Press 2 For Regular Location");
            try {
                L = sc.nextInt();
            }catch (Exception e){
            }
            if (L>=1 && L<=2){
                flag2 = true;
            }else {
                System.out.println("Please Choose 1 or 2");

            }
        }

        boolean flag3 = false;
        int size = 1;
        while (flag3 == false) {
            System.out.println("Enter Shop Size Details");
            System.out.println("Press 1 To Get Medium Size Shop");
            System.out.println("Press 2 To Get Large Size Shop");
            System.out.println("Press 3 To Get Extra Large Size Shop");
            try {
                size = sc.nextInt();
            }catch (Exception e){
                System.out.println("Please Choose Valid Between 1 to 3");
            }
            if(size>=1 && size<=3){
                flag3 = true;
            }
        }
        ShopManagementSystem s = new ShopManagementSystem();
        s.GetAShopForRent(type, L, size, name,mobileNumber, GovernmentIdentityNo, SName);
    }


    //Display Shop Details
    public static void getShopDetailsFromDatabase(int shopNumber) throws SQLException {
        String sql = "select * from rentedShop";
        PreparedStatement pst = Main.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        boolean found = false;
        while (rs.next()){
            if(rs.getInt(1)==shopNumber) {
                int shopNo = rs.getInt(1);
                String shopName = rs.getString(2);
                String renterName = rs.getString(3);
                String renterMobileNo = rs.getString(4);
                String renterPANNo = rs.getString(5);
                String locationType = rs.getString(6);
                String size = rs.getString(7);
                String shopCategory = rs.getString(8);
                double rent = rs.getDouble(9);

                System.out.println();
                System.out.println("**********************************************************");
                System.out.println("Details of Shop Number " + shopNumber);
                System.out.println();
                System.out.println("Renter Name : " + renterName);
                System.out.println("Renter Mobile Number : " + renterMobileNo);
                System.out.println("Shop Name : " + shopName);
                System.out.println("Renter Government Identity Number : " + renterPANNo);
                System.out.println("Shop Category : " + shopCategory);
                System.out.println("Location Type : " + locationType);
                System.out.println("Shop Size : " + size);
                System.out.println("Shop Rent : Rs." + rent);

                System.out.println("**********************************************************");
                System.out.println();
                found = true;
                break;
            }
        }
        if(found==false){
            System.out.println("**********************************************************");
            System.out.println(shopNumber+" is Not On Rent.");
            System.out.println("**********************************************************");
            System.out.println();
        }
    }


    //Display Floor Details
    public static void DisplayFloorDetailsOrderByRent(int floor) throws SQLException {
        System.out.println("**********************************************************");
        String sql = "select * from rentedShop Order by Rent";
        PreparedStatement pst = Main.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()){
            if(floor==rs.getInt(1)/100) {
                System.out.println();
                System.out.println("Shop No. = " + rs.getInt(1));
                System.out.println("Rent = Rs." + rs.getDouble(9));
                System.out.println();
            }
        }
        System.out.println("**********************************************************");
    }

    public static void listOfNonRentedShopOfParticularFloor(int floor) throws SQLException {
        System.out.println("************************************************************");
        if(floor>=0 && floor<=4) {
            String sql = "select * from nonRentedShop";
            PreparedStatement pst = Main.con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) / 100 == floor)
                    System.out.println(rs.getInt(1) + " is Not Rented");
            }
        }else {
            System.out.println("Invalid Floor Number");
            System.out.println("!!!!!!!!!!!!!!Floor Number is Must Be In 0 to 4 !!!!!!!!!!!!!!");
        }
        System.out.println("************************************************************");
    }

    public static void listOfRentedShopOfParticularFloor(int floor) throws SQLException {
        System.out.println("************************************************************");
        if(floor>=0 && floor<=4) {
            String sql = "select * from rentedShop";
            PreparedStatement pst = Main.con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) / 100 == floor)
                    System.out.println(rs.getInt(1) + " is Rented");
            }
        }else {
            System.out.println("Invalid Floor Number");
            System.out.println("!!!!!!!!!!!!!!Floor Number is Must Be In 0 to 4 !!!!!!!!!!!!!!");
        }
        System.out.println("************************************************************");
    }

    public static void withdrawShopFromRent(int shopNo) throws SQLException {
        String sql = "delete from rentedShop where shopNo = "+shopNo;
        int r = Main.st.executeUpdate(sql);
        if(r>0){
            System.out.println();
            System.out.println("Shop Successfully withdraw From Rent");
            System.out.println();
        }else {
            System.out.println();
            System.out.println("!!!!Failed To Withdraw Shop or Invalid Shop Number!!!!");
            System.out.println();
        }
    }

    public static void insertIntoNonRentedShop() throws SQLException {
        for(int i=0; i<=4; i++){
            for(int j=1; j<=15; j++){
                int shopNo = i*100 + j;
                String sql = "insert into nonRentedShop(shopNo) values(400) ";
                PreparedStatement pst = Main.con.prepareStatement(sql);
                pst.setInt(1,shopNo);
                pst.execute();
            }
        }
    }
}
