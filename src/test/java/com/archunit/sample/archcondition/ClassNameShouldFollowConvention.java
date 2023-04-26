package com.archunit.sample.archcondition;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.properties.HasName;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

public class ClassNameShouldFollowConvention extends ArchCondition<JavaClass> {

    private final String regex;

    public ClassNameShouldFollowConvention(String regex) {
        super("have a name that follows the convention '" + regex + "'");
        this.regex = regex;
    }

    @Override
    public void check(JavaClass javaClass, ConditionEvents events) {
        if (!javaClass.isAnonymousClass() && javaClass instanceof HasName) {
            String className = ((HasName) javaClass).getName();
            if (!className.matches(regex)) {
                events.add(SimpleConditionEvent.violated(javaClass, String.format("class name %s does not match pattern %s", className, regex)));
            }
        }
    }
}

