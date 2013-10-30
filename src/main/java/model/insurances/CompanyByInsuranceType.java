package model.insurances;

/**
 * Created with IntelliJ IDEA.
 * User: nik
 * Date: 31.10.13
 * Time: 1:57
 * To change this template use File | Settings | File Templates.
 */
public class CompanyByInsuranceType {

    private final int companyByInsuranceTypeID;
    private final String companyByInsuranceTypeName;
    private final String companyByInsuranceTypeDescription;

    public CompanyByInsuranceType(int companyByInsuranceTypeID, String companyByInsuranceTypeName, String companyByInsuranceTypeDescription) {
        this.companyByInsuranceTypeID = companyByInsuranceTypeID;
        this.companyByInsuranceTypeName = companyByInsuranceTypeName;
        this.companyByInsuranceTypeDescription = companyByInsuranceTypeDescription;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyByInsuranceType)) return false;

        CompanyByInsuranceType companyByInsuranceType = (CompanyByInsuranceType) o;

        if (companyByInsuranceTypeID != companyByInsuranceType.companyByInsuranceTypeID) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return companyByInsuranceTypeID;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Company{");
        sb.append("companyByInsuranceTypeID=").append(companyByInsuranceTypeID);
        sb.append(", companyByInsuranceTypeName='").append(companyByInsuranceTypeName).append('\'');
        sb.append(", companyByInsuranceTypeDescription=").append(companyByInsuranceTypeDescription);
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
