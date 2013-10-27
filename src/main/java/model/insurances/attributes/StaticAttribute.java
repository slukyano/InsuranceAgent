package model.insurances.attributes;

import model.ModelController;

import java.sql.SQLException;

public class StaticAttribute extends InsuranceAttribute {
    private final int staticAttributeValueId;

    public int getStaticAttributeValueId() {
        return staticAttributeValueId;
    }

    public StaticAttribute(int attributeId, int insuranceId, String attributeName, int typeId, int staticAttributeValueId) {
        super(attributeId, insuranceId, attributeName, typeId);
        this.staticAttributeValueId = staticAttributeValueId;
    }

    @Override
    public String getValue() {
        try {
            return ModelController.getInstance().getStaticAttributeValue(staticAttributeValueId).getValue();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StaticAttribute{");
        sb.append("staticAttributeValueId=").append(staticAttributeValueId);
        sb.append(", value='").append(getValue()).append('\'');
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
