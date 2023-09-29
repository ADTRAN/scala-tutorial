package StandardScalaPlayground.Sections

/**
 * This file is unit tested in StandardScala/src/test/scala/TestTestTarget.scala
 * Go there for the tutorial
 */

class TestTarget(factor1: Int, factor2: Int) {
  def add: Int = {
    factor1 + factor2
  }
}

object TestTargetObject {
  def subtract(factor1: Int, factor2: Int): Int = {
    factor1 - factor2
  }
}

class TestTargetNeedsMocks(mockThis: MockThis) {
  def callMockThis(): Int = {
    mockThis.process("Hello!") + 1
  }
}

class MockThis {
  def process(input: String): Int = {
    Thread.sleep(1000000000)
    println(input)
    1
  }
}
