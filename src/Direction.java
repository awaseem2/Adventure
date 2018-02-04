import com.google.gson.annotations.SerializedName;

public class Direction {
    @SerializedName("directionName") private String name;
    private String room;

    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
    }

}
