package advent.twenty_fifteen;

import advent.common.DailyProblem;
import advent.utilities.FileUtilities;
import com.google.gson.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Day12JSAbacusFrameworkIO implements DailyProblem<Integer, Integer> {
    private final int part1Answer;
    private final int part2Answer;

    private static final JsonParser parser = new JsonParser();

    private static JsonElement PARSE_JSON(String text) {
        return parser.parse(text.trim());
    }

    public Day12JSAbacusFrameworkIO(String filename) {
        List<JsonElement> json = FileUtilities.readLines(filename, Day12JSAbacusFrameworkIO::PARSE_JSON);

        this.part1Answer = json.stream().mapToInt(entity -> calculateSum(entity, false)).sum();
        this.part2Answer = json.stream().mapToInt(entity -> calculateSum(entity, true)).sum();
    }

    private int calculateSum(JsonElement element, boolean ignoreRed) {
        int total = 0;
        if (element.isJsonObject()) {
            boolean redDetected = false;
            JsonObject object = element.getAsJsonObject();
            for (Map.Entry<String, JsonElement> e : object.entrySet()) {
                if (e.getValue().isJsonPrimitive() && e.getValue().getAsJsonPrimitive().getAsString().equals("red")) {
                    redDetected = true;
                }
                total += calculateSum(e.getValue(), ignoreRed);
            }
            if (ignoreRed && redDetected) {
                return 0;
            }
        } else if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            Iterator<JsonElement> iter = array.iterator();
            while (iter.hasNext()) {
                total += calculateSum(iter.next(), ignoreRed);
            }
        } else if (element.isJsonPrimitive()) {
            JsonPrimitive primitive = element.getAsJsonPrimitive();
            if (primitive.isNumber()) {
                total += primitive.getAsInt();
            }
        } else {
            System.out.println("Unknown: " + element);
        }
        return total;
    }

    @Override
    public Integer getPart1Answer() {
        return part1Answer;
    }

    @Override
    public Integer getPart2Answer() {
        return part2Answer;
    }
}
