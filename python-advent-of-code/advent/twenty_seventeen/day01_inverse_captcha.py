from advent.utilities.file_reader import FileReader


class Day01InverseCaptcha:
    def __init__(self, filename):
        captchas = FileReader.read_file(filename)

        self.part_1_answer = 0
        for captcha in captchas:
            self.part_1_answer = self.part_1_answer + self.p1_calc(captcha.strip())

        self.part_2_answer = 0
        for captcha in captchas:
            self.part_2_answer = self.part_2_answer + self.p2_calc(captcha.strip())

    @staticmethod
    def p1_calc(captcha):
        retval = 0
        for i in range(len(captcha)):
            if captcha[i] == captcha[(i + 1) % len(captcha)]:
                retval = retval + int(captcha[i:i + 1])
        return retval

    @staticmethod
    def p2_calc(captcha):
        retval = 0
        for i in range(len(captcha)):
            if captcha[i] == captcha[(i + len(captcha) // 2) % len(captcha)]:
                retval = retval + int(captcha[i:i + 1])
        return retval
