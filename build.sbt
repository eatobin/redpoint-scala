ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"
ThisBuild / useCoursier := false

val circeVersion = "0.13.0"

lazy val root = (project in file("."))
  .settings(
    name := "redpoint",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.5",
      "org.scalatest" %% "scalatest" % "3.2.5" % "test"
    ),
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion),
    scalacOptions += "-deprecation"
  )
