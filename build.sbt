lazy val akkaHttpVersion = "10.0.9"
lazy val akkaVersion    = "2.5.3"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.example",
      scalaVersion    := "2.12.3"
    )),
    name := "akkaHttp",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,

      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
      "org.scalatest"     %% "scalatest"         % "3.0.1"         % Test,
      "com.amazonaws" % "aws-java-sdk-sqs" % "1.11.192",
      "com.amazonaws" % "aws-java-sdk-kinesis" % "1.11.192",

      "org.slf4j" % "slf4j-api" % "1.7.21",
      "ca.pjer" % "logback-awslogs-appender" % "0.1.1"
    )
  )
  enablePlugins(JavaAppPackaging)
