package adsd.demo.ovappavo;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class DisplayTrip {
    private final String displayString;
    private List<Image> iconsStart = new ArrayList<>();
    private List<Image> iconsEnd = new ArrayList<>();
    private final String[] iconPaths = {"src/main/java/images/OVapp/rampIcon.png",
            "src/main/java/images/OVapp/toiletIcon.jpeg",
            "src/main/java/images/OVapp/serviceEmployeeIcon.png",
            "src/main/java/images/OVapp/AEDIcon.jpeg"};

    public DisplayTrip(String displayString, boolean[] iconsStart, boolean[] iconsEnd) {
        this.displayString = displayString;
        if (iconsStart != null) {
            this.iconsStart = getIcons(iconsStart);
        }

        if (iconsEnd != null) {
            this.iconsEnd = getIcons(iconsEnd);
        }
    }

    private List<Image> getIcons(boolean[] services) {
        List<Image> icons = new ArrayList<>();
        int index = -1;
        for (boolean service : services) {
            index++;
            if (service) {
                icons.add(new Image("file:" + iconPaths[index], 32,32, true, true));
            }
        }
        return icons;
    }

    public String getDisplayString() {
        return displayString;
    }

    public List<Image> getIconsStart() {
        return iconsStart;
    }

    public List<Image> getIconsEnd() {
        return iconsEnd;
    }
}

