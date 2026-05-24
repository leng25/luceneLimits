package org.example;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class LuceneSimulator extends Simulation {

    HttpProtocolBuilder protocol = http
        .baseUrl("http://localhost:8080")
        .acceptHeader("application/json");

    ScenarioBuilder indexScenario = scenario("Index Documents")
        .exec(http("POST /index")
            .post("/index")
            .header("Content-Type", "application/json")
            .body(StringBody("""
                    {"title":"test","content":"hello world"}
                    """))
            .check(status().is(201))
        );

    ScenarioBuilder searchScenario = scenario("Search Documents")
        .exec(http("GET /search")
            .get("/search?q=hello")
            .check(status().is(200))
        );

    {
        setUp(
            indexScenario.injectOpen(atOnceUsers(1000)),
            searchScenario.injectOpen(atOnceUsers(1000))
        ).protocols(protocol);
    }
}
