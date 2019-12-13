package com.adevinta.cats_effect_workshop.helpers

import com.adevinta.cats_effect_workshop.helpers.MonadErrorThrowable.MonadErrorThrowable
import cats.MonadError
import cats.implicits._

final case class IllegalNumberFormat(value: String) extends Exception

object IntParser {

  def parseInt[F[_]: MonadErrorThrowable](value: String): F[Int] =
    MonadError[F, Throwable].catchNonFatal(value.toInt).adaptErr { case _ => IllegalNumberFormat(value) }

}
