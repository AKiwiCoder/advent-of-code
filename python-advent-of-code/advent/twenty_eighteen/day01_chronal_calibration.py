import functools

from advent.utilities.file_reader import FileReader
from advent.utilities.converters import Converters


def calc_sum(a, b):
    return a + b


class Day01ChronalCalibration:
    def __init__(self, filename):
        frequency_changes = FileReader.read_file(filename, Converters.as_integer)
        self.part_1_answer = functools.reduce(calc_sum, frequency_changes, 0)

        frequencies = set()
        frequency = 0
        index = 0
        while frequency not in frequencies:
            if index >= len(frequency_changes):
                index = 0
            frequencies.add(frequency)
            frequency = frequency + frequency_changes[index]
            index = index + 1
        self.part_2_answer = frequency
