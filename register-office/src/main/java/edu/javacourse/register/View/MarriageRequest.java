package edu.javacourse.register.View;

import java.io.Serializable;
import java.time.LocalDate;

public class MarriageRequest implements Serializable {
    private String husbandSurname;
    private String husbandGivenname;
    private String husbandPatronymic;
    private LocalDate husbandDateBith;
    private String husbandPassportSerial;
    private String husbandPassportNumber;
    private LocalDate husbandPassportIssueDate;
    private String wifeSurname;
    private String wifeGivenname;
    private String wifePatronymic;
    private LocalDate wifeDateBith;
    private String wifePassportSerial;
    private String wifePassportNumber;
    private LocalDate wifePassportIssueDate;

    private String certificateNumber;
    private LocalDate certificateDate;
}
