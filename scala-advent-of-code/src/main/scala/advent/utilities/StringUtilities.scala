package advent.utilities

import scala.annotation.tailrec

object StringUtilities {
  private val hexArray = Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

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
