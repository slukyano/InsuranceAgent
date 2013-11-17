drop user insurance_admin;
drop role insurance_admins;


create user insurance_admin identified by 4321;
create role insurance_admins;

grant insurance_admins to insurance_admin;
grant all privileges to insurance_admins with admin option;
alter user insurance_admin default role insurance_admins;

drop user insurance_manager;
drop role insurance_managers;

create user insurance_manager identified by 4321;
create role insurance_managers;

grant insurance_managers to insurance_manager;
grant select any table,
      insert any table,
      update any table,
      delete any table 
      to insurance_managers;
alter user insurance_manager default role insurance_managers;

drop user insurance_agent;
drop role insurance_agents;

create user insurance_agent identified by 4321;
create role insurance_agents;

grant insurance_agents to insurance_agent;

grant select,
      insert,
      update,
      delete 
      on LEGAL_PERSONS 
      to insurance_agents;
grant select,
      insert,
      update,
      delete 
      on NATURAL_PERSONS 
      to insurance_agents;
grant select,
      insert,
      update,
      delete 
      on INSURANCES to insurance_agents;
grant select on USERS_AND_USERDATA to insurance_agents;
      
alter user insurance_agent default role insurance_agents;

grant select on USERS_AND_USERDATA to insurance_naturals;
grant select on USERS_AND_USERDATA to insurance_legals; 
grant select on USERS_AND_USERDATA to insurance_agents;
grant select on USERS_AND_USERDATA to insurance_managers;
grant select on USERS_AND_USERDATA to insurance_admins;
