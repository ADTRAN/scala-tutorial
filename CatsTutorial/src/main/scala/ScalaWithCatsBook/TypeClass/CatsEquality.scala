package ScalaWithCatsBook.TypeClass

import cats._
import cats.implicits._ // for some and none

object CatsEquality extends App {

  final case class Cat(name: String, age: Int, color: String)

  implicit val catEquality: Eq[Cat] = Eq.instance[Cat] { (cat1, cat2) =>
    cat1.name === cat2.name &&
      cat1.age === cat2.age &&
      cat1.color === cat2.color
  }

  val _cat1 = Cat("Garfield", 38, "orange and black")
  val _cat2 = Cat("Heathcliff", 33, "orange and black")

  println(s"cat1 == cat1 is ${_cat1 === _cat1}")
  println(s"cat1 == cat2 is ${_cat1 === _cat2}")

  val optionCat1 = Option(_cat1)
  val optionCat2 = Option.empty[Cat]

  println(s"ocat1 == ocat2 is ${optionCat1 === optionCat2}")

}
