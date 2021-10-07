import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
public class Route {
    public static void main(String[] args) {
        port(5000);
        ArrayList<String> users = new ArrayList<>();
        get("/schedules", (req, res) -> {
            Map<String, Object> map = new HashMap<>();
            return new ModelAndView(map, "landing.handlebars");
        }, new HandlebarsTemplateEngine());
    }


}
