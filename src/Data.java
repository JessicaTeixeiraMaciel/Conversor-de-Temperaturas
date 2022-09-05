import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {
    final Map<String, String> symbolTemps = new HashMap<>() {{
        put("CELSIUS", "ºC");
        put("FAHRENHEIT", "°F");
        put("KELVIN", "K");
    }};

    List<Double> tempsImput = new ArrayList<>();
    List<Double> tempsOutput = new ArrayList<>();

    String unitInput = "";
    String unitOutput = "";
}
