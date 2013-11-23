package model.insurances.attributes;

public class AttributeType {
    private final int attributeTypeId;
    private final String name;
    private final String description;
    private final int cbitID;

    public AttributeType(int attributeTypeId, String name, String description, int cbitID) {
        this.attributeTypeId = attributeTypeId;
        this.name = name;
        this.description = description;
        this.cbitID = cbitID;
    }

    public int getCbitID() {
        return cbitID;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {

        return name;
    }

    public int getAttributeTypeId() {

        return attributeTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttributeType)) return false;

        AttributeType that = (AttributeType) o;

        if (attributeTypeId != that.attributeTypeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return attributeTypeId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AttributeType{");
        sb.append("attributeTypeId=").append(attributeTypeId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
