package model.clients;

public abstract class Client {
    private final int clientId;
    private final int agentId;

    protected Client(int clientId, int agentId) {
        this.clientId = clientId;
        this.agentId = agentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (clientId != client.clientId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return clientId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("clientId=").append(clientId);
        sb.append(", agentId=").append(agentId);
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
