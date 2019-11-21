from advent.utilities.file_reader import FileReader


class Day01NoTimeForATaxicab:
    @staticmethod
    def move_convertor(line):
        return [(bit.strip()[0], int(bit.strip()[1:])) for bit in line.split(',')]

    _left_turn = {
        "North": "West",
        "South": "East",
        "West": "South",
        "East": "North"
    }
    _right_turn = {
        "North": "East",
        "South": "West",
        "West": "North",
        "East": "South"
    }

    _deltas = {
        "North": (0, 1),
        "South": (0, -1),
        "West": (-1, 0),
        "East": (1, 0)
    }

    def __init__(self, filename):
        moves = FileReader.read_file(filename, Day01NoTimeForATaxicab.move_convertor)[0]

        facing = 'North'
        pos = (0, 0)
        first_match = None
        path = []

        for (turn, steps) in moves:
            if turn == 'R':
                facing = self._right_turn[facing]
            if turn == 'L':
                facing = self._left_turn[facing]
            delta = self._deltas[facing]
            for s in range(0, steps):
                pos = (pos[0] + delta[0], pos[1] + delta[1])
                if pos in path and first_match is None:
                    first_match = pos
                path.append(pos)

        self.part_1_answer = self.manhatten_distance((0, 0), pos)
        self.part_2_answer = self.manhatten_distance((0, 0), first_match)

    @staticmethod
    def manhatten_distance(start, end):
        return abs(start[0] - end[0]) + abs(start[1] - end[1])
