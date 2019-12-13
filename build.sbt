import Dependencies._
import ScalaOptions.scalaCompileOptions

ThisBuild / scalaVersion := "2.11.12"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.adevinta"
ThisBuild / organizationName := "adevinta"

lazy val root = (project in file("."))
  .settings(
    name := "cats-effect-workshop",
    scalacOptions := scalaCompileOptions,
    libraryDependencies ++= Seq(catsEffect, scalaTest % Test),
    addCompilerPlugin(
      "org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full
    )
  )
