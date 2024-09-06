ThisBuild / version := "0.0"
ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "Tutorial"
  )
  .aggregate(standardScala, monixTutorial, catsTutorial)

lazy val standardScala = (project in file("StandardScala"))
  .settings(
    name := "StandardScala",
      libraryDependencies ++= Seq(
        "com.github.nscala-time" %% "nscala-time" % "2.32.0",
        "org.scalatest" %% "scalatest" % "3.2.18" % Test,
        "org.scalamock" %% "scalamock" % "6.0.0" % Test
      )
  )

lazy val monixTutorial = (project in file("MonixTutorial"))
  .settings(
    name := "MonixTutorial",
    libraryDependencies ++= Seq(
      "io.monix" %% "monix" % "3.4.1",
      "org.typelevel" %% "cats-core" % "2.12.0",
      "org.typelevel" %% "cats-kernel" % "2.11.0",
      "com.github.nscala-time" %% "nscala-time" % "2.32.0"
    )
  )

lazy val catsTutorial = (project in file("CatsTutorial"))
  .settings(
    name := "CatsTutorial",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.5.4" withSources() withJavadoc(),
      "co.fs2" %% "fs2-core" % "3.10.2",
      "co.fs2" %% "fs2-io" % "3.10.2",
      "co.fs2" %% "fs2-reactive-streams" % "3.10.2",
      "co.fs2" %% "fs2-scodec" % "3.10.2"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-unchecked",
      "-language:postfixOps"
    )
  )