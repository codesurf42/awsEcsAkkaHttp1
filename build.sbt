lazy val akkaHttpVersion = "10.0.10"
lazy val akkaVersion = "2.5.6"
lazy val awsSdkVersion = "1.11.227"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.4"
    )),
    name := "akkaHttp",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,

      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
      "org.scalatest" %% "scalatest" % "3.0.4" % Test,
      "com.amazonaws" % "aws-java-sdk-sqs" % awsSdkVersion,
      "com.amazonaws" % "aws-java-sdk-kinesis" % awsSdkVersion,

      "org.slf4j" % "slf4j-api" % "1.7.25",
      "ca.pjer" % "logback-awslogs-appender" % "1.0.0"
    )
  )
