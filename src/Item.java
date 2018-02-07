import java.util.Objects;

public class Item {
    private String name;
    private String use;

    public String getName() {
        return name;
    }

    public String getUse() {
        return use;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUse(String use) {
        this.use = use;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(use, item.use);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, use);
    }
}
