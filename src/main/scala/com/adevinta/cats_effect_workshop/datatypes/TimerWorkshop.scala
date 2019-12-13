package com.adevinta.cats_effect_workshop.datatypes

import java.time.Instant

import com.adevinta.cats_effect_workshop.helpers.{Console, IntParser}
import com.adevinta.cats_effect_workshop.WorkshopApp
import cats.effect.{IO, Sync, Timer}
import cats.implicits._
import scala.concurrent.duration._

final class BracketAndSyncWorkshop[F[_]: Sync: Timer](console: Console[F]) {

  def getCurrentTimeMillis: F[Long] = ???

  def sleep(sleepDuration: FiniteDuration): F[Unit] = ???

  def waitForIt: F[Unit] = {
    for {
      input <- console.readLine("Enter a number of seconds from 1 to 5: ")
      numberOfSeconds <- IntParser.parseInt[F](input)
      sleepDuration = numberOfSeconds.seconds
      currentTimeMillis <- getCurrentTimeMillis
      waitUntil = Instant.ofEpochMilli(currentTimeMillis).plusSeconds(numberOfSeconds)
      _ <- console.writeLine(s"Waiting until $waitUntil...")
      _ <- sleep(sleepDuration)
      _ <- console.writeLine("Done. Bye!")
    } yield ()
  }
}

object BracketAndSyncWorkshopApp extends WorkshopApp {

  override def runExamples(args: List[String]): IO[Unit] =
    new BracketAndSyncWorkshop[IO](console).waitForIt
}
