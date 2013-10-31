package model.insurances;

import model.Agent;
import model.Company;
import model.ModelController;
import model.clients.Client;
import model.insurances.attributes.InsuranceAttribute;

import java.sql.SQLException;
import java.util.ArrayList;

public class Insurance {
    //region Fields
    private final int insuranceId;
    private final int clientId;
    private final String clientType;
    private final int companyByInsuranceTypeId;
    private final int agentId;
    private final double baseValue;
    //endregion

    //region Getters
    public int getInsuranceId() {
        return insuranceId;
    }

    public int getClientId() {
        return clientId;
    }

    public Client getClient() throws SQLException {
        return clientType.equals("LEGAL")
                ? ModelController.getInstance().getLegalPerson(clientId)
                : ModelController.getInstance().getNaturalPerson(clientId);
    }

    public double getBaseValue() {
        return baseValue;
    }

    public int getCompanyByInsuranceTypeId() {
        return companyByInsuranceTypeId;
    }

    public CompanyByInsuranceType getCompanyByInsuranceType() throws SQLException {
        return ModelController.getInstance().getCompanyByInsuranceType(companyByInsuranceTypeId);
    }

    public Company getCompany() throws SQLException {
        return getCompanyByInsuranceType().getCompany();
    }

    public InsuranceType getInsuranceType() throws SQLException {
        return getCompanyByInsuranceType().getInsuranceType();
    }

    public String getClientType() {
        return clientType;
    }

    public int getAgentId() {
        return agentId;
    }

    public Agent getAgent() throws SQLException {
        return ModelController.getInstance().getAgent(agentId);
    }

    public ArrayList<InsuranceAttribute> getAttributes() throws SQLException {
        return ModelController.getInstance().getInsuranceAttributes(this);
    }
    //endregion

    //region Constructors
    public Insurance(int insuranceId, int clientId, String clientType, int companyByInsuranceTypeId, int agentId, double baseValue) {
        this.insuranceId = insuranceId;
        this.clientId = clientId;
        this.clientType = clientType;
        this.agentId = agentId;
        this.baseValue = baseValue;
        this.companyByInsuranceTypeId = companyByInsuranceTypeId;
    }
    //endregion

    //region Public Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Insurance)) return false;

        Insurance insurance = (Insurance) o;

        if (insuranceId != insurance.insuranceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return insuranceId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Insurance{");
        sb.append("insuranceId=").append(insuranceId);
        sb.append(", clientId=").append(clientId);
        sb.append(", clientType=").append(clientType);
        sb.append(", CompanyByInsuranceTypeID =").append(companyByInsuranceTypeId);
        sb.append(", agentId=").append(agentId);
        sb.append(", baseValue=").append(baseValue);
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
    //endregion
}
