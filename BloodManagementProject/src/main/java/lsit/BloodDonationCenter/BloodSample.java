package lsit.BloodDonationCenter;

import java.util.UUID;
import java.time.LocalDate;

public class BloodSample {
    public UUID id;
    public UUID donorId; // Refers to the donor who gave this sample
    public LocalDate collectionDate;
    public String label;
    public String qualityStatus;
    public String status; // "collected", "tested", "stored", "discarded"
    public boolean isEligible;

    public BloodSample() {
        this.id = UUID.randomUUID();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDonorId() {
        return donorId;
    }

    public void setDonorId(UUID donorId) {
        this.donorId = donorId;
    }

    public String getQualityStatus() {
        return qualityStatus;
    }

    public void setQualityStatus(String qualityStatus) {
        this.qualityStatus = qualityStatus;
    }

    public boolean isEligible() {
        return isEligible;
    }

    public void setEligible(boolean eligible) {
        isEligible = eligible;
    }
}
