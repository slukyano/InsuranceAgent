package model.insurances.attributes;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 26.10.13
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */
public abstract class InsuranceAttribute {
    private final int attributeId;
    private final int insuranceId;
    private final String attributeName;
    private final AttributeType type;

    public AttributeType getType() {
        return type;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public abstract String getValue();

    protected InsuranceAttribute(int attributeId, int insuranceId, String attributeName, AttributeType type) {

        this.attributeId = attributeId;
        this.insuranceId = insuranceId;
        this.attributeName = attributeName;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsuranceAttribute)) return false;

        InsuranceAttribute that = (InsuranceAttribute) o;

        if (attributeId != that.attributeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return attributeId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InsuranceAttribute{");
        sb.append("attributeId=").append(attributeId);
        sb.append(", insuranceId=").append(insuranceId);
        sb.append(", attributeName='").append(attributeName).append('\'');
        sb.append(", type=").append(type);
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
