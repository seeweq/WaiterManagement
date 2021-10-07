import com.github.javafaker.Faker;

import java.sql.*;

public class Manager {


    public static void main(String[] args) {

        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("drop table if exists waiters");
            statement.executeUpdate("drop table if exists days");
            statement.executeUpdate("drop table if exists shifts");
            statement.executeUpdate("drop table if exists shifts_requests");
            statement.executeUpdate("drop table if exists shifts_confirmed");

            statement.executeUpdate("create table waiters(waiter_id INT, firstName String, lastName String)");
            statement.executeUpdate("create table days(day_name String)");
            statement.executeUpdate("create table shifts_requests(id INTEGER PRIMARY KEY  , waiter_Id INTEGER, day_Id INTEGER, pending String ,FOREIGN KEY(id) REFERENCES waiter(id),  FOREIGN KEY (day_id) REFERENCES day(id))");


            String InsertWaiter = "INSERT INTO waiters(waiter_id,firstName,lastName) VALUES (?,?,?)";
            String InsertMon = "INSERT INTO days(day_name) VALUES ('Mon')";
            String InsertTue = "INSERT INTO days(day_name) VALUES ('Tue')";
            String InsertWed = "INSERT INTO days(day_name) VALUES ('Wed')";
            String InsertThu = "INSERT INTO days(day_name) VALUES ('Thu')";
            String InsertFri = "INSERT INTO days(day_name) VALUES ('Fri')";
            String InsertSat = "INSERT INTO days(day_name) VALUES ('Sat')";
            String InsertSun = "INSERT INTO days(day_name) VALUES ('Sun')";
            String InsertShifts = "INSERT INTO shifts_requests(waiter_id,day_id) VALUES (?,?)";

            PreparedStatement preparedStmtWaiters = connection.prepareStatement(InsertWaiter);
            PreparedStatement preparedStmMon = connection.prepareStatement(InsertMon);
            PreparedStatement preparedStmTue = connection.prepareStatement(InsertTue);
            PreparedStatement preparedStmWed = connection.prepareStatement(InsertWed);
            PreparedStatement preparedStmThu = connection.prepareStatement(InsertThu);
            PreparedStatement preparedStmFri = connection.prepareStatement(InsertFri);
            PreparedStatement preparedStmSat= connection.prepareStatement(InsertSat);
            PreparedStatement preparedStmSun = connection.prepareStatement(InsertSun);
            PreparedStatement preparedStmShifts = connection.prepareStatement(InsertShifts);
            preparedStmMon.execute();
            preparedStmTue.execute();
            preparedStmWed.execute();
            preparedStmThu.execute();
            preparedStmFri.execute();
            preparedStmSat.execute();
            preparedStmSun.execute();

            for (int i=0;i < 8;i++) {
                Faker faker = new Faker();

                int waiter_id = faker.random().nextInt(1,100);
                String  waiterName = faker.name().firstName();
                String lastName = faker.name().lastName();
                //set parameters in the statement

                preparedStmtWaiters.setInt(1, waiter_id);
                preparedStmtWaiters.setString(2, waiterName);
                preparedStmtWaiters.setString(3, lastName);







                //execute statement
                preparedStmtWaiters.executeUpdate();





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


}

