# scala-simple-project

https://blog.udemy.com/scala-tutorial-getting-started-with-scala/

http://www.brunton-spall.co.uk/post/2011/12/02/map-map-and-flatmap-in-scala/

https://twitter.github.io/scala_school/

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
