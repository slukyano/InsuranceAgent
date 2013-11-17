INSERT INTO agents (FirstName,SecondName,LastName,HiringDate, QuitDate) Values ('Сидор', 'Сидорович','Сидоров', to_date('18/10/2008','dd/mm/yyyy'), to_date('18/10/2011','dd/mm/yyyy'));
INSERT INTO agents (FirstName,SecondName,LastName,HiringDate, QuitDate) Values ('John', 'William','Smith', to_date('18/10/2008','dd/mm/yyyy'), to_date('18/10/2011','dd/mm/yyyy'));

INSERT INTO companies (CompanyName) Values ('Белтрансводстрах');
INSERT INTO companies (CompanyName) Values ('Страхование');

INSERT INTO insurance_types (InsuranceTypeName) values ('страхование от конца света');
INSERT INTO insurance_types (InsuranceTypeName) values ('страхование от огромного макаронного монстра');

INSERT INTO COMPANIES_BY_INSURANCE_TYPE (CompanyID,InsuranceTypeID) VALUES (
  (select CompanyID from companies where CompanyName='Белтрансводстрах'),
  (select InsuranceTypeID from INSURANCE_TYPES where InsuranceTypeName='страхование от конца света')
);
INSERT INTO COMPANIES_BY_INSURANCE_TYPE (CompanyID,InsuranceTypeID) VALUES (
  (select CompanyID from companies where CompanyName='Белтрансводстрах'),
  (select InsuranceTypeID from INSURANCE_TYPES where InsuranceTypeName='страхование от огромного макаронного монстра')
);
INSERT INTO COMPANIES_BY_INSURANCE_TYPE (CompanyID,InsuranceTypeID) VALUES (
  (select CompanyID from companies where CompanyName='Страхование'),
  (select InsuranceTypeID from INSURANCE_TYPES where InsuranceTypeName='страхование от огромного макаронного монстра')
);

Insert Into Attribute_types (AttributeTypeName, CompanyByInsuranceTypeID) values (
  'ФИО',
  (select CompanyByInsuranceTypeID 
    from COMPANIES_BY_INSURANCE_TYPE 
    where CompanyID = (select CompanyID 
      from Companies 
      where CompanyName='Белтрансводстрах') 
    AND
    InsuranceTypeID=(select InsuranceTypeID 
      from Insurance_types 
      where InsuranceTypeName='страхование от огромного макаронного монстра'))
);
Insert Into Attribute_types (AttributeTypeName, CompanyByInsuranceTypeID) values (
  'ФИО',
  (select CompanyByInsuranceTypeID 
    from COMPANIES_BY_INSURANCE_TYPE 
    where CompanyID = (select CompanyID 
      from Companies 
      where CompanyName='Страхование') 
    AND
    InsuranceTypeID=(select InsuranceTypeID 
      from Insurance_types 
      where InsuranceTypeName='страхование от огромного макаронного монстра'))
);
INSERT INTO legal_persons (AgentID, LegalName,Address, VATIN) Values (
  (Select AgentID from agents where LastName='Smith'),
  'Рога и Копыта',
  'Одесса',
  1111);
INSERT INTO legal_persons (AgentID, LegalName, Address, VATIN) Values (
  (Select AgentID from agents where LastName='Сидоров'),
  'Шарашкина контора',
  'СПБ',
  22222);

INSERT INTO natural_persons (AgentID,LastName,FirstName, SecondName,DateOfBirth) values (
  (Select AgentID from agents where LastName='Smith'),
  'иванов',
  'иван', 
  'Иваныч', 
  to_date('18/10/1992','dd/mm/yyyy'));
INSERT INTO natural_persons (AgentID,LastName,FirstName, SecondName,DateOfBirth) values (
  (Select AgentID from agents where LastName='Сидоров'),
  'Петров',
  'Петр', 
  'Петрович', 
  to_date('12/11/1990','dd/mm/yyyy'));
  
INSERT INTO RATE_TYPES (RateTypeName, CompanyByInsuranceTypeID) Values (
  'скидка', 
  (Select CompanyByInsuranceTypeID 
    from Companies_by_Insurance_type 
    where CompanyID=(Select CompanyID 
      from Companies 
      where CompanyName='Белтрансводстрах') 
    AND 
    InsuranceTypeID=(select InsuranceTypeID 
      from Insurance_Types 
      where InsuranceTypeName='страхование от огромного макаронного монстра'))
);
INSERT INTO RATE_TYPES (RateTypeName, CompanyByInsuranceTypeID) Values (
  'коэффициент', 
  (Select CompanyByInsuranceTypeID 
    from Companies_by_Insurance_type 
    where CompanyID=(Select CompanyID 
      from Companies 
      where CompanyName='Белтрансводстрах') 
    AND 
    InsuranceTypeID=(select InsuranceTypeID 
      from Insurance_Types 
      where InsuranceTypeName='страхование от огромного макаронного монстра'))
);
INSERT INTO RATE_TYPES (RateTypeName, CompanyByInsuranceTypeID) Values (
  'скидка', 
  (Select CompanyByInsuranceTypeID 
    from Companies_by_Insurance_type 
    where CompanyID=(Select CompanyID 
      from Companies 
      where CompanyName='Страхование') 
    AND 
    InsuranceTypeID=(select InsuranceTypeID 
      from Insurance_Types 
      where InsuranceTypeName='страхование от огромного макаронного монстра'))
);

