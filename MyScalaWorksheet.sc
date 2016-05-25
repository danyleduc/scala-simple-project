class Person (
               val firstName: String,
               val lastName: String,
               val age: Int,
               val vocation: String
             ) {

  def fullName = firstName + " " + lastName
  def birthPlace: Unit = println("I was born in this universe")
  def introduce (informal: Boolean): String = {
    if (informal)
      "Hi, I'm " + firstName + "!"
    else
      "Hello, my name is " + fullName + ". I'm a " + vocation + "."
  }

}

trait Worker {
  def selfIntroduction: String
}

class Musician (
                 firstName: String,
                 lastName: String,
                 age: Int,
                 val speciality: String,
                 val favoriteInstrument: String)
  extends Person(firstName, lastName, age, "musician") with Worker {

  def selfIntroduction =
    "As a " + vocation + ", I am a " + speciality +
      " who likes to play the Instrument " + favoriteInstrument + "."
  override def birthPlace : Unit =  println("I was born in Europe")
}

class Student (school: String, subject: String) extends Worker {
  def selfIntroduction = "I'm studying " + subject + " at " + school + "!"
}


object Surgeon{
  var count_of_surgeons : Int = 0
}


class Surgeon (
                firstName: String,
                lastName: String,
                age: Int,
                val speciality: String,
                favoriteTool: String)
  extends Person(firstName, lastName, age, "Surgeon") with Worker {

  def selfIntroduction =
    "As a " + vocation + ", I work on " + speciality +
      ". Much of my surgical operartions are done with " + favoriteTool + "."
  def incrementSurgeonCount:Int =
  {Surgeon.count_of_surgeons +=1; return Surgeon.count_of_surgeons}
}


//here’s how we can create Jack Carter now who is a Person
val jackCarter = new Person("Jack", "Carter", 37, "musician")

//we can now access the fields and functions

jackCarter.age
jackCarter.introduce(true)

val jillBrown = new Person("Jill", "Brown", 34, "computer scientist")
val johnDoe = new Person("John", "Doe", 43, "philosopher")
val harryPotter = new Person("Harry", "Potter", 28, "mathematician")
val johnBaldridge = new Person("John", "Baldridge", 43, "trucker")

//These above Person objects can now be aggregated into a list
// together, giving us a List[Person]
// that allows mapping to retrieve specific values, like first
// names and ages, and
// performing computations like calculating the average age of
// the individuals in the list.

val people = List(jackCarter, jillBrown, johnDoe, harryPotter)
people.map(person => person.firstName)
people.map(person => person.age)
people.map(person => person.age).sum/people.length.toDouble

//sorting according age can be done now

val ageSortedPeople = people.sortBy(_.age)
ageSortedPeople.map(person => person.fullName + ":" + person.age)

//grouping people by first name, last name, etc.

people.groupBy(person => person.firstName)
people.groupBy(person => person.lastName)

//With this, we can have all the Johns greet us.

people.groupBy(person => person.firstName)("John").foreach(john => println(john.introduce(true)))

//example of a standalone object

object JoelAbrahamsson extends Person("Joel", "Abrahamsson", 43, "musician")
JoelAbrahamsson.introduce(true)

//The Musician class has its own parameter list: some
// of these, like firstName, lastName, and age,
// are passed on from Person, and there are new
// parameter fields speciality and favoriteInstrument.

val cliffRichard = new Musician("Cliff", "Richard", 83, "guitarist", "guitar")
cliffRichard.selfIntroduction
cliffRichard.age
cliffRichard.introduce(true)
cliffRichard.favoriteInstrument

val anyStudent = new Student("The University of California, Berkeley", "economics")
anyStudent.selfIntroduction

//Notice that the parameters school and subject
// were not "val" in the Student definition
// which means that they are not member fields
// of the Student class. Hence, attempting to
// access the value provided for school for
// anyStudent fails as you can see by uncommenting
//the line of code immediately below

//anyStudent.school  ----fails
 
val billyBowden = new Surgeon("Billy", "Bowden", 44, "heart transplant", "scalpel")
billyBowden.selfIntroduction

//Notably, the speciality field of Surgeon is
// disconnected from that of Musician, so there
// is no particular expectation of consistency
// of use across the two. So, what happens if we
// put cliffRichard and billyBowden in a List together

val professionals = List(cliffRichard, billyBowden)

//we can treat all of the elements of
// professionals as Persons, e.g., accessing
// their vocation (which is a member field of Person).

professionals.map(prof => prof.vocation)

//We can treat each element of the list as
// a Person and a Worker, e.g., printing out their
// fullName (from Person) and their
// selfIntroduction (from Worker).

professionals.foreach(prof => println(prof.fullName + ": " + prof.selfIntroduction))

//However, access to fields and functions
// that are specific to Muscian or Surgeon,
//such as favoriteTool from Surgeon is not
// possible - try to run the code below
//by uncommenting

//professionals.map(prof => prof.favoriteTool) ---this fails