package model.insurances;

public class Insurance {
    //region Fields
    private final int insuranceId;
    private final int clientId;
    private final int companyId;
    private final int agentId;
    private final int typeId;
    //endregion

    //region Getters
    public int getInsuranceId() {
        return insuranceId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getAgentId() {
        return agentId;
    }

    public int getTypeId() {
        return typeId;
    }
    //endregion

    //region Constructors
    public Insurance(int insuranceId, int clientId, int companyId, int agentId, int typeId) {
        this.insuranceId = insuranceId;
        this.clientId = clientId;
        this.companyId = companyId;
        this.agentId = agentId;
        this.typeId = typeId;
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
        sb.append(", companyId=").append(companyId);
        sb.append(", agentId=").append(agentId);
        sb.append(", typeId=").append(typeId);
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
    //endregion
}
