package advent.utilities

import scala.annotation.tailrec

object StringUtilities {
  private val hexArray = Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

  def bitsToLong(binary : String) : Long = {
    binary.toCharArray.toList.foldLeft(0L)((acc, char) => if (char == '0') acc * 2L else acc * 2L + 1L)
  }

  def hexToNibble(hex : Char) : String = {
    hex match {
      case '0' => "0000"
      case '1' => "0001"
      case '2' => "0010"
      case '3' => "0011"
      case '4' => "0100"
      case '5' => "0101"
      case '6' => "0110"
      case '7' => "0111"
      case '8' => "1000"
      case '9' => "1001"
      case 'A' => "1010"
      case 'B' => "1011"
      case 'C' => "1100"
      case 'D' => "1101"
      case 'E' => "1110"
      case 'F' => "1111"
    }
  }

  def bytesToHex(bytes: Array[Byte]): String = {
    val output = Array.ofDim[Char](bytes.size * 2)
    bytes.foldLeft(0)((idx, b) => {
      val v = bytes(idx) & 0xFF
      output(idx * 2) = hexArray(v >>> 4)
      output(idx * 2 + 1) = hexArray(v & 0x0F)
      (idx + 1)
    })
    output.mkString
  }

  def findNonOverlappingPairs(password: String): List[String] = {
    @tailrec
    def process(working: String, acc: List[String]): List[String] = {
      if (working.isEmpty || working.length == 1) {
        acc
      } else if (working.charAt(0) == working.charAt(1)) {
        process(working.drop(2), working.take(2) :: acc)
      } else {
        process(working.tail, acc)
      }
    }
    process(password, List())
  }
}
