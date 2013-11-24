package model;

import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import model.insurances.Insurance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum UserType {
    ADMIN {
        @Override
        public List<NaturalPerson> getNaturalPersons() throws SQLException {
            return ModelController.getInstance().getNaturalPersons();
        }

        @Override
        public List<LegalPerson> getLegalPersons() throws SQLException {
            return ModelController.getInstance().getLegalPersons();
        }

        @Override
        public List<Insurance> getInsurances() throws SQLException {
            return ModelController.getInstance().getInsurances();
        }
    },
    MANAGER {
        @Override
        public List<NaturalPerson> getNaturalPersons() throws SQLException {
            return ModelController.getInstance().getNaturalPersons(
                    (Agent)ModelController.getInstance().getUserObject());
        }

        @Override
        public List<LegalPerson> getLegalPersons() throws SQLException {
            return ModelController.getInstance().getLegalPersons(
                    (Agent)ModelController.getInstance().getUserObject());
        }

        @Override
        public List<Insurance> getInsurances() throws SQLException {
            return ModelController.getInstance().getInsurances(
                    (Agent)ModelController.getInstance().getUserObject());
        }
    },
    AGENT {
        @Override
        public List<NaturalPerson> getNaturalPersons() throws SQLException {
            return ModelController.getInstance().getNaturalPersons(
                    (Agent)ModelController.getInstance().getUserObject());
        }

        @Override
        public List<LegalPerson> getLegalPersons() throws SQLException {
            return ModelController.getInstance().getLegalPersons(
                    (Agent)ModelController.getInstance().getUserObject());
        }

        @Override
        public List<Insurance> getInsurances() throws SQLException {
            return ModelController.getInstance().getInsurances(
                    (Agent)ModelController.getInstance().getUserObject());
        }
    },
    NATURAL {
        @Override
        public List<NaturalPerson> getNaturalPersons() throws SQLException {
            ArrayList<NaturalPerson> list = new ArrayList<NaturalPerson>();
            list.add((NaturalPerson)ModelController.getInstance().getUserObject());
            return list;
        }

        @Override
        public List<LegalPerson> getLegalPersons() throws SQLException {
            return null;
        }

        @Override
        public List<Insurance> getInsurances() throws SQLException {
            return ModelController.getInstance().getInsurances(
                    (NaturalPerson)ModelController.getInstance().getUserObject());
        }
    },
    LEGAL {
        @Override
        public List<NaturalPerson> getNaturalPersons() throws SQLException {
            return null;
        }

        @Override
        public List<LegalPerson> getLegalPersons() throws SQLException {
            ArrayList<LegalPerson> list = new ArrayList<LegalPerson>();
            list.add((LegalPerson)ModelController.getInstance().getUserObject());
            return list;
        }

        @Override
        public List<Insurance> getInsurances() throws SQLException {
            return ModelController.getInstance().getInsurances(
                    (LegalPerson)ModelController.getInstance().getUserObject());
        }
    };

    public static UserType fromRoleName(String roleName) {
        if (roleName.compareToIgnoreCase("insurance_admins") == 0)
            return UserType.ADMIN;
        else if (roleName.compareToIgnoreCase("insurance_managers") == 0)
            return UserType.MANAGER;
        else if (roleName.compareToIgnoreCase("insurance_agents") == 0)
            return UserType.AGENT;
        else if (roleName.compareToIgnoreCase("insurance_legals") == 0)
            return UserType.LEGAL;
        else if (roleName.compareToIgnoreCase("insurance_naturals") == 0)
            return UserType.NATURAL;
            //TODO do we need unauthorized?
        else
            return null;
    }

    public abstract List<NaturalPerson> getNaturalPersons() throws SQLException;
    public abstract List<LegalPerson> getLegalPersons() throws SQLException;
    public abstract List<Insurance> getInsurances() throws SQLException;
}
