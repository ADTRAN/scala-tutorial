package ScalaWithCatsBook.Functors

object Solution_ExerciseInvariantFunctor {

  // invariant functors implement method called imap (combination of map and contramap)
  // implement the imap method
  trait Codec[A] { self =>
    def encode(value: A): String
    def decode(value: String): A
    def imap[B](dec: A => B, enc: B => A): Codec[B] = new Codec[B] {
      def encode(value: B): String = self.encode(enc(value))
      def decode(value: String): B = dec(self.decode(value))
    }
  }

}
