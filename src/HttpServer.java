/*import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

class HttpServer implements Runnable{

  private Messageable receiver;

  HttpServer(Messageable receiver) {
    this.receiver = receiver;
  }

  public void run() {
    port(1771);

    get("/test", (req, res) -> "PASS");

    get("/web", (req, res) -> formatForWeb(
        receiver.message(
            req.queryParams("from"),
            req.queryParams("body")
        )
    ));

    post("/sms", (req, res) -> {

      res.type("application/xml");
      Body body = new Body.Builder(
          receiver.message(
              req.queryParams("From"),
              req.queryParams("Body")
          )
      ).build();
      Message sms = new Message.Builder()
                        .body(body)
                        .build();
      MessagingResponse twiml = new MessagingResponse.Builder()
                                    .message(sms)
                                    .build();
      return twiml.toXml();
    });
  }

  private String formatForWeb(String message) {
    return "<!DOCTYPE html><html><head><link rel='stylesheet' href='http://mnrva.net/plant/response.css'></head><body>" + message + "</body></html>";
  }

}
*/