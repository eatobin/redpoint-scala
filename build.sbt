ThisBuild / scalaVersion := "2.12.11"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "redpoint-scala",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.1.1" % "test"
    )
  )
