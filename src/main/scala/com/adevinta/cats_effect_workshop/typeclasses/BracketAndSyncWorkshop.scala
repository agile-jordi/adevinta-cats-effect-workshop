package com.adevinta.cats_effect_workshop.typeclasses

import com.adevinta.cats_effect_workshop.helpers.{Files, FilesUI}
import com.adevinta.cats_effect_workshop.WorkshopApp
import cats.effect.{IO, Sync}
import cats.implicits._

// We could have used Bracket here
final class BracketAndSyncWorkshop[F[_]: Sync](files: Files[F], filesUI: FilesUI[F]) {

  def pureFunctionalFinally: F[Unit] = {
    for {
      fileName <- filesUI.readFileName
      // TODO: Fix that so the file is always closed
      file <- files.open(fileName)
      _ <- filesUI.printLines(file)
      _ <- files.close(file)
    } yield ()
  }
}

object BracketAndSyncWorkshopApp extends WorkshopApp {

  override def runExamples(args: List[String]): IO[Unit] =
    new BracketAndSyncWorkshop[IO](files, filesUI).pureFunctionalFinally
}
