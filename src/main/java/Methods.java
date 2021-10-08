import com.github.javafaker.Faker;

import java.sql.*;
import java.util.ArrayList;

public class Methods {

    public void createData(){
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("drop table if exists waiters");
            statement.executeUpdate("drop table if exists days");
            statement.executeUpdate("drop table if exists shifts_requests");

            statement.executeUpdate("create table waiters(waiter_id INT, firstName String, lastName String)");
            statement.executeUpdate("create table days(day_id INTEGER , day_name String)");
            statement.executeUpdate("create table shifts_requests(id INTEGER PRIMARY KEY  , waiter_Id INTEGER, day_Id INTEGER ,FOREIGN KEY(waiter_id) REFERENCES waiter(id),  FOREIGN KEY (day_id) REFERENCES day(id))");

            String InsertWaiter = "INSERT INTO waiters(waiter_id,firstName,lastName) VALUES (?,?,?)";
            String InsertMon = "INSERT INTO days(day_id,day_name) VALUES (01,'Mon')";
            String InsertTue = "INSERT INTO days(day_id,day_name) VALUES (02,'Tue')";
            String InsertWed = "INSERT INTO days(day_id,day_name) VALUES (03,'Wed')";
            String InsertThu = "INSERT INTO days(day_id,day_name) VALUES (04,'Thu')";
            String InsertFri = "INSERT INTO days(day_id,day_name) VALUES (05,'Fri')";
            String InsertSat = "INSERT INTO days(day_id,day_name) VALUES (06,'Sat')";
            String InsertSun = "INSERT INTO days(day_id,day_name) VALUES (07,'Sun')";

            PreparedStatement preparedStmtWaiters = connection.prepareStatement(InsertWaiter);
            PreparedStatement preparedStmMon = connection.prepareStatement(InsertMon);
            PreparedStatement preparedStmTue = connection.prepareStatement(InsertTue);
            PreparedStatement preparedStmWed = connection.prepareStatement(InsertWed);
            PreparedStatement preparedStmThu = connection.prepareStatement(InsertThu);
            PreparedStatement preparedStmFri = connection.prepareStatement(InsertFri);
            PreparedStatement preparedStmSat= connection.prepareStatement(InsertSat);
            PreparedStatement preparedStmSun = connection.prepareStatement(InsertSun);

            preparedStmMon.execute();

            preparedStmTue.execute();
            preparedStmWed.execute();
            preparedStmThu.execute();
            preparedStmFri.execute();
            preparedStmSat.execute();
            preparedStmSun.execute();
            int j = 1;
            for (int i=0;i < 7;i++) {
                Faker faker = new Faker();

                int waiter_id = j;
                String  waiterName = faker.name().firstName();
                String lastName = faker.name().lastName();
                //set parameters in the statement

                preparedStmtWaiters.setInt(1, waiter_id);
                preparedStmtWaiters.setString(2, waiterName);
                preparedStmtWaiters.setString(3, lastName);

                //execute statement
                preparedStmtWaiters.executeUpdate();
                j++;
            }
            ResultSet rs = statement.executeQuery("SELECT * from waiters");
            while (rs.next()) {
                // read the result set

                System.out.println("name = " + rs.getString("firstName"));

                System.out.println("id = " + rs.getInt("waiter_id"));
            }


        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

    public void addRequest (String name, String [] days) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

            final String NameID = "Select Waiter_id from waiters where firstName = ?";
            PreparedStatement getNameID = connection.prepareStatement(NameID);
            getNameID.setString(1, name);
            ResultSet NameId = getNameID.executeQuery();
            int nameId = NameId.getInt("waiter_id");

            for(int i = 0; i < days.length; i++){

                final String DayID = "Select day_id from days where day_name = ?";
                PreparedStatement getDayID = connection.prepareStatement(DayID);
                getDayID.setString(1,days[i]);
                ResultSet DayId = getDayID.executeQuery();
                int dayId = DayId.getInt("day_id");

                if(limitCheck(dayId,connection)>=3){
                    System.err.println("reached day limit");
                }else {
                    final String InsertRequest = "INSERT INTO shifts_requests(waiter_id,day_id) VALUES (?,?)";
                    PreparedStatement insertReq = connection.prepareStatement(InsertRequest);
                    insertReq.setString(1, String.valueOf(nameId));
                    insertReq.setString(2, String.valueOf(dayId));
                    insertReq.execute();
                }
            }

        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

    public int limitCheck(int id,Connection conn){
//        Connection conn = null;
        int total = 0;
        try{
//            conn = DriverManager.getConnection("jdbc:sqlite:sample.db");

            final String DayLimit = "select count(*) as dayTotal from shifts_requests where day_id =?";
            PreparedStatement dayLim = conn.prepareStatement(DayLimit);
            dayLim.setString(1,String.valueOf(id));
            ResultSet dayTot = dayLim.executeQuery();
            total = Integer.parseInt(String.valueOf(dayTot.getInt("dayTotal")));


        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return total;
    }

    //    public void accept () {
//
//    }
    public ArrayList<String> schedule(){
        ArrayList<String> list = new ArrayList<>();

        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();

            String days = "select * from days";
            String waiters= "select * from waiters";
            String shifts = "select water_name where waiter_id value(?)";

            ResultSet dayZ = statement1.executeQuery(days);
            ResultSet waiterZ = statement2.executeQuery(waiters);
            ResultSet requests = statement3.executeQuery(shifts);


            for(int i =1;i<7;i++){
                while (dayZ.next()){

                }

            }

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return list;
    }

}
