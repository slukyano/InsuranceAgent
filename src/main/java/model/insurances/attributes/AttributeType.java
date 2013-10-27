package model.insurances.attributes;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 26.10.13
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */
public class AttributeType {
    private final int typeId;
    private final String name;
    private final String description;

    public AttributeType(int typeId, String name, String description) {
        this.typeId = typeId;
        this.name = name;
        this.description = description;

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
