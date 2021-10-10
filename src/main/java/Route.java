import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.result.ResultIterable;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


import java.util.*;

import static spark.Spark.*;
public class Route {
    public static void main(String[] args) {
        port(5000);
        String dbDiskURL = "jdbc:sqlite:file:./waiter.db";

        Jdbi jdbi = Jdbi.create(dbDiskURL);

//        get a handle to the database
            Handle handle = jdbi.open();





        get("/schedules", (req, res) -> {

            List<Day> days= handle.select("select id, day from days")
                    .mapToBean(Day.class)
                    .list();

            List<Waiter> waiters = handle.select("select id, waiter from waiters")
               .mapToBean(Waiter.class)
                .list();

             handle.select("SELECT day, waiter,id FROM waiters INNER JOIN shifts on waiters.waiter_id = shift.day_id");


            List<Shift> shifts = new ArrayList<>();

            for(Day day:days){
                System.out.println(day.day_id);

                Shift daysOfShift = new Shift();
                shifts.add(new Shift());
                Waiter aWaiter = new Waiter();
                daysOfShift.addWaiter(aWaiter);



            }

            System.out.println(shifts);


           Map<String, Object> map = new HashMap<>();
            map.put("days",days);
            map.put("waiters",waiters);
           map.put("shifts",shifts);
            return new ModelAndView(map, "selectdays.handlebars");
        }, new HandlebarsTemplateEngine());


        post("/schedules", (request, response) -> {

            Shift shift = new Shift();

            List<Day> days= handle.select("select id, day from days")
                    .mapToBean(Day.class)
                    .list();

            List<Waiter> waiters = handle.select("select id, waiter from waiters")
                    .mapToBean(Waiter.class)
                    .list();


            Map<String, Object> map = new HashMap<>();

            String waiter = request.queryParams("waiter");

            String [] checkedDays = request.queryParamsValues("day");
            System.out.println(waiter);
            System.out.println(Arrays.toString(checkedDays));

//            handle.createQuery("insert ");

            map.put("waiter",waiter);
            map.put("day",checkedDays);

            // create the greeting message

            handle.select("SELECT day, waiter,id FROM waiters INNER JOIN shifts on waiters.waiter_id = shift.day_id ");

            List<Shift> shifts = new ArrayList<>();
            Waiter aWaiter = new Waiter();
            ResultIterable<Waiter> getWaiter = handle.select("select id from waiters where waiter = ?",waiter).
                    mapToBean(Waiter.class);

            for(Day day:days){
//                System.out.println(day.day_id);

                handle.select("select id from days where id =?",day.day_id);

                Shift daysOfShift = new Shift();
                shifts.add(shift);


                daysOfShift.addWaiter(aWaiter);


               List<Shift> onDudy = handle.select("select waiter_id from shifts where day_id = ?",day.day_id)
                       .mapToBean(Shift.class)
                       .list();

                System.out.println(onDudy);
            }








            // put it in the map which is passed to the template - the value will be merged into the template
            //put the greet String on top in the greeting handlebars in html file
//            map.put("" );


            return new ModelAndView(map, "selecteddays.handlebars");

        }, new HandlebarsTemplateEngine());


    }
}