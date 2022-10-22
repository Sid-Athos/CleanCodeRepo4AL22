package com.cleancode.cleancodedbimpl.generators.formatters;

import com.cleancode.cleancodedbimpl.generators.UUIDGenerator;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UUIDFormatter {
    private static final Logger LOGGER = Logger.getLogger(UUIDFormatter.class.getName());
    public static Optional<String> formatUUIDSequenceWithPrefixAndSuffixOptions(UUID uuidToFormat, boolean withUnionTrailRemoval, CharSequence joiner, boolean withPrefix,
                                                                            String prefix,
                                                      boolean withSuffix, String suffix){
        if(uuidToFormat == null){
            throw new IllegalArgumentException("UUID can not be null");
        }

        String newBusinessReferenceToBindWithoutUnionTrails = uuidToFormat.toString();
        if(withPrefix){
            newBusinessReferenceToBindWithoutUnionTrails = prefix.concat(newBusinessReferenceToBindWithoutUnionTrails);
        }

        if(withSuffix){
            newBusinessReferenceToBindWithoutUnionTrails = newBusinessReferenceToBindWithoutUnionTrails.concat(suffix);
        }

        if(!withUnionTrailRemoval){
            return Optional.ofNullable(newBusinessReferenceToBindWithoutUnionTrails);
        }

        return Optional.of(String.join(Objects.requireNonNullElse(joiner, ""), newBusinessReferenceToBindWithoutUnionTrails.split("-")));
    }

    public static Optional<String> formatUUIDSequence(UUID uuidToFormat, boolean withUnionTrailRemoval, CharSequence joiner){
        if(uuidToFormat == null){
            throw new IllegalArgumentException("UUID can not be null");
        }
        LOGGER.log(Level.INFO,
                "Formatting UUIDSequence. UUID : ".concat(uuidToFormat.toString()).concat(" SplitOption : ").concat(withUnionTrailRemoval ? "true":"false").concat(" Joiner : ").concat(joiner.toString()));
        String newBusinessReferenceToBindWithoutUnionTrails = uuidToFormat.toString();

        if(!withUnionTrailRemoval){
            return Optional.ofNullable(newBusinessReferenceToBindWithoutUnionTrails);
        }

        return Optional.of(String.join(joiner, newBusinessReferenceToBindWithoutUnionTrails.split("-")));
    }
}
