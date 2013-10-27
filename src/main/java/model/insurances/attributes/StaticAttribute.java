package model.insurances.attributes;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 26.10.13
 * Time: 20:51
 * To change this template use File | Settings | File Templates.
 */
public class StaticAttribute extends InsuranceAttribute {
    private StaticAttributeValue staticAttributeValue;

    public StaticAttributeValue getStaticAttributeValue() {
        return staticAttributeValue;
    }

    @Override
    public String getValue() {
        return staticAttributeValue.getValue();
    }

    public StaticAttribute(int attributeId, int insuranceId, String attributeName, AttributeType type, StaticAttributeValue staticAttributeValue) {
        super(attributeId, insuranceId, attributeName, type);
        this.staticAttributeValue = staticAttributeValue;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StaticAttribute{");
        sb.append("staticAttributeValue=").append(staticAttributeValue);
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
