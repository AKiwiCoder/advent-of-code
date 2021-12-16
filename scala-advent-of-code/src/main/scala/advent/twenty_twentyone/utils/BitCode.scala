package advent.twenty_twentyone.utils

abstract class BitCodeInstruction(version : Int) {
  def checksum() : Int
  def evaluate() : Long
}

object BitCodeInstruction {
  def buildInstruction(version : Int, opcode : String, value: Long) : BitCodeInstruction = {
    BitCodeLiteral(version, value)
  }

  def buildInstruction(version : Int, opcode : String, subInstructions : List[BitCodeInstruction]) : BitCodeInstruction = {
    Integer.parseInt(opcode, 2) match {
      case 0 => BitCodeSum(version, subInstructions)
      case 1 => BitCodeProduct(version, subInstructions)
      case 2 => BitCodeMinimum(version, subInstructions)
      case 3 => BitCodeMaximum(version, subInstructions)
      case 5 => BitCodeGreaterThan(version, subInstructions(0), subInstructions(1))
      case 6 => BitCodeLessThan(version, subInstructions(0), subInstructions(1))
      case 7 => BitCodeEquals(version, subInstructions(0), subInstructions(1))
    }
  }
}

case class BitCodeLiteral(version: Int, value : Long) extends BitCodeInstruction(version) {
  override def checksum(): Int = {
    version
  }

  override def evaluate(): Long = {
    value
  }
}

case class BitCodeSum(version: Int, instructions: List[BitCodeInstruction]) extends BitCodeInstruction(version) {
  override def checksum(): Int = {
    version + instructions.foldLeft(0)((acc, instruction) => acc + instruction.checksum())
  }

  override def evaluate(): Long = {
    instructions.foldLeft(0L)((acc, instruction) => acc + instruction.evaluate())
  }
}

case class BitCodeProduct(version: Int, instructions: List[BitCodeInstruction]) extends BitCodeInstruction(version) {
  override def checksum(): Int = {
    version + instructions.foldLeft(0)((acc, instruction) => acc + instruction.checksum())
  }

  override def evaluate(): Long = {
    instructions.foldLeft(1L)((acc, instruction) => acc * instruction.evaluate())
  }
}

case class BitCodeMinimum(version: Int, instructions: List[BitCodeInstruction]) extends BitCodeInstruction(version) {
  override def checksum(): Int = {
    version + instructions.foldLeft(0)((acc, instruction) => acc + instruction.checksum())
  }

  override def evaluate(): Long = {
    instructions.map(_.evaluate()).min
  }
}

case class BitCodeMaximum(version: Int, instructions: List[BitCodeInstruction]) extends BitCodeInstruction(version) {
  override def checksum(): Int = {
    version + instructions.foldLeft(0)((acc, instruction) => acc + instruction.checksum())
  }

  override def evaluate(): Long = {
    instructions.map(_.evaluate()).max
  }
}

case class BitCodeGreaterThan(version: Int, left : BitCodeInstruction, right : BitCodeInstruction) extends BitCodeInstruction(version) {
  override def checksum(): Int = {
    version + left.checksum() + right.checksum()
  }

  override def evaluate(): Long = {
    if (left.evaluate() > right.evaluate()) 1 else 0
  }
}

case class BitCodeLessThan(version: Int, left : BitCodeInstruction, right : BitCodeInstruction) extends BitCodeInstruction(version) {
  override def checksum(): Int = {
    version + left.checksum() + right.checksum()
  }

  override def evaluate(): Long = {
    if (left.evaluate() < right.evaluate()) 1 else 0
  }
}

case class BitCodeEquals(version: Int, left : BitCodeInstruction, right : BitCodeInstruction) extends BitCodeInstruction(version) {
  override def checksum(): Int = {
    version + left.checksum() + right.checksum()
  }

  override def evaluate(): Long = {
    if (left.evaluate() == right.evaluate()) 1 else 0
  }
}