INSERT INTO INSURANCES (ClientID,ClientType, CompanyByInsuranceTypeID, AgentID, BaseValue) values(
  (select NaturalPersonID from Natural_persons where LastName='иванов'),
  'NATURAL',
  (SELECT CompanyByInsuranceTypeID
    FROM COMPANIES_BY_INSURANCE_TYPE
    WHERE
     CompanyID = (SELECT CompanyID
        FROM COMPANIES
        WHERE CompanyName = 'Белтрансводстрах') AND
    InsuranceTypeID = (SELECT InsuranceTypeID
      FROM INSURANCE_TYPES
      WHERE InsuranceTypeName = 'страхование от конца света')),
  (Select AgentID from Agents where LastName='Сидоров'),
  1000);
INSERT INTO INSURANCES (ClientID,ClientType, CompanyByInsuranceTypeID, AgentID, BaseValue) values(
  (select NaturalPersonID from Natural_persons where LastName='Петров'),
  'NATURAL',
  (SELECT CompanyByInsuranceTypeID
    FROM COMPANIES_BY_INSURANCE_TYPE
    WHERE
     CompanyID = (SELECT CompanyID
        FROM COMPANIES
        WHERE CompanyName = 'Белтрансводстрах') AND
    InsuranceTypeID = (SELECT InsuranceTypeID
      FROM INSURANCE_TYPES
      WHERE InsuranceTypeName = 'страхование от огромного макаронного монстра')),
  (Select AgentID from Agents where LastName='Сидоров'),
  1000);
INSERT INTO INSURANCES (ClientID,ClientType, CompanyByInsuranceTypeID, AgentID, BaseValue) values(
  (select LegalPersonID from Legal_persons where LegalName='Рога и Копыта'),
  'LEGAL',
  (SELECT CompanyByInsuranceTypeID
    FROM COMPANIES_BY_INSURANCE_TYPE
    WHERE
     CompanyID = (SELECT CompanyID
        FROM COMPANIES
        WHERE CompanyName = 'Страхование') AND
    InsuranceTypeID = (SELECT InsuranceTypeID
      FROM INSURANCE_TYPES
      WHERE InsuranceTypeName = 'страхование от огромного макаронного монстра')),
  (Select AgentID from Agents where LastName='Smith'),
  1000);
  
  INSERT INTO INSURANCE_ATTRIBUTES (AttributeTypeID,AttributeValue,InsuranceID) values (
    (select AttributeTypeID 
      from Attribute_types 
      where AttributeTypeName='ФИО' 
    and
    CompanyByInsuranceTypeID=(SELECT CompanyByInsuranceTypeID
        FROM COMPANIES_BY_INSURANCE_TYPE
        WHERE
          CompanyID = (SELECT CompanyID
             FROM COMPANIES
             WHERE CompanyName = 'Страхование') AND
          InsuranceTypeID = (SELECT InsuranceTypeID
              FROM INSURANCE_TYPES
              WHERE InsuranceTypeName = 'страхование от огромного макаронного монстра'))),
    'Петров Петр Петрович',
    (Select InsuranceID 
      from INSURANCES 
      where ClientID=(select LegalPersonID from Legal_Persons
        where LegalName='Рога и Копыта')
      AND
      CompanyByInsuranceTypeID= (SELECT CompanyByInsuranceTypeID
        FROM COMPANIES_BY_INSURANCE_TYPE
        WHERE
          CompanyID = (SELECT CompanyID
             FROM COMPANIES
             WHERE CompanyName = 'Страхование') AND
          InsuranceTypeID = (SELECT InsuranceTypeID
              FROM INSURANCE_TYPES
              WHERE InsuranceTypeName = 'страхование от огромного макаронного монстра'))));

INSERT INTO INSURANCE_RATES (RateTypeID, RateValue , InsuranceID) values (
  (select RateTypeID 
    from Rate_types 
    where RateTypeName='скидка' 
  AND  CompanyByInsuranceTypeID=(SELECT CompanyByInsuranceTypeID
    FROM COMPANIES_BY_INSURANCE_TYPE
    WHERE CompanyID = (SELECT CompanyID
      FROM COMPANIES
        WHERE CompanyName = 'Страхование') 
        AND
        InsuranceTypeID = (SELECT InsuranceTypeID
          FROM INSURANCE_TYPES
          WHERE InsuranceTypeName = 'страхование от огромного макаронного монстра'))),
  0.5,
  (Select InsuranceID 
      from INSURANCES 
      where ClientID=(select LegalPersonID from Legal_Persons
        where LegalName='Рога и Копыта') AND
      CompanyByInsuranceTypeID= (SELECT CompanyByInsuranceTypeID
        FROM COMPANIES_BY_INSURANCE_TYPE
        WHERE
          CompanyID = (SELECT CompanyID
             FROM COMPANIES
             WHERE CompanyName = 'Страхование') AND
          InsuranceTypeID = (SELECT InsuranceTypeID
              FROM INSURANCE_TYPES
              WHERE InsuranceTypeName = 'страхование от огромного макаронного монстра'))));
  
  
    
--INSERT INTO 
--drop trigger check_natural_person;
--drop trigger check_legal_person;
