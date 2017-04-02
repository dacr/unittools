name := "unittools"

organization :="fr.janalyse"
homepage := Some(new URL("https://github.com/dacr/unittools"))

scalaVersion := "2.11.8"
scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature", "-language:implicitConversions")
crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.1")

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

//publishArtifact in packageDoc := false

