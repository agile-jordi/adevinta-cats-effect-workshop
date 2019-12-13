package com.adevinta.cats_effect_workshop.typeclasses

import com.adevinta.cats_effect_workshop.helpers.{Console, IntParser}
import com.adevinta.cats_effect_workshop.WorkshopApp
import com.adevinta.cats_effect_workshop.helpers.MonadErrorThrowable.MonadErrorThrowable
import cats.effect.IO
import cats.implicits._

final class MonadErrorWorkshop[F[_]: MonadErrorThrowable](console: Console[F]) {

  def catchNonFatal: F[Unit] = {
    for {
      input <- console.readLine("Enter a number: ")
      parsed <- IntParser.parseInt[F](input)
      _ <- console.writeLine(s"The number is $parsed")
    } yield ()
  }

  /** Try to run the number game but recover from NumberFormatException by telling the user and starting over again */
  def recover: F[Unit] = ???

}

object MonadErrorWorkshopApp extends WorkshopApp {

  override def runExamples(args: List[String]): IO[Unit] =
    new MonadErrorWorkshop[IO](console).catchNonFatal
}
