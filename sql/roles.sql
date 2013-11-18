--ADMINS--
drop user insurance_admin;
drop role insurance_admins;

create user insurance_admin identified by 4321;
create role insurance_admins;

grant insurance_admins to insurance_admin;

grant all privileges to insurance_admins with admin option;

alter user insurance_admin default role insurance_admins;

--MANAGERS--
drop user insurance_manager;
drop role insurance_managers;

create user insurance_manager identified by 4321;
create role insurance_managers;

grant insurance_managers to insurance_manager;

grant select,
      insert,
      update,
      delete
      on AGENTS to insurance_managers;
grant select,
      insert,
      update,
      delete
      on Attribute_types to insurance_managers;
grant select,
      insert,
      update,
      delete
      on companies to insurance_managers;
grant select,
      insert,
      update,
      delete
      on companies_by_insurance_type to insurance_managers;
grant select,
      insert,
      update,
      delete
      on insurance_attributes to insurance_managers;
grant select,
      insert,
      update,
      delete
      on Insurance_types to insurance_managers;
grant select,
      insert,
      update,
      delete
      on insurances to insurance_managers;
grant select,
      insert,
      update,
      delete
      on legal_persons to insurance_managers;
grant select,
      insert,
      update,
      delete
      on natural_persons to insurance_managers;
grant select on USERS_AND_USERDATA to insurance_managers;
grant create session to insurance_managers;


alter user insurance_manager default role insurance_managers;
--AGENTS--
drop user insurance_agent;
drop role insurance_agents;

create user insurance_agent identified by 4321;
create role insurance_agents;

grant insurance_agents to insurance_agent;

grant select,
      insert,
      update,
      delete 
      on LEGAL_PERSONS to insurance_agents;
grant select,
      insert,
      update,
      delete 
      on NATURAL_PERSONS to insurance_agents;
grant select,
      insert,
      update,
      delete 
      on INSURANCES to insurance_agents;
grant select,
      insert,
      update,
      delete
      on insurance_attributes to insurance_agents;
grant select on AGENTS to insurance_agents;
grant select on Attribute_types to insurance_agents;
grant select on companies to insurance_agents;
grant select on companies_by_insurance_type to insurance_agents;
grant select on Insurance_types to insurance_agents;
grant select on insurances to insurance_agents;
grant select on legal_persons to insurance_agents;
grant select on natural_persons to insurance_agents;
grant select on USERS_AND_USERDATA to insurance_agents;
grant create session to insurance_agents;
      
alter user insurance_agent default role insurance_agents;
--LEGALS--
drop user insurance_legal;
drop role insurance_legals;

create user insurance_legal identified by 4321;
create role insurance_legals;

grant insurance_legals to insurance_legal;

grant select on LEGAL_PERSONS to insurance_legals;
grant select on INSURANCES to insurance_legals;
grant select on AGENTS to insurance_legals;
grant select on Attribute_types to insurance_legals;
grant select on companies to insurance_legals;
grant select on companies_by_insurance_type to insurance_legals;
grant select on Insurance_types to insurance_legals;
grant select on insurances to insurance_legals;
grant select on USERS_AND_USERDATA to insurance_legals;
grant create session to insurance_legals;

alter user insurance_legal default role insurance_legals;

--NATURALS--
drop user insurance_natural;
drop role insurance_naturals;

create user insurance_natural identified by 4321;
create role insurance_naturals;

grant insurance_naturals to insurance_natural;

grant select on NATURAL_PERSONS to insurance_naturals;
grant select on INSURANCES to insurance_naturals;
grant select on AGENTS to insurance_naturals;
grant select on Attribute_types to insurance_naturals;
grant select on companies to insurance_naturals;
grant select on companies_by_insurance_type to insurance_naturals;
grant select on Insurance_types to insurance_naturals;
grant select on insurances to insurance_naturals;
grant select on USERS_AND_USERDATA to insurance_naturals;
grant create session to insurance_naturals;

alter user insurance_natural default role insurance_naturals;
