package adsd.demo.ovappavo;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class TripDisplayCell {
    private final String displayString;
    private List<Image> iconsStart = new ArrayList<>();
    private List<Image> iconsEnd = new ArrayList<>();
    private final String[] iconPaths = {"src/main/java/images/OVapp/rampIcon.png",
            "src/main/java/images/OVapp/toiletIcon.jpg",
            "src/main/java/images/OVapp/serviceEmployeeIcon.png",
            "src/main/java/images/OVapp/AEDIcon.jpeg"};

    public TripDisplayCell(String displayString, boolean[] iconsStart, boolean[] iconsEnd) {
        this.displayString = displayString;
        this.iconsStart = getIcons(iconsStart);
        this.iconsEnd = getIcons(iconsEnd);
    }
    public TripDisplayCell(String displayString) {
        this.displayString = displayString;
    }

    private List<Image> getIcons(boolean[] services) {
        List<Image> icons = new ArrayList<>();
        
        int index = -1;
        for (boolean service : services) {
            index++;
            if (service) {
                icons.add(new Image("file:" + iconPaths[index], 32, 32, true, true));
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

