#SCALA
##Basic
###Why Scala?
Expressive
First-class functions
Closures
Concise
Type inference
Literal syntax for function creation
Java interoperability
Can reuse java libraries
Can reuse java tools
No performance penalty
How Scala?
Compiles to java bytecode
Works with any standard JVM
Or even some non-standard JVMs like Dalvik
Scala compiler written by author of Java compiler
Think Scala
Scala is not just a nicer Java. You should learn it with a fresh mind- you will get more out of these classes.

###Get Scala
Scala School’s examples work with Scala 2.9.x . If you use Scala 2.10.x or newer, most examples work OK, but not all.

Start the Interpreter
Start the included sbt console.
```scala
$ sbt console
```

Welcome to Scala version 2.8.0.final (Java HotSpot(TM) 64-Bit Server VM, Java 1.6.0_20).
Type in expressions to have them evaluated.
Type :help for more information.

scala>
Expressions
```scala
scala> 1 + 1
res0: Int = 2
```
res0 is an automatically created value name given by the interpreter to the result of your expression. It has the type Int and contains the Integer 2.

(Almost) everything in Scala is an expression.

###Values
You can give the result of an expression a name.
```scala
scala> val two = 1 + 1
two: Int = 2
```

You cannot change the binding to a val.

###Variables
If you need to change the binding, you can use a var instead.
```scala
scala> var name = "steve"
name: java.lang.String = steve

scala> name = "marius"
name: java.lang.String = marius
```
###Functions
You can create functions with def.
```scala
scala> def addOne(m: Int): Int = m + 1
addOne: (m: Int)Int
```
In Scala, you need to specify the type signature for function parameters. The interpreter happily repeats the type signature back to you.
```scala
scala> val three = addOne(2)
three: Int = 3
```
You can leave off parens on functions with no arguments.
```scala
scala> def three() = 1 + 2
three: ()Int

scala> three()
res2: Int = 3

scala> three
res3: Int = 3
```
###Anonymous Functions
You can create anonymous functions.
```scala
scala> (x: Int) => x + 1
res2: (Int) => Int = <function1>
```
This function adds 1 to an Int named x.
```scala
scala> res2(1)
res3: Int = 2
```
You can pass anonymous functions around or save them into vals.
```scala
scala> val addOne = (x: Int) => x + 1
addOne: (Int) => Int = <function1>

scala> addOne(1)
res4: Int = 2
```
If your function is made up of many expressions, you can use {} to give yourself some breathing room.
```scala
def timesTwo(i: Int): Int = {
  println("hello world")
  i * 2
}
```
This is also true of an anonymous function.
```scala
scala> { i: Int =>
  println("hello world")
  i * 2
}

res0: (Int) => Int = <function1>
```
You will see this syntax often used when passing an anonymous function as an argument.

###Partial application
You can partially apply a function with an underscore, which gives you another function. Scala uses the underscore to mean different things in different contexts, but you can usually think of it as an unnamed magical wildcard. In the context of { _ + 2 } it means an unnamed parameter. You can use it like so:
```scala
scala> def adder(m: Int, n: Int) = m + n
adder: (m: Int,n: Int)Int
scala> val add2 = adder(2, _:Int)
add2: (Int) => Int = <function1>

scala> add2(3)
res50: Int = 5
```
You can partially apply any argument in the argument list, not just the last one.

###Curried functions
Sometimes it makes sense to let people apply some arguments to your function now and others later.

Here’s an example of a function that lets you build multipliers of two numbers together. At one call site, you’ll decide which is the multiplier and at a later call site, you’ll choose a multiplicand.
```scala
scala> def multiply(m: Int)(n: Int): Int = m * n
multiply: (m: Int)(n: Int)Int
```
You can call it directly with both arguments.
```scala
scala> multiply(2)(3)
res0: Int = 6
```
You can fill in the first parameter and partially apply the second.
```scala
scala> val timesTwo = multiply(2) _
timesTwo: (Int) => Int = <function1>

scala> timesTwo(3)
res1: Int = 6
```
You can take any function of multiple arguments and curry it. Let’s try with our earlier adder
```scala
scala> val curriedAdd = (adder _).curried
curriedAdd: Int => (Int => Int) = <function1>

scala> val addTwo = curriedAdd(2)
addTwo: Int => Int = <function1>

scala> addTwo(4)
res22: Int = 6
```
###Variable length arguments
There is a special syntax for methods that can take parameters of a repeated type. To apply String’s capitalize function to several strings, you might write:
```scala
def capitalizeAll(args: String*) = {
  args.map { arg =>
    arg.capitalize
  }
}

scala> capitalizeAll("rarity", "applejack")
res2: Seq[String] = ArrayBuffer(Rarity, Applejack)
Classes
scala> class Calculator {
     |   val brand: String = "HP"
     |   def add(m: Int, n: Int): Int = m + n
     | }
defined class Calculator

scala> val calc = new Calculator
calc: Calculator = Calculator@e75a11

scala> calc.add(1, 2)
res1: Int = 3

scala> calc.brand
res2: String = "HP"
```
Contained are examples defining methods with def and fields with val. Methods are just functions that can access the state of the class.

###Constructor
Constructors aren’t special methods, they are the code outside of method definitions in your class. Let’s extend our Calculator example to take a constructor argument and use it to initialize internal state.
```scala
class Calculator(brand: String) {
  /**
   * A constructor.
   */
  val color: String = if (brand == "TI") {
    "blue"
  } else if (brand == "HP") {
    "black"
  } else {
    "white"
  }

  // An instance method.
  def add(m: Int, n: Int): Int = m + n
}
```
Note the two different styles of comments.

You can use the constructor to construct an instance:
```scala
scala> val calc = new Calculator("HP")
calc: Calculator = Calculator@1e64cc4d

scala> calc.color
res0: String = black
```
###Expressions
Our Calculator example gave an example of how Scala is expression-oriented. The value color was bound based on an if/else expression. Scala is highly expression-oriented: most things are expressions rather than statements.

Aside: Functions vs Methods
Functions and methods are largely interchangeable. Because functions and methods are so similar, you might not remember whether that thing you call is a function or a method. When you bump into a difference between methods and functions, it might confuse you.
```scala
scala> class C {
     |   var acc = 0
     |   def minc = { acc += 1 }
     |   val finc = { () => acc += 1 }
     | }
defined class C

scala> val c = new C
c: C = C@1af1bd6

scala> c.minc // calls c.minc()

scala> c.finc // returns the function as a value:
res2: () => Unit = <function0>
```
When you can call one “function” without parentheses but not another, you might think Whoops, I thought I knew how Scala functions worked, but I guess not. Maybe they sometimes need parentheses? You might understand functions, but be using a method.

In practice, you can do great things in Scala while remaining hazy on the difference between methods and functions. If you’re new to Scala and read explanations of the differences, you might have trouble following them. That doesn’t mean you’re going to have trouble using Scala. It just means that the difference between functions and methods is subtle enough such that explanations tend to dig into deep parts of the language.

###Inheritance
```scala
class ScientificCalculator(brand: String) extends Calculator(brand) {
  def log(m: Double, base: Double) = math.log(m) / math.log(base)
}
```
See Also Effective Scala points out that a Type alias is better than extends if the subclass isn’t actually different from the superclass. A Tour of Scala describes Subclassing.
```scala
###Overloading methods
class EvenMoreScientificCalculator(brand: String) extends ScientificCalculator(brand) {
  def log(m: Int): Double = log(m, math.exp(1))
}
```
###Abstract Classes
You can define an abstract class, a class that defines some methods but does not implement them. Instead, subclasses that extend the abstract class define these methods. You can’t create an instance of an abstract class.
```scala
scala> abstract class Shape {
     |   def getArea():Int    // subclass should define this
     | }
defined class Shape

scala> class Circle(r: Int) extends Shape {
     |   def getArea():Int = { r * r * 3 }
     | }
defined class Circle

scala> val s = new Shape
<console>:8: error: class Shape is abstract; cannot be instantiated
       val s = new Shape
               ^

scala> val c = new Circle(2)
c: Circle = Circle@65c0035b
```
###Traits
traits are collections of fields and behaviors that you can extend or mixin to your classes.
```scala
trait Car {
  val brand: String
}

trait Shiny {
  val shineRefraction: Int
}
class BMW extends Car {
  val brand = "BMW"
}
```
One class can extend several traits using the with keyword:

