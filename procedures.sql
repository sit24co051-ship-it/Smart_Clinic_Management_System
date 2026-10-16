
USE smart_clinic_db;

DELIMITER //

CREATE PROCEDURE GetDailyAppointmentReportByDoctor(
    IN p_doctor_id BIGINT,
    IN p_date DATE
)
BEGIN
    SELECT
        a.appointment_datetime AS appointment_time,
        a.status AS status,
        p.full_name AS patient_name,
        p.phone AS patient_phone
    FROM appointments a
    INNER JOIN patients p
        ON a.patient_id = p.patient_id
    WHERE a.doctor_id = p_doctor_id
      AND DATE(a.appointment_datetime) = p_date
    ORDER BY a.appointment_datetime;
END //

CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(
    IN p_month INT,
    IN p_year INT
)
BEGIN
    SELECT
        d.doctor_id,
        d.full_name AS doctor_name,
        COUNT(DISTINCT a.patient_id) AS total_patients
    FROM doctors d
    INNER JOIN appointments a
        ON d.doctor_id = a.doctor_id
    WHERE MONTH(a.appointment_datetime) = p_month
      AND YEAR(a.appointment_datetime) = p_year
    GROUP BY d.doctor_id, d.full_name
    ORDER BY total_patients DESC
    LIMIT 1;
END //

CREATE PROCEDURE GetDoctorWithMostPatientsByYear(
    IN p_year INT
)
BEGIN
    SELECT
        d.doctor_id,
        d.full_name AS doctor_name,
        COUNT(DISTINCT a.patient_id) AS total_patients
    FROM doctors d
    INNER JOIN appointments a
        ON d.doctor_id = a.doctor_id
    WHERE YEAR(a.appointment_datetime) = p_year
    GROUP BY d.doctor_id, d.full_name
    ORDER BY total_patients DESC
    LIMIT 1;
END //

DELIMITER ;
