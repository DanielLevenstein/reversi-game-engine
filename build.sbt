ThisBuild / scalaVersion := "2.13.14"
ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / organization := "org.esotericcode.reversi.gameengine"
ThisBuild / name := "reversi-game-engine"

lazy val playVersion = "2.8.0"
lazy val slickVersion = "3.3.3"
lazy val playSlickVersion = "5.0.0"
lazy val playJsonVersion = "2.8.0"
lazy val scalatestPlusPlayVersion = "5.1.0"
lazy val scalatestPlusMockitoVersion = "3.2.16.0"
lazy val mockitoCoreVersion = "5.14.2"
lazy val h2Version = "2.2.224"
lazy val guavaVersion = "28.1-jre"
lazy val playGuiceVersion = "2.8.1"
lazy val scalaLangXmlVersion = "2.3.0"
enablePlugins(PlayScala)
//
//resolvers += "Lightbend Releases" at "https://repo.lightbend.com/lightbend-releases"

libraryDependencies ++= Seq(
  // Play Framework
  "com.typesafe.play" %% "play" % playVersion,
  "com.typesafe.play" %% "play-slick" % playSlickVersion,
  "com.typesafe.play" %% "play-json" % playJsonVersion,
  "com.typesafe.play" %% "play-guice" % playGuiceVersion,

  // Slick
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,

  // Database
  "org.postgresql" % "postgresql" % "42.7.5",
  "org.scala-lang.modules" %% "scala-xml" % scalaLangXmlVersion,
  "com.h2database" % "h2" % h2Version % Runtime,

  // Utilities
  "com.google.guava" % "guava" % guavaVersion,
  "jakarta.inject" % "jakarta.inject-api" % "2.0.1",

  // Test
  "org.scalatestplus.play" %% "scalatestplus-play" % scalatestPlusPlayVersion % Test,
  "org.scalatestplus" %% "mockito-4-11" % scalatestPlusMockitoVersion % Test,
  "org.mockito" % "mockito-core" % mockitoCoreVersion % Test,
  "org.junit.jupiter" % "junit-jupiter-api" % "5.10.2" % Test,
  "org.junit.jupiter" % "junit-jupiter-engine" % "5.10.2" % Test
)

libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % "early-semver"
dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "2.3.0"
scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")
javacOptions ++= Seq("--release", "11")