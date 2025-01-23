package jehoff;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import jehoff.health.HealthCheck.TemplateHealthCheck;
import jehoff.resources.AppResource;

/**
 * Hello world!
 */
public class App extends Application<AppConfig> {
  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public String getName() {
    return "hello-world";
  }

  @Override
  public void initialize(Bootstrap<AppConfig> bootstrap) {
    // nothing to do yet
  }

  @Override
  public void run(AppConfig config, Environment env) {
    AppResource app_res = new AppResource(config.getTemplate(), config.getDefaultName());
    env.jersey().register(app_res);

    TemplateHealthCheck health_check = new TemplateHealthCheck(config.getTemplate());
    env.healthChecks().register("template", health_check);
  }

}
