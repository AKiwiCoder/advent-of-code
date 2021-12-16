package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.twenty_twentyone.utils.{BitCodeInstruction, BitCodeLiteral}
import advent.utilities.FileUtilities
import advent.utilities.StringUtilities.{bitsToLong, hexToNibble}

import scala.annotation.tailrec

class Day16PacketDecoder(filename: String) extends DailyProblem[Int, Long] {

  private val input = FileUtilities.readFile(filename)(0).toCharArray.map(hexToNibble).foldLeft("")((acc, chunk) => acc + chunk)

  def decodeLiteral(stream: String): (Int, String) = { // (length, value)
    val bit = stream.take(5)
    if (bit(0) == '1') { // Not the last chunk
      val (lengthOfRestOfLiteral, literalBitString) = decodeLiteral(stream.drop(5))
      (lengthOfRestOfLiteral + 5, bit.drop(1) + literalBitString)
    } else { // Last chunk
      val value = bit.drop(1)
      (5, value)
    }
  }

  private def extractXBytes(input: String, length: Int): (String, String) = {
    (input.take(length), input.drop(length))
  }

  private def bitsToInstruction(input: String) : BitCodeInstruction = {
    def parseInstruction(stream: String): (BitCodeInstruction, Int) = {
      def parseSubInstruction(stream: String, length: Int, instructions : List[BitCodeInstruction]) : List[BitCodeInstruction] = {
        if (length == 0) {
          instructions.reverse
        } else {
          val (instruction, pktLength) = parseInstruction(stream)
          parseSubInstruction(stream.drop(pktLength), length - pktLength, instruction :: instructions)
        }
      }

      val (version, str1) = extractXBytes(stream, 3)
      val (typeId, str2) = extractXBytes(str1, 3)

      val versionNumber = Integer.parseInt(version, 2)

      typeId match {
        case "100" => { // Literal
          val (length, value) = decodeLiteral(str2)
          (BitCodeInstruction.buildInstruction(versionNumber, "100", bitsToLong(value)), 6 + length)
        }
        case operatorCode => { // Operator
          val (lengthTypeId, str3) = extractXBytes(str2, 1)

          if (lengthTypeId == "0") {
            val (subPacketLength, str4) = extractXBytes(str3, 15)
            val subInstructions = parseSubInstruction(str4, Integer.parseInt(subPacketLength, 2), List())
            (BitCodeInstruction.buildInstruction(versionNumber, operatorCode, subInstructions), 6 + 1 + 15 + Integer.parseInt(subPacketLength, 2))
          } else {
            val (subPacketCount, str4) = extractXBytes(str3, 11)
            val count = Integer.parseInt(subPacketCount, 2)
            val (instructions, subPacketLength) = (0 until count).foldLeft((List[BitCodeInstruction](), 0))((acc, num) => {
              val (subInstruction, pktLength) = parseInstruction(str4.drop(acc._2))
              (subInstruction :: acc._1, acc._2 + pktLength)
            })

            (BitCodeInstruction.buildInstruction(versionNumber, operatorCode, instructions.reverse), 6 + 1 + 11 + subPacketLength)
          }
        }
      }
    }

    parseInstruction(input)._1
  }

  private val instructions = bitsToInstruction(input)

  override val part1Answer: Int = instructions.checksum()
  override val part2Answer: Long = instructions.evaluate()
}


