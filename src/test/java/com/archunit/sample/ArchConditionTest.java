package com.archunit.sample;

import com.archunit.sample.archcondition.ClassNameShouldFollowConvention;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchConditionTest {

    private static JavaClasses myClasses;

    @BeforeAll
    public static void setUp() {
        myClasses = new ClassFileImporter().importPackages("com.archunit.sample");
    }

    @Test
    public void 클래스명_컨벤션_테스트_실패() {
        ArchRule rule = classes().that().resideInAPackage("com.archunit.sample.list").should(new ClassNameShouldFollowConvention(".*List$"));
        rule.check(myClasses);
    }
}
