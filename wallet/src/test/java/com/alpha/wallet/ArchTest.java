package com.alpha.wallet;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.alpha.wallet");

        noClasses()
            .that()
            .resideInAnyPackage("com.alpha.wallet.service..")
            .or()
            .resideInAnyPackage("com.alpha.wallet.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.alpha.wallet.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
