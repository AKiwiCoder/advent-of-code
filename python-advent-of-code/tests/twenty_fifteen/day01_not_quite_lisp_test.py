import unittest

from advent.twenty_fifteen.day01_not_quite_list import Day01NotQuiteLisp


class Day01NotQuiteLispTestCase(unittest.TestCase):
    def test_real(self):
        dp = Day01NotQuiteLisp("data/twenty_fifteen/Day01-NotQuiteLisp-input.txt")
        self.assertEqual(74, dp.part_1_answer)
        self.assertEqual(1795, dp.part_2_answer)

    def test_example_1(self):
        dp = Day01NotQuiteLisp("data/twenty_fifteen/Day01-NotQuiteLisp-example#1.txt")
        self.assertEqual(0, dp.part_1_answer)
        self.assertEqual(-1, dp.part_2_answer)

    def test_example_2(self):
        dp = Day01NotQuiteLisp("data/twenty_fifteen/Day01-NotQuiteLisp-example#2.txt")
        self.assertEqual(0, dp.part_1_answer)
        self.assertEqual(-1, dp.part_2_answer)

    def test_example_3(self):
        dp = Day01NotQuiteLisp("data/twenty_fifteen/Day01-NotQuiteLisp-example#3.txt")
        self.assertEqual(3, dp.part_1_answer)
        self.assertEqual(-1, dp.part_2_answer)

    def test_example_4(self):
        dp = Day01NotQuiteLisp("data/twenty_fifteen/Day01-NotQuiteLisp-example#4.txt")
        self.assertEqual(3, dp.part_1_answer)
        self.assertEqual(-1, dp.part_2_answer)

    def test_example_5(self):
        dp = Day01NotQuiteLisp("data/twenty_fifteen/Day01-NotQuiteLisp-example#5.txt")
        self.assertEqual(3, dp.part_1_answer)
        self.assertEqual(1, dp.part_2_answer)

    def test_example_6(self):
        dp = Day01NotQuiteLisp("data/twenty_fifteen/Day01-NotQuiteLisp-example#6.txt")
        self.assertEqual(-1, dp.part_1_answer)
        self.assertEqual(3, dp.part_2_answer)

    def test_example_7(self):
        dp = Day01NotQuiteLisp("data/twenty_fifteen/Day01-NotQuiteLisp-example#7.txt")
        self.assertEqual(-1, dp.part_1_answer)
        self.assertEqual(1, dp.part_2_answer)

    def test_example_8(self):
        dp = Day01NotQuiteLisp("data/twenty_fifteen/Day01-NotQuiteLisp-example#8.txt")
        self.assertEqual(-3, dp.part_1_answer)
        self.assertEqual(1, dp.part_2_answer)

    def test_example_9(self):
        dp = Day01NotQuiteLisp("data/twenty_fifteen/Day01-NotQuiteLisp-example#9.txt")
        self.assertEqual(-3, dp.part_1_answer)
        self.assertEqual(1, dp.part_2_answer)


if __name__ == '__main__':
    unittest.main()
