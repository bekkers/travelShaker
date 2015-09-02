package com.prochainvol.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.prochainvol.api.response.ReportUnit;

public class ReportUnitExclusionStrategy implements ExclusionStrategy {

    public boolean shouldSkipClass(Class<?> clazz) {
        if (clazz.equals(ReportUnit.class)
         	)
            return true;
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f) {

        return false;
    }

}
