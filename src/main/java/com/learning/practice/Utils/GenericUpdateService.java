package com.learning.practice.Utils;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class GenericUpdateService {

    /**
     * Update an existing entity with the provided updated entity details.
     *
     * @param existingEntity The existing entity fetched from the database.
     * @param updatedEntity  The updated entity containing new field values.
     * @param <T>            The type of the entity.
     * @return The updated entity.
     * @throws EntityNotFoundException if the existing entity is not found.
     */
    public <T> T updateEntity(T existingEntity, T updatedEntity) throws IllegalAccessException {
        // Get all fields of the entity class
        Field[] fields = existingEntity.getClass().getDeclaredFields();

        // Iterate through each field
        for (Field field : fields) {
            // Set field accessibility to true (in case of private fields)
            field.setAccessible(true);

            // Get value from updated entity
            Object updatedValue = field.get(updatedEntity);

            // Check if the value is not null and not equal to the default value of the data type
            if (updatedValue != null && !isDefaultValue(updatedValue)) {
                // Update the existing entity field with the updated value
                field.set(existingEntity, updatedValue);
            }
        }
        return existingEntity;
    }

    /**
     * Check if the given value is a default value for its data type.
     *
     * @param value The value to check.
     * @return True if the value is a default value, false otherwise.
     */
    private static boolean isDefaultValue(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue() == 0; // Check for default numeric values (0)
        } else if (value instanceof Boolean) {
            return !((Boolean) value); // Check for default boolean value (false)
        } else if (value instanceof Character) {
            return ((Character) value) == '\u0000'; // Check for default char value ('\u0000')
        }
        // Add additional checks for other data types as needed

        return false; // Default value check failed
    }

}
