import WrappedRoute._
import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.ExecutionContextExecutor


trait Service {
  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit val materializer: Materializer

  def config: Config
  val logger: LoggingAdapter

}

object CromIAMServer extends App with Service with CromIAMApiService {
  override implicit val system = ActorSystem()
  override implicit val executor = system.dispatcher
  override implicit val materializer = ActorMaterializer()

  override val config = ConfigFactory.load()
  override val logger = Logging(system, getClass)
  val routeUnwrapped = false
  val possibleRoutes: Route = workflowRoutes.wrapped("api", routeUnwrapped)

  Http().bindAndHandle(possibleRoutes, config.getString("http.interface"), config.getInt("http.port"))
}
