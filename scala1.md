# scala-simple-project

https://blog.udemy.com/scala-tutorial-getting-started-with-scala/

http://www.brunton-spall.co.uk/post/2011/12/02/map-map-and-flatmap-in-scala/

https://twitter.github.io/scala_school/

###Introduction
-------------------------

- Modern multi-paradigm programming language
- Designed to express common programming patterns in a concise, elegant, type-safe way
- Smoothly integrates features of object-oriented and functional languages

####Object oriented
- Every value is an object
- Types and behavior of objects are described by classes and traits
- Classes are extended by subclassing
- Flexible mixing-based composition mechanism as a clean replacement for multiple inheritance

####Functionnal
- Every function is a value
- Lightweight syntax for defining anonymous functions
- Supports higher-order functions
- Allows functions to be nested
- Supports currying
- Scala’s case classes and its built-in support for pattern matching model algebraic types used in many functional programming languages
- Singleton objects provide a convenient way to group functions that aren’t members of a class.
- Ideal for developing applications like web services

####Statically typed
Scala is equipped with an expressive type system that enforces statically that abstractions are used in a safe and coherent manner. In particular, the type system supports:

- generic classes
- variance annotations
- upper and lower type bounds,
- inner classes and abstract types as object members
- compound types
- explicitly typed self references
- implicit parameters and conversions
- polymorphic methods

A local type inference mechanism takes care that the user is not required to annotate the program with redundant type information. In combination, these features provide a powerful basis for the safe reuse of programming abstractions and for the type-safe extension of software.


####Extensible
In practice, the development of domain-specific applications often requires domain-specific language extensions. Scala provides a unique combination of language mechanisms that make it easy to smoothly add new language constructs in form of libraries:

any method may be used as an infix or postfix operator
closures are constructed automatically depending on the expected type (target typing).
A joint use of both features facilitates the definition of new statements without extending the syntax and without using macro-like meta-programming facilities.

Scala is designed to interoperate well with the popular Java 2 Runtime Environment (JRE). In particular, the interaction with the mainstream object-oriented Java programming language is as smooth as possible. Newer Java features like annotations and Java generics have direct analogues in Scala. Those Scala features without Java analogues, such as default and named parameters, compile as close to Java as they can reasonably come. Scala has the same compilation model (separate compilation, dynamic class loading) like Java and allows access to thousands of existing high-quality libraries.

###Declarations
-------------------------

####Variable declaration
```scala
 val x:Int = 11
```

#####immutable (read-only):
```scala
val distance: Double = 22
val myarr:Array[String]=new Array(6)
myarr = new Array(5) //This will give a reassignment to val error.
myarr (0)="Scala" //This does not give an error.
```

#####mutable (read-write):
```scala
var temp:Int=22
age: Int=35
 ```
There is an exception to this rule when initializing vals and vars.
When they are used as constructor parameters, they will be initialized when the object is instantiated. Also, derived classes can override vals declared inside the parent classes.

####Scala and the Usage of Semicolons
```scala
str = "hello world"; println (str)
```

####Statements, Expressions and Operators
```scala
var myValue = {
  val a=2
  val b = 3
  a+b
}

val z = println(105)
Output:
105
z: Unit = ()
```
The call to println doesn’t produce a value, so the expression doesn’t either. Scala has a special type for an expression that doesn’t produce a value: it is called a Unit.

####Compound Expressions
```scala
val isOfficeHour = {
  val officeStarts = 10
  val officeCloses = 20
  println("Office hours: "+
  officeStarts + " - " + officeCloses)
  if(time>= officeStarts && time <= officeCloses){
    true
  }else{
    false
  }
}
```
it returns true


Some constructs that are represented as statements in other languages are actually expressions in Scala. For example:

if-else structures are statements in most languages, but are expressions in Scala. The type of an if-else expression is the common supertype of all the branches.
while loops are expressions in Scala that return a Unit. Similarly, for loops (not to be confused with for expressions) also return Unit.
throw, used to generate exceptions, are expressions of type Nothing, which is a bottom type to all types in Scala.


