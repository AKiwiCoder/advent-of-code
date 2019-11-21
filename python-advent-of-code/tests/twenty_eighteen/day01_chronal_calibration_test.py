import unittest

from advent.twenty_eighteen.day01_chronal_calibration import Day01ChronalCalibration


class Day01ChronalCalibrationTestCase(unittest.TestCase):
    def test_real(self):
        cc = Day01ChronalCalibration("data/twenty_eighteen/Day01-ChronalCalibration-input.txt")
        self.assertEqual(430, cc.part_1_answer)
        self.assertEqual(462, cc.part_2_answer)

    def test_example_1(self):
        cc = Day01ChronalCalibration("data/twenty_eighteen/Day01-ChronalCalibration-example#1.txt")
        self.assertEqual(3, cc.part_1_answer)
        self.assertEqual(2, cc.part_2_answer)

    def test_example_2(self):
        cc = Day01ChronalCalibration("data/twenty_eighteen/Day01-ChronalCalibration-example#2.txt")
        self.assertEqual(0, cc.part_1_answer)
        self.assertEqual(0, cc.part_2_answer)

    def test_example_3(self):
        cc = Day01ChronalCalibration("data/twenty_eighteen/Day01-ChronalCalibration-example#3.txt")
        self.assertEqual(4, cc.part_1_answer)
        self.assertEqual(10, cc.part_2_answer)

    def test_example_4(self):
        cc = Day01ChronalCalibration("data/twenty_eighteen/Day01-ChronalCalibration-example#4.txt")
        self.assertEqual(4, cc.part_1_answer)
        self.assertEqual(5, cc.part_2_answer)

    def test_example_5(self):
        cc = Day01ChronalCalibration("data/twenty_eighteen/Day01-ChronalCalibration-example#5.txt")
        self.assertEqual(1, cc.part_1_answer)
        self.assertEqual(14, cc.part_2_answer)


if __name__ == '__main__':
    unittest.main()
