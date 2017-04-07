import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server._
import spray.json._


trait CromIAMApiService extends Directives with SprayJsonSupport with DefaultJsonProtocol {

  def returnInternalServerError(msg: String)  = HttpResponse(InternalServerError, entity = msg)


  val workflowRoutes = queryRoute ~ queryPostRoute ~ workflowOutputsRoute ~ submitRoute ~ submitBatchRoute ~
    workflowLogsRoute ~ abortRoute ~ metadataRoute ~ timingRoute ~ statusRoute ~ backendRoute ~ statsRoute ~ versionRoute


  def statusRoute =
    path("workflows" / Segment / Segment / "status") { (version, possibleWorkflowId) =>
      get {
        complete {
          returnInternalServerError("workflow status")
        }
      }
    }

  def queryRoute =
    path("workflows" / Segment / "query") { version =>
      parameterSeq { parameters =>
        get {
          complete {
            returnInternalServerError("query get")
          }
        }
      }
    }

  def queryPostRoute =
    path("workflows" / Segment / "query") { version =>
      (post & entity(as[Seq[Map[String, String]]])) { parameterMap =>
          complete {
//            returnInternalServerError("workflow query post")
            JsString("workflow query post")
          }
      }
    }

  def abortRoute =
    path("workflows" / Segment / Segment / "abort") { (version, possibleWorkflowId) =>
      post {
        complete {
          returnInternalServerError("workflow abort")
        }
      }
    }

  def submitRoute =
    path("workflows" / Segment) { version =>
      post {
        complete{
          returnInternalServerError("submit workflow")
        }
      }
    }

  def submitBatchRoute =
    path("workflows" / Segment / "batch") { version =>
      post {
        complete {
          returnInternalServerError("batch submit workflow")
          }
        }
      }

  def workflowOutputsRoute =
    path("workflows" / Segment / Segment / "outputs") { (version, possibleWorkflowId) =>
      get {
        complete {
          returnInternalServerError("workflow outputs")
        }
      }
    }

  def workflowLogsRoute =
    path("workflows" / Segment / Segment / "logs") { (version, possibleWorkflowId) =>
      get {
        complete {
          returnInternalServerError("workflow logs")
        }
      }
    }

  def metadataRoute =
    path("workflows" / Segment / Segment / "metadata") { (version, possibleWorkflowId) =>
      get {
        complete {
          returnInternalServerError("workflow metdata")
        }
      }
    }

  def timingRoute =
    path("workflows" / Segment / Segment / "timing") { (version, possibleWorkflowId) =>
      get {
        complete {
          returnInternalServerError("workflow timing")
        }
      }
    }

  def statsRoute =
    path("engine" / Segment / "stats") { version =>
      get {
        complete {
          returnInternalServerError("engine stats")
        }
      }
    }

  def versionRoute =
    path("engine" / Segment / "version") { version =>
      get {
        complete {
          returnInternalServerError("engine version")
        }
      }
    }

  def backendRoute =
    path("workflows" / Segment / "backends") { version =>
      get {
        complete {
          returnInternalServerError("engine backends")
        }
      }
    }

}
