package model.insurances;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 26.10.13
 * Time: 20:37
 * To change this template use File | Settings | File Templates.
 */
public class InsuranceType {
    private final int insuranceTypeId;
    private final String name;
    private final String description;

    public int getInsuranceTypeId() {
        return insuranceTypeId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public InsuranceType(int insuranceTypeId, String name, String description) {
        this.insuranceTypeId = insuranceTypeId;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsuranceType)) return false;

        InsuranceType that = (InsuranceType) o;

        if (insuranceTypeId != that.insuranceTypeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return insuranceTypeId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InsuranceType{");
        sb.append("insuranceTypeId=").append(insuranceTypeId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
