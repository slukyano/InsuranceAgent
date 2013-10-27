package model;

import model.clients.Client;
import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import model.insurances.Insurance;
import model.insurances.attributes.*;

import java.sql.*;

public class ModelController {
    //region Static Members
    private static ModelController ourInstance;

    public static ModelController getInstance() {
        return ourInstance;
    }

    public static void initializeInstance(String connectionUrl, String username, String password) {
        ourInstance = new ModelController(connectionUrl, username, password);
    }
    //endregion

    //region Fields
    private final String connectionUrl;
    private final String username;
    private final String password;
    //endregion

    //region Constructors
    private ModelController(String connectionUrl, String username, String password) {
        this.connectionUrl = connectionUrl;
        this.username = username;
        this.password = password;
    }
    //endregion

    //region Factories
    //region Client Factories
    public Client getClient(int clientId) throws SQLException {
        ResultSet rSet = executeQuery("SELECT * FROM CLIENTS"
                + "JOIN NATURAL_PERSONS USING (ClientId)"
                + "JOIN LEGAL_PERSONS USING (ClientId)"
                + "WHERE ClientId = " + clientId);

        if (!rSet.next())
            return null;
        return (isLegal) ? new LegalPerson() : new NaturalPerson();
    }
    //endregion

    //region Agent Factories
    public Agent getAgent(int agentId) throws SQLException {
        ResultSet rSet = executeQuery("SELECT * FROM AGENTS WHERE AgentId = " + agentId);

        if (!rSet.next())
            return null;
        return new Agent();
    }
    //endregion

    //region Company Factories
    public Company getCompany(int companyId) throws SQLException {
        ResultSet rSet = executeQuery("SELECT * FROM COMPANIES WHERE CompanyId = " + companyId);

        if (!rSet.next())
            return null;
        return new Company();
    }
    //endregion

    //region Insurance Factories
    public Insurance getInsurance(int insuranceId) throws SQLException {
        ResultSet rSet = executeQuery("SELECT * FROM INSURANCES WHERE InsuranceId = " + insuranceId);

        if (!rSet.next())
            return null;
        return new Insurance();
    }
    //endregion

    //region Attribute Factories
    public AttributeType getAttributeType(int typeId) throws SQLException {
        ResultSet rSet = executeQuery("SELECT * FROM ATTRIBUTE_TYPES WHERE AttributeTypeId = " + typeId);

        if (!rSet.next())
            return null;
        return new AttributeType(rSet.getInt(0), rSet.getString(1), rSet.getString(2));
    }

    public InsuranceAttribute getInsuranceAttribute(int attributeId) throws SQLException {
        ResultSet rSet = executeQuery("SELECT * FROM INSURANCE_ATTRIBUTES WHERE AttributeId = " + attributeId);

        if (!rSet.next())
            return null;
        return (isStatic) ? new StaticAttribute() : new RegularAttribute();
    }

    public StaticAttributeValue getStaticAttributeValue(int valueId) throws SQLException {
        ResultSet rSet = executeQuery("SELECT * FROM STATIC_ATTRIBUTES WHERE StaticAttributeId = " + valueId);

        if (!rSet.next())
            return null;
        return new StaticAttributeValue(rSet.getInt(0),
                rSet.getString(1), rSet.getInt(2), rSet.getString(3), rSet.getString(4));
    }
    //endregion
    //endregion

    //region Database Management
    private ResultSet executeQuery(String queryString) throws SQLException {
        Statement stmt;
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);
        stmt = conn.createStatement();
        ResultSet rSet = stmt.executeQuery(queryString);
        stmt.close();
        conn.close();
        return rSet;
    }

    private void executeUpdate(String updateString) throws SQLException {
        Statement stmt;
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);
        stmt = conn.createStatement();
        stmt.executeUpdate(updateString);
        stmt.close();
        conn.close();
    }
    //endregion
}
