package com.adevinta.cats_effect_workshop

import com.adevinta.cats_effect_workshop.helpers.{Console, Files, FilesUI}
import cats.effect.{ExitCode, IO, IOApp}

abstract class WorkshopApp extends IOApp {

  protected val console: Console[IO] = new Console[IO]
  protected val files: Files[IO] = new Files[IO]
  val filesUI = new FilesUI[IO](console)

  def runExamples(args: List[String]): IO[Unit]

  final override def run(args: List[String]): IO[ExitCode] =
    runExamples(args).map(_ => ExitCode.Success)
}