####Operator Overloading

Scala also allows operator overloading. Operators are typically things such as +, -, and !.

As a programmer, you would frequently use operators to perform arithmetic on numbers, or for manipulating Strings. Operator overloading, just like method overloading, allows you to redefine their behaviour for a particular type, and give them meaning for your own custom classes.


####Loops
------------------
#####for loop
```scala
var a = 5
for(a <- 1 to 10)
{
  println("Value of number : "+ a)
}

//output
a: Int = 5
Value of number : 1
Value of number : 2
Value of number : 3
Value of number : 4
Value of number : 5
Value of number : 6
Value of number : 7
Value of number : 8
Value of number : 9
Value of number : 10
res2: Unit = ()
```

#####loop break
```scala
object BreakTest{
  def main() {
    var number = 0
    var numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    
    var loop = new Breaks
    loop.breakable {
      for (number <- numList) {
        println("value : " + number)
        if (number == 5) {
          loop.break()
        }
      }
    }
    println("after the loop")
  }
}
BreakTest.main()
```

####Functional Programming in Scala
----------------------------------
Scala is a hybrid object-functional language: it supports both object-oriented and functional programming paradigms.
You get the best of both worlds with Scala – the ability to use both.

In Scala it is possible to have nested functions (functions within functions), a function returning a function, a function taking a function as parameters, and so on.

The functional programming paradigm was explicitly created to support a purely functional approach to problem solving.

In the world of functional/declarative style of programming, the goal when we develop is as follows:

Focus on what to achieve in our code and ask the language to decide how to achieve the target
Deal with non-mutating objects

(see link above for java version)
The equivalent Scala code using functional programming is shown in the code below:
```scala
var myList =  List(1,2,3,4)
//(listElement,sum)=>listElement+sum is a lambda expression
//(listElement,sum) is an anonymous function (with 2 parameters)
//listElement+sum is the function body
//Executed by each iteration, types of params and
// return types are autom. inferred by Scala runtime
val mySum = myList.reduce((listElement,sum)=>listElement+sum)
println(mySum)

//Output
myList: List[Int] = List(1, 2, 3, 4)
mySum: Int = 10
10

```

#####Functions
```scala
def functionName ([list of parameters]) : [return type] = {
   function body
   return [expr]
}
```
Here are a few basic facts about Scala functions:

def is keyword indicating that it is a function.
return type could be any valid scala data type, and the list of parameters would be a list of variables separated by comma; the list of parameters and return type are optional.
Much like in Java, a return statement can be used (not mandatory, as you can see in the screenshot below) along with an expression in case function returns a value.
Methods are implicitly declared abstract if you omit the equals sign and method body. The enclosing type is then itself abstract.
A function which does not return anything can return Unit, which is equivalent to void in Java and indicates that function does not return anything. The functions which do not return anything in Scala are called procedures.


#####Class construction
```scala
class Customer(var fullName:String, var orderValue:Double){
  println("Inside primary constructor")
  private val address = "My Address"
  var age = 0

  //some methods
  override def toString = s"$fullName has order value of $orderValue"
  def printAddress { println(s"Address = $address")}
  def calculateOrderTaxValue(orderValue:Double)={
    val tax = orderValue * 0.5 ; tax;
  }
  
  //call to method
  printAddress
  
  //auxiliary constructor
  def this(fullName:String){
    this(fullName,1850)
  }
  println("still in the constructor")
}

val cust = new Customer("Cliff Richard",1850)
println(cust.calculateOrderTaxValue(1850))

```

#####Getters/Setters
```scala
class Employee(){
  private var _age = 0
  var name = ""
  
  //Getter
  def age = _age
  
  /Setter
  def age_= (value:Int) : Unit = _age = value
}

var emp = new Employee
emp.age=25
println(emp.age)
```

