package model;

import model.insurances.CompanyByInsuranceType;
import model.insurances.InsuranceType;

import java.sql.SQLException;
import java.util.ArrayList;

public class Company {
    private final int companyId;
    private final String name;
    private final int parentCompanyId;
    private final String description;

    public int getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public int getParentCompanyId() {
        return parentCompanyId;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<CompanyByInsuranceType> getCompaniesByInsuranceType() throws SQLException {
        return ModelController.getInstance().getCompaniesByInsuranceTypes(this);
    }

    public ArrayList<InsuranceType> getInsuranceTypes() throws SQLException {
        ArrayList<CompanyByInsuranceType> compList = getCompaniesByInsuranceType();
        ArrayList<InsuranceType> list = new ArrayList<InsuranceType>(compList.size());
        for (CompanyByInsuranceType companyByInsuranceType : compList)
            list.add(companyByInsuranceType.getInsuranceType());
        return list;
    }

    public Company(int companyId, String name, int parentCompanyId, String description) {
        this.companyId = companyId;
        this.name = name;
        this.parentCompanyId = parentCompanyId;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;

        Company company = (Company) o;

        if (companyId != company.companyId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return companyId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Company{");
        sb.append("companyId=").append(companyId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", parentCompanyId=").append(parentCompanyId);
        sb.append(", description='").append(description).append('\'');
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
