import ch.qos.logback.classic.LoggerContext
import org.slf4j.LoggerFactory

/**
  * Performance test for the Address Postcode searchs.
  */
class AddressPostcodeGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://127.0.0.1:8080"""

    val httpConf = http
        .baseURL(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connection("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test search Address by postcode")
        .exec(http("Authentication")
            .post("/api/authenticate")
            .headers(headers_http_authentication)
            .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJSON
            .check(header.get("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(10)
        .exec(http("Get addresses by postcode in IE - V93 WN9T")
            .get("/api/addresses/postcode/V93%20WN9T")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
        .pause(5 seconds, 15 seconds)
        .exec(http("Get addresses by postcode in UK - EC1A 1BB")
            .get("/api/addresses/postcode/EC1A%201BB")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
        .pause(5 seconds, 15 seconds)
        .exec(http("Get addresses by postcode in IE - D02 WY65")
            .get("/api/addresses/postcode/D02%20WY65")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
        .pause(5 seconds, 15 seconds)
        .exec(http("Get addresses by postcode in UK - W1A 0AX")
            .get("/api/addresses/postcode/W1A%200AX")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
        .pause(5 seconds, 15 seconds)

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(200) over (1 minutes))
    ).protocols(httpConf)
}
