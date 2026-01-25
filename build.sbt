ThisBuild / scalaVersion := "2.13.14"
ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / organization := "org.esotericcode.reversi.gameengine"
ThisBuild / name := "reversi-game-engine"

lazy val playVersion = "2.8.18"
lazy val playCacheVersion = "2.8.18"
lazy val caffeineCacheVersion = "3.2.3"
lazy val slickVersion = "3.3.3"
lazy val playSlickVersion = "5.0.0"
lazy val playJsonVersion = "2.8.0"
lazy val scalatestPlusPlayVersion = "5.1.0"
lazy val scalatestPlusMockitoVersion = "3.2.16.0"
lazy val mockitoCoreVersion = "5.14.2"
lazy val h2Version = "2.2.224"
lazy val guavaVersion = "11.0"
lazy val playGuiceVersion = "2.8.1"
lazy val scalaLangXmlVersion = "2.3.0"

enablePlugins(PlayScala)

// Source directories
Compile / scalaSource := baseDirectory.value / "app"
Test / scalaSource := baseDirectory.value / "app-test"

// Dependencies
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % playVersion,
  "com.typesafe.play" %% "play-slick" % playSlickVersion,
  "com.typesafe.play" %% "play-json" % playJsonVersion,
  "com.typesafe.play" %% "play-guice" % playGuiceVersion,
  "com.typesafe.play" %% "play-cache" % playCacheVersion,
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
  "org.postgresql" % "postgresql" % "42.7.5",
  "org.scala-lang.modules" %% "scala-xml" % scalaLangXmlVersion,
  "com.h2database" % "h2" % h2Version % Runtime,
  "com.google.guava" % "guava" % guavaVersion,
  "jakarta.inject" % "jakarta.inject-api" % "2.0.1",
  "org.scalatestplus.play" %% "scalatestplus-play" % scalatestPlusPlayVersion % Test,
  "org.scalatestplus" %% "mockito-4-11" % scalatestPlusMockitoVersion % Test,
  "org.mockito" % "mockito-core" % mockitoCoreVersion % Test,
  "org.junit.jupiter" % "junit-jupiter-api" % "5.10.2" % Test,
  "org.junit.jupiter" % "junit-jupiter-engine" % "5.10.2" % Test,
  "org.scalamock" %% "scalamock" % "7.3.2" % Test
)
dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "2.3.0"

// Scala compiler options
scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")
javacOptions ++= Seq("--release", "11")

// --- sbt-assembly config ---
import sbtassembly.AssemblyPlugin.autoImport._
assembly / assemblyJarName := "reversi-game-engine.jar"
assembly / mainClass := Some("org.esotericcode.reversi.gameengine.CommandLineGame")
assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case "reference.conf"              => MergeStrategy.concat
  case x                             => MergeStrategy.first
}
