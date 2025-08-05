package zerobase.weather;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zerobase.weather.service.DiaryService;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OpenWeatherApiTest {

    @Test
    @DisplayName("OpenWeatherMap JSON을 정상 파싱한다")
    void parseWeatherTest() throws Exception {
        // given
        String fakeJson = "{\n" +
                "  \"weather\": [\n" +
                "    {\n" +
                "      \"main\": \"Clear\",\n" +
                "      \"icon\": \"01d\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"main\": {\n" +
                "    \"temp\": 298.15\n" +
                "  }\n" +
                "}";

        DiaryService service = new DiaryService(null, null);

        // private 메서드 접근
        Method parseMethod = DiaryService.class.getDeclaredMethod("parseWeather", String.class);
        parseMethod.setAccessible(true);

        // when
        Map<String, Object> result = (Map<String, Object>) parseMethod.invoke(service, fakeJson);

        // then
        assertEquals("Clear", result.get("main"));
        assertEquals("01d", result.get("icon"));
        assertEquals(298.15, (double) result.get("temp"));
    }
}
