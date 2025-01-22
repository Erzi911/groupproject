package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class appointmentDAO {
    private Connection connection;

    public appointmentDAO(Connection connection) {
        this.connection = connection;
    }

    public void addAppointment(Appointment appointment) throws SQLException {
        String query = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, notes) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, appointment.getPatientId());
        stmt.setInt(2, appointment.getDoctorId());
        stmt.setTimestamp(3, Timestamp.valueOf(appointment.getAppointmentDate()));
        stmt.setString(4, appointment.getNotes());
        stmt.executeUpdate();
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setId(rs.getInt("id"));
            appointment.setPatientId(rs.getInt("patient_id"));
            appointment.setDoctorId(rs.getInt("doctor_id"));
            appointment.setAppointmentDate(rs.getTimestamp("appointment_date").toLocalDateTime());
            appointment.setNotes(rs.getString("notes"));
            appointments.add(appointment);
        }
        return appointments;
    }

    public Appointment getAppointmentById(int id) throws SQLException {
        String query = "SELECT * FROM appointments WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setId(rs.getInt("id"));
            appointment.setPatientId(rs.getInt("patient_id"));
            appointment.setDoctorId(rs.getInt("doctor_id"));
            appointment.setAppointmentDate(rs.getTimestamp("appointment_date").toLocalDateTime());
            appointment.setNotes(rs.getString("notes"));
            return appointment;
        }
        return null;
    }

    public void updateAppointment(Appointment appointment) throws SQLException {
        String query = "UPDATE appointments SET patient_id = ?, doctor_id = ?, appointment_date = ?, notes = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, appointment.getPatientId());
        stmt.setInt(2, appointment.getDoctorId());
        stmt.setTimestamp(3, Timestamp.valueOf(appointment.getAppointmentDate()));
        stmt.setString(4, appointment.getNotes());
        stmt.setInt(5, appointment.getId());
        stmt.executeUpdate();
    }

    public void deleteAppointment(int id) throws SQLException {
        String query = "DELETE FROM appointments WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
