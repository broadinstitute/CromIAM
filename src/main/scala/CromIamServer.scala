import akka.Done
import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.server.{Route, HttpApp}
import akka.http.scaladsl.settings.ServerSettings
import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.config.{ConfigFactory, Config}
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future, Promise}
import scala.language.postfixOps
import WrappedRoute._

trait Service {
  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit val materializer: Materializer

  def config: Config
  val logger: LoggingAdapter

}

object CromIamServer extends HttpApp with Service with CromIamApiService {
  override implicit val system = ActorSystem()
  override implicit val executor = system.dispatcher
  override implicit val materializer = ActorMaterializer()

  override val config = ConfigFactory.load()
  override val logger = Logging(system, getClass)
  val routeUnwrapped = false
  override val route: Route = cromIamRoutes.wrapped("api", routeUnwrapped)

  // Override default shutdownsignal which was just "hit return/enter"
  override def waitForShutdownSignal(actorSystem: ActorSystem)(implicit executionContext: ExecutionContext): Future[Done] = {
    val promise = Promise[Done]()
    sys.addShutdownHook {
      promise.success(Done)
    }
    promise.future
  }

  def main(args: Array[String]): Unit = {
    CromIamServer.startServer(CromIamServer.config.getString("http.interface"), CromIamServer.config.getInt("http.port"), ServerSettings(CromIamServer.config))
  }
}