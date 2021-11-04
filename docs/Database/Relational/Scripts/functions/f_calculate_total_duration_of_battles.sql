USE `erb_hub`;

SET GLOBAL log_bin_trust_function_creators = 1;

DROP FUNCTION IF EXISTS f_calculate_total_duration_of_battles;

DELIMITER //

CREATE FUNCTION f_calculate_total_duration_of_battles()
RETURNS CHAR(8)

BEGIN
	-- DECLARATIONS
	DECLARE v_total_time CHAR(8) DEFAULT 0;
    -- ============
    
    -- EXECUTION
    SELECT cast(date_format(sec_to_time(sum(time_to_sec(str_to_date(duration, '%i:%s')))), '%H:%i:%s') as CHAR)
    INTO v_total_time FROM battles b;
    RETURN v_total_time;
    -- =========
END;//

DELIMITER ;