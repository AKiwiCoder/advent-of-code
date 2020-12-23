package advent.twenty_twenty

class Node(val value : Int, var next: Node, var prev : Node) {
  def getValue() : Int = {
    value
  }
}

class CircularList(var head : Node = null) {
  private var index : Map[Int, Node] = Map()
  private var max : Int = -1

  def getHead() : Node = {
    head
  }

  def getMaxValue() : Int = {
    max
  }

  def insertAfter(value : Int, at : Node = head) : Node = {
    if (head == null) {
      head = new Node(value, null, null)
      index = index + (value -> head)
      max = value
      head.next = head
      head.prev = head
      head
    } else {
      val node = new Node(value, at.next, at)
      index = index + (value -> node)
      max = if (value > max) value else max
      at.next.prev = node
      at.next = node
      node
    }
  }

  def insertBefore(value : Int, at : Node = head) : Node = {
    if (head == null) {
      head = new Node(value, null, null)
      index = index + (value -> head)
      max = value
      head.next = head
      head.prev = head
      head
    } else {
      val node = new Node(value, at, at.prev)
      index = index + (value -> node)
      max = if (value > max) value else max
      at.prev.next = node
      at.prev = node
      node
    }
  }

  def find(value : Int) : Node = {
    index(value)
  }

  def moveHeadOne() : Node = {
    head = head.next
    head
  }

  def cutThree(from : Node = head) : Node = {
    val one = from.next
    val three = from.next.next.next

    from.next = three.next
    three.next.prev = from
    one
  }

  def spliceThree(from : Node, splice : Node): Unit = {
    val spliceEnd = splice.next.next

    spliceEnd.next = from.next
    splice.prev = from

    from.next.prev = splice
    from.next = splice
  }

  final def dump( node : Node = head, sep : String = ", ") : String = {
    if (node == null) {
      "<empty>"
    } else {
      var output = ""
      var current = node
      do {
        output +=  current.value + (if (current.next == node) "" else sep)
        current = current.next
      } while (current != node)
      output
    }
  }
}