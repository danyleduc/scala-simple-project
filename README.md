# scala-simple-project

https://blog.udemy.com/scala-tutorial-getting-started-with-scala/

##Variable declaration : 

 val x:Int = 11

### 2 types :
####immutable (read-only):
val distance: Double = 22

val myarr:Array[String]=new Array(6)

myarr = new Array(5) //This will give a reassignment to val error.

myarr (0)="Scala" //This does not give an error.

####mutable (read-write):
var temp:Int=22

age: Int=35

#####There is an exception to this rule when initializing vals and vars.
#####When they are used as constructor parameters, they will be initialized when the object is instantiated. Also, derived classes can override vals declared inside the parent classes.
