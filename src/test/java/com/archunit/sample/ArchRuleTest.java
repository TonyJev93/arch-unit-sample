package com.archunit.sample;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.archunit.sample")
public class ArchRuleTest {

    @ArchTest
    ArchRule 페키지_의존성_막는_테스트_성공 =
            noClasses().that().resideInAnyPackage("..target..")
                    .should().dependOnClassesThat().resideInAnyPackage("..foo..");
    @ArchTest
    ArchRule 페키지_의존성_막는_테스트_실패 =
            noClasses().that().resideInAnyPackage("..source..")
                    .should().dependOnClassesThat().resideInAnyPackage("..foo..");

    @ArchTest
    ArchRule 페키지_의존성_제약_걸기_테스트_성공 =
            classes().that().resideInAnyPackage("..target..")
                    .should().onlyHaveDependentClassesThat().resideInAnyPackage("..source..");

    @ArchTest
    ArchRule 페키지_의존성_제약_걸기_테스트_실패 =
            classes().that().resideInAnyPackage("..source..")
                    .should().onlyHaveDependentClassesThat().resideInAnyPackage("..target..");
}
