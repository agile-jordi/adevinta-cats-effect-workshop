package com.adevinta.cats_effect_workshop.datatypes

import scala.io.Source

import com.adevinta.cats_effect_workshop.helpers.{Console, Files, FilesUI}
import com.adevinta.cats_effect_workshop.WorkshopApp
import cats.effect.{Bracket, IO, Resource, Sync}
import cats.implicits._

// We could have used Bracket here
final class ResourceWorkshop[F[_]: Sync](files: Files[F], console: Console[F], filesUI: FilesUI[F]) {

  def fileResource(fileName: String): Resource[F, Source] = ???

  def filesResource(fileName1: String, fileName2: String): Resource[F, (Source, Source)] = ???

  def diff(lines1: List[String], lines2: List[String]): String = {
    (lines1.length, lines2.length) match {
      case (l, r) if l < r => s"The first file has ${r - l} lines LESS than the second one"
      case (l, r) if l > r => s"The first file has ${r - l} lines MORE than the second one"
      case _ => s"Both files have the same number of lines"
    }
  }

  def useFile: F[Unit] =
    for {
      fileName <- filesUI.readFileName
      _ <- fileResource(fileName).use(filesUI.printLines)
    } yield ()

  def composeResources: F[Unit] =
    for {
      fileName1 <- filesUI.readFileName
      fileName2 <- filesUI.readFileName
      _ <- filesResource(fileName1, fileName2).use {
        case (file1, file2) =>
          console.writeLine(diff(file1.getLines().toList, file2.getLines().toList))
      }
    } yield ()

}

object ResourceWorkshopApp extends WorkshopApp {

  override def runExamples(args: List[String]): IO[Unit] =
    new ResourceWorkshop[IO](files, console, filesUI).useFile
}
