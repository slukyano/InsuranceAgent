package model.insurances.attributes;

import model.ModelController;

import java.sql.SQLException;

public class InsuranceAttribute {
    private final int attributeId;
    private final int insuranceId;
    private final String attributeValue;
    private final int attributeTypeId;

    public int getAttributeTypeId() {
        return attributeTypeId;
    }

    public String getAttributeValue() {

        return attributeValue;
    }

    public int getAttributeId() {
        return attributeId;

    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public String getAttributeName() {
        return attributeValue;
    }

    public int getTypeId() {
        return attributeTypeId;
    }

    public AttributeType getType() throws SQLException {
        return ModelController.getInstance().getAttributeType(attributeTypeId);
    }

    public InsuranceAttribute(int attributeId, int insuranceId, String attributeValue, int attributeTypeId) {
        this.attributeId = attributeId;
        this.insuranceId = insuranceId;
        this.attributeValue = attributeValue;
        this.attributeTypeId = attributeTypeId;
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
        sb.append(", attributeValue='").append(attributeValue).append('\'');
        sb.append(", attributeTypeId=").append(attributeTypeId);
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
