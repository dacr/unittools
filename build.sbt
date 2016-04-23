name := "unittools"

version := "0.2.7-SNAPSHOT"

organization :="fr.janalyse"

organizationHomepage := Some(new URL("http://www.janalyse.fr"))

scalaVersion := "2.10.6"

crossScalaVersions := Seq("2.10.6", "2.11.8")

scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature", "-language:implicitConversions")

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.6" % "test"
)

publishArtifact in packageDoc := false

