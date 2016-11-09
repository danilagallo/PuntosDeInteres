package web;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {
	public static void main(String[] args) {
		Spark.port(9000);
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();
		Spark.staticFileLocation("/static");
		Spark.get("/", HomeController::principal, engine);
		Spark.get("/admin/ingreso", UserController::adminLog, engine);
	}

}