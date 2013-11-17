CREATE SEQUENCE insurance_rates_s
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE;
/

CREATE OR REPLACE TRIGGER insurance_rates_id
before insert on INSURANCE_RATES
for each row
begin
  if :new.RateID is null then
    select insurance_rates_s.nextval into :new.RateID from dual;
  end if;
end insurance_rates_id;
/

CREATE SEQUENCE rate_type_s
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE;
/

CREATE OR REPLACE TRIGGER rate_type_id
before insert on RATE_TYPES
for each row
begin
  if :new.RateTypeID is null then
    select rate_type_s.nextval into :new.RateTypeID from dual;
  end if;
end rate_type_id;
/


CREATE SEQUENCE natural_persons_s
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE;
/

CREATE OR REPLACE TRIGGER natural_person_id
before insert on NATURAL_PERSONS
for each row
begin
  if :new.NaturalPersonID is null then
    select natural_persons_s.nextval into :new.NaturalPersonID from dual;
  end if;
end natural_person_id;
/

CREATE SEQUENCE legal_persons_s
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE;
/

CREATE OR REPLACE TRIGGER legal_person_id
before insert on LEGAL_PERSONS
for each row
begin
  if :new.LegalPersonID is null then
    select legal_persons_s.nextval into :new.LegalPersonID from dual;
  end if;
end legal_person_id;
/


CREATE SEQUENCE agents_s
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE;



CREATE OR REPLACE TRIGGER agent_id
before insert on AGENTS
for each row
begin
  if :new.AgentID is null then
    select agents_s.nextval into :new.AgentID from dual;
  end if;
end agent_id;
/

CREATE SEQUENCE insurances_s
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE;
/

CREATE OR REPLACE TRIGGER insurance_id
before insert on INSURANCES
for each row
begin
  if :new.InsuranceID is null then
    select insurances_s.nextval into :new.InsuranceID from dual;
  end if;
end insurance_id;
/


CREATE SEQUENCE companies_s
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE;
/

CREATE OR REPLACE TRIGGER company_id
before insert on COMPANIES
for each row
begin
  if :new.CompanyID is null then
    select companies_s.nextval into :new.CompanyID from dual;
  end if;
end company_id;
/


CREATE SEQUENCE insurance_types_s
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE;
/

CREATE OR REPLACE TRIGGER insurance_type_id
before insert on INSURANCE_TYPES
for each row
begin
  if :new.InsuranceTypeID is null then
    select insurance_types_s.nextval into :new.InsuranceTypeID from dual;
  end if;
end insurance_type_id;
/

CREATE SEQUENCE companies_by_insurance_types_s
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE;
/

CREATE OR REPLACE TRIGGER company_by_insurance_type_id
before insert on COMPANIES_BY_INSURANCE_TYPE
for each row
begin
  if :new.CompanyByInsuranceTypeID is null then
    select companies_by_insurance_types_s.nextval into :new.CompanyByInsuranceTypeID from dual;
  end if;
end company_by_insurance_type_id;
/

CREATE SEQUENCE insurance_attributes_s
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE;
/

CREATE OR REPLACE TRIGGER insurance_attribute_id
before insert on INSURANCE_ATTRIBUTES
for each row
begin
  if :new.AttributeID is null then
    select insurance_attributes_s.nextval into :new.AttributeID from dual;
  end if;
end insurance_attribute_id;
/

CREATE SEQUENCE attribute_types_s
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE;

CREATE OR REPLACE TRIGGER attribute_type_id
before insert on ATTRIBUTE_TYPES
for each row
begin
  if :new.AttributeTypeID is null then
    select attribute_types_s.nextval into :new.AttributeTypeID from dual;
  end if;
end attribute_type_id;
/