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




           Map<String, Object> map = new HashMap<>();
            map.put("days",days);
            map.put("waiters",waiters);

            return new ModelAndView(map, "selectdays.handlebars");
        }, new HandlebarsTemplateEngine());










        post("/schedules", (request, response) -> {

            Map<String, Object> map = new HashMap<>();

            String waiter = request.queryParams("waiter");

            String [] checkedDays = request.queryParamsValues("day");
            System.out.println(waiter);
            System.out.println(Arrays.toString(checkedDays));

           Integer waiterId =  handle.select("select id from waiters where waiter = ?",waiter).mapTo(Integer.class).findOnly();
            // get the waiter id
            // loop over checked days
                // insert the shift for each day and waiterid


            for( String daysId : checkedDays){
                handle.execute("insert into shifts(day_id, waiter_id) values(?,?)",daysId,waiterId);
            }


            map.put("waiter",waiter);
            map.put("day",checkedDays);

            // create the greeting message



//            List<Waiter> waiters = handle.select("select id, waiter from waiters where waiters.waiter =?",waiter)
//                    .mapToBean(Waiter.class)
//                    .list();

            // put it in the map which is passed to the template - the value will be merged into the template
            //put the greet String on top in the greeting handlebars in html file



            return new ModelAndView(map, "selecteddays.handlebars");

        }, new HandlebarsTemplateEngine());


        get("/shifts", (req, res) -> {
//            List<Shift> shifts = new ArrayList<>();


            handle.select("select waiters.waiter FROM waiters INNER JOIN shifts ON waiter.day_.id = days.id");

            List<Shift> allShifts = handle.select("select day_id, waiter_id, waiter, day  FROM shifts " +
                    "inner join waiters on waiters.id = waiter_id  " +
                    "inner join days on days.id = day_id").mapToBean(Shift.class).list();


            String[] weekDays = {
                    "Monday",
                    "Tuesday",
                    "Wednesday",
                    "Thursday",
                    "Friday",
                    "Saturday",
                    "Sunday"};

            Map<String, DayShift> dayShiftMap = new HashMap<>();

            for (String weekDay : weekDays) {
                dayShiftMap.put(weekDay, new DayShift(weekDay));
            }

            for (Shift shift : allShifts) {
                dayShiftMap.get(shift.getDay()).addWaiter(shift.getWaiter());
            }

            Collection<DayShift> shifts = dayShiftMap.values();


            Map<String, Object> map = new HashMap<>();
            map.put("shifts", shifts);
            map.put("Dayshift",shifts);


            return new ModelAndView(map, "landing.handlebars");
        }, new HandlebarsTemplateEngine());



    }
}