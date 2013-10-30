package model.clients;

import model.insurances.Insurance;

import java.util.Collection;

public class LegalPerson extends Client {
    private final String name;
    private final String vatin;
    private final String address;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getVatin() {
        return vatin;
    }

    public LegalPerson(int clientId, int agentId, String name, String vatin, String address) {
        super(clientId, agentId);
        this.name = name;
        this.vatin = vatin;
        this.address = address;
    }

    @Override
    public Collection<Insurance> getInsurances() {
         return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LegalPerson)) return false;

        Client client = (Client) o;

        if (clientId != client.clientId) return false;

        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LegalPerson{");
        sb.append("name='").append(name).append('\'');
        sb.append(", vatin='").append(vatin).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
