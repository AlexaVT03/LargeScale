package lsit.BloodDonationCenter;

import java.util.*;
import org.springframework.web.bind.annotation.*;

import lsit.BloodDonationCenter.BloodSample;
import lsit.BloodDonationCenter.BloodSampleRepository;

@RestController
@RequestMapping("/api/blood-samples")
public class BloodSampleController {
    private final BloodSampleRepository sampleRepository;

    public BloodSampleController(BloodSampleRepository sampleRepository){
        this.sampleRepository = sampleRepository;
    }

    @GetMapping
    public List<BloodSample> list(){
        return sampleRepository.list();
    }

    @GetMapping("/{id}")
    public BloodSample get(@PathVariable("id") UUID id){
        return sampleRepository.get(id);
    }

    @PostMapping
    public BloodSample add(@RequestBody BloodSample sample){
        sampleRepository.add(sample);
        return sample;
    }

    @PutMapping("/{id}")
    public BloodSample update(@PathVariable("id") UUID id, @RequestBody BloodSample sample){
        sample.id = id;
        sampleRepository.update(sample);
        return sample;
    }

    @PutMapping("/{id}/check-eligibility")
    public BloodSample checkEligibility(@PathVariable UUID id) {
        BloodSample bloodSample = sampleRepository.get(id);
        if (bloodSample == null) {
            throw new RuntimeException("Blood sample not found");
        }

        // Business logic to check eligibility
        boolean isEligible = "Approved".equalsIgnoreCase(bloodSample.getQualityStatus());
        bloodSample.setEligible(isEligible);
        sampleRepository.update(bloodSample);

        return bloodSample;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id){
        sampleRepository.remove(id);
    }
}