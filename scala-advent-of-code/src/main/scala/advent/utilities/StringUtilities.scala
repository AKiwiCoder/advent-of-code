package advent.utilities

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
}
