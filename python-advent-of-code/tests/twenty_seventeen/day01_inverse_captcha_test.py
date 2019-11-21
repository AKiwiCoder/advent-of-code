import unittest

from advent.twenty_seventeen.day01_inverse_captcha import Day01InverseCaptcha


class Day01InverseCaptchaTestCase(unittest.TestCase):
    def test_real(self):
        dp = Day01InverseCaptcha("data/twenty_seventeen/Day01-InverseCaptcha-input.txt")
        self.assertEqual(1253, dp.part_1_answer)
        self.assertEqual(1278, dp.part_2_answer)

    def test_example_1(self):
        dp = Day01InverseCaptcha("data/twenty_seventeen/Day01-InverseCaptcha-example#1.txt")
        self.assertEqual(16, dp.part_1_answer)
        self.assertEqual(10, dp.part_2_answer)

    def test_example_2(self):
        dp = Day01InverseCaptcha("data/twenty_seventeen/Day01-InverseCaptcha-example#2.txt")
        self.assertEqual(3, dp.part_1_answer)
        self.assertEqual(26, dp.part_2_answer)


if __name__ == '__main__':
    unittest.main()