```scala
class BMW extends Car with Shiny {
  val brand = "BMW"
  val shineRefraction = 12
}
```
See Also Effective Scala has opinions about trait.

When do you want a Trait instead of an Abstract Class? If you want to define an interface-like type, you might find it difficult to choose between a trait or an abstract class. Either one lets you define a type with some behavior, asking extenders to define some other behavior. Some rules of thumb:

Favor using traits. It’s handy that a class can extend several traits; a class can extend only one class.
If you need a constructor parameter, use an abstract class. Abstract class constructors can take parameters; trait constructors can’t. For example, you can’t say trait t(i: Int) {}; the i parameter is illegal.
You are not the first person to ask this question. See fuller answers at stackoverflow:Scala traits vs abstract classes, Difference between Abstract Class and Trait, and Programming in Scala: To trait, or not to trait?

###Types
Earlier, you saw that we defined a function that took an Int which is a type of Number. Functions can also be generic and work on any type. When that occurs, you’ll see a type parameter introduced with the square bracket syntax. Here’s an example of a Cache of generic Keys and Values.
```scala
trait Cache[K, V] {
  def get(key: K): V
  def put(key: K, value: V)
  def delete(key: K)
}
```
Methods can also have type parameters introduced.
```scala
def remove[K](key: K)
```


apply methods
apply methods give you a nice syntactic sugar for when a class or object has one main use.
```scala
scala> class Foo {}
defined class Foo

scala> object FooMaker {
     |   def apply() = new Foo
     | }
defined module FooMaker

scala> val newFoo = FooMaker()
newFoo: Foo = Foo@5b83f762
or

scala> class Bar {
     |   def apply() = 0
     | }
defined class Bar

scala> val bar = new Bar
bar: Bar = Bar@47711479

scala> bar()
res8: Int = 0
```
Here our instance object looks like we’re calling a method. More on that later!

###Objects
Objects are used to hold single instances of a class. Often used for factories.
```scala
object Timer {
  var count = 0

  def currentCount(): Long = {
    count += 1
    count
  }
}
```
How to use
```scala
scala> Timer.currentCount()
res0: Long = 1
```
Classes and Objects can have the same name. The object is called a ‘Companion Object’. We commonly use Companion Objects for Factories.

Here is a trivial example that only serves to remove the need to use ‘new’ to create an instance.
```scala
class Bar(foo: String)

object Bar {
  def apply(foo: String) = new Bar(foo)
}
```
###Functions are Objects
In Scala, we talk about object-functional programming often. What does that mean? What is a Function, really?

A Function is a set of traits. Specifically, a function that takes one argument is an instance of a Function1 trait. This trait defines the apply() syntactic sugar we learned earlier, allowing you to call an object like you would a function.
```scala
scala> object addOne extends Function1[Int, Int] {
     |   def apply(m: Int): Int = m + 1
     | }
defined module addOne

scala> addOne(1)
res2: Int = 2
```
There is Function0 through 22. Why 22? It’s an arbitrary magic number. I’ve never needed a function with more than 22 arguments so it seems to work out.

The syntactic sugar of apply helps unify the duality of object and functional programming. You can pass classes around and use them as functions and functions are just instances of classes under the covers.

Does this mean that every time you define a method in your class, you’re actually getting an instance of Function*? No, methods in classes are methods. Methods defined standalone in the repl are Function* instances.

Classes can also extend Function and those instances can be called with ().
```scala
scala> class AddOne extends Function1[Int, Int] {
     |   def apply(m: Int): Int = m + 1
     | }
defined class AddOne

scala> val plusOne = new AddOne()
plusOne: AddOne = <function1>

scala> plusOne(1)
res0: Int = 2
```
A nice short-hand for extends Function1[Int, Int] is extends (Int => Int)
```scala
class AddOne extends (Int => Int) {
  def apply(m: Int): Int = m + 1
}
```
###Packages
You can organize your code inside of packages.

package com.twitter.example
at the top of a file will declare everything in the file to be in that package.

Values and functions cannot be outside of a class or object. Objects are a useful tool for organizing static functions.
```scala
package com.twitter.example

object colorHolder {
  val BLUE = "Blue"
  val RED = "Red"
}```
Now you can access the members directly

println("the color is: " + com.twitter.example.colorHolder.BLUE)
Notice what the scala repl says when you define this object:
```scala
scala> object colorHolder {
     |   val Blue = "Blue"
     |   val Red = "Red"
     | }
defined module colorHolder```
This gives you a small hint that the designers of Scala designed objects to be part of Scala’s module system.

 ### Matching
One of the most useful parts of Scala.

Matching on values
```scala
val times = 1

times match {
  case 1 => "one"
  case 2 => "two"
  case _ => "some other number"
}```
Matching with guards
```scala
times match {
  case i if i == 1 => "one"
  case i if i == 2 => "two"
  case _ => "some other number"
}```
Notice how we captured the value in the variable ‘i’.

The _ in the last case statement is a wildcard; it ensures that we can handle any statement. Otherwise you will suffer a runtime error if you pass in a number that doesn’t match. We discuss this more later.

See Also Effective Scala has opinions about when to use tching and pattern matching formatting. A Tour of Scala describes Pattern Matching

###Matching on type
You can use match to handle values of different types differently.
```scala
def bigger(o: Any): Any = {
  o match {
    case i: Int if i < 0 => i - 1
    case i: Int => i + 1
    case d: Double if d < 0.0 => d - 0.1
    case d: Double => d + 0.1
    case text: String => text + "s"
  }
}```
###Matching on class members
Remember our calculator from earlier.

Let’s classify them according to type.

Here’s the painful way first.
```scala
def calcType(calc: Calculator) = calc match {
  case _ if calc.brand == "hp" && calc.model == "20B" => "financial"
  case _ if calc.brand == "hp" && calc.model == "48G" => "scientific"
  case _ if calc.brand == "hp" && calc.model == "30B" => "business"
  case _ => "unknown"
}```
Wow, that’s painful. Thankfully Scala provides some nice tools specifically for this.

###Case Classes
case classes are used to conveniently store and match on the contents of a class. You can construct them without using new.
```scala
scala> case class Calculator(brand: String, model: String)
defined class Calculator

scala> val hp20b = Calculator("hp", "20b")
hp20b: Calculator = Calculator(hp,20b)

case classes automatically have equality and nice toString methods based on the constructor arguments.

scala> val hp20b = Calculator("hp", "20b")
hp20b: Calculator = Calculator(hp,20b)

scala> val hp20B = Calculator("hp", "20b")
hp20B: Calculator = Calculator(hp,20b)

scala> hp20b == hp20B
res6: Boolean = true
case classes can have methods just like normal classes.
```
###CASE CLASSES WITH PATTERN MATCHING
case classes are designed to be used with pattern matching. Let’s simplify our calculator classifier example from earlier.
```scala
val hp20b = Calculator("hp", "20B")
val hp30b = Calculator("hp", "30B")

def calcType(calc: Calculator) = calc match {
  case Calculator("hp", "20B") => "financial"
  case Calculator("hp", "48G") => "scientific"
  case Calculator("hp", "30B") => "business"
  case Calculator(ourBrand, ourModel) => "Calculator: %s %s is of unknown type".format(ourBrand, ourModel)
}
Other alternatives for that last match

  case Calculator(_, _) => "Calculator of unknown type"
OR we could simply not specify that it’s a Calculator at all.
  case _ => "Calculator of unknown type"
OR we could re-bind the matched value with another name
  case c@Calculator(_, _) => "Calculator: %s of unknown type".format(c)```
###Exceptions
Exceptions are available in Scala via a try-catch-finally syntax that uses pattern matching.
```scala
try {
  remoteCalculatorService.add(1, 2)
} catch {
  case e: ServerIsDownException => log.error(e, "the remote calculator service is unavailable. should have kept your trusty HP.")
} finally {
  remoteCalculatorService.close()
}
trys are also expression-oriented

val result: Int = try {
  remoteCalculatorService.add(1, 2)
} catch {
  case e: ServerIsDownException => {
    log.error(e, "the remote calculator service is unavailable. should have kept your trusty HP.")
    0
  }
} finally {
  remoteCalculatorService.close()
}
```

