package lsit.BloodDonationCenter;

import java.util.*;

import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import lsit.BloodDonationCenter.Donor;
import lsit.BloodDonationCenter.DonorRepository;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    private final DonorRepository donorRepository;

    public DonorController(DonorRepository donorRepository){
        this.donorRepository = donorRepository;
    }

    @GetMapping
    public List<Donor> list(){
        return donorRepository.list();
    }

    @GetMapping("/{id}")
    public Donor get(@PathVariable("id") UUID id){
        return donorRepository.get(id);
    }

    @PostMapping
    public Donor add(@RequestBody Donor donor){
        donorRepository.add(donor);
        return donor;
    }

    @PutMapping("/{id}")
    public Donor update(@PathVariable("id") UUID id, @RequestBody Donor donor){
        donor.id = id;
        donorRepository.update(donor);
        return donor;
    }

    @PutMapping("/{id}/check-eligibility")
    public Donor checkEligibility(@PathVariable UUID id) {
        // Update donor eligibility based on business rules
        Donor optionalDonor = donorRepository.get(id);

        if (optionalDonor != null) {
            Donor donor = optionalDonor;
            boolean isEligible = donor.getAge() >= 18 && donor.getAge() <= 65;
            donor.setEligible(isEligible);
            donorRepository.update(donor);
            return donor; // Return the updated donor
        }

        // If donor not found, return null
        return null;
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id){
        donorRepository.remove(id);
    }
}