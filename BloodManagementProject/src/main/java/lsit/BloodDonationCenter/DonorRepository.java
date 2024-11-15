package lsit.BloodDonationCenter;

import java.util.*;

import lsit.BloodDonationCenter.Donor;
import org.springframework.stereotype.Repository;

@Repository
public class DonorRepository {
    private final Map<UUID, Donor> donors = new HashMap<>();

    public List<Donor> list() {
        return new ArrayList<>(donors.values());
    }

    public Donor get(UUID id) {
        return donors.get(id);
    }

    public void add(Donor donor) {
        donors.put(donor.id, donor);
    }

    public void update(Donor donor) {
        donors.put(donor.id, donor);
    }

    public void remove(UUID id) {
        donors.remove(id);
    }
}