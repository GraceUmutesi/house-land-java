import static spark.Spark.*;
import java.util.HashMap;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;

public class App {
    public static void main(String [] args){
        staticFileLocation("/public");
//        get("/", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "buyHouse.hbs");
        }, new HandlebarsTemplateEngine());
        get("/fill", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "reponseForm.hbs");
        }, new HandlebarsTemplateEngine());
    }
}