import static org.junit.jupiter.api.Assertions.assertEquals;

import adsd.demo.ovappavo.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

class TestTripReadFromFile {

    TripFile tripFile = new TripFile("src/main/resources/data/tripHistory.txt");
    TrainData data = TrainData.getTrainDataInstance();

    @Test
    @DisplayName("parse of text of trip")
    void testCorrectParse() {
        Trip expected = tripFile.stringToTripFromFile("15:00,Groningen,Amersfoort,285.19,238,Train,Groningen-Amersfoort");
        Trip actual = new Trip(LocalTime.parse("15:00"), data.findLocation("Groningen"), data.findLocation("Amersfoort"),
                285.19,238,"Train", List.of(data.findLocation("Groningen"), data.findLocation("Amersfoort")));

        assertEquals(expected, actual);
    }
}
