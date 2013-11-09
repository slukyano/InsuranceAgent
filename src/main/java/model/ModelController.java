package model;

import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import model.insurances.CompanyByInsuranceType;
import model.insurances.Insurance;
import model.insurances.InsuranceType;
import model.insurances.attributes.AttributeType;
import model.insurances.attributes.InsuranceAttribute;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

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
        String sql = "UPDATE NATURAL_PERSONS"
                        + "SET FirstName = " + naturalPerson.getFirstName()
                            +"SecondName = " + naturalPerson.getSecondName()
                            +"LastName = " + naturalPerson.getLastName()
                            +"DateOfBirth = " + naturalPerson.getDateOfBirth() //TODO:Check if valid string
                            +"AgentID = " + naturalPerson.getAgentId()
                        + "wHERE NaturalPersonID = " + naturalPerson.getClientId();
        executeSQL(sql);

    }

    public void createNaturalPerson(NaturalPerson naturalPerson)      throws SQLException {
        String sql = "INSERT INTO NATURAL_PERSONS (FirstName, SecondName, LastName, DateOfBirth, AgentID)"
                        + "VALUES (" +   naturalPerson.getFirstName()
                                     + "," +naturalPerson.getSecondName()
                                     + "," +naturalPerson.getLastName()
                                     + "," +naturalPerson.getDateOfBirth()
                                     + "," +naturalPerson.getAgentId()+")";
        executeSQL(sql);
        //TODO: should set an ID somehow
    }

    public void  deleteNaturalPerson(int ClientId)      throws SQLException {
        String sql = "DELETE FROM NATURAL_PERSONS"
                        + "WHERE NaturalPersonID = "  +  ClientId;
        executeSQL(sql);
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
        String sql = "UPDATE Legal_PERSONS"
                + "SET LegalName = " + legalPerson.getName()
                +"Address = " + legalPerson.getAddress()
                +"vatin = " + legalPerson.getVatin()
                +"AgentID = " + legalPerson.getAgentId()
                + "wHERE LegalPersonID = " + legalPerson.getClientId();
        executeSQL(sql);

    }

    public void createLegalPerson(LegalPerson legalPerson)      throws SQLException {
        String sql = "INSERT INTO Legal_PERSONS (LegalName, Address, vatin, AgentID)"
                + "VALUES (" +   legalPerson.getName()
                + "," +legalPerson.getAddress()
                + "," +legalPerson.getVatin()
                + "," +legalPerson.getAgentId()+")";
        executeSQL(sql);
        //TODO: should set an ID somehow
    }

    public void  deleteLegalPerson(int ClientId)      throws SQLException {
        String sql = "DELETE FROM Legal_PERSONS"
                + "WHERE LegalPersonID = "  +  ClientId;
        executeSQL(sql);
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
    //endregion

    //region CompanyByInsuranceType Factories
    public CompanyByInsuranceType getCompanyByInsuranceType(int companyByInsTypeId) throws SQLException {
        String sql = "SELECT companyByInsuranceTypeID, companyID, insuranceID"
                + " FROM companies_by_insurance_type"
                + " WHERE companyByInsuranceTypeID = " + companyByInsTypeId;
        return getObject(CompanyByInsuranceTypeFactory.getInstance(), sql);
    }

    public ArrayList<CompanyByInsuranceType> getCompaniesByInsuranceTypes() throws SQLException {
        String sql = "SELECT companyByInsuranceTypeID, companyID, insuranceID"
                + " FROM companies_by_insurance_type";
        return getObjects(CompanyByInsuranceTypeFactory.getInstance(), sql);
    }

    public ArrayList<CompanyByInsuranceType> getCompaniesByInsuranceTypes(Company company) throws SQLException {
        String sql = "SELECT companyByInsuranceTypeID, companyID, insuranceID"
                + " FROM companies_by_insurance_type"
                + " WHERE companyID = " + company.getCompanyId();
        return getObjects(CompanyByInsuranceTypeFactory.getInstance(), sql);
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
}
