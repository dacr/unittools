name := "unittools"

version := "0.2.5"

organization :="fr.janalyse"

organizationHomepage := Some(new URL("http://www.janalyse.fr"))

scalaVersion := "2.10.5"

crossScalaVersions := Seq("2.10.4", "2.11.6")

scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature", "-language:implicitConversions")

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)

publishTo := Some(
     Resolver.sftp(
         "JAnalyse Repository",
         "www.janalyse.fr",
         "/home/tomcat/webapps-janalyse/repository"
     ) as("tomcat", new File(util.Properties.userHome+"/.ssh/id_rsa"))
)

publishArtifact in packageDoc := false

