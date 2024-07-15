# Scala Tutorial
Scala examples and exercises, in source code.

### Setup & Contributing
- [Environment Requirements Guide](/docs/environment-setup.md)
- [Contribution Guide](/docs/contributing.md)

### Sections:
- [Standard Scala](#standard-scala)
- [Monix](#Monix)

## Standard Scala
#### Sections:
- [Table of Contents](StandardScala/src/main/scala/StandardScalaPlayground/TableOfContents.scala)

1. Basic IntelliJ Tips
   - [Basic IntelliJ](StandardScala/src/main/scala/StandardScalaPlayground/Sections/BasicIntelliJ.scala)

2. Basic Syntax
   - [Making A Playground](StandardScala/src/main/scala/StandardScalaPlayground/Sections/MakingAPlayground.scala)
   - [Vars and Vals](StandardScala/src/main/scala/StandardScalaPlayground/Sections/VarsAndVals.scala)
   - [Types](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Types.scala)
   - [Functions](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Functions.scala)

3. Object Oriented
   - [Objects](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Objects.scala)
   - [Classes](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Classes.scala)
   - [Case Classes](StandardScala/src/main/scala/StandardScalaPlayground/Sections/CaseClasses.scala)
   - [Traits](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Traits.scala)
   - [.apply( )](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Apply.scala)
   - [Companion Objects](StandardScala/src/main/scala/StandardScalaPlayground/Sections/CompanionObjects.scala)

4. Data Structures/Collections
   - [Tuples](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Tuples.scala)
   - [Lists](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Lists.scala)
   - [Sets](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Sets.scala)
   - [Maps](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Maps.scala)

5. Iterable Operations
   - [.map( )](StandardScala/src/main/scala/StandardScalaPlayground/Sections/MapOperations.scala)
   - [.flatMap( )](StandardScala/src/main/scala/StandardScalaPlayground/Sections/FlatMap.scala)
   - [.foreach( )](StandardScala/src/main/scala/StandardScalaPlayground/Sections/ForEach.scala)
   - [.filter( )](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Filter.scala)
   - [.fold( ), .foldLeft( ), .foldRight( )](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Fold.scala)
   - [.reduce( ), .reduceLeft( ), .reduceRight( )](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Reduce.scala)
   - [.collect{ }](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Collect.scala)

6. Other Logic
   - [If Else](StandardScala/src/main/scala/StandardScalaPlayground/Sections/IfElse.scala)
   - [For Loops](StandardScala/src/main/scala/StandardScalaPlayground/Sections/ForLoops.scala)
   - [While Loops](StandardScala/src/main/scala/StandardScalaPlayground/Sections/WhileLoops.scala)
   - [Match Case](StandardScala/src/main/scala/StandardScalaPlayground/Sections/MatchCase.scala)
   - [For Comprehensions](StandardScala/src/main/scala/StandardScalaPlayground/Sections/ForComprehensions.scala)
   - [Try Catch](StandardScala/src/main/scala/StandardScalaPlayground/Sections/TryCatch.scala)

7. Miscellaneous
   - [Options - Some( ), None](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Options.scala)
   - [Eithers - Right( ), Left( )](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Eithers.scala)
   - [Ranges](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Ranges.scala)

8. Concurrent Programming
    - [Futures](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Futures.scala)
    - [Threads](StandardScala/src/main/scala/StandardScalaPlayground/Sections/Threads.scala)

9. Testing
    - [Unit testing](StandardScala/src/main/scala/StandardScalaPlayground/Sections/TestTarget.scala)

## Monix
#### Sections:
- [Table of Contents](MonixTutorial/src/main/scala/MonixPlayground/MonixTableOfContents.scala)

1. Tasks
   - Basics
     - [Task Basics](MonixTutorial/src/main/scala/MonixPlayground/Tasks/TasksBasics.scala)
   - Builders
     - [Task( ) Task.eval( ) Task.delay( )](MonixTutorial/src/main/scala/MonixPlayground/Tasks/TaskEvalDelay.scala)
     - [Task.now( ) Task.pure( )](MonixTutorial/src/main/scala/MonixPlayground/Tasks/TaskNowPure.scala)
   - Manipulation & Composition
     - [.flatMap( ), .map( )](MonixTutorial/src/main/scala/MonixPlayground/Tasks/TaskFlatMap.scala)
     - [For Comprehensions](MonixTutorial/src/main/scala/MonixPlayground/Tasks/TaskForComprehensions.scala)
     - [Task.sequence( ), Task.parSequence( ), Task.parSequenceUnordered( )](MonixTutorial/src/main/scala/MonixPlayground/Tasks/TaskSequence.scala)
     - [Parallelism - .executeAsync, .executeOn( ), Task.parSequence( )](MonixTutorial/src/main/scala/MonixPlayground/Tasks/TasksParallel.scala)
   - Execution
     - [.foreach( )](MonixTutorial/src/main/scala/MonixPlayground/Tasks/TaskForEach.scala)
     - [.runToFuture](MonixTutorial/src/main/scala/MonixPlayground/Tasks/TaskRunToFuture.scala)
     - [.runAsync( ), .runAsyncAndForget](MonixTutorial/src/main/scala/MonixPlayground/Tasks/TaskRunAsync.scala)
     - [.runSyncUnsafe( )](MonixTutorial/src/main/scala/MonixPlayground/Tasks/TaskRunSyncUnsafe.scala)
     - [TaskApp](MonixTutorial/src/main/scala/MonixPlayground/Tasks/TaskApps.scala)

2. Observables
   - Basics
     - [Observable Basics](MonixTutorial/src/main/scala/MonixPlayground/Observables/ObservablesBasics.scala)
   - Builders
     - [Observable.eval( ) Observable.delay( )](MonixTutorial/src/main/scala/MonixPlayground/Observables/ObservableEvalDelay.scala)
     - [Observable.now( ) Observable.pure( )](MonixTutorial/src/main/scala/MonixPlayground/Observables/ObservableNowPure.scala)
   - Manipulation & Composition
     - [.map( ), .flatMap( ), .concatMap( )](MonixTutorial/src/main/scala/MonixPlayground/Observables/ObservableFlatMap.scala)
     - [For Comprehensions](MonixTutorial/src/main/scala/MonixPlayground/Observables/ObservableForComprehension.scala)
     - [Parallelism - .executeAsync, .mapParallelOrdered( ), .mapParallelUnordered( )](MonixTutorial/src/main/scala/MonixPlayground/Observables/ObservablesParallel.scala)
     - [Multicasting - .replay, .multicast( )](MonixTutorial/src/main/scala/MonixPlayground/Observables/ObservablesMulticast.scala)

