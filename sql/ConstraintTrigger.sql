CREATE OR REPLACE TRIGGER check_insurance_client_id
BEFORE INSERT ON INSURANCES
FOR EACH ROW
DECLARE
	err_wrong_client EXCEPTION;
	err_wrong_client_type EXCEPTION;
	client_does_exist INTEGER;
  
BEGIN
	IF :new.ClientType = 'NATURAL' THEN
		SELECT COUNT(*) 
			INTO client_does_exist 
			FROM NATURAL_PERSONS 
			WHERE NaturalPersonID = :new.ClientID;
		IF client_does_exist = 0 THEN
			RAISE err_wrong_client;
		END IF;
	ELSIF :new.ClientType = 'LEGAL' THEN
		SELECT COUNT(*) 
			INTO client_does_exist 
			FROM LEGAL_PERSONS 
			WHERE LegalPersonID = :new.ClientID;
		IF client_does_exist = 0 THEN
			RAISE err_wrong_client;
		END IF;
	ELSE
		RAISE err_wrong_client_type;
	END IF;
	
EXCEPTION
	WHEN err_wrong_client THEN RAISE_APPLICATION_ERROR(-20998, 'Attempt to add client with wrong ID');
	WHEN err_wrong_client_type THEN RAISE_APPLICATION_ERROR(-20997, 'Attempt to add client with wrong client type');
	WHEN OTHERS THEN RAISE_APPLICATION_ERROR(-20999, 'Unknown error');
END check_insurance_client_id;