package model.clients;

import model.Agent;
import model.ModelController;
import model.insurances.Insurance;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Client {
    protected final int clientId;
    private final int agentId;

    protected Client(int clientId, int agentId) {
        this.clientId = clientId;
        this.agentId = agentId;
    }

    public int getClientId() {
        return clientId;
    }

    public int getAgentId() {
        return agentId;
    }

    public Agent getAgent() throws SQLException {
        return ModelController.getInstance().getAgent(agentId);
    }

    public abstract ArrayList<Insurance> getInsurances() throws SQLException;

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
