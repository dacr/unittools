name := "unittools"

version := "0.2.3"

organization :="fr.janalyse"

organizationHomepage := Some(new URL("http://www.janalyse.fr"))

scalaVersion := "2.10.0"

crossScalaVersions := Seq("2.8.1", "2.8.2", "2.9.1", "2.9.2", "2.10.0")

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.0.M5b" % "test"
)

publishTo := Some(
     Resolver.sftp(
         "JAnalyse Repository",
         "www.janalyse.fr",
         "/home/tomcat/webapps-janalyse/repository"
     ) as("tomcat", new File(util.Properties.userHome+"/.ssh/id_rsa"))
)

publishArtifact in packageDoc := false

