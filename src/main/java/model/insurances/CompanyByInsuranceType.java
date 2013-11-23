package model.insurances;

import model.Company;
import model.ModelController;
import model.insurances.attributes.AttributeType;

import java.sql.SQLException;
import java.util.List;

public class CompanyByInsuranceType {
    private final int companyByInsuranceTypeId;
    private final int companyId;
    private final int insuranceTypeId;

    public int getCompanyByInsuranceTypeId() {
        return companyByInsuranceTypeId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public Company getCompany() throws SQLException {
        return ModelController.getInstance().getCompany(companyId);
    }

    public int getInsuranceTypeId() {
        return insuranceTypeId;
    }

    public InsuranceType getInsuranceType() throws SQLException {
        return ModelController.getInstance().getInsuranceType(insuranceTypeId);
    }

    public List<AttributeType> getAttributeTypes() throws SQLException {
        return ModelController.getInstance().getAttributeTypes(this);
    }

    public CompanyByInsuranceType(int companyByInsuranceTypeId, int companyId, int insuranceTypeId) {
        this.companyByInsuranceTypeId = companyByInsuranceTypeId;
        this.companyId = companyId;
        this.insuranceTypeId = insuranceTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyByInsuranceType)) return false;

        CompanyByInsuranceType that = (CompanyByInsuranceType) o;

        if (companyByInsuranceTypeId != that.companyByInsuranceTypeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return companyByInsuranceTypeId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompanyByInsuranceType{");
        sb.append("companyByInsuranceTypeId=").append(companyByInsuranceTypeId);
        sb.append(", companyId=").append(companyId);
        sb.append(", insuranceTypeId=").append(insuranceTypeId);
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
