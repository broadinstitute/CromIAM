import akka.event.NoLogging
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest._


class CromIamApiServiceSpec extends FlatSpec with Matchers with ScalatestRouteTest with CromIamApiService with Service {
  override def testConfigSource = "akka.loglevel = WARNING"
  override def config = testConfig
  override val logger = NoLogging

  "Stats endpoint" should "respond with internal server error" in {
    Get("/engine/v1/stats") ~> cromIamRoutes ~> check {
      status shouldBe InternalServerError
    }
  }
}