package JavaIndividual;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Collections;

class ShopManagementSystem extends MallManagementSystem{

    int MediumSizePrimeLocationShop;
    int MediumSizeRegularLocationShop;
    int LargeSizePrimeLocationShop;
    int LargeSizeRegularLocationShop;
    int ExtraLargeSizePrimeLocationShop;
    int ExtraLargeSizeRegularLocationShop;

    static ShopManagementSystem Floor[] = new ShopManagementSystem[5];

    String SetShopType(int a){
        String s;
        if(a==1){
            s = "Novelty Shop";
        }
        else if(a==2) {
            s = "Women's Wear";
        }
        else if(a==3){
            s = "Men's Wear";
        }
        else if(a==4){
            s = "Food Shop";
        }
        else{
            s = "Other";
        }
        return s;
    }

    String SetShopLocation(int a){
        String s = "";

        if(a==1){
            s = "Prime Location";
        }
        else{
            s = "Regular Location";
        }
        return s;
    }

    String SetShopSize(int a){
        String s = "";

        if(a==1){
            s = "Medium Size Shop";
        }
        else if(a==2){
            s = "Large Size Shop";
        }
        else if(a==3){
            s = "Extra Large Size Shop";
        }
        return s;
    }

    static void DefineAllShop() throws SQLException {
        for(int i=0; i<Floor.length; i++){
            Floor[i] = new ShopManagementSystem();
        }

        for(int i=0; i<Floor.length; i++){
            Floor[i].MediumSizePrimeLocationShop = 3;
            Floor[i].MediumSizeRegularLocationShop = 4;
            Floor[i].LargeSizePrimeLocationShop = 2;
            Floor[i].LargeSizeRegularLocationShop = 3;
            Floor[i].ExtraLargeSizePrimeLocationShop = 1;
            Floor[i].ExtraLargeSizeRegularLocationShop = 2;
            String sql = "insert into ShopManagement values(?,?,?,?,?,?,?)";
            PreparedStatement pst = Main.con.prepareStatement(sql);
            pst.setInt(1,i);
            pst.setInt(2,3);
            pst.setInt(3,4);
            pst.setInt(4,2);
            pst.setInt(5,3);
            pst.setInt(6,1);
            pst.setInt(7,1);
            pst.execute();
        }
    }



    void GetAShopForRent(int type, int Ltype, int size, String name, long mobileNumber, String GovernmentIdentityNo, String SName) throws SQLException {

        int floor = type-1;
        int ShopNumber;
        int SNumber = 0;
        boolean youGetShop = false;

        if(Ltype == 1 && size == 1){
            String sql = "select MSPLS from ShopManagement where floor = ?";
            PreparedStatement pst = Main.con.prepareStatement(sql);
            pst.setInt(1,floor);
            ResultSet rs = pst.executeQuery();
            int MSPLS = 0;
            while (rs.next()){
                MSPLS = rs.getInt(1);
            }
            if(MSPLS>0){
                SNumber = MSPLS;
                MSPLS = MSPLS - 1;
                youGetShop = true;
                String sql1 = "update ShopManagement set MSPLS = "+MSPLS+" where floor = "+floor;
                Main.st.execute(sql1);
            }
            else{
                System.out.println("Sorry....Shop Is Not Available!!!");
            }
        }

        else if(Ltype == 2 && size == 1){
            String sql = "select MSRLS from ShopManagement where floor = ?";
            PreparedStatement pst = Main.con.prepareStatement(sql);
            pst.setInt(1,floor);
            ResultSet rs = pst.executeQuery();
            int MSRLS = 0;
            while (rs.next()){
                MSRLS = rs.getInt(1);
            }
            if(MSRLS>0){
                SNumber = MSRLS;
                MSRLS = MSRLS - 1;
                youGetShop = true;
                String sql1 = "update ShopManagement set MSRLS = "+MSRLS+" where floor = "+floor;
                Main.st.execute(sql1);
            }
            else{
                System.out.println("Sorry....Shop Is Not Available!!!");
            }
        }

        else if(Ltype == 1 && size == 2){
            String sql = "select LSPLS from ShopManagement where floor = ?";
            PreparedStatement pst = Main.con.prepareStatement(sql);
            pst.setInt(1,floor);
            ResultSet rs = pst.executeQuery();
            int LSPLS = 0;
            while (rs.next()){
                LSPLS = rs.getInt(1);
            }
            if(LSPLS>0){
                SNumber = LSPLS;
                LSPLS = LSPLS - 1;
                youGetShop = true;
                String sql1 = "update ShopManagement set LSPLS = "+LSPLS+" where floor = "+floor;
                Main.st.execute(sql1);
            }
            else{
                System.out.println("Sorry....Shop Is Not Available!!!");
            }
        }

        else if(Ltype == 2 && size == 2){
            String sql = "select LSRLS from ShopManagement where floor = ?";
            PreparedStatement pst = Main.con.prepareStatement(sql);
            pst.setInt(1,floor);
            ResultSet rs = pst.executeQuery();
            int LSRLS = 0;
            while (rs.next()){
                LSRLS = rs.getInt(1);
            }
            if(LSRLS>0){
                SNumber = LSRLS;
                LSRLS = LSRLS - 1;
                youGetShop = true;
                String sql1 = "update ShopManagement set LSRLS = "+LSRLS+" where floor = "+floor;
                Main.st.execute(sql1);
            }
            else{
                System.out.println("Sorry....Shop Is Not Available!!!");
            }
        }

        else if(Ltype == 1 && size == 3){
            String sql = "select ELSPLS from ShopManagement where floor = ?";
            PreparedStatement pst = Main.con.prepareStatement(sql);
            pst.setInt(1,floor);
            ResultSet rs = pst.executeQuery();
            int ELSPLS = 0;
            while (rs.next()){
                ELSPLS = rs.getInt(1);
            }
            if(ELSPLS>0){
                SNumber = ELSPLS;
                ELSPLS = ELSPLS - 1;
                youGetShop = true;
                String sql1 = "update ShopManagement set ELSPLS = "+ELSPLS+" where floor = "+floor;
                Main.st.execute(sql1);
            }
            else{
                System.out.println("Sorry....Shop Is Not Available!!!");
            }
        }

        else if(Ltype == 2 && size == 3){
            String sql = "select ELSRLS from ShopManagement where floor = ?";
            PreparedStatement pst = Main.con.prepareStatement(sql);
            pst.setInt(1,floor);
            ResultSet rs = pst.executeQuery();
            int ELSRLS = 0;
            while (rs.next()){
                ELSRLS = rs.getInt(1);
            }
            if(ELSRLS>0){
                SNumber = ELSRLS;
                ELSRLS = ELSRLS - 1;
                youGetShop = true;
                String sql1 = "update ShopManagement set ELSRLS = "+ELSRLS+" where floor = "+floor;
                Main.st.execute(sql1);
            }
            else{
                System.out.println("Sorry....Shop Is Not Available!!!");
            }
        }

        if(youGetShop==true){
            ShopNumber = floor*100 + SNumber;

            MallManagementSystem.arr[floor][SNumber].ShopRenterName = name;
            MallManagementSystem.arr[floor][SNumber].ShopRenterMobileNo = mobileNumber;
            MallManagementSystem.arr[floor][SNumber].ShopRenterGovernmentIdentityNo = GovernmentIdentityNo;
            MallManagementSystem.arr[floor][SNumber].ShopName = SName;
            MallManagementSystem.arr[floor][SNumber].ShopType = SetShopType(type);
            MallManagementSystem.arr[floor][SNumber].ShopLocationType = SetShopLocation(Ltype);
            MallManagementSystem.arr[floor][SNumber].ShopSize = SetShopSize(size);
            MallManagementSystem.arr[floor][SNumber].ShopRent = setRentForShopNo(ShopNumber);

            String sql = "insert into rentedShop values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = Main.con.prepareStatement(sql);
            pst.setInt(1,ShopNumber);
            pst.setString(2,SName);
            pst.setString(3,name);
            pst.setString(4,""+mobileNumber);
            pst.setString(5,GovernmentIdentityNo);
            pst.setString(6,SetShopLocation(Ltype));
            pst.setString(7,SetShopSize(size));
            pst.setString(8,SetShopType(type));
            pst.setDouble(9,setRentForShopNo(ShopNumber));
            int r = pst.executeUpdate();
            if(r>0){
                System.out.println("Data Saved in Database Successfully");

                String s = "delete from nonRentedShop where shopNo = "+ShopNumber+";";
                Main.st.execute(s);
            }
            else {
                System.out.println("Failed To Save Data !!!");
            }

            System.out.println("**********************************************************");
            System.out.println("You Get Rented Shop Successfully!!!");
            System.out.println("Your Shop Number is : "+ShopNumber);
            System.out.println("Rent : Rs."+MallManagementSystem.arr[floor][SNumber].ShopRent);
            System.out.println("**********************************************************");
        }
    }

    double setRentForShopNo(int ShopNo){
        int floor = ShopNo/100;
        int shopNo = ShopNo%100;
        double rent;
        double shopFloorCharge = 0;
        double shopLocationCharge =0;
        double shopSizeCharge = 0;

        switch(floor){
            case 0 : shopFloorCharge = 30000; break;
            case 1 : shopFloorCharge = 35000; break;
            case 2 : shopFloorCharge = 40000; break;
            case 3 : shopFloorCharge = 36500; break;
            case 4 : shopFloorCharge = 32500; break;
            default:
        }

        if(arr[floor][shopNo].ShopLocationType.equals("Prime Location")){
            shopLocationCharge = shopFloorCharge*(0.25);
        }
        else{
            shopLocationCharge = shopFloorCharge*(0.1);
        }

        if(arr[floor][shopNo].ShopSize.equals("Medium Size Shop")){
            shopSizeCharge = shopFloorCharge * 0.25;
        }
        else if(arr[floor][shopNo].ShopSize.equals("Large Size Shop")){
            shopSizeCharge = shopFloorCharge * 0.4;
        }
        else if(arr[floor][shopNo].ShopSize.equals("Extra Large Size Shop")){
            shopSizeCharge = shopFloorCharge * 0.5;
        }

        rent = shopFloorCharge + shopLocationCharge + shopSizeCharge;
        return rent;
    }

}
