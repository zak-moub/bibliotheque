package biblio.ynov.group8;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("biblio.ynov.group8");

        noClasses()
            .that()
                .resideInAnyPackage("biblio.ynov.group8.service..")
            .or()
                .resideInAnyPackage("biblio.ynov.group8.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..biblio.ynov.group8.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
