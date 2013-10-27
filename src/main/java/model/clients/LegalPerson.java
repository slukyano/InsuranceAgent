package model.clients;

import model.Agent;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 26.10.13
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */
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

    protected LegalPerson(int clientId, Agent agent, String name, String vatin, String address) {
        super(clientId, agent);
        this.name = name;
        this.vatin = vatin;
        this.address = address;
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
