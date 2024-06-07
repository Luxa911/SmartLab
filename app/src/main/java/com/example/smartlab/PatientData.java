package com.example.smartlab;

public class PatientData {
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthDate;
    private String gender;
    public PatientData(String firstName, String lastName, String middleName, String birthDate, String gender){
        this.firstName = firstName;
        this.lastName =lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