##Collections

###Basic Data Structures

Scala provides some nice collections.

See Also Effective Scala has opinions about how to use collections.
```scala
###Lists
scala> val numbers = List(1, 2, 3, 4)
numbers: List[Int] = List(1, 2, 3, 4)```
###Sets
Sets have no duplicates
```scala
scala> Set(1, 1, 2)
res0: scala.collection.immutable.Set[Int] = Set(1, 2)```
###Tuple
A tuple groups together simple logical collections of items without using a class.
```scala
scala> val hostPort = ("localhost", 80)
hostPort: (String, Int) = (localhost, 80)```
Unlike case classes, they don’t have named accessors, instead they have accessors that are named by their position and is 1-based rather than 0-based.
```scala
scala> hostPort._1
res0: String = localhost

scala> hostPort._2
res1: Int = 80
Tuples fit with pattern matching nicely.

hostPort match {
  case ("localhost", port) => ...
  case (host, port) => ...
}```
Tuple has some special sauce for simply making Tuples of 2 values: ->
```scala
scala> 1 -> 2
res0: (Int, Int) = (1,2)```
See Also Effective Scala has opinions about destructuring bindings (“unpacking” a tuple).

###Maps
It can hold basic datatypes.
```scala
Map(1 -> 2)
Map("foo" -> "bar")```
This looks like special syntax but remember back to our discussion of Tuple that -> can be use to create Tuples.

Map() also uses that variable argument syntax we learned back in Lesson #1: Map(1 -> "one", 2 -> "two") which expands into Map((1, "one"), (2, "two")) with the first element being the key and the second being the value of the Map.

Maps can themselves contain Maps or even functions as values.
```scala
Map(1 -> Map("foo" -> "bar"))
Map("timesTwo" -> { timesTwo(_) })```
###Option
Option is a container that may or may not hold something.

The basic interface for Option looks like:
```scala
trait Option[T] {
  def isDefined: Boolean
  def get: T
  def getOrElse(t: T): T
}```
Option itself is generic and has two subclasses: Some[T] or None

Let’s look at an example of how Option is used:

Map.get uses Option for its return type. Option tells you that the method might not return what you’re asking for.
```scala
scala> val numbers = Map("one" -> 1, "two" -> 2)
numbers: scala.collection.immutable.Map[java.lang.String,Int] = Map(one -> 1, two -> 2)

scala> numbers.get("two")
res0: Option[Int] = Some(2)

scala> numbers.get("three")
res1: Option[Int] = None```
Now our data appears trapped in this Option. How do we work with it?

A first instinct might be to do something conditionally based on the isDefined method.
```scala
// We want to multiply the number by two, otherwise return 0.
val result = if (res1.isDefined) {
  res1.get * 2
} else {
  0
}```
We would suggest that you use either getOrElse or pattern matching to work with this result.

getOrElse lets you easily define a default value.
```scala
val result = res1.getOrElse(0) * 2
Pattern matching fits naturally with Option.

val result = res1 match {
  case Some(n) => n * 2
  case None => 0
}```
See Also Effective Scala has opinions about Options.

###Functional Combinators

List(1, 2, 3) map squared applies the function squared to the elements of the list, returning a new list, perhaps List(1, 4, 9). We call operations like map combinators. (If you’d like a better definition, you might like Explanation of combinators on Stackoverflow.) Their most common use is on the standard data structures.

###map
Evaluates a function over each element in the list, returning a list with the same number of elements.
```scala
scala> numbers.map((i: Int) => i * 2)
res0: List[Int] = List(2, 4, 6, 8)```
or pass in a function (the Scala compiler automatically converts our method to a function)

```scala
scala> def timesTwo(i: Int): Int = i * 2
timesTwo: (i: Int)Int

scala> numbers.map(timesTwo)
res0: List[Int] = List(2, 4, 6, 8)```
###foreach
foreach is like map but returns nothing. foreach is intended for side-effects only.
```scala
scala> numbers.foreach((i: Int) => i * 2)```
returns nothing.

You can try to store the return in a value but it’ll be of type Unit (i.e. void)
```scala
scala> val doubled = numbers.foreach((i: Int) => i * 2)
doubled: Unit = ()```
###filter
removes any elements where the function you pass in evaluates to false. Functions that return a Boolean are often called predicate functions.
```scala
scala> numbers.filter((i: Int) => i % 2 == 0)
res0: List[Int] = List(2, 4)
scala> def isEven(i: Int): Boolean = i % 2 == 0
isEven: (i: Int)Boolean

scala> numbers.filter(isEven _)
res2: List[Int] = List(2, 4)```
###zip
zip aggregates the contents of two lists into a single list of pairs.
```scala
scala> List(1, 2, 3).zip(List("a", "b", "c"))
res0: List[(Int, String)] = List((1,a), (2,b), (3,c))```
partition
partition splits a list based on where it falls with respect to a predicate function.
```scala
scala> val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
scala> numbers.partition(_ % 2 == 0)
res0: (List[Int], List[Int]) = (List(2, 4, 6, 8, 10),List(1, 3, 5, 7, 9))```
###find
find returns the first element of a collection that matches a predicate function.
```scala
scala> numbers.find((i: Int) => i > 5)
res0: Option[Int] = Some(6)```
###drop & dropWhile
drop drops the first i elements
```scala
scala> numbers.drop(5)
res0: List[Int] = List(6, 7, 8, 9, 10)```
dropWhile removes the first elements that match a predicate function. For example, if we dropWhile odd numbers from our list of numbers, 1 gets dropped (but not 3 which is “shielded” by 2).
```scala
scala> numbers.dropWhile(_ % 2 != 0)
res0: List[Int] = List(2, 3, 4, 5, 6, 7, 8, 9, 10)```
###foldLeft
```scala
scala> numbers.foldLeft(0)((m: Int, n: Int) => m + n)
res0: Int = 55```
0 is the starting value (Remember that numbers is a List[Int]), and m
acts as an accumulator.

Seen visually:
```scala
scala> numbers.foldLeft(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }
m: 0 n: 1
m: 1 n: 2
m: 3 n: 3
m: 6 n: 4
m: 10 n: 5
m: 15 n: 6
m: 21 n: 7
m: 28 n: 8
m: 36 n: 9
m: 45 n: 10
res0: Int = 55
foldRight```
Is the same as foldLeft except it runs in the opposite direction.
```scala
scala> numbers.foldRight(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }
m: 10 n: 0
m: 9 n: 10
m: 8 n: 19
m: 7 n: 27
m: 6 n: 34
m: 5 n: 40
m: 4 n: 45
m: 3 n: 49
m: 2 n: 52
m: 1 n: 54
res0: Int = 55```
###flatten
flatten collapses one level of nested structure.
```scala
scala> List(List(1, 2), List(3, 4)).flatten
res0: List[Int] = List(1, 2, 3, 4)```
###flatMap
flatMap is a frequently used combinator that combines mapping and flattening. flatMap takes a function that works on the nested lists and then concatenates the results back together.
```scala
scala> val nestedNumbers = List(List(1, 2), List(3, 4))
nestedNumbers: List[List[Int]] = List(List(1, 2), List(3, 4))

scala> nestedNumbers.flatMap(x => x.map(_ * 2))
res0: List[Int] = List(2, 4, 6, 8)```
Think of it as short-hand for mapping and then flattening:
```scala
scala> nestedNumbers.map((x: List[Int]) => x.map(_ * 2)).flatten
res1: List[Int] = List(2, 4, 6, 8)```
that example calling map and then flatten is an example of the “combinator”-like nature of these functions.

See Also Effective Scala has opinions about flatMap.

Generalized functional combinators
Now we’ve learned a grab-bag of functions for working with collections.

What we’d like is to be able to write our own functional combinators.

Interestingly, every functional combinator shown above can be written on top of fold. Let’s see some examples.
```scala
def ourMap(numbers: List[Int], fn: Int => Int): List[Int] = {
  numbers.foldRight(List[Int]()) { (x: Int, xs: List[Int]) =>
    fn(x) :: xs
  }
}

scala> ourMap(numbers, timesTwo(_))
res0: List[Int] = List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)```
Why List[Int]()? Scala wasn’t smart enough to realize that you wanted an empty list of Ints to accumulate into.

