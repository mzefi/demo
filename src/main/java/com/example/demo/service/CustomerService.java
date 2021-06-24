package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    /*public Customer getCustomer(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(!customer.isPresent()){
            throw new IllegalStateException("Customer not found");
        }
        return customer.get();
    }*/

    public void addNewCustomer(Customer customer){
        customerRepository.save(customer);
    }

    /*public void deleteCustomer(Long id) {
        if(!customerRepository.existsById(id)){
            throw new IllegalStateException("Customer with " + id+ "doesn't exist");
        }
        customerRepository.deleteById(id);
    }*/

    @Transactional
    public void updateCustomer(Long id, String name, String surname, String nationality, String birthDate) throws ParseException {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Customer not found"));

        if(name != null && name.length() > 0 && !Objects.equals(customer.getName(), name)){
            customer.setName(name);
        }

        if(surname != null && surname.length() > 0 && !Objects.equals(customer.getSurname(), surname)){
            customer.setSurname(surname);
        }

        if(nationality != null && nationality.length() > 0 && !Objects.equals(customer.getNationality(), nationality)){
            customer.setNationality(nationality);
        }

        if(birthDate != null && !Objects.equals(customer.getBirthDate().toString(), birthDate)){
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = formatter.parse(birthDate);
            customer.setBirthDate(date);
        }
    }

    public boolean getWorldCheck(Long id) {

        Optional<Customer> world = customerRepository.findById(id);
        if(!world.isPresent()){
            throw new IllegalStateException("No Worldcheck available");
        }
        return world.get().isWorldCheck();
    }
}
