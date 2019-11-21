import unittest

from advent.twenty_fifteen.day02_I_was_told_there_would_be_no_math import Day02IWasToldThereWouldBeNoMath


class Day02IWasToldThereWouldBeNoMathTestCase(unittest.TestCase):
    def test_real(self):
        dp = Day02IWasToldThereWouldBeNoMath("data/twenty_fifteen/Day02-IWasToldThereWouldBeNoMath-input.txt")
        self.assertEqual(1606483, dp.part_1_answer)
        self.assertEqual(3842356, dp.part_2_answer)

    def test_example_1(self):
        dp = Day02IWasToldThereWouldBeNoMath("data/twenty_fifteen/Day02-IWasToldThereWouldBeNoMath-example#1.txt")
        self.assertEqual(101, dp.part_1_answer)
        self.assertEqual(48, dp.part_2_answer)


if __name__ == '__main__':
    unittest.main()
