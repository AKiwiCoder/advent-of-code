from advent.utilities.file_reader import FileReader


class Day01NotQuiteLisp:
    def __init__(self, filename):
        steps = FileReader.read_file(filename)[0]

        basement = -1
        floor = 0
        index = 0

        for step in steps:
            index = index + 1
            if step == '(':
                floor = floor + 1
            if step == ')':
                floor = floor - 1
            if floor == -1 and basement == -1:
                basement = index

        self.part_1_answer = floor
        self.part_2_answer = basement
