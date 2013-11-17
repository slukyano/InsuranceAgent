INSERT INTO agents (FirstName,SecondName,LastName,HiringDate, QuitDate) Values ('�����', '���������','�������', to_date('18/10/2008','dd/mm/yyyy'), to_date('18/10/2011','dd/mm/yyyy'));
INSERT INTO agents (FirstName,SecondName,LastName,HiringDate, QuitDate) Values ('John', 'William','Smith', to_date('18/10/2008','dd/mm/yyyy'), to_date('18/10/2011','dd/mm/yyyy'));

INSERT INTO companies (CompanyName) Values ('����������������');
INSERT INTO companies (CompanyName) Values ('�����������');

INSERT INTO insurance_types (InsuranceTypeName) values ('����������� �� ����� �����');
INSERT INTO insurance_types (InsuranceTypeName) values ('����������� �� ��������� ����������� �������');

INSERT INTO COMPANIES_BY_INSURANCE_TYPE (CompanyID,InsuranceTypeID) VALUES (
  (select CompanyID from companies where CompanyName='����������������'),
  (select InsuranceTypeID from INSURANCE_TYPES where InsuranceTypeName='����������� �� ����� �����')
);
INSERT INTO COMPANIES_BY_INSURANCE_TYPE (CompanyID,InsuranceTypeID) VALUES (
  (select CompanyID from companies where CompanyName='����������������'),
  (select InsuranceTypeID from INSURANCE_TYPES where InsuranceTypeName='����������� �� ��������� ����������� �������')
);
INSERT INTO COMPANIES_BY_INSURANCE_TYPE (CompanyID,InsuranceTypeID) VALUES (
  (select CompanyID from companies where CompanyName='�����������'),
  (select InsuranceTypeID from INSURANCE_TYPES where InsuranceTypeName='����������� �� ��������� ����������� �������')
);

Insert Into Attribute_types (AttributeTypeName, CompanyByInsuranceTypeID) values (
  '���',
  (select CompanyByInsuranceTypeID 
    from COMPANIES_BY_INSURANCE_TYPE 
    where CompanyID = (select CompanyID 
      from Companies 
      where CompanyName='����������������') 
    AND
    InsuranceTypeID=(select InsuranceTypeID 
      from Insurance_types 
      where InsuranceTypeName='����������� �� ��������� ����������� �������'))
);
Insert Into Attribute_types (AttributeTypeName, CompanyByInsuranceTypeID) values (
  '���',
  (select CompanyByInsuranceTypeID 
    from COMPANIES_BY_INSURANCE_TYPE 
    where CompanyID = (select CompanyID 
      from Companies 
      where CompanyName='�����������') 
    AND
    InsuranceTypeID=(select InsuranceTypeID 
      from Insurance_types 
      where InsuranceTypeName='����������� �� ��������� ����������� �������'))
);
INSERT INTO legal_persons (AgentID, LegalName,Address, VATIN) Values (
  (Select AgentID from agents where LastName='Smith'),
  '���� � ������',
  '������',
  1111);
INSERT INTO legal_persons (AgentID, LegalName, Address, VATIN) Values (
  (Select AgentID from agents where LastName='�������'),
  '��������� �������',
  '���',
  22222);

INSERT INTO natural_persons (AgentID,LastName,FirstName, SecondName,DateOfBirth) values (
  (Select AgentID from agents where LastName='Smith'),
  '������',
  '����', 
  '������', 
  to_date('18/10/1992','dd/mm/yyyy'));
INSERT INTO natural_persons (AgentID,LastName,FirstName, SecondName,DateOfBirth) values (
  (Select AgentID from agents where LastName='�������'),
  '������',
  '����', 
  '��������', 
  to_date('12/11/1990','dd/mm/yyyy'));
  
INSERT INTO RATE_TYPES (RateTypeName, CompanyByInsuranceTypeID) Values (
  '������', 
  (Select CompanyByInsuranceTypeID 
    from Companies_by_Insurance_type 
    where CompanyID=(Select CompanyID 
      from Companies 
      where CompanyName='����������������') 
    AND 
    InsuranceTypeID=(select InsuranceTypeID 
      from Insurance_Types 
      where InsuranceTypeName='����������� �� ��������� ����������� �������'))
);
INSERT INTO RATE_TYPES (RateTypeName, CompanyByInsuranceTypeID) Values (
  '�����������', 
  (Select CompanyByInsuranceTypeID 
    from Companies_by_Insurance_type 
    where CompanyID=(Select CompanyID 
      from Companies 
      where CompanyName='����������������') 
    AND 
    InsuranceTypeID=(select InsuranceTypeID 
      from Insurance_Types 
      where InsuranceTypeName='����������� �� ��������� ����������� �������'))
);
INSERT INTO RATE_TYPES (RateTypeName, CompanyByInsuranceTypeID) Values (
  '������', 
  (Select CompanyByInsuranceTypeID 
    from Companies_by_Insurance_type 
    where CompanyID=(Select CompanyID 
      from Companies 
      where CompanyName='�����������') 
    AND 
    InsuranceTypeID=(select InsuranceTypeID 
      from Insurance_Types 
      where InsuranceTypeName='����������� �� ��������� ����������� �������'))
);

