package lsit.BloodDonationCenter;

import java.util.*;

import lsit.BloodDonationCenter.BloodSample;
import org.springframework.stereotype.Repository;

@Repository
public class BloodSampleRepository {
    private final Map<UUID, BloodSample> samples = new HashMap<>();

    public List<BloodSample> list() {
        return new ArrayList<>(samples.values());
    }

    public BloodSample get(UUID id) {
        return samples.get(id);
    }

    public void add(BloodSample sample) {
        samples.put(sample.id, sample);
    }

    public void update(BloodSample sample) {
        samples.put(sample.id, sample);
    }

    public void remove(UUID id) {
        samples.remove(id);
    }
}
