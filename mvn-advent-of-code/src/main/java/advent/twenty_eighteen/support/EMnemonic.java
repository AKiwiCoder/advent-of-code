package advent.twenty_eighteen.support;

public enum EMnemonic {
    /*        Addition:
     *        addr (add register) stores into register C the result of adding register A and register B.
     *        addi (add immediate) stores into register C the result of adding register A and value B.
     */
    addr() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = registers[a] + registers[b];
        }
    },
    addi() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = registers[a] + b;
        }
    },

    /*        Multiplication:
     *        mulr (multiply register) stores into register C the result of multiplying register A and register B.
     *        muli (multiply immediate) stores into register C the result of multiplying register A and value B.
     */
    mulr() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = registers[a] * registers[b];
        }
    },
    muli() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = registers[a] * b;
        }
    },

    /*        Bitwise AND:
     *        banr (bitwise AND register) stores into register C the result of the bitwise AND of register A and register B.
     *        bani (bitwise AND immediate) stores into register C the result of the bitwise AND of register A and value B.
     */
    banr() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = registers[a] & registers[b];
        }
    },
    bani() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = registers[a] & b;
        }
    },

    /*        Bitwise OR:
     *        borr (bitwise OR register) stores into register C the result of the bitwise OR of register A and register B.
     *        bori (bitwise OR immediate) stores into register C the result of the bitwise OR of register A and value B.
     */
    borr() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = registers[a] | registers[b];
        }
    },
    bori() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = registers[a] | b;
        }
    },

    /*        Assignment:
     *        setr (set register) copies the contents of register A into register C. (Input B is ignored.)
     *        seti (set immediate) stores value A into register C. (Input B is ignored.)
     */
    setr() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = registers[a];
        }
    },
    seti() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = a;
        }
    },

    /*        Greater-than testing:
     *        gtir (greater-than immediate/register) sets register C to 1 if value A is greater than register B. Otherwise, register C is set to 0.
     *        gtri (greater-than register/immediate) sets register C to 1 if register A is greater than value B. Otherwise, register C is set to 0.
     *        gtrr (greater-than register/register) sets register C to 1 if register A is greater than register B. Otherwise, register C is set to 0.
     */
    gtir() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = (a > registers[b]) ? 1 : 0;
        }
    },
    gtri() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = (registers[a] > b) ? 1 : 0;
        }
    },
    gtrr() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = (registers[a] > registers[b]) ? 1 : 0;
        }
    },

    /*        Equality testing:
     *        eqir (equal immediate/register) sets register C to 1 if value A is equal to register B. Otherwise, register C is set to 0.
     *        eqri (equal register/immediate) sets register C to 1 if register A is equal to value B. Otherwise, register C is set to 0.
     *        eqrr (equal register/register) sets register C to 1 if register A is equal to register B. Otherwise, register C is set to 0.
     */
    eqir() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = (a == registers[b]) ? 1 : 0;
        }
    },
    eqri() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = (registers[a] == b) ? 1 : 0;
        }
    },
    eqrr() {
        @Override
        public void execute(int a, int b, int c, int[] registers) {
            registers[c] = (registers[a] == registers[b]) ? 1 : 0;
        }
    };

    public boolean isPossible(int a, int b, int c, int[] input, int[] output) {
        int[] working = new int[input.length];
        System.arraycopy(input, 0, working, 0, input.length);
        execute(a, b, c, working);

        for (int i = 0; i != working.length; i++) {
            if (working[i] != output[i]) {
                return false;
            }
        }
        return true;
    }

    public abstract void execute(int a, int b, int c, int[] registers);
}
