from advent.utilities.file_reader import FileReader


class Day02BathroomSecurity:
    _keypad_part_1 = [
        ['*', '*', '*', '*', '*'],  #
        ['*', '1', '2', '3', '*'],  #
        ['*', '4', '5', '6', '*'],  #
        ['*', '7', '8', '9', '*'],  #
        ['*', '*', '*', '*', '*']  #
    ]

    _keypad_part_2 = [
        ['*', '*', '*', '*', '*', '*', '*'],  #
        ['*', '*', '*', '1', '*', '*', '*'],  #
        ['*', '*', '2', '3', '4', '*', '*'],  #
        ['*', '5', '6', '7', '8', '9', '*'],  #
        ['*', '*', 'A', 'B', 'C', '*', '*'],  #
        ['*', '*', '*', 'D', '*', '*', '*'],  #
        ['*', '*', '*', '*', '*', '*', '*']  #
    ]

    def __init__(self, filename):
        digits = FileReader.read_file(filename)
        self.part_1_answer = Day02BathroomSecurity.move(self._keypad_part_1, digits, (2, 2))
        self.part_2_answer = Day02BathroomSecurity.move(self._keypad_part_2, digits, (3, 1))

    @staticmethod
    def move(keypad, digits, start):
        pressed_digits = []
        current_position = start
        for movements in digits:
            for move in movements:
                new_position = current_position
                if move == 'U':
                    new_position = (current_position[0] - 1, current_position[1])
                if move == 'D':
                    new_position = (current_position[0] + 1, current_position[1])
                if move == 'L':
                    new_position = (current_position[0], current_position[1] - 1)
                if move == 'R':
                    new_position = (current_position[0], current_position[1] + 1)

                if keypad[new_position[0]][new_position[1]] != '*':
                    current_position = new_position
            pressed_digits.append(keypad[current_position[0]][current_position[1]])
        return "".join(pressed_digits)
