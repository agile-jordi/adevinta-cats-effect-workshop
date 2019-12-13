package com.adevinta.cats_effect_workshop.helpers

import scala.io.Source
import cats.implicits._

import cats.effect.Sync
import cats.Applicative

final class FilesUI[F[_]: Sync](console: Console[F]) {

  def readFileName: F[String] =
    for {
      userInput <- console.readLine("Enter a file name: [build.sbt] ")
      fileName = if (userInput.trim.isEmpty) "build.sbt" else userInput
    } yield fileName

  def printLines(file: Source): F[Unit] = {
    val lines = file.getLines().toList
    for {
      _ <- if (lines.size > 5) {
        Sync[F].raiseError[Unit](new RuntimeException(s"Common! File is too large!"))
      } else {
        ().pure[F]
      }
      _ <- lines.traverse(console.writeLine)
    } yield ()
  }

}
