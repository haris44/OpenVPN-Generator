name := """VPN-Service"""
organization := "io.hidenn"

version := "2.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

libraryDependencies += filters
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % Test

// https://mvnrepository.com/artifact/ch.qos.logback/logback-access
libraryDependencies += "ch.qos.logback" % "logback-access" % "1.2.3"

// https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3" % "test"

// https://mvnrepository.com/artifact/ch.qos.logback/logback-core
libraryDependencies += "ch.qos.logback" % "logback-core" % "1.2.3"

// https://mvnrepository.com/artifact/net.logstash.logback/logstash-logback-encoder
libraryDependencies += "net.logstash.logback" % "logstash-logback-encoder" % "4.10"
