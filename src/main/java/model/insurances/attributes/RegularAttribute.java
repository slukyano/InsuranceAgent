package model.insurances.attributes;

public class RegularAttribute extends InsuranceAttribute {
    private final String value;

    @Override
    public String getValue() {
        return value;
    }

    public RegularAttribute(int attributeId, int insuranceId, String attributeName, int typeId, String value) {
        super(attributeId, insuranceId, attributeName, typeId);
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
