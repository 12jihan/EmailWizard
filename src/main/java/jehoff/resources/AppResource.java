package jehoff.resources;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.codahale.metrics.annotation.Timed;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jehoff.Saying;

@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
public class AppResource {
  private final String template;
  private final String defaultName;
  private final AtomicLong counter;

  public AppResource(String template, String defaultName) {
    this.template = template;
    this.defaultName = defaultName;
    this.counter = new AtomicLong();
  }

  @GET
  @Timed
  public Saying sayHello(@QueryParam("name") Optional<String> name) {
    final String value = String.format(template, name.orElse(defaultName));
    return new Saying(counter.incrementAndGet(), value);
  }
}