INSERT INTO INSURANCES (ClientID,ClientType, CompanyByInsuranceTypeID, AgentID, BaseValue) values(
  (select NaturalPersonID from Natural_persons where LastName='������'),
  'NATURAL',
  (SELECT CompanyByInsuranceTypeID
    FROM COMPANIES_BY_INSURANCE_TYPE
    WHERE
     CompanyID = (SELECT CompanyID
        FROM COMPANIES
        WHERE CompanyName = '����������������') AND
    InsuranceTypeID = (SELECT InsuranceTypeID
      FROM INSURANCE_TYPES
      WHERE InsuranceTypeName = '����������� �� ����� �����')),
  (Select AgentID from Agents where LastName='�������'),
  1000);
INSERT INTO INSURANCES (ClientID,ClientType, CompanyByInsuranceTypeID, AgentID, BaseValue) values(
  (select NaturalPersonID from Natural_persons where LastName='������'),
  'NATURAL',
  (SELECT CompanyByInsuranceTypeID
    FROM COMPANIES_BY_INSURANCE_TYPE
    WHERE
     CompanyID = (SELECT CompanyID
        FROM COMPANIES
        WHERE CompanyName = '����������������') AND
    InsuranceTypeID = (SELECT InsuranceTypeID
      FROM INSURANCE_TYPES
      WHERE InsuranceTypeName = '����������� �� ��������� ����������� �������')),
  (Select AgentID from Agents where LastName='�������'),
  1000);
INSERT INTO INSURANCES (ClientID,ClientType, CompanyByInsuranceTypeID, AgentID, BaseValue) values(
  (select LegalPersonID from Legal_persons where LegalName='���� � ������'),
  'LEGAL',
  (SELECT CompanyByInsuranceTypeID
    FROM COMPANIES_BY_INSURANCE_TYPE
    WHERE
     CompanyID = (SELECT CompanyID
        FROM COMPANIES
        WHERE CompanyName = '�����������') AND
    InsuranceTypeID = (SELECT InsuranceTypeID
      FROM INSURANCE_TYPES
      WHERE InsuranceTypeName = '����������� �� ��������� ����������� �������')),
  (Select AgentID from Agents where LastName='Smith'),
  1000);
  
  INSERT INTO INSURANCE_ATTRIBUTES (AttributeTypeID,AttributeValue,InsuranceID) values (
    (select AttributeTypeID 
      from Attribute_types 
      where AttributeTypeName='���' 
    and
    CompanyByInsuranceTypeID=(SELECT CompanyByInsuranceTypeID
        FROM COMPANIES_BY_INSURANCE_TYPE
        WHERE
          CompanyID = (SELECT CompanyID
             FROM COMPANIES
             WHERE CompanyName = '�����������') AND
          InsuranceTypeID = (SELECT InsuranceTypeID
              FROM INSURANCE_TYPES
              WHERE InsuranceTypeName = '����������� �� ��������� ����������� �������'))),
    '������ ���� ��������',
    (Select InsuranceID 
      from INSURANCES 
      where ClientID=(select LegalPersonID from Legal_Persons
        where LegalName='���� � ������')
      AND
      CompanyByInsuranceTypeID= (SELECT CompanyByInsuranceTypeID
        FROM COMPANIES_BY_INSURANCE_TYPE
        WHERE
          CompanyID = (SELECT CompanyID
             FROM COMPANIES
             WHERE CompanyName = '�����������') AND
          InsuranceTypeID = (SELECT InsuranceTypeID
              FROM INSURANCE_TYPES
              WHERE InsuranceTypeName = '����������� �� ��������� ����������� �������'))));

INSERT INTO INSURANCE_RATES (RateTypeID, RateValue , InsuranceID) values (
  (select RateTypeID 
    from Rate_types 
    where RateTypeName='������' 
  AND  CompanyByInsuranceTypeID=(SELECT CompanyByInsuranceTypeID
    FROM COMPANIES_BY_INSURANCE_TYPE
    WHERE CompanyID = (SELECT CompanyID
      FROM COMPANIES
        WHERE CompanyName = '�����������') 
        AND
        InsuranceTypeID = (SELECT InsuranceTypeID
          FROM INSURANCE_TYPES
          WHERE InsuranceTypeName = '����������� �� ��������� ����������� �������'))),
  0.5,
  (Select InsuranceID 
      from INSURANCES 
      where ClientID=(select LegalPersonID from Legal_Persons
        where LegalName='���� � ������') AND
      CompanyByInsuranceTypeID= (SELECT CompanyByInsuranceTypeID
        FROM COMPANIES_BY_INSURANCE_TYPE
        WHERE
          CompanyID = (SELECT CompanyID
             FROM COMPANIES
             WHERE CompanyName = '�����������') AND
          InsuranceTypeID = (SELECT InsuranceTypeID
              FROM INSURANCE_TYPES
              WHERE InsuranceTypeName = '����������� �� ��������� ����������� �������'))));
  
  
    
--INSERT INTO 
--drop trigger check_natural_person;
--drop trigger check_legal_person;
