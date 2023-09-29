package CatsEffectTutorial

import cats.Id
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import fs2.{Pure, Stream}

import scala.concurrent.duration.{Duration, DurationInt, SECONDS}
import scala.concurrent.{Await, Future, TimeoutException}
import scala.util.{Failure, Success, Try}


object TestingCats extends App {

val mine = None

  println(mine.getOrElse(None))





}
