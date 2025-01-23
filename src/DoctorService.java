import java.sql.SQLException;
import java.util.List;
public class DoctorService {
    private final DoctorDAO doctorDAO;
    public DoctorService(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }
    public void addDoctor(Doctor doctor) throws SQLException {
        if (doctor.getFullName() == null  doctor.getFullName().isEmpty()) {
            throw new IllegalArgumentException("Doctor's name cannot be empty.");
        }
        if (doctor.getSpecialization() == null  doctor.getSpecialization().isEmpty()) {
            throw new IllegalArgumentException("Doctor's specialization cannot be empty.");
        }
        doctorDAO.addDoctor(doctor);
    }
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found in the system.");
        }
        return doctors;
    }
    public Doctor getDoctorById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid doctor ID.");
        }
        Doctor doctor = doctorDAO.getDoctorById(id);
        if (doctor == null) {
            System.out.println("Doctor with ID " + id + " not found.");
        }
        return doctor;
    }
    public void updateDoctor(Doctor doctor) throws SQLException {
        if (doctor.getId() <= 0) {
            throw new IllegalArgumentException("Invalid doctor ID.");
        }
        if (doctor.getFullName() == null  doctor.getFullName().isEmpty()) {
            throw new IllegalArgumentException("Doctor's name cannot be empty.");
        }
        if (doctor.getSpecialization() == null  doctor.getSpecialization().isEmpty()) {
            throw new IllegalArgumentException("Doctor's specialization cannot be empty.");
        }
        doctorDAO.updateDoctor(doctor);
    }

    public void deleteDoctor(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid doctor ID.");
        }
        doctorDAO.deleteDoctor(id);
    }
}