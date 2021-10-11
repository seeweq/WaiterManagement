import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.result.ResultIterable;
import org.jdbi.v3.core.statement.Query;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


import java.util.*;

import static spark.Spark.*;
public class Route {

    private static Query id;

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

//             handle.select("SELECT day, waiter,id FROM waiters INNER JOIN shifts on waiters.waiter_id = shift.day_id");
//
//
//            List<Shift> shifts = new ArrayList<>();
//
//            for(Day day:days){
//                System.out.println(day.id);
//
//                Shift daysOfShift = new Shift();
//                shifts.add(new Shift());
//                Waiter aWaiter = new Waiter();
//                daysOfShift.addWaiter(aWaiter);
//
//
//
//            }
//
//            System.out.println(shifts);


           Map<String, Object> map = new HashMap<>();
            map.put("days",days);
            map.put("waiters",waiters);

            return new ModelAndView(map, "selectdays.handlebars");
        }, new HandlebarsTemplateEngine());


        post("/schedules", (request, response) -> {

            Shift shift = new Shift();
            Waiter aWaiter = new Waiter();

            List<Day> days= handle.select("select id, day from days")
                    .mapToBean(Day.class)
                    .list();

            List<Shift> sh = handle.select("select day from days inner join shifts on days.id = shifts.day_id")
                    .mapToBean(Shift.class)
                    .list();

            Map<String, Object> map = new HashMap<>();

            String waiter = request.queryParams("waiter");

            String [] checkedDays = request.queryParamsValues("day");
            System.out.println(waiter);
            System.out.println(Arrays.toString(checkedDays));


            for(int i=1 ; i <checkedDays.length;i++){
             handle.createQuery("insert into shifts(day_id) values(?)");

            }
           handle.select("select id from waiters where waiter = ?",waiter).mapToBean(Integer.class);

            map.put("waiter",waiter);
            map.put("day",checkedDays);
            map.put("shifts",sh);
            // create the greeting message



            List<Shift> shifts = new ArrayList<>();


            List<Waiter> waiters = handle.select("select id, waiter from waiters where waiters.waiter =?",waiter)
                    .mapToBean(Waiter.class)
                    .list();

            for(Day day:days){
                id = handle.select("select id from waiters where id =?", day.id);


                handle.select("select waiters.waiter FROM waiters INNER JOIN shifts ON waiters.id = waiter.day_.id");

                Shift daysOfShift = new Shift();
                shifts.add(shift);
                daysOfShift.addWaiter(aWaiter);



            }


            System.out.println(shifts);






            // put it in the map which is passed to the template - the value will be merged into the template
            //put the greet String on top in the greeting handlebars in html file



            return new ModelAndView(map, "selecteddays.handlebars");

        }, new HandlebarsTemplateEngine());


    }
}