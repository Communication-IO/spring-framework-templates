package com.ahlquist.commio.datajdbccrud.model;

public record User
(Long id, 
    String firstName, 
        String lastName,
          String email) {
}
