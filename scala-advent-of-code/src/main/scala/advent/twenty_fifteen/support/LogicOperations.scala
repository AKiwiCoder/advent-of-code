package advent.twenty_fifteen.support

abstract class Input {
  def ready(wires: Map[String, Int]): Boolean

  def value(wires: Map[String, Int]): Int
}

case class WireInput(name: String) extends Input {
  override def ready(wires: Map[String, Int]): Boolean = wires.contains(name)

  override def value(wires: Map[String, Int]): Int = wires(name)
}

case class ConstantInput(value: Int) extends Input {
  override def ready(wires: Map[String, Int]): Boolean = true

  override def value(wires: Map[String, Int]): Int = value
}

abstract class LogicOperation {
  def ready(wires: Map[String, Int]): Boolean

  def update(wires: Map[String, Int]): Map[String, Int]
}

case class SignalOperation(in: Input, out: String) extends LogicOperation {
  override def ready(wires: Map[String, Int]): Boolean = in.ready(wires) && !wires.contains(out)

  override def update(wires: Map[String, Int]): Map[String, Int] = wires + (out -> in.value(wires))
}

case class AndOperation(lhs: Input, rhs: Input, out: String) extends LogicOperation {
  override def ready(wires: Map[String, Int]): Boolean = lhs.ready(wires) && rhs.ready(wires) && !wires.contains(out)

  override def update(wires: Map[String, Int]): Map[String, Int] = wires + (out -> (lhs.value(wires) & rhs.value(wires)))
}

case class OrOperation(lhs: Input, rhs: Input, out: String) extends LogicOperation {
  override def ready(wires: Map[String, Int]): Boolean = lhs.ready(wires) && rhs.ready(wires) && !wires.contains(out)

  override def update(wires: Map[String, Int]): Map[String, Int] = wires + (out -> (lhs.value(wires) | rhs.value(wires)))
}

case class LShiftOperation(in: Input, count: Int, out: String) extends LogicOperation {
  override def ready(wires: Map[String, Int]): Boolean = in.ready(wires) && !wires.contains(out)

  override def update(wires: Map[String, Int]): Map[String, Int] = wires + (out -> (in.value(wires) << count))
}

case class RShiftOperation(in: Input, count: Int, out: String) extends LogicOperation {
  override def ready(wires: Map[String, Int]): Boolean = in.ready(wires) && !wires.contains(out)

  override def update(wires: Map[String, Int]): Map[String, Int] = wires + (out -> (in.value(wires) >> count))
}

case class NotOperation(in: Input, out: String) extends LogicOperation {
  override def ready(wires: Map[String, Int]): Boolean = in.ready(wires) && !wires.contains(out)

  override def update(wires: Map[String, Int]): Map[String, Int] = wires + (out -> ((-in.value(wires) - 1) + 65536))
}
