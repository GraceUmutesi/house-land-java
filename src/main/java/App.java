import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;

import models.Lands;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.List;
import java.util.Map;
import spark.ModelAndView;

public class App {
    public static void main(String [] args){
        staticFileLocation("/public");
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/land/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "lands-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/lands", ((request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "lands.hbs");
        }), new HandlebarsTemplateEngine());

        get("/succ", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "landsuccess.hbs");
        }, new HandlebarsTemplateEngine());

        post("lands", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String property = request.queryParams("property");
            String location = request.queryParams("location");
            String meansofpayment = request.queryParams("meansofpayment");
            String plot = request.queryParams("plot");
            String price = request.queryParams("price");

            Lands lands = new Lands(name, email, property, location, plot, meansofpayment, price);
            lands.save();
            List<Lands> parcels = Lands.allLands();
            model.put("parcels", parcels);

            return new ModelAndView(model, "lands.hbs");
        }, new HandlebarsTemplateEngine());
    }

}