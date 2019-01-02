package advent.twenty_fifteen.support;

public class LightCommand {
    private final Operation operation;
    private final int sRow;
    private final int sCol;
    private final int eRow;
    private final int eCol;

    enum Operation {
        On, Off, Toggle
    }

    public LightCommand(Operation operation, int sRow, int sCol, int eRow, int eCol) {
        this.operation = operation;
        this.sRow = sRow;
        this.sCol = sCol;
        this.eRow = eRow;
        this.eCol = eCol;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getStartRow() {
        return sRow;
    }

    public int getStartCol() {
        return sCol;
    }

    public int getEndRow() {
        return eRow;
    }

    public int getEndCol() {
        return eCol;
    }

    public void modify(Light light) {
        switch (operation) {
            case On:
                light.turnOn();
                light.setBrightness(light.getBrightness() + 1);
                break;
            case Off:
                light.turnOff();
                light.setBrightness(Math.max(0, light.getBrightness() - 1));
                break;
            case Toggle:
                light.toggle();
                light.setBrightness(light.getBrightness() + 2);
                break;
        }
    }

    @Override
    public String toString() {
        return "LightCommand{" +
                "operation=" + operation +
                ", sRow=" + sRow +
                ", sCol=" + sCol +
                ", eRow=" + eRow +
                ", eCol=" + eCol +
                '}';
    }

    /*
                turn on 0,0 through 999,999
                toggle 0,0 through 999,0
                turn off 499,499 through 500,500
                 */
    public static LightCommand PARSE(String line) {
        String[] bits = line.split(" ");
        if (line.startsWith("turn on ")) {
            return PARSE_COMMAND(bits, Operation.On, 2, 4);
        } else if (line.startsWith("toggle ")) {
            return PARSE_COMMAND(bits, Operation.Toggle, 1, 3);
        } else if (line.startsWith("turn off ")) {
            return PARSE_COMMAND(bits, Operation.Off, 2, 4);
        } else {
            throw new IllegalArgumentException("Unknown Command '" + line + "'");
        }
    }

    private static LightCommand PARSE_COMMAND(String[] bits, Operation operation, int start, int end) {
        String[] coords;
        int sRow, sCol, eRow, eCol;
        coords = bits[start].split(",");
        sRow = Integer.parseInt(coords[0]);
        sCol = Integer.parseInt(coords[1]);
        coords = bits[end].split(",");
        eRow = Integer.parseInt(coords[0]);
        eCol = Integer.parseInt(coords[1]);
        return new LightCommand(operation, sRow, sCol, eRow, eCol);
    }
}
