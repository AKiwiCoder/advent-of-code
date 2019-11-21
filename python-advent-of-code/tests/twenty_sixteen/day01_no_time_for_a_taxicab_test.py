import unittest

from advent.twenty_sixteen.day01_no_time_for_a_taxicab import Day01NoTimeForATaxicab


class Day01NoTimeForATaxicabTestCase(unittest.TestCase):
    def test_real(self):
        dp = Day01NoTimeForATaxicab("data/twenty_sixteen/Day01-NoTimeForATaxicab-input.txt")
        self.assertEqual(301, dp.part_1_answer)
        self.assertEqual(130, dp.part_2_answer)

    def test_example_1(self):
        dp = Day01NoTimeForATaxicab("data/twenty_sixteen/Day01-NoTimeForATaxicab-example#1.txt")
        self.assertEqual(8, dp.part_1_answer)
        self.assertEqual(4, dp.part_2_answer)


if __name__ == '__main__':
    unittest.main()
