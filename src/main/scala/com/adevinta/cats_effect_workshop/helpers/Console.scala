package com.adevinta.cats_effect_workshop.helpers

import cats.effect.Sync

final class Console[F[_]: Sync] {

  def readLine(message: String): F[String] =
    Sync[F].delay(scala.io.StdIn.readLine(message))

  def writeLine(message: String): F[Unit] = Sync[F].delay(println(message))

}
