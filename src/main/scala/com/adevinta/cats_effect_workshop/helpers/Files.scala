package com.adevinta.cats_effect_workshop.helpers

import scala.io.Source

import cats.effect.Sync

final class Files[F[_]: Sync] {

  def open(fileName: String): F[Source] =
    Sync[F].delay(Source.fromFile(fileName))

  def close(file: Source): F[Unit] = Sync[F].delay(file.close())
}
