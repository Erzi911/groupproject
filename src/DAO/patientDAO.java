package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class patientDAO {
    private Connection connection;

    public patientDAO(Connection connection) {
        this.connection = connection;
    }

    public void addPatient(Patient patient) throws SQLException {
        String query = "INSERT INTO patients (iin, name, date_of_birth, gender, nationality, citizenship, address, blood_group, rhesus_factor, contact_info) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, patient.getIin());
        stmt.setString(2, patient.getName());
        stmt.setDate(3, Date.valueOf(patient.getDateOfBirth()));
        stmt.setString(4, patient.getGender());
        stmt.setString(5, patient.getNationality());
        stmt.setString(6, patient.getCitizenship());
        stmt.setString(7, patient.getAddress());
        stmt.setString(8, patient.getBloodGroup());
        stmt.setString(9, patient.getRhesusFactor());
        stmt.setString(10, patient.getContactInfo());
        stmt.executeUpdate();
    }

    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setIin(rs.getString("iin"));
            patient.setName(rs.getString("name"));
            patient.setDateOfBirth(rs.getString("date_of_birth"));
            patient.setGender(rs.getString("gender"));
            patient.setNationality(rs.getString("nationality"));
            patient.setCitizenship(rs.getString("citizenship"));
            patient.setAddress(rs.getString("address"));
            patient.setBloodGroup(rs.getString("blood_group"));
            patient.setRhesusFactor(rs.getString("rhesus_factor"));
            patient.setContactInfo(rs.getString("contact_info"));
            patients.add(patient);
        }
        return patients;
    }

    public Patient getPatientById(int id) throws SQLException {
        String query = "SELECT * FROM patients WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setIin(rs.getString("iin"));
            patient.setName(rs.getString("name"));
            patient.setDateOfBirth(rs.getString("date_of_birth"));
            patient.setGender(rs.getString("gender"));
            patient.setNationality(rs.getString("nationality"));
            patient.setCitizenship(rs.getString("citizenship"));
            patient.setAddress(rs.getString("address"));
            patient.setBloodGroup(rs.getString("blood_group"));
            patient.setRhesusFactor(rs.getString("rhesus_factor"));
            patient.setContactInfo(rs.getString("contact_info"));
            return patient;
        }
        return null;
    }

    public void updatePatient(Patient patient) throws SQLException {
        String query = "UPDATE patients SET iin = ?, name = ?, date_of_birth = ?, gender = ?, nationality = ?, citizenship = ?, address = ?, blood_group = ?, rhesus_factor = ?, contact_info = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, patient.getIin());
        stmt.setString(2, patient.getName());
        stmt.setDate(3, Date.valueOf(patient.getDateOfBirth()));
        stmt.setString(4, patient.getGender());
        stmt.setString(5, patient.getNationality());
        stmt.setString(6, patient.getCitizenship());
        stmt.setString(7, patient.getAddress());
        stmt.setString(8, patient.getBloodGroup());
        stmt.setString(9, patient.getRhesusFactor());
        stmt.setString(10, patient.getContactInfo());
        stmt.setInt(11, patient.getId());
        stmt.executeUpdate();
    }

    public void deletePatient(int id) throws SQLException {
        String query = "DELETE FROM patients WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
