name := "tasky"
version := "0.1.0"
scalaVersion := "2.11.6"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
// Pass option to scala compiler
scalacOptions ++= Seq("-feature", "-language:_", "-unchecked", "-deprecation", "-encoding", "utf8")


// Define my own Task. It's a two-step process:
//   1. Define a TaskKey
//   2. Provide the task definition

// Define a git count Task
val gitCommitCountTask = taskKey[String]("Prints commit count of the current branch")

// git count task definition
gitCommitCountTask := {
  val branch = scala.sys.process.Process("git symbolic-ref -q HEAD").lines.head.replace("refs/heads/", "")
  val commitCount = scala.sys.process.Process(s"git rev-list --count $branch").lines.head
  println(s"Total number of commit on [$branch] is : $commitCount")
  commitCount // return
}