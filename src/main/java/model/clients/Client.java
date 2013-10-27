package model.clients;

import model.Agent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 26.10.13
 * Time: 20:20
 * To change this template use File | Settings | File Templates.
 */
public abstract class Client {
    private final int clientId;
    private final Agent agent;

    public int getClientId() {
        return clientId;
    }

    public Agent getAgent() {
        return agent;
    }

    protected Client(int clientId, Agent agent) {
        this.clientId = clientId;
        this.agent = agent;
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
        sb.append(", agent=").append(agent);
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