#####Class inheritance
```scala
class Vehicle(val enginePower:Integer,
              val wheelCount:Integer,
              val chassisNumber:String){

  def this(wheelCount:Integer, chassisNumber:String)=
  this(100,wheelCount,chassisNumber)

  def this(chassisNumber:String)=
    this(105,4,chassisNumber)
}

class Car(enginePower:Integer,
          wheelCount:Integer,chassisNumber: String,val wiperType:String)
  extends Vehicle(enginePower,wheelCount,chassisNumber){

  override def toString =
    s"$enginePower: $wheelCount, $chassisNumber $wiperType"

}

class MotorBike(wheelCount:Integer,chassisNumber: String,val handleType:String)
  extends Vehicle(wheelCount,chassisNumber){

    override def toString =
    s"$enginePower: $wheelCount, $chassisNumber $handleType"
  }

def show(vehicle: Vehicle)=
s"engine power : ${vehicle.enginePower}, count of wheel: ${vehicle.wheelCount}, chassis number ${vehicle.chassisNumber}"

show(new Vehicle(1000,4,"h1fg87"))
show(new Car(800,4,"h1fg87","double edged foldable"))
show(new MotorBike(2,"h1fg87","high bent"))
```

#####Scala object

Scala’s object keyword defines something that looks roughly like a class, except you can’t create instances of an object – only one instance exists. By creating an object, one can logically group methods and fields
```scala
object X {
  val mynumber = 2
  def func1 = this.mynumber * 10
  def func2 = this.mynumber / 20
}

//val obj = new X // Will not work !
X.mynumber
X.func1
X.func2

//Output
res0: Int = 2
res1: Int = 20
res2: Int = 0

```

#####Companion object
The object keyword allows one to create a companion object for a class. The difference between an ordinary object and a companion object is that the latter has the same name as the name of a class. Hence, an association between the companion object is created.

The screenshot example below shows that anyVal has only a single piece of storage (no matter how many instances are created) and that x1 and x2 are both accessing that same memory. To access elements of the companion object from methods of the class, you must give the name of the companion object (AnyObject), as in lines 8 and 9.

```scala
object AnyObject{
  var anyVal:Int = 1 // single copy
}

class AnyObject {
  def multiply(n:Int) = {
    AnyObject.anyVal = AnyObject.anyVal * n
    AnyObject.anyVal }
  }

var x1 = new AnyObject
var x2 = new AnyObject
x1.multiply(2)  //2
x2.multiply(2)  //4
x1.multiply(2)  //8  
```

#####Traits
A Trait is a different approach to creating classes: it allows inheritance in small chunks rather than all in a single go, which is what happens when creating a subclass.

Traits are small, logical concepts that allow one to easily “mix in” ideas to create a class.

For this reason, they are often called mixin types. Trait typically represent a singular concept such as Color, Softness, Brittleness, etc.

Important facts about Trait

A trait definition looks like a class, but uses the trait keyword instead of class.
To combine traits into a class, you always begin with the extends keyword, then add additional traits using the with keyword.
A class can only inherit from a single concrete or abstract base class, but it can combine as many traits as you want. If there is no concrete or abstract base class, you still start with the extends keyword for the first trait, followed by the with keyword for the remaining traits.
A freestanding trait cannot be instantiated; a Trait does not have a full-fledged constructor.
If any of the fields and/or methods that a class extends lack definition or implementation, then Scala will insist that you include the abstract keyword.
Here we have some of the simplest possible Trait and Class definitions:

```scala
trait Hardness
trait Brittleness
trait Color

class Material

class Metal extends Material with Hardness
 with Texture with Hardness
 
class Plastic extends Brittleness with Color
 with Hardness
```
Traits can inherit from abstract or concrete classes. Traits can also inherit from other traits – see the screenshot below. Here are example of some traits that have defined some member variables:
```scala
trait SuperTrait {
  def spt = "abc"
}

trait Subtrait1 extends SuperTrait{
  def sut1 = "pqr"
}

trait Subtrait2 extends Subtrait1{
  def sut2 = "fgh"
}

class AnyClass extends Subtrait2
val myval = new AnyClass
myval.spt
myval.sut1
myval.sut2

//Output
defined class AnyClass
myval: AnyClass = AnyClass@39ce1cbb
res0: String = abc
res1: String = pqr
res2: String = fgh
```

