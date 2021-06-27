import scala.annotation.tailrec
object main extends App {
  // LABORATORY 1
  println(
    "__________________________\nLABORATORY 1\n__________________________"
  )
  // Task 1
  var weekdays: List[String] = List(
    "Poniedzialek",
    "Wtorek",
    "Sroda",
    "Czwartek",
    "Piatek",
    "Sobota",
    "Niedziela"
  )
  def connected_string(str_list: List[String]): String = {
    var ret: String = "";
    var element = "";
    for (element <- str_list) {
      ret += element + ",";
    }
    ret.substring(0, ret.length() - 1)
  }
  println("#1 For Loop output: " + connected_string(weekdays))
  def connected_string_start_p(
                               str_list: List[String],
                               start_char: Char
                             ): String = {
    var ret: String = "";
    var element = "";
    for (element <- str_list if element.head == start_char) {
      ret += element + ",";
    }
    ret.substring(0, ret.length() - 1)
  }
  println("#1 Filter string by 'P': " + connected_string_start_p(weekdays, 'P'))
  def connected_string_while(str_list: List[String]): String = {
    var ret: String = "";
    var element = "";
    var i = 0;
    while (i < str_list.length) {
      ret += str_list(i) + ",";
      i += 1
    }
    ret.substring(0, ret.length() - 1)
  }
  println("#1 While loop output: " + connected_string_while(weekdays))
  // Task 2
  def connected_string_recurrent(
                            str_list: List[String],
                            str_so_far: String = ""
                          ): String = str_list match {
    case Nil => str_so_far
    case x :: Nil =>
      (str_so_far + ',' + x).substring(1, (str_so_far + ',' + x).length())
    case x :: y :: xs => connected_string_recurrent(y :: xs, str_so_far + ',' + x)
  }
  println("#2 Recurrent loop output: " + connected_string_recurrent(weekdays))
  def connected_string_recurrent_reverse(
                                    str_list: List[String],
                                    str_so_far: String = ""
                                  ): String = str_list match {
    case Nil => str_so_far
    case x :: Nil =>
      (x + ',' + str_so_far).substring(0, (str_so_far + ',' + x).length() - 1)
    case x :: y :: xs =>
      connected_string_recurrent_reverse(y :: xs, x + ',' + str_so_far)
  }
  println(
    "#2 Recurrent reverse output: " + connected_string_recurrent_reverse(weekdays)
  )
  // Task 3
  @tailrec
  def connected_string_recurrent_tail(
                                 str_list: List[String],
                                 result: String = ""
                               ): String = {
    if (str_list.length == 0)
      result
    else
      connected_string_recurrent_tail(
        str_list.tail,
        if (result != "") result + ',' + str_list.head else str_list.head
      )
  }

  println(
    "#3 Tail recurrent string: " + connected_string_recurrent_tail(weekdays)
  )
  // Task 4

  println("#4 Foldl string: " + weekdays.foldLeft("") {
    (connected_string, element) =>
      if (connected_string != "") connected_string + ',' + element else element
  })

  println("#4 Foldr string: " + weekdays.foldRight("") {
    (connected_string, element) =>
      if (element != "") element + ',' + connected_string else connected_string
  })

  println(
    "#4 Foldl filter string by 'P': " + weekdays
      .filter(_.head == 'S')
      .foldLeft("") { (connected_string, element) =>
        if (connected_string != "") connected_string + ',' + element else element

      }
  )

  // Task 5
  val products: Map[String, Double] =
    Map("beer" -> 15, "coffee" -> 10, "book" -> 100)
  val discounted =
    products.view.mapValues(price => price * 0.9).toMap
  println(s"#5 Discounted products: from $products to  $discounted")
  // Task 6
  val tuple_3 = ("text", 27, 3.14)
  def print_tuple(list_to_print: (Any, Any, Any)) = {
    println(s"#6 Tuple with 3 data types: $list_to_print")
  }
  print_tuple(tuple_3)
  // Task 7
  val capitals = Map(
    "Brazil" -> "Brasilia",
    "Chile" -> "Santiago",
    "Iceland" -> "Reykjavik",
    "Austria" -> "Wien"
  )
  def option_example(key: Option[String]) = key match {
    case Some(s) => s
    case None    => "?"
  }
  println("#7 The capital of Brasil is " + option_example(capitals.get("Brazil")))
  println(
    "#7 The capital of Austria is " + option_example(capitals.get("Austria"))
  )
  // Task 8
  var values = List[Int](0, -1, 2, 0, 3, -4, 6, 0, 7, 0, -8, 0, 9, 10, 0, 11, 0, 12)
  def delete_zeros(
                     int_list: List[Int],
                     filtered: List[Int] = List[Int]()
                   ): List[Int] =
    int_list match {
      case Nil      => filtered
      case x :: Nil => if (x != 0) x :: filtered else filtered
      case x :: y :: xs =>
        if (x != 0) delete_zeros(y :: xs, x :: filtered)
        else delete_zeros(y :: xs, filtered)
    }
  println("#8 Values without zeros " + delete_zeros(values))
  // Task 9
  def enlarge(nums: List[Int]) = {
    def increase(num: Int): Int = {
      num + 1
    }
    nums.map(increase)
  }
  println("#9 Bigger values " + enlarge(values))
  // Task 10

