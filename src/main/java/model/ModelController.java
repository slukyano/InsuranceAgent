package model;

import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import model.insurances.CompanyByInsuranceType;
import model.insurances.Insurance;
import model.insurances.InsuranceType;
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
    public LegalPerson getLegalPerson(int clientId) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT LegalPersonID, LegalName, Address, vatin, AgentID"
                        + " FROM Legal_PERSONS"
                        + " WHERE LegalPersonID = ?");
        stmt.setDouble(1, clientId);

        ResultSet rSet = stmt.executeQuery();

        if (!rSet.next())
            return null;
        return new LegalPerson(rSet.getInt("LegalPersonID"),
                rSet.getInt("AgentId"),
                rSet.getString("LegalName"),
                rSet.getString("vatin"),
                rSet.getString("Address"));
    }
    //endregion

    //region Agent Factories
    public Agent getAgent(int agentId) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);


        PreparedStatement stmt = conn.prepareStatement(
                "SELECT AgentId,FirstName,SecondName,LastName,HiringDate,QuitDate"
                        + " FROM AGENTS"
                        + " WHERE AgentId = ?");
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

    public CompanyByInsuranceType getCompanyByInsuranceType(int companyByInsuranceTypeID) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT companyByInsuranceTypeID, companyByInsuranceTypeName,companyByInsuranceTypeDescription"
                        + " FROM insurance_types"
                        + " WHERE InsuranceTypeId = ?");
        stmt.setDouble(1, companyByInsuranceTypeID);

        ResultSet rSet = stmt.executeQuery();

        if (!rSet.next())
            return null;
        return new CompanyByInsuranceType(rSet.getInt("companyByInsuranceTypeID"),
                rSet.getString("companyByInsuranceTypeName"),
                rSet.getString("companyByInsuranceTypeDescription"));
    }
    //endregion

    //region Insurance Factories
    public Insurance getInsurance(int insuranceId) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT InsuranceID, ClientID,ClientType,CompanyByInsuranceTypeID,AgentId,BaseValue"
                        + " FROM INSURANCES"
                        + " WHERE InsuranceId = ?");
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

    public InsuranceType getInsuranceType(int insuranceTypeId) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT InsuranceTypeID, InsuranceTypeName,InsuranceTypeDescription"
                        + " FROM insurance_types"
                        + " WHERE InsuranceTypeId = ?");
        stmt.setDouble(1, insuranceTypeId);

        ResultSet rSet = stmt.executeQuery();

        if (!rSet.next())
            return null;
        return new InsuranceType(rSet.getInt("InsuranceTypeID"),
                rSet.getString("InsuranceTypeName"),
                rSet.getString("InsuranceTypeDescription"));
    }
    //endregion

    //region Attribute Factories
    public AttributeType getAttributeType(int typeId) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT AttributeId,AttributeName,AttributeDescription,CompanyByInsuranceTypeId"
                        + " FROM ATTRIBUTE_TYPES+"
                        + " WHERE AttributeTypeId = ?");
        stmt.setDouble(1, typeId);

        ResultSet rSet = stmt.executeQuery();

        if (!rSet.next())
            return null;
        return new AttributeType(rSet.getInt("AttributeId"),
                rSet.getString("AttributeName"),
                rSet.getString("AttributeDescription"),
                rSet.getString("CompanyByInsuranceTypeId"));
    }

    public InsuranceAttribute getInsuranceAttribute(int attributeId) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT AttributeID, AttrinuteTypeId,AttributeValue,InsuranceId "
                        + " FROM INSURANCE_ATTRIBUTES"
                        + " WHERE AttributeId = ?");
        stmt.setDouble(1, attributeId);

        ResultSet rSet = stmt.executeQuery();

        if (!rSet.next())
            return null;
        return new InsuranceAttribute(rSet.getInt("AttributeID"),
                rSet.getInt("AttributeTypeId"),
                rSet.getString("AttributeValue"),
                rSet.getInt("InsuranceId"));
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