###Collections
----------
**Easy to use:** A small vocabulary of 20-50 methods is enough to solve most collection problems in a couple of operations. No need to wrap your head around complicated looping structures or recursions. Persistent collections and side-effect-free operations mean that you need not worry about accidentally corrupting existing collections with new data. Interference between iterators and collection updates is eliminated.

**Concise:** You can achieve with a single word what used to take one or several loops. You can express functional operations with lightweight syntax and combine operations effortlessly, so that the result feels like a custom algebra.

**Safe:** This one has to be experienced to sink in. The statically typed and functional nature of Scala’s collections means that the overwhelming majority of errors you might make are caught at compile-time. The reason is that (1) the collection operations themselves are heavily used and therefore well tested. (2) the usages of the collection operation make inputs and output explicit as function parameters and results. (3) These explicit inputs and outputs are subject to static type checking. The bottom line is that the large majority of misuses will manifest themselves as type errors. It’s not at all uncommon to have programs of several hundred lines run at first try.

**Fast:** Collection operations are tuned and optimized in the libraries. As a result, using collections is typically quite efficient. You might be able to do a little bit better with carefully hand-tuned data structures and operations, but you might also do a lot worse by making some suboptimal implementation decisions along the way. What’s more, collections have been recently adapted to parallel execution on multi-cores. Parallel collections support the same operations as sequential ones, so no new operations need to be learned and no code needs to be rewritten. You can turn a sequential collection into a parallel one simply by invoking the par method.

**Universal:** Collections provide the same operations on any type where it makes sense to do so. So you can achieve a lot with a fairly small vocabulary of operations. For instance, a string is conceptually a sequence of characters. Consequently, in Scala collections, strings support all sequence operations. The same holds for arrays.

**Example:** Here’s one line of code that demonstrates many of the advantages of Scala’s collections.
```scala
val (minors, adults) = people partition (_.age < 18)

```
It’s immediately clear what this operation does: It partitions a collection of people into minors and adults depending on their age. Because the partition method is defined in the root collection type TraversableLike, this code works for any kind of collection, including arrays. The resulting minors and adults collections will be of the same type as the people collection.

This code is much more concise than the one to three loops required for traditional collection processing (three loops for an array, because the intermediate results need to be buffered somewhere else). Once you have learned the basic collection vocabulary you will also find writing this code is much easier and safer than writing explicit loops. Furthermore, the partition operation is quite fast, and will get even faster on parallel collections on multi-cores. (Parallel collections have been released as part of Scala 2.9.)

####Mutable and Immutable Collections
-** mutable collection :** you can change, add, or remove elements of a collection
- **immutable collection :** never change ( You have still operations that simulate additions, removals, or updates, but those operations will in each case return a new collection and leave the old collection unchanged.)
- **All collection classes are found in the package scala.collection or one of its sub-packages mutable, immutable, and generic.** Most collection classes needed by client code exist in three variants, which are located in packages scala.collection, scala.collection.immutable, and scala.collection.mutable, respectively. Each variant has different characteristics with respect to mutability.
- 


##Summary







































# scala-simple-project

https://blog.udemy.com/scala-tutorial-getting-started-with-scala/

http://www.brunton-spall.co.uk/post/2011/12/02/map-map-and-flatmap-in-scala/

https://twitter.github.io/scala_school/

###Introduction
-------------------------

- Modern multi-paradigm programming language
- Designed to express common programming patterns in a concise, elegant, type-safe way
- Smoothly integrates features of object-oriented and functional languages

####Object oriented
- Every value is an object
- Types and behavior of objects are described by classes and traits
- Classes are extended by subclassing
- Flexible mixing-based composition mechanism as a clean replacement for multiple inheritance

