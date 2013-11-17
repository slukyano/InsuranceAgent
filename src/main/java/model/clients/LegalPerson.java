package model.clients;

import model.ModelController;
import model.insurances.Insurance;

import java.sql.SQLException;
import java.util.ArrayList;

public class LegalPerson extends Client {
    private final String name;
    private final String vatin;
    private final String address;

    @Override
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
    public ArrayList<Insurance> getInsurances() throws SQLException {
        return ModelController.getInstance().getInsurances(this);
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