###Map?
All of the functional combinators shown work on Maps, too. Maps can be thought of as a list of pairs so the functions you write work on a pair of the keys and values in the Map.
```
scala> val extensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
extensions: scala.collection.immutable.Map[String,Int] = Map((steve,100), (bob,101), (joe,201))```
Now filter out every entry whose phone extension is lower than 200.
```scala
scala> extensions.filter((namePhone: (String, Int)) => namePhone._2 < 200)
res0: scala.collection.immutable.Map[String,Int] = Map((steve,100), (bob,101))```
Because it gives you a tuple, you have to pull out the keys and values with their positional accessors. Yuck!

Lucky us, we can actually use a pattern match to extract the key and value nicely.
```scala
scala> extensions.filter({case (name, extension) => extension < 200})
res0: scala.collection.immutable.Map[String,Int] = Map((steve,100), (bob,101))
```

##Pattern matching & functional composition

###Function Composition
Let’s make two aptly-named functions:
```scala
scala> def f(s: String) = "f(" + s + ")"
f: (String)java.lang.String

scala> def g(s: String) = "g(" + s + ")"
g: (String)java.lang.String```
###compose
compose makes a new function that composes other functions f(g(x))
```scala
scala> val fComposeG = f _ compose g _
fComposeG: (String) => java.lang.String = <function>

scala> fComposeG("yay")
res0: java.lang.String = f(g(yay))```
###andThen
andThen is like compose, but calls the first function and then the second, g(f(x))
```scala
scala> val fAndThenG = f _ andThen g _
fAndThenG: (String) => java.lang.String = <function>

scala> fAndThenG("yay")
res1: java.lang.String = g(f(yay))```
###Currying vs Partial Application
case statements
So just what are case statements?
It’s a subclass of function called a PartialFunction.

What is a collection of multiple case statements?
They are multiple PartialFunctions composed together.

###Understanding PartialFunction
A function works for every argument of the defined type. In other words, a function defined as (Int) => String takes any Int and returns a String.

A Partial Function is only defined for certain values of the defined type. A Partial Function (Int) => String might not accept every Int.

isDefinedAt is a method on PartialFunction that can be used to determine if the PartialFunction will accept a given argument.

Note PartialFunction is unrelated to a partially applied function that we talked about earlier.

See Also Effective Scala has opinions about PartialFunction.
```scala
scala> val one: PartialFunction[Int, String] = { case 1 => "one" }
one: PartialFunction[Int,String] = <function1>

scala> one.isDefinedAt(1)
res0: Boolean = true

scala> one.isDefinedAt(2)
res1: Boolean = false
You can apply a partial function.

scala> one(1)
res2: String = one```
PartialFunctions can be composed with something new, called orElse, that reflects whether the PartialFunction is defined over the supplied argument.
```scala
scala> val two: PartialFunction[Int, String] = { case 2 => "two" }
two: PartialFunction[Int,String] = <function1>

scala> val three: PartialFunction[Int, String] = { case 3 => "three" }
three: PartialFunction[Int,String] = <function1>

scala> val wildcard: PartialFunction[Int, String] = { case _ => "something else" }
wildcard: PartialFunction[Int,String] = <function1>

scala> val partial = one orElse two orElse three orElse wildcard
partial: PartialFunction[Int,String] = <function1>

scala> partial(5)
res24: String = something else

scala> partial(3)
res25: String = three

scala> partial(2)
res26: String = two

scala> partial(1)
res27: String = one

scala> partial(0)
res28: String = something else```
The mystery of case.
Last week we saw something curious. We saw a case statement used where a function is normally used.
```scala
scala> case class PhoneExt(name: String, ext: Int)
defined class PhoneExt

scala> val extensions = List(PhoneExt("steve", 100), PhoneExt("robey", 200))
extensions: List[PhoneExt] = List(PhoneExt(steve,100), PhoneExt(robey,200))

scala> extensions.filter { case PhoneExt(name, extension) => extension < 200 }
res0: List[PhoneExt] = List(PhoneExt(steve,100))
```

##Type & polymorphism basics

###What are static types? Why are they useful?
According to Pierce: “A type system is a syntactic method for automatically checking the absence of certain erroneous behaviors by classifying program phrases according to the kinds of values they compute.”

Types allow you to denote function domain & codomains. For example, from mathematics, we are used to seeing:
```scala
f: R -> N```
this tells us that function “f” maps values from the set of real numbers to values of the set of natural numbers.

In the abstract, this is exactly what concrete types are. Type systems give us some more powerful ways to express these sets.

Given these annotations, the compiler can now statically (at compile time) verify that the program is sound. That is, compilation will fail if values (at runtime) will not comply to the constraints imposed by the program.

Generally speaking, the typechecker can only guarantee that unsound programs do not compile. It cannot guarantee that every sound program will compile.

With increasing expressiveness in type systems, we can produce more reliable code because it allows us to prove invariants about our program before it even runs (modulo bugs in the types themselves, of course!). Academia is pushing the limits of expressiveness very hard, including value-dependent types!

Note that all type information is removed at compile time. It is no longer needed. This is called erasure.

###Types in Scala
Scala’s powerful type system allows for very rich expression. Some of its chief features are:

parametric polymorphism roughly, generic programming
(local) type inference roughly, why you needn’t say val i: Int = 12: Int
existential quantification roughly, defining something for some unnamed type
views we’ll learn these next week; roughly, “castability” of values of one type to another
Parametric polymorphism
Polymorphism is used in order to write generic code (for values of different types) without compromising static typing richness.

For example, without parametric polymorphism, a generic list data structure would always look like this (and indeed it did look like this in Java prior to generics):
```scala
scala> 2 :: 1 :: "bar" :: "foo" :: Nil
res5: List[Any] = List(2, 1, bar, foo)
Now we cannot recover any type information about the individual members.

scala> res5.head
res6: Any = 2```
And so our application would devolve into a series of casts (“asInstanceOf[]”) and we would lack type safety (because these are all dynamic).

Polymorphism is achieved through specifying type variables.
```scala
scala> def drop1[A](l: List[A]) = l.tail
drop1: [A](l: List[A])List[A]

scala> drop1(List(1,2,3))
res1: List[Int] = List(2, 3)```
Scala has rank-1 polymorphism
Roughly, this means that there are some type concepts you’d like to express in Scala that are “too generic” for the compiler to understand. Suppose you had some function
```scala
def toList[A](a: A) = List(a)```
which you wished to use generically:
```scala
def foo[A, B](f: A => List[A], b: B) = f(b)```
This does not compile, because all type variables have to be fixed at the invocation site. Even if you “nail down” type B,
```scala
def foo[A](f: A => List[A], i: Int) = f(i)```
…you get a type mismatch.

###Type inference
A traditional objection to static typing is that it has much syntactic overhead. Scala alleviates this by providing type inference.

The classic method for type inference in functional programming languages is Hindley-Milner, and it was first employed in ML.

Scala’s type inference system works a little differently, but it’s similar in spirit: infer constraints, and attempt to unify a type.

In Scala, for example, you cannot do the following:
```scala
scala> { x => x }
<console>:7: error: missing parameter type
       { x => x }```
Whereas in OCaml, you can:
```scala
# fun x -> x;;
- : 'a -> 'a = <fun>```
In scala all type inference is local. Scala considers one expression at a time. For example:
```scala
scala> def id[T](x: T) = x
id: [T](x: T)T

scala> val x = id(322)
x: Int = 322

scala> val x = id("hey")
x: java.lang.String = hey

scala> val x = id(Array(1,2,3,4))
x: Array[Int] = Array(1, 2, 3, 4)```
Types are now preserved, The Scala compiler infers the type parameter for us. Note also how we did not have to specify the return type explicitly.

###Variance
Scala’s type system has to account for class hierarchies together with polymorphism. Class hierarchies allow the expression of subtype relationships. A central question that comes up when mixing OO with polymorphism is: if T’ is a subclass of T, is Container[T’] considered a subclass of Container[T]? Variance annotations allow you to express the following relationships between class hierarchies & polymorphic types:

###Meaning	Scala notation
covariant	C[T’] is a subclass of C[T]	[+T]
contravariant	C[T] is a subclass of C[T’]	[-T]
invariant	C[T] and C[T’] are not related	[T]
The subtype relationship really means: for a given type T, if T’ is a subtype, can you substitute it?
```scala
scala> class Covariant[+A]
defined class Covariant

scala> val cv: Covariant[AnyRef] = new Covariant[String]
cv: Covariant[AnyRef] = Covariant@4035acf6

scala> val cv: Covariant[String] = new Covariant[AnyRef]
<console>:6: error: type mismatch;
 found   : Covariant[AnyRef]
 required: Covariant[String]
       val cv: Covariant[String] = new Covariant[AnyRef]
                                   ^
scala> class Contravariant[-A]
defined class Contravariant

scala> val cv: Contravariant[String] = new Contravariant[AnyRef]
cv: Contravariant[AnyRef] = Contravariant@49fa7ba

scala> val fail: Contravariant[AnyRef] = new Contravariant[String]
<console>:6: error: type mismatch;
 found   : Contravariant[String]
 required: Contravariant[AnyRef]
       val fail: Contravariant[AnyRef] = new Contravariant[String]
                                     ^
Contravariance seems strange. When is it used? Somewhat surprising!

trait Function1 [-T1, +R] extends AnyRef
If you think about this from the point of view of substitution, it makes a lot of sense. Let’s first define a simple class hierarchy:

scala> class Animal { val sound = "rustle" }
defined class Animal

scala> class Bird extends Animal { override val sound = "call" }
defined class Bird

scala> class Chicken extends Bird { override val sound = "cluck" }
defined class Chicken```
Suppose you need a function that takes a Bird param:
```scala
scala> val getTweet: (Bird => String) = // TODO```
The standard animal library has a function that does what you want, but it takes an Animal parameter instead. In most situations, if you say “I need a ___, I have a subclass of ___”, you’re OK. But function parameters are contravariant. If you need a function that takes a Bird and you have a function that takes an Chicken, that function would choke on a Duck. But a function that takes an Animal is OK:
```scala
scala> val getTweet: (Bird => String) = ((a: Animal) => a.sound )
getTweet: Bird => String = <function1>```
A function’s return value type is covariant. If you need a function that returns a Bird but have a function that returns a Chicken, that’s great.
```scala
scala> val hatch: (() => Bird) = (() => new Chicken )
hatch: () => Bird = <function0>```
###Bounds
Scala allows you to restrict polymorphic variables using bounds. These bounds express subtype relationships.
```scala
scala> def cacophony[T](things: Seq[T]) = things map (_.sound)
<console>:7: error: value sound is not a member of type parameter T
       def cacophony[T](things: Seq[T]) = things map (_.sound)
                                                        ^

scala> def biophony[T <: Animal](things: Seq[T]) = things map (_.sound)
biophony: [T <: Animal](things: Seq[T])Seq[java.lang.String]

scala> biophony(Seq(new Chicken, new Bird))
res5: Seq[java.lang.String] = List(cluck, call)```
Lower type bounds are also supported; they come in handy with contravariance and clever covariance. List[+T] is covariant; a list of Birds is a list of Animals. List defines an operator ::(elem T) that returns a new List with elem prepended. The new List has the same type as the original:
```scala
scala> val flock = List(new Bird, new Bird)
flock: List[Bird] = List(Bird@7e1ec70e, Bird@169ea8d2)

scala> new Chicken :: flock
res53: List[Bird] = List(Chicken@56fbda05, Bird@7e1ec70e, Bird@169ea8d2)```
List also defines ::[B >: T](x: B) which returns a List[B]. Notice the B >: T. That specifies type B as a superclass of T. That lets us do the right thing when prepending an Animal to a List[Bird]:
```scala
scala> new Animal :: flock
res59: List[Animal] = List(Animal@11f8d3a8, Bird@7e1ec70e, Bird@169ea8d2)```
Note that the return type is List[Animal].

###Quantification
Sometimes you do not care to be able to name a type variable, for example:
```scala
scala> def count[A](l: List[A]) = l.size
count: [A](List[A])Int```
Instead you can use “wildcards”:
```scala
scala> def count(l: List[_]) = l.size
count: (List[_])Int```
This is shorthand for:
```scala
scala> def count(l: List[T forSome { type T }]) = l.size
count: (List[T forSome { type T }])Int```
Note that quantification can get tricky:
```scala
scala> def drop1(l: List[_]) = l.tail
drop1: (List[_])List[Any]```
Suddenly we lost type information! To see what’s going on, revert to the heavy-handed syntax:
```scala
scala> def drop1(l: List[T forSome { type T }]) = l.tail
drop1: (List[T forSome { type T }])List[T forSome { type T }]```
We can’t say anything about T because the type does not allow it.

You may also apply bounds to wildcard type variables:
```scala
scala> def hashcodes(l: Seq[_ <: AnyRef]) = l map (_.hashCode)
hashcodes: (Seq[_ <: AnyRef])Seq[Int]

scala> hashcodes(Seq(1,2,3))
<console>:7: error: type mismatch;
 found   : Int(1)
 required: AnyRef
Note: primitive types are not implicitly converted to AnyRef.
You can safely force boxing by casting x.asInstanceOf[AnyRef].
       hashcodes(Seq(1,2,3))
                     ^

scala> hashcodes(Seq("one", "two", "three"))
res1: Seq[Int] = List(110182, 115276, 110339486)
```

##Simple Build Tool

###About SBT
SBT is a modern build tool. While it is written in Scala and provides many Scala conveniences, it is a general purpose build tool.

###Why SBT?
Sane(ish) dependency management
Ivy for dependency management
Only-update-on-request model
Full Scala language support for creating tasks
Continuous command execution
Launch REPL in project context
###Getting Started
Download the jar
Create an sbt shell script that calls the jar, e.g.
java -Xmx512M -jar sbt-launch.jar "$@"
make sure it’s executable and in your path
run sbt to create your project
```scala
[local ~/projects]$ sbt
Project does not exist, create new project? (y/N/s) y
Name: sample
Organization: com.twitter
Version [1.0]: 1.0-SNAPSHOT
Scala version [2.7.7]: 2.8.1
sbt version [0.7.4]:      
Getting Scala 2.7.7 ...
:: retrieving :: org.scala-tools.sbt#boot-scala
	confs: [default]
	2 artifacts copied, 0 already retrieved (9911kB/221ms)
Getting org.scala-tools.sbt sbt_2.7.7 0.7.4 ...
:: retrieving :: org.scala-tools.sbt#boot-app
	confs: [default]
	15 artifacts copied, 0 already retrieved (4096kB/167ms)
[success] Successfully initialized directory structure.
Getting Scala 2.8.1 ...
:: retrieving :: org.scala-tools.sbt#boot-scala
	confs: [default]
	2 artifacts copied, 0 already retrieved (15118kB/386ms)
[info] Building project sample 1.0-SNAPSHOT against Scala 2.8.1
[info]    using sbt.DefaultProject with sbt 0.7.4 and Scala 2.7.7

```
Note that it’s good form to start out with a SNAPSHOT version of your project.
```scala
###Project Layout
project – project definition files
project/build/ yourproject .scala – the main project definition file
project/build.properties – project, sbt and scala version definitions
src/main – your app code goes here, in a subdirectory indicating the
code’s language (e.g. src/main/scala, src/main/java)
src/main/resources – static files you want added to your jar
(e.g. logging config)
src/test – like src/main, but for tests
lib_managed – the jar files your project depends on. Populated by sbt update
target – the destination for generated stuff (e.g. generated thrift
code, class files, jars)```
Adding Some Code
We’ll be creating a simple JSON parser for simple tweets. Add the following code to
src/main/scala/com/twitter/sample/SimpleParser.scala
```scala
package com.twitter.sample

case class SimpleParsed(id: Long, text: String)

class SimpleParser {

  val tweetRegex = "\"id\":(.*),\"text\":\"(.*)\"".r

  def parse(str: String) = {
    tweetRegex.findFirstMatchIn(str) match {
      case Some(m) => {
        val id = str.substring(m.start(1), m.end(1)).toInt
        val text = str.substring(m.start(2), m.end(2))
        Some(SimpleParsed(id, text))
      }
      case _ => None
    }
  }
}```
This is ugly and buggy, but should compile.