####Functionnal
- Every function is a value
- Lightweight syntax for defining anonymous functions
- Supports higher-order functions
- Allows functions to be nested
- Supports currying
- Scala’s case classes and its built-in support for pattern matching model algebraic types used in many functional programming languages
- Singleton objects provide a convenient way to group functions that aren’t members of a class.
- Ideal for developing applications like web services

####Statically typed
Scala is equipped with an expressive type system that enforces statically that abstractions are used in a safe and coherent manner. In particular, the type system supports:

- generic classes
- variance annotations
- upper and lower type bounds,
- inner classes and abstract types as object members
- compound types
- explicitly typed self references
- implicit parameters and conversions
- polymorphic methods

A local type inference mechanism takes care that the user is not required to annotate the program with redundant type information. In combination, these features provide a powerful basis for the safe reuse of programming abstractions and for the type-safe extension of software.


####Extensible
In practice, the development of domain-specific applications often requires domain-specific language extensions. Scala provides a unique combination of language mechanisms that make it easy to smoothly add new language constructs in form of libraries:

any method may be used as an infix or postfix operator
closures are constructed automatically depending on the expected type (target typing).
A joint use of both features facilitates the definition of new statements without extending the syntax and without using macro-like meta-programming facilities.

Scala is designed to interoperate well with the popular Java 2 Runtime Environment (JRE). In particular, the interaction with the mainstream object-oriented Java programming language is as smooth as possible. Newer Java features like annotations and Java generics have direct analogues in Scala. Those Scala features without Java analogues, such as default and named parameters, compile as close to Java as they can reasonably come. Scala has the same compilation model (separate compilation, dynamic class loading) like Java and allows access to thousands of existing high-quality libraries.

###Declarations
-------------------------

####Variable declaration
```scala
 val x:Int = 11
```

#####immutable (read-only):
```scala
val distance: Double = 22
val myarr:Array[String]=new Array(6)
myarr = new Array(5) //This will give a reassignment to val error.
myarr (0)="Scala" //This does not give an error.
```

#####mutable (read-write):
```scala
var temp:Int=22
age: Int=35
 ```
There is an exception to this rule when initializing vals and vars.
When they are used as constructor parameters, they will be initialized when the object is instantiated. Also, derived classes can override vals declared inside the parent classes.

####Scala and the Usage of Semicolons
```scala
str = "hello world"; println (str)
```

####Statements, Expressions and Operators
```scala
var myValue = {
  val a=2
  val b = 3
  a+b
}

val z = println(105)
Output:
105
z: Unit = ()
```
The call to println doesn’t produce a value, so the expression doesn’t either. Scala has a special type for an expression that doesn’t produce a value: it is called a Unit.

####Compound Expressions
```scala
val isOfficeHour = {
  val officeStarts = 10
  val officeCloses = 20
  println("Office hours: "+
  officeStarts + " - " + officeCloses)
  if(time>= officeStarts && time <= officeCloses){
    true
  }else{
    false
  }
}
```
it returns true


Some constructs that are represented as statements in other languages are actually expressions in Scala. For example:

if-else structures are statements in most languages, but are expressions in Scala. The type of an if-else expression is the common supertype of all the branches.
while loops are expressions in Scala that return a Unit. Similarly, for loops (not to be confused with for expressions) also return Unit.
throw, used to generate exceptions, are expressions of type Nothing, which is a bottom type to all types in Scala.


####Operator Overloading

Scala also allows operator overloading. Operators are typically things such as +, -, and !.

As a programmer, you would frequently use operators to perform arithmetic on numbers, or for manipulating Strings. Operator overloading, just like method overloading, allows you to redefine their behaviour for a particular type, and give them meaning for your own custom classes.


####Loops
------------------
#####for loop
```scala
var a = 5
for(a <- 1 to 10)
{
  println("Value of number : "+ a)
}

//output
a: Int = 5
Value of number : 1
Value of number : 2
Value of number : 3
Value of number : 4
Value of number : 5
Value of number : 6
Value of number : 7
Value of number : 8
Value of number : 9
Value of number : 10
res2: Unit = ()
```

