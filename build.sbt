name := """todolist"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.7"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies ++= Seq(evolutions, jdbc)
libraryDependencies += "com.typesafe.play" %% "anorm" % "2.5.3"
libraryDependencies += "com.h2database" % "h2" % "1.4.192"

val appDependencies = Seq(
  "postgresql" % "postgresql" % "8.4-702.jdbc4"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
