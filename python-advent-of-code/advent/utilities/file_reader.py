import os

from advent.utilities.converters import Converters


class FileReader:
    @staticmethod
    def read_file(filename, converter=Converters.as_string):
        cwd = os.getcwd()
        found_filename = cwd + "/" + filename
        for i in range(0, 3):
            if not os.path.isfile(found_filename):
                cwd = cwd + "/.."
                found_filename = cwd + "/" + filename

        if not os.path.isfile(found_filename):
            raise FileNotFoundError('File does not exist. ' + found_filename)

        with open(found_filename) as file:
            content = file.read().splitlines()

        return [converter(line) for line in content]