#####loop break
```scala
object BreakTest{
  def main() {
    var number = 0
    var numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    
    var loop = new Breaks
    loop.breakable {
      for (number <- numList) {
        println("value : " + number)
        if (number == 5) {
          loop.break()
        }
      }
    }
    println("after the loop")
  }
}
BreakTest.main()
```

####Functional Programming in Scala
----------------------------------
Scala is a hybrid object-functional language: it supports both object-oriented and functional programming paradigms.
You get the best of both worlds with Scala – the ability to use both.

In Scala it is possible to have nested functions (functions within functions), a function returning a function, a function taking a function as parameters, and so on.

The functional programming paradigm was explicitly created to support a purely functional approach to problem solving.

In the world of functional/declarative style of programming, the goal when we develop is as follows:

Focus on what to achieve in our code and ask the language to decide how to achieve the target
Deal with non-mutating objects

(see link above for java version)
The equivalent Scala code using functional programming is shown in the code below:
```scala
var myList =  List(1,2,3,4)
//(listElement,sum)=>listElement+sum is a lambda expression
//(listElement,sum) is an anonymous function (with 2 parameters)
//listElement+sum is the function body
//Executed by each iteration, types of params and
// return types are autom. inferred by Scala runtime
val mySum = myList.reduce((listElement,sum)=>listElement+sum)
println(mySum)

//Output
myList: List[Int] = List(1, 2, 3, 4)
mySum: Int = 10
10

```

#####Functions
```scala
def functionName ([list of parameters]) : [return type] = {
   function body
   return [expr]
}
```
Here are a few basic facts about Scala functions:

def is keyword indicating that it is a function.
return type could be any valid scala data type, and the list of parameters would be a list of variables separated by comma; the list of parameters and return type are optional.
Much like in Java, a return statement can be used (not mandatory, as you can see in the screenshot below) along with an expression in case function returns a value.
Methods are implicitly declared abstract if you omit the equals sign and method body. The enclosing type is then itself abstract.
A function which does not return anything can return Unit, which is equivalent to void in Java and indicates that function does not return anything. The functions which do not return anything in Scala are called procedures.


#####Class construction
```scala
class Customer(var fullName:String, var orderValue:Double){
  println("Inside primary constructor")
  private val address = "My Address"
  var age = 0

  //some methods
  override def toString = s"$fullName has order value of $orderValue"
  def printAddress { println(s"Address = $address")}
  def calculateOrderTaxValue(orderValue:Double)={
    val tax = orderValue * 0.5 ; tax;
  }
  
  //call to method
  printAddress
  
  //auxiliary constructor
  def this(fullName:String){
    this(fullName,1850)
  }
  println("still in the constructor")
}

val cust = new Customer("Cliff Richard",1850)
println(cust.calculateOrderTaxValue(1850))

```

#####Getters/Setters
```scala
class Employee(){
  private var _age = 0
  var name = ""
  
  //Getter
  def age = _age
  
  /Setter
  def age_= (value:Int) : Unit = _age = value
}

var emp = new Employee
emp.age=25
println(emp.age)
```

#####Class inheritance
```scala
class Vehicle(val enginePower:Integer,
              val wheelCount:Integer,
              val chassisNumber:String){

  def this(wheelCount:Integer, chassisNumber:String)=
  this(100,wheelCount,chassisNumber)

  def this(chassisNumber:String)=
    this(105,4,chassisNumber)
}

class Car(enginePower:Integer,
          wheelCount:Integer,chassisNumber: String,val wiperType:String)
  extends Vehicle(enginePower,wheelCount,chassisNumber){

  override def toString =
    s"$enginePower: $wheelCount, $chassisNumber $wiperType"

}

class MotorBike(wheelCount:Integer,chassisNumber: String,val handleType:String)
  extends Vehicle(wheelCount,chassisNumber){

    override def toString =
    s"$enginePower: $wheelCount, $chassisNumber $handleType"
  }

def show(vehicle: Vehicle)=
s"engine power : ${vehicle.enginePower}, count of wheel: ${vehicle.wheelCount}, chassis number ${vehicle.chassisNumber}"

show(new Vehicle(1000,4,"h1fg87"))
show(new Car(800,4,"h1fg87","double edged foldable"))
show(new MotorBike(2,"h1fg87","high bent"))
```

