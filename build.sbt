ThisBuild / scalaVersion := "2.13.11"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.eatobin"
ThisBuild / organizationName := "eatobin"
ThisBuild / useCoursier := true

val circeVersion = "0.14.5"

lazy val root = (project in file("."))
  .settings(
    name := "redpointscala",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.16",
      "org.scalatest" %% "scalatest" % "3.2.16" % Test
    ),
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion),
    scalacOptions += "-deprecation",
    assembly / assemblyJarName := "redpoint-scala-sbt-assembly-fatjar-1.0.jar"
  )
