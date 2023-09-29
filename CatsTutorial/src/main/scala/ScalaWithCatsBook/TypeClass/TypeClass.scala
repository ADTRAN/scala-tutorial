package ScalaWithCatsBook.TypeClass

// from https://www.scalawithcats.com/dist/scala-with-cats.pdf
object TypeClass {

  // anatomy of a type class:
  // 1.) traits -- type class
  // 2.) implicit values -- type class instances
  // 3.) implicit parameters -- type class use
  // 4.) implicit classes -- utilities that make type classes easier to use

  // AST
  sealed trait Json

  final case class JsObject(get: Map[String, Json]) extends Json

  final case class JsString(get: String) extends Json

  final case class JsNumber(get: Double) extends Json

  final case object JsNull extends Json

  //1.) the type class -- will write an A as a Json class
  trait JsonWriter[A] {
    def write(value: A): Json
  }

  final case class Person(name: String, email: String)

  object JsonWriterInstances {
    //2.) type class instances defined by concrete implementations of type class
    //    note, we implicitly scope these
    implicit val stringWriter: JsonWriter[String] = new JsonWriter[String] {
      def write(value: String): Json = JsString(value)
    }

    implicit val personWriter: JsonWriter[Person] = new JsonWriter[Person] {
      def write(value: Person): Json =
        JsObject(Map("name" -> JsString(value.name), "email" -> JsString(value.email)))
    }
  }

  //3.) for a type class to work we must provide implicit parameters
  //    so if we want to call Json.toJson(???) on something ???
  //    we need that something to have an implicit JsonWriter defined for the type of something
  object Json {
    def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }
  // bring in our personWriter into implicit scope by importing implicits defined in JsonWriterInstances

  import JsonWriterInstances._
  // we could also write as Json.toJson(....)(JsonWriterInstances.personWriter)
  Json.toJson(Person("Jason", "j@home.com"))

  //4.) using 'interface syntax' -- extension methods to extend existing types with interface methods
  //    this uses pattern called 'pimp my library' also known as 'type enrichment'
  object JsonSyntax {
    implicit class JsonWriterOps[A](value: A) {
      def toJson(implicit w: JsonWriter[A]): Json = w.write(value)
    }
  }
  //   with the above syntax the Person can write itself

  import JsonSyntax._

  // we enrich the Person type with the toJson method given an implicit JsonWriter[Person] is in scope
  val noahJson: Json = Person("Noah", "n@work.com").toJson
}
