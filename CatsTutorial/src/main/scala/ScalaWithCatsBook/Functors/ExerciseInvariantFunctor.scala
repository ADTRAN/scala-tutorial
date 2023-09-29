package ScalaWithCatsBook.Functors

object ExerciseInvariantFunctor {

  // invariant functors implement method called imap (combination of map and contramap)
  // implement the imap method
  trait Codec[A] {
    def encode(value: A): String
    def decode(value: String): A
    def imap[B](dec: A => B, enc: B => A): Codec[B] = ???
  }

}
