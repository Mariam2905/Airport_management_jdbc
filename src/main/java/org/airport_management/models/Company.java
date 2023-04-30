package org.airport_management.models;

import java.util.Date;
import java.util.Objects;

public class Company {
    private int companyId;
    private String companyName;
    private Date foundingDate;

    /**
     * Default constructor
     */
    public Company() {
    }

    /**
     *
     * @param companyId;
     * @param companyName;
     * @param foundingDate;
     */
    public Company(int companyId, String companyName, Date foundingDate) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.foundingDate = foundingDate;
    }

    /**
     * @param companyName;
     * @param date;
     */
    public Company(String companyName, Date date) {
        this.companyName = companyName;
        this.foundingDate = date;
    }


    /**
     * @return the companyId;
     */
    public int getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId the companyId to set;
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * @return the companyName;
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set;
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    /**
     * @return the foundingDate;
     */
    public Date getFoundingDate() {
        return foundingDate;
    }


    /**
     * @param foundingDate the foundingDate to set;
     */
    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    @Override
    public String toString() {
        return "Company {" + companyId + " " + companyName + " " + foundingDate + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Company other = (Company) obj;
        return companyId == other.companyId && Objects.equals(companyName, other.companyName) && Objects.equals(foundingDate, other.foundingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, companyName, foundingDate);
    }
}
