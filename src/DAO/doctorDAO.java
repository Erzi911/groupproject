package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class doctorDAO {
    private Connection connection;

    public doctorDAO(Connection connection) {
        this.connection = connection;
    }

    public void addDoctor(Doctor doctor) throws SQLException {
        String query = "INSERT INTO doctors (full_name, specialization, work_hours, office_number, experience_years, contact_info) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, doctor.getFullName());
        stmt.setString(2, doctor.getSpecialization());
        stmt.setString(3, doctor.getWorkHours());
        stmt.setString(4, doctor.getOfficeNumber());
        stmt.setInt(5, doctor.getExperienceYears());
        stmt.setString(6, doctor.getContactInfo());
        stmt.executeUpdate();
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Doctor doctor = new Doctor();
            doctor.setId(rs.getInt("id"));
            doctor.setFullName(rs.getString("full_name"));
            doctor.setSpecialization(rs.getString("specialization"));
            doctor.setWorkHours(rs.getString("work_hours"));
            doctor.setOfficeNumber(rs.getString("office_number"));
            doctor.setExperienceYears(rs.getInt("experience_years"));
            doctor.setContactInfo(rs.getString("contact_info"));
            doctors.add(doctor);
        }
        return doctors;
    }

    public Doctor getDoctorById(int id) throws SQLException {
        String query = "SELECT * FROM doctors WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Doctor doctor = new Doctor();
            doctor.setId(rs.getInt("id"));
            doctor.setFullName(rs.getString("full_name"));
            doctor.setSpecialization(rs.getString("specialization"));
            doctor.setWorkHours(rs.getString("work_hours"));
            doctor.setOfficeNumber(rs.getString("office_number"));
            doctor.setExperienceYears(rs.getInt("experience_years"));
            doctor.setContactInfo(rs.getString("contact_info"));
            return doctor;
        }
        return null;
    }

    public void updateDoctor(Doctor doctor) throws SQLException {
        String query = "UPDATE doctors SET full_name = ?, specialization = ?, work_hours = ?, office_number = ?, experience_years = ?, contact_info = ? " +
                "WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, doctor.getFullName());
        stmt.setString(2, doctor.getSpecialization());
        stmt.setString(3, doctor.getWorkHours());
        stmt.setString(4, doctor.getOfficeNumber());
        stmt.setInt(5, doctor.getExperienceYears());
        stmt.setString(6, doctor.getContactInfo());
        stmt.setInt(7, doctor.getId());
        stmt.executeUpdate();
    }

    public void deleteDoctor(int id) throws SQLException {
        String query = "DELETE FROM doctors WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
