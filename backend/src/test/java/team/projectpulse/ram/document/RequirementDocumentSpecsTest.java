package team.projectpulse.ram.document;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import team.projectpulse.AbstractIntegrationTest;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class RequirementDocumentSpecsTest extends AbstractIntegrationTest {

    @Autowired
    DocumentRepository documentRepository;

    private Locale originalLocale;

    @BeforeEach
    void rememberLocale() {
        originalLocale = Locale.getDefault();
    }

    @AfterEach
    void restoreLocale() {
        Locale.setDefault(originalLocale); // don't leak Turkish locale into other tests
    }

    @Test
    void titleSearchIsCaseInsensitiveOnTurkishLocaleServer() {
        // Seed data contains "Software Requirements Specification".
        Locale.setDefault(Locale.forLanguageTag("tr-TR"));

        List<RequirementDocument> found =
                documentRepository.findAll(RequirementDocumentSpecs.hasTitleLike("REQUIREMENTS"));

        assertThat(found).isNotEmpty();
    }
}