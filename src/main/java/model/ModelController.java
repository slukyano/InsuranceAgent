package model;

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
    public NaturalPerson getNaturalPerson(int clientId) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT NaturalPersonID, FirstName, SecondName, LastName, DateOfBirth, AgentID"
                        + " FROM NATURAL_PERSONS"
                        + " WHERE NaturalPersonID = ?");
        stmt.setDouble(1, clientId);

        ResultSet rSet = stmt.executeQuery();

        if (!rSet.next())
            return null;
        return new NaturalPerson(rSet.getInt("NaturalPersonID"),
                rSet.getInt("AgentId"),
                rSet.getString("FirstName"),
                rSet.getString("SecondName"),
                rSet.getString("LastName"),
                rSet.getDate("DateOfBirth"));
    }
    //endregion

    //region Agent Factories
    public Agent getAgent(int agentId) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);


        PreparedStatement stmt = conn.prepareStatement (
                "SELECT AgentId,FirstName,SecondName,LastName,HiringDate,QuitDate"
                    +" FROM AGENTS"
                    +" WHERE AgentId = ?");
        stmt.setDouble(1, agentId);

        ResultSet rSet = stmt.executeQuery();

        if (!rSet.next())
            return null;
        return new Agent(rSet.getInt("AgentId"),
                rSet.getString("FirstName"),
                rSet.getString("SecondName"),
                rSet.getString("LastName"),
                rSet.getDate("HiringDate"),
                rSet.getDate("QuitDate"));
    }
    //endregion

    //region Company Factories
    public Company getCompany(int companyId) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT CompanyID, CompanyName,ParentCompanyId,CompanyDescription "
                    + " FROM COMPANIES"
                    + " WHERE CompanyId = ?");
        stmt.setDouble(1, companyId);

        ResultSet rSet = stmt.executeQuery();

        if (!rSet.next())
            return null;
        return new Company(rSet.getInt("CompanyID"),
                rSet.getString("CompanyName"),
                rSet.getInt("ParentCompanyId"),
                rSet.getString("CompanyDescription"));
    }
    //endregion

    //region Insurance Factories
    public Insurance getInsurance(int insuranceId) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT InsuranceID, ClientID,ClientType,CompanyByInsuranceTypeID,AgentId,BaseValue"
                    +" FROM INSURANCES"
                    +" WHERE InsuranceId = ?");
        stmt.setDouble(1, insuranceId);

        ResultSet rSet = stmt.executeQuery();

        if (!rSet.next())
            return null;
        return new Insurance(rSet.getInt("InsuranceID"),
                rSet.getInt("ClientID"),
                rSet.getString("ClientType"),
                rSet.getInt("CompanyByInsuranceTypeID"),
                rSet.getInt("AgentId"),
                rSet.getDouble("BaseValue"));
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
        //stmt.close();
        //conn.close();
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
