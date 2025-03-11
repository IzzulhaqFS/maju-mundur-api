package com.enigmacamp.maju_mundur.services.impl;

import com.enigmacamp.maju_mundur.dto.request.customer.NewCustomerRequest;
import com.enigmacamp.maju_mundur.dto.request.customer.UpdateCustomerRequest;
import com.enigmacamp.maju_mundur.dto.response.customer.CustomerResponse;
import com.enigmacamp.maju_mundur.entities.Customer;
import com.enigmacamp.maju_mundur.repositories.CustomerRepository;
import com.enigmacamp.maju_mundur.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse create(NewCustomerRequest request) {
        Customer customer = Customer.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .point(0)
                .build();

        customerRepository.saveAndFlush(customer);

        return getCustomerResponse(customer);
    }

    @Override
    public CustomerResponse getById(String id) {
        Customer customer = getCustomer(id);
        return getCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::getCustomerResponse).toList();
    }

    @Override
    public CustomerResponse update(UpdateCustomerRequest request) {
        Customer customer = getCustomer(request.getId());

        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setPoint(request.getPoint());

        customerRepository.saveAndFlush(customer);

        return getCustomerResponse(customer);
    }

    @Override
    public void delete(String id) {
        Customer customer = getCustomer(id);
        customerRepository.delete(customer);
    }

    @Override
    public void updatePoint(String id, Integer point) {
        Customer customer = getCustomer(id);
        customerRepository.updatePoint(customer.getId(), point);
    }

    private CustomerResponse getCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .point(customer.getPoint())
                .build();
    }

    private Customer getCustomer(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer data not found."));
    }
}
