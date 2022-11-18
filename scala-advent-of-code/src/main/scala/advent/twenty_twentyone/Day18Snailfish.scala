package advent.twenty_twentyone

import advent.common.DailyProblem
import advent.twenty_twentyone.Day18Snailfish.toTreeNode
import advent.utilities.FileUtilities

import java.util.concurrent.atomic.DoubleAdder
import scala.annotation.tailrec


abstract class TreeNode(var parent: TreeNode) {
  def setParent(newParent : TreeNode) : Unit = {
    this.parent = newParent
  }

  def split(): (TreeNode, Boolean) = ???

  def explode(): (TreeNode, Boolean) = ???

  def findLeftHandLeaf(me: TreeNode): Leaf = {
    val nodes = root().enumerate()
    val index = nodes.indexOf(me)
    val found = nodes.take(index).findLast(e => e != me && e.isInstanceOf[Leaf])
    found match {
      case None => null
      case Some(x) => x.asInstanceOf[Leaf]
    }
  }

  def findRightHandLeaf(me: TreeNode): Leaf = {
    val nodes = root().enumerate()
    val index = nodes.indexOf(me)
    val found = nodes.drop(index).find(e => e != me && e.isInstanceOf[Leaf])
    found match {
      case None => null
      case Some(x) => x.asInstanceOf[Leaf]
    }
  }

  def root(): TreeNode = if (parent != null) parent.root() else this

  def enumerate(): List[TreeNode] = ???

  def magnitude(): Int = ???

  def depth(): Int = if (parent == null) 0 else 1 + parent.depth()
}

class Node(nodeParent : TreeNode, var lhs: TreeNode, var rhs: TreeNode) extends TreeNode(nodeParent) {

  override def toString: String = {
    "[" + lhs + "," + rhs + "]"
  }

  override def split(): (TreeNode, Boolean) = {
    val lhsSplit = lhs.split()
    lhs = lhsSplit._1

    if (!lhsSplit._2) {
      val rhsSplit = rhs.split()
      rhs = rhsSplit._1
      (this, rhsSplit._2)
    } else {
      (this, true)
    }
  }

  override def magnitude(): Int = lhs.magnitude() * 3 + rhs.magnitude() * 2

  override def explode(): (TreeNode, Boolean) = {
    val calcDepth = depth()
    if (calcDepth >= 4 && lhs.isInstanceOf[Leaf] && rhs.isInstanceOf[Leaf]) {
      val leftHandLeaf = findLeftHandLeaf(lhs)
      val rightHandLeaf = findRightHandLeaf(rhs)
      if (leftHandLeaf != null) {
        leftHandLeaf.value = leftHandLeaf.value + lhs.asInstanceOf[Leaf].value
      }
      if (rightHandLeaf != null) {
        rightHandLeaf.value = rightHandLeaf.value + rhs.asInstanceOf[Leaf].value
      }

      val newLeaf = new Leaf(parent, 0)
      if (newLeaf.depth() != calcDepth) {
      }
      (new Leaf(parent, 0), true)
    } else {
      val lhsExplode = lhs.explode()
      lhs = lhsExplode._1
      if (!lhsExplode._2) {
        val rhsExplode = rhs.explode()
        rhs = rhsExplode._1
        (this, rhsExplode._2)
      } else {
        (this, true)
      }
    }
  }

  override def enumerate(): List[TreeNode] = lhs.enumerate() ::: rhs.enumerate()

}

class Leaf(nodeParent: TreeNode, var value: Int) extends TreeNode(nodeParent) {
  override def toString: String = "" + value + ""

  override def magnitude(): Int = value

  override def split(): (TreeNode, Boolean) = {
    if (value >= 10) {
      val node = new Node(parent, null, null)
      node.lhs = new Leaf(node, value / 2)
      node.rhs = new Leaf(node, if (value % 2 == 1) 1 + value / 2 else value / 2)
      (node, true)
    } else {
      (this, false)
    }
  }

  override def explode(): (TreeNode, Boolean) = (this, false)

  override def enumerate(): List[TreeNode] = List(this)
}


object Day18Snailfish {
  def add(lhs: TreeNode, rhs: TreeNode): TreeNode = {

    val newRoot = new Node(null, lhs, rhs)
    lhs.parent = newRoot
    rhs.parent = newRoot

    val r = reduce(newRoot)
    r
  }

  def reduce(newRoot: TreeNode): TreeNode = {
    @tailrec
    def loopExplode(root: TreeNode, haveExploded: Boolean): (TreeNode, Boolean) = {
      val explode = root.explode();
      if (explode._2) {
        loopExplode(explode._1, true)
      } else {
        (explode._1, haveExploded)
      }
    }


    @tailrec
    def loop(root: TreeNode): TreeNode = {
      val r1 = loopExplode(root, false)
      val r2 = r1._1.split();

      if (r1._2 || r2._2) {
        loop(r2._1)
      } else {
        r2._1
      }
    }
    loop(newRoot)
  }

  def parse(line: String): List[Any] = {
    def walk(line: String, string: String, stack: List[Any]): List[Any] = {
      if (line.isEmpty) {
        stack.head.asInstanceOf[List[Any]]
      } else {
        line.head match {
          case '[' => walk(line.tail, "", stack)
          case ']' => {
            if (string != "") {
              val lhs = stack(0)
              val rhs = if (string != "") string.toInt else stack(1)
              walk(line.tail, "", List(lhs, rhs) :: stack.drop(if (string != "") 1 else 2))
            } else {
              val rhs = stack(0)
              val lhs = stack(1)
              walk(line.tail, "", List(lhs, rhs) :: stack.drop(2))
            }
          }
          case ',' => walk(line.tail, "", if (string != "") string.toInt :: stack else stack)
          case c => walk(line.tail, string + c, stack)
        }
      }
    }

    walk(line.trim, "", List())
  }


  def toTreeNode(list: List[Any]): TreeNode = {
    def build(current: List[Any], parent: TreeNode): TreeNode = {
      if (current.isEmpty) {
        parent
      } else {
        val node = new Node(parent, null, null)

        val lhs = current(0)
        val rhs = current(1)

        val tNL = lhs match {
          case v: Int => new Leaf(node, v)
          case l: List[Any] => build(l, node);
        }

        val rNL = rhs match {
          case v: Int => new Leaf(node, v)
          case l: List[Any] => build(l, node);
        }

        node.lhs = tNL
        node.rhs = rNL

        node
      }
    }

    build(list, null)
  }

  def parseTree(line: String): TreeNode = {
    toTreeNode(parse(line))
  }
}


class Day18Snailfish(filename: String) extends DailyProblem[Int, Int] {
  private val input = FileUtilities.readFile(filename).map(Day18Snailfish.parse)

  private val trees = input.map(toTreeNode)

  private val root = trees.tail.foldLeft(trees.head)((acc, bit) => Day18Snailfish.add(acc, bit))

  val sumString: String = root.toString

  def getTree(idx : Int) : TreeNode = {
    toTreeNode(input(idx))
  }

  private val combos = trees.indices.flatMap(idx1 => trees.indices.map(idx2 => (idx1, idx2))).toList
  private val magnitudes = combos.map(e => Day18Snailfish.add(getTree(e._1), getTree(e._2)).magnitude())

  override val part1Answer: Int = root.magnitude()
  override val part2Answer: Int = magnitudes.max
}



