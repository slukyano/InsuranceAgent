CREATE OR REPLACE FUNCTION compute_payment
	(insurance_id IN INTEGER)
	RETURN FLOAT
IS
	payment FLOAT;
	temp_rate FLOAT;
	CURSOR rates
	IS
		SELECT RateValue
		FROM INSURANCE_RATES
		WHERE InsuranceID = insurance_id;
BEGIN
	SELECT BaseValue
		INTO payment
		FROM INSURANCES
		WHERE InsuranceID = insurance_id;
	
	OPEN rates;
	LOOP 
	fetch rates into temp_rate;
	EXIT WHEN rates%NOTFOUND;
	payment := payment * temp_rate;
	END LOOP;
	CLOSE rates;

RETURN payment;
END;