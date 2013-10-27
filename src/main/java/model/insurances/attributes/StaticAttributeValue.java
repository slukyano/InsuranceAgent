package model.insurances.attributes;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 27.10.13
 * Time: 0:54
 * To change this template use File | Settings | File Templates.
 */
public class StaticAttributeValue {
    private int staticValueId;
    private String name;
    private AttributeType type;
    private String value;
    private String description;

    public int getStaticValueId() {
        return staticValueId;
    }

    public String getName() {
        return name;
    }

    public AttributeType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public StaticAttributeValue(int staticValueId, String name, AttributeType type, String value, String description) {
        this.staticValueId = staticValueId;
        this.name = name;
        this.type = type;
        this.value = value;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaticAttributeValue)) return false;

        StaticAttributeValue that = (StaticAttributeValue) o;

        if (staticValueId != that.staticValueId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return staticValueId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StaticAttributeValue{");
        sb.append("staticValueId=").append(staticValueId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", value='").append(value).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
