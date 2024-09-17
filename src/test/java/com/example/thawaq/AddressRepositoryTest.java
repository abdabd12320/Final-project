package com.example.thawaq;

import com.example.thawaq.Model.Address;
import com.example.thawaq.Model.Branch;
import com.example.thawaq.Repository.AddressRepository;
import com.example.thawaq.Repository.BranchRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BranchRepository branchRepository;

    private Address address;
    private Branch branch;

    @BeforeEach
    public void setUp() {

        branch = new Branch();
        branch.setName("Main Branch");
        branch = branchRepository.save(branch);

        address = new Address();
        address.setCity("Jeddah");
        address.setStreet("5th Avenue");
        address.setBranch(branch);
        address = addressRepository.save(address);
    }

    @Test
    public void testFindAddressById() {

        Address foundAddress = addressRepository.findAddressById(address.getId());
        Assertions.assertThat(foundAddress).isNotNull();
        Assertions.assertThat(foundAddress.getCity()).isEqualTo("Jeddah");
        Assertions.assertThat(foundAddress.getStreet()).isEqualTo("5th Avenue");
        Assertions.assertThat(foundAddress.getBranch().getName()).isEqualTo("Main Branch");
    }

    @Test
    public void testFindAddressByCity() {

        List<Address> foundAddresses = addressRepository.findAddressByCity("Jeddah");
        Assertions.assertThat(foundAddresses).isNotEmpty();
        Assertions.assertThat(foundAddresses.get(0).getCity()).isEqualTo("Jeddah");
        Assertions.assertThat(foundAddresses.get(0).getStreet()).isEqualTo("5th Avenue");
    }
}