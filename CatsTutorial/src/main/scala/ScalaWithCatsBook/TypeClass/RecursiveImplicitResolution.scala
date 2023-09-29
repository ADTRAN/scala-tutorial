package ScalaWithCatsBook.TypeClass

object RecursiveImplicitResolution {
  import ScalaWithCatsBook.TypeClass.TypeClass._
  import ScalaWithCatsBook.TypeClass.TypeClass.JsonWriterInstances._

  // type classes can also be created by construction from other type class instances
  // consider JsonWriter[Option[A]]

  // ugh, do we have to create something like
  implicit val _optionIntWriter: JsonWriter[Option[Int]] = ??? // for all boxed options

  // doesn't scale so we dispatch to implicit writer of Int
  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] =
    new JsonWriter[Option[A]] {
      def write(option: Option[A]): Json =
        option match {
          case Some(a) => writer.write(a)
          case None    => JsNull
        }
    }

  // so now we have a way to get Json for an Option[String] instance
  val optionalJsonHello: Json = Json.toJson(Option("hello"))

  val nothin: Option[String] = None
  val optionalJsonNullWorld: Json = Json.toJson(nothin)
}
