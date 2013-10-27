package model.insurances.attributes;

public class StaticAttributeValue {
    private final int staticValueId;
    private final String name;
    private final int typeId;
    private final String value;
    private final String description;

    public int getStaticValueId() {
        return staticValueId;
    }

    public String getName() {
        return name;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public StaticAttributeValue(int staticValueId, String name, int typeId, String value, String description) {
        this.staticValueId = staticValueId;
        this.name = name;
        this.typeId = typeId;
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
        sb.append(", typeId=").append(typeId);
        sb.append(", value='").append(value).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
