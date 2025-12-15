package com.ctw.workstation.exception;

/**
 * EntityNotFoundException to handle the entity not found exception
 * If the entity is not found, this exception will be thrown
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(final String message) {
        super(message);
    }
}
