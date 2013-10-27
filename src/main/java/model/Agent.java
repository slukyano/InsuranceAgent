package model;

import java.util.Date;

public class Agent {
    private final int agentId;
    private final String firstName;
    private final String secondName;
    private final String lastName;
    private final Date hiringDate;
    private final Date quitDate;

    public int getAgentId() {
        return agentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public Date getQuitDate() {
        return quitDate;
    }

    public Agent(int agentId, String firstName, String secondName, String lastName, Date hiringDate, Date quitDate) {
        this.agentId = agentId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.hiringDate = hiringDate;
        this.quitDate = quitDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agent)) return false;

        Agent agent = (Agent) o;

        if (agentId != agent.agentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return agentId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Agent{");
        sb.append("agentId=").append(agentId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", hiringDate=").append(hiringDate);
        sb.append(", quitDate=").append(quitDate);
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
