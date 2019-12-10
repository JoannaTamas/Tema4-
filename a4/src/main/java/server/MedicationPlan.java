package server;

import java.io.Serializable;

public class MedicationPlan implements Serializable {
    int id;
    int patient_id;
    String start_day;
    int taken;

    public MedicationPlan(int id, int patient_id, String start_day, int taken) {
        this.id = id;
        this.patient_id = patient_id;
        this.start_day = start_day;
        this.taken = taken;
    }

    public MedicationPlan() {

    }

    public int getId() {
        return id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public String getStart_day() {
        return start_day;
    }

    public int getTaken() {
        return taken;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void setStart_day(String start_day) {
        this.start_day = start_day;
    }

    public void setTaken(int taken) {
        this.taken = taken;
    }
}
