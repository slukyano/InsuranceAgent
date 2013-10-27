package model.insurances.attributes;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 26.10.13
 * Time: 20:51
 * To change this template use File | Settings | File Templates.
 */
public class RegularAttribute extends InsuranceAttribute {
    private final String value;

    @Override
    public String getValue() {
        return value;
    }

    public RegularAttribute(int attributeId, int insuranceId, String attributeName, AttributeType type, String value) {
        super(attributeId, insuranceId, attributeName, type);
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RegularAttribute{");
        sb.append("value='").append(value).append('\'');
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