#####Scala object

Scala’s object keyword defines something that looks roughly like a class, except you can’t create instances of an object – only one instance exists. By creating an object, one can logically group methods and fields
```scala
object X {
  val mynumber = 2
  def func1 = this.mynumber * 10
  def func2 = this.mynumber / 20
}

//val obj = new X // Will not work !
X.mynumber
X.func1
X.func2

//Output
res0: Int = 2
res1: Int = 20
res2: Int = 0

```

#####Companion object
The object keyword allows one to create a companion object for a class. The difference between an ordinary object and a companion object is that the latter has the same name as the name of a class. Hence, an association between the companion object is created.

The screenshot example below shows that anyVal has only a single piece of storage (no matter how many instances are created) and that x1 and x2 are both accessing that same memory. To access elements of the companion object from methods of the class, you must give the name of the companion object (AnyObject), as in lines 8 and 9.

```scala
object AnyObject{
  var anyVal:Int = 1 // single copy
}

class AnyObject {
  def multiply(n:Int) = {
    AnyObject.anyVal = AnyObject.anyVal * n
    AnyObject.anyVal }
  }

var x1 = new AnyObject
var x2 = new AnyObject
x1.multiply(2)  //2
x2.multiply(2)  //4
x1.multiply(2)  //8  
```

#####Traits
A Trait is a different approach to creating classes: it allows inheritance in small chunks rather than all in a single go, which is what happens when creating a subclass.

Traits are small, logical concepts that allow one to easily “mix in” ideas to create a class.

For this reason, they are often called mixin types. Trait typically represent a singular concept such as Color, Softness, Brittleness, etc.

Important facts about Trait

A trait definition looks like a class, but uses the trait keyword instead of class.
To combine traits into a class, you always begin with the extends keyword, then add additional traits using the with keyword.
A class can only inherit from a single concrete or abstract base class, but it can combine as many traits as you want. If there is no concrete or abstract base class, you still start with the extends keyword for the first trait, followed by the with keyword for the remaining traits.
A freestanding trait cannot be instantiated; a Trait does not have a full-fledged constructor.
If any of the fields and/or methods that a class extends lack definition or implementation, then Scala will insist that you include the abstract keyword.
Here we have some of the simplest possible Trait and Class definitions:

```scala
trait Hardness
trait Brittleness
trait Color

class Material

class Metal extends Material with Hardness
 with Texture with Hardness
 
class Plastic extends Brittleness with Color
 with Hardness
```
Traits can inherit from abstract or concrete classes. Traits can also inherit from other traits – see the screenshot below. Here are example of some traits that have defined some member variables:
```scala
trait SuperTrait {
  def spt = "abc"
}

trait Subtrait1 extends SuperTrait{
  def sut1 = "pqr"
}

trait Subtrait2 extends Subtrait1{
  def sut2 = "fgh"
}

class AnyClass extends Subtrait2
val myval = new AnyClass
myval.spt
myval.sut1
myval.sut2

//Output
defined class AnyClass
myval: AnyClass = AnyClass@39ce1cbb
res0: String = abc
res1: String = pqr
res2: String = fgh
```

###Collections
----------
**Easy to use:** A small vocabulary of 20-50 methods is enough to solve most collection problems in a couple of operations. No need to wrap your head around complicated looping structures or recursions. Persistent collections and side-effect-free operations mean that you need not worry about accidentally corrupting existing collections with new data. Interference between iterators and collection updates is eliminated.

**Concise:** You can achieve with a single word what used to take one or several loops. You can express functional operations with lightweight syntax and combine operations effortlessly, so that the result feels like a custom algebra.

