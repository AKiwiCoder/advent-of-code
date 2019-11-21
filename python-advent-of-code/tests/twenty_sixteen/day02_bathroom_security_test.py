import unittest

from advent.twenty_sixteen.day02_bathroom_security import Day02BathroomSecurity


class Day02BathroomSecurityTestCase(unittest.TestCase):
    def test_real(self):
        dp = Day02BathroomSecurity("data/twenty_sixteen/Day02-BathroomSecurity-input.txt")
        self.assertEqual("14894", dp.part_1_answer)
        self.assertEqual("26B96", dp.part_2_answer)

    def test_example_1(self):
        dp = Day02BathroomSecurity("data/twenty_sixteen/Day02-BathroomSecurity-example#1.txt")
        self.assertEqual("1985", dp.part_1_answer)
        self.assertEqual("5DB3", dp.part_2_answer)


if __name__ == '__main__':
    unittest.main()
