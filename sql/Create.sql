create table AGENTS 
(
   AgentID            INTEGER              not null,
   FirstName          VARCHAR2(50)         not null,
   SecondName         VARCHAR2(50),
   LastName           VARCHAR2(50)         not null,
   HiringDate         DATE                 not null,
   QuitDate          DATE,
   constraint PK_AGENTS primary key (AgentID),
   constraint "Quit > Hiring" check (QuitDate > HiringDate),
   constraint "Unique name and date" unique (FirstName,SecondName,LastName,HiringDate)
);

create table LEGAL_PERSONS 
(
   LegalPersonID      INTEGER              not null,
   LegalName          VARCHAR2(200)         not null,
   Address            VARCHAR2(200),
   VATIN              VARCHAR2(50),
   AgentID            INTEGER              not null,
   constraint PK_LEGAL_PERSONS primary key (LegalPersonID),
   constraint FK_LEGAL_PERSONS_AGENTS foreign key (AgentID)
      references AGENTS(AgentID),
   constraint "Unique name and address" unique (LegalName)
);

create table NATURAL_PERSONS 
(
   NaturalPersonID    INTEGER              not null,
   FirstName          VARCHAR2(50)         not null,
   SecondName         VARCHAR2(50),
   LastName          VARCHAR2(50)         not null,
   DateOfBirth        DATE,
   AgentID            INTEGER              not null,
   constraint PK_NATURAL_PERSONS primary key (NaturalPersonID),
   constraint FK_NATURAL_PERSONS_AGENTS foreign key (AgentID)
      references AGENTS(AgentID),
   constraint "Unique name and birthday" unique (FirstName, SecondName, LastName, DateOfBirth)
);

create table COMPANIES 
(
   CompanyID          INTEGER              not null,
   CompanyName        VARCHAR2(200)         not null,
   ParentCompanyID    INTEGER,
   CompanyDescription VARCHAR2(50),
   constraint PK_COMPANIES primary key (CompanyID),
   constraint "Unique name" unique (CompanyName)
);

create table INSURANCE_TYPES 
(
   InsuranceTypeID    INTEGER              not null,
   InsuranceTypeName  VARCHAR2(200)         not null,
   InsuranceTypeDescription VARCHAR2(200),
   constraint PK_INSURANCE_TYPES primary key (InsuranceTypeID),
   constraint "Unique insurance type name" unique (InsuranceTypeName)
);

create table COMPANIES_BY_INSURANCE_TYPE 
(
   CompanyByInsuranceTypeID INTEGER        not null,
   CompanyID          INTEGER              not null,
   InsuranceTypeID    INTEGER              not null,
   constraint PK_COMPANIES_BY_INSURANCE_TYPE primary key (CompanyByInsuranceTypeID),
   constraint FK_COMPANIES foreign key (CompanyID)
      references COMPANIES(CompanyID),
   constraint FK_INSURANCE_TYPES foreign key (InsuranceTypeID)
      references INSURANCE_TYPES(InsuranceTypeID)
);

create table INSURANCES 
(
   InsuranceID        INTEGER              not null,
   ClientID           INTEGER              not null,
   ClientType         VARCHAR2(50)              not null,
   CompanyByInsuranceTypeID INTEGER        not null,
   AgentID            INTEGER,
--   BaseValueTypeBySource VARCHAR(50)           not null,
   BaseValue          FLOAT,
--   StaticBaseValueID  INTEGER,
   constraint PK_INSURANCES primary key (InsuranceID),
   constraint FK_COMPANIES_BY_INSURANCE_TYPE foreign key (CompanyByInsuranceTypeID)
      references COMPANIES_BY_INSURANCE_TYPE(CompanyByInsuranceTypeID),
   constraint FK_INSURANCES_AGENTS foreign key (AgentID)
      references AGENTS(AgentID)
--   constraint "Wrong TypeBySource" check (BaseValueTypeBySource in ('REGULAR','STATIC'))
);

create table ATTRIBUTE_TYPES 
(
   AttributeTypeID    INTEGER              not null,
   AttributeTypeName  VARCHAR2(200)         not null,
--   AttributeTypeBySource VARCHAR(50)       not null,
   AttributeTypeDescription VARCHAR2(200),
   CompanyByInsuranceTypeID INTEGER not null,
   constraint PK_ATTRIBUTE_TYPES primary key (AttributeTypeID),
--   constraint "Wrong TypeBySource" check (AttributeTypeBySource in ('REGULAR','STATIC')),
   constraint FK_ATTR_TYPE_COMP_INS_TYPE foreign key (CompanyByInsuranceTypeID)
      references COMPANIES_BY_INSURANCE_TYPE(CompanyByInsuranceTypeID),  
   constraint "Unique AttrName and InsType" unique (AttributeTypeName,CompanyByInsuranceTypeID)
);

