package model.clients;

import model.Agent;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 26.10.13
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */
public class NaturalClient extends Client {
    private final String firstName;
    private final String secondName;
    private final String lastName;
    private final Date dateOfBirth;

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getLastName() {
        return lastName;

    }

    protected NaturalClient(int clientId, Agent agent, String firstName, String secondName, String lastName, Date dateOfBirth) {
        super(clientId, agent);
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NaturalClient{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
