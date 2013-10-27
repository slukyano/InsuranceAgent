package model.clients;

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
