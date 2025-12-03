ThisBuild / scalaVersion := "2.13.14"
ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / organization := "org.esotericcode.reversi.gameengine"
ThisBuild / name := "reversi-game-engine"

//lazy val playVersion = "2.8.0"
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
//
//resolvers += "Lightbend Releases" at "https://repo.lightbend.com/lightbend-releases"

// Tell sbt where to find main code
Compile / scalaSource := baseDirectory.value / "app"

// Tell sbt where to find test code
Test / scalaSource := baseDirectory.value / "app-test"
libraryDependencies += "com.typesafe.play" %% "play" % playVersion
libraryDependencies += "com.typesafe.play" %% "play-slick" % playSlickVersion
libraryDependencies += "com.typesafe.play" %% "play-json" % playJsonVersion
libraryDependencies += "com.typesafe.play" %% "play-guice" % playGuiceVersion
libraryDependencies += "com.typesafe.play" %% "play-cache" % playCacheVersion
libraryDependencies += "com.typesafe.slick" %% "slick" % slickVersion
libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % slickVersion
libraryDependencies += "org.postgresql" % "postgresql" % "42.7.5"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % scalaLangXmlVersion
libraryDependencies += "com.h2database" % "h2" % h2Version % Runtime
libraryDependencies += "com.google.guava" % "guava" % guavaVersion
libraryDependencies += "jakarta.inject" % "jakarta.inject-api" % "2.0.1"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % scalatestPlusPlayVersion % Test
libraryDependencies += "org.scalatestplus" %% "mockito-4-11" % scalatestPlusMockitoVersion % Test
libraryDependencies += "org.mockito" % "mockito-core" % mockitoCoreVersion % Test
libraryDependencies += "org.junit.jupiter" % "junit-jupiter-api" % "5.10.2" % Test
libraryDependencies += "org.junit.jupiter" % "junit-jupiter-engine" % "5.10.2" % Test
libraryDependencies += "org.scalamock" %% "scalamock" % "7.3.2" % Test
libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % "early-semver"
dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "2.3.0"
scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")
javacOptions ++= Seq("--release", "11")