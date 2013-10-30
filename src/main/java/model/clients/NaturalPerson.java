package model.clients;

import model.insurances.Insurance;

import java.util.Collection;
import java.util.Date;

public class NaturalPerson extends Client {
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

    public NaturalPerson(int clientId, int agentId, String firstName, String secondName, String lastName, Date dateOfBirth) {
        super(clientId, agentId);
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public Collection<Insurance> getInsurances() {
              return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NaturalPerson)) return false;

        Client client = (Client) o;

        if (clientId != client.clientId) return false;

        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NaturalPerson{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append("} ");
        sb.append(super.toString());

        return sb.toString();
    }
}
