import re

from advent.utilities.file_reader import FileReader


class Day02IWasToldThereWouldBeNoMath:
    def __init__(self, filename):
        presents = FileReader.read_file(filename, Day02IWasToldThereWouldBeNoMath.present_converter)
        self.part_1_answer = sum([Day02IWasToldThereWouldBeNoMath.calc_1(present) for present in presents])
        self.part_2_answer = sum([Day02IWasToldThereWouldBeNoMath.calc_2(present) for present in presents])

    @staticmethod
    def calc_1(present):
        a = present[0]
        b = present[1]
        c = present[2]
        return 3 * a * b + 2 * b * c + 2 * a * c

    @staticmethod
    def calc_2(present):
        a = present[0]
        b = present[1]
        c = present[2]
        return 2 * a + 2 * b + a * b * c

    @staticmethod
    def present_converter(line):
        m = re.match(r'([0-9]*)x([0-9]*)x([0-9]*)', line)
        r = [int(m.group(1)), int(m.group(2)), int(m.group(3))]
        r.sort()
        return r
