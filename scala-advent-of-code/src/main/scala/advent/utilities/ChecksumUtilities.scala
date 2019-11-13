package advent.utilities

import java.security.MessageDigest

import scala.annotation.tailrec

object ChecksumUtilities {
  private val ZEROS = "0000000000000000000000000000000000000000000000000000000"

  private val digest = MessageDigest.getInstance("MD5")

  def md5hashString(text: String): String = {
    synchronized {
      StringUtilities.bytesToHex(digest.digest(text.getBytes))
    }
  }

  def doesItStartWithXZeros(hash: String, zeros: String): Boolean = hash.startsWith(zeros)

  def findNextHashWithXZeros(key: String, start: Int, zeroCount: Int): (String, Int) = {
    @tailrec
    def walkHashes(index: Int, key : String, targetPrefix : String): (String, Int) = {
      val hash = md5hashString(s"$key$index")
      if (hash.startsWith(targetPrefix)) {
        (hash, index)
      } else {
        walkHashes(index + 1, key, targetPrefix)
      }
    }
    walkHashes(start, key, ZEROS.take(zeroCount))
  }
}