###Testing in the Console
SBT can be used both as a command line script and as a build console. We’ll be primarily using it as a build console, but most commands can be run standalone by passing the command as an argument to SBT, e.g.

sbt test
Note that if a command takes arguments, you need to quote the entire
argument path, e.g.

sbt 'test-only com.twitter.sample.SampleSpec'

It’s weird that way.

Anyway. To start working with our code, launch sbt
```scala
[local ~/projects/sbt-sample]$ sbt
[info] Building project sample 1.0-SNAPSHOT against Scala 2.8.1
[info]    using sbt.DefaultProject with sbt 0.7.4 and Scala 2.7.7
> 
SBT allows you to start a Scala REPL with all your project
dependencies loaded. It compiles your project source before launching
the console, providing us a quick way to bench test our parser.

> console
[info] 
[info] == compile ==
[info]   Source analysis: 0 new/modified, 0 indirectly invalidated, 0 removed.
[info] Compiling main sources...
[info] Nothing to compile.
[info]   Post-analysis: 3 classes.
[info] == compile ==
[info] 
[info] == copy-test-resources ==
[info] == copy-test-resources ==
[info] 
[info] == test-compile ==
[info]   Source analysis: 0 new/modified, 0 indirectly invalidated, 0 removed.
[info] Compiling test sources...
[info] Nothing to compile.
[info]   Post-analysis: 0 classes.
[info] == test-compile ==
[info] 
[info] == copy-resources ==
[info] == copy-resources ==
[info] 
[info] == console ==
[info] Starting scala interpreter...
[info] ```
Welcome to Scala version 2.8.1.final (Java HotSpot(TM) 64-Bit Server VM, Java 1.6.0_22).
Type in expressions to have them evaluated.
Type :help for more information.
```scala
scala> 
Our code has compiled, and we’re provide the typical Scala prompt. We’ll create a new parser, an exemplar tweet, and ensure it “works”

scala> import com.twitter.sample._            
import com.twitter.sample._

scala> val tweet = """{"id":1,"text":"foo"}"""
tweet: java.lang.String = {"id":1,"text":"foo"}

scala> val parser = new SimpleParser          
parser: com.twitter.sample.SimpleParser = com.twitter.sample.SimpleParser@71060c3e

scala> parser.parse(tweet)                    
res0: Option[com.twitter.sample.SimpleParsed] = Some(SimpleParsed(1,"foo"}))

scala> ```
Adding Dependencies
Our simple parser works for this very small set of inputs, but we want to add tests and break it. The first step is adding the specs test library and a real JSON parser to our project. To do this we have to go beyond the default SBT project layout and create a project.

SBT considers Scala files in the project/build directory to be project definitions. Add the following to project/build/SampleProject.scala
```scala
import sbt._

class SampleProject(info: ProjectInfo) extends DefaultProject(info) {
  val jackson = "org.codehaus.jackson" % "jackson-core-asl" % "1.6.1"
  val specs = "org.scala-tools.testing" % "specs_2.8.0" % "1.6.5" % "test"
}```
A project definition is an SBT class. In our case we extend SBT’s DefaultProject.

You declare dependencies by specifying a val that is a dependency. SBT uses reflection to scan all the dependency vals in your project and build up a dependency tree at build time. The syntax here may be new, but this is equivalent to the maven dependency
```scala
<dependency>
  <groupId>org.codehaus.jackson</groupId>
  <artifactId>jackson-core-asl</artifactId>
  <version>1.6.1</version>
</dependency>
<dependency>
  <groupId>org.scala-tools.testing</groupId>
  <artifactId>specs_2.8.0</artifactId>
  <version>1.6.5</version>
  <scope>test</scope>
</dependency>```
Now we can pull down dependencies for our project. From the command line (not the sbt console), run sbt update
```scala
[local ~/projects/sbt-sample]$ sbt update
[info] Building project sample 1.0-SNAPSHOT against Scala 2.8.1
[info]    using SampleProject with sbt 0.7.4 and Scala 2.7.7
[info] 
[info] == update ==
[info] :: retrieving :: com.twitter#sample_2.8.1 [sync]
[info] 	confs: [compile, runtime, test, provided, system, optional, sources, javadoc]
[info] 	1 artifacts copied, 0 already retrieved (2785kB/71ms)
[info] == update ==
[success] Successful.
[info] 
[info] Total time: 1 s, completed Nov 24, 2010 8:47:26 AM
[info] 
[info] Total session time: 2 s, completed Nov 24, 2010 8:47:26 AM
[success] Build completed successfully.```

You’ll see that sbt retrieved the specs library. You’ll now also have a lib_managed directory, and lib_managed/scala_2.8.1/test will have specs_2.8.0-1.6.5.jar

###Adding Tests
Now that we have a test library added, put the following code in
```scala
src/test/scala/com/twitter/sample/SimpleParserSpec.scala

package com.twitter.sample

import org.specs._

object SimpleParserSpec extends Specification {
  "SimpleParser" should {
    val parser = new SimpleParser()
    "work with basic tweet" in {
      val tweet = """{"id":1,"text":"foo"}"""
      parser.parse(tweet) match {
        case Some(parsed) => {
          parsed.text must be_==("foo")
          parsed.id must be_==(1)
        }
        case _ => fail("didn't parse tweet")
      }
    }
  }
}```
In the sbt console, run test
```scala
> test
[info] 
[info] == compile ==
[info]   Source analysis: 0 new/modified, 0 indirectly invalidated, 0 removed.
[info] Compiling main sources...
[info] Nothing to compile.
[info]   Post-analysis: 3 classes.
[info] == compile ==
[info] 
[info] == test-compile ==
[info]   Source analysis: 0 new/modified, 0 indirectly invalidated, 0 removed.
[info] Compiling test sources...
[info] Nothing to compile.
[info]   Post-analysis: 10 classes.
[info] == test-compile ==
[info] 
[info] == copy-test-resources ==
[info] == copy-test-resources ==
[info] 
[info] == copy-resources ==
[info] == copy-resources ==
[info] 
[info] == test-start ==
[info] == test-start ==
[info] 
[info] == com.twitter.sample.SimpleParserSpec ==
[info] SimpleParserSpec
[info] SimpleParser should
[info]   + work with basic tweet
[info] == com.twitter.sample.SimpleParserSpec ==
[info] 
[info] == test-complete ==
[info] == test-complete ==
[info] 
[info] == test-finish ==
[info] Passed: : Total 1, Failed 0, Errors 0, Passed 1, Skipped 0
[info]  
[info] All tests PASSED.
[info] == test-finish ==
[info] 
[info] == test-cleanup ==
[info] == test-cleanup ==
[info] 
[info] == test ==
[info] == test ==
[success] Successful.
[info] 
[info] Total time: 0 s, completed Nov 24, 2010 8:54:45 AM
> ```
Our test works! Now we can add more. One of the nice things SBT provides is a way to run triggered actions. Prefacing an action with a tilde starts up a loop that runs the action any time source files change. Lets run ~test and see what happens.
```scala
[info] == test ==
[success] Successful.
[info] 
[info] Total time: 0 s, completed Nov 24, 2010 8:55:50 AM
1. Waiting for source changes... (press enter to interrupt)
Now let’s add the following test cases

    "reject a non-JSON tweet" in {
      val tweet = """"id":1,"text":"foo""""
      parser.parse(tweet) match {
        case Some(parsed) => fail("didn't reject a non-JSON tweet")
        case e => e must be_==(None)
      }
    }

    "ignore nested content" in {
      val tweet = """{"id":1,"text":"foo","nested":{"id":2}}"""
      parser.parse(tweet) match {
        case Some(parsed) => {
          parsed.text must be_==("foo")
          parsed.id must be_==(1)
        }
        case _ => fail("didn't parse tweet")
      }
    }

    "fail on partial content" in {
      val tweet = """{"id":1}"""
      parser.parse(tweet) match {
        case Some(parsed) => fail("didn't reject a partial tweet")
        case e => e must be_==(None)
      }
    }```
