package StandardScalaPlayground.Sections

object Maps extends App {

  /** Basic Maps */
  // Maps are Scala's key -> value collection:
  val someMap: Map[String, Int] = Map("key1" -> 1, "key2" -> 2)
  println(s"Found key2: ${someMap("key2")}")

  // Keys must be unique, values do not. If the same key is added twice, the second addition will replace the first:
  val unique = Map("key1" -> 1, "key1" -> 300)
  println(s"Found key1: ${unique("key1")}")
  println(s"Maps' keys are unique: $unique")


  /** Adding to a Map */
  val add1 = Map(1 -> 2) ++ Map(10 -> 20)
  println(s"Combined Map is $add1")


  /** Updating a Value */
  // To "update" a key's value in a Map, simply add a Map with the same key
  val updated = Map("key1" -> 1) ++ Map("key1" -> 100000)
  println(s"Updated Map is $updated")


  /** Getting all keys/values */
  // Just get the keys:
  println(s"keys: ${Map(1 -> "value1", 2 -> "value2").keys}")
  // Notice the return type is a Set( ), which makes sense, because every element of a set is unique

  // Just get the values:
  println(s"values: ${Map(1 -> "value1", 2 -> "value2").values}")


  /** Getting a key/value from a single element */
  // Same ._1, ._2  notation as Tuples:
  Map(1 -> 2, 3 -> 4).foreach(element =>
    println(s"Key was ${element._1}, value was ${element._2}")
  )


  /** Checking if a key exists in the Map */
  val contains = Map("one" -> "two", "three" -> "four").contains("one")
  println(s"The Map contains one: $contains")


  /** Getting a value that may or may not exist */
  val getMap = Map("key-1" -> "val")
  val myVal = getMap.getOrElse("key-2", "default")
  // Above, the first item in the .getOrElse is the key we want, and the second value what we will return if we
  // don't find it.

  // Note that you can use { } to wrap a complicated operation in this "orElse" value, e.g.:
  getMap.getOrElse("key-2", { println("missed!"); "default" })
  // Also notice that we use a ; here to keep two statements on the same line. You could use a newline instead.

  // These are two general tools in scala - wrapping { } around multiple statements to encapsulate them into one, and
  // the use of ; to do multiple things on the same line. These can be used in various circumstances, not only in this .getOrElse

}


object MapsExercises extends App {
  /** Exercises */
  // 1. Get the element "key" from Map("one" -> "no", "two" -> "no", "three" -> "key") two different ways

  // 2. Get the second element of the single tuple value in Map(1 -> 2), without using .values

}






/** Exercise Solutions */
// 1. Map("one" -> "no", "two" -> "no", "three" -> "key")("three")
//    Map("one" -> "no", "two" -> "no", "three" -> "key").values.last
//    Map("one" -> "no", "two" -> "no", "three" -> "key").map(x => if (x._2 == "key") x._2).last

// 2. Map(1 -> 2).last._2