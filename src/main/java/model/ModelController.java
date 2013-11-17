package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import model.insurances.CompanyByInsuranceType;
import model.insurances.Insurance;
import model.insurances.InsuranceType;
import model.insurances.attributes.AttributeType;
import model.insurances.attributes.InsuranceAttribute;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class ModelController {
    //region Static Members
    private static ModelController ourInstance;



    public static ModelController getInstance() {
        return ourInstance;
    }

    public static void initializeInstance(String connectionUrl, String username, String password) throws SQLException {
        // will throw exception, if fail to log in
        Locale.setDefault(Locale.ENGLISH);
        DriverManager.getConnection(connectionUrl, username, password).close();


        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement selectRole = conn.prepareStatement("select Role from session_roles where role like 'INSURANCE_%'");
        ResultSet rSet = selectRole.executeQuery();

        if (!rSet.next()) {
            ourInstance = new ModelController(connectionUrl, username, password, UserType.UNAUTHORIZED);
            return;
        }
        String role = rSet.getString("Role");
        selectRole.close();
        UserType userType = UserType.fromRoleName(role);
        switch (userType) {
            case ADMIN:
                ourInstance = new ModelController(connectionUrl, username, password, UserType.ADMIN);
                break;
            case UNAUTHORIZED:
                ourInstance = new ModelController(connectionUrl, username, password, UserType.UNAUTHORIZED);
                break;
            default:
                PreparedStatement selectType = conn.prepareStatement(
                    "SELECT dataId,userType from USERS_AND_USERDATA" +
                            "  where userId = SYS_CONTEXT ('USERENV', 'SESSION_USERID')");
                ResultSet selectTypeRSet = selectType.executeQuery();
                if (!selectTypeRSet.next()) {
                    ourInstance = new ModelController(connectionUrl, username, password, UserType.UNAUTHORIZED);
                    break;
                }
                int dataId = selectTypeRSet.getInt("dataId");
                ourInstance = new ModelController(connectionUrl, username, password, userType, dataId);

                selectTypeRSet.close();

        }
        conn.close();

    }
    //endregion

    //region Fields
    private final String connectionUrl;
    private final String username;
    private final String password;
    private final UserType userType;
    private int dataId;
    //endregion

    //region Getters
    public UserType getUserType() {
        return userType;
    }
    //endregion

    //region Constructors
    private ModelController(String connectionUrl, String username, String password, UserType userType) {
        this.connectionUrl = connectionUrl;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    public ModelController(String connectionUrl, String username, String password, UserType userType, int dataId) {
        this(connectionUrl,username,password,userType);
        this.dataId = dataId;
    }
    //endregion



    public Object getUserObject() throws SQLException {
        switch (userType) {
            case AGENT:
            case MANAGER:
                return getAgent(dataId);
            case NATURAL:
                return getNaturalPerson(dataId);
            case LEGAL:
                return getLegalPerson(dataId);
            default:
                return null;
        }
    }



    //region Factory Methods
    //region Generic Factories
    private <T> T getObject(ObjectFactory<T> factory, String sqlQuery) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        ResultSet rSet = stmt.executeQuery();

        T result = rSet.next()
                ? factory.createObject(rSet)
                : null;

        stmt.close();
        conn.close();

        return result;
    }

    private <T> ArrayList<T> getObjects(ObjectFactory<T> factory, String sqlQuery) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        ResultSet rSet = stmt.executeQuery();

        ArrayList<T> list = new ArrayList<T>();
        while (rSet.next())
            list.add(factory.createObject(rSet));

        stmt.close();
        conn.close();

        return list;
    }

    private void executeSQL(String sqlQuery)  throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        stmt.executeUpdate();

        stmt.close();
        conn.close();

    }
    //endregion

    //region NaturalPerson Factories
    public NaturalPerson getNaturalPerson(int NaturalPersonID) throws SQLException {
        String sql = "SELECT NaturalPersonID, FirstName, SecondName, LastName, DateOfBirth, AgentID"
                        + " FROM NATURAL_PERSONS"
                        + " WHERE NaturalPersonID = " + NaturalPersonID;

        return getObject(NaturalPersonFactory.getInstance(), sql);
    }

    public ArrayList<NaturalPerson> getNaturalPersons() throws SQLException {
        String sql = "SELECT NaturalPersonID, FirstName, SecondName, LastName, DateOfBirth, AgentID"
                        + " FROM NATURAL_PERSONS";
        return getObjects(NaturalPersonFactory.getInstance(), sql);
    }

    public ArrayList<NaturalPerson> getNaturalPersons(Agent agent) throws SQLException {
        String sql = "SELECT NaturalPersonID, FirstName, SecondName, LastName, DateOfBirth, AgentID"
                        + " FROM NATURAL_PERSONS"
                        + " WHERE AgentID = " + agent.getAgentId();
        return getObjects(NaturalPersonFactory.getInstance(), sql);
    }

    public void updateNaturalPerson(NaturalPerson naturalPerson)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE NATURAL_PERSONS"
                        + " SET FirstName = ?,"
                        +" SecondName = ?,"
                        +" LastName = ?,"
                        +" DateOfBirth = ?,"
                        +" AgentID = ?"
                        +" WHERE NaturalPersonID = ?");
        stmt.setString(1,naturalPerson.getFirstName());
        stmt.setString(2,naturalPerson.getSecondName());
        stmt.setString(3,naturalPerson.getLastName());
        stmt.setDate(4,new java.sql.Date(naturalPerson.getDateOfBirth().getTime()));
        stmt.setInt(5, naturalPerson.getAgentId());
        stmt.setInt(6,naturalPerson.getClientId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void createNaturalPerson(NaturalPerson naturalPerson)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO NATURAL_PERSONS (FirstName, SecondName, LastName, DateOfBirth, AgentID)"
                        + " VALUES ( ?,?,?,?,?)");
        stmt.setString(1,naturalPerson.getFirstName());
        stmt.setString(2,naturalPerson.getSecondName());
        stmt.setString(3,naturalPerson.getLastName());
        stmt.setDate(4,new java.sql.Date(naturalPerson.getDateOfBirth().getTime()));
        stmt.setInt(5,naturalPerson.getAgentId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO: should set an ID somehow
    }

    public void  deleteNaturalPerson(int ClientId)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM NATURAL_PERSONS"
                        + " WHERE NaturalPersonID = ?");
        stmt.setInt(1, ClientId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO delete cascade?
    }
    //endregion

    //region LegalPerson Factories
    public LegalPerson getLegalPerson(int clientId) throws SQLException {
        String sql = "SELECT LegalPersonID, LegalName, Address, vatin, AgentID"
                + " FROM Legal_PERSONS"
                + " WHERE LegalPersonID = " + clientId;
        return getObject(LegalPersonFactory.getInstance(), sql);
    }

    public ArrayList<LegalPerson> getLegalPersons() throws SQLException {
        String sql = "SELECT LegalPersonID, LegalName, Address, vatin, AgentID"
                        + " FROM LEGAL_PERSONS";
        return getObjects(LegalPersonFactory.getInstance(), sql);
    }

    public ArrayList<LegalPerson> getLegalPersons(Agent agent) throws SQLException {
        String sql = "SELECT LegalPersonID, LegalName, Address, vatin, AgentID"
                + " FROM LEGAL_PERSONS"
                + " WHERE AgentID = " + agent.getAgentId();
        return getObjects(LegalPersonFactory.getInstance(), sql);
    }

    public void updateLegalPerson(LegalPerson legalPerson)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Legal_PERSONS"
                        + " SET LegalName = ?,"
                        +" Address = ?,"
                        +" vatin = ?,"
                        +" AgentID = ?"
                        + " wHERE LegalPersonID = ? ");
        stmt.setString(1, legalPerson.getName());
        stmt.setString(2,legalPerson.getAddress());
        stmt.setString(3,legalPerson.getVatin());
        stmt.setInt(4, legalPerson.getAgentId());
        stmt.setInt(5, legalPerson.getClientId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void createLegalPerson(LegalPerson legalPerson)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Legal_PERSONS (LegalName, Address, vatin, AgentID)"
                    + " VALUES (?,?,?,?)");
        stmt.setString(1, legalPerson.getName());
        stmt.setString(2,legalPerson.getAddress());
        stmt.setString(3,legalPerson.getVatin());
        stmt.setInt(4, legalPerson.getAgentId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO: should set an ID somehow
    }

    public void  deleteLegalPerson(int ClientId)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM Legal_PERSONS"
                        + " WHERE LegalPersonID = ?");
        stmt.setInt(1, ClientId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO delete cascade?
    }
    //endregion

    //region Agent Factories
    public Agent getAgent(int agentId) throws SQLException {
        String sql = "SELECT AgentId,FirstName,SecondName,LastName,HiringDate,QuitDate"
                + " FROM AGENTS"
                + " WHERE AgentId = " +  agentId;
        return getObject(AgentFactory.getInstance(), sql);
    }

    public ArrayList<Agent> getAgents() throws SQLException {
        String sql = "SELECT AgentId,FirstName,SecondName,LastName,HiringDate,QuitDate"
                + " FROM AGENTS";
        return getObjects(AgentFactory.getInstance(), sql);
    }

    public void updateAgent(Agent agent)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE AGENTS"
                        + " SET FirstName = ?,"
                        +"SecondName = ?,"
                        +"LastName = ?,"
                        +"HiringDate = ?,"
                        +"QuitDate = ?"
                        + "WHERE AgentId = ?");
        stmt.setString(1,agent.getFirstName());
        stmt.setString(2,agent.getSecondName());
        stmt.setString(3,agent.getLastName());
        stmt.setDate(4, new java.sql.Date(agent.getHiringDate().getTime()));
        stmt.setDate(5,new java.sql.Date(agent.getQuitDate().getTime()));
        stmt.setInt(6,agent.getAgentId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void createAgent(Agent agent)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO AGENTS (FirstName,SecondName,LastName,HiringDate,QuitDate)"
                + "VALUES (?,?,?,?,?)");
        stmt.setString(1,agent.getFirstName());
        stmt.setString(2,agent.getSecondName());
        stmt.setString(3,agent.getLastName());
        stmt.setDate(4, new java.sql.Date(agent.getHiringDate().getTime()));
        stmt.setDate(5, new java.sql.Date(agent.getQuitDate().getTime()));
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void  deleteAgent(int AgentId)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM AGENTS"
                        + " WHERE AgentId = ? ");
        stmt.setInt(1, AgentId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
    //endregion

    //region Company Factories
    public Company getCompany(int companyId) throws SQLException {

        String sql = "SELECT CompanyID, CompanyName,ParentCompanyId,CompanyDescription "
                + " FROM COMPANIES"
                + " WHERE CompanyId = " + companyId;
        return getObject(CompanyFactory.getInstance(), sql);
    }

    public ArrayList<Company> getCompanies() throws SQLException {
        String sql = "SELECT CompanyID, CompanyName,ParentCompanyId,CompanyDescription "
                + " FROM COMPANIES";
        return getObjects(CompanyFactory.getInstance(), sql);
    }
    public void updateCompany(Company company)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE COMPANIES"
                        + " SET CompanyName = ?,"
                        +" ParentCompanyId = ?,"
                        +" CompanyDescription = ?"
                        + " WHERE CompanyId = ?");
        stmt.setString(1,company.getName());
        stmt.setInt(2, company.getParentCompanyId());
        stmt.setString(3,company.getDescription());
        stmt.setInt(4,company.getCompanyId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void createCompany(Company company)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO COMPANIES (CompanyName,ParentCompanyId,CompanyDescription)"
                        + " VALUES (?,?,?)");
        stmt.setString(1,company.getName());
        stmt.setInt(2,company.getParentCompanyId());
        stmt.setString(3,company.getDescription());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO: should set an ID somehow
    }

    public void  deleteCompany(int companyId)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM COMPANIES"
                        + " WHERE CompanyId = ?");
        stmt.setInt(1,companyId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO delete cascade?
    }
    //endregion

    //region CompanyByInsuranceType Factories
    public CompanyByInsuranceType getCompanyByInsuranceType(int companyByInsTypeId) throws SQLException {
        String sql = "SELECT companyByInsuranceTypeID, companyID, insuranceTypeID"
                + " FROM companies_by_insurance_type"
                + " WHERE companyByInsuranceTypeID = " + companyByInsTypeId;
        return getObject(CompanyByInsuranceTypeFactory.getInstance(), sql);
    }

    public ArrayList<CompanyByInsuranceType> getCompaniesByInsuranceTypes() throws SQLException {
        String sql = "SELECT companyByInsuranceTypeID, companyID, insuranceTypeID"
                + " FROM companies_by_insurance_type";
        return getObjects(CompanyByInsuranceTypeFactory.getInstance(), sql);
    }

    public ArrayList<CompanyByInsuranceType> getCompaniesByInsuranceTypes(Company company) throws SQLException {
        String sql = "SELECT companyByInsuranceTypeID, companyID, insuranceTypeID"
                + " FROM companies_by_insurance_type"
                + " WHERE companyID = " + company.getCompanyId();
        return getObjects(CompanyByInsuranceTypeFactory.getInstance(), sql);
    }
    public void updateCompanyByInsuranceType(CompanyByInsuranceType companyByInsuranceType)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE companies_by_insurance_type"
                        + " SET companyID = ?,"
                        +" insuranceTypeID = ?"
                        + " where companyByInsuranceTypeID = ?");
        stmt.setInt(1, companyByInsuranceType.getCompanyId());
        stmt.setInt(2,companyByInsuranceType.getInsuranceTypeId());
        stmt.setInt(3, companyByInsuranceType.getCompanyByInsuranceTypeId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();

    }

    public void createCompanyByInsuranceType(CompanyByInsuranceType companyByInsuranceType)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO companies_by_insurance_type (companyID, insuranceTypeID)"
                        + " VALUES (?,?)");
        stmt.setInt(1, companyByInsuranceType.getCompanyId());
        stmt.setInt(2,companyByInsuranceType.getInsuranceTypeId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO: should set an ID somehow
    }

    public void  deleteCompanyByInsuranceType(int companyByInsuranceTypeId)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM companies_by_insurance_type"
                        + " WHERE companyByInsuranceTypeID = ?");
        stmt.setInt(1,companyByInsuranceTypeId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO delete cascade?
    }
    //endregion

    //region Insurance Factories
    public Insurance getInsurance(int insuranceId) throws SQLException {
        String sql = "SELECT InsuranceID, ClientID,ClientType,CompanyByInsuranceTypeID,AgentId,BaseValue"
                + " FROM INSURANCES"
                + " WHERE InsuranceId = " + insuranceId;
        return getObject(InsuranceFactory.getInstance(), sql);
    }

    public ArrayList<Insurance> getInsurances() throws SQLException {
        String sql = "SELECT InsuranceID, ClientID,ClientType,CompanyByInsuranceTypeID,AgentId,BaseValue"
                + " FROM INSURANCES";
        return getObjects(InsuranceFactory.getInstance(), sql);
    }

    public ArrayList<Insurance> getInsurances(NaturalPerson person) throws SQLException {
        String sql = "SELECT InsuranceID, ClientID, ClientType, CompanyByInsuranceTypeID, AgentId, BaseValue"
                + " FROM INSURANCES"
                + " WHERE ClientID = " + person.getClientId()
                + " AND ClientType = 'NATURAL'";
        return getObjects(InsuranceFactory.getInstance(), sql);
    }

    public ArrayList<Insurance> getInsurances(LegalPerson person) throws SQLException {
        String sql = "SELECT InsuranceID, ClientID, ClientType, CompanyByInsuranceTypeID, AgentId, BaseValue"
                + " FROM INSURANCES"
                + " WHERE ClientID = " + person.getClientId()
                + " AND ClientType = 'LEGAL'";
        return getObjects(InsuranceFactory.getInstance(), sql);
    }

    public ArrayList<Insurance> getInsurances(Agent agent) throws SQLException {
        String sql = "SELECT InsuranceID, ClientID, ClientType, CompanyByInsuranceTypeID, AgentId, BaseValue"
                + " FROM INSURANCES"
                + " WHERE AgentID = " + agent.getAgentId();
        return getObjects(InsuranceFactory.getInstance(), sql);
    }
    public void updateInsurance(Insurance insurance)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE INSURANCES"
                        + "SET ClientID = ?,"
                        +" ClientType = ?,"
                        +" CompanyByInsuranceTypeID = ?,"
                        + " AgentId = ?,"
                        + " BaseValue = ?"
                        + " Where InsuranceId = ?");
        stmt.setInt(1,insurance.getClientId());
        stmt.setString(2, insurance.getClientType());
        stmt.setInt(3,insurance.getCompanyByInsuranceTypeId());
        stmt.setInt(4,insurance.getAgentId());
        stmt.setDouble(5,insurance.getBaseValue());
        stmt.setInt(6,insurance.getInsuranceId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void createInsurance(Insurance insurance)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO INSURANCES (ClientID, ClientType, CompanyByInsuranceTypeID, AgentId, BaseValue)"
                        + " VALUES (?,?,?,?,?)");
        stmt.setInt(1,insurance.getClientId());
        stmt.setString(2, insurance.getClientType());
        stmt.setInt(3,insurance.getCompanyByInsuranceTypeId());
        stmt.setInt(4,insurance.getAgentId());
        stmt.setDouble(5,insurance.getBaseValue());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO: should set an ID somehow
    }

    public void  deleteInsurance(int insuranceId)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM INSURANCES"
                        + " WHERE insuranceID = ?");
        stmt.setInt(1,insuranceId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO delete cascade?
    }
    //endregion

    //region InsuranceType Factories
    public InsuranceType getInsuranceType(int insuranceTypeId) throws SQLException {
        String sql = "SELECT InsuranceTypeID, InsuranceTypeName,InsuranceTypeDescription"
                + " FROM insurance_types"
                + " WHERE InsuranceTypeId = " + insuranceTypeId;
        return getObject(InsuranceTypeFactory.getInstance(), sql);
    }

    public ArrayList<InsuranceType> getInsuranceTypes() throws SQLException {
        String sql = "SELECT InsuranceTypeID, InsuranceTypeName,InsuranceTypeDescription"
                + " FROM insurance_types";
        return getObjects(InsuranceTypeFactory.getInstance(), sql);
    }
    public void updateInsuranceType(InsuranceType insuranceType)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE insurance_types"
                        + " SET InsuranceTypeName = ?,"
                        +" InsuranceTypeDescription = ?"
                        +" Where InsuranceTypeId = ?");
        stmt.setString(1,insuranceType.getName());
        stmt.setString(2, insuranceType.getDescription());
        stmt.setInt(3,insuranceType.getInsuranceTypeId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void createInsuranceType(InsuranceType insuranceType)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO insurance_types (InsuranceTypeName,InsuranceTypeDescription)"
                        + " VALUES (?,?)");
        stmt.setString(1,insuranceType.getName());
        stmt.setString(2, insuranceType.getDescription());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO: should set an ID somehow
    }

    public void  deleteInsuranceType(int insuranceTypeId)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM insurance_types"
                        + "WHERE InsuranceTypeId = ?");
        stmt.setInt(1,insuranceTypeId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO delete cascade?
    }
    //endregion

    //region Attribute Factories
    public AttributeType getAttributeType(int attributeTypeId) throws SQLException {
        String sql = "SELECT AttributeId,AttributeName,AttributeDescription"
                + " FROM ATTRIBUTE_TYPES"
                + " WHERE AttributeTypeId = " + attributeTypeId;
        return getObject(AttributeTypeFactory.getInstance(), sql);
    }

    public ArrayList<AttributeType> getAttributeTypes() throws SQLException {
        String sql = "SELECT AttributeId,AttributeName,AttributeDescription"
                + " FROM ATTRIBUTE_TYPES";
        return getObjects(AttributeTypeFactory.getInstance(), sql);
    }
    public void updateAttributeType(AttributeType attributeType)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE ATTRIBUTE_TYPES"
                        + " SET AttributeName = ?,"
                        +" AttributeDescription = ?"
                        +" Where AttributeTypeId = ?");
        stmt.setString(1,attributeType.getName());
        stmt.setString(2,attributeType.getDescription());
        stmt.setInt(3,attributeType.getTypeId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void createAttributeType(AttributeType attributeType)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO ATTRIBUTE_TYPES (AttributeName,AttributeDescription)"
                        + " VALUES (??)");
        stmt.setString(1,attributeType.getName());
        stmt.setString(2,attributeType.getDescription());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
       //TODO: should set an ID somehow
    }

    public void  deleteAttributeType(int attributeTypeId)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM ATTRIBUTE_TYPES"
                        + " WHERE AttributeTypeId = ?");
        stmt.setInt(1,attributeTypeId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO delete cascade?
    }

    public InsuranceAttribute getInsuranceAttribute(int insuranceAttributeId) throws SQLException {
        String sql = "SELECT AttributeID, AttributeTypeId,AttributeValue,InsuranceId "
                + " FROM INSURANCE_ATTRIBUTES"
                + " WHERE AttributeId = " + insuranceAttributeId;
        return getObject(InsuranceAttributeFactory.getInstance(), sql);
    }

    public ArrayList<InsuranceAttribute> getInsuranceAttributes() throws SQLException {
        String sql = "SELECT AttributeID, AttributeTypeId,AttributeValue,InsuranceId "
                + " FROM INSURANCE_ATTRIBUTES";
        return getObjects(InsuranceAttributeFactory.getInstance(), sql);
    }

    public ArrayList<InsuranceAttribute> getInsuranceAttributes(Insurance insurance) throws SQLException {
        String sql = "SELECT AttributeID, AttributeTypeId,AttributeValue,InsuranceId "
                + " FROM INSURANCE_ATTRIBUTES"
                + " WHERE InsuranceId = " + insurance.getInsuranceId();
        return getObjects(InsuranceAttributeFactory.getInstance(), sql);
    }
    public void updateInsuranceAttribute(InsuranceAttribute insuranceAttribute)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE INSURANCE_ATTRIBUTES"
                        + " SET AttributeTypeId = ?," + insuranceAttribute.getAttributeTypeId()
                        +" AttributeValue = ?," + insuranceAttribute.getAttributeValue()
                        +" InsuranceId = ?" +insuranceAttribute.getInsuranceId()
                        + " where AttributeId = ?");
        stmt.setInt(1,insuranceAttribute.getTypeId());
        stmt.setString(2,insuranceAttribute.getAttributeValue());
        stmt.setInt(3,insuranceAttribute.getInsuranceId());
        stmt.setInt(4,insuranceAttribute.getAttributeId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public void createInsuranceAttribute(InsuranceAttribute insuranceAttribute)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO INSURANCE_ATTRIBUTES (AttributeTypeId,AttributeValue,InsuranceId)"
                        + " VALUES (?,?,?)");
        stmt.setInt(1,insuranceAttribute.getTypeId());
        stmt.setString(2,insuranceAttribute.getAttributeValue());
        stmt.setInt(3,insuranceAttribute.getInsuranceId());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO: should set an ID somehow
    }

    public void  deleteInsuranceAttribute(int insuranceAttributeId)      throws SQLException {
        Connection conn = DriverManager.getConnection(connectionUrl, username, password);

        PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM INSURANCE_ATTRIBUTES"
                        + " WHERE AttributeId = ?");
        stmt.setInt(1,insuranceAttributeId);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        //TODO delete cascade?
    }
    //endregion
    //endregion

    private static abstract class ObjectFactory<T> {
        public abstract T createObject(ResultSet resultSet) throws SQLException;
    }

    private static class NaturalPersonFactory extends ObjectFactory<NaturalPerson> {
        private static NaturalPersonFactory instance = new NaturalPersonFactory();

        public static NaturalPersonFactory getInstance() {
            return instance;
        }

        @Override
        public NaturalPerson createObject(ResultSet rSet) throws SQLException {
            return new NaturalPerson(rSet.getInt("NaturalPersonID"),
                    rSet.getInt("AgentId"),
                    rSet.getString("FirstName"),
                    rSet.getString("SecondName"),
                    rSet.getString("LastName"),
                    rSet.getDate("DateOfBirth"));
        }
    }

    private static class LegalPersonFactory extends ObjectFactory<LegalPerson> {
        private static LegalPersonFactory instance = new LegalPersonFactory();

        public static LegalPersonFactory getInstance() {
            return instance;
        }

        @Override
        public LegalPerson createObject(ResultSet rSet) throws SQLException {
            return new LegalPerson(rSet.getInt("LegalPersonID"),
                    rSet.getInt("AgentId"),
                    rSet.getString("LegalName"),
                    rSet.getString("vatin"),
                    rSet.getString("Address"));
        }
    }

    private static class AgentFactory extends ObjectFactory<Agent> {
        private static AgentFactory instance = new AgentFactory();

        public static AgentFactory getInstance() {
            return instance;
        }

        @Override
        public Agent createObject(ResultSet rSet) throws SQLException {
            return new Agent(rSet.getInt("AgentId"),
                    rSet.getString("FirstName"),
                    rSet.getString("SecondName"),
                    rSet.getString("LastName"),
                    rSet.getDate("HiringDate"),
                    rSet.getDate("QuitDate"));
        }
    }

    private static class CompanyFactory extends ObjectFactory<Company> {
        private static CompanyFactory instance = new CompanyFactory();

        public static CompanyFactory getInstance() {
            return instance;
        }

        @Override
        public Company createObject(ResultSet rSet) throws SQLException {
            return new Company(rSet.getInt("CompanyID"),
                    rSet.getString("CompanyName"),
                    rSet.getInt("ParentCompanyId"),
                    rSet.getString("CompanyDescription"));
        }
    }

    private static class CompanyByInsuranceTypeFactory extends ObjectFactory<CompanyByInsuranceType> {
        private static CompanyByInsuranceTypeFactory instance = new CompanyByInsuranceTypeFactory();

        public static CompanyByInsuranceTypeFactory getInstance() {
            return instance;
        }

        @Override
        public CompanyByInsuranceType createObject(ResultSet rSet) throws SQLException {
            return new CompanyByInsuranceType(rSet.getInt("companyByInsuranceTypeID"),
                    rSet.getInt("companyID"),
                    rSet.getInt("insuranceID"));
        }
    }

    private static class InsuranceFactory extends ObjectFactory<Insurance> {
        private static InsuranceFactory instance = new InsuranceFactory();

        public static InsuranceFactory getInstance() {
            return instance;
        }

        @Override
        public Insurance createObject(ResultSet rSet) throws SQLException {
            return new Insurance(rSet.getInt("InsuranceID"),
                    rSet.getInt("ClientID"),
                    rSet.getString("ClientType"),
                    rSet.getInt("CompanyByInsuranceTypeID"),
                    rSet.getInt("AgentId"),
                    rSet.getDouble("BaseValue"));
        }
    }

    private static class InsuranceTypeFactory extends ObjectFactory<InsuranceType> {
        private static InsuranceTypeFactory instance = new InsuranceTypeFactory();

        public static InsuranceTypeFactory getInstance() {
            return instance;
        }

        @Override
        public InsuranceType createObject(ResultSet rSet) throws SQLException {
            return new InsuranceType(rSet.getInt("InsuranceTypeID"),
                    rSet.getString("InsuranceTypeName"),
                    rSet.getString("InsuranceTypeDescription"));
        }
    }

    private static class AttributeTypeFactory extends ObjectFactory<AttributeType> {
        private static AttributeTypeFactory instance = new AttributeTypeFactory();

        public static AttributeTypeFactory getInstance() {
            return instance;
        }

        @Override
        public AttributeType createObject(ResultSet rSet) throws SQLException {
            return new AttributeType(rSet.getInt("AttributeId"),
                    rSet.getString("AttributeName"),
                    rSet.getString("AttributeDescription"));
        }
    }

    private static class InsuranceAttributeFactory extends ObjectFactory<InsuranceAttribute> {
        private static InsuranceAttributeFactory instance = new InsuranceAttributeFactory();

        public static InsuranceAttributeFactory getInstance() {
            return instance;
        }

        @Override
        public InsuranceAttribute createObject(ResultSet rSet) throws SQLException {
            return new InsuranceAttribute(rSet.getInt("AttributeID"),
                    rSet.getInt("AttributeTypeId"),
                    rSet.getString("AttributeValue"),
                    rSet.getInt("InsuranceId"));
        }
    }
      //TODO delete when debug done and crear package list
    public static void main(String[] args) {
        try {
            // will throw exception if fail to log in
            ModelController.initializeInstance("jdbc:oracle:thin:@5.19.237.145:65432:xe",
                    "system",
                    "1234");
            ModelController instance = ModelController.getInstance();

            //TODO make nullable ints (like in base)
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