After we save our file, SBT detects our changes, runs tests, and informs us our parser is lame
```scala
[info] == com.twitter.sample.SimpleParserSpec ==
[info] SimpleParserSpec
[info] SimpleParser should
[info]   + work with basic tweet
[info]   x reject a non-JSON tweet
[info]     didn't reject a non-JSON tweet (Specification.scala:43)
[info]   x ignore nested content
[info]     'foo","nested":{"id' is not equal to 'foo' (SimpleParserSpec.scala:31)
[info]   + fail on partial content
```
So let’s rework our JSON parser to be real
```scala
package com.twitter.sample

import org.codehaus.jackson._
import org.codehaus.jackson.JsonToken._

case class SimpleParsed(id: Long, text: String)

class SimpleParser {

  val parserFactory = new JsonFactory()

  def parse(str: String) = {
    val parser = parserFactory.createJsonParser(str)
    if (parser.nextToken() == START_OBJECT) {
      var token = parser.nextToken()
      var textOpt:Option[String] = None
      var idOpt:Option[Long] = None
      while(token != null) {
        if (token == FIELD_NAME) {
          parser.getCurrentName() match {
            case "text" => {
              parser.nextToken()
              textOpt = Some(parser.getText())
            }
            case "id" => {
              parser.nextToken()
              idOpt = Some(parser.getLongValue())
            }
            case _ => // noop
          }
        }
        token = parser.nextToken()
      }
      if (textOpt.isDefined && idOpt.isDefined) {
        Some(SimpleParsed(idOpt.get, textOpt.get))
      } else {
        None
      }
    } else {
      None
    }
  }
}
```
This is a simple Jackson parser. When we save, SBT recompiles our code and reruns our tests. Getting better!
```scala
info] SimpleParser should
[info]   + work with basic tweet
[info]   + reject a non-JSON tweet
[info]   x ignore nested content
[info]     '2' is not equal to '1' (SimpleParserSpec.scala:32)
[info]   + fail on partial content
[info] == com.twitter.sample.SimpleParserSpec ==
Uh oh. We need to check for nested objects. Let’s add some ugly
guards to our token reading loop.

  def parse(str: String) = {
    val parser = parserFactory.createJsonParser(str)
    var nested = 0
    if (parser.nextToken() == START_OBJECT) {
      var token = parser.nextToken()
      var textOpt:Option[String] = None
      var idOpt:Option[Long] = None
      while(token != null) {
        if (token == FIELD_NAME && nested == 0) {
          parser.getCurrentName() match {
            case "text" => {
              parser.nextToken()
              textOpt = Some(parser.getText())
            }
            case "id" => {
              parser.nextToken()
              idOpt = Some(parser.getLongValue())
            }
            case _ => // noop
          }
        } else if (token == START_OBJECT) {
          nested += 1
        } else if (token == END_OBJECT) {
          nested -= 1
        }
        token = parser.nextToken()
      }
      if (textOpt.isDefined && idOpt.isDefined) {
        Some(SimpleParsed(idOpt.get, textOpt.get))
      } else {
        None
      }
    } else {
      None
    }
  }
  ```
And… it works!

###Packaging and Publishing
At this point we can run the package command to generate a jar file. However we may want to share our jar with other teams. To do this we’ll build on StandardProject, which gives us a big head start.

The first step is include StandardProject as an SBT plugin. Plugins are a way to introduce dependencies to your build, rather than your project. These dependencies are defined in project/plugins/Plugins.scala. Add the following to the Plugins.scala file.
```scala
import sbt._

class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val twitterMaven = "twitter.com" at "http://maven.twttr.com/"
  val defaultProject = "com.twitter" % "standard-project" % "0.7.14"
}
```
Note that we’ve specified a maven repository as well as a dependency. That’s because the standard project library is hosted by us, which isn’t one of the default repos sbt checks.

We’ll also update our project definition to extend StandardProject, include an SVN publishing trait, and define the repository we wish to publish to. Alter SampleProject.scala to the following
```scala
import sbt._
import com.twitter.sbt._

class SampleProject(info: ProjectInfo) extends StandardProject(info) with SubversionPublisher {
  val jackson = "org.codehaus.jackson" % "jackson-core-asl" % "1.6.1"
  val specs = "org.scala-tools.testing" % "specs_2.8.0" % "1.6.5" % "test"

  override def subversionRepository = Some("http://svn.local.twitter.com/maven/")
}
```
Now if we run the publish action we’ll see the following
```scala
[info] == deliver ==
IvySvn Build-Version: null
IvySvn Build-DateTime: null
[info] :: delivering :: com.twitter#sample;1.0-SNAPSHOT :: 1.0-SNAPSHOT :: release :: Wed Nov 24 10:26:45 PST 2010
[info] 	delivering ivy file to /Users/mmcbride/projects/sbt-sample/target/ivy-1.0-SNAPSHOT.xml
[info] == deliver ==
[info] 
[info] == make-pom ==
[info] Wrote /Users/mmcbride/projects/sbt-sample/target/sample-1.0-SNAPSHOT.pom
[info] == make-pom ==
[info] 
[info] == publish ==
[info] :: publishing :: com.twitter#sample
[info] Scheduling publish to http://svn.local.twitter.com/maven/com/twitter/sample/1.0-SNAPSHOT/sample-1.0-SNAPSHOT.jar
[info] 	published sample to com/twitter/sample/1.0-SNAPSHOT/sample-1.0-SNAPSHOT.jar
[info] Scheduling publish to http://svn.local.twitter.com/maven/com/twitter/sample/1.0-SNAPSHOT/sample-1.0-SNAPSHOT.pom
[info] 	published sample to com/twitter/sample/1.0-SNAPSHOT/sample-1.0-SNAPSHOT.pom
[info] Scheduling publish to http://svn.local.twitter.com/maven/com/twitter/sample/1.0-SNAPSHOT/ivy-1.0-SNAPSHOT.xml
[info] 	published ivy to com/twitter/sample/1.0-SNAPSHOT/ivy-1.0-SNAPSHOT.xml
[info] Binary diff deleting com/twitter/sample/1.0-SNAPSHOT
[info] Commit finished r977 by 'mmcbride' at Wed Nov 24 10:26:47 PST 2010
[info] Copying from com/twitter/sample/.upload to com/twitter/sample/1.0-SNAPSHOT
[info] Binary diff finished : r978 by 'mmcbride' at Wed Nov 24 10:26:47 PST 2010
[info] == publish ==
[success] Successful.
[info] 
[info] Total time: 4 s, completed Nov 24, 2010 10:26:47 AM
And (after some time), we can go to binaries.local.twitter.com to see our published jar.
```
Adding Tasks
Tasks are Scala functions. The simplest way to add a task is to include a val in your project definition using the task method, e.g.

lazy val print = task {log.info("a test action"); None}
If you want dependencies and a description you can add them like this

lazy val print = task {log.info("a test action"); None}.dependsOn(compile) describedAs("prints a line after compile")
If we reload our project and run the print action we’ll see the following
```scala
> print
[info] 
[info] == print ==
[info] a test action
[info] == print ==
[success] Successful.
[info] 
[info] Total time: 0 s, completed Nov 24, 2010 11:05:12 AM

```
So it works. If you’re defining a task in a single project this works just fine. However if you’re defining this in a plugin it’s fairly inflexible. I may want to

lazy val print = printAction
def printAction = printTask.dependsOn(compile) describedAs("prints a line after compile")
def printTask = task {log.info("a test action"); None}
This allows consumers to override the task itself, the dependencies and/or description of the task, or the action. Most built in SBT actions follow this pattern. As an example, we can modify the builtin package task to print the current timestamp by doing the following

lazy val printTimestamp = task { log.info("current time is " + System.currentTimeMillis); None}
override def packageAction = super.packageAction.dependsOn(printTimestamp)


##More Collections

###The basics
###List
###The standard linked list.
```scala
scala> List(1, 2, 3)
res0: List[Int] = List(1, 2, 3)```
You can cons them up as you would expect in a functional language.
```scala
scala> 1 :: 2 :: 3 :: Nil
res1: List[Int] = List(1, 2, 3)```
See also API doc

###Set
Sets have no duplicates
```scala
scala> Set(1, 1, 2)
res2: scala.collection.immutable.Set[Int] = Set(1, 2)```
See also API doc

