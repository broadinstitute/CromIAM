name := "CromIam"
organization := "org.broadinstitute"
version := "1.0"

scalaVersion := "2.12.1"

scalacOptions := List(
  "-Xlint",
  "-feature",
  "-Xmax-classfile-name", "200",
  "-target:jvm-1.8",
  "-encoding", "UTF-8",
  "-unchecked",
  "-deprecation",
  "-Xfuture",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-unused",
  "-Ywarn-unused-import",
  "-Xfatal-warnings"
)

scalacOptions in (Compile, doc) ++= List(
  // http://stackoverflow.com/questions/31488335/scaladoc-2-11-6-fails-on-throws-tag-with-unable-to-find-any-member-to-link#31497874
  "-no-link-warnings"
)

libraryDependencies ++= {
  val akkaV       = "2.4.17"
  val akkaHttpV   = "10.0.5"
  val scalaTestV  = "3.0.1"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV,
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV,
    "com.iheart" %% "ficus" % "1.4.0",
    "org.webjars" %  "swagger-ui" % "2.1.1",
    "org.scalatest" %% "scalatest" % scalaTestV % Test,
    "io.swagger" % "swagger-parser" % "1.0.22" % Test,
    "org.yaml" % "snakeyaml" % "1.17" % Test
  )
}

Revolver.settings