/*create table STATIC_ATTRIBUTES 
(
   StaticAttributeID  INTEGER              not null,
   AttributeTypeID    INTEGER              not null,
   StaticAttributeValue VARCHAR2(50),
   StaticAttributeDescription VARCHAR2(50),
   constraint PK_STATIC_ATTRIBUTES primary key (StaticAttributeID),
   constraint FK_STAT_ATTR_ATTR_TYPES foreign key (AttributeTypeID)
      references ATTRIBUTE_TYPES(AttributeTypeID),
   constraint "Unique static attribute type and value" unique (AttributeTypeID,StaticAttributeValue)
*/

create table INSURANCE_ATTRIBUTES 
(
   AttributeID        INTEGER              not null,
   AttributeTypeID    INTEGER              not null,
   AttributeValue     VARCHAR2(50),
--   StaticAttributeID  INTEGER,
   InsuranceID        INTEGER              not null,
   constraint PK_INSURANCE_ATTRIBUTES primary key (AttributeID),
   constraint FK_ATTRIBUTE_TYPES foreign key (AttributeTypeID)
      references ATTRIBUTE_TYPES(AttributeTypeID),
--   constraint FK_STATIC_ATTRIBUTES foreign key (StaticAttributeID)
--      references STATIC_ATTRIBUTES(StaticAttributeID),
   constraint FK_INSURANCES foreign key (InsuranceID)
      references INSURANCES(InsuranceID),
   constraint "Unique ins and attr type" unique (InsuranceID, AttributeTypeID)
);

create table RATE_TYPES 
(
   RateTypeID    INTEGER              not null,
   RateTypeName  VARCHAR2(50)         not null,
--   RateTypeBySource VARCHAR(50)       not null,
   RateTypeDescription VARCHAR2(200),
   CompanyByInsuranceTypeID INTEGER not null,
   constraint PK_RATE_TYPES primary key (RateTypeID),
--   constraint "Wrong TypeBySource" check (RateTypeBySource in ('REGULAR','STATIC')),
   constraint FK_RATE_TYPE_COMP_INS_TYPE foreign key (CompanyByInsuranceTypeID)
      references COMPANIES_BY_INSURANCE_TYPE(CompanyByInsuranceTypeID),  
   constraint "Unique rate name and ins type" unique (RateTypeName,CompanyByInsuranceTypeID)
);

/*create table STATIC_RATES
(
   StaticRateID  INTEGER              not null,
   RateTypeID    INTEGER              not null,
   StaticRateValue VARCHAR2(50),
   StaticRateDescription VARCHAR2(50),
   constraint PK_STATIC_RATES primary key (StaticRateID),
   constraint FK_STATIC_RATE_TYPES foreign key (RateTypeID)
      references RATE_TYPES(RateTypeID),
   constraint "Unique static attribute type and value" unique (RateTypeID,StaticRateValue)
);
*/

create table INSURANCE_RATES
(
   RateID        INTEGER              not null,
   RateTypeID    INTEGER              not null,
   RateValue     float,
--   StaticRateID  INTEGER,
   InsuranceID        INTEGER              not null,
   constraint PK_INSURANCE_RATES primary key (RateID),
   constraint FK_RATE_TYPES foreign key (RateTypeID)
      references RATE_TYPES(RateTypeID),
--   constraint FK_STATIC_RATES foreign key (StaticRateID)
--      references STATIC_RATES(StaticRateID),
   constraint FK_RATE_INSURANCES foreign key (InsuranceID)
      references INSURANCES(InsuranceID),
   constraint "Unique ins and rate type" unique (InsuranceID, RateTypeID)
);


create table USERS_AND_USERDATA (
  userid number,
  dataid number,
  usertype varchar2(20),
  constraint PK_UAUD_userId primary key (userid),
  constraint "Wrong TypeBySource" check (usertype in ('LEGAL','NATURAL','AGENT')),  
  constraint "unique dataId and UserType" unique (dataId,usertype)
);
