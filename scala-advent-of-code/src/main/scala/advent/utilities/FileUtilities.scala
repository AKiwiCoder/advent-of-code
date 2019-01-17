package advent.utilities

import java.io.InputStream

object FileUtilities {
  def readFile[T](filename: String, convertor: (String) => T): List[T] = {
    val t = readFile(filename).map(line => convertor(line))
    println(t);
    t
  }

  def readFile(filename: String): List[String] = {
    val stream: InputStream = getClass.getResourceAsStream(filename)
    scala.io.Source.fromInputStream(stream).getLines.toList
  }
}
