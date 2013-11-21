package model.clients;

import model.ModelController;
import model.insurances.Insurance;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public String getFullName() {
        return getLastName() + " " + getFirstName() + " " + getSecondName();
    }

    @Override
    public String getName() {
        return getFullName();
    }

    @Override
    public String getClientType() {
        return "NATURAL";
    }

    public NaturalPerson(int clientId, int agentId, String firstName, String secondName, String lastName, Date dateOfBirth) {
        super(clientId, agentId);
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public ArrayList<Insurance> getInsurances() throws SQLException {
        return ModelController.getInstance().getInsurances(this);
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
