# Contributing

### Style
- Use multiline comments for headings /** */
- Use inline comments for commentary //
- Use two newlines between headings
- Indent under each heading
- Each tutorial section should have an exercise section 

The rest is up to your discretion. Try to make it simple and look good.

Template:

```scala
package StandardScalaPlayground.Sections

object ObjectName extends App {

  /** While Loop Basics */
  // Some inline commentary
  var x = 0

}


object ObjectNameExercises extends App {
  /** Exercises */
  // 1. 

}


/** Exercise Solutions */
// 1. 

```


### Raising a Pull Request
- Anyone can raise a pull request. Create a new branch, and push it up to Github for review:
  - `git checkout -b "<some-branch-name>"`
  - `git add <files>`
  - `git commit -m "<some description>"`
  - `git push origin <your-branch-name>`

Everything in `<pointy braces>` above is a variable - `<your input here>`