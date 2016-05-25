# scala-simple-project

https://blog.udemy.com/scala-tutorial-getting-started-with-scala/

###Variable declaration
-------------------------
```scala
 val x:Int = 11
```
#### 2 types :
####immutable (read-only):
```scala
val distance: Double = 22
val myarr:Array[String]=new Array(6)
myarr = new Array(5) //This will give a reassignment to val error.
myarr (0)="Scala" //This does not give an error.
```

####mutable (read-write):
```scala
var temp:Int=22
age: Int=35
 ```
#####There is an exception to this rule when initializing vals and vars.
#####When they are used as constructor parameters, they will be initialized when the object is instantiated. Also, derived classes can override vals declared inside the parent classes.

###Scala and the Usage of Semicolons
-------------------------
```scala
str = "hello world"; println (str)
```

###Statements, Expressions and Operators
-------------------------
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

Compound Expressions
-----------------
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


Operator Overloading
--------------------------------------
Scala also allows operator overloading. Operators are typically things such as +, -, and !.

As a programmer, you would frequently use operators to perform arithmetic on numbers, or for manipulating Strings. Operator overloading, just like method overloading, allows you to redefine their behaviour for a particular type, and give them meaning for your own custom classes.


###Loops
------------------
####for loop
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
