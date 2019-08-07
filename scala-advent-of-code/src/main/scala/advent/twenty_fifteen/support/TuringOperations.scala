package advent.twenty_fifteen.support

abstract class TuringOperation {
  def execute(registers: Map[String, Int]): Map[String, Int]
}

case class HalfRegisterOperation(register: String) extends TuringOperation {
  def execute(registers: Map[String, Int]): Map[String, Int] = registers + (register -> (registers(register) / 2)) + ("pc" -> (registers("pc") + 1))
}

case class TripleRegisterOperation(register: String) extends TuringOperation {
  def execute(registers: Map[String, Int]): Map[String, Int] = registers + (register -> (registers(register) * 3)) + ("pc" -> (registers("pc") + 1))
}

case class IncrementRegisterOperation(register: String) extends TuringOperation {
  def execute(registers: Map[String, Int]): Map[String, Int] = registers + (register -> (registers(register) + 1)) + ("pc" -> (registers("pc") + 1))
}

case class JumpOperation(offset: Int) extends TuringOperation {
  def execute(registers: Map[String, Int]): Map[String, Int] = registers + ("pc" -> (registers("pc") + offset))
}

case class JumpIfEvenOperation(register: String, offset: Int) extends TuringOperation {
  def apply(registers: Map[String, Int]): Boolean = registers(register) % 2 == 0

  def execute(registers: Map[String, Int]): Map[String, Int] = registers + ("pc" -> (registers("pc") + (if (apply(registers)) offset else 1)))
}

case class JumpIfOneOperation(register: String, offset: Int) extends TuringOperation {
  def apply(registers: Map[String, Int]): Boolean = registers(register) == 1

  def execute(registers: Map[String, Int]): Map[String, Int] = registers + ("pc" -> (registers("pc") + (if (apply(registers)) offset else 1)))
}