###Seq
Sequences have a defined order.
```scala
scala> Seq(1, 1, 2)
res3: Seq[Int] = List(1, 1, 2)```
(Notice that returned a List. Seq is a trait; List is a lovely implementation of Seq. There’s a factory object Seq which, as you see here, creates Lists.)

See also API doc

###Map
Maps are key value containers.
```scala
scala> Map('a' -> 1, 'b' -> 2)
res4: scala.collection.immutable.Map[Char,Int] = Map((a,1), (b,2))```
See also API doc

###The Hierarchy
These are all traits, both the mutable and immutable packages have implementations of these as well as specialized implementations.

###Traversable
All collections can be traversed. This trait defines standard function combinators. These combinators are written in terms of foreach, which collections must implement.

See Also API doc

###Iterable
Has an iterator() method to give you an Iterator over the elements.

See Also API doc

###Seq
Sequence of items with ordering.

See Also API doc

###Set
A collection of items with no duplicates.

See Also API doc

###Map
Key Value Pairs.

See Also API doc

###The methods
###Traversable
All of these methods below are available all the way down. The argument and return types types won’t always look the same as subclasses are free to override them.

def head : A
def tail : Traversable[A]
Here is where the Functional Combinators are defined.
```scala
def map [B] (f: (A) => B) : CC[B]```

returns a collection with every element transformed by f
```scala
def foreach[U](f: Elem => U): Unit```

executes f over each element in a collection.
```scala
def find (p: (A) => Boolean) : Option[A]```

returns the first element that matches the predicate function
```scala
def filter (p: (A) => Boolean) : Traversable[A]```

returns a collection with all elements matching the predicate function

###Partitioning:
```scala
def partition (p: (A) ⇒ Boolean) : (Traversable[A], Traversable[A])```

Splits a collection into two halves based on a predicate function
```scala
def groupBy [K] (f: (A) => K) : Map[K, Traversable[A]]```

###Conversion:

Interestingly, you can convert one collection type to another.
```scala
def toArray : Array[A]
def toArray [B >: A] (implicit arg0: ClassManifest[B]) : Array[B]
def toBuffer [B >: A] : Buffer[B]
def toIndexedSeq [B >: A] : IndexedSeq[B]
def toIterable : Iterable[A]
def toIterator : Iterator[A]
def toList : List[A]
def toMap [T, U] (implicit ev: <:<[A, (T, U)]) : Map[T, U]
def toSeq : Seq[A]
def toSet [B >: A] : Set[B]
def toStream : Stream[A]
def toString () : String
def toTraversable : Traversable[A]```
Let’s convert a Map to an Array. You get an Array of the Key Value pairs.
```scala
scala> Map(1 -> 2).toArray
res41: Array[(Int, Int)] = Array((1,2))```
###Iterable
Adds access to an iterator.
```scala
  def iterator: Iterator[A]```
What does an Iterator give you?
```scala
def hasNext(): Boolean
def next(): A```
This is very Java-esque. You often won’t see iterators used in Scala, you are much more likely to see the functional combinators or a for-comprehension used.

###Set
```scala
  def contains(key: A): Boolean
  def +(elem: A): Set[A]
  def -(elem: A): Set[A]```
###Map
Sequence of key and value pairs with lookup by key.

Pass a List of Pairs into apply() like so
```scala
scala> Map("a" -> 1, "b" -> 2)
res0: scala.collection.immutable.Map[java.lang.String,Int] = Map((a,1), (b,2))```
Or also like:
```scala
scala> Map(("a", 2), ("b", 2))
res0: scala.collection.immutable.Map[java.lang.String,Int] = Map((a,2), (b,2))```
###DIGRESSION
What is ->? That isn’t special syntax, it’s a method that returns a Tuple.
```scala
scala> "a" -> 2

res0: (java.lang.String, Int) = (a,2)
Remember, that is just sugar for

scala> "a".->(2)

res1: (java.lang.String, Int) = (a,2)
You can also build one up via ++

scala> Map.empty ++ List(("a", 1), ("b", 2), ("c", 3))
res0: scala.collection.immutable.Map[java.lang.String,Int] = Map((a,1), (b,2), (c,3))```
###Commonly-used subclasses
HashSet and HashMap Quick lookup, the most commonly used forms of these collections. HashSet API, HashMap API

TreeMap A subclass of SortedMap, it gives you ordered access. TreeMap API

Vector Fast random selection and fast updates. Vector API
```scala
scala> IndexedSeq(1, 2, 3)
res0: IndexedSeq[Int] = Vector(1, 2, 3)```
Range Ordered sequence of Ints that are spaced apart. You will often see this used where a counting for-loop was used before. Range API
```scala
scala> for (i <- 1 to 3) { println(i) }
1
2
3
Ranges have the standard functional combinators available to them.

scala> (1 to 3).map { i => i }
res0: scala.collection.immutable.IndexedSeq[Int] = Vector(1, 2, 3)```
###Defaults
Using apply methods on the traits will give you an instance of the default implementation, For instance, Iterable(1, 2) returns a List as its default implementation.
```scala
scala> Iterable(1, 2)

res0: Iterable[Int] = List(1, 2)
Same with Seq, as we saw earlier

scala> Seq(1, 2)
res3: Seq[Int] = List(1, 2)

scala> Iterable(1, 2)
res1: Iterable[Int] = List(1, 2)

scala> Sequence(1, 2)
warning: there were deprecation warnings; re-run with -deprecation for details
res2: Seq[Int] = List(1, 2)
Set

scala> Set(1, 2)
res31: scala.collection.immutable.Set[Int] = Set(1, 2)```
###Some descriptive traits
IndexedSeq fast random-access of elements and a fast length operation. API doc

LinearSeq fast access only to the first element via head, but also has a fast tail operation. API doc

###Mutable vs. Immutable
###immutable

###Pros

Can’t change in multiple threads
###Con

Can’t change at all
Scala allows us to be pragmatic, it encourages immutability but does not penalize us for needing mutability. This is very similar to var vs. val. We always start with val and move back to var when required.

We favor starting with the immutable versions of collections but switching to the mutable ones if performance dictates. Using immutable collections means you won’t accidentally change things in multiple threads.

###Mutable
All of the above classes we’ve discussed were immutable. Let’s discuss the commonly used mutable collections.

HashMap defines getOrElseUpdate, += HashMap API
```scala
scala> val numbers = collection.mutable.Map(1 -> 2)
numbers: scala.collection.mutable.Map[Int,Int] = Map((1,2))

scala> numbers.get(1)
res0: Option[Int] = Some(2)

scala> numbers.getOrElseUpdate(2, 3)
res54: Int = 3

scala> numbers
res55: scala.collection.mutable.Map[Int,Int] = Map((2,3), (1,2))

scala> numbers += (4 -> 1)
res56: numbers.type = Map((2,3), (4,1), (1,2))```
ListBuffer and ArrayBuffer Defines += ListBuffer API, ArrayBuffer API

LinkedList and DoubleLinkedList LinkedList API, DoubleLinkedList API

PriorityQueue API doc

Stack and ArrayStack Stack API, ArrayStack API

StringBuilder Interestingly, StringBuilder is a collection. API doc

###Life with Java
You can easily move between Java and Scala collection types using conversions that are available in the JavaConverters package. It decorates commonly-used Java collections with asScala methods and Scala collections with asJava methods.
```scala
   import scala.collection.JavaConverters._
   val sl = new scala.collection.mutable.ListBuffer[Int]
   val jl : java.util.List[Int] = sl.asJava
   val sl2 : scala.collection.mutable.Buffer[Int] = jl.asScala
   assert(sl eq sl2)
   ```
Two-way conversions:
```scala
scala.collection.Iterable <=> java.lang.Iterable
scala.collection.Iterable <=> java.util.Collection
scala.collection.Iterator <=> java.util.{ Iterator, Enumeration }
scala.collection.mutable.Buffer <=> java.util.List
scala.collection.mutable.Set <=> java.util.Set
scala.collection.mutable.Map <=> java.util.{ Map, Dictionary }
scala.collection.mutable.ConcurrentMap <=> java.util.concurrent.ConcurrentMap
In addition, the following one way conversions are provided:

scala.collection.Seq => java.util.List
scala.collection.mutable.Seq => java.util.List
scala.collection.Set => java.util.Set
scala.collection.Map => java.util.Map
```
