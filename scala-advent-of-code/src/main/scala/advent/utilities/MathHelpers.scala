package advent.utilities

object MathHelpers {
  def sign(number: Int): Int = {
    if (number > 0) {
      1
    } else if (number < 0) {
      -1
    } else {
      0
    }
  }
}
