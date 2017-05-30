package cromiam.webservice

import akka.event.NoLogging
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest._

import scala.concurrent.Future

class CromIamApiServiceSpec extends FlatSpec with Matchers with CromIamApiService with ScalatestRouteTest {
  override def testConfigSource = "akka.loglevel = DEBUG"
  val logger = NoLogging

  override def configuration = throw new NotImplementedError("This spec shouldn't need to access the real interface/port")
  override protected lazy val UserIdHeader = "blah"

  override def forwardToCromwell(httpRequest: HttpRequest): Future[HttpResponse] = {
    Future.successful(HttpResponse(status = InternalServerError))
  }

  "Stats endpoint" should "be forbidden" in {
    Get("/api/engine/v1/stats") ~> allRoutes ~> check {
      status shouldBe Forbidden
      responseAs[String] shouldBe CromIamApiService.CromIamForbidden
    }
  }
}