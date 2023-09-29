import StandardScalaPlayground.Sections.{MockThis, TestTarget, TestTargetNeedsMocks, TestTargetObject}
import org.scalamock.scalatest.MockFactory
import org.scalatest.wordspec.AnyWordSpec

class TestTestTarget extends AnyWordSpec with MockFactory {
  /** Unit Testing Basics */
    // - This tutorial uses AnyWordSpec. There are many testing suites in scalatest, such as AnyFunSuite, AnyFlatSpec, etc.
    // - Unit test files should be named after whatever they are testing, with a "Test" prepended, e.g. MyObject, TestMyObject
    // - This entire suite can be run by using `sbt test` at the top level of the project
    // - You can also only run this file, using `sbt "testOnly TestTestTarget"`
    // - Individual test can be run using the green play buttons next to the test names in IntelliJ


  /** When, Should, In */
    // - AnyWordSpec supports using keywords to make test names into sentences:

    "A MyObject" when {
      "no defaults are overridden" should {
        "print an error message and return" in {
          assert(true)  // some assertion
        }
      }
    }

    // - You do not have to use the full "when, should, in" pattern. The following will also work:
    "MyObject" should {
      "do nothing" in {
        assert(true)
      }
    }

    "MyObject should do something" in {
      assert(true)
    }


  /** The following sections test the [[TestTarget]] */


  /** Some Basic Unit Tests */
    // - Below, we test a method in a class, and an object. Each test must contain an assertion, or it will pass
    //   without actually testing anything.

    "A TestTarget" should {
      "return the sum of its factors with .add" in {
        val target = new TestTarget(2, 3)
        assert(target.add == 5)
      }
    }

    "A TestTargetObject" should {
      "return the sum of its factors with .add" in {
        val sum = TestTargetObject.subtract(2, 4)
        assert(sum == -2)
      }
    }


  /** Mocks */
    // - This tutorial uses scalamock. There are other mocking frameworks, such as Mockito.
    // - Often, you will be testing a class which has parameters consisting of other classes. Sometimes,
    //   it will not be possible to use those other classes, usually because what they require to actually work involves
    //   far more than we are trying to test in unit testing. One way to solve this issue is with Mocks.

    "A TestTargetNeedsMocks" should {
      "return a value due to a mock being passed into it" in {
        val mockedMockThis = mock[MockThis]
        val testTarget = new TestTargetNeedsMocks(mockedMockThis)

        (mockedMockThis.process _).expects("Hello!").returning(1)

        assert(testTarget.callMockThis() == 2)
      }
    }
    // - Above, we create a mock of a class that is used in creating the testTarget.
    // - We then set up an expectation statement for when the mock's .process method is called with "Hello!", and
    //   provide a return value. This allows us to avoid the infinite sleep in the real .process method, because the
    //   real method is never called.

    // - It's important to note that singleton objects cannot be mocked. If you need a mock, you should turn the object
    //   into a class or trait, or make it extend a class or trait, mocking the class or trait instead.


  /** Manual "Mocks" */
    // - You can also make your own "mock" of a class for testing purposes
    "A TestTargetNeedsMocks" should {
      "return a value due to a manual mock being passed into it" in {
        class myMock extends MockThis {
          override def process(input: String): Int = 1
        }
        val testTarget = new TestTargetNeedsMocks(new myMock)

        assert(testTarget.callMockThis() == 2)
      }
    }
    // - Above, instead of using scalamock, we simply made another class that extended the class we wanted to mock the
    //   functionality of, and re-implemented the method with "override def". This works because this new class will
    //   satisfy the type check for whatever class it extends.

}