**Safe:** This one has to be experienced to sink in. The statically typed and functional nature of Scala’s collections means that the overwhelming majority of errors you might make are caught at compile-time. The reason is that (1) the collection operations themselves are heavily used and therefore well tested. (2) the usages of the collection operation make inputs and output explicit as function parameters and results. (3) These explicit inputs and outputs are subject to static type checking. The bottom line is that the large majority of misuses will manifest themselves as type errors. It’s not at all uncommon to have programs of several hundred lines run at first try.

**Fast:** Collection operations are tuned and optimized in the libraries. As a result, using collections is typically quite efficient. You might be able to do a little bit better with carefully hand-tuned data structures and operations, but you might also do a lot worse by making some suboptimal implementation decisions along the way. What’s more, collections have been recently adapted to parallel execution on multi-cores. Parallel collections support the same operations as sequential ones, so no new operations need to be learned and no code needs to be rewritten. You can turn a sequential collection into a parallel one simply by invoking the par method.

**Universal:** Collections provide the same operations on any type where it makes sense to do so. So you can achieve a lot with a fairly small vocabulary of operations. For instance, a string is conceptually a sequence of characters. Consequently, in Scala collections, strings support all sequence operations. The same holds for arrays.

**Example:** Here’s one line of code that demonstrates many of the advantages of Scala’s collections.
```scala
val (minors, adults) = people partition (_.age < 18)

```
It’s immediately clear what this operation does: It partitions a collection of people into minors and adults depending on their age. Because the partition method is defined in the root collection type TraversableLike, this code works for any kind of collection, including arrays. The resulting minors and adults collections will be of the same type as the people collection.

This code is much more concise than the one to three loops required for traditional collection processing (three loops for an array, because the intermediate results need to be buffered somewhere else). Once you have learned the basic collection vocabulary you will also find writing this code is much easier and safer than writing explicit loops. Furthermore, the partition operation is quite fast, and will get even faster on parallel collections on multi-cores. (Parallel collections have been released as part of Scala 2.9.)

####Mutable and Immutable Collections
-** mutable collection :** you can change, add, or remove elements of a collection
- **immutable collection :** never change ( You have still operations that simulate additions, removals, or updates, but those operations will in each case return a new collection and leave the old collection unchanged.)
- **All collection classes are found in the package scala.collection or one of its sub-packages mutable, immutable, and generic.** Most collection classes needed by client code exist in three variants, which are located in packages scala.collection, scala.collection.immutable, and scala.collection.mutable, respectively. Each variant has different characteristics with respect to mutability.

###Other def from tutorialpoints
Scala has a rich set of collection library. Collections are containers of things. Those containers can be sequenced, linear sets of items like List, Tuple, Option, Map, etc. The collections may have an arbitrary number of elements or be bounded to zero or one element (e.g., Option).

Collections may be strict or lazy. Lazy collections have elements that may not consume memory until they are accessed, like Ranges. Additionally, collections may be mutable (the contents of the reference can change) or immutable (the thing that a reference refers to is never changed). Note that immutable collections may contain mutable items.

For some problems, mutable collections work better, and for others, immutable collections work better. When in doubt, it is better to start with an immutable collection and change it later if you need mutable ones.

This chapter gives details of the most commonly used collection types and most frequently used operations over those collections.

###SN	Collections with Description
###Scala Lists
Scala's List[T] is a linked list of type T.

###Scala Sets
A set is a collection of pairwise different elements of the same type.

###Scala Maps
A Map is a collection of key/value pairs. Any value can be retrieved based on its key.

###Scala Tuples
Unlike an array or list, a tuple can hold objects with different types.

###Scala Options
Option[T] provides a container for zero or one element of a given type.

###Scala Iterators
An iterator is not a collection, but rather a way to access the elements of a collection one by one.

Example:
Following code snippet is a simple example to define all the above type of collections:
```scala
// Define List of integers.
val x = List(1,2,3,4)

// Define a set.
var x = Set(1,3,5,7)

// Define a map.
val x = Map("one" -> 1, "two" -> 2, "three" -> 3)

// Create a tuple of two elements.
val x = (10, "Scala")

// Define an option
val x:Option[Int] = Some(5)
```




