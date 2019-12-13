package com.adevinta.cats_effect_workshop.helpers

import cats.MonadError

object MonadErrorThrowable {

  type MonadErrorThrowable[x[_]] = MonadError[x, Throwable]

  def apply[F[_]](implicit ME: MonadErrorThrowable[F]): MonadErrorThrowable[F] = ME

}
