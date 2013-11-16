package model.insurances.attributes;

public class AttributeType {
    private final int typeId;
    private final String name;
    private final String description;

    public AttributeType(int typeId, String name, String description) {
        this.typeId = typeId;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {

        return name;
    }

    public int getTypeId() {

        return typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttributeType)) return false;

        AttributeType that = (AttributeType) o;

        if (typeId != that.typeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return typeId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AttributeType{");
        sb.append("typeId=").append(typeId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
