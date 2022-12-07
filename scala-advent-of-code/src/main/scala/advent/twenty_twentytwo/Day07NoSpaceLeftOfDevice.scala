package advent.twenty_twentytwo

import advent.common.DailyProblem
import advent.utilities.FileUtilities

import scala.annotation.tailrec

abstract class FileSystemObject {
   def name() : String
}

case class Directory(parent: Directory, name : String) extends FileSystemObject {
}

case class File(directory : Directory, name : String, size : Long) extends FileSystemObject {
}

class Day07NoSpaceLeftOfDevice(filename: String) extends DailyProblem[Long, Long] {
  private val input = FileUtilities.readFile(filename)

  private val cd_cmd = "\\$ cd (.+)".r
  private val ls_cmd = "\\$ ls".r
  private val dir_entry = "dir (.+)".r
  private val file_entry = "([0-9]+) (.+)".r

  @tailrec
  private def walk(remain : List[String], dir : Directory, root : Directory, acc : Map[Directory, List[FileSystemObject]]) : Map[Directory, List[FileSystemObject]] = {
    if (remain.isEmpty) {
      acc
    } else {
      val line = remain.head

      line match {
        case cd_cmd(dest) => {
          if (dest == "..")
            walk(remain.tail, dir.parent, root, acc)
          else if (dest == "/")
            walk(remain.tail, root, root, acc)
          else {
            val new_dir = acc(dir).find(f => f.name().equals(dest)).get.asInstanceOf[Directory]
            walk(remain.tail, new_dir, root, acc + (new_dir -> List()))
          }
        }
        case ls_cmd() => walk(remain.tail, dir, root, acc)
        case dir_entry(name) => {
          val child : List[FileSystemObject] = acc(dir)
          val new_dir = Directory(dir, name)

          val new_acc_1: Map[Directory, List[FileSystemObject]] = acc + (dir -> (new_dir :: child))
          val new_acc_2: Map[Directory, List[FileSystemObject]] = new_acc_1 + (new_dir -> List())
          walk(remain.tail, dir, root, new_acc_2)
        }
        case file_entry(size, name) => {
          val child = acc(dir)
          walk(remain.tail, dir, root, acc + (dir -> (File(dir, name, size.toLong) :: child)))
        }
      }
    }
  }

  private val root = Directory(null, "/")

  private val files = walk(input, root, root, Map(root -> List[FileSystemObject]()))

  private def calc_sizes(dir : Directory): Long = {
    files(dir).map {
      case d: Directory => calc_sizes(d)
      case f: File => f.size
    }.sum
  }

  println(files.keys)
  println(files.keys.map(d => (d.name, calc_sizes(d))))
  println(files.keys.map(d => calc_sizes(d)).filter(_ <= 100000))

  override val part1Answer: Long = files.keys.map(d => calc_sizes(d)).filter(_ <= 100000).sum
  override val part2Answer: Long = 0
}

