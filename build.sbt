ThisBuild / scalaVersion := "2.13.3"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"
ThisBuild / useCoursier := false

lazy val root = (project in file("."))
  .settings(
    name := "redpoint",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.1.2" % "test",
      "io.spray" %% "spray-json" % "1.3.5"
    ),
    scalacOptions += "-deprecation"
  )
