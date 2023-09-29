package StandardScalaPlayground.Sections

object MakingAPlayground extends App {
  /**
    - Anywhere in a Scala project, you can use "object SomeName extends App" to give yourself a simple app to use as a playground.
    - You can create an object in IntelliJ by right-clicking a package -> New -> Scala Class -> Object
    - You run these Apps in IntelliJ by pressing the green play button next to the object
   */
  println("Hello world")
}


// You can also use the more verbose, Java-like App:
object AMainObject {
  def main(args: Array[String]) = {}
}


/* I prefer the first implementation. These are useful to make while you're developing to atomically test what
 you're working on, or experiment in a somewhat contained environment. */