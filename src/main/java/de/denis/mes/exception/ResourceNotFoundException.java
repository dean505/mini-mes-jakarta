package de.denis.mes.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " mit ID " + id + " wurde nicht gefunden.");
    }
}