  def filter_range(nums: List[Double]) = {
    nums.filter(x => x < 12 && x > -5)
  }
  println(
    "#10 Some numbers " + filter_range(values.map(num => num.toDouble))
  )
  // LABORATORY 2
  println(
    "__________________________\nLABORATORY 2\n__________________________"
  )
  // Task 1
  def match_days(day: String): String = day match {
    case "Sobota" | "Niedziela"                                     => "Weekend"
    case "Poniedzialek" | "Wtorek" | "Sroda" | "Czwartek" | "Piatek"=> "Praca"
    case _                                                          => "Nie ma takiego dnia"
  }
  println(s"#1")
  println(
    "Poniedzialek to " + match_days("Poniedzialek")
  )
  println(
    "Niedziela to " + match_days("Niedziela")
  )
  println(
    "Nic to " + match_days("Nic")
  )
  // Task 2
  class KontoBankowe(private var balance: Int = 0) {
    def wplata(change: Int): Unit = {
      balance += change
    }
    def wyplata(change: Int): Unit = {
      balance -= change
    }
    def stanKonta(): Int = {
      balance
    }
  }
  val Konto1 = new KontoBankowe
  Konto1.wplata(5000)
  Konto1.wyplata(200)
  val Konto2 = new KontoBankowe(20000)
  Konto2.wplata(500)
  Konto2.wyplata(500)
  println(
    "#2 In the second account there is " + Konto2.stanKonta() + " USD and in the first " + Konto1
      .stanKonta() + " USD"
  )

  // Task 3
  case class Osoba(var name: String, var surname: String)
  val ja = new Osoba("Izabela", "Lugowska")
  val mom = new Osoba("Renata", "Lugowska")
  val friend = new Osoba("Ross", "Geller")
  val stranger = new Osoba("someone", "someone")
  def powitaj(someone: Osoba): String = someone match {
    case Osoba("Renata", "Lugowska") =>
      s"Hi mom."
    case Osoba("Izabela", "Lugowska") => s"Hallo Iza"
    case Osoba("Ross", "Geller")  => s"Hii :("
    case _                              => "Good morning!"
  }
  println(s"#3")
  println(powitaj(ja))
  println(powitaj(mom))
  println(powitaj(friend))
  println(powitaj(stranger))
  // Task 4
  def funkcja3(value: Int, action: (Int) => Int): Int = {
    action(action(action(value)))
  }
  println(s"#4 ")
  println(funkcja3(3, a => a * 3))
  // Task 5
  trait Student extends Osoba1 { override def tax = 0 }
  trait Pracownik extends Osoba1 {
    var _salary: Integer = 0
    def salary() {
      _salary
    }
    def salary_=(new_salary: Integer): Unit = {
      _salary = new_salary
    }
    override def tax: Double =
      0.2 * _salary.toDouble
  }
  trait Nauczyciel extends Pracownik {
    override def tax = 0.1 * _salary
  }

  abstract class Osoba1(
                           val name: String,
                           val surname: String
                         ) {
    def tax(): Double
  }
  var osoba_1 = new Osoba1("The", "Student") with Student
  var osoba_2 = new Osoba1("Office", "Worker") with Pracownik
  osoba_2._salary = 1500
  var osoba_3 = new Osoba1("The", "Teacher") with Nauczyciel
  var osoba_4 = new Osoba1("Student", "Worker") with Student with Pracownik
  var osoba_5 = new Osoba1("Worker", "Student") with Pracownik with Student
  osoba_4._salary = 1200
  osoba_5._salary = 1500

  def wypisz_osobe(person: Osoba1): Unit = {
    println(s"#5 ${person.name} ${person.surname} tax: ${person.tax}")
  }
  wypisz_osobe(osoba_2) //Jest podatek
  wypisz_osobe(osoba_4) //Jest podatek
  wypisz_osobe(osoba_5) //Brak podatku
}


