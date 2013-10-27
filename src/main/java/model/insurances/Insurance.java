package model.insurances;

import model.Agent;
import model.Company;
import model.clients.Client;
import model.insurances.attributes.InsuranceAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 26.10.13
 * Time: 20:54
 * To change this template use File | Settings | File Templates.
 */
public class Insurance {
    private final int insuranceId;
    private final Client client;
    private final Company company;
    private final Agent agent;
    private final InsuranceType type;
    private final ArrayList<InsuranceAttribute> attributes;

    public ArrayList<InsuranceAttribute> getAttributes() {
        return attributes;
    }

    public InsuranceType getType() {
        return type;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public Client getClient() {
        return client;
    }

    public Company getCompany() {
        return company;
    }

    public Agent getAgent() {
        return agent;
    }

    protected Insurance(int insuranceId, Client client, Company company, Agent agent, InsuranceType type, ArrayList<InsuranceAttribute> attributes) {
        this.insuranceId = insuranceId;
        this.client = client;
        this.company = company;
        this.agent = agent;
        this.type = type;
        this.attributes = attributes;
    }

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
        sb.append(", client=").append(client);
        sb.append(", company=").append(company);
        sb.append(", agent=").append(agent);
